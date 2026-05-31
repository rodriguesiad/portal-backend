package portal.editais.service.projeto;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import portal.editais.dto.projeto.EvidenciaDTO;
import portal.editais.dto.projeto.EvidenciaResponseDTO;
import portal.editais.dto.projeto.ProjetoResponseDTO;
import portal.editais.dto.projeto.ValidarEvidenciaDTO;
import portal.editais.entity.AtividadeProjeto;
import portal.editais.entity.Documento;
import portal.editais.entity.Evidencia;
import portal.editais.entity.Projeto;
import portal.editais.entity.User;
import portal.editais.enumeration.ContextoDocumento;
import portal.editais.enumeration.StatusEvidencia;
import portal.editais.repository.AtividadeProjetoRepository;
import portal.editais.repository.DocumentoRepository;
import portal.editais.repository.EvidenciaRepository;
import portal.editais.repository.ProjetoRepository;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final EvidenciaRepository evidenciaRepository;
    private final DocumentoRepository documentoRepository;
    private final AtividadeProjetoRepository atividadeRepository;

    public ProjetoServiceImpl(
            ProjetoRepository projetoRepository,
            EvidenciaRepository evidenciaRepository,
            DocumentoRepository documentoRepository,
            AtividadeProjetoRepository atividadeRepository) {
        this.projetoRepository = projetoRepository;
        this.evidenciaRepository = evidenciaRepository;
        this.documentoRepository = documentoRepository;
        this.atividadeRepository = atividadeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjetoResponseDTO> listarProjetosDoProponente() {
        return projetoRepository.findBySubprojetoAutorId(getLoggedInUser().getId()).stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjetoResponseDTO> listarProjetosDoAuditor() {
        return projetoRepository.findByAuditorId(getLoggedInUser().getId()).stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProjetoResponseDTO buscarProjeto(Integer id) {
        Projeto projeto = buscarProjetoPermitido(id);
        return toResponse(projeto);
    }

    @Override
    @Transactional
    public EvidenciaResponseDTO enviarEvidencia(Integer projetoId, EvidenciaDTO dto) {
        Projeto projeto = buscarProjetoPermitido(projetoId);
        if (!projeto.getSubprojeto().getAutor().getId().equals(getLoggedInUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Somente o proponente pode enviar evidencias.");
        }

        Documento foto = documentoRepository.findById(dto.fotoDocumentoId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento da foto nao encontrado."));
        if (foto.getContexto() != ContextoDocumento.EVIDENCIA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento deve possuir contexto EVIDENCIA.");
        }

        AtividadeProjeto atividade = null;
        if (dto.atividadeId() != null) {
            atividade = atividadeRepository.findById(dto.atividadeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atividade nao encontrada."));
            if (!atividade.getProjeto().getId().equals(projetoId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atividade nao pertence ao projeto.");
            }
        }

        Evidencia evidencia = new Evidencia();
        evidencia.setProjeto(projeto);
        evidencia.setAtividade(atividade);
        evidencia.setFoto(foto);
        evidencia.setDescricao(dto.descricao());
        evidencia.setLatitude(dto.latitude());
        evidencia.setLongitude(dto.longitude());
        evidencia.setStatus(StatusEvidencia.PENDENTE);
        return EvidenciaResponseDTO.toResponse(evidenciaRepository.save(evidencia));
    }

    @Override
    @Transactional
    public EvidenciaResponseDTO validarEvidencia(Integer evidenciaId, ValidarEvidenciaDTO dto) {
        Evidencia evidencia = evidenciaRepository.findById(evidenciaId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evidencia nao encontrada."));
        if (!evidencia.getProjeto().getAuditor().getId().equals(getLoggedInUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Somente o auditor vinculado pode validar evidencias.");
        }
        if (dto.status() == StatusEvidencia.PENDENTE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Use APROVADA ou REJEITADA para validar evidencias.");
        }
        evidencia.setStatus(dto.status());
        evidencia.setComentarioAuditor(dto.comentario());
        evidencia.setValidadoPor(getLoggedInUser());
        return EvidenciaResponseDTO.toResponse(evidenciaRepository.save(evidencia));
    }

    private Projeto buscarProjetoPermitido(Integer id) {
        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto nao encontrado."));
        User usuario = getLoggedInUser();
        boolean proponente = projeto.getSubprojeto().getAutor().getId().equals(usuario.getId());
        boolean auditor = projeto.getAuditor().getId().equals(usuario.getId());
        if (!proponente && !auditor) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario nao vinculado ao projeto.");
        }
        return projeto;
    }

    private ProjetoResponseDTO toResponse(Projeto projeto) {
        List<EvidenciaResponseDTO> evidencias = evidenciaRepository.findByProjetoIdOrderByCriadoEmDesc(projeto.getId())
            .stream()
            .map(EvidenciaResponseDTO::toResponse)
            .toList();
        return ProjetoResponseDTO.toResponse(projeto, evidencias);
    }

    private User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

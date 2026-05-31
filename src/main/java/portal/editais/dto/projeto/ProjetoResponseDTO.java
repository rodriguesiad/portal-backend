package portal.editais.dto.projeto;

import java.time.LocalDateTime;
import java.util.List;
import portal.editais.dto.edital.EditalResumoResponseDTO;
import portal.editais.dto.projeto.instituicao.InstituicaoResponseDTO;
import portal.editais.dto.projeto.localizacao.LocalizacaoResponseDTO;
import portal.editais.dto.projeto.planoexecucao.PlanoExecucaoResponseDTO;
import portal.editais.dto.projeto.publico.PublicoBeneficiadoResponseDTO;
import portal.editais.entity.Projeto;
import portal.editais.enumeration.StatusProjeto;

public record ProjetoResponseDTO(
    Integer id,
    Integer editalId,
    String editalTitulo,
    String nome,
    String proponente,
    String auditor,
    StatusProjeto status,
    String resumo,
    String justificativaMerito,
    LocalDateTime criadoEm,
    EditalResumoResponseDTO edital,
    InstituicaoResponseDTO instituicao,
    LocalizacaoResponseDTO localizacao,
    PublicoBeneficiadoResponseDTO publicoBeneficiado,
    PlanoExecucaoResponseDTO planoExecucao,
    Boolean declarouVeracidadeInformacoes,
    Boolean autorizouTratamentoDadosLgpd,
    Boolean comprometeuPrestacaoContas,
    Boolean autorizouMonitoramentoAuditoria,
    List<AtividadeProjetoResponseDTO> atividades,
    List<EvidenciaResponseDTO> evidencias
) {
    public static ProjetoResponseDTO toResponse(Projeto projeto, List<EvidenciaResponseDTO> evidencias) {
        return new ProjetoResponseDTO(
            projeto.getId(),
            projeto.getEdital() != null ? projeto.getEdital().getId() : null,
            projeto.getEdital() != null ? projeto.getEdital().getTitulo() : null,
            projeto.getNomeProjeto(),
            projeto.getAutor() != null ? projeto.getAutor().getNome() : null,
            projeto.getAuditor() != null ? projeto.getAuditor().getNome() : null,
            projeto.getStatus(),
            projeto.getResumo(),
            projeto.getJustificativaMerito(),
            projeto.getCreatedAt(),
            projeto.getEdital() != null ? EditalResumoResponseDTO.toResponse(projeto.getEdital()) : null,
            projeto.getInstituicao() != null ? InstituicaoResponseDTO.toResponse(projeto.getInstituicao()) : null,
            projeto.getLocalizacao() != null ? LocalizacaoResponseDTO.toResponse(projeto.getLocalizacao()) : null,
            projeto.getPublicoBeneficiado() != null ? PublicoBeneficiadoResponseDTO.toResponse(projeto.getPublicoBeneficiado()) : null,
            projeto.getPlanoExecucao() != null ? PlanoExecucaoResponseDTO.toResponse(projeto.getPlanoExecucao()) : null,
            projeto.getDeclarouVeracidadeInformacoes(),
            projeto.getAutorizouTratamentoDadosLgpd(),
            projeto.getComprometeuPrestacaoContas(),
            projeto.getAutorizouMonitoramentoAuditoria(),
            projeto.getAtividades().stream().map(AtividadeProjetoResponseDTO::toResponse).toList(),
            evidencias
        );
    }
}

package portal.editais.dto.projeto;

import java.time.LocalDate;

import portal.editais.entity.Projeto;
import portal.editais.enumeration.StatusProjeto;

public record ProjetoResumoResponseDTO(
        Integer id,
        String nome,
        String instituicaoId,
        String razaoSocialInstituicao,
        Integer editalId,
        String nomeEdital,
        LocalDate dataCriacao,
        String progresso,
        StatusProjeto status) {

    public static ProjetoResumoResponseDTO toResponse(Projeto entity) {
        return new ProjetoResumoResponseDTO(
                entity.getId(),
                entity.getNomeProjeto(),

                entity.getInstituicao() != null
                        ? String.valueOf(entity.getInstituicao().getId())
                        : null,

                entity.getInstituicao() != null
                        ? entity.getInstituicao().getRazaoSocial()
                        : null,

                entity.getEdital() != null
                        ? entity.getEdital().getId()
                        : null,

                entity.getEdital() != null
                        ? entity.getEdital().getTitulo()
                        : null,

                entity.getCreatedAt() != null
                        ? entity.getCreatedAt().toLocalDate()
                        : null,

                entity.getProgresso(),
                entity.getStatus());
    }
}
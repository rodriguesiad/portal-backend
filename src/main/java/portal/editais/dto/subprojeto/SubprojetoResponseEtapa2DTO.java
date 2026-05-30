package portal.editais.dto.subprojeto;

import portal.editais.entity.Subprojeto;

public record SubprojetoResponseEtapa2DTO(
                Integer id,
                String nomeSubprojeto,
                Integer edital,
                String resumo,
                String justificativaMerito) {
        public static SubprojetoResponseEtapa2DTO toResponse(Subprojeto entity) {
                return new SubprojetoResponseEtapa2DTO(
                                entity.getId(),
                                entity.getNomeSubprojeto(),
                                entity.getEdital(),
                                entity.getResumo(),
                                entity.getJustificativaMerito());
        }
}

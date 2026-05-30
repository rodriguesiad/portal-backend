package portal.editais.dto.subprojeto.instituicao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import portal.editais.dto.subprojeto.instituicao.representante.RepresentanteLegalResponseDTO;
import portal.editais.entity.Instituicao;

public record InstituicaoResponseDTO(
                Integer id,
                LocalDateTime createdAt,
                LocalDateTime updatedAt,
                String nomeFantasia,
                String razaoSocial,
                String cnpj,
                LocalDate dataFundacao,
                Integer idNaturezaJuridica,
                String naturezaJuridica,
                String areaAtuacao,
                String site,
                String redesSociais,
                String situacao,
                RepresentanteLegalResponseDTO representanteLegal) {

        public static InstituicaoResponseDTO toResponse(Instituicao entity) {
                return new InstituicaoResponseDTO(
                                entity.getId(),
                                entity.getCreatedAt(),
                                entity.getUpdatedAt(),
                                entity.getNomeFantasia(),
                                entity.getRazaoSocial(),
                                entity.getCnpj(),
                                entity.getDataFundacao(),
                                entity.getNaturezaJuridica().getId(),
                                entity.getNaturezaJuridica().getNome(),
                                entity.getAreaAtuacao(),
                                entity.getSite(),
                                entity.getRedesSociais(),
                                entity.getSituacao(),
                                entity.getRepresentanteLegal() != null
                                                ? RepresentanteLegalResponseDTO
                                                                .toResponse(entity.getRepresentanteLegal())
                                                : null);
        }
}
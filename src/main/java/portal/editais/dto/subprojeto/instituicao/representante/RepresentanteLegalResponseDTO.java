package portal.editais.dto.subprojeto.instituicao.representante;

import portal.editais.entity.RepresentanteLegal;

public record RepresentanteLegalResponseDTO(
        Integer id,
        String nomeCompleto,
        String cpf,
        String rg,
        String email,
        String telefone,
        String cargo) {

    public static RepresentanteLegalResponseDTO toResponse(RepresentanteLegal entity) {
        return new RepresentanteLegalResponseDTO(
                entity.getId(),
                entity.getNomeCompleto(),
                entity.getCpf(),
                entity.getRg(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getCargo());
    }

}

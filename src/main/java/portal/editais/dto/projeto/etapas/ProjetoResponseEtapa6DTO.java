package portal.editais.dto.projeto.etapas;

import portal.editais.entity.Projeto;

public record ProjetoResponseEtapa6DTO(
                Integer id,
                Boolean declarouVeracidadeInformacoes,
                Boolean autorizouTratamentoDadosLgpd,
                Boolean comprometeuPrestacaoContas,
                Boolean autorizouMonitoramentoAuditoria) {
        public static ProjetoResponseEtapa6DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa6DTO(
                                entity.getId(),
                                entity.getDeclarouVeracidadeInformacoes(),
                                entity.getAutorizouTratamentoDadosLgpd(),
                                entity.getComprometeuPrestacaoContas(),
                                entity.getAutorizouMonitoramentoAuditoria());
        }
}

package portal.editais.dto.projeto.etapas;

import jakarta.validation.constraints.AssertTrue;

public record ProjetoEtapa6DTO(
                @AssertTrue(message = "É necessário declarar a veracidade das informações.") Boolean declarouVeracidadeInformacoes,

                @AssertTrue(message = "É necessário autorizar o tratamento de dados conforme a LGPD.") Boolean autorizouTratamentoDadosLgpd,

                @AssertTrue(message = "É necessário concordar com a prestação de contas.") Boolean comprometeuPrestacaoContas,

                @AssertTrue(message = "É necessário autorizar o monitoramento e auditoria do projeto.") Boolean autorizouMonitoramentoAuditoria) {
}

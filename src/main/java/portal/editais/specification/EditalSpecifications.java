package portal.editais.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.JoinType;
import portal.editais.entity.Edital;
import portal.editais.enumeration.SituacaoEdital;
import portal.editais.enumeration.StatusEdital;

public final class EditalSpecifications {

    private EditalSpecifications() {
    }

    public static Specification<Edital> comStatus(StatusEdital status) {
        return (root, query, builder) -> status == null
                ? builder.conjunction()
                : builder.equal(root.get("status"), status);
    }

    public static Specification<Edital> comSituacaoPublica(SituacaoEdital situacao) {
        return (root, query, builder) -> {
            if (situacao == null || situacao == SituacaoEdital.TODOS) {
                return builder.conjunction();
            }
            return builder.equal(root.get("status"), StatusEdital.valueOf(situacao.name()));
        };
    }

    public static Specification<Edital> comFrenteAtuacao(Integer frenteAtuacaoId) {
        return (root, query, builder) -> frenteAtuacaoId == null
                ? builder.conjunction()
                : builder.equal(root.join("frenteAtuacao", JoinType.INNER).get("id"), frenteAtuacaoId);
    }

    public static Specification<Edital> publico() {
        return (root, query, builder) -> builder.notEqual(root.get("status"), StatusEdital.RASCUNHO);
    }

    public static Specification<Edital> comRegiaoImediata(Integer regiaoImediataId) {
        return (root, query, builder) -> regiaoImediataId == null
                ? builder.conjunction()
                : builder.equal(root.join("regiaoImediata", JoinType.INNER).get("id"), regiaoImediataId);
    }

    public static Specification<Edital> comOrgaoProponente(Integer orgaoProponenteId) {
        return (root, query, builder) -> orgaoProponenteId == null
                ? builder.conjunction()
                : builder.equal(root.join("orgaoProponente", JoinType.INNER).get("id"), orgaoProponenteId);
    }

    public static Specification<Edital> comInicioRecebimento(LocalDate inicioRecebimento) {
        return (root, query, builder) -> inicioRecebimento == null
                ? builder.conjunction()
                : builder.greaterThanOrEqualTo(root.get("inicioRecebimentoPropostas"), inicioRecebimento);
    }

    public static Specification<Edital> comFimRecebimento(LocalDate fimRecebimento) {
        return (root, query, builder) -> fimRecebimento == null
                ? builder.conjunction()
                : builder.lessThanOrEqualTo(root.get("fimRecebimentoPropostas"), fimRecebimento);
    }
}

package portal.editais.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import portal.editais.entity.Edital;
import portal.editais.entity.Projeto;
import portal.editais.enumeration.SituacaoProjeto;

@Builder
public class ProjetoSpecification implements Specification<Projeto> {

    @Builder.Default
    private final transient Integer autorId = null;

    @Builder.Default
    private final transient String nomeProjeto = null;

    @Builder.Default
    private final transient String tituloEdital = null;

    @Builder.Default
    private final transient List<SituacaoProjeto> situacoes = null;

    @Override
    public Predicate toPredicate(
            Root<Projeto> root,
            CriteriaQuery<?> query,
            CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(autorId).ifPresent(id -> predicates.add(
                builder.equal(root.get("autor").get("id"), id)));

        Optional.ofNullable(nomeProjeto)
                .filter(s -> !s.isBlank())
                .ifPresent(nome -> predicates.add(
                        builder.like(
                                builder.lower(root.get("nomeProjeto")),
                                "%" + nome.toLowerCase() + "%")));

        Optional.ofNullable(tituloEdital)
                .filter(s -> !s.isBlank())
                .ifPresent(titulo -> {

                    Join<Projeto, Edital> editalJoin = root.join("edital");

                    predicates.add(
                            builder.like(
                                    builder.lower(editalJoin.get("nomeEdital")),
                                    "%" + titulo.toLowerCase() + "%"));
                });

        if (!ObjectUtils.isEmpty(situacoes)) {
            predicates.add(root.get("situacao").in(situacoes));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}

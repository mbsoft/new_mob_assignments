package org.denso.skateboarddataaccessor.repository.specification;

import org.denso.skateboarddataaccessor.model.Skateboard;
import org.denso.skateboarddataaccessor.model.request.FindBySpecsFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SkateboardSpecification implements Specification<Skateboard> {

    private FindBySpecsFilterRequest skateboardFilter;

    public SkateboardSpecification(FindBySpecsFilterRequest skateboardFilter) {
        super();
        this.skateboardFilter = skateboardFilter;
    }

    public Predicate toPredicate(Root<Skateboard> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        javax.persistence.criteria.Predicate p = cb.conjunction();

        if (skateboardFilter.getBrand() != null) {
            p.getExpressions().add(cb.equal(root.get("brand"), skateboardFilter.getBrand()));
        }

        if (skateboardFilter.getMinHeight() != null) {
            p.getExpressions().add(cb.greaterThanOrEqualTo(root.get("height"), skateboardFilter.getMinHeight()));
        }

        if (skateboardFilter.getMaxHeight() != null) {
            p.getExpressions().add(cb.lessThanOrEqualTo(root.get("height"), skateboardFilter.getMaxHeight()));
        }

        if (skateboardFilter.getMinWidth() != null) {
            p.getExpressions().add(cb.greaterThanOrEqualTo(root.get("width"), skateboardFilter.getMinWidth()));
        }

        if (skateboardFilter.getMaxWidth() != null) {
            p.getExpressions().add(cb.lessThanOrEqualTo(root.get("width"), skateboardFilter.getMinWidth()));
        }

        if (skateboardFilter.getMinLength() != null) {
            p.getExpressions().add(cb.greaterThanOrEqualTo(root.get("length"), skateboardFilter.getMinLength()));
        }

        if (skateboardFilter.getMaxLength() != null) {
            p.getExpressions().add(cb.lessThanOrEqualTo(root.get("length"), skateboardFilter.getMaxLength()));
        }

        if (skateboardFilter.getMinWeight() != null) {
            p.getExpressions().add(cb.greaterThanOrEqualTo(root.get("weight"), skateboardFilter.getMinWeight()));
        }

        if (skateboardFilter.getMaxWeight() != null) {
            p.getExpressions().add(cb.lessThanOrEqualTo(root.get("weight"), skateboardFilter.getMaxWeight()));
        }

        // Ignore unavailable records
        p.getExpressions().add(cb.equal(root.get("available"), true));

        return p;
    }
}

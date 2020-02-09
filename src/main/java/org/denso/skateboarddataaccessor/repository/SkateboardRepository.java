package org.denso.skateboarddataaccessor.repository;

import org.denso.skateboarddataaccessor.model.Skateboard;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SkateboardRepository extends JpaRepository<Skateboard, Long> {

    List<Skateboard> findAllByAvailableIsTrue();

    List<Skateboard> findByAvailableIsTrueAndBrandEquals(String brandName);

    <T> Object findAll(Specification<T> where);
}

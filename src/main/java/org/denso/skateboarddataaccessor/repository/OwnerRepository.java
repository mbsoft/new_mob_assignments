package org.denso.skateboarddataaccessor.repository;

import org.denso.skateboarddataaccessor.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findByNameEquals(String name);
}

package com.denso.demo.repository;

import com.denso.demo.entity.SkateBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkateBoardRepository extends JpaRepository<SkateBoardEntity, Long> {
}

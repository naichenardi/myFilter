package com.korber.myfilter.db.repositories;

import com.korber.myfilter.db.entities.MyFilterAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFilterAuditRepository extends JpaRepository<MyFilterAudit, Integer> {
}
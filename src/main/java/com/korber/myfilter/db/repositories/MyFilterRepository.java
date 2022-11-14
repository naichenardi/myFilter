package com.korber.myfilter.db.repositories;

import com.korber.myfilter.db.entities.MyFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface MyFilterRepository extends JpaRepository<MyFilter, UUID> {
    @Transactional
    @Modifying
    @Query(value = "update myfilter set status = ? where parent_id = ?", nativeQuery = true)
    void deprecateBranchesByIdFilter(String status, UUID id);

    @Transactional
    @Modifying
    @Query(value = "update myfilter set version = ? where parent_id = ?", nativeQuery = true)
    void updateAllBranches(Integer version, UUID id);
}

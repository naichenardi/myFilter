package com.korber.myfilter.db.repositories;

import com.korber.myfilter.db.entities.MyFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MyFilterRepository extends JpaRepository<MyFilter, UUID> {
    @Modifying
    @Query(value = "update myfilter set status = 'DEPRECATED' where parent_id = :id",nativeQuery = true)
    void deprecateBranchesByIdFilter(@Param(value = "id") UUID id);
}

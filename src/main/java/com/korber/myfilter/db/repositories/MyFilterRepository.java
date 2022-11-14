package com.korber.myfilter.db.repositories;

import com.korber.myfilter.db.model.MyFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MyFilterRepository extends JpaRepository<MyFilter, UUID> {

    @Modifying
    @Query(value = "update myfilter f set version = :version where id = :id", nativeQuery = true)
    void alterFilterInsertVersion(@Param("version")int version, @Param("id")UUID id);
}
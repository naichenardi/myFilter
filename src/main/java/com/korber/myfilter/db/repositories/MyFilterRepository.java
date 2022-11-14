package com.korber.myfilter.db.repositories;

import com.korber.myfilter.db.entities.MyFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MyFilterRepository extends JpaRepository<MyFilter, UUID> {}

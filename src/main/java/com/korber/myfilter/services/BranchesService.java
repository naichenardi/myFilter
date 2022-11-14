package com.korber.myfilter.services;

import com.korber.myfilter.db.entities.Branch;

import java.util.UUID;

public interface BranchesService {
    void deprecateBranchesFromFilter(UUID filterId);

}

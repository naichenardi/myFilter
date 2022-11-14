package com.korber.myfilter.services;

import com.korber.myfilter.db.entities.MyFilter;

import java.util.UUID;

public interface BranchesService {
    void deprecateBranchesFromFilter(UUID filterId);

    MyFilter createBranch(UUID idFilter) ;

    void updateAllBranches(MyFilter filter);
}

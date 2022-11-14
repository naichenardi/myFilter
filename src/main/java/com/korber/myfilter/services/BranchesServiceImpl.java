package com.korber.myfilter.services;

import com.korber.myfilter.db.repositories.MyFilterAuditRepository;
import com.korber.myfilter.db.repositories.MyFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BranchesServiceImpl implements BranchesService{
    private final MyFilterRepository filterRepository;
    private final MyFilterAuditRepository auditRepository;

    @Autowired
    public BranchesServiceImpl(MyFilterRepository filterRepository, MyFilterAuditRepository auditRepository) {
        this.filterRepository = filterRepository;
        this.auditRepository = auditRepository;
    }

    @Override
    public void deprecateBranchesFromFilter(UUID filterId) {
        filterRepository.deprecateBranchesByIdFilter(filterId);
    }
}

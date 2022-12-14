package com.korber.myfilter.services;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.db.entities.MyFilterAudit;
import com.korber.myfilter.db.entities.enumm.StatusFilter;
import com.korber.myfilter.db.repositories.MyFilterAuditRepository;
import com.korber.myfilter.db.repositories.MyFilterRepository;
import com.korber.myfilter.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BranchesServiceImpl implements BranchesService {
    private final MyFilterRepository filterRepository;
    private final MyFilterAuditRepository auditRepository;

    @Autowired
    public BranchesServiceImpl(MyFilterRepository filterRepository, MyFilterAuditRepository auditRepository) {
        this.filterRepository = filterRepository;
        this.auditRepository = auditRepository;
    }

    @Override
    public void deprecateBranchesFromFilter(UUID filterId) {
        filterRepository.deprecateBranchesByIdFilter(StatusFilter.DEPRECATED.name(), filterId);
    }

    @Override
    public MyFilter createBranch(UUID idFilter) {
        Optional<MyFilter> result = filterRepository.findById(idFilter);

        MyFilter filter = result.orElseThrow(() -> {
            throw new ServiceException("Filter not found!");
        });
        MyFilter branch = filter.createBranch();

        return saveFilter(branch);
    }

    @Override
    public void updateAllBranches(MyFilter filter) {
        filterRepository.updateAllBranches(filter.getVersion(), filter.getId());
    }

    @Override
    public void mergeBranch(UUID branchId) {
        Optional<MyFilter> filter = filterRepository.findByIdAndParentIsNotNull(branchId);

        filter.ifPresentOrElse(f -> {
            f.getParent().merge(f);
            saveFilter(f);
            deprecateBranchesFromFilter(f.getParent().getId());
        }, () -> {
            throw new ServiceException("Branch no found!");
        });
    }

    @Override
    public MyFilter updateBranch(UUID id, MyFilter branch) {
        Optional<MyFilter> branchDB = filterRepository.findById(id);
        MyFilter filter = branchDB.orElseThrow(ServiceException::new);
        filter.merge(branch);
        return saveFilter(filter);
    }

    private MyFilter saveFilter(MyFilter branch) {
        auditRepository.saveAndFlush(new MyFilterAudit(filterRepository.saveAndFlush(branch)));
        return branch;
    }
}

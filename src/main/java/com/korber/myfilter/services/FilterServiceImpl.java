package com.korber.myfilter.services;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.db.entities.MyFilterAudit;
import com.korber.myfilter.db.entities.Screen;
import com.korber.myfilter.db.entities.User;
import com.korber.myfilter.db.repositories.MyFilterAuditRepository;
import com.korber.myfilter.db.repositories.MyFilterRepository;
import com.korber.myfilter.db.repositories.ScreenRepository;
import com.korber.myfilter.db.repositories.UserRepository;
import com.korber.myfilter.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilterServiceImpl implements FilterService {

    private final BranchesService branchesService;
    private final MyFilterRepository filterRepository;
    private final MyFilterAuditRepository auditRepository;
    private final UserRepository userRepository;
    private final ScreenRepository screenRepository;

    @Autowired
    public FilterServiceImpl(BranchesService branchesService, MyFilterRepository filterRepository, MyFilterAuditRepository auditRepository, UserRepository userRepository, ScreenRepository screenRepository) {
        this.branchesService = branchesService;
        this.filterRepository = filterRepository;
        this.auditRepository = auditRepository;
        this.userRepository = userRepository;
        this.screenRepository = screenRepository;
    }

    @Override
    public MyFilter save(MyFilter myFilter) {
        try {
            if (myFilter.getUser() != null) {
                Optional<User> user = userRepository.findById(myFilter.getUser().getId());
                myFilter.setUser(user.orElseThrow(() -> {throw new ServiceException("User id not found!");}));
            }
            if (myFilter.getScreen() != null) {
                Optional<Screen> screen = screenRepository.findById(myFilter.getScreen().getId());
                myFilter.setScreen(screen.orElseThrow(() -> {throw new ServiceException("Screen id not found!");}));
            }

            MyFilter filter = filterRepository.saveAndFlush(myFilter);
            auditRepository.saveAndFlush(new MyFilterAudit(filter));

            return filter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MyFilter update(String id,boolean deprecateBranches, MyFilter myFilter) throws ServiceException {
        Optional<MyFilter> filterDB = filterRepository.findById(UUID.fromString(id));
        MyFilter filter = filterDB.orElseThrow(ServiceException::new);
        filter.fillFields(myFilter);
        auditRepository.saveAndFlush(new MyFilterAudit(filter));
        branchesService.deprecateBranchesFromFilter(filter.getId());
        return filterRepository.saveAndFlush(filter);
    }

    @Override
    public List<MyFilter> listAllActiveFilters() {
        return filterRepository.findAll();
    }

    @Override
    public void delete(MyFilter myFilter) {
        filterRepository.delete(myFilter);
    }
}

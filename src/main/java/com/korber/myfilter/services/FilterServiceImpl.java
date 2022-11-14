package com.korber.myfilter.services;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.db.entities.MyFilterAudit;
import com.korber.myfilter.db.entities.Screen;
import com.korber.myfilter.db.entities.User;
import com.korber.myfilter.db.repositories.MyFilterAuditRepository;
import com.korber.myfilter.db.repositories.MyFilterRepository;
import com.korber.myfilter.db.repositories.ScreenRepository;
import com.korber.myfilter.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilterServiceImpl implements FilterService{

    private final MyFilterRepository filterRepository;
    private final MyFilterAuditRepository auditRepository;
    private final UserRepository userRepository;
    private final ScreenRepository screenRepository;

    @Autowired
    public FilterServiceImpl(MyFilterRepository filterRepository, MyFilterAuditRepository auditRepository, UserRepository userRepository, ScreenRepository screenRepository) {
        this.filterRepository = filterRepository;
        this.auditRepository = auditRepository;
        this.userRepository = userRepository;
        this.screenRepository = screenRepository;
    }

    @Override
    public MyFilter save(MyFilter myFilter) {
        try {
            if (myFilter.getUser() != null){
                Optional<User> user = userRepository.findById(myFilter.getUser().getId());
                user.ifPresent(myFilter::setUser);
            }
            if (myFilter.getScreen() != null){
                Optional<Screen> screen = screenRepository.findById(myFilter.getScreen().getId());
                screen.ifPresent(myFilter::setScreen);
            }

            MyFilter filter = filterRepository.saveAndFlush(myFilter);

            auditRepository.saveAndFlush(new MyFilterAudit(filter));

            return filter;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MyFilter update(MyFilter myFilter) {
        filterRepository.save(myFilter);
        return myFilter;
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

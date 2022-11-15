package com.korber.myfilter.services;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    MyFilter save(MyFilter myFilter);
    MyFilter update(String id, boolean deprecateBranches, MyFilter myFilter) throws ServiceException;
    Page<MyFilter> listAllActiveFilters(Pageable pageable);
    void delete(UUID id);
}

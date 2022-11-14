package com.korber.myfilter.services;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.exception.ServiceException;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    MyFilter save(MyFilter myFilter);
    MyFilter update(String id, boolean deprecateBranches, MyFilter myFilter) throws ServiceException;
    List<MyFilter> listAllActiveFilters();
    void delete(UUID id);
}

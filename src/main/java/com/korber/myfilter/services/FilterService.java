package com.korber.myfilter.services;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.exception.ServiceException;

import java.util.List;

public interface FilterService {
    MyFilter save(MyFilter myFilter);
    MyFilter update(String id, boolean deprecateBranches, MyFilter myFilter) throws ServiceException;
    List<MyFilter> listAllActiveFilters();
    void delete(MyFilter myFilter);
}

package com.korber.myfilter.services;

import com.korber.myfilter.db.entities.MyFilter;

import java.util.List;

public interface FilterService {
    MyFilter save(MyFilter myFilter);
    MyFilter update(MyFilter myFilter);
    List<MyFilter> listAllActiveFilters();
    void delete(MyFilter myFilter);
}

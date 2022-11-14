package com.korber.myfilter.controller;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.services.FilterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {
    private final FilterService service;

    public FilterController(FilterService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<MyFilter> getAllActiveFilters(){
        return service.listAllActiveFilters();
    }

    @PostMapping
    @ResponseBody
    public MyFilter save(@RequestBody MyFilter filter){
        return service.save(filter);
    }

    @PutMapping
    @ResponseBody
    public MyFilter update(@RequestBody MyFilter filter){
        return service.update(filter);
    }
}

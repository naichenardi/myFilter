package com.korber.myfilter.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.services.FilterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filter")
public class FilterController {
    private final FilterService service;

    public FilterController(FilterService service) {
        this.service = service;
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    public MyFilter save(@RequestBody MyFilter filter){
        return service.save(filter);
    }
}

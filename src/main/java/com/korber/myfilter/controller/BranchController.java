package com.korber.myfilter.controller;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.services.BranchesService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/branch")
public class BranchController {
    private final BranchesService service;

    public BranchController(BranchesService service) {
        this.service = service;
    }

    @PostMapping(path = "/{filter_id}", produces = "application/json")
    public MyFilter create(@PathVariable("filter_id") String filterId) {
        return service.createBranch(UUID.fromString(filterId));
    }
}

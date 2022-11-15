package com.korber.myfilter.controller;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.exception.ServiceException;
import com.korber.myfilter.services.BranchesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(path = "/{branch_id}/merge", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void merge(@PathVariable("branch_id") String branchId) {
        service.mergeBranch(UUID.fromString(branchId));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MyFilter update(@PathVariable String id, @RequestBody MyFilter branch) throws ServiceException {
        return service.updateBranch(UUID.fromString(id), branch);
    }

}

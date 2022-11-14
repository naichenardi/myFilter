package com.korber.myfilter.controller;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.exception.ServiceException;
import com.korber.myfilter.services.FilterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/filter")
public class FilterController {
    private final FilterService service;

    public FilterController(FilterService service) {
        this.service = service;
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    public MyFilter create(@RequestBody MyFilter filter){
        return service.save(filter);
    }

    @PutMapping(path = "/{id}",produces = "application/json")
    @ResponseBody
    public MyFilter update(
            @PathVariable String id,
            @RequestParam(name = "deprecateBranches") boolean deprecateBranches,
            @RequestBody MyFilter filter) throws ServiceException {
        return service.update(id, deprecateBranches, filter);
    }

    @DeleteMapping(path = "/{id}",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        service.delete(UUID.fromString(id));
    }
}

package com.amoghdambal.solvedoku.controller;

import com.amoghdambal.solvedoku.data.Grid;
import com.amoghdambal.solvedoku.service.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class GridController {
    @Autowired
    private GridService gridService;

    @PostMapping
    public Grid create(@RequestBody Grid g) {
        return gridService.save(g);
    }

    @PutMapping("/{id}")
    public Grid update(@RequestBody Grid g) {
        return gridService.save(g);
    }

    @GetMapping("/{id}")
    public Grid find(@PathVariable String id) {
        return gridService.find(id);
    }

    @GetMapping
    public List<Grid> findAll() {
        return gridService.findAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        gridService.delete(id);
    }
}

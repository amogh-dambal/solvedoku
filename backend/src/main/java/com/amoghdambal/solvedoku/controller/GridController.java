package com.amoghdambal.solvedoku.controller;

import com.amoghdambal.solvedoku.data.Grid;
import com.amoghdambal.solvedoku.service.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class GridController {
    private final Logger LOGGER = Logger.getLogger(String.valueOf(GridController.class));

    @Autowired
    private GridService service;

    /* Basic CRUD Mappings */
    @GetMapping("/{id}")
    public Grid getGrid(@PathVariable String id) {
        return service.find(id);
    }

    @GetMapping
    public List<Grid> getGrids() {
        return service.findAll();
    }

    @PostMapping
    public Grid createGrid(@RequestBody Grid g) {
        return service.save(g);
    }

    @PutMapping("/{id}")
    public Grid updateGrid(@PathVariable String id, @RequestBody Grid g) {
        Grid current = service.find(id);
        current.setGridRepresentation(g.getGridRepresentation());

        return service.save(current);
    }

    @DeleteMapping("/{id}")
    public void deleteGrid(@PathVariable String id) {
        this.service.delete(id);
    }

    /* Solver mappings */
    @GetMapping("/solve")
    public Grid solveGrid(@RequestBody Grid g) {
        Grid solved = service.solve(g);
        return solved;
    }
}

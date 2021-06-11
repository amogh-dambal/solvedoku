package com.amoghdambal.solvedoku.service;

import com.amoghdambal.solvedoku.data.Grid;
import com.amoghdambal.solvedoku.exception.EntityNotFoundException;
import com.amoghdambal.solvedoku.repository.GridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridService {
    @Autowired
    private GridRepository repository;

    public Grid save(Grid g) {
        return this.repository.save(g);
    }

    public Grid find(String id) {
        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Grid> findAll() {
        return this.repository.findAll();
    }

    public void delete(String id) {
        this.repository.deleteById(id);
    }

    public Grid solve(Grid g) {
        GridSolver solver = new GridSolver(g);
        return solver.getSolution();
    }
}

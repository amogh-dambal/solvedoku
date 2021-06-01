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
    private GridRepository gridRepository;

    public List<Grid> findAll() {
        return gridRepository.findAll();
    }

    public Grid save(Grid g) {
        return gridRepository.save(g);
    }

    public Grid find(String id) {
        return gridRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void delete(String id) {
        gridRepository.deleteById(id);
    }
}

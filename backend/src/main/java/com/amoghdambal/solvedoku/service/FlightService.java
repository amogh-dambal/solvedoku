package com.amoghdambal.solvedoku.service;

import com.amoghdambal.solvedoku.data.Flight;
import com.amoghdambal.solvedoku.exception.EntityNotFoundException;
import com.amoghdambal.solvedoku.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight save(Flight f) {
        return flightRepository.save(f);
    }

    public Flight find(Long id) {
        return flightRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}

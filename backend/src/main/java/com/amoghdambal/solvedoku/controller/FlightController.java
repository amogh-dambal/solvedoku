package com.amoghdambal.solvedoku.controller;

import com.amoghdambal.solvedoku.data.Flight;
import com.amoghdambal.solvedoku.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/test")
public class FlightController {
    private final Logger LOGGER = Logger.getLogger(String.valueOf(FlightController.class));

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getFlights() {
        return flightService.findAll();
    }

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable Long id) {
        return flightService.find(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight)  {
        return flightService.save(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight f) {
        Flight currentFlight = flightService.find(id);

        currentFlight.setOrigin(f.getOrigin());
        currentFlight.setDestination((f.getDestination()));
        currentFlight.setFlightNumber(f.getFlightNumber());
        currentFlight.setAirline((f.getAirline()));

        return flightService.save(currentFlight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        this.flightService.delete(id);
    }
}

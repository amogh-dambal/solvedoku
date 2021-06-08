package com.amoghdambal.solvedoku.repository;

import com.amoghdambal.solvedoku.data.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<Flight, Long> {
}

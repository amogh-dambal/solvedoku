package com.amoghdambal.solvedoku.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.amoghdambal.solvedoku.data.Grid;

public interface GridRepository extends MongoRepository<Grid, String> {
}

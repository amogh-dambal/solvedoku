package com.amoghdambal.solvedoku.repository;

import com.amoghdambal.solvedoku.data.Grid;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GridRepository extends MongoRepository<Grid, String> {
}

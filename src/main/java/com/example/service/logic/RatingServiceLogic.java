package com.example.service.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.example.entity.cdo.SaveResult;
import com.example.exception.WrongRatingException;
import com.example.service.RatingService;

@Service
public class RatingServiceLogic implements RatingService{
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Integer> findMovieIdsByAvgRating(double criteriaAvg) {
        if(criteriaAvg < 1 || criteriaAvg > 5){
            throw new WrongRatingException(criteriaAvg);
        }
        GroupOperation groupByMovieId = Aggregation.group("movieId").avg("rating").as("avgRating");
        MatchOperation filterByAvgRating = Aggregation.match(Criteria.where("avgRating").gte(criteriaAvg));
        Aggregation aggregation = Aggregation.newAggregation(groupByMovieId, filterByAvgRating);
        AggregationResults<SaveResult> results = mongoTemplate.aggregate(aggregation, "rating", SaveResult.class);

        return results.getMappedResults().stream().map(SaveResult::getId).collect(Collectors.toList());
    }
}

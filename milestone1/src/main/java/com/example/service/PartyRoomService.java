package com.example.service;

import com.example.entity.Movie;
import com.example.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.mongodb.client.result.DeleteResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyRoomService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public PartyRoomService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Movie> findRandomTop3Movies() {
        // MongoDB 집계 파이프라인을 사용하여 각기 다른 장르의 영화 3개를 무작위로 선택
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.sample(3)  // 전체 컬렉션에서 무작위로 3개의 영화를 선택
        );
        AggregationResults<Movie> aggResults = mongoTemplate.aggregate(aggregation, "movie", Movie.class);
        return aggResults.getMappedResults();
    }

    // This method should retrieve a movie by its ID from the database
    public Movie getMovieById(int movieId) {
        Query query = new Query(Criteria.where("movieId").is(movieId));
        return mongoTemplate.findOne(query, Movie.class);
    }

    public Content createContent(Content content) {
        // MongoDB에 Content 객체 저장
        return mongoTemplate.save(content);
    }

    public Content getContentByContentName(String contentName) {
        Query query = new Query(Criteria.where("name").is(contentName));
        return mongoTemplate.findOne(query, Content.class);
    }

    public List<Content> findAllContents() {
        return mongoTemplate.findAll(Content.class);
    }

    public boolean deleteContentByName(String contentName) {
        // MongoDB에서 해당 contentName을 가진 Content 객체를 찾아 삭제
        Query query = new Query(Criteria.where("name").is(contentName));
        DeleteResult result = mongoTemplate.remove(query, Content.class);
        return result.getDeletedCount() > 0;
    }
    
}

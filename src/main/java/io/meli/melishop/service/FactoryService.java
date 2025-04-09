package io.meli.melishop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.meli.melishop.db.Db;
import io.meli.melishop.factory.RecommendationStrategyFactory;
import io.meli.melishop.model.User;
import io.meli.melishop.strategy.RecommendationStrategy;

@Service
public class FactoryService {

    
    @Autowired
    Db db;
    
    @Cacheable
    public Map<String, List<String>> recommend(Long userId, String strategy) {
        
        User user = Db.getUserById(userId);
        
        RecommendationStrategy recommendationStrategy = new RecommendationStrategyFactory().createRecommendationStrategy(strategy);

        List<String> recommendation = recommendationStrategy.recommendProducts(user);

        return Map.of("Recommended products for you, " + user.toString() + ", considering " + recommendationStrategy.getStrategyType() , recommendation);

    }

}

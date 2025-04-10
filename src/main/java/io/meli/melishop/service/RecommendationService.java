package io.meli.melishop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.meli.melishop.db.UserRepo;
import io.meli.melishop.enums.EnumStrategyType;
import io.meli.melishop.factory.RecommendationStrategyFactory;
import io.meli.melishop.model.User;
import io.meli.melishop.strategy.RecommendationStrategy;

@Service
public class RecommendationService {

    @Autowired
    RecommendationStrategyFactory factory;
    
    @Cacheable
    public Map<String, List<String>> recommend(Long userId, EnumStrategyType strategy) {
        
        User user = UserRepo.getUserById(userId);
        
        RecommendationStrategy recommendationStrategy = factory.createRecommendationStrategy(strategy);

        List<String> recommendation = recommendationStrategy.recommendProducts(user);

        return Map.of("Recommended products for you, considering " + strategy , recommendation);

    }

}

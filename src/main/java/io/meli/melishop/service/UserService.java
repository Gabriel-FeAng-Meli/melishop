package io.meli.melishop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.meli.melishop.db.Db;
import io.meli.melishop.factory.HistoryStrategyFactory;
import io.meli.melishop.factory.PopularityStrategyFactory;
import io.meli.melishop.factory.SimilarityStrategyFactory;
import io.meli.melishop.model.User;
import io.meli.melishop.strategy.RecommendationStrategy;

@Service
public class UserService {

    private final Map<String, RecommendationStrategy> chooseStrategy = Map.of(
        "HISTORY", new HistoryStrategyFactory().createRecommendationStrategy(),
        "POPULARITY", new PopularityStrategyFactory().createRecommendationStrategy(),
        "SIMILARITY", new SimilarityStrategyFactory().createRecommendationStrategy()
    );

    @Autowired
    Db db;

    @Cacheable
    public Map<String, List<String>> recommend(Long userId, String strategy) {
        
        User user = db.getUserById(userId);

        List<String> recommendation = chooseStrategy.get(strategy).recommendProducts(user);

        return Map.of("Recommended products for you, " + user.toString() + ", considering " + strategy, recommendation);

    }

}

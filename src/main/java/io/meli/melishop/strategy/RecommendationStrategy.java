package io.meli.melishop.strategy;

import java.util.List;

import io.meli.melishop.model.User;

public interface RecommendationStrategy {
    
    List<String> recommendProducts(User user);

    String getStrategyType();
    
}

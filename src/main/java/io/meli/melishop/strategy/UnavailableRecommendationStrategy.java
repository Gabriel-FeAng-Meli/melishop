package io.meli.melishop.strategy;

import java.util.List;

import org.springframework.stereotype.Component;

import io.meli.melishop.model.User;

@Component
public class UnavailableRecommendationStrategy implements RecommendationStrategy {

    @Override
    public List<String> recommendProducts(User user) {
        List<String> recommendation = List.of("The selected recommendation type is not avaiable");
        return recommendation;
    }
}

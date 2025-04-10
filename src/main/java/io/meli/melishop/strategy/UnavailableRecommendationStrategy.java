package io.meli.melishop.strategy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.meli.melishop.model.User;

@Component
public class UnavailableRecommendationStrategy extends AbstractRecommendationStrategy {

    @Override
    public List<String> recommendProducts(User user) {
        List<String> recommendation = List.of("The selected recommendation type is not avaiable");
        return recommendation;
    }
    
    @Override
    public List<String> createTopThree(Map<String, Integer> userHistory) {
        List<String> topThreeMostBoughtByUser = List.of("");
        return topThreeMostBoughtByUser;
    }
}

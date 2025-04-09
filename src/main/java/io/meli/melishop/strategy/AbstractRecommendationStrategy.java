package io.meli.melishop.strategy;

import java.util.List;
import java.util.Map;

import io.meli.melishop.model.User;

public abstract class AbstractRecommendationStrategy implements RecommendationStrategy {

    User userExample;
    Map<String, Integer> userHistoryORProduct;

    public abstract List<String> createTopThree(Map<String, Integer> objectFromFakeDb);

    final void createRecommendation() {
        createTopThree(userHistoryORProduct);
        recommendProducts(userExample);
    }

}

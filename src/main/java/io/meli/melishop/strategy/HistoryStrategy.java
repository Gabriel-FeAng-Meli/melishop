package io.meli.melishop.strategy;

import java.util.List;
import java.util.Map;


import io.meli.melishop.model.User;
import io.meli.melishop.util.MapUtils;

public class HistoryStrategy extends AbstractRecommendationStrategy {

    @Override
    public List<String> recommendProducts(User user) {
        List<String> recommendation = createTopThree(user.history());
        return recommendation;
    }
    
    @Override
    public List<String> createTopThree(Map<String, Integer> userHistory) {
        List<String> topThreeMostBoughtByUser = MapUtils.createTopThreeListFromMapValue(userHistory);
        return topThreeMostBoughtByUser;
    }
}

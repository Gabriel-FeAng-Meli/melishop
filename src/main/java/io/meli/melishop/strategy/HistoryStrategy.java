package io.meli.melishop.strategy;

import java.util.List;

import io.meli.melishop.db.Db;
import io.meli.melishop.model.User;

public class HistoryStrategy implements RecommendationStrategy {
    
    public List<String> recommendProducts(User user) {

        List<String> topThree = Db.createTopThreeList(user.getHistory());

        return topThree;
    }
}

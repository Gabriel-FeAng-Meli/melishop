package io.meli.melishop.strategy;

import java.util.List;

import io.meli.melishop.db.Db;
import io.meli.melishop.model.User;

public class PopularityStrategy implements RecommendationStrategy {

    public List<String> recommendProducts(User user) {

        return Db.createTopThreeList(Db.allProducts);

    }
}

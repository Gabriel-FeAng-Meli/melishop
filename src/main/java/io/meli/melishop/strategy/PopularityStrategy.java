package io.meli.melishop.strategy;

import java.util.List;
import java.util.Map;

import io.meli.melishop.db.Db;
import io.meli.melishop.model.User;
import io.meli.melishop.util.MapUtils;

public class PopularityStrategy extends AbstractRecommendationStrategy {

    @Override
    public String getStrategyType() {
        return "popularity";
    }

    @Override
    public List<String> recommendProducts(User user) {

        List<String> recommendation = createTopThree(Db.allProducts);

        return recommendation;
    }

    @Override
    public List<String> createTopThree(Map<String, Integer> productsOnDatabase) {

        List<String> topThreeMostSoldProducts = MapUtils.createTopThreeListFromMapValue(productsOnDatabase);

        return topThreeMostSoldProducts;
    }
}

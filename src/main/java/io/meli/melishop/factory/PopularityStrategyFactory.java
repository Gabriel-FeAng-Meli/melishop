package io.meli.melishop.factory;

import io.meli.melishop.strategy.PopularityStrategy;
import io.meli.melishop.strategy.RecommendationStrategy;

public class PopularityStrategyFactory extends RecommendationStrategyFactory {
    public RecommendationStrategy createRecommendationStrategy() {
        return new PopularityStrategy();
    }
}

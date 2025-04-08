package io.meli.melishop.factory;

import io.meli.melishop.strategy.HistoryStrategy;
import io.meli.melishop.strategy.RecommendationStrategy;

public class HistoryStrategyFactory extends RecommendationStrategyFactory {
    public RecommendationStrategy createRecommendationStrategy() {
        return new HistoryStrategy();
    }
}

package io.meli.melishop.factory;

import io.meli.melishop.strategy.HistoryStrategy;
import io.meli.melishop.strategy.RecommendationStrategy;

public class HistoryStrategyFactory extends RecommendationStrategyFactory {

    @Override
    public RecommendationStrategy createRecommendationStrategy() {
        return new HistoryStrategy();
    }
}

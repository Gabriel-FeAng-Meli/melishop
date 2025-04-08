package io.meli.melishop.factory;

import io.meli.melishop.strategy.RecommendationStrategy;
import io.meli.melishop.strategy.SimilarityStrategy;

public class SimilarityStrategyFactory extends RecommendationStrategyFactory {
    public RecommendationStrategy createRecommendationStrategy() {
        return new SimilarityStrategy();
    }
}

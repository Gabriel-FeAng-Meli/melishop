package io.meli.melishop.factory;

import io.meli.melishop.enums.EnumStrategyType;
import io.meli.melishop.strategy.HistoryStrategy;
import io.meli.melishop.strategy.PopularityStrategy;
import io.meli.melishop.strategy.RecommendationStrategy;
import io.meli.melishop.strategy.SimilarityStrategy;

public class RecommendationStrategyFactory {

    public RecommendationStrategy createRecommendationStrategy(EnumStrategyType type) {
        switch (type) {
            case HISTORY: return new HistoryStrategy();
            case POPULARITY: return new PopularityStrategy();
            case SIMILARITY: return new SimilarityStrategy();
            default: return null;
        }
    }

}

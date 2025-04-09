package io.meli.melishop.factory;

import io.meli.melishop.strategy.RecommendationStrategy;

public class RecommendationStrategyFactory {

    public RecommendationStrategy createRecommendationStrategy(String type) {
        if (typeIsHistory(type)) {
            return new HistoryStrategyFactory().createRecommendationStrategy();
        } else if (typeIsSimilarity(type)) {
            return new SimilarityStrategyFactory().createRecommendationStrategy();
        } else {
            return new PopularityStrategyFactory().createRecommendationStrategy();
        }
    }

    public RecommendationStrategy createRecommendationStrategy() {
        return new PopularityStrategyFactory().createRecommendationStrategy();
    }

    boolean typeIsHistory(String type) {
        return type.equalsIgnoreCase("HISTORY");
    }

    boolean typeIsSimilarity(String type) {
        return type.equalsIgnoreCase("SIMILARITY");
    }

}

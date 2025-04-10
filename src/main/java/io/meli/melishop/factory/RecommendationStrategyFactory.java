package io.meli.melishop.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.meli.melishop.enums.EnumStrategyType;
import io.meli.melishop.strategy.UnavailableRecommendationStrategy;
import io.meli.melishop.strategy.RecommendationStrategy;

@Component
public class RecommendationStrategyFactory {

    @Autowired
    @Qualifier(value = "historyStrategy")
    RecommendationStrategy historyStrategy;

    @Autowired
    @Qualifier(value = "popularityStrategy")
    RecommendationStrategy popularityStrategy;

    @Autowired
    @Qualifier(value = "similarityStrategy")
    RecommendationStrategy similarityStrategy;

    public RecommendationStrategy createRecommendationStrategy(EnumStrategyType type) {
        switch (type) {
            case HISTORY: return historyStrategy;
            case POPULARITY: return popularityStrategy;
            case SIMILARITY: return similarityStrategy;
            default: return new UnavailableRecommendationStrategy();
        }
    }


}

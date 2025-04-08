package io.meli.melishop.factory;

import io.meli.melishop.strategy.RecommendationStrategy;

public abstract class RecommendationStrategyFactory {

    protected abstract RecommendationStrategy createRecommendationStrategy();
}

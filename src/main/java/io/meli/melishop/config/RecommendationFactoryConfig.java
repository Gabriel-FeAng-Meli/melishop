package io.meli.melishop.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import io.meli.melishop.strategy.HistoryStrategy;
import io.meli.melishop.strategy.PopularityStrategy;
import io.meli.melishop.strategy.RecommendationStrategy;
import io.meli.melishop.strategy.SimilarityStrategy;
import io.meli.melishop.strategy.UnavailableRecommendationStrategy;

@Configuration
public class RecommendationFactoryConfig {

  @Bean
  @Qualifier("historyStrategy")
  @Profile(value = "history")
  public RecommendationStrategy historyStrategy(ApplicationContext context) {
    RecommendationStrategy str = new HistoryStrategy();
      return str;
  }

  @ConditionalOnMissingBean(name = "historyStrategy")
  @Bean
  @Qualifier("historyStrategy")
  public RecommendationStrategy unavailableHistoryStrategy(ApplicationContext context) {
      return new UnavailableRecommendationStrategy();
  }

  @Bean
  @Qualifier("popularityStrategy")
  @Profile(value = "popularity")
  public RecommendationStrategy popularityStrategy(ApplicationContext context) {
      return new PopularityStrategy();
  }

  @ConditionalOnMissingBean(name = "popularityStrategy")
  @Bean
  @Qualifier("popularityStrategy")
  public RecommendationStrategy unavailablePopularityStrategy(ApplicationContext context) {
      return new UnavailableRecommendationStrategy();
  }

  @Bean
  @Qualifier("similarityStrategy")
  @Profile(value = "similarity")
  public RecommendationStrategy similarityStrategy(ApplicationContext context) {
      return new SimilarityStrategy();
  }

  @ConditionalOnMissingBean(name = "similarityStrategy")
  @Bean
  @Qualifier("similarityStrategy")
  public RecommendationStrategy unavailableSimilarityStrategy(ApplicationContext context) {
      return new UnavailableRecommendationStrategy();
  }




}
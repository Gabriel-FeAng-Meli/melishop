package io.meli.melishop.strategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.meli.melishop.db.ProductRepo;
import io.meli.melishop.db.UserRepo;
import io.meli.melishop.model.User;
import io.meli.melishop.util.MapUtils;

public class SimilarityStrategy extends AbstractRecommendationStrategy {

    User mostSimilarUser = new User(null, null);
    double highestSimilarity;
    Double compatibilityRatio;
    Double similarityRatio;

    @Override
    public List<String> createTopThree(Map<String, Integer> mostSimilarUser) {
        List<String> mostBoughtProductsByMostSimilarUser = MapUtils.createTopThreeListFromMapValue(mostSimilarUser);

        return mostBoughtProductsByMostSimilarUser;
    }

    @Override
    public List<String> recommendProducts(User userWaitingRecommendation) {

        highestSimilarity = 0.0;

        List<User> otherUsers = getAllUsersFromDbExcept(userWaitingRecommendation);

        otherUsers.forEach(otherUser -> {
            Double similarityBetweenTwoUsers = calculateSimilarity(userWaitingRecommendation, otherUser);

            if (similarityBetweenTwoUsers > highestSimilarity) {
                highestSimilarity = similarityBetweenTwoUsers;
                mostSimilarUser = otherUser;
            }
        });
        List<String> recommendation = createTopThree(mostSimilarUser.history());
        return recommendation;
    }

    private List<User> getAllUsersFromDbExcept(User userToIgnore) {
        
        List<User> otherUsers = UserRepo.getAllUsers().entrySet().stream()
            .filter(entry -> entry.getValue() != userToIgnore.id())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        return otherUsers;
    }

    private void applySimilarityFactor(Integer quantityA, Integer quantityB) {
        similarityRatio += quantityA >= quantityB ?
            quantityB.doubleValue()/quantityA.doubleValue() : quantityA.doubleValue()/quantityB.doubleValue();
        
    }

    private void applyCompatibilityFactor(Integer quantityA) {
        double distinctionFactor =  Math.log(quantityA) / 10;
        compatibilityRatio *= (1 - distinctionFactor);
    }

    private Double calculateSimilarity(User userA, User userB) {
        compatibilityRatio = 1.0;
        similarityRatio = 0.0;

        List<String> productsInCommon = ProductRepo.allProducts.keySet().stream()
            .filter(product -> userA.history().containsKey(product) && userB.history().containsKey(product))
            .distinct()
            .collect(Collectors.toList());
        
        Map<String, Integer> productsOnlyOneUserHas = 
            userA.history().entrySet().stream()
                .filter(entry -> !userB.history().containsKey(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        productsOnlyOneUserHas.putAll(
            userB.history().entrySet().stream()
                .filter(entry -> !userA.history().containsKey(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );

        productsInCommon.forEach(product -> {
            applySimilarityFactor(userA.history().get(product), userB.history().get(product));
        });

        productsOnlyOneUserHas.values().forEach(quantity -> {
            applyCompatibilityFactor(quantity);
        });

        return similarityRatio * compatibilityRatio;
    }

}

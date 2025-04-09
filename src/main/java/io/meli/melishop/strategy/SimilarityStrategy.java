package io.meli.melishop.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import io.meli.melishop.db.Db;
import io.meli.melishop.model.User;
import io.meli.melishop.util.MapUtils;

public class SimilarityStrategy extends AbstractRecommendationStrategy {

    User mostSimilarUser;
    double highestSimilarity;
    double compatibilityRatio;
    double similarityRatio;

    @Override
    public String getStrategyType() {
        return "similarity";
    }

    @Override
    public List<String> createTopThree(Map<String, Integer> mostSimilarUser) {
        List<String> mostBoughtProductsByMostSimilarUser = MapUtils.createTopThreeListFromMapValue(mostSimilarUser);

        return mostBoughtProductsByMostSimilarUser;
    }

    @Override
    public List<String> recommendProducts(User userWaitingRecommendation) {

        List<User> otherUsers = getAllUsersFromDbExcept(userWaitingRecommendation);
        otherUsers.forEach(otherUser -> {
            Double similarityBetweenTwoUsers = calculateSimilarity(userWaitingRecommendation, otherUser);
            if (similarityBetweenTwoUsers > highestSimilarity) {
                highestSimilarity = similarityBetweenTwoUsers;
                mostSimilarUser = otherUser;
            }
        });
        List<String> recommendation = createTopThree(mostSimilarUser.getHistory());
        return recommendation;
    }

    List<User> getAllUsersFromDbExcept(User userToIgnore) {
        
        Map<User, Long> allUsers = Db.getAllUsers();
        List<User> otherUsers = new ArrayList<>();
        allUsers.forEach((user, id) -> {
            if (id != userToIgnore.getId()) {
                otherUsers.add(user);
            }
        });
        return otherUsers;
    }

    Double applySimilarityFactor(Double similarity, Integer quantityA, Integer quantityB) {
        if (quantityA >= quantityB) {
            return similarity += quantityB.doubleValue()/quantityA.doubleValue(); 
        } else {
            return similarity += quantityA.doubleValue()/quantityB.doubleValue();
        }
    }

    Double applyCompatibilityFactor(Double compatibility, Integer quantityA) {
        double distinctionFactor =  Math.log(quantityA) / 10;
        return compatibility * (1 - distinctionFactor);
    }

    Double calculateSimilarity(User userA, User userB) {
        compatibilityRatio = 1;

        Set<String> productsInCommon = new HashSet<>();
        Map<String, Integer> productsOnlyOneUserHas = new HashMap<>();

        userA.getHistory().keySet().forEach(key -> {
            if (userB.getHistory().containsKey(key)) {
                productsInCommon.add(key);
            } else {
                productsOnlyOneUserHas.put(key, userA.getHistory().get(key));
            }
        });

        userB.getHistory().keySet().forEach(key -> {
            if (!userA.getHistory().containsKey(key)) {
                productsOnlyOneUserHas.put(key, userB.getHistory().get(key));
            }
        });

        productsInCommon.forEach(product -> {
            similarityRatio = applySimilarityFactor(similarityRatio, userA.getHistory().get(product), userB.getHistory().get(product));
        });

        productsOnlyOneUserHas.forEach((key, value) -> {
            compatibilityRatio = applyCompatibilityFactor(compatibilityRatio, value);
        });

        Double result = similarityRatio * compatibilityRatio;
        return result;
    }

}

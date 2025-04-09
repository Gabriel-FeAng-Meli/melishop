package io.meli.melishop.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import io.meli.melishop.db.Db;
import io.meli.melishop.model.User;

public class SimilarityStrategy implements RecommendationStrategy {


    double distinctionRatio;
    double similarityRatio;

    public List<String> recommendProducts(User userA) {

        Map<User, Long> allUsers = Db.getAllUsers();

        List<User> otherUsers = new ArrayList<>();
        allUsers.forEach((user, id) -> {
            if (id != userA.getId()) {
                otherUsers.add(user);
            }
        });

        User userB = otherUsers.get(0);
        User userC = otherUsers.get(1);

        Double similarityAB = calculateSimilarity(userA, userB);
        Double similarityAC = calculateSimilarity(userA, userC);

        List<String> topThree = new ArrayList<>();

        if (similarityAB > similarityAC) {
            topThree = Db.createTopThreeList(userB.getHistory());
        } else {
            topThree = Db.createTopThreeList(userC.getHistory());
        }

        return topThree;
    }

    private Double applySimilarityFactor(Double similarity, Integer quantityA, Integer quantityB) {

        if (quantityA >= quantityB) {
            return similarity += quantityB.doubleValue()/quantityA.doubleValue(); 
        } else {
            return similarity += quantityA.doubleValue()/quantityB.doubleValue();
        }
    }

    private Double applyDistinctionFactor(Double distinction, Integer quantityA) {

        double distinctionFactor =  Math.log(quantityA) / 10;
        
        return distinction * (1 - distinctionFactor);
    }

    private Double calculateSimilarity(User userA, User userB) {

        similarityRatio = 0;
        distinctionRatio = 1;

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
            distinctionRatio = applyDistinctionFactor(distinctionRatio, value);
        });

        Double result = similarityRatio * distinctionRatio;
        return result;
    }

}

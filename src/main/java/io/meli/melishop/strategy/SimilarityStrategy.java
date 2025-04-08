package io.meli.melishop.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import io.meli.melishop.db.Db;
import io.meli.melishop.model.User;

public class SimilarityStrategy implements RecommendationStrategy {

    @Autowired
    Db db;

        
    public List<String> recommendProducts(User userA) {

        List<Long> userIds = List.of(1L, 2L, 3L);
        userIds.remove(userA.getId());

        User userB = db.getUserById(userIds.get(0));
        User userC = db.getUserById(userIds.get(1));

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

    private void applySimilarityFactor(Double similarityFactor, Integer quantityA, Integer quantityB) {

        if (quantityA >= quantityB) {
            similarityFactor *= quantityB.doubleValue()/quantityA.doubleValue(); 
        } else {
            similarityFactor *= quantityA.doubleValue()/quantityB.doubleValue();
        }
    }

    private void applyDistinctionFactor(Double distinctionFactor, Integer quantityA) {

        distinctionFactor *= 1 / quantityA;
    }

    private Double calculateSimilarity(User userA, User userB) {

        double distinction = 1;
        double similarity = 1;

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
            applySimilarityFactor(similarity, userA.getHistory().get(product), userB.getHistory().get(product));
        });

        productsOnlyOneUserHas.forEach((key, value) -> {
            applyDistinctionFactor(distinction, value);
        });

        Double result = similarity * distinction;
        return result;
    }

}

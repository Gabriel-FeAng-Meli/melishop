package io.meli.melishop.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.stereotype.Service;

import io.meli.melishop.model.User;

@Service
public class Db {

    public static final Map<String, Integer> allProducts = Map.of(
            "pen", 0,
            "tv", 5,
            "cereal", 10,
            "apple", 20,
            "banana", 30,
            "milk", 40,
            "rice", 50,
            "paper", 60);

    public static List<String> createTopThreeList(Map<String, Integer> map) {

        List<Map.Entry<String, Integer>> availableProducts = new ArrayList<>(map.entrySet());
        availableProducts.sort(Map.Entry.comparingByValue());

        int size = availableProducts.size();

        availableProducts.get(0).getKey();

        List<String> sortedList = new ArrayList<>();
        sortedList.add(0, availableProducts.get(size - 1).getKey());
        sortedList.add(1, availableProducts.get(size - 2).getKey());
        sortedList.add(2, availableProducts.get(size - 3).getKey());
        
        return sortedList;
    }

    Map<String, Integer> order_1 = Map.of(
            "tv", 2,
            "apple", 10,
            "banana", 5,
            "paper", 60);
    User user_1 = new User(1L, order_1);

    Map<String, Integer> order_2 = Map.of(
            "tv", 3,
            "apple", 10,
            "banana", 20,
            "milk", 30,
            "rice", 30);
    User user_2 = new User(2L, order_2);

    Map<String, Integer> order_3 = Map.of(
            "banana", 5,
            "milk", 10,
            "rice", 20);
    User user_3 = new User(3L, order_3);

    public User getUserById(Long id) {
        switch (id.toString()) {
            case "1":
                return this.user_1;
            case "2":
                return this.user_2;
            default:
                return this.user_3;
        }
    }

}

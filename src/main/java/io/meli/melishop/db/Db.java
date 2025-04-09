package io.meli.melishop.db;

import java.util.Map;

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

    static Map<String, Integer> order_1 = Map.of(
            "tv", 2,
            "apple", 10,
            "banana", 5,
            "paper", 60);
    static User user_1 = new User(1L, order_1);

    static Map<String, Integer> order_2 = Map.of(
            "tv", 3,
            "apple", 10,
            "banana", 20,
            "milk", 30,
            "rice", 30);
    static User user_2 = new User(2L, order_2);

    static Map<String, Integer> order_3 = Map.of(
            "banana", 5,
            "milk", 10,
            "rice", 20);
    static User user_3 = new User(3L, order_3);

    public static User getUserById(Long id) {
        switch (id.toString()) {
            case "1":
                return user_1;
            case "2":
                return user_2;
            default:
                return user_3;
        }
    }

    public static Map<User, Long> getAllUsers() {
        Map<User, Long> users = Map.of(user_1, 1L, user_2, 2L, user_3, 3L);

        return users;
    }

}

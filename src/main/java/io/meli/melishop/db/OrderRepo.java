package io.meli.melishop.db;

import java.util.Map;

public class OrderRepo {

    public static final Map<String, Integer> order_1 = Map.of(
        "tv", 2,
        "apple", 10,
        "banana", 5,
        "paper", 60
    );

    public static final Map<String, Integer> order_2 = Map.of(
        "tv", 3,
        "apple", 10,
        "banana", 20,
        "milk", 30,
        "rice", 30
    );

    public static final Map<String, Integer> order_3 = Map.of(
        "banana", 5,
        "milk", 10,
        "rice", 20
    );

}

package io.meli.melishop.db;

import java.util.Map;

public class ProductRepo {

    public static final Map<String, Integer> allProducts = Map.of(
        "pen", 0,
        "tv", 5,
        "cereal", 10,
        "apple", 20,
        "banana", 30,
        "milk", 40,
        "rice", 50,
        "paper", 60
    );
}

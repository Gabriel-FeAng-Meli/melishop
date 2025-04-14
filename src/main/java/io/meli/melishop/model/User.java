package io.meli.melishop.model;

import java.util.Map;

public record User(Long id, Map<String, Integer> history) {} 
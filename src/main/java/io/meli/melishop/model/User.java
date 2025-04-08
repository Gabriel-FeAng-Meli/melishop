package io.meli.melishop.model;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private Map<String, Integer> history;

    public User(Long id) {
        this.id = id;
        this.history = new HashMap<>();
    }

}

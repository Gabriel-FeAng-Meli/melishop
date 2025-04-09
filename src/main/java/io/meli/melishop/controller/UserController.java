package io.meli.melishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.meli.melishop.service.FactoryService;

@RestController
@RequestMapping("/recommendation")
public class UserController {

    @Autowired
    private FactoryService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> recommend(@PathVariable(value = "id") Long userId, @RequestParam(defaultValue = "POPULARITY") String strategy) {

        return ResponseEntity.status(200).body(userService.recommend(userId, strategy));
    }
}

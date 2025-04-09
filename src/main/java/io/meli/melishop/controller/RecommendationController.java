package io.meli.melishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.meli.melishop.enums.EnumStrategyType;
import io.meli.melishop.service.RecommendationService;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> recommend(@PathVariable(value = "id") Long userId, @RequestParam EnumStrategyType strategy) {
        return ResponseEntity.status(200).body(recommendationService.recommend(userId, strategy));
    }
}

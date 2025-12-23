package com.pantrytracker.pantryTracker_back_java.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pantrytracker.pantryTracker_back_java.services.PantryProductsService;
import com.pantrytracker.pantryTracker_back_java.models.Product;
import java.util.List;

@RestController
@RequestMapping("/api/pantry/")
public class PantryProductsController {

    private final PantryProductsService pantryProductsService;

    public PantryProductsController(PantryProductsService pantryProductsService) {
        this.pantryProductsService = pantryProductsService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getPantry() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String username = authentication.getName(); // lo extrae del JWT

        if (username.isEmpty()) {
            return ResponseEntity.status(500).body(null);
        }

        List<Product> products = pantryProductsService.getPantry(username);
        return ResponseEntity.ok(products);
    }

}

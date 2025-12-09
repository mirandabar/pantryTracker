package com.pantrytracker.pantryTracker_back_java.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pantrytracker.pantryTracker_back_java.services.AddPurchaseService;
import com.pantrytracker.pantryTracker_back_java.models.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/purchase/")
public class AddPurchaseController {

    private final AddPurchaseService addPurchaseService;

    public AddPurchaseController(AddPurchaseService addPurchaseService) {
        this.addPurchaseService = addPurchaseService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPurchase(@RequestBody List<Product> items) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // lo extrae del JWT

        boolean isCorrect = addPurchaseService.addPurchase(items, username);

        if (!isCorrect) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error adding purchase(s)");
            return ResponseEntity.status(500).body(response);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Purchase(s) added successfully");
        return ResponseEntity.ok(response);
    }


}

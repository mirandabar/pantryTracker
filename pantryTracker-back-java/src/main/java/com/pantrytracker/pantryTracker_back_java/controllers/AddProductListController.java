package com.pantrytracker.pantryTracker_back_java.controllers;

import com.pantrytracker.pantryTracker_back_java.models.ProductList;
import com.pantrytracker.pantryTracker_back_java.services.AddProductListService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productList/")
public class AddProductListController {

    private final AddProductListService addProductListService;

    public AddProductListController(AddProductListService addProductListService) {
        this.addProductListService = addProductListService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProductList(@RequestBody List<ProductList> items) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String username = authentication.getName(); // lo extrae del JWT

        boolean isCorrect = addProductListService.addProductList(items, username);

        if (!isCorrect) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error adding purchase(s)");
            return ResponseEntity.status(500).body(response);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Purchase(s) added successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProductList>> getProductList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String username = authentication.getName(); // lo extrae del JWT

        if (username.isEmpty()) {
            return ResponseEntity.status(500).body(null);
        }

        List<ProductList> products = addProductListService.getProductList(username);
        return ResponseEntity.ok(products);
    }


}

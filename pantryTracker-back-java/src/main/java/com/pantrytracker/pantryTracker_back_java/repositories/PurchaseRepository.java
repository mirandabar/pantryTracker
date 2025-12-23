package com.pantrytracker.pantryTracker_back_java.repositories;

import com.pantrytracker.pantryTracker_back_java.models.Product;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    Optional<Product> findById(Long id);

    List<Product> findByUserId(Long userId);

    void save(Product product);

    void saveAll(List<Product> products);

    void deleteById(Long id);
}

package com.pantrytracker.pantryTracker_back_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pantrytracker.pantryTracker_back_java.models.Product;

@Repository
public interface PurchaseRepository extends JpaRepository<Product, Long> {
    Product findByUserName(String userName);
    boolean existsByUserName(String userName);
}
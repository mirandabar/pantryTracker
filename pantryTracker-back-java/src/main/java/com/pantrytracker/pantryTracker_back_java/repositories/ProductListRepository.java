package com.pantrytracker.pantryTracker_back_java.repositories;

import com.pantrytracker.pantryTracker_back_java.models.ProductList;

import java.util.List;
import java.util.Optional;

public interface ProductListRepository {

    Optional<ProductList> findById(Long id);

    List<ProductList> findByUserId(Long userId);

    void save(ProductList productList);

    void saveAll(List<ProductList> productsList);

    void deleteById(Long id);
}

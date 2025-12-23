package com.pantrytracker.pantryTracker_back_java.services;

import com.pantrytracker.pantryTracker_back_java.models.ProductList;
import com.pantrytracker.pantryTracker_back_java.repositories.ProductListRepository;
import com.pantrytracker.pantryTracker_back_java.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AddProductListService {

    private final ProductListRepository productListRepository;
    private final UserRepository userRepository;

    public AddProductListService(ProductListRepository productListRepository, UserRepository userRepository) {
        this.productListRepository = productListRepository;
        this.userRepository = userRepository;
    }

    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    public boolean addProductList(List<ProductList> items, String userName) {

        if (!userRepository.existsByUserName(userName)) {
            return false;
        }

        Long userId = userRepository.getUserIdByUserName(userName);

        for (ProductList product : items) {
            product.setUserId(userId);
            product.setProductName(product.getProductName().toLowerCase());
            product.setPurchaseDate(getCurrentDate());
            productListRepository.save(product);
        }

        return true;
    }

    public List<ProductList> getProductList(String userName) {

        if (!userRepository.existsByUserName(userName)) {
            return null;
        }

        Long userId = userRepository.getUserIdByUserName(userName);

        return productListRepository.findByUserId(userId);
    }

}

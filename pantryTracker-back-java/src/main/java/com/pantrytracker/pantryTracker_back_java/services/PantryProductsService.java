package com.pantrytracker.pantryTracker_back_java.services;

import com.pantrytracker.pantryTracker_back_java.models.Product;
import com.pantrytracker.pantryTracker_back_java.repositories.PurchaseRepository;
import com.pantrytracker.pantryTracker_back_java.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PantryProductsService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    public PantryProductsService(PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }

    public List<Product> getPantry(String userName) {

        if (!userRepository.existsByUserName(userName)) {
            return null;
        }

        Long userId = userRepository.getUserIdByUserName(userName);

        return purchaseRepository.findByUserId(userId);
    }

}

package com.pantrytracker.pantryTracker_back_java.services;

import com.pantrytracker.pantryTracker_back_java.models.Product;
import com.pantrytracker.pantryTracker_back_java.models.User;
import com.pantrytracker.pantryTracker_back_java.repositories.PurchaseRepository;
import com.pantrytracker.pantryTracker_back_java.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class AddPurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    public AddPurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }

    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    public boolean addPurchase(List<Product> items, String userName) {

        if (!userRepository.existsByUserName(userName)) {
            return false;
        }

        User user = userRepository.findByUserName(userName);

        for (Product product : items) {
            product.setUser(user);
            product.setProductName(product.getProductName().toLowerCase());
            product.setPurchaseDate(getCurrentDate());
            purchaseRepository.save(product);
        }

        return true;
    }

}

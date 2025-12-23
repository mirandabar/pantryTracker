package com.pantrytracker.pantryTracker_back_java.repositories.sqlite;

import com.pantrytracker.pantryTracker_back_java.models.Product;
import com.pantrytracker.pantryTracker_back_java.repositories.PurchaseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SqlitePurchaseRepository implements PurchaseRepository {

    private final JdbcTemplate jdbc;

    public SqlitePurchaseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = """
            SELECT id, user_id, product_name, category, quantity, price,
                   expiration_date, purchase_date
            FROM products
            WHERE id = ?
        """;

        return jdbc.query(sql, rs -> {
            if (!rs.next()) return Optional.empty();
            return Optional.of(map(rs));
        }, id);
    }

    @Override
    public List<Product> findByUserId(Long userId) {
        String sql = """
            SELECT id, user_id, product_name, category, quantity, price,
                   expiration_date, purchase_date
            FROM products
            WHERE user_id = ?
            ORDER BY purchase_date DESC
        """;

        return jdbc.query(sql, (rs, rowNum) -> map(rs), userId);
    }

    @Override
    public void save(Product product) {
        String sql = """
            INSERT INTO products (
                user_id, product_name, category, quantity, price,
                expiration_date, purchase_date
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbc.update(
                sql,
                product.getUserId(),
                product.getProductName(),
                product.getCategory(),
                product.getQuantity(),
                product.getPrice(),
                product.getExpirationDate(),
                product.getPurchaseDate()
        );
    }

    @Override
    public void saveAll(List<Product> products) {
        String sql = """
            INSERT INTO products (
                user_id, product_name, category, quantity, price,
                expiration_date, purchase_date
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbc.batchUpdate(sql, products, products.size(), (ps, p) -> {
            ps.setLong(1, p.getUserId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getCategory());
            ps.setInt(4, p.getQuantity());
            ps.setFloat(5, p.getPrice());
            ps.setString(6, p.getExpirationDate());
            ps.setString(7, p.getPurchaseDate());
        });
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("DELETE FROM products WHERE id = ?", id);
    }

    private Product map(java.sql.ResultSet rs) throws java.sql.SQLException {
        Product p = new Product();
        p.setId(rs.getLong("id"));
        p.setUserId(rs.getLong("user_id"));
        p.setProductName(rs.getString("product_name"));
        p.setCategory(rs.getString("category"));
        p.setQuantity(rs.getInt("quantity"));
        p.setPrice(rs.getFloat("price"));
        p.setExpirationDate(rs.getString("expiration_date"));
        p.setPurchaseDate(rs.getString("purchase_date"));
        return p;
    }
}


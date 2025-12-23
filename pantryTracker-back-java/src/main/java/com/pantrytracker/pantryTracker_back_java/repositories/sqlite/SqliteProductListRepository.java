package com.pantrytracker.pantryTracker_back_java.repositories.sqlite;

import com.pantrytracker.pantryTracker_back_java.models.ProductList;
import com.pantrytracker.pantryTracker_back_java.repositories.ProductListRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SqliteProductListRepository implements ProductListRepository {

    private final JdbcTemplate jdbc;

    public SqliteProductListRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<ProductList> findById(Long id) {
        String sql = """
            SELECT id, user_id, product_name, category, quantity, purchase_date
            FROM productList
            WHERE id = ?
        """;

        return jdbc.query(sql, rs -> {
            if (!rs.next()) return Optional.empty();
            return Optional.of(map(rs));
        }, id);
    }

    @Override
    public List<ProductList> findByUserId(Long userId) {
        String sql = """
            SELECT id, user_id, product_name, category, quantity, purchase_date
            FROM productList
            WHERE user_id = ?
            ORDER BY purchase_date DESC
        """;

        return jdbc.query(sql, (rs, rowNum) -> map(rs), userId);
    }

    @Override
    public void save(ProductList productList) {
        String sql = """
            INSERT INTO productList (
                user_id, product_name, category, quantity, purchase_date
            )
            VALUES (?, ?, ?, ?, ?)
        """;

        jdbc.update(
                sql,
                productList.getUserId(),
                productList.getProductName(),
                productList.getCategory(),
                productList.getQuantity(),
                productList.getPurchaseDate()
        );
    }

    @Override
    public void saveAll(List<ProductList> productsList) {
        String sql = """
            INSERT INTO productList (
                user_id, product_name, category, quantity, purchase_date
            )
            VALUES (?, ?, ?, ?, ?)
        """;

        jdbc.batchUpdate(sql, productsList, productsList.size(), (ps, p) -> {
            ps.setLong(1, p.getUserId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getCategory());
            ps.setInt(4, p.getQuantity());
            ps.setString(5, p.getPurchaseDate());
        });
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("DELETE FROM productList WHERE id = ?", id);
    }

    private ProductList map(java.sql.ResultSet rs) throws java.sql.SQLException {
        ProductList p = new ProductList();
        p.setId(rs.getLong("id"));
        p.setUserId(rs.getLong("user_id"));
        p.setProductName(rs.getString("product_name"));
        p.setCategory(rs.getString("category"));
        p.setQuantity(rs.getInt("quantity"));
        p.setPurchaseDate(rs.getString("purchase_date"));
        return p;
    }
}


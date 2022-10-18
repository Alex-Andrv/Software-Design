package ru.akirakozov.sd.refactoring.database;

import ru.akirakozov.sd.refactoring.Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao {
    private static final Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> query(String query) {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                String  name = rs.getString("name");
                int price  = rs.getInt("price");
                products.add(new Product(name, price));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<Integer> aggregationQuery(String query) {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insert(Product product) {
        try (Statement stmt = conn.createStatement()) {
            String sql = String.format(
                    "INSERT INTO PRODUCT (NAME, PRICE) " +
                            "VALUES (\"%s\",%d)", product.getName(), product.getPrice());
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

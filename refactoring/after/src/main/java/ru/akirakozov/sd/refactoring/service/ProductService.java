package ru.akirakozov.sd.refactoring.service;

import ru.akirakozov.sd.refactoring.model.Product;

import java.util.List;
import java.util.Optional;

import static ru.akirakozov.sd.refactoring.database.ProductDao.aggregationQuery;
import static ru.akirakozov.sd.refactoring.database.ProductDao.query;

public class ProductService {
    public static List<Product> findAll() {
        return query("SELECT * FROM PRODUCT");
    }

    public static Optional<Product> getCheapest() {
        List<Product> products = query("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1");
        if (products.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(products.get(0));
        }
    }

    public static Optional<Product> getDearest() {
        List<Product> products = query("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
        if (products.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(products.get(0));
        }
    }

    public static Optional<Integer> getSum() {
        return aggregationQuery("SELECT SUM(price) FROM PRODUCT");
    }

    public static Optional<Integer> getCount() {
        return aggregationQuery("SELECT COUNT(*) FROM PRODUCT");
    }


}

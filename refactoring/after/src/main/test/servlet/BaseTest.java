package servlet;

import org.junit.jupiter.api.BeforeEach;
import ru.akirakozov.sd.refactoring.model.Product;

import java.util.Comparator;
import java.util.List;

import static database.InitTestDb.initTestDb;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.akirakozov.sd.refactoring.database.ProductDao.insert;
import static ru.akirakozov.sd.refactoring.service.ProductService.findAll;

public class BaseTest {

    @BeforeEach
    public void setup() throws Exception {
        initTestDb();
    }


    protected void fillTable(List<Product> expected) {
        for (Product product : expected) {
            insert(product);
        }
    }

    protected void validateProdTable(List<Product> expected) {
        List<Product> actual = findAll();
        assertListProducts(actual, expected);
    }

    protected void assertListProducts(List<Product> expected,  List<Product> actual) {
        expected.sort(Comparator.comparing(Product::getName));
        actual.sort(Comparator.comparing(Product::getName));
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}

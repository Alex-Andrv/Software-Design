package ru.akirakozov.sd.refactoring.view;

import ru.akirakozov.sd.refactoring.Model.Product;

import java.util.List;
import java.util.StringJoiner;

public class HtmlConvertorGetProduct extends  HtmlConvertorAbstract {
    private final List<Product> products;

    public HtmlConvertorGetProduct(List<Product> products) {
        this.products = products;
    }

    protected void getBody() {
        for (Product product : products) {
            String name = product.getName();
            long price = product.getPrice();
            htmlJoiner.add(name + "\t" + price + "</br>");
        }
    }
}

package ru.akirakozov.sd.refactoring.view;

import ru.akirakozov.sd.refactoring.model.Product;

import java.util.List;

public class HtmlConvertorGetProduct extends  HtmlConvertorAbstract {
    private final List<Product> products;

    public HtmlConvertorGetProduct(List<Product> products) {
        this.products = products;
    }

    protected void getBody() {
        for (Product product : products) {
            htmlJoiner.add(product.toHtml());
        }
    }
}

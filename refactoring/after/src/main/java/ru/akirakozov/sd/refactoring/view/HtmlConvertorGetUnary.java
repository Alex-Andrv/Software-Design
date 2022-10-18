package ru.akirakozov.sd.refactoring.view;

import ru.akirakozov.sd.refactoring.Model.Product;

import java.util.Optional;

public abstract class HtmlConvertorGetUnary extends HtmlConvertorAbstract {
    private final Optional<Product> productOrNull;

    protected HtmlConvertorGetUnary(Optional<Product> productOrNull) {
        this.productOrNull = productOrNull;
    }

    abstract protected String getHeader();


    protected void getBody() {
        htmlJoiner.add("<h1>" + getHeader() + "</h1>");
        if (productOrNull.isPresent()) {
            Product product = productOrNull.get();
            String name = product.getName();
            long price = product.getPrice();
            htmlJoiner.add(name + "\t" + price + "</br>");
        } else {
            htmlJoiner.add("could not find product" + "</br>");
        }
    }
}
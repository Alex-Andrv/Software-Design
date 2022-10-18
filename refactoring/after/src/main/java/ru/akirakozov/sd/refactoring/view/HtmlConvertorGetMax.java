package ru.akirakozov.sd.refactoring.view;

import ru.akirakozov.sd.refactoring.Model.Product;

import java.util.Optional;

public class HtmlConvertorGetMax extends HtmlConvertorGetUnary {
    public HtmlConvertorGetMax(Optional<Product> productOrNull) {
        super(productOrNull);
    }

    @Override
    protected String getHeader() {
        return "Product with max price: ";
    }

}

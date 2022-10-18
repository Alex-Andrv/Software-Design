package ru.akirakozov.sd.refactoring.view;

import ru.akirakozov.sd.refactoring.Model.Product;

import java.util.Optional;

public class HtmlConvertorGetMin extends  HtmlConvertorGetUnary  {
    public HtmlConvertorGetMin(Optional<Product> productOrNull) {
        super(productOrNull);
    }

    @Override
    protected String getHeader() {
        return "Product with min price: ";
    }
}

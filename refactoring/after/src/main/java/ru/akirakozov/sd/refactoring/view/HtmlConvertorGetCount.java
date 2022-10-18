package ru.akirakozov.sd.refactoring.view;

import java.util.Optional;

public class HtmlConvertorGetCount extends HtmlConvertorGetAggregation  {

    public HtmlConvertorGetCount(Optional<Integer> productOrNull) {
        super(productOrNull);
    }

    @Override
    protected String getHeader() {
        return "Number of products: ";
    }
}

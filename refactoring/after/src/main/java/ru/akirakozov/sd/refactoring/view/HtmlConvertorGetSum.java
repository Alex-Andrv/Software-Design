package ru.akirakozov.sd.refactoring.view;

import java.util.Optional;

public class HtmlConvertorGetSum extends  HtmlConvertorGetAggregation {

    public HtmlConvertorGetSum(Optional<Integer> productOrNull) {
        super(productOrNull);
    }

    @Override
    protected String getHeader() {
        return "Summary price: ";
    }
}

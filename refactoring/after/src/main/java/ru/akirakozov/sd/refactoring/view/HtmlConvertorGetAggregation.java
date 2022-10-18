package ru.akirakozov.sd.refactoring.view;

import java.util.Optional;

public abstract class HtmlConvertorGetAggregation extends HtmlConvertorAbstract {

    private final Optional<Integer> productOrNull;

    protected HtmlConvertorGetAggregation(Optional<Integer> productOrNull) {
        this.productOrNull = productOrNull;
    }

    abstract protected String getHeader();

    protected void getBody() {
        htmlJoiner.add("<h1>" + getHeader() + "</h1>");
        if (productOrNull.isPresent()) {
            Integer value = productOrNull.get();
            htmlJoiner.add(getHeader() + value);
        } else {
            htmlJoiner.add("could not find value" + "</br>");
        }
    }
}

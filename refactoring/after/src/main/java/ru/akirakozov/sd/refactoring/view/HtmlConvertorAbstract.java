package ru.akirakozov.sd.refactoring.view;

import java.util.StringJoiner;

public abstract class HtmlConvertorAbstract implements HtmlConvertor {

    protected final StringJoiner htmlJoiner;

    protected HtmlConvertorAbstract() {
        this.htmlJoiner = new StringJoiner(" ");;
    }

    abstract protected void getBody();

    @Override
    public String toHtml() {
        htmlJoiner.add("<html><body>");
        getBody();
        htmlJoiner.add("</body></html>");
        return htmlJoiner.toString();
    }

}

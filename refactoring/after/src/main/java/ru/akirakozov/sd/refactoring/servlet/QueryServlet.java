package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.Model.Product;
import ru.akirakozov.sd.refactoring.view.HtmlConvertorGetMax;
import ru.akirakozov.sd.refactoring.view.HtmlConvertorGetMin;
import ru.akirakozov.sd.refactoring.view.HtmlConvertorGetSum;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static ru.akirakozov.sd.refactoring.service.ProductService.*;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {

            Optional<Product> productOrNull = getCheapest();
            response.getWriter().print(new HtmlConvertorGetMax(productOrNull).toHtml());

        } else if ("min".equals(command)) {

            Optional<Product> productOrNull = getDearest();
            response.getWriter().print(new HtmlConvertorGetMin(productOrNull).toHtml());

        } else if ("sum".equals(command)) {

            Optional<Integer> sum = getSum();
            response.getWriter().print(new HtmlConvertorGetSum(sum).toHtml());

        } else if ("count".equals(command)) {

            Optional<Integer> count = getCount();
            response.getWriter().print(new HtmlConvertorGetSum(count).toHtml());

        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}

package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.model.Product;
import ru.akirakozov.sd.refactoring.view.HtmlConvertorGetProduct;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.akirakozov.sd.refactoring.service.ProductService.findAll;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> products = findAll();
        response.getWriter().print(new HtmlConvertorGetProduct(products).toHtml());

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

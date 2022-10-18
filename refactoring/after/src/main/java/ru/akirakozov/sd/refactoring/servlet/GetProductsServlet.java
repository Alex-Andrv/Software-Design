package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.Model.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static ru.akirakozov.sd.refactoring.database.ProductDao.query;
import static ru.akirakozov.sd.refactoring.service.ProductService.findAll;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> products = findAll();

        response.getWriter().println("<html><body>");

        for (Product product : products) {
            String name = product.getName();
            long price = product.getPrice();
            response.getWriter().println(name + "\t" + price + "</br>");
        }
        response.getWriter().println("</body></html>");

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

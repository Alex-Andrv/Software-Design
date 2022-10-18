package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.Model.Product;

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

            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with max price: </h1>");
            Optional<Product> productOrNull = getCheapest();
            if (productOrNull.isPresent()) {
                Product product = productOrNull.get();
                String name = product.getName();
                long price = product.getPrice();
                response.getWriter().println(name + "\t" + price + "</br>");
            } else {
                response.getWriter().println("could not find product" + "</br>");
            }
            response.getWriter().println("</body></html>");

        } else if ("min".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with min price: </h1>");
            Optional<Product> productOrNull = getDearest();
            if (productOrNull.isPresent()) {
                Product product = productOrNull.get();
                String name = product.getName();
                long price = product.getPrice();
                response.getWriter().println(name + "\t" + price + "</br>");
            } else {
                response.getWriter().println("could not find product" + "</br>");
            }
            response.getWriter().println("</body></html>");
        } else if ("sum".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("Summary price: ");

            Optional<Integer> count = getSum();

            if (count.isPresent()) {
                response.getWriter().println(count.get());
            }
            response.getWriter().println("</body></html>");
        } else if ("count".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("Number of products: ");

            Optional<Integer> count = getCount();

            if (count.isPresent()) {
                response.getWriter().println(count.get());
            }
            response.getWriter().println("</body></html>");
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}

package servlet;


import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.model.Product;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddProductServletIntegrationTest extends BaseTest {

    @Test
    public void addProductTest() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("test_prod_1");
        when(request.getParameter("price")).thenReturn("10");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new AddProductServlet().doGet(request, response);

        verify(response).setContentType("text/html");
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(response).getWriter();
        assertEquals(stringWriter.toString(), "OK");

        validateProdTable(List.of(new Product("test_prod_1", 10)));
    }
}

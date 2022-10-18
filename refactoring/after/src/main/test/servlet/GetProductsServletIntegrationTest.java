package servlet;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.model.Product;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

// TODO refactor test
public class GetProductsServletIntegrationTest extends BaseTest {
    @Test
    public void getProductsServletIntegrationTest() throws IOException {
        fillTable(List.of(new Product("test_prod_1", 10),
                new Product("test_prod_2", 20)));

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new GetProductsServlet().doGet(request, response);

        verifyNoInteractions(request);
        verify(response).setContentType("text/html");
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }
}

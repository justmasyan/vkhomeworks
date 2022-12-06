package serversentity;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import repositoryies.ProductRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class MethodsServlet extends HttpServlet {

    private final ProductRepository repository = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> list = repository.getAll();

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(ContentGenerator.content(list).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String companyTitle = request.getParameter("company");
        int companyId = repository.getCompanyId(companyTitle);

        if (companyId == -1) {
            repository.addProvider(companyTitle);
            companyId = repository.getCompanyId(companyTitle);
        }

        Product product = new Product(request.getParameter("title"), companyId, Integer.parseInt(request.getParameter("amount")));
        repository.addProduct(product);

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(ContentGenerator.content().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }
}


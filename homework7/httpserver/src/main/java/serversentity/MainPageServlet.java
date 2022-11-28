package serversentity;



import jakarta.servlet.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainPageServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(ContentGenerator.content().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }
}

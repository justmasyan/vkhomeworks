import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import serversentity.*;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import repositoryies.DataBaseInitializer;

import java.util.EnumSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
//        DataBaseInitializer.initDefaultDatabase();

        Server server = ServerFactory.build(8080);
        var firstContextHandler = new ServletContextHandler();
        firstContextHandler.setContextPath("/");

        firstContextHandler.addServlet(
                new ServletHolder("mainpage",MainPageServlet.class),
                "/");

        firstContextHandler.addServlet(
                new ServletHolder("methods", MethodsServlet.class),
                "/products");

        FilterHolder filterHolder = new FilterHolder(new MyFilter());
        filterHolder.setInitParameter("maxRequests","1");
        firstContextHandler.addFilter(filterHolder,"/*", EnumSet.of(DispatcherType.REQUEST));

        var hashConfig = Main.class.getResource("/hash_config").toExternalForm();
        HashLoginService loginService = new HashLoginService("login",hashConfig);
        var securityHandler = SecurityHandlerFactory.build(loginService);
        server.addBean(securityHandler);
        securityHandler.setHandler(firstContextHandler);

        server.setHandler(securityHandler);
        Scanner sc = new Scanner(System.in);
        server.start();
        System.out.println("Enter something to disable the server");
        sc.nextLine();
        server.stop();
    }
}

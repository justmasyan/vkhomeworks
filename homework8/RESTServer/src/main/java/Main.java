import configuration.GuiceListener;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import serversentity.MyFilter;
import serversentity.SecurityHandlerFactory;
import serversentity.ServerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = ServerFactory.build(8080);

        ServletContextHandler firstContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        firstContextHandler.addServlet(new ServletHolder("dispatcherServlets",HttpServletDispatcher.class),
                "/");
        firstContextHandler.addEventListener(new GuiceListener());

        FilterHolder filterHolder = new FilterHolder(MyFilter.class);
        filterHolder.setInitParameter("maxRequests","1");
        firstContextHandler.addFilter(filterHolder,"/*", EnumSet.of(DispatcherType.REQUEST));

        var hashConfig = Main.class.getResource("/hash_config").toExternalForm();
        HashLoginService loginService = new HashLoginService("login",hashConfig);
        var securityHandler = SecurityHandlerFactory.build(loginService);
        server.addBean(securityHandler);
        securityHandler.setHandler(firstContextHandler);

        server.setHandler(securityHandler);
        server.start();

    }
}

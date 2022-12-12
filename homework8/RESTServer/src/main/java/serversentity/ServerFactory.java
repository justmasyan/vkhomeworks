package serversentity;

import org.eclipse.jetty.server.*;

public class ServerFactory {

    public static Server build(int port){
        var server = new Server();
        final ServerConnector serverConnector = new ServerConnector(server, new HttpConnectionFactory());
        serverConnector.setHost("localhost");
        serverConnector.setPort(port);
        server.setConnectors(new Connector[]{serverConnector});
        return server;
    }
}

package launchers;

import vertexes.InfoVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class InfoVerticleLauncher {
    public static void main(String[] args) {
        Vertx.clusteredVertx(
                new VertxOptions(),
                vertxResult -> {
                    final var vertex = vertxResult.result();
                    final var options = new DeploymentOptions().setWorker(true);
                    vertex.sharedData().getCounter("counterPlayers", counter -> {
                        if (counter.succeeded()) {
                            counter.result().incrementAndGet(number -> {
                                vertex.deployVerticle(new InfoVerticle(), options);
                            });
                        }
                    });

                }
        );
    }
}

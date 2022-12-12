package launchers;

import vertexes.Member;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.List;

public class MemberLauncher {
    public static void main(String[] args) {
        Vertx.clusteredVertx(
                new VertxOptions(),
                vertxResult -> {
                    final var vertex = vertxResult.result();
                    final var options = new DeploymentOptions().setWorker(true);
                    vertex.sharedData().getCounter("counterPlayers", counter -> {
                        if (counter.succeeded()) {
                            counter.result().incrementAndGet(number -> {
                                String nickname = "Member#" + number.result();
                                vertex.deployVerticle(new Member(nickname), options);
                                vertex.sharedData().<String, List<String>>getAsyncMap("players",map ->{
                                    map.result().get("info", getResult ->{
                                        final var newList = getResult.result();
                                        newList.add(nickname);
                                        map.result().put("info",newList);
                                    });
                                });
                            });
                        }
                    });

                }
        );
    }
}

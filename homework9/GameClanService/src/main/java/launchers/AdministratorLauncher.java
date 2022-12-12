package launchers;

import vertexes.Administrator;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.ArrayList;
import java.util.List;

public class AdministratorLauncher {
    public static void main(String[] args) {
        Vertx.clusteredVertx(
                new VertxOptions(),
                vertxResult -> {
                    final var vertex = vertxResult.result();
                    final var options = new DeploymentOptions().setWorker(true);
                    vertex.sharedData().getCounter("counterPlayers", counter -> {
                        if (counter.succeeded()) {
                            counter.result().incrementAndGet(number -> {
                                vertex.sharedData().getCounter("clans",counterClans ->{
                                    if(counterClans.succeeded()){
                                        counterClans.result().incrementAndGet(numberClans ->{
                                            String nickname = "Member#" + number.result();
                                            vertex.deployVerticle(new Administrator(nickname,"clan" + numberClans,5), options);
                                            vertex.sharedData().<String, List<String>>getAsyncMap("players", map ->{
                                                map.result().get("info", getResult ->{
                                                    final var newList = getResult.result();
                                                    newList.add(nickname);
                                                    map.result().put("info",new ArrayList<>(newList),completion ->{
                                                        System.out.println("Админ добавлен");
                                                    });
                                                });
                                            });
                                        });
                                    }
                                });

                            });
                        }
                    });

                }
        );
    }
}

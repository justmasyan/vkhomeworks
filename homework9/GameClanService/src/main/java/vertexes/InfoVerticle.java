package vertexes;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import models.ClanInfo;

import java.util.ArrayList;
import java.util.List;

public class InfoVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.eventBus().<JsonObject>consumer("registerNewClan", event -> {
            vertx.sharedData().<String, ClanInfo>getAsyncMap("clans", map -> {
                map.result().get(event.body().getString("clanTitle"), getResult -> {
                    map.result().put(event.body().getString("clanTitle"), new ClanInfo(
                            event.body().getString("clanTitle"),
                            event.body().getInteger("maxAmountMembers"),
                            event.body().getJsonArray("members").getList()));
                });
            });
        });

        vertx.eventBus().<JsonArray>consumer("addedNewMember", event -> {
            List<String> list = event.body().getList();
            vertx.sharedData().<String, ClanInfo>getAsyncMap("clans", map -> {
                map.result().get(list.get(0), getResult -> {
                    final var newList = getResult.result().getMembers();
                    newList.add(list.get(1));
                    map.result().put(list.get(0), new ClanInfo(getResult.result().getClanTitle(), getResult.result().getMaxAmountMembers(), newList));
                });
            });
        });

        vertx.eventBus().consumer("info/allClans", event -> {
            vertx.sharedData().getAsyncMap("clans", map -> {
                map.result().keys(getResult ->{
                    event.reply(JsonArray.of(getResult.result().stream().toArray()));
                });
            });
        });

        vertx.eventBus().<String>consumer("info/clan", event -> {
            vertx.sharedData().<String, ClanInfo>getAsyncMap("clans", map -> {
                map.result().get(event.body(), getResult -> {
                    event.reply(JsonArray.of(getResult.result().getMembers().stream().toArray()));
                });
            });
        });

        vertx.eventBus().<String>consumer("info/capacity", event -> {
            System.out.println("Информационный вектор: запрос на проверку возможности добавления нового участника");
            vertx.sharedData().<String, ClanInfo>getAsyncMap("clans", map -> {
                map.result().get(event.body(), getResult -> {
                    event.reply(getResult.result().getMembers().size() < getResult.result().getMaxAmountMembers());
                });
            });
        });

        vertx.sharedData().<String, List<String>>getAsyncMap("players", map -> {
            map.result().put("info", new ArrayList<>());
        });

        vertx.setPeriodic(1000, timer -> {
            System.out.println("timer workaet");
            vertx.sharedData().<String, List<String>>getAsyncMap("players", map -> {
                map.result().get("info", getResult -> {
                    System.out.println("Список активных участников:" + getResult.result());
                });
            });
            vertx.sharedData().<String, ClanInfo>getAsyncMap("clans", map -> {
                map.result().values(getResult ->{
                    final var clans = getResult.result();
                    if (clans.size() != 0) {
                        for (ClanInfo clan : clans) {
                            System.out.println(clan.getClanTitle());
                            System.out.println("##############");
                            System.out.println("MaxNumberMembers: " + clan.getMaxAmountMembers());
                            System.out.println("List of members:" + clan.getMembers());
                            System.out.println("##############\n");
                        }
                    }
                });
            });
        });
    }

}

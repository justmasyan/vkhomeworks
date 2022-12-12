package vertexes;


import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import models.ClanInfo;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Moderator {
    private final ClanInfo clan;

    public Administrator(String nickname, String clanTitle, int maxAmountMembers) {
        super(nickname);
        List<String> list = new ArrayList<>();
        list.add(nickname);
        this.clanTitle = clanTitle;
        clan = new ClanInfo(clanTitle, maxAmountMembers, list);
    }

    @Override
    public void start() {
        vertx.executeBlocking(completion -> {
            vertx.eventBus().request("registerNewClan", JsonObject.mapFrom(clan));
            completion.complete();
        });

        monitorAmountMembersOfClan();

        super.start();
    }

    private void monitorAmountMembersOfClan() {
        vertx.setPeriodic(5000, timer -> {
            vertx.eventBus().<Boolean>request("info/capacity", clanTitle, reply -> {
                if (reply.succeeded() && !reply.result().body()) {
                    System.out.println("Администратор: достигнуто максимальное количество участников.");
                    System.out.println("Администратор: запрос на очистку клана");

                    vertx.eventBus().<JsonArray>request("info/clan",clanTitle, getReplyMembers ->{
                        List<String> membersClan = getReplyMembers.result().body().getList();
                        membersClan.remove(nickname);
                        for (String str: membersClan) {
                            vertx.eventBus().send(str,"closingClan");
                        }
                    });

                    vertx.eventBus().request("registerNewClan", JsonObject.mapFrom(clan));
                }
            });
        });
    }

}

package vertexes;

import io.vertx.core.json.JsonArray;

import java.util.List;

public class Moderator extends Member {

    public Moderator(String nickname) {
        super(nickname);
    }

    @Override
    public void start() {
        acceptRequestToClan();
        super.start();
    }

    private void acceptRequestToClan() {
        vertx.eventBus().<JsonArray>consumer("acceptAtClan", event -> {
            List<String> arguments = event.body().getList();
            if (!"NONE".equals(clanTitle) && clanTitle.equals(arguments.get(0))) {
                System.out.println("Модератор: Запрос на добавление");
                vertx.eventBus().<Boolean>request("info/capacity", clanTitle, reply -> {
                    if (reply.succeeded() && reply.result().body()) {
                        System.out.println("Модератор: участник " + arguments.get(1) + " принят.");
                        vertx.eventBus().send("acceptedTo" + arguments.get(1), clanTitle);

                        System.out.println("Модератор: участник " + arguments.get(1) + " добавлен в клан.");
                        vertx.eventBus().send("addedNewMember", JsonArray.of(new String[]{clanTitle, arguments.get(1)}));
                    }
                });
            }
        });
    }
}

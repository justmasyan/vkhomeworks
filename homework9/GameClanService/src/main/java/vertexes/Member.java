package vertexes;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;

import java.util.List;


public class Member extends AbstractVerticle {

    protected String clanTitle = "NONE";
    final protected String nickname;

    public Member(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void start() {
        sendRequestToClan();
        vertx.eventBus().<String>consumer(nickname, event -> {
            String msg = event.body();
            if("closingClan".equals(msg)) {
                System.out.println(nickname + " был исключен из" + clanTitle);
                clanTitle = "NONE";
            } else {
                System.out.println(nickname + " получил сообщение: " + msg);
            }
        });
        vertx.eventBus().<String>consumer("acceptedTo" + nickname, event -> {
            clanTitle = event.body();
            System.out.println(nickname + " принят в " + clanTitle);
        });
    }


    private void sendRequestToClan() {
        vertx.setPeriodic(1000, timer -> {
            if (!"NONE".equals(clanTitle)) {
                vertx.eventBus().<JsonArray>request("info/clan", clanTitle, reply -> {
                    System.out.println("Запрос на получение списка участников клана");

                    if (reply.succeeded()) {
                        List<String> listMembers = reply.result().body().getList();
                        System.out.println("Люди для отправления сообщения" + listMembers);
                        if (!listMembers.isEmpty()) {
                            int randomId = (int) (Math.random() * listMembers.size());
                            System.out.println("Пользователь: отправил сообщение " + listMembers.get(randomId));
                            vertx.eventBus().send(listMembers.get(randomId), "Hello from " + nickname);
                        }
                    }
                });
            } else {
                vertx.eventBus().<JsonArray>request("info/allClans", "something", reply -> {
                    System.out.println("Запрос на получение доступных кланов");
                    if (reply.succeeded()) {
                        List<String> listClans = reply.result().body().getList();
                        System.out.println("Лист доступных кланов для вступления" + listClans);
                        if (!listClans.isEmpty()) {
                            int randomId = (int) (Math.random() * listClans.size());
                            System.out.println("Пользователь: отправил запрос на добавление в клан " + listClans.get(randomId) + randomId);
                            vertx.eventBus().send("acceptAtClan", JsonArray.of(new String[]{listClans.get(randomId), nickname}));
                        }
                    }
                });
            }
        });
    }
}

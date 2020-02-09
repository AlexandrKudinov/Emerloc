package websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


@ServerEndpoint(value = "/game", configurator = GameServerEndPointConfigurator.class)
public class GameServerEndPoint {

    private static Queue<Session> playersSessions = new ArrayBlockingQueue<Session>(2);

    private static List<websocket.GameSession> gameSessions = Collections.synchronizedList(new LinkedList<>());

    public void createGameSession() {
        Session playerOneSession = playersSessions.remove();
        Session playerTwoSession = playersSessions.remove();
        websocket.GameSession gameSession = new websocket.GameSession(playerOneSession, playerTwoSession);
        gameSessions.add(gameSession);
    }


    private websocket.GameSession getGameSession(Session session) {
        for (websocket.GameSession gameSession : gameSessions) {
            if (gameSession.containSession(session)) {
                return gameSession;
            }
        }
        return null;
    }

    @OnOpen
    public void onOpen(Session userSession) {
        playersSessions.add(userSession);
        System.out.println("user added: " + userSession.getId());
        if (playersSessions.size() == 2) {
            createGameSession();
        }
    }

    @OnClose
    public void onClose(Session userSession) {
        System.out.println("user " + userSession.getId() + " removed!");
        websocket.GameSession gameSession = getGameSession(userSession);
        Session session = gameSession.getAnotherPlayerSession(userSession);
        session.getAsyncRemote().sendText(" another player leaved game... ,gameSession ended");
        gameSessions.remove(gameSession);
    }

    @OnMessage
    public void onMessage(String message, Session userSession) {
        System.out.println(userSession.getId() + " : " + message);
        websocket.GameSession gameSession = getGameSession(userSession);
        gameSession.sendMessageToPlayers(message);

    }
}


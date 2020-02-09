package websocket;

import logic.Pipeline;
import logic.Structure;
import logic.WaterSupplyMap;

import javax.websocket.Session;
import java.util.List;

public class GameSession {
    private Session playerOneSession;
    private Session playerTwoSession;
    private WaterSupplyMap waterSupplyMap = new WaterSupplyMap();
    private Structure structure = new Structure();

    public GameSession(Session player1, Session player2) {
        this.playerOneSession = player1;
        this.playerTwoSession = player2;

        waterSupplyMap.setStructure(structure);
        structure.bind();
        structure.buildHouseBlocks();
        structure.buildHouses();

        waterSupplyMap.setMap(structure.getMap());
        waterSupplyMap.addPipes();
        waterSupplyMap.addWaterIntake();
        waterSupplyMap.pipelineUnion();
        waterSupplyMap.generateAccidents();
        structure.addVan();
        System.out.println("New game session with " + player1.getId() + " , " + player2.getId() + " started!");
    }

    public boolean containSession(Session session) {
        if (playerOneSession == session || playerTwoSession == session) {
            return true;
        }
        return false;
    }

    public List<Pipeline> getPipelines() {
        return waterSupplyMap.getPipelines();
    }

    public Session getPlayerOneSession() {
        return playerOneSession;
    }

    public Session getPlayerTwoSession() {
        return playerTwoSession;
    }

    public Session getAnotherPlayerSession(Session session) {
        return playerOneSession == session ? playerTwoSession : playerOneSession;
    }

    public void sendMessageToPlayers(String message) {
        System.out.println("Sending to " + playerOneSession.getId() + " & " + playerTwoSession.getId());
        playerOneSession.getAsyncRemote().sendText(message);
        playerTwoSession.getAsyncRemote().sendText(message);
    }

}

package it.polimi.ingsw.messages.toClient.game;

import it.polimi.ingsw.common.VirtualView;
import it.polimi.ingsw.messages.toClient.MessageToClient;

import java.util.List;

public class ChooseLeaderCardsRequest implements MessageToClient {

    /**
     * Message used to ask the client to choose 2 out of the 4 leader cards assigned
     */

    List<Integer> leaderCardsIDs;

    public ChooseLeaderCardsRequest(List<Integer> leaderCardsIDs){
        this.leaderCardsIDs = leaderCardsIDs;
    }

    @Override
    public void handleMessage(VirtualView view) {
        view.displayChooseLeaderCardsRequest(leaderCardsIDs);
    }

    public String toString(){
        return "sending leader cards to choose";
    }
}

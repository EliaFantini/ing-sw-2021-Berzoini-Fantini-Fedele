package it.polimi.ingsw.messages.toClient.lobby;

import it.polimi.ingsw.common.VirtualView;
import it.polimi.ingsw.messages.toClient.MessageToClient;

public class NumberOfPlayersRequest implements MessageToClient {


    @Override
    public void handleMessage(VirtualView view) {
        System.out.println(this.toString());
        view.displayNumberOfPlayersRequest();
    }
}
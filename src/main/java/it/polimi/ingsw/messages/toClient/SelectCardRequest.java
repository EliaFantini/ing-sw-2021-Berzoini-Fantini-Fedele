package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.common.VirtualView;

import java.util.List;

public class SelectCardRequest implements MessageToClient{
    List<Integer> CardsIDs;
    boolean leaderORdevelopment;
    public SelectCardRequest(List<Integer> CardsIDs, boolean leaderORdevelopment){
        this.CardsIDs = CardsIDs;
        this.leaderORdevelopment=leaderORdevelopment;
    }
    @Override
    public void handleMessage(VirtualView view) {
        view.displaySelectCardRequest(CardsIDs,leaderORdevelopment);
    }
}
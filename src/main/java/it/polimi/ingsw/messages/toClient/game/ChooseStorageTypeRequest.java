package it.polimi.ingsw.messages.toClient.game;

import it.polimi.ingsw.common.ViewInterface;
import it.polimi.ingsw.enumerations.Resource;
import it.polimi.ingsw.messages.toClient.MessageToClient;

import java.util.List;

/**
 * Message to ask in which depot the player wants to store the resource
 */
public class ChooseStorageTypeRequest extends MessageToClient {
    private Resource resource;
    private List<String> availableDepots;
    private boolean canDiscard;
    private boolean canReorganize;

    public ChooseStorageTypeRequest(Resource resource, List<String> availableDepots, boolean canDiscard, boolean canReorganize){
        super(true);
        this.resource = resource;
        this.availableDepots = availableDepots;
        this.canDiscard = canDiscard;
        this.canReorganize = canReorganize;
    }

    @Override
    public void handleMessage(ViewInterface view) {
        view.displayChooseStorageTypeRequest(resource, availableDepots, canDiscard, canReorganize);
    }

    public String toString(){
        return "asking to choose a storage type";
    }
}

package it.polimi.ingsw.controller.game_phases;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.TurnController;
import it.polimi.ingsw.enumerations.GameMode;
import it.polimi.ingsw.exceptions.InvalidArgumentException;
import it.polimi.ingsw.jsonParsers.GameCloneThroughJson;
import it.polimi.ingsw.messages.toClient.matchData.*;
import it.polimi.ingsw.model.PersistentGame;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.LeaderCard;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.messages.toServer.game.ChooseActionResponse;
import it.polimi.ingsw.messages.toServer.game.EndTurnRequest;
import it.polimi.ingsw.messages.toServer.MessageToServer;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.server.Server;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

public abstract class PlayPhase {
    private Controller controller;
    private TurnController turnController;
    private Player player;
    private PersistentGame lastTurnGameCopy;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setTurnController(TurnController turnController) {
        this.turnController = turnController;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Controller getController() {
        return controller;
    }

    public TurnController getTurnController() {
        return turnController;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract void nextTurn();

    public abstract void handleResourceDiscard(String nickname);

    public void handleMessage(MessageToServer message, ClientHandler clientHandler) {
        if (message instanceof ChooseActionResponse && (clientHandler.getNickname().equals(getTurnController().getCurrentPlayer().getNickname()))) {
            Server.SERVER_LOGGER.log(Level.INFO, "New message from " + clientHandler.getNickname() + " that has chosen his next action : " + turnController.getPossibleActions().get(((ChooseActionResponse)message).getActionChosen()).toString());
            getTurnController().doAction(((ChooseActionResponse) message).getActionChosen());
        }
        if (message instanceof EndTurnRequest && (clientHandler.getNickname().equals(getTurnController().getCurrentPlayer().getNickname())))
            getTurnController().endTurn();
    }

    public abstract void handleEndTriggered();
    public String toString(){
        return "Play Phase";
    }

    public void saveGameCopy(Game game){
        lastTurnGameCopy = new PersistentGame(game);
    }

    public void reloadGameCopy(boolean disconnection){
        controller.sendMatchData(controller.getGame(), disconnection);
    }

    public PersistentGame getLastTurnGameCopy() {
        return lastTurnGameCopy;
    }

    public void setLastTurnGameCopy(PersistentGame lastTurnGameCopy) {
        this.lastTurnGameCopy = lastTurnGameCopy;
    }
}

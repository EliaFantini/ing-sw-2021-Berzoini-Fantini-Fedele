package it.polimi.ingsw.common;

import it.polimi.ingsw.enumerations.ClientHandlerPhase;
import it.polimi.ingsw.enumerations.GameMode;

import java.io.Serializable;

public interface ClientHandlerInterface {
    /**
     * Method used to send message to the client, through an object stream
     * @param message the message to be sent
     */
    void sendMessageToClient(Serializable message);
    GameMode getGameMode();
    String getNickname();
    ClientHandlerPhase getClientHandlerPhase();
    void setNickname(String nickname);
    void setClientHandlerPhase(ClientHandlerPhase clientHandlerPhase);
    void setGameMode(GameMode gameMode);
    void setGameStarted(boolean gameStarted);
    /**
     * Timer used to disconnect players who are too slow in sending their responses
     */
    void startTimer();
}

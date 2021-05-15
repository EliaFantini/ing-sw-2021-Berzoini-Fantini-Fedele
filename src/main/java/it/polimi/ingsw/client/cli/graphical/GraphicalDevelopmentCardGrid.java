package it.polimi.ingsw.client.cli.graphical;

import it.polimi.ingsw.client.MatchData;
import it.polimi.ingsw.common.LightDevelopmentCard;
import it.polimi.ingsw.enumerations.FlagColor;
import it.polimi.ingsw.enumerations.Level;

import java.util.*;

public class GraphicalDevelopmentCardGrid {

    private final int h_space = 1; //horizontal_space between cards
    private final int v_space = 0; //vertical_space between cards

    private final int cardWidth = GraphicalCard.CardWidth;
    private final int cardHeight = GraphicalCard.CardHeight;

    private final int width = cardWidth*4 + h_space *3;
    private final int height = cardHeight*3 + v_space *3;

    List<LightDevelopmentCard> cardsToDisplay;

    private final char[][] symbols = new char[height][width];
    private final Colour[][] colours = new Colour[height][width];

    //Note that x move vertically and y move horizontally
    public void addPixel(int x, int y, Colour colour, char symbol){
        symbols[x][y] = symbol;
        colours[x][y] = colour;
    }

    private void setCardsToDisplay(List<Integer> cardsToDisplay){
        this.cardsToDisplay = new ArrayList<>();
        for(Integer ID : cardsToDisplay){
            this.cardsToDisplay.add(MatchData.getInstance().getDevelopmentCardByID(ID));
        }
    }

    public void drawDevelopmentCardGrid(List<Integer> cardsToDisplay){
        setCardsToDisplay(cardsToDisplay);
        reset();
        int x_coord = 0;
        int y_coord = 0;
        for(LightDevelopmentCard ldc : this.cardsToDisplay){
            List<Integer> coordinates = retrieveCoordinates(ldc);
            x_coord = coordinates.get(0);
            y_coord = coordinates.get(1);
            GraphicalCard gc = new GraphicalCard(this, ldc);
            gc.drawOnScreen(x_coord, y_coord);
        }
    }

    private List<Integer> retrieveCoordinates(LightDevelopmentCard ldc) {
        int x = 0;
        int y = 0;
        x = (Level.valueOf(ldc.getFlagLevel()).getValue() * - 1) + 2;
        y = FlagColor.valueOf(ldc.getFlagColor()).getValue();
        x = x*(cardHeight + v_space);
        y = y*(cardWidth + h_space);
        return new ArrayList<>(Arrays.asList(x, y));
    }

    public void displayDevelopmentCardGrid() {
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
               System.out.print(colours[i][j].getCode() + symbols[i][j]); //+ Colour.ANSI_RESET
            }
            System.out.print("\n");
        }
    }

    private void reset(){
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                symbols[i][j] = ' ';
                colours[i][j] = Colour.ANSI_BRIGHT_WHITE;
            }
        }
    }

    private List<String> cardInfoParse(String s){
        int index = s.indexOf("flagColor");
        return null;
    }

    char[][] getSymbols() {
        return symbols;
    }

    Colour[][] getColours() {
        return colours;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
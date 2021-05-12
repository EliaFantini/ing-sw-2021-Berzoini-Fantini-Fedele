package it.polimi.ingsw.client.cli.graphical;

import it.polimi.ingsw.enumerations.Marble;

import java.util.List;

public class GraphicalMarket {

    public static void printMarket(Marble[][] marketTray, Marble slideMarble){
        int index = 1;
        for (int i = 0; i < marketTray.length; i++){
            for (int j = 0; j < marketTray[i].length; j++) {
                System.out.print(Colour.getMarbleColour(marketTray[i][j]) + " @ ");
            }
            System.out.print(Colour.ANSI_BRIGHT_GREEN.getCode() + index + "\n");
            index++;
        }
        System.out.println(" 7  6  5  4 " + Colour.getMarbleColour(slideMarble) + "@\n" + Colour.ANSI_RESET);
    }

    public static void printMarbleLine(List<Marble> marbles){
        for (Marble marble : marbles)
            System.out.print(Colour.getMarbleColour(marble) + " @ ");
        System.out.print(Colour.ANSI_RESET + "\n");
    }
}
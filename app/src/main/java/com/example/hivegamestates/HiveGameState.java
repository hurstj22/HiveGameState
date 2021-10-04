package com.example.hivegamestates;

import java.util.ArrayList;

public class HiveGameState {

    public enum Turn{
        PLAYER1,
        COMPUTER,
        NETWORKPLAYER
    }

    public enum Bugs{ //Do we need this enum in here if it's already in the Tile class?
        BEE,
        ANT,
        BEETLE,
        GRASSHOPPER,
        SPIDER
    }

    //Variables of gameState
    private ArrayList<Tile> gameBoard;
    private ArrayList<Tile> displayBoard;
    private Bugs piecesRemain[][];
    private Turn whoseTurn;

    /**
     * Default constructor.
     */
    public HiveGameState(){
        gameBoard = new ArrayList<Tile>();
        displayBoard = new ArrayList<Tile>();
        piecesRemain = new Bugs[2][5];
        whoseTurn = Turn.PLAYER1;
    }

    /**
     * Deep copy constructor.
     * @param other
     */
    public HiveGameState(HiveGameState other){
        this.gameBoard = new ArrayList<Tile>();
        for(Tile tile: other.gameBoard){
            this.gameBoard = other.gameBoard;
        }
        this.displayBoard = new ArrayList<Tile>();
        for(Tile tile: other.displayBoard){
            this.displayBoard = other.displayBoard;
        }
        this.piecesRemain = new Bugs[2][5];
        for (int i = 0; i < other.piecesRemain.length; i++){
            for (int j = 0; j < other.piecesRemain[i].length; j++){
                this.piecesRemain[i][j] = other.piecesRemain[i][j];
            }
        }
    }

    /**
     * Method to print out gameState of app in readable format.
     * @return
     */
    @Override
    public String toString(){
        String currentState = "";

        for (Tile tile: gameBoard){
            switch(tile.getType()){
                case EMPTY:
                    currentState += " "; //add space for nothing there
                    break;
                case QUEEN_BEE:

                    break;
                case ANT:

                    break;
                case BEETLE:

                    break;
                case SPIDER:

                    break;
                case GRASSHOPPER:

                    break;
                case POTENTIAL:
                    currentState += "P"; //add P for potential future spot
                    break;
            }
        }
        return currentState;
    }
}

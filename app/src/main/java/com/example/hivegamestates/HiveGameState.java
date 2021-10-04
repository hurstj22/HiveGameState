package com.example.hivegamestates;

import java.util.ArrayList;

public class HiveGameState {

    public enum turn{
        PLAYER1,
        COMPUTER,
        NETWORKPLAYER
    }

    public enum bugs{ //Do we need this enum in here if it's already in the Tile class?
        BEE,
        ANT,
        BEETLE,
        GRASSHOPPER,
        SPIDER
    }

    //Variables of gameState
    private ArrayList<Tile> gamePieces;
    private bugs piecesRemain[][];
    private turn whoseTurn;

    /**
     * Default constructor.
     */
    public HiveGameState(){
        gamePieces = new ArrayList<Tile>();
        piecesRemain = new bugs[2][5];
        whoseTurn = turn.PLAYER1;
    }

    /**
     * Deep copy constructor.
     * @param other
     */
    public HiveGameState(HiveGameState other){
        this.gamePieces = new ArrayList<Tile>();
        for(Tile tile: other.gamePieces){
            this.gamePieces = other.gamePieces;
        }
        this.piecesRemain = new bugs[2][5];
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

        for (Tile tile: gamePieces){
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

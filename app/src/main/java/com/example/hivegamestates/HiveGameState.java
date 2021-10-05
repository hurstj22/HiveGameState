package com.example.hivegamestates;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<ArrayList<Tile>> gameBoard;
    private ArrayList<ArrayList<Tile>> displayBoard;
    private Bugs piecesRemain[][];
    private Turn whoseTurn;

    /**
     * Default constructor.
     */
    public HiveGameState(){
        gameBoard = new ArrayList<ArrayList<Tile>>();
        displayBoard = new ArrayList<ArrayList<Tile>>();
        piecesRemain = new Bugs[2][5];
        whoseTurn = Turn.PLAYER1;
    }

    /**
     * Deep copy constructor.
     * @param other
     */
    public HiveGameState(HiveGameState other){
        this.gameBoard = new ArrayList<ArrayList<Tile>>();
        for(int index = 0; index < gameBoard.size(); index++){
            other.gameBoard.add(new ArrayList<Tile>(gameBoard.get(index)));
        }
        this.displayBoard = new ArrayList<ArrayList<Tile>>();
        for(int index = 0; index < displayBoard.size(); index++) {
            other.displayBoard.add(new ArrayList<Tile>(displayBoard.get(index)));
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
                    currentState += tile.getPiece() + "Q";
                    break;
                case ANT:
                    currentState += tile.getPiece() + "A";
                    break;
                case BEETLE:
                    currentState += tile.getPiece() + "B";
                    break;
                case SPIDER:
                    currentState += tile.getPiece() + "S";
                    break;
                case GRASSHOPPER:
                    currentState += tile.getPiece() + "G";
                    break;
                case POTENTIAL:
                    currentState += tile.getPiece() + "P"; //add P for potential future spot
                    break;
            }
        }
        return currentState;
    }


}

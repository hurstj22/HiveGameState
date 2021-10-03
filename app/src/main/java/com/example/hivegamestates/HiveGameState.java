package com.example.hivegamestates;

import java.util.ArrayList;

public class HiveGameState {

    public enum turn{
        PLAYER1,
        COMPUTER,
        NETWORKPLAYER
    }

    public enum bugs{
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
        for (Tile tile: gamePieces){
            if (tile == null){

            }
            //will print
            else{

            }
        }
        return "";
    }
}

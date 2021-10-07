package com.example.hivegamestates;

import java.util.ArrayList;
import java.util.List;

public class HiveGameState {

    public enum Turn{
        PLAYER1,
        COMPUTER,
        NETWORKPLAYER
    }

    //Variables of gameState
    private ArrayList<ArrayList<Tile>> gameBoard;
    private ArrayList<ArrayList<Tile>> displayBoard;
    private Tile.Bug piecesRemain[][];
    private Turn whoseTurn;

    private static final int tileSize = 300;

    private ArrayList<Tile> potentialMoves;
    /**
     * Default constructor.
     */
    public HiveGameState(){
        //Initialize gameBoard to be 14 rows of empty tiles
        gameBoard = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < 14; i++){
            gameBoard.add(new ArrayList<Tile>());
            for(int j = 0; j < 14; j++){
                gameBoard.get(i).add(new Tile (i*tileSize, j*tileSize, Tile.PlayerPiece.EMPTY));
            }
        }
        //Initialize displayBoard to be 14 rows of empty tiles
        displayBoard = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < 14; i++){
            displayBoard.add(new ArrayList<Tile>());
            for(int j = 0; j < 14; j++){
                displayBoard.get(i).add(new Tile (i*tileSize, j*tileSize, Tile.PlayerPiece.EMPTY));
            }
        }
        piecesRemain = new Tile.Bug[2][5];
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
        this.piecesRemain = new Tile.Bug[2][5];
        for (int i = 0; i < other.piecesRemain.length; i++){
            for (int j = 0; j < other.piecesRemain[i].length; j++){
                this.piecesRemain[i][j] = other.piecesRemain[i][j];
            }
        }
    }

    /**
     * Creates a new gamestate object and
     * is called when the new game button is clicked
     * @return
     */
    public boolean newGame(){
        new HiveGameState(); //creates a new blank gameState object
        return true;
    }

    /**
     * Exits the game,
     * called when the exit game button is clicked
     * @return
     */
    public boolean endGame(){
        return true;
    }

    /**
     * Takes in the previous gamestate
     * and sets the current gameState equal to it
     * using deep copy constructor
     * @return
     */
    public boolean undoMove(HiveGameState previousGameState){
        new HiveGameState(previousGameState);
        return true;
    }

    /**
     * Checks the spot a piece is trying to move to determine if it is
     * a valid move according to the movement rules
     * @param tile the piece that is trying to move
     * @param xCoord the x coordinate where the piece is moving
     * @param yCoord the y coordinate where the piece is moving
     * @return true if can move there, false if not
     */
    public boolean validMove(Tile tile, int xCoord, int yCoord){
        if(!breakHive(tile, xCoord, yCoord)){//as long as the move doesn't break the hive
            //and the type isn't grasshopper or beetle since they
            //don't obey the freedom of movement rule
            if(tile.getType() != Tile.Bug.GRASSHOPPER || tile.getType() != Tile.Bug.BEETLE){
                if(freedom(tile)){//obeys freedom of movement (not surrounded)
                    return true;
                }
            }
            else{ //type IS grasshopper or beetle and the move doesn't break the hive
                return true;
            }
        } //move breaks the hive :(
        return false;
    }

    /**
     * Highlights the potential moves of a selected piece. The class variable potentials is
     * changed to reflect potential moves.
     * @param tile the selected piece
     * @return true if potential moves exist. false if not.
     */
    public boolean selectTile(Tile tile) {
        if(validMove(tile, tile.getCoordX(), tile.getCoordY())) {
            //if the piece can be moved legally
            switch (tile.getType()){
                case ANT:
                    //
                case BEETLE:
                    //
                case GRASSHOPPER:
                    //
                case SPIDER:
                    //
                case QUEEN_BEE:
                    //
                case EMPTY:
                    //
                    return false;
                case POTENTIAL:
                    //
                    return false;

            }

        }


        return false;

    }

    /**
     * Checks the hive (ie gameboard) to see if the entire board is connected and if
     * without the Tile in the spot it currently is the board would STILL be connected
     * @param tile the piece being checked against breaking the hive
     * @param xCoord the x coordinate it is trying to move to (not sure if neccesary)
     * @param yCoord the y coordinate it is trying to move to (not sure if neccesary)
     * @return false if move would NOT break the hive, true if move would break hive
     */
    public boolean breakHive(Tile tile, int xCoord, int yCoord){

        return true;
    }

    /**
     * checks a tile to see if it is surrounded to the point
     * where it no longer has freedom of movement
     * @param tile the piece being checked
     * @return true if it is free to move, false if it is trapped
     */
    public boolean freedom(Tile tile){

        return true;
    }

    /**
     * Method to print out gameState in readable format.
     * @return
     */
    @Override
    public String toString(){
        String currentState = "";
        for (ArrayList<Tile> row: gameBoard) {
            for (Tile tile : row) {
                switch (tile.getType()) {
                    case EMPTY:
                        currentState += " "; //add space for nothing there
                        break;
                    case QUEEN_BEE:
                        currentState += tile.getPlayerPiece().name() + "Q";
                        break;
                    case ANT:
                        currentState += tile.getPlayerPiece().name() + "A";
                        break;
                    case BEETLE:
                        currentState += tile.getPlayerPiece().name() + "B";
                        break;
                    case SPIDER:
                        currentState += tile.getPlayerPiece().name() + "S";
                        break;
                    case GRASSHOPPER:
                        currentState += tile.getPlayerPiece().name() + "G";
                        break;
                    case POTENTIAL:
                        currentState += tile.getPlayerPiece().name() + "P"; //add P for potential future spot
                        break;
                }
            }
        }
        return currentState;
    }

    /**
     *
     * @param moveTile
     * @param newXCoord
     * @param newYCoord
     * @return
     */
    public boolean makeMove(Tile moveTile, int newXCoord, int newYCoord) {
        //need to get position of newTile based on x and y coordinates
        int[] newTileCords = positionOfTile(newXCoord, newYCoord);

        if(potentialMoves.contains(gameBoard.get(newTileCords[0]).get(newTileCords[1]))){
            //put tile moveTile in new location and update coordinates
            // and put empty tile in old spot
        }
        return true;
    }

    public int[] positionOfTile(int xCord, int yCord){
        int[] positionInGameBoard = new int[2];
        positionInGameBoard[0] = xCord/tileSize;
        positionInGameBoard[1] = yCord/tileSize;
        return positionInGameBoard;
    }

}

package com.example.hivegamestates;


import java.util.ArrayList;

public class HiveGameState {

    public enum Turn{
        PLAYER1,
        COMPUTER,
        NETWORKPLAYER
    }

    public enum Direction {
        UP_LEFT,
        UP_RIGHT,
        LEFT,
        RIGHT,
        DOWN_LEFT,
        DOWN_RIGHT
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
                gameBoard.get(i).add(new Tile (i, j, Tile.PlayerPiece.EMPTY));
            }
        }
        //Initialize displayBoard to be 14 rows of empty tiles
        displayBoard = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < 14; i++){
            displayBoard.add(new ArrayList<Tile>());
            for(int j = 0; j < 14; j++){
                displayBoard.get(i).add(new Tile (i, j, Tile.PlayerPiece.EMPTY));
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
     * @return true if can move there, false if not
     */
    public boolean validMove(Tile tile){
        if(!breakHive(tile)){//as long as the move doesn't break the hive
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
     * Checks the hive (ie gameboard) to see if the entire board is connected and if
     * without the Tile in the spot it currently is the board would STILL be connected
     * @param tile the piece being checked against breaking the hive
     * @return false if move would NOT break the hive, true if move would break hive
     */
    public boolean breakHive(Tile tile){
        //perform a DFS
        return true;
    }

    /**
     * checks a tile to see if it is surrounded to the point
     * where it no longer has freedom of movement
     * Different logic due to the offest of hexes
     * @param tile the piece being checked
     * @return true if it is free to move, false if it is trapped
     */
    public boolean freedom(Tile tile){
        int count = 0;

        switch(tile.getIndexY() % 2){
            case 0: //even row
                //LU: (row--, col), LM: (row, col--), LD: (row++, col),
                //RU: (row--, col++), RM: (row, col++), RD: (row++, col++)

                break;
            case 1: //odd row
                //LU: (row--, col--), LM: (row, col--), LD: (row++, col--),
                //RU: (row--, col), RM: (row, col++), RD: (row++, col)

                break;
        }

        return true;
    }

    /**
     * Highlights the potential moves of a selected piece. The class variable potentials is
     * changed to reflect potential moves.
     * @param tile the selected piece
     * @return true if potential moves exist. false if not.
     */
    public boolean selectTile(Tile tile) {
        //we should make sure the person whose turn it is has placed their queen somewhere
        if(validMove(tile)) {                                       ///ATTENTION, THIS IS WRONG AND NOT FUNCTIONAL
            //if the piece can be moved legally
            switch (tile.getType()){
                case ANT:
                    //
                case BEETLE:
                    //utilize the beetleSearch function to find potential moves.
                    return beetleSearch(tile);
                case GRASSHOPPER:
                    //utilize the startGrasshopperSearch function to find potential moves.
                    return startGrasshopperSearch(tile);
                case SPIDER:
                    //
                case QUEEN_BEE:
                    //utilize the queenSearch function to find potential moves.
                    return queenSearch(tile);
                case EMPTY:
                    //
                    return false;

            }

        }
        return false;
    }

    /**
     * Checks for valid moves for the Beetle tile. Modifies ArrayList potentialMoves to reflect
     * valid moves for the beetle tile. Returns false if no moves are found.
     * @param tile the tile the player wishes to move
     * @return returns true if there are valid moves for the beetle piece. False if not.
     */
    public boolean beetleSearch(Tile tile) {
            // Ensure any tile that the beetle touches touches another bug tile
            // before highlighting as potential
            int x = tile.getIndexX();
            int y = tile.getIndexY();

            if (x % 2 == 0) {
                // For even rows

                if (nextTo(tile, gameBoard.get(x - 1).get(y))) {
                    //Check tile above left of tile is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x - 1).get(y));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x - 1).get(y + 1))) {
                    //Check tile above right of til is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x - 1).get(y + 1));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x).get(y - 1))) {
                    //Check tile to the left of til is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x).get(y - 1));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x).get(y + 1))) {
                    //Check tile to the right of ti is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x).get(y + 1));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x + 1).get(y))) {
                    //Check tile below left of tile is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x + 1).get(y));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x + 1).get(y + 1))) {
                    //Check tile below right of til is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x + 1).get(y + 1));
                    return true;
                }
            } else {
                // For odd rows

                if (nextTo(tile, gameBoard.get(x - 1).get(y - 1))) {
                    //Check tile above left of tile is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x - 1).get(y - 1));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x - 1).get(y))) {
                    //Check tile above right of til is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x - 1).get(y));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x).get(y - 1))) {
                    //Check tile to the left of til is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x).get(y - 1));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x).get(y + 1))) {
                    //Check tile to the right of ti is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x).get(y + 1));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x + 1).get(y - 1))) {
                    //Check tile below left of tile is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x + 1).get(y - 1));
                    return true;
                } else if (nextTo(tile, gameBoard.get(x + 1).get(y))) {
                    //Check tile below right of til is nextTo another tile
                    //If true, add tile to ArrayList<Tile> potentials
                    potentialMoves.add(gameBoard.get(x + 1).get(y));
                    return true;
                }

            }
            return false;
    }

    /**
     * Checks for valid moves for the Queen Bee tile. Modifies ArrayList potentialMoves to reflect
     * valid moves for the queen bee tile. Returns false if no moves are found.
     *
     * @param tile the tile the player wishes to move
     * @return
     */
    public boolean queenSearch(Tile tile) {
        //Ensure adjacent tiles are empty with things next to them
        int x = tile.getIndexX();
        int y = tile.getIndexY();

        if (x % 2 == 0) {
            // For even rows

            if(gameBoard.get(x-1).get(y).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x-1).get(y))){
                //Check tile above left of tile is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x-1).get(y));
                return true;
            } else if(gameBoard.get(x-1).get(y+1).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x-1).get(y+1))) {
                //Check tile above right of til is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x-1).get(y+1));
                return true;
            } else if(gameBoard.get(x).get(y-1).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x).get(y-1))) {
                //Check tile to the left of til is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x).get(y-1));
                return true;
            } else if(gameBoard.get(x).get(y+1).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x).get(y+1))) {
                //Check tile to the right of ti is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x).get(y+1));
                return true;
            } else if(gameBoard.get(x+1).get(y).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x+1).get(y))) {
                //Check tile below left of tile is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x+1).get(y));
                return true;
            } else if(gameBoard.get(x+1).get(y+1).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x+1).get(y+1))) {
                //Check tile below right of til is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x+1).get(y+1));
                return true;
            }
        } else {
            // For odd rows

            if(gameBoard.get(x-1).get(y-1).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x-1).get(y-1))){
                //Check tile above left of tile is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x-1).get(y-1));
               return true;
            } else if(gameBoard.get(x-1).get(y).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x-1).get(y))) {
                //Check tile above right of til is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x-1).get(y));
                return true;
            } else if(gameBoard.get(x).get(y-1).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x).get(y-1))) {
                //Check tile to the left of til is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x).get(y-1));
                return true;
            } else if(gameBoard.get(x).get(y+1).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x).get(y+1))) {
                //Check tile to the right of ti is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x).get(y+1));
                return true;
            } else if(gameBoard.get(x+1).get(y-1).getType() == Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x+1).get(y-1))) {
                //Check tile below left of tile is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x+1).get(y-1));
                return true;
            } else if(gameBoard.get(x+1).get(y).getType() != Tile.Bug.EMPTY &&
                    nextTo(tile, gameBoard.get(x+1).get(y))) {
                //Check tile below right of til is empty and nextTo
                //If true, add tile to ArrayList<Tile> potentials
                potentialMoves.add(gameBoard.get(x+1).get(y));
                return true;
            }

        }
        return false;
    }

    /**
     * Checks tiles adjacent to the selected tile for bug pieces. Starts recursive grasshopperSearch
     * on valid moves
     * @param tile the tile the player wishes to move.
     * @return true if moves found. False if not
     */
    public boolean startGrasshopperSearch(Tile tile) {
        //set a recursive search in each direction from the starting tile
        int x = tile.getIndexX();
        int y = tile.getIndexY();
        boolean valid = false;

        if ( x % 2 == 0 ) {
            //if the tile is on an even row

            //Check tile to the above left
            if (gameBoard.get(x-1).get(y).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x-1).get(y), Direction.UP_LEFT);
                valid = true;
            }
            //Check the tile to the above right
            if (gameBoard.get(x-1).get(y+1).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x-1).get(y+1), Direction.UP_RIGHT);
                valid = true;
            }

            //Check the tile to the left
            if (gameBoard.get(x).get(y-1).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x).get(y-1), Direction.LEFT);
                valid = true;
            }

            //Check the tile to the right
            if (gameBoard.get(x).get(y+1).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x).get(y+1), Direction.RIGHT);
                valid = true;
            }

            //Check the tile to the below left
            if (gameBoard.get(x+1).get(y).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x+1).get(y), Direction.DOWN_LEFT);
                valid = true;
            }

            if (gameBoard.get(x+1).get(y+1).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x+1).get(y+1), Direction.DOWN_RIGHT);
                valid = true;
            }
        } else {
            //if the tile is on an odd row

            //Check tile to the above left
            if (gameBoard.get(x-1).get(y-1).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x-1).get(y-1), Direction.UP_LEFT);
                valid = true;
            }
            //Check the tile to the above right
            if (gameBoard.get(x-1).get(y).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x-1).get(y), Direction.UP_RIGHT);
                valid = true;
            }

            //Check the tile to the left
            if (gameBoard.get(x).get(y-1).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x).get(y-1), Direction.LEFT);
                valid = true;
            }

            //Check the tile to the right
            if (gameBoard.get(x).get(y+1).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x).get(y+1), Direction.RIGHT);
                valid = true;
            }

            //Check the tile to the below left
            if (gameBoard.get(x+1).get(y-1).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x+1).get(y-1), Direction.DOWN_LEFT);
                valid = true;
            }

            if (gameBoard.get(x+1).get(y).getType() != Tile.Bug.EMPTY) {
                grasshopperSearch(gameBoard.get(x+1).get(y), Direction.DOWN_RIGHT);
                valid = true;
            }
        }

        return valid;
    }

    /**
     * Recursive function to determine where a grasshopper tile will stop moving. Intakes a tile
     * and a direction. Modifies ArrayList potentialMoves to reflect findings.
     * @param tile current tile in path of Grasshopper to be checked
     * @param direction direction of travel for a grasshopper piece.
     * @return Should always return true.
     */
    public boolean grasshopperSearch(Tile tile, Direction direction) {
        int x = tile.getIndexX();
        int y = tile.getIndexY();

        if(x % 2 == 0) {
            //if tile is on an even row
            switch (direction) {
                case UP_LEFT:

                    //If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x-1).get(y), direction);

                case UP_RIGHT:

                    //If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x-1).get(y+1), direction);
                case LEFT:

                    //If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x).get(y-1), direction);
                case RIGHT:

                    //If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x).get(y+1), direction);
                case DOWN_LEFT:

                    // If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x+1).get(y), direction);
                case DOWN_RIGHT:

                    // If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x+1).get(y+1), direction);
            }
        } else {
            //if tile is on an odd row
            switch (direction) {
                case UP_LEFT:

                    // If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x-1).get(y-1), direction);
                case UP_RIGHT:

                    // If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x-1).get(y), direction);
                case LEFT:

                    // If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x).get(y-1), direction);
                case RIGHT:

                    // If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x).get(y+1), direction);
                case DOWN_LEFT:

                    // If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    } // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x+1).get(y-1), direction);
                case DOWN_RIGHT:

                    // If tile is empty, add to potential moves
                    if(tile.getType() == Tile.Bug.EMPTY){
                        potentialMoves.add(tile);
                        return true;

                    }  // Otherwise, continue search in same direction
                    return grasshopperSearch(gameBoard.get(x+1).get(y), direction);
            }
        }
        return false;
    }

    /**
     * Ensures Tile tile is empty and next to an occupied Tile that is not the selected Tile.
     * Helper function for piece search functions
     *
     * @param selectedTile the tile the player wishes to move
     * @param tile the tile to be checked against
     * @return
     */
    public boolean nextTo(Tile selectedTile, Tile tile) {
        if (tile.getType() == Tile.Bug.EMPTY) {
            // Assign tile coordinates to integer values for ease of handling
            int x = tile.getIndexX();
            int y = tile.getIndexY();

            if (x % 2 == 0) {
                // For even rows

                if(gameBoard.get(x-1).get(y).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x-1).get(y) != selectedTile){
                    //Check tile above left of tile
                    return true;
                } else if(gameBoard.get(x-1).get(y+1).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x-1).get(y+1) != selectedTile) {
                    //Check tile above right of tile
                    return true;
                } else if(gameBoard.get(x).get(y-1).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x).get(y-1) != selectedTile) {
                    //Check tile to the left of tile
                    return true;
                } else if(gameBoard.get(x).get(y+1).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x).get(y+1) != selectedTile) {
                    //Check tile to the right of tile
                    return true;
                } else if(gameBoard.get(x+1).get(y).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x+1).get(y) != selectedTile) {
                    //Check tile below left of tile
                    return true;
                } else if(gameBoard.get(x+1).get(y+1).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x+1).get(y+1) != selectedTile) {
                    //Check tile below right of tile
                    return true;
                }
                } else {
                // For odd rows

                if(gameBoard.get(x-1).get(y-1).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x-1).get(y-1) != selectedTile ){
                    //Check tile above left of tile
                    return true;
                } else if(gameBoard.get(x-1).get(y).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x-1).get(y) != selectedTile ) {
                    //Check tile above right of tile
                    return true;
                } else if(gameBoard.get(x).get(y-1).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x).get(y-1) != selectedTile ) {
                    //Check tile to the left of tile
                    return true;
                } else if(gameBoard.get(x).get(y+1).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x).get(y+1) != selectedTile ) {
                    //Check tile to the right of tile
                    return true;
                } else if(gameBoard.get(x+1).get(y-1).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x+1).get(y-1) != selectedTile) {
                    //Check tile below left of tile
                    return true;
                } else if(gameBoard.get(x+1).get(y).getType() != Tile.Bug.EMPTY &&
                        gameBoard.get(x+1).get(y) != selectedTile) {
                    //Check tile below right of tile
                    return true;
                }

            }
        }
        return false;

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

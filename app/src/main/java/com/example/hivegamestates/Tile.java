package com.example.hivegamestates;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Tile {

    public enum Bug {
        EMPTY,
        QUEEN_BEE,
        BEETLE,
        ANT,
        GRASSHOPPER,
        SPIDER
    }

    public enum PlayerPiece {
        EMPTY,
        B,
        W
    }

    // Class Variables
    private Bug type;
    private Tile onTopOf;
    private PlayerPiece piece;
    private int indexX; //integer index in the arrayList of tiles
    private int indexY;
    private boolean visited; //variable for the DFS

    /**
     * Makes a default constructor for a tile
     * @param x
     * @param y
     */
    public Tile(int x, int y, PlayerPiece piece) {
        //default constructor could make a null tile
        type = Bug.EMPTY;
        onTopOf = null;
        indexX = x;
        indexY = y;
        this.piece = piece;
    }

    /**
     * Uhh this isn't supposed to bethe copy constructor. Its supposed to be used in move to
     * make a piece have another beneath it.
     * @param bug which type of piece is getting copied
     * @param onTop
     */
    public Tile(Bug bug, Tile onTop, int x, int y, PlayerPiece pieceInstance) {
        type = bug;
        piece = pieceInstance;
        onTopOf = onTop;
        indexX = x;
        indexY = y;
    }

    /**
     * Copy constructor
     * @param other
     * @return a new copied Tile object
     */
    public Tile(Tile other) {
        if (other == null) {

        } else {
            //Tile tile = new Tile(other.getIndexX(), other.getIndexY(), other.getPlayerPiece());
            this.indexX = other.getIndexX();
            this.indexY = other.getIndexY();
            this.piece = other.getPlayerPiece();
            this.onTopOf = new Tile(other.getOnTopOf());
            this.type = other.getType();
            this.visited = other.visited;
        }
    }

    public void setType(Bug bug) {
        type = bug;
    }
    public void setOnTopOf(Tile tile) {
        onTopOf = tile;
    }
    public void setIndexX(int x) {
        indexX = x;
    }
    public void setIndexY(int y ) {indexY = y; }
    public int getIndexX() {
        return indexX;
    }
    public int getIndexY() {
        return indexY;
    }
    public Bug getType() {
        return type;
    }
    public Tile getOnTopOf() {
        return onTopOf;
    }
    public PlayerPiece getPlayerPiece(){
        return piece;
    }
    public boolean getVisited() { return visited; }
    public void setVisited(boolean v){
        visited = v;
    }

    /**
     * Method for drawing hexagon.
     * @param mCanvas
     * @param x
     * @param y
     * @param radius
     * @param sides
     * @param startAngle
     * @param anticlockwise
     * @param paint
     */
    private void drawPolygon(Canvas mCanvas, float x, float y, float radius, float sides, float startAngle, boolean anticlockwise, Paint paint) {
        if (sides < 3) {
            return; }
        float a = ((float) Math.PI *2) / sides * (anticlockwise ? -1 : 1);
        mCanvas.save();
        mCanvas.translate(x, y);
        mCanvas.rotate(startAngle);
        Path path = new Path();
        path.moveTo(radius, 0);
        for(int i = 1; i < sides; i++) {
            path.lineTo(radius * (float) Math.cos(a * i), radius * (float) Math.sin(a * i));
        }
        path.close();
        mCanvas.drawPath(path, paint);
        mCanvas.restore();
    }
}

package com.example.hivegamestates;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Tile {


    public enum Piece {
        EMPTY,
        POTENTIAL,
        QUEEN_BEE,
        BEETLE,
        ANT,
        GRASSHOPPER,
        SPIDER
    }

    public enum PlayerPiece {
        EMPTY,
        BLACK,
        WHITE
    }

    // Class Variables
    private Piece type;
    private Tile onTopOf;
    private PlayerPiece piece;
    private int coordX; //integer index in the arrayList of tiles
    private int coordY;

    /**
     * Makes a default constructor for a tile
     * @param x
     * @param y
     */
    public Tile(int x, int y, PlayerPiece piece) {
        //default constructor could make a null tile
        type = Piece.EMPTY;
        onTopOf = null;
        coordX = x;
        coordY = y;
        this.piece = piece;
    }

    /**
     *
     * @param piece which type of piece is getting copied
     * @param tile
     */
    public Tile(Piece piece, Tile tile, int x, int y, PlayerPiece p) {
        type = piece;
        onTopOf = tile.onTopOf;
        coordX = x;
        coordY = y;
        this.piece = p;
    }

    /**
     * Copy constructor
     * @param other
     * @return a new copied Tile object
     */
    public Tile Tile(Tile other){
        Tile tile = new Tile(other.coordX, other.coordY, other.piece);
        tile.onTopOf = other.onTopOf;
        tile.type = other.type;

        return tile;
    }

    public void setType(Piece piece) {
        type = piece;
    }

    public void setOnTopOf(Tile tile) {
        onTopOf = tile;
    }

    public Piece getType() {
        return type;
    }

    public Tile getOnTopOf() {
        return onTopOf;
    }

    public PlayerPiece getPiece(){
        return piece;
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

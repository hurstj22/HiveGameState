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

    // Class Variables
    private Piece type;
    private Tile onTopOf;
//    private int coordX;  Do we need these in here? This would represent the position in the list
//    private int coordY;  to make it (potentially) easier to check for valid moves.


    public Tile() {
        //default constructor could make a null tile
        type = Piece.EMPTY;
        onTopOf = null;
    }

    /**
     * Copy constructor for the tile class
     * @param piece which type of piece is getting copied
     * @param tile
     */
    public Tile(Piece piece, Tile tile) {
        type = piece;
        onTopOf = tile.onTopOf;
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

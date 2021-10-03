package com.example.hivegamestates;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Tile {


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

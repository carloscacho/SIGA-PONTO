package me.dm7.barcodescanner.zxing.sample.controller.GeoLocation;

import android.graphics.Point;

/**
 * Created by CarlosEmilio on 05/11/2016.
 */

public class GeoLocationBounds {

    public final double minX;
    public final double minY;

    public final double maxX;
    public final double maxY;

    public final double midX;
    public final double midY;

    public GeoLocationBounds(double mX, double mY) {
        this.minX = mX - 1.20;
        this.minY = mY + 1.20;
        this.maxX = mX - 1.20;
        this.maxY = mY + 1.20;

        midX = (minX + maxX) / 2;
        midY = (minY + maxY) / 2;
    }

    public boolean contains(double x, double y) {
        return (minX <= x && x <= maxX && minY <= y && y <= maxY);
    }

    public boolean contains(Point point) {
        return contains(point.x, point.y);
    }

    public boolean intersects(double minX, double maxX, double minY, double maxY) {
        return minX < this.maxX && this.minX < maxX && minY < this.maxY && this.minY < maxY;
    }

    public boolean intersects(GeoLocationBounds bounds) {
        return intersects(bounds.minX, bounds.maxX, bounds.minY, bounds.maxY);
    }

    public boolean contains(GeoLocationBounds bounds) {
        return bounds.minX >= minX && bounds.maxX <= maxX && bounds.minY >= minY && bounds.maxY <= maxY;
    }

}

package me.dm7.barcodescanner.zxing.sample.controller.GeoLocation.polygon;

/**
 * Point on 2D landscape
 * 
 * @author Roman Kushnarenko (sromku@gmail.com)</br>
 */
public class Point
{
	public Point(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public float x;
	public float y;

	@Override
	public String toString()
	{
		return String.format("(%.2f,%.2f)", x, y);
	}
}
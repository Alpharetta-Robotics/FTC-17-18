package org.firstinspires.ftc.commonteamcode.util;

/**
 * Created by Benjamin Jensrud on 10/20/2017.
 */

public class Point {
	private double x, y;

	public Point (double[] point) {
		x = point[0];
		y = point[1];
	}

	public Point (double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double[] getValues() {
		return new double[]{x,y};
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}
}

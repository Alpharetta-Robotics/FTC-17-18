package org.firstinspires.ftc.commonteamcode.util;

/**
 * Created by Benjamin Jensrud on 10/20/2017.
 * Can define a polygon and determine via ray-tracing if a point is contained within
 */

public class PointDefinedArea {
	private double[][] area;

	public PointDefinedArea (double[][] area) {
		this.area = area;
	}

	public PointDefinedArea (Point[] points) {
		double[][] area = new double[2][points.length];
		for (int i = 0; i < points.length; i++) {
			area[i] = points[i].getValues();
		}
		this.area = area;
	}

	public boolean contains (double[] point) {
		return contains(new Point(point));
	}

	public boolean contains (Point point) {
		boolean inside = false;
		int len = area.length;
		for (int i = 0; i < len; i++) {
			if (intersects(area[i], area[(i+1) % len], point.getValues()))
				inside = !inside;
		}
		return inside;
	}

	private boolean intersects(double[] A, double[] B, double[] P) {
		if (A[1] > B[1])
			return intersects(B,A,P);

		if (P[1] == A[1] || P[1] == B[1])
			P[1] += 0.0001;

		if (P[1] > B[1] || P[1] < A[1] || P[0] > Math.max(A[0], B[0]))
			return false;

		if (P[0] < Math.min(A[0], B[0]))
			return true;

		double red = (P[1] - A[1]) / (P[0] - A[0]);
		double blue = (B[1] - A[1]) / (B[0] - A[0]);
		return red >= blue;
	}
}

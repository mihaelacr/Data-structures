package own;

import java.util.Arrays;

/*
 * Computing the convex hull of a set of points using
 * the Graham scan algorithm.
 * Application: find the two points with biggest euclidean distance between
 * them given a set of points.
 * http://en.wikipedia.org/wiki/Graham_scan
 */

public class ConvexHull {

	private int n;
	private Point[] points;

	public ConvexHull(Point[] points) {
		this.n = points.length;
		this.points = points;
	}

	private Point getPointWithLowestYCoord() {
		Point lowest_point = points[0];
		for (int i = 1; i < n; i++) {
			if (points[i].getY() < lowest_point.getY()) {
				lowest_point = points[i];
			} else if (points[i].getY() < lowest_point.getY()) {
				if (points[i].getX() > lowest_point.getX())
					lowest_point = points[i];
			}
		}
		return lowest_point;
	}

	public int computeConvexHull() {
		if (n <= 2)
			return n;
		Point point_with_lowest_y_coord = getPointWithLowestYCoord();
		for (int i = 0; i < n; i++) {
			points[i].setComparatorPoint(point_with_lowest_y_coord);
		}
		Arrays.sort(points);
		// decide if an angle is counterclockwise or not
		// if it is not counterclockwise, do not include it in the convexHull
		int convex_hull_index = 1;
		int i = 2;
		while (i < n) {
			while (!Point.isCounterclockwise(points[convex_hull_index - 1],
					points[convex_hull_index], points[i])) {
				if (convex_hull_index >= 1)
					convex_hull_index--;
				else if (i == n)
					// all points are collinear
					break;
				else
					i++;
			}
			convex_hull_index++;
			swap(points, convex_hull_index, i);
		}
		return convex_hull_index;
	}

	private void swap(Point[] points, int index1, int index2) {
		assert (index1 < points.length);
		assert (index2 < points.length);

		Point aux = points[index1];
		points[index1] = points[index2];
		points[index2] = aux;
	}

	public static void main(String[] args) {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, -1);
		Point p3 = new Point(-1, 1);
		Point p4 = new Point(-1, -1);
		Point p5 = new Point(0, 0);
		Point p6 = new Point(0, 1);
		Point[] points = new Point[6];
		points[0] = p1;
		points[1] = p2;
		points[2] = p3;
		points[3] = p4;
		points[4] = p5;
		points[5] = p6;

		// System.out.println(p3.getPolarAngle(p2));
		// System.out.println(p5.getPolarAngle(p2));
		new ConvexHull(points).computeConvexHull();
		// System.out.println(Point.isCounterclockwise(p1, p3, p5));
	}
}

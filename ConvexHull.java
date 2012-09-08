package own;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Computing the convex hull of a set of points using
 * the Graham scan algorithm.
 * Application: find the two points with biggest euclidean distance between
 * them given a set of points.
 */

public class ConvexHull {

	private int n;
	private Point[] points;

	public ConvexHull(int n) {
		this.n = n;
		points = new Point[n];
		readPoints();
	}

	public ConvexHull(Point[] points) {
		this.n = points.length;
		this.points = points;
	}

	private void readPoints() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < n; i++) {
			System.out.println("Introduce the next point");
			double x = 0;
			double y = 0;
			boolean try_again = true;
			int tries = 0;
			while (try_again && tries < 5)
				try {
					System.out.println("x=");
					x = Double.parseDouble(br.readLine());
					try_again = false;
				} catch (IOException e) {
					try_again = true;
					tries++;
					System.out.println("Invalid input, try again");
					if (tries == 5) {
						System.out
								.println("You are unable to pass in a valid coordinate");
						System.exit(0);
					}
				}
			try_again = true;
			tries = 0;
			while (try_again && tries < 5)
				try {
					System.out.println("y=");
					y = Double.parseDouble(br.readLine());
					try_again = false;
				} catch (IOException e) {
					try_again = true;
					tries++;
					System.out.println("Invalid input, try again");
					if (tries == 5) {
						System.out
								.println("You are unable to pass in a valid coordinate");
						System.exit(0);
					}
				}
			points[i] = new Point(x, y);
		}
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

	// TODO mihaelacr handle case where n < 2
	public void computeConvexHull() {
		List<Point> convex_hull = new ArrayList<Point>();
		Point point_with_lowest_y_coord = getPointWithLowestYCoord();
		// System.out.println(point_with_lowest_y_coord);
		for (int i = 0; i < n; i++) {
			points[i].setComparatorPoint(point_with_lowest_y_coord);
		}
		Arrays.sort(points);
		// decide if an angle is counterclockwise or not
		// if it is not counterclockwise, do not include it in the convexHull
		Point second_to_last_point = points[0];
		// assert (second_to_last_point.getY() ==
		// point_with_lowest_y_coord.getY());
		Point last_point = points[1];
		// for (Point p : points) {
		// System.out.println(p);
		// }

		convex_hull.add(second_to_last_point);
		convex_hull.add(last_point);
		for (int i = 2; i < n; i++) {
			Point current_point = points[i];
			// System.out.println(current_point);
			last_point = convex_hull.get(convex_hull.size() - 1);
			second_to_last_point = convex_hull.get(convex_hull.size() - 2);
			if (Point.isCounterclockwise(second_to_last_point, last_point,
					current_point)) {
				// System.out.println("is counter clockwise");

				convex_hull.add(current_point);
				second_to_last_point = last_point;
				last_point = current_point;
			} else {
				convex_hull.remove(last_point);
				convex_hull.add(current_point);
			}
		}
		printList(convex_hull);
	}

	// TODO refractor
	public void printList(List list) {
		for (Object o : list)
			System.out.println(o);
	}

	public static void main(String[] args) {
		// TODO mihaelacr, promt for number of points
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

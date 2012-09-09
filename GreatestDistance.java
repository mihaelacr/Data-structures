package own;

/**
 * Finds the maximum distance between n set of points. Worst case is O(n^2) when
 * the polygon determined by the set of points is convex. Can be reduced to
 * O(nlogn).
 */

public class GreatestDistance {

	Point[] points;

	public GreatestDistance(Point[] points) {
		// readPoints();
		this.points = points;
	}

	public double getMaximumDistance() {
		ConvexHull hull = new ConvexHull(points);
		Point[] hull_points = hull.getConvexHull();
		double max_distance = 0;
		// TODO (mihaelacr) this can be reduce to O(n)
		for (int i = 0; i < hull_points.length; i++)
			for (int j = i + 1; j < hull_points.length; j++) {
				Point p1 = points[i];
				Point p2 = points[j];
				double current_distance = p1.getEuclideanDistance(p2);
				if (current_distance > max_distance) {
					max_distance = current_distance;
				}

			}
		return max_distance;
	}
}

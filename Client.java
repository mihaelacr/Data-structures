package own;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

	private static Point[] readPoints() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce the number of points");
		int n = 0;
		try {
			n = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			System.out.println("Please introduce a natural number.");
			System.exit(0);
		}
		Point[] points = new Point[n];
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
		return points;
	}

	public static void main(String[] args) {
		Point[] points = readPoints();
		System.out.println(new GreatestDistance(points).getMaximumDistance());
	}
}

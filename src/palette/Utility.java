package palette;

public class Utility {
	public static void printMatrix(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.printf(" %d ", a[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int max(double a, double b, double c) {
		if (a > b) {
			if (a > c) {
				return 1;
			} else {
				return 3;
			}
		} else {
			if (b > c) {
				return 2;
			} else {
				return 3;
			}
		}
	}

	public static int min(double a, double b, double c) {
		if (a < b) {
			if (a < c) {
				return 1;
			} else {
				return 3;
			}
		} else {
			if (b < c) {
				return 2;
			} else {
				return 3;
			}
		}
	}

	public static double val_max(double a, double b, double c) {
		if (a > b) {
			if (a > c) {
				return a;
			} else {
				return c;
			}
		} else {
			if (b > c) {
				return b;
			} else {
				return c;
			}
		}
	}

	public static double val_min(double a, double b, double c) {
		if (a < b) {
			if (a < c) {
				return a;
			} else {
				return c;
			}
		} else {
			if (b < c) {
				return b;
			} else {
				return c;
			}
		}
	}
}

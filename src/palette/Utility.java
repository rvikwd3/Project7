package palette;

public class Utility {
	public static double rgb2hue(double red, double green, double blue){
		double n, d, input, hue;

		n=(0.5)*((red-green)+(red-blue));
		d=Math.pow((Math.pow(red-green,2)+((red-blue)*(green-blue))),0.5);

		hue = Math.acos(n/(d+0.000001)) * (180/Math.PI);

		//Debugging sout
		System.err.println("Red:\t"+red+"\nGreen:\t"+green+"\nBlue:\t"+blue+"\nn:\t"+n+"\nd:\t"+d+"\nacosd:\t"+hue);

		if(blue>green) {
			System.err.println("\t\t\t\t\t\t\t\tBlue>Green");
			hue = 360 - hue;
		}

		hue = hue / 360.0;

		return hue;
	}

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

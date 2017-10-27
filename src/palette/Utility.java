package palette;

public class Utility {
	public static double rgb2hue(double red, double green, double blue){
		double n, d, input, hue=0;
//		double d_sum, d_diff;
		double rn, gn, bn;

		//RGB->Hue Method 1
/*		n=(0.5)*((red-green)+(red-blue));
		d=Math.pow((Math.pow(red-green,2)+((red-blue)*(green-blue))),0.5);


		hue = Math.acos(n/(d+0.000001)) * (180/Math.PI);

		if(blue>green) {
//			System.err.println("\t\t\t\t\t\t\t\tBlue>Green");
			hue = 360 - hue;
		}

		hue = hue / 360.0;*/

		//RGB->Hue Method 2
/*		n = red - (green/2.0) - (blue/2.0);
		d_sum = Math.pow(red,2) + Math.pow(green,2) + Math.pow(blue,2);
		d_diff = (red * green) + (red * blue) + (green * blue);
		d = d_sum - d_diff;

		if(d == 0.0){
			return 0.0;
		}

		if(green >= blue){
			hue = Math.acos(n/d) * (180/Math.PI);
		}else if(blue > green){
			hue = 360 - Math.acos(n/d) * (180/Math.PI);
		}*/

		//RGB->Hue Method 3
		red = red/255.0;
		green = green/255.0;
		blue = blue/255.0;

		rn = 	red / (red + green + blue);
		gn = 	green / (red + green + blue);
		bn =	blue / (red + green + blue);

		n = (0.5 * ((rn - gn) + (rn - bn)));
		d = Math.sqrt(	(rn - gn) * (rn - gn) + (rn -bn) * (gn - bn));
		if(d==0.0){
			return 0.0;
		}

		hue = Math.acos(n/d);

		if(blue > green){
			hue = 2*Math.PI - hue;
		}

		hue = hue * (180/Math.PI);

		//Debugging sout
//		System.err.println("Red:\t"+red+"\nGreen:\t"+green+"\nBlue:\t"+blue+"\nn:\t"+n+"\nd:\t"+d+"\nacosd:\t"+hue);

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

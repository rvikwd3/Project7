/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palette;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;

/**
 *
 * @author parnika
 */
public class ProcessImage {
    
    public BufferedImage convFileToImage(String filename) {
		// TODO Auto-generated method stub
		File fl = new File(filename);
		BufferedImage image = null;
		try {
			image = ImageIO.read(fl); // convert sample image to BufferedImage
			// Object
		} catch (IOException e) {
		}
		return image;
	}
		
	public int[][] convImageToArray(BufferedImage bi) {
		// TODO Auto-generated method stub
		int[][] c = new int[bi.getHeight()][bi.getWidth()];

		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				c[i][j] = bi.getRGB(j, i);
			}
		}
		return c;
	}

	public myColor[][] convRGBArrayToMyColorArray(int input_array[][]) {

		int dimension_1 = input_array.length;
		int dimension_2 = input_array[0].length;

		//Transferring rgbArray to myColorArray
		myColor[][] colorArray = new myColor[dimension_1][dimension_2];

		for(int i=0; i<dimension_1; i++) {
			for (int j = 0; j < dimension_2; j++) {
				colorArray[i][j] = new myColor();
				colorArray[i][j].getColorName("(" + Integer.toString(i) + "," + Integer.toString(j) + ")");

				//Extract RGB values from TYPE_INT_ARGB
				colorArray[i][j].red = (input_array[i][j] >> 16) & 0xff;
				colorArray[i][j].green = (input_array[i][j] >> 8) & 0xff;
				colorArray[i][j].blue = (input_array[i][j]) & 0xff;

				//Debug test color for each pixel
//                System.out.println("\t"+colorArray[i][j].color_name);
//                System.out.println("\t"+colorArray[i][j].red);
//                System.out.println("\t"+colorArray[i][j].green);
//                System.out.println("\t"+colorArray[i][j].blue);
			}
		}

		return colorArray;
	}

	/*
	Convert RGB matrix to only Hue component matrix
	 */
	public double[][] convRGBToHueArray(int input_array[][]){

		int dimension_1 = input_array.length;
		int dimension_2 = input_array[0].length;

		int red, green, blue;
		double n, d;

		//Return array
		double[][] hue_array = new double[dimension_1][dimension_2];

		for(int i=0; i<dimension_1; i++) {
			for (int j = 0; j < dimension_2; j++) {

				red = (input_array[i][j] >> 16) & 0xff;
				green = (input_array[i][j] >> 8) & 0xff;
				blue = (input_array[i][j]) & 0xff;

				//moved rgb->hue in Utility.RGB2Hue

				hue_array[i][j] = Utility.rgb2hue(red, green, blue);

				//Debugging sout
				System.out.println("Final Hue Array:\t"+hue_array[i][j]);

			}
		}

		return hue_array;
	}

	public double[][] convRGBToHueArray_RaviImplementation(int input_array[][]){

		int dimension_1 = input_array.length;
		int dimension_2 = input_array[0].length;

		double red, green, blue, val_max, val_min, delta, n, d;

		//Return array
		double[][] hue_array = new double[dimension_1][dimension_2];

		for(int i=0; i<dimension_1; i++) {
			for (int j = 0; j < dimension_2; j++) {

				red = ((input_array[i][j] >> 16) & 0xff) / 255.0;
				green = ((input_array[i][j] >> 8) & 0xff) / 255.0;
				blue = ((input_array[i][j]) & 0xff) / 255.0;

				val_max = Utility.val_max(red, green, blue);
				val_min = Utility.val_min(red, green, blue);

				delta = val_max - val_min;

				if(delta == 0)
					hue_array[i][j] = 0;
				else {

					switch (Utility.max(red, green, blue)) {
						case 1:
							hue_array[i][j] = (green - blue) / delta;
							if(hue_array[i][j] < 0){	hue_array[i][j] += 6.0;	}

							break;
						case 2:
							hue_array[i][j] = (blue - red) / delta + 2;
							break;
						case 3:
							hue_array[i][j] = (red - green) / delta + 4;
							break;
						default:
							System.err.println("(" + i + "," + j + ")\t\tNot a valid color switch");
							hue_array[i][j] = 0;
					}
				}

				//Debugging sout
//				System.out.println("\n("+i+","+j+")\nInputRGB:\t"+input_array[i][j]+"\nRed:\t"+red+"\nGreen:\t"+green+"\nBlue:\t"+blue+"\ndelta:\t"+delta+"\nval_max:\t"+val_max+"\nacosd:\t"+hue_array[i][j]);

				hue_array[i][j] *= 60.0;

				//Debugging sout
//				System.out.println("Final Hue Array:\t"+hue_array[i][j]);

			}
		}

		return hue_array;
	}
}
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
}

package palette;

import java.awt.image.BufferedImage;

public class Main{
    public static void main(String [] args){
        System.out.println("Starting Main");

        String input_image_filename = args[0];

        Palette std_palette = new Palette();

        //Adding white to standard palette
/*        myColor white = new myColor("White", 255, 255, 255);
        std_palette.addToPalette(white);
		System.out.println("Added white to std_palette");*/

		//Adding yellow to standard palette
		myColor yellow1 = new myColor("DarkYellow", 128, 128, 0);
		std_palette.addToPalette(yellow1);
		System.out.println("Added yellow to std_palette");

		System.out.println(Utility.rgb2hue(128, 128, 0));

		myColor yellow2 = new myColor("LightYellow", 255, 255, 224);
		std_palette.addToPalette(yellow2);
		System.out.println("Added yellow to std_palette");

		System.out.println(Utility.rgb2hue(255,255, 224));

		System.out.println("Added dark orange to std_palette");
		System.out.println(Utility.rgb2hue(77, 50, 0));



		//Adding orange to standard palette
		myColor orange = new myColor("Orange", 255,165,0);
		std_palette.addToPalette(orange);
		System.out.println("Added myorange to std_palette");
		System.out.println(Utility.rgb2hue(255, 165, 0));

			//Adding orange seems to overpower red


        ProcessImage process_img = new ProcessImage();
        BufferedImage input_image = process_img.convFileToImage(input_image_filename);
		System.out.println("Converted file to image");
		int[][] input_image_rgb_array = process_img.convImageToArray(input_image);
		System.out.println("Converted image to rgbarray");

        /*
        We'll convert the RGB matrix to a matrix with only the Hue component
         */
		double[][]input_image_hue_array = process_img.convRGBToHueArray_RaviImplementation(input_image_rgb_array);
		System.out.println("converted RGB Matrix to Hue matrix");

//        myColor[][] color_array = process_img.convRGBArrayToMyColorArray(input_image_rgb_array);


        ConnectedColorComponent C = new ConnectedColorComponent();
        C.initialize(input_image_rgb_array, 50);
		System.out.println("Initialized CCC with rgb array");

        //	Name pixels by labels according to nearby labels

        int [][] CCC_Matrix = C.labelColouredComponents(50);
		System.out.println("Labelled CCC by labels");
		//Outputs lavel matrix
//        Utility.printMatrix(C.getL());

		LabelProcessor lp = new LabelProcessor(C.getL());
		System.out.println("Called LabelProcessor");

        ColorProcessor rgb_processor = new ColorProcessor(std_palette);
        rgb_processor.listPaletteColors();

        System.out.println("Dimension 1:"+input_image_rgb_array.length);
        System.out.println("Dimension 2:"+input_image_rgb_array[0].length);


//        rgb_processor.listClosestPaletteColors(color_array, std_palette, 50);
//        std_palette.putColorCountTable();
        rgb_processor.listClosestPaletteColors(input_image_hue_array, std_palette, 50);
        std_palette.putColorCountTable();

        //Checking radians vs degrees
//		double pi;
//		pi = Math.atan(1)*4;
//        System.out.println("pi"+pi+"\tcos(pi)"+Math.cos(pi));

        System.out.println("End Main");
    }
}

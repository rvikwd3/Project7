package palette;

import java.awt.image.BufferedImage;

public class Main{
    public static void main(String [] args){
        System.out.println("Starting Main");

        String input_image_filename = args[0];

        Palette std_palette = new Palette();

        myColor white = new myColor("white", 255, 255, 255);
        //std_palette.putPalette();

        //Testing pallete distance & color
//        myColor test_color = new myColor("test", 0,0,255);
//        test_color.putColor();
//
//        double distance = std_palette.rgbDistance(test_color);
//        System.out.println("Final Distance:"+"\t"+distance);
//        System.out.println("Closest Color:");
//        std_palette.rgbClosestColor(test_color).putColor();

        ProcessImage process_img = new ProcessImage();
        BufferedImage input_image = process_img.convFileToImage(input_image_filename);
        int[][] input_image_rgb_array = process_img.convImageToArray(input_image);

        /*
        We'll convert the RGB matrix to a matrix with only the Hue component
         */
//        HSI[][] input_image_hsi_array = process_img.convRGBArrayToHSIArray(input_image_rgb_array);
//        double[][] input_image_hue_array = process_img.convRGBToHueArray(input_image_rgb_array);
		double[][]input_image_hue_array = process_img.convRGBToHueArray_RaviImplementation(input_image_rgb_array);


        myColor[][] color_array = process_img.convRGBArrayToMyColorArray(input_image_rgb_array);


        ConnectedColorComponent C = new ConnectedColorComponent();
        C.initialize(input_image_rgb_array, 50);

        //	Name pixels by labels according to nearby labels

        int [][] CCC_Matrix = C.labelColouredComponents(50);
//        Utility.printMatrix(C.getL());

        // Try initializing myColor matrix
//		C.initializeMyColor(color_array, 50);
//		int [][] CCC_MyColor = C.labelMyColorComponents(50);
//		myColor[][] MyColor_LabelMatrix = C.getP_my_color();

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

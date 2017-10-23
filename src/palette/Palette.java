package palette;

import java.lang.Math;

class Palette {
    palette.myColor[] color_list = new palette.myColor[3];
    int color_count[] = new int[3];

    myColor closest_color = new myColor();

    Palette() {
        System.out.println("Initializing standard palette");

        //Set the color_count array to 0
        for(int i=0; i<color_count.length; i++){color_count[i]=0;};

        // We'll set the standard RGB Colors
        palette.myColor color_red = new palette.myColor("red", 255, 0, 0);
        palette.myColor color_green = new palette.myColor("green", 0, 255, 0);
        palette.myColor color_blue = new palette.myColor("blue", 0, 0, 255);

        //Explicit RGB palette initialization
//        color_red.getColorName("Red");
//        color_green.getColorName("Green");
//        color_blue.getColorName("Blue");
//
//        color_red.getColors(255, 0, 0);
//        color_green.getColors(0, 255, 0);
//        color_blue.getColors(0, 0, 255);

        color_list[0] = color_red;
        color_list[1] = color_green;
        color_list[2] = color_blue;
    }

    void putPalette(){
        System.out.println("Palette:");

        for(int i=0; i<color_list.length; i++){
            color_list[i].putColor();
        }
    }

    double rgbDistance(myColor input_color){
        double minimum_distance = Double.MAX_VALUE;
        double color_distance = 0;

        for(int i=0; i<color_list.length; i++){

            //Compute RGB Distances
            int color_distance_red, color_distance_blue, color_distance_green;

            color_distance_red = input_color.red - color_list[i].red;
            color_distance_green = input_color.green - color_list[i].green;
            color_distance_blue = input_color.blue - color_list[i].blue;

            //Debug palette names/values
//            System.out.println("\t\tColor:");
//            color_list[i].putColor();


            //Calculate cartesian RGB distance
            color_distance
                =   Math.pow(color_distance_red,2)+
                    Math.pow(color_distance_green,2)+
                    Math.pow(color_distance_blue,2);
            //Debug distance check
//            System.out.println("\t\tDistance:"+color_distance);

            //If the distance is the least,
            //then keep it as new minimum distance
            if (color_distance < minimum_distance){
                minimum_distance = color_distance;
                closest_color = color_list[i];

                //Debug check minimum distance
//                System.out.println("\t\tChanged minimum distance");
            }
        }

        return minimum_distance;
    }

    void resetClosestColor(){
        closest_color = new myColor();

        for(int i=0; i<color_list.length; i++){
            color_count[i] = 0;
        }
    }


    myColor rgbClosestColor(myColor input_color){
        //Run rgbDistance to re-calculate distance
        //read global closest_color and return that

        this.rgbDistance(input_color);
        return closest_color;
    }

    myColor rgbClosestColor(myColor input_color, int threshold){
        this.rgbDistance(input_color);
        return closest_color;
    }

    void incrementColorCount(String inc_color_name){
        for(int i=0; i<color_list.length; i++){
            if(color_list[i].color_name.equals(inc_color_name)){
                color_count[i]++;
            }
        }
    }

    void decrementColorCount(String inc_color_name){
        for(int i=0; i<color_list.length; i++){
            if(color_list[i].color_name.equals(inc_color_name)){
                color_count[i]--;
            }
        }
    }

    int putColorCount(String inc_color_name){
        int return_val = -1;

        for(int i=0; i<color_list.length; i++) {

            if (color_list[i].color_name.equals(inc_color_name)) {
                return_val = color_count[i];
            }
        }

        return return_val;
    }

    void putColorCountTable(){
        for(int i=0; i<color_list.length; i++){
            System.out.print(color_list[i].color_name+":\t");
            System.out.println(putColorCount(color_list[i].color_name));
        }

//        System.out.println("Red:\t"+std_palette.putColorCount("red"));
//        System.out.println("Green:\t"+std_palette.putColorCount("green"));
//        System.out.println("Blue:\t"+std_palette.putColorCount("blue"));
    }

    String[] putColorNames(){
        String[] color_names = new String[color_list.length];
        for(int i=0; i<color_names.length; i++){
            color_names[i] = color_list[i].color_name;
        }

        return color_names;
    }

}

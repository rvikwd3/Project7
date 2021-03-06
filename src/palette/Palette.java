package palette;

import java.lang.Math;
import java.util.HashMap;
import java.util.Iterator;

class Palette {
	HashMap<String, myColor> rgb_color_list = new HashMap<String, myColor>();
	HashMap<String, Integer> rgb_color_count = new HashMap<String, Integer>();

	HashMap<String, hsiColor> hsi_color_list = new HashMap<String, hsiColor>();
	HashMap<String, hsiColor> hsi_color_count = new HashMap<String, hsiColor>();

//    palette.myColor[] rgb_color_list = new palette.myColor[3];
//    int rgb_color_count[] = new int[3];

    myColor closest_color = new myColor();

    Palette(){
		System.out.println("Initializing standard palette in HashMap");

		//Set standard RGB colors as RGB objects
		myColor color_red = new myColor("Red", 255, 0, 0);
		myColor color_green = new myColor("Green", 0, 255, 0);
		myColor color_blue = new myColor("Blue", 0, 0, 255);

		rgb_color_list.put(color_red.color_name, color_red);
		rgb_color_list.put(color_green.color_name, color_green);
		rgb_color_list.put(color_blue.color_name, color_blue);

		//Initialize rgb_color_count list
		for(String key : rgb_color_list.keySet()){
			rgb_color_count.put(key, 0);
		}

	}

	void addToPalette(myColor new_color){
    	//Check if color aleady exists in palette
		for(String key : rgb_color_list.keySet()){
			if(key.equals(new_color.color_name)){
				System.out.println("Color "+new_color.color_name+" already exists");
				break;
			}
		}

		try{
			rgb_color_list.put(new_color.color_name, new_color);
			rgb_color_count.put(new_color.color_name, 0);
		}catch(Exception e){
			System.out.println("Error occurred at adding new color to HashMap");
		}
	}

    void putPalette(){
        System.out.println("Palette:");

		for(String key : rgb_color_list.keySet()){
			rgb_color_list.get(key).putColor();
		}
    }

    double hueDistance(double hue){
    	double minimum_distance = Double.MAX_VALUE;
    	double hue_distance = 0;
    	double palette_color_hue = 0;

    	for(String key : rgb_color_list.keySet()){

    		//Convert palette RGB color to Hue
			palette_color_hue = Utility.rgb2hue((double) rgb_color_list.get(key).red, (double) rgb_color_list.get(key).green, (double) rgb_color_list.get(key).blue);

			//Compute absolute distance between palette color & input hue
			hue_distance = (hue > palette_color_hue)?(hue-palette_color_hue):(palette_color_hue-hue);

			//If distance is least then keep it as new minimum distance
			if(hue_distance < minimum_distance){
				minimum_distance = hue_distance;
				closest_color = rgb_color_list.get(key);
			}


		}

		return minimum_distance;
	}

    double rgbDistance(myColor input_color){
        double minimum_distance = Double.MAX_VALUE;
        double color_distance = 0;

        for(String key : rgb_color_list.keySet()){

        	//If colors are the same then return 0 distance
			if(input_color.color_name.equals(rgb_color_list.get(key).color_name)){
				System.err.println("Colors "+input_color.color_name+", "+rgb_color_list.get(key).color_name+" are the same");
				break;
			}

            //Compute RGB Distances
            int color_distance_red, color_distance_blue, color_distance_green;

            color_distance_red = input_color.red - rgb_color_list.get(key).red;
            color_distance_green = input_color.green - rgb_color_list.get(key).green;
            color_distance_blue = input_color.blue - rgb_color_list.get(key).blue;

            //Debug palette names/values
//            System.out.println("\t\tColor:");
//            rgb_color_list[i].putColor();


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
                closest_color = rgb_color_list.get(key);

                //Debug check minimum distance
//                System.out.println("\t\tChanged minimum distance");
            }
        }

        return minimum_distance;
    }

    void resetColorCount(){
    	//New HashMap color count reset
		for(String key : rgb_color_count.keySet()){
			rgb_color_count.put(key, 0);
		}

    	//Old rgb_color_count array reset
/*		for(int i=0; i<rgb_color_list.length; i++){
			rgb_color_count[i] = 0;
		}*/
	}

    void resetClosestColor(){
        closest_color = new myColor();
        this.resetColorCount();
    }

    myColor hueClosestColor(double hue){
		//Run hueDistance to re-calculate distance
		//read global closest_color and return that
    	this.hueDistance(hue);
    	return closest_color;
	}

	myColor hueClosestColor(double hue, int threshold){
    	//Overloaded for threshold

		this.hueDistance(hue);
		return closest_color;
	}

    myColor rgbClosestColor(myColor input_color){
        //Run rgbDistance to re-calculate distance
        //read global closest_color and return that

        this.rgbDistance(input_color);
        return closest_color;
    }

    //Overloaded threshold closest color
    myColor rgbClosestColor(myColor input_color, int threshold){
        this.rgbDistance(input_color);
        return closest_color;
    }

    void incrementColorCount(String inc_color_name){
		//New hashmap version
		for(String key : rgb_color_list.keySet()){
			if(key.equals(inc_color_name)){
				rgb_color_count.put(key, rgb_color_count.get(key)+1);
			}
		}
    }

    void decrementColorCount(String inc_color_name){
		//New hashmap version
		for(String key : rgb_color_list.keySet()){
			if(key.equals(inc_color_name)){
				rgb_color_count.put(key, rgb_color_count.get(key)-1);
			}
		}
    }

    int putColorCount(String inc_color_name){
        int return_val = -1;

        for(String key : rgb_color_list.keySet()){

            if (rgb_color_list.get(key).color_name.equals(inc_color_name)) {
                return_val = rgb_color_count.get(key);
            }
        }

        return return_val;
    }

    void putColorCountTable(){
        for(String key : rgb_color_list.keySet()){
            System.out.print(rgb_color_list.get(key).color_name+":\t");
            System.out.println(putColorCount(rgb_color_list.get(key).color_name));
        }
    }

    String[] putColorNames(){
        String[] color_names = new String[rgb_color_list.size()];

        int i=0;
        for(String key : rgb_color_list.keySet()){
            color_names[i++] = rgb_color_list.get(key).color_name;
        }

        return color_names;
    }

}

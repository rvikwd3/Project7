package palette;

import java.lang.Math;
import java.util.HashMap;

class Palette {
	HashMap<String, myColor> color_list = new HashMap<String, myColor>();
	HashMap<String, Integer> color_count = new HashMap<String, Integer>();

//    palette.myColor[] color_list = new palette.myColor[3];
//    int color_count[] = new int[3];

    myColor closest_color = new myColor();

    Palette(){
		System.out.println("Initializing standard palette in HashMap");

		//Set standard RGB colors as RGB objects
		myColor color_red = new myColor("Red", 255, 0, 0);
		myColor color_green = new myColor("Green", 0, 255, 0);
		myColor color_blue = new myColor("Blue", 0, 0, 255);

		color_list.put(color_red.color_name, color_red);
		color_list.put(color_green.color_name, color_green);
		color_list.put(color_blue.color_name, color_blue);

		//Initialize color_count list
		for(String key : color_list.keySet()){
			color_count.put(key, 0);
		}

	}

	void addToPalette(myColor new_color){
    	try{
			color_list.put(new_color.color_name, new_color);
			color_count.put(new_color.color_name, 0);
		}catch(Exception e){
			System.out.println("Error occured at adding new color to HashMap");
		}
	}

    void putPalette(){
        System.out.println("Palette:");

		for(String key : color_list.keySet()){
			color_list.get(key).putColor();
		}
    }

    double hueDistance(double hue){
    	double minimum_distance = Double.MAX_VALUE;
    	double hue_distance = 0;
    	double palette_color_hue = 0;

    	for(String key : color_list.keySet()){

    		//Convert palette RGB color to Hue
			palette_color_hue = Utility.rgb2hue((double)color_list.get(key).red, (double)color_list.get(key).green, (double)color_list.get(key).blue);

			//Compute absolute distance between palette color & input hue
			hue_distance = (hue > palette_color_hue)?(hue-palette_color_hue):(palette_color_hue-hue);

			//If distance is least then keep it as new minimum distance
			if(hue_distance < minimum_distance){
				minimum_distance = hue_distance;
				closest_color = color_list.get(key);
			}


		}

		return minimum_distance;
	}

    double rgbDistance(myColor input_color){
        double minimum_distance = Double.MAX_VALUE;
        double color_distance = 0;

        for(String key : color_list.keySet()){

            //Compute RGB Distances
            int color_distance_red, color_distance_blue, color_distance_green;

            color_distance_red = input_color.red - color_list.get(key).red;
            color_distance_green = input_color.green - color_list.get(key).green;
            color_distance_blue = input_color.blue - color_list.get(key).blue;

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
                closest_color = color_list.get(key);

                //Debug check minimum distance
//                System.out.println("\t\tChanged minimum distance");
            }
        }

        return minimum_distance;
    }

    void resetColorCount(){
    	//New HashMap color count reset
		for(String key : color_count.keySet()){
			color_count.put(key, 0);
		}

    	//Old color_count array reset
/*		for(int i=0; i<color_list.length; i++){
			color_count[i] = 0;
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
		for(String key : color_list.keySet()){
			if(key.equals(inc_color_name)){
				color_count.put(key, color_count.get(key)+1);
			}
		}
    }

    void decrementColorCount(String inc_color_name){
		//New hashmap version
		for(String key : color_list.keySet()){
			if(key.equals(inc_color_name)){
				color_count.put(key, color_count.get(key)-1);
			}
		}
    }

    int putColorCount(String inc_color_name){
        int return_val = -1;

        for(String key : color_list.keySet()){

            if (color_list.get(key).color_name.equals(inc_color_name)) {
                return_val = color_count.get(key);
            }
        }

        return return_val;
    }

    void putColorCountTable(){
        for(String key : color_list.keySet()){
            System.out.print(color_list.get(key).color_name+":\t");
            System.out.println(putColorCount(color_list.get(key).color_name));
        }
    }

    String[] putColorNames(){
        String[] color_names = new String[color_list.size()];

        int i=0;
        for(String key : color_list.keySet()){
            color_names[i++] = color_list.get(key).color_name;
        }

        return color_names;
    }

}

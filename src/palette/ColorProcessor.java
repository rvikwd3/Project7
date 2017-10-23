package palette;

public class ColorProcessor {
    Palette palette;

    ColorProcessor(){
        this.palette = new Palette();
        System.out.println("Color Processor:\tUsing the standard RGB Palette");
    }

    ColorProcessor(Palette palette){
        this.palette = palette;
    }

    void listPaletteColors(){
        String[] color_names = palette.putColorNames();
        for(int i=0; i<color_names.length; i++){
            System.out.println(color_names[i]);
        }
    }

    void listClosestPaletteColors(myColor[][] color_array, Palette palette, int threshold){

        //Need to reset color_count array and closest_color in case this is called more than once
        palette.resetClosestColor();

        for(int i=0; i<color_array.length; i++){
            for(int j=0; j<color_array[0].length; j++){


                System.out.print(color_array[i][j].color_name+"\t"+"Closest color: ");
                String closest_color = palette.rgbClosestColor(color_array[i][j], threshold).color_name;

                //Increment closest palette color count
                palette.incrementColorCount(closest_color);

                //Display closest palette color
                System.out.println(palette.closest_color.color_name);
            }
        }
    }
}

package palette;

class myColor{
    int red, green, blue;
    String color_name = new String();

    myColor(){
        this.getColorName("null");
        this.getColors(0,0,0);
    }

    myColor(String name, int r, int g, int b){
        this.getColorName(name);
        this.getColors(r, g, b);
    }

    void getColorName(String color_name){
        this.color_name = color_name;
    }

    void getColors(int r, int g, int b){
        this.red = r;
        this.blue = b;
        this.green = g;
    }

    void putColor(){
        System.out.println(this.color_name+":\t");
        System.out.println(this.red+"\t"+this.green+"\t"+this.blue);
    }

    boolean equals(myColor input_color){
        boolean eq_red, eq_green, eq_blue, eq_name;
        eq_red = (this.red == input_color.red)?true:false;
        eq_green = (this.green == input_color.green)?true:false;
        eq_blue = (this.blue == input_color.blue)?true:false;

        eq_name = (this.color_name.equals(input_color.color_name));

        if(eq_red && eq_green && eq_blue && eq_name)
            return true;
        else
            return false;
    }

}

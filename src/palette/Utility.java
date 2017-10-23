package palette;

public class Utility {
    public static void printMatrix(int[][] a){
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[i].length; j++){
                System.out.printf(" %d ", a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}

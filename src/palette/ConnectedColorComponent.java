/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palette;

import java.util.Stack;

/**
 *
 * @author parnika
 */
public class ConnectedColorComponent {
    private class StackElement {
		int y;
		int xl;
		int xr;
		int dy;		
	}

	private int[][] L;
	int[][] getL() {
		return L;
	}

	private int[][] P;
	public int[][] getP() {
		return P;
	}

	/*
	Tried labelling individual pixels with colors
	 */
//	private myColor[][] P_my_color;
//	public myColor[][] getP_my_color(){ return P_my_color; }

	private double max_distance;
	private int maxLabel;
	public int getMaxLabel() {
		return maxLabel;
	}
	
	private int M;
	private int N;
	private double  threshold;
	private Stack <StackElement> segmentStack;

	public void ConnectedColorComponent()
	{
		L=null;
		P=null;

//		P_my_color = null;

		segmentStack=null;
		maxLabel = 0;
		max_distance = Math.sqrt(Math.pow(255,2)*3);
		threshold = 0.0;
	}

//	public void initializeMyColor(myColor [][] matrix, double percent){
//		N = matrix.length;
//		M = matrix[0].length;
//		threshold = percent;
//
//		max_distance = Math.sqrt(Math.pow(255,2)*3);
//
//		if (M > 0 && N > 0){
//			L = new int[N][M];
//			P = new int[N][M];
//			P_my_color = new myColor[N][M];
//
//			for(int i=0; i<N; i++){
//				for(int j=0; j<M; j++){
//
//					P[i][j] = 1;
//					L[i][j] = 0;
//
//					P_my_color[i][j] = matrix[i][j];
//				}
//				segmentStack = new Stack<StackElement>();
//			}
//
//		}
//	}
//
//	public int[][] labelMyColorComponents(double percent){
//		if (L!=null){
//			int label = 0;
//
//			for (int y=0; y<N; y++){
//				for (int x=0; x<M; x++){
//
//					if (P[y][x] != -1) {
//						++label;
//						fill_my_color(x, y, label, percent);
//						System.err.println("Fill_my_color iteration label:\t"+label);
//					}
//				}
//			}
//			maxLabel = label;
//		}
//		return L;
//	}
//
//	private void fill_my_color (int x, int y, int label, double percent)
//	{
//		if (P != null)
//		{
//
//			int seed = P[y][x];
//
//			System.err.print("("+x+","+y+")\t");
//
//			myColor seed_my_color = P_my_color[y][x];
//
//			push(y, x, x, 1);
//			push(y + 1, x, x, -1);
//
//
//			while (!segmentStack.isEmpty())
//			{
//				int x1,x2,dy,start;
//				StackElement item = pop();
//				y=item.y+item.dy;
//				x1 = item.xl;
//				x2 = item.xr;
//				dy = item.dy;
//				x = x1;
//				//while (x >= 0 && P[y][x] == seed)
//				while (x >= 0 && isSameMyColor(P_my_color[y][x], seed_my_color, percent))
//				{
//					L[y][x] = label;
//					P[y][x] = -1;
//
//					System.err.println("("+x+","+y+") is labelled as "+L[y][x]);
//
//					P_my_color[y][x] = seed_my_color;
//
//					x--;
//				}
//
//				if (x < x1)
//				{
//					start = x + 1;
//					if (start < x1)
//					{
//						push(y, start, x1-1, -dy);
//					}
//					x = x1 + 1 ;
//					do
//					{
//						//while (x < M && P[y][x] == seed)
//						while (x < M && isSameMyColor(P_my_color[y][x], seed_my_color, percent))
//						{
//							L[y][x] = label;
//							P[y][x] = -1;
//
//							System.err.println("("+x+","+y+") is labelled as "+L[y][x]);
//
//							P_my_color[y][x] = seed_my_color;
//
//							x++;
//						}
//						push(y,start,x-1,dy);
//
//						if (x > x2+1) {
//							push(y,x2+1,x-1,-dy);
//						}
//
//						x++;
//						//while (x <= x2 && P[y][x] != seed)
//						while (x <= x2 && !isSameMyColor(P_my_color[y][x], seed_my_color, percent))
//							x++;
//						start = x;
//					}
//					while (x <= x2);
//				}
//				else
//				{
//					if(P[y][x]==-1)
//						System.err.println("("+x+","+y+") is previously labelled as "+L[y][x]);
//
//					x++;
//
//					//while (x <= x2 && P[y][x] != seed)
//					while (x <= x2 && !isSameMyColor(P_my_color[y][x], seed_my_color, percent))
//						x++;
//					start = x;
//					while (x <= x2)
//					{
//						//while (x < M && P[y][x] == seed)
//						while (x < M && isSameMyColor(P_my_color[y][x], seed_my_color, percent))
//						{
//							L[y][x] = label;
//							P[y][x] = -1;
//
//							System.err.println("("+x+","+y+")\t is -1\t\tLabel: "+label);
//
//							P_my_color[y][x] = seed_my_color;
//
//							x++;
//						}
//						push(y,start,x-1,dy);
//
//						if (x > x2+1) {
//							push(y,x2+1,x-1,-dy);
//						}
//
//						x++;
//						//while (x <= x2 && P[y][x] != seed)
//						while (x <= x2 && !isSameMyColor(P_my_color[y][x], seed_my_color, percent))
//							x++;
//						start = x;
//					}
//				}
//			}
//		}
//	}
//
//	private boolean isSameMyColor (myColor rgb1, myColor rgb2, double threshold){
//		double red_distance, green_distance, blue_distance, distance;
//
//		red_distance = Math.pow(rgb1.red - rgb2.red, 2);
//		green_distance = Math.pow(rgb1.green - rgb2.green, 2);
//		blue_distance = Math.pow(rgb1.blue - rgb2.blue, 2);
//
//		distance = Math.sqrt(red_distance + green_distance + blue_distance);
//
//		double percent = 100.0 * distance/max_distance;
//		return (percent<=threshold?true:false);
//	}

	private boolean isSamePixelColour (int rgb1, int rgb2, double threshold)
	{
		int r1=(rgb1>>16)&0xff;
		int g1=(rgb1>>8)&0xff;
		int b1= rgb1&0xff;
		
		int r2=(rgb2>>16)&0xff;
		int g2=(rgb2>>8)&0xff;
		int b2= rgb2&0xff;

//		HSI hsi1 = new hsi1(rgb1);
//		HSI hsi2 = new hsi2(rgb2);
//
//		return (isHueMatch(hsi1, hsi2));

		int seperation = 3 * (r2-r1)*(r2-r1) + 4 * (g2-g1)*(g2-g1) + 3 * (b2-b1)*(b2-b1);
		double distance = Math.sqrt(seperation);

		double percent = 100.0 * distance/(Math.sqrt(3 * (255 * 255) + 4 * (255 * 255) +3 * (255* 255)));
		return (percent <= threshold);
	}

	void initialize(int[][] matrix, double percent)
	{		
		N = matrix.length;
		M = matrix[0].length;
		threshold = percent;
		
		if (M > 0 && N > 0)
		{
			L = new int[N][M];
			P = new int[N][M];		
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M;j++)
				{
					int rgb = matrix[i][j];
					//int r=(rgb>>16)&0xff;
					//int g=(rgb>>8)&0xff;
					//int b= rgb&0xff;
					//P[i][j]=256*256*r+256*g+b;
					P[i][j] = rgb;
					L[i][j] = 0;
				}
			segmentStack = new Stack<StackElement>();
		}
	}

	private void push(int y, int xl, int xr, int dy)
	{
		if ((y + dy >=0 ) && (y + dy < N))
		{
			StackElement item = new StackElement();
			item.y = y;
			item.xl = xl;
			item.xr = xr;
			item.dy = dy;
			segmentStack.push(item);
		}		
	}

	private StackElement pop()
	{
		StackElement item = null;
		if (!segmentStack.isEmpty())
		{
			item = segmentStack.pop();						
		}
		return item;
	}

	private void fill (int x, int y, int label, double percent)
	{
		if (P != null)
		{

			int seed = P[y][x];

			push(y, x, x, 1);
			push(y + 1, x, x, -1);
			while (!segmentStack.isEmpty())
			{				
				int x1,x2,dy,start;
				StackElement item = pop();
				y=item.y+item.dy;
				x1 = item.xl;
				x2 = item.xr;
				dy = item.dy;				
				x = x1;				
				//while (x >= 0 && P[y][x] == seed)
				while (x >= 0 && isSamePixelColour(P[y][x], seed, percent))
				{
					L[y][x] = label;
					P[y][x] = -1;
					x--;		
				}

				if (x < x1)
				{
					start = x + 1;
					if (start < x1)
					{
						push(y, start, x1-1, -dy);
					}
					x = x1 + 1 ;
					do 
					{
						//while (x < M && P[y][x] == seed)
						while (x < M && isSamePixelColour(P[y][x], seed, percent))
						{
							L[y][x] = label;
							P[y][x] = -1;
							x++;
						}
						push(y,start,x-1,dy);

						if (x > x2+1) {
							push(y,x2+1,x-1,-dy);
						}

						x++;
						//while (x <= x2 && P[y][x] != seed)
						while (x <= x2 && !isSamePixelColour(P[y][x], seed, percent))
							x++;
						start = x;
					}
					while (x <= x2);	
				}
				else
				{
					x++;
					//while (x <= x2 && P[y][x] != seed)
					while (x <= x2 && !isSamePixelColour(P[y][x], seed, percent))
						x++;
					start = x;
					while (x <= x2)
					{
						//while (x < M && P[y][x] == seed)
						while (x < M && isSamePixelColour(P[y][x], seed, percent))
						{
							L[y][x] = label;
							P[y][x] = -1;
							x++;
						}
						push(y,start,x-1,dy);

						if (x > x2+1) {
							push(y,x2+1,x-1,-dy);
						}

						x++;
						//while (x <= x2 && P[y][x] != seed)
						while (x <= x2 && !isSamePixelColour(P[y][x], seed, percent))
							x++;
						start = x;	
					}					
				}										
			}
		}
	}

	int[][] labelColouredComponents(double percent)
	{				
		if (L != null)
		{
			int label = 0;	
			for (int y = 0;y < N; y++)
			{
				for (int x = 0; x < M; x++)
				{
					if (P[y][x] != -1) {
						label++;
						fill(x, y, label, percent);
					}
				}
			}
			maxLabel = label;
		}		
		return L;
	}
    
}

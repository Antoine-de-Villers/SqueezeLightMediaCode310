package gti310.tp4;

public class Conversion {

	public static int[][][] convertRGBToYCbCr(int[][][] image)
	{
		
		int[][][] imageYCbCr = new int[Main.COLOR_SPACE_SIZE][image[0].length][image[0][0].length];
		
		for(int i = 0; i < image[0].length; i++)
		{
			for(int j = 0; j < image[0][0].length;j++)
			{
		
			int Y = (int)(0.299*image[Main.R][i][j]+0.587*image[Main.G][i][j]+0.114*image[Main.B][i][j]);
	        int Cb=(int)(128-0.169*image[Main.R][i][j]-0.331*image[Main.G][i][j]+0.500*image[Main.B][i][j]);
	        int Cr =(int)(128+0.500*image[Main.R][i][j]-0.419*image[Main.G][i][j]-0.081*image[Main.B][i][j]);
		
	        imageYCbCr[Main.Y][i][j]=Y;
			imageYCbCr[Main.Cb][i][j]=Cb;
			imageYCbCr[Main.Cr][i][j]=Cr;
	        
			}
			
		}
		return imageYCbCr;
	}
	
	public static int[][][] convertYUVToRGB(int[][][] image)
	{
		
		int[][][] imageRGB = new int[Main.COLOR_SPACE_SIZE][image[0].length][image[0][0].length];
		
		for(int i = 0; i < image[0].length; i++)
		{
			for(int j = 0; j < image[0][0].length;j++)
			{		
				 int R = (int) (image[Main.Y][i][j] + 1.40200 * (image[Main.Cr][i][j] - 0x80));
				 int G = (int) (image[Main.Y][i][j] - 0.34414 * (image[Main.Cb][i][j] - 0x80) - 0.71414 * (image[Main.Cr][i][j] - 0x80));
				 int B = (int) (image[Main.Y][i][j] + 1.77200 * (image[Main.Cb][i][j] - 0x80));
		
				R = (R<0 ? 0 :( R > 255 ? 255 : R) );
				G = (G<0 ? 0 :( G > 255 ? 255 : G) );
				B = (B<0 ? 0 :( B > 255 ? 255 : B) );				
		
				imageRGB[Main.R][i][j]=R;
				imageRGB[Main.G][i][j]=G;
				imageRGB[Main.B][i][j]=B;				
			}			
		}	

		return imageRGB;
	}
	
	
}

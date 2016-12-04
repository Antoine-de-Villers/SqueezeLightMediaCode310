package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class Quantification {
	private static int[][] Y = new int[8][8];
	private static int[][] CbCr = new int[8][8];
	
	
	/**n
	 * C1+C2+C3+C4+C5+C6(N-1)+C7N+C8(N-1)N+C9(N-1)N*N+C10N*N*N+C11N*N*N+C12N*N*N
	 * C13N+C14
	 *  
	 *  K+N+N+N^2+N^3+N^3+N^3+N^3+N+C14
	 *  
	 * O(N^3)
	 * 
	 * @param Liste de blocs
	 * @param degrée de qualité
	 */	
	public static List<int[][][]> Do(List<int[][][]> blocs, int fq)
	{		
		if (fq==100)
			return blocs;
		
		List<int[][][]> blocsQuant = new LinkedList<int[][][]>();
		
		InitMatrice();
		double a = (fq<=50 ? (double)(50/fq) : (double)(200-2*fq)/100);
		
		for (int[][][] bloc : blocs)
		{
			int[][][] blocQuant = new int[Main.COLOR_SPACE_SIZE][8][8];
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					blocQuant[Main.Y][i][j]=(int) Math.round(bloc[Main.Y][i][j]/(a*Y[i][j]));
					blocQuant[Main.Cb][i][j]=(int) Math.round(bloc[Main.Cb][i][j]/(a*CbCr[i][j]));
					blocQuant[Main.Cr][i][j]=(int) Math.round(bloc[Main.Cr][i][j]/(a*CbCr[i][j]));
				}
			
			blocsQuant.add(blocQuant);
		}		

		return blocsQuant;
	}
		
	/**n
	 * C1+C2+C3+C4+C5+C6(N-1)+C7N+C8(N-1)N+C9(N-1)N*N+C10N*N*N+C11N*N*N+C12N*N*N
	 * C13N+C14
	 *  
	 *  K+N+N+N^2+N^3+N^3+N^3+N^3+N+C14
	 *  
	 * O(N^3)
	 * 
	 * @param Liste de blocs
	 * @param degrée de qualité
	 */	
	public static List<int[][][]> UnDo(List<int[][][]> blocs, int fq)
	{
		if (fq==100)
			return blocs;
		
		List<int[][][]> blocsUnQuant = new LinkedList<int[][][]>();
		
		InitMatrice();
		double a = (fq<=50 ? (double)(50/fq) : (double)(200-2*fq)/100);
		
		for (int[][][] bloc : blocs)//6
		{
			int[][][] blocUnQuant = new int[Main.COLOR_SPACE_SIZE][8][8];
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					blocUnQuant[Main.Y][i][j]=(int) Math.round(bloc[Main.Y][i][j]*(a*Y[i][j]));
					blocUnQuant[Main.Cb][i][j]=(int) Math.round(bloc[Main.Cb][i][j]*(a*CbCr[i][j]));
					blocUnQuant[Main.Cr][i][j]=(int) Math.round(bloc[Main.Cr][i][j]*(a*CbCr[i][j]));
				}
			
			blocsUnQuant.add(blocUnQuant);
		}

		
		return blocsUnQuant;
	}
	
	/**n  
	 *  C1+...+C128 = K = 1
	 *  
	 * O(1)
	 *	
	 */	
	private static void InitMatrice()
	{
		Y[0][0]=16;
		Y[0][1]=11;
		Y[0][2]=10;
		Y[0][3]=16;
		Y[0][4]=24;
		Y[0][5]=40;
		Y[0][6]=51;
		Y[0][7]=61;

		Y[1][0]=12;
		Y[1][1]=12;
		Y[1][2]=14;
		Y[1][3]=19;
		Y[1][4]=26;
		Y[1][5]=58;
		Y[1][6]=60;
		Y[1][7]=55;

		Y[2][0]=14;
		Y[2][1]=13;
		Y[2][2]=16;
		Y[2][3]=24;
		Y[2][4]=40;
		Y[2][5]=57;
		Y[2][6]=69;
		Y[2][7]=56;

		Y[3][0]=14;
		Y[3][1]=17;
		Y[3][2]=22;
		Y[3][3]=29;
		Y[3][4]=51;
		Y[3][5]=87;
		Y[3][6]=80;
		Y[3][7]=62;

		Y[4][0]=18;
		Y[4][1]=22;
		Y[4][2]=37;
		Y[4][3]=56;
		Y[4][4]=68;
		Y[4][5]=109;
		Y[4][6]=103;
		Y[4][7]=77;

		Y[5][0]=24;
		Y[5][1]=35;
		Y[5][2]=55;
		Y[5][3]=64;
		Y[5][4]=81;
		Y[5][5]=104;
		Y[5][6]=113;
		Y[5][7]=92;

		Y[6][0]=49;
		Y[6][1]=64;
		Y[6][2]=78;
		Y[6][3]=87;
		Y[6][4]=103;
		Y[6][5]=121;
		Y[6][6]=120;
		Y[6][7]=101;

		Y[7][0]=72;
		Y[7][1]=92;
		Y[7][2]=95;
		Y[7][3]=98;
		Y[7][4]=112;
		Y[7][5]=100;
		Y[7][6]=103;
		Y[7][7]=99;
		
		CbCr[0][0]=17;
		CbCr[0][1]=18;
		CbCr[0][2]=24;
		CbCr[0][3]=47;
		CbCr[0][4]=99;
		CbCr[0][5]=99;
		CbCr[0][6]=99;
		CbCr[0][7]=99;
		
		CbCr[1][0]=18;
		CbCr[1][1]=21;
		CbCr[1][2]=26;
		CbCr[1][3]=66;
		CbCr[1][4]=99;
		CbCr[1][5]=99;
		CbCr[1][6]=99;
		CbCr[1][7]=99;

		CbCr[2][0]=24;
		CbCr[2][1]=26;
		CbCr[2][2]=56;
		CbCr[2][3]=99;
		CbCr[2][4]=99;
		CbCr[2][5]=99;
		CbCr[2][6]=99;
		CbCr[2][7]=99;

		CbCr[3][0]=47;
		CbCr[3][1]=66;
		CbCr[3][2]=99;
		CbCr[3][3]=99;
		CbCr[3][4]=99;
		CbCr[3][5]=99;
		CbCr[3][6]=99;
		CbCr[3][7]=99;

		CbCr[4][0]=99;
		CbCr[4][1]=99;
		CbCr[4][2]=99;
		CbCr[4][3]=99;
		CbCr[4][4]=99;
		CbCr[4][5]=99;
		CbCr[4][6]=99;
		CbCr[4][7]=99;

		CbCr[5][0]=99;
		CbCr[5][1]=99;
		CbCr[5][2]=99;
		CbCr[5][3]=99;
		CbCr[5][4]=99;
		CbCr[5][5]=99;
		CbCr[5][6]=99;
		CbCr[5][7]=99;

		CbCr[6][0]=99;
		CbCr[6][1]=99;
		CbCr[6][2]=99;
		CbCr[6][3]=99;
		CbCr[6][4]=99;
		CbCr[6][5]=99;
		CbCr[6][6]=99;
		CbCr[6][7]=99;

		CbCr[7][0]=99;
		CbCr[7][1]=99;
		CbCr[7][2]=99;
		CbCr[7][3]=99;
		CbCr[7][4]=99;
		CbCr[7][5]=99;
		CbCr[7][6]=99;
		CbCr[7][7]=99;
	}

}

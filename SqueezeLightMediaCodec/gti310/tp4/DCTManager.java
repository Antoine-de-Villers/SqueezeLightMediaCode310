package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class DCTManager {


	/**
	 *  
	 * O(N^5)
	 */		
	public static List<int[][][]> DCT(List<int[][][]> blocs)
	{
		
		double[][] COS = new double[8][8];
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				COS[i][j]=Math.cos(((2*i+1)*j*Math.PI/16));
		
		List<int[][][]> Fs = new LinkedList<int[][][]>();
		double Fy = 0;
		int[] Fy1 = new int[8];
		int[][] Fy2 = new int[8][];
		double FCb = 0;
		int[] FCb1 = new int[8];
		int[][] FCb2 = new int[8][];
		double FCr = 0;
		int[] FCr1 = new int[8];
		int[][] FCr2 = new int[8][];
		
		for (int[][][] bloc : blocs)
		{
			Fy2 = new int[8][];
			FCb2 = new int[8][];
			FCr2 = new int[8][];
			for (int u=0;u<8;u++)
			{
				Fy1=new int[8];
				FCb1=new int[8];
				FCr1=new int[8];
				for (int v=0;v<8;v++)
				{
					Fy = 0;
					FCb = 0;
					FCr = 0;
					for (int i=0;i<8;i++)
						for (int j=0;j<8;j++)
						{
							Fy+=COS[i][u]*COS[j][v]*bloc[Main.Y][i][j];
							FCb+=COS[i][u]*COS[j][v]*bloc[Main.Cb][i][j];
							FCr+=COS[i][u]*COS[j][v]*bloc[Main.Cr][i][j];
						}
					Fy*=(C(u)*C(v)/4);
					FCb*=(C(u)*C(v)/4);
					FCr*=(C(u)*C(v)/4);
					Fy1[v]=(int)Math.round(Fy);
					FCb1[v]=(int)Math.round(FCb);
					FCr1[v]=(int)Math.round(FCr);
				}
				Fy2[u]=Fy1;
				FCb2[u]=FCb1;
				FCr2[u]=FCr1;
			}
			Fs.add(new int[][][]{Fy2,FCb2,FCr2});
		}
		
		return Fs;
	}

	/** 
	 * O(N^5)
	 * 
	 * @param
	 */		
	
	public static List<int[][][]> iDCT(List<int[][][]> blocs)
	{
		double[][] COS = new double[8][8];
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				COS[i][j]=Math.cos(((2*i+1)*j*Math.PI/16));
		
		List<int[][][]> Fs = new LinkedList<int[][][]>();
		double Fy = 0;
		int[] Fy1 = new int[8];
		int[][] Fy2 = new int[8][];
		double FCb = 0;
		int[] FCb1 = new int[8];
		int[][] FCb2 = new int[8][];
		double FCr = 0;
		int[] FCr1 = new int[8];
		int[][] FCr2 = new int[8][];
		
		for (int[][][] bloc : blocs)
		{
			Fy2 = new int[8][];
			FCb2 = new int[8][];
			FCr2 = new int[8][];
			for (int u=0;u<8;u++)
			{
				Fy1=new int[8];
				FCb1=new int[8];
				FCr1=new int[8];
				for (int v=0;v<8;v++)
				{
					Fy = 0;
					FCb = 0;
					FCr = 0;
					for (int i=0;i<8;i++)
						for (int j=0;j<8;j++)
						{
							Fy+=(C(i)*C(j)/4)*(COS[u][i]*COS[v][j]*bloc[Main.Y][i][j]);
							FCb+=(C(i)*C(j)/4)*(COS[u][i]*COS[v][j]*bloc[Main.Cb][i][j]);
							FCr+=(C(i)*C(j)/4)*(COS[u][i]*COS[v][j]*bloc[Main.Cr][i][j]);
						}
					Fy1[v]=(int)Math.round(Fy);
					FCb1[v]=(int)Math.round(FCb);
					FCr1[v]=(int)Math.round(FCr);
				}
				Fy2[u]=Fy1;
				FCb2[u]=FCb1;
				FCr2[u]=FCr1;
			}
			Fs.add(new int[][][]{Fy2,FCb2,FCr2});
		}
		
		return Fs;
	}
	
	/*
	 * O(1)
	 */
	private static double C(int w)
	{
		return (w==0 ? 1/Math.sqrt(2) : 1);
	}
}


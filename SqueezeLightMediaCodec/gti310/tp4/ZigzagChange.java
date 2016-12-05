package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class ZigzagChange {

	/**
	 * 
	 * O(N^3)
	 * 
	 * @param Liste
	 *            de blocs
	 */
	public static int[][][] GetAC(List<int[][][]> blocs) {
		List<int[]> Y = new LinkedList<int[]>();
		List<int[]> Cb = new LinkedList<int[]>();
		List<int[]> Cr = new LinkedList<int[]>();

		for (int b = 0; b < blocs.size(); b++) {
			int Y0 = 0;
			int Cb0 = 0;
			int Cr0 = 0;
			int[][] AC = new int[Main.COLOR_SPACE_SIZE][63];

			int[] ZigZagOrder = { 0, 1, 5, 6, 14, 15, 27, 28, 2, 4, 7, 13, 16, 26, 29, 42, 3, 8, 12, 17, 25, 30, 41, 43,
					9, 11, 18, 24, 31, 40, 44, 53, 10, 19, 23, 32, 39, 45, 52, 54, 20, 22, 33, 38, 46, 51, 55, 60, 21,
					34, 37, 47, 50, 56, 59, 61, 35, 36, 48, 49, 57, 58, 62, 63 };

			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (!(i == 0 && j == 0)) {
						AC[Main.Y][ZigZagOrder[i * 8 + j] - 1] = blocs.get(b)[Main.Y][i][j];
						AC[Main.Cb][ZigZagOrder[i * 8 + j] - 1] = blocs.get(b)[Main.Cb][i][j];
						AC[Main.Cr][ZigZagOrder[i * 8 + j] - 1] = blocs.get(b)[Main.Cr][i][j];
					}

			for (int val : AC[Main.Y]) {
				if (val == 0)
					Y0++;
				else {
					int[] tab = { Y0, val };
					Y.add(tab);
					Y0 = 0;
				}
			}
			{
				int[] tab = { 0, 0 };
				Y.add(tab);
			}

			for (int val : AC[Main.Cb]) {
				if (val == 0)
					Cb0++;
				else {
					int[] tab = { Cb0, val };
					Cb.add(tab);
					Cb0 = 0;
				}
			}
			{
				int[] tab = { 0, 0 };
				Cr.add(tab);
			}

			for (int val : AC[Main.Cr]) {
				if (val == 0)
					Cr0++;
				else {
					int[] tab = { Cr0, val };
					Cr.add(tab);
					Cr0 = 0;
				}
			}
			{
				int[] tab = { 0, 0 };
				Cr.add(tab);
			}
		}

		int biggestSize=0;
		if(Y.size()>Cb.size()&&Y.size()>Cr.size()){
			biggestSize=Y.size();
		}
		else if(Cb.size()>Y.size()&&Cb.size()>Cr.size()){
			biggestSize=Cb.size();
		}
		else if(Cr.size()>Cb.size()&&Cr.size()>Y.size()){
			biggestSize=Cr.size();
		}
		int[][][] ACsToWrite = new int[Main.COLOR_SPACE_SIZE][biggestSize][2];
		for (int i = 0; i < Y.size(); i++)
			ACsToWrite[0][i] = Y.get(i);

		for (int i = 0; i < Cb.size(); i++) {
			ACsToWrite[1][i] = Cb.get(i);
		}
		for (int i = 0; i < Cr.size(); i++) {
			ACsToWrite[2][i] = 	Cr.get(i);
		}

		return ACsToWrite;
	}

	/**
	 * 
	 * O(N)
	 * 
	 * @param Liste
	 *            de blocs
	 */
	public static int[][] GetDC(List<int[][][]> blocs) {
		int[][] DC = new int[Main.COLOR_SPACE_SIZE][blocs.size()];
		int k = 0;

		for (int[][][] bloc : blocs) {
			DC[Main.Y][k] = bloc[Main.Y][0][0];
			DC[Main.Cb][k] = bloc[Main.Cb][0][0];
			DC[Main.Cr][k] = bloc[Main.Cr][0][0];
			k++;
		}

		for (int i = (DC[Main.Y].length - 1); i >= 1; i--) {
			DC[Main.Y][i] = DC[Main.Y][i] - DC[Main.Y][i - 1];
			DC[Main.Cb][i] = DC[Main.Cb][i] - DC[Main.Cb][i - 1];
			DC[Main.Cr][i] = DC[Main.Cr][i] - DC[Main.Cr][i - 1];
		}

		return DC;
	}

	/**
	 * 
	 * O(N^2)
	 * 
	 * @param Liste
	 *            de DCs
	 * @param Liste
	 *            de Yc
	 * @param Liste
	 *            de Uc
	 * @param Liste
	 *            de Vc
	 * @param La
	 *            largeur
	 * @param La
	 *            hauteur
	 */
	public static List<int[][][]> CreateBlocs(int[][] DCs, List<int[]> Yc, List<int[]> Cbc, List<int[]> Crc, int width,
			int height) {
		List<int[][][]> blocs = new LinkedList<int[][][]>();

		// DCs
		for (int i = 1; i < DCs[Main.Y].length; i++) {
			DCs[Main.Y][i] = DCs[Main.Y][i] + DCs[Main.Y][i - 1];
			DCs[Main.Cb][i] = DCs[Main.Cb][i] + DCs[Main.Cb][i - 1];
			DCs[Main.Cr][i] = DCs[Main.Cr][i] + DCs[Main.Cr][i - 1];
		}

		for (int i = 0; i < DCs[Main.Y].length; i++) {
			int[][][] bloc = new int[Main.COLOR_SPACE_SIZE][8][8];
			bloc[Main.Y][0][0] = DCs[Main.Y][i];
			bloc[Main.Cb][0][0] = DCs[Main.Cb][i];
			bloc[Main.Cr][0][0] = DCs[Main.Cr][i];
			blocs.add(bloc);
		}

		// ACs
		List<Integer> Y = new LinkedList<Integer>();
		List<Integer> Cb = new LinkedList<Integer>();
		List<Integer> Cr = new LinkedList<Integer>();

		int ibloc = 0;
		for (int i = 0; i < Yc.size(); i++) {
			if (Yc.get(i)[1] == 0) {
				while (ibloc < 63) {
					Y.add(0);
					ibloc++;
				}
				ibloc = 0;
			} else {
				for (int j = 0; j < Yc.get(i)[0]; j++) {
					Y.add(0);
					ibloc++;
				}

				Y.add(Yc.get(i)[1]);
				ibloc++;
			}
		}

		ibloc = 0;
		for (int i = 0; i < Cbc.size(); i++) {
			if (Cbc.get(i)[1] == 0) {
				while (ibloc < 63) {
					Cb.add(0);
					ibloc++;
				}
				ibloc = 0;
			} else {
				for (int j = 0; j < Cbc.get(i)[0]; j++) {
					Cb.add(0);
					ibloc++;
				}

				Cb.add(Cbc.get(i)[1]);
				ibloc++;
			}
		}

		ibloc = 0;
		for (int i = 0; i < Crc.size(); i++) {
			if (Crc.get(i)[1] == 0) {
				while (ibloc < 63) {
					Cr.add(0);
					ibloc++;
				}
				ibloc = 0;
			} else {
				for (int j = 0; j < Crc.get(i)[0]; j++) {
					Cr.add(0);
					ibloc++;
				}

				Cr.add(Crc.get(i)[1]);
				ibloc++;
			}
		}

		int[][] listACs = new int[Main.COLOR_SPACE_SIZE][(width * height * 63 / 64)];
		int[][] listACs2 = new int[Main.COLOR_SPACE_SIZE][63];

		for (int i = 0; i < (width * height * 63 / 64); i++) {
			if (i >= Y.size())
				listACs[0][i] = 0;
			else
				listACs[0][i] = Y.get(i);

			if (i >= Cb.size())
				listACs[1][i] = 0;
			else
				listACs[1][i] = Cb.get(i);

			if (i >= Cr.size())
				listACs[2][i] = 0;
			else
				listACs[2][i] = Cr.get(i);
		}

		for (int b = 0; b < blocs.size(); b++) {
			for (int i = 0; i < 63; i++) {
				listACs2[0][findZigZagOrder(i + 1) - 1] = listACs[0][i + b * 63];
				listACs2[1][findZigZagOrder(i + 1) - 1] = listACs[1][i + b * 63];
				listACs2[2][findZigZagOrder(i + 1) - 1] = listACs[2][i + b * 63];
			}

			for (int i = 1; i < 64; i++) {
				blocs.get(b)[0][i / 8][i % 8] = listACs2[0][i - 1];
				blocs.get(b)[1][i / 8][i % 8] = listACs2[1][i - 1];
				blocs.get(b)[2][i / 8][i % 8] = listACs2[2][i - 1];
			}
		}

		return blocs;
	}

	/**
	 * 
	 * O(N)
	 * 
	 * @param Indexe
	 */
	private static int findZigZagOrder(int i) {
		int[] ZigZagOrder = { 0, 1, 5, 6, 14, 15, 27, 28, 2, 4, 7, 13, 16, 26, 29, 42, 3, 8, 12, 17, 25, 30, 41, 43, 9,
				11, 18, 24, 31, 40, 44, 53, 10, 19, 23, 32, 39, 45, 52, 54, 20, 22, 33, 38, 46, 51, 55, 60, 21, 34, 37,
				47, 50, 56, 59, 61, 35, 36, 48, 49, 57, 58, 62, 63 };

		for (int j = 0; j < ZigZagOrder.length; j++)
			if (ZigZagOrder[j] == i)
				return j;

		return 0;
	}

}

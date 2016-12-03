package gti310.tp4;

import java.util.Scanner;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author Fran�ois Caron
 */
public class Main {

	/*
	 * The entire application assumes that the blocks are 8x8 squares.
	 */
	public static final int BLOCK_SIZE = 8;
	
	/*
	 * The number of dimensions in the color spaces.
	 */
	public static final int COLOR_SPACE_SIZE = 3;
	
	/*
	 * The RGB color space.
	 */
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	/*
	 * The YUV color space.
	 */
	public static final int Y = 0;
	public static final int U = 1;
	public static final int V = 2;
	
	public static final Scanner sc= new Scanner(System.in);
	
	/**
	 * The application's entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean valid=false;
		while(!valid){
		System.out.println("Squeeze Light Media Codec !\nEntrez C pour encoder l'image en format "
				+ "Squeeze Light,\nou D pour d�coder l'image Squeeze Light");
		String reponse = sc.nextLine();
		if (reponse.toLowerCase().equals("c")){
			encode();
			valid=true;
		}
		else if (reponse.toLowerCase().equals("d")){
			decode();
			valid=true;
		}
		else {
			System.out.println("Vous n'avez pas rentrer une r�ponse valide, veuillez r�essayez.");
		}
		}
		
	}
	public static void encode(){
		new FileViewer(true);
	}
	public static void decode(){
		new FileViewer(false);
	}
}

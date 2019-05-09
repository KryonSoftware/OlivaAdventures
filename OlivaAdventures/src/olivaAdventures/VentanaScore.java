package olivaAdventures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class VentanaScore implements KeyListener {

	/* --- VARIABLES --- */
	public static final int frameX=1000;											// Medida horizontal en pixeles para el JFrame.
	public static final int frameY=1000;											// Medida vertical en pixeles para el JFrame.
	public static final int JLabelTotal = 400;										// Numero de JLabels que habra en el JFrame.
	public static final int labelX=50;												// Medida horizontal en pixeles para los JLabel.
	public static final int labelY=50;												// Medida vertical en pixeles para los JLabel.

	public volatile boolean scoresOn=true;

	private String path = "/Menu/puntuaciones.txt";
	public JFrame frame;	
	private Dimension Dim = new Dimension(frameX, frameY);

	private int x = 0;																// Coordenada X para situar el JLabel.
	private int y = 0;																// Coordenada Y para situar el JLabel.
	private int n = JLabelTotal + 1;												// Numero total de JLabels que habra, se suma +1 porque asignamos al primer JLabel "1" para facilitar las cuentas.
	private JLabel[] lista = new JLabel[n];											// Array que guardara los distintos JLabels.
	private String[] puntuacion = new String[5];									// Array que guardara las puntuaciones.
	private Musica exit=new Musica();
	
	
	private enum Posicion {															// Asignacion de un nombre para cada JLabel con letra, numero o guion.

		N1_1, N1_2, N1_3,
		N2_1, N2_2, N2_3,
		N3_1, N3_2, N3_3,
		N4_1, N4_2, N4_3,
		N5_1, N5_2, N5_3,
		S1_1, S1_2, S1_3, S1_4, S1_5, S1_6,
		S2_1, S2_2, S2_3, S2_4, S2_5, S2_6,
		S3_1, S3_2, S3_3, S3_4, S3_5, S3_6,
		S4_1, S4_2, S4_3, S4_4, S4_5, S4_6,
		S5_1, S5_2, S5_3, S5_4, S5_5, S5_6,
		guion;
	}

	/* --- CONSTRUCTOR --- */
	public VentanaScore(JFrame frame) {

		exit.cargarExit();
		this.frame=frame;
		lecturaFichero();					// Leemos y guardamos las puntuaciones.
		frameConfiguracion();				// Aplicamos el JFrame.

		for (int i=1 ; i<n ; i++) {			// Hacemos un bucle con una duracion igual al numero de JLabels que tienen que crearse.
			seleccionarLabel(i);			// Decidimos que contendra el label en cuestion (fondo transparente o bien un caracter).
			actualizarCoordenadas();		// Actualizamos las coordenadas "x" e "y" para colocar el siguiente JLabel.
		}
		imagenDeFondo();					// Ponemos al fondo un label = a las dimensiones del frame para la imagen de fondo.

	}

	/* --- METODOS PRINCIPALES --- */
	private void lecturaFichero() {

		try {
//			.getBytes("UTF-8")
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path),"UTF-8"));
			String linea;

			linea = br.readLine();
			for (int i=0 ; i<5 ; i++) {
				puntuacion[i] = linea;
				linea = br.readLine();
			}
			br.close();
		}

		catch(IOException e) {
			System.out.println("No se puede abrir el fichero");
		}

	}

	private void frameConfiguracion() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameX, frameY);
		frame.setLocation(100,100);
		frame.setLayout(null);

		/*
		 * Les linies que venen a continuacio me permeten dimensionar les mides del frame
		 * a les mides que realment vull. I.e. eliminant la barra del titol de la finestra.
		 */

		frame.setPreferredSize(Dim);
		frame.pack();
		Dim.width  = frameX  + (frame.getWidth()  - frame.getContentPane().getWidth()); 
		Dim.height = frameY + (frame.getHeight() - frame.getContentPane().getHeight());
		frame.setResizable(false);				// Esta opcion en Windows 7 desplaza 10 pixeles hacia abajo y a la derecha el frame, por eso...
		Dim.width -= 10;						// ... le quitamos esos 10 pixeles a la anchura (X).
		Dim.height -= 10;						// ... le quitamos tambien 10 pixeles a la altura (Y).
		frame.setPreferredSize(Dim);
		frame.pack();
		frame.addKeyListener(this);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);		// Asi se abrira la ventana centrada en la pantalla.
		frame.setVisible(true);
	}

	private void seleccionarLabel(int c) {

		/* --- JLABEL DE INICIO --- */
		int punt1 = 146;
		int punt2 = 186;
		int punt3 = 226;
		int punt4 = 266;
		int punt5 = 306;

		/* --- PUNTUACION 1 --- */
		if      (c == punt1)      { labelConCaracter(c, Posicion.N1_1); }
		else if (c == punt1 + 1)  { labelConCaracter(c, Posicion.N1_2); }
		else if (c == punt1 + 2)  { labelConCaracter(c, Posicion.N1_3); }

		else if (c == punt1 + 4)  { labelConCaracter(c, Posicion.S1_1); }
		else if (c == punt1 + 5)  { labelConCaracter(c, Posicion.S1_2); }
		else if (c == punt1 + 6)  { labelConCaracter(c, Posicion.S1_3); }
		else if (c == punt1 + 7)  { labelConCaracter(c, Posicion.S1_4); }
		else if (c == punt1 + 8) { labelConCaracter(c, Posicion.S1_5); }
		else if (c == punt1 + 9) { labelConCaracter(c, Posicion.S1_6); }

		/* --- PUNTUACION 2 --- */
		else if (c == punt2)      { labelConCaracter(c, Posicion.N2_1); }
		else if (c == punt2 + 1)  { labelConCaracter(c, Posicion.N2_2); }
		else if (c == punt2 + 2)  { labelConCaracter(c, Posicion.N2_3); }

		else if (c == punt2 + 4)  { labelConCaracter(c, Posicion.S2_1); }
		else if (c == punt2 + 5)  { labelConCaracter(c, Posicion.S2_2); }
		else if (c == punt2 + 6)  { labelConCaracter(c, Posicion.S2_3); }
		else if (c == punt2 + 7)  { labelConCaracter(c, Posicion.S2_4); }
		else if (c == punt2 + 8) { labelConCaracter(c, Posicion.S2_5); }
		else if (c == punt2 + 9) { labelConCaracter(c, Posicion.S2_6); }

		/* --- PUNTUACION 3 --- */
		else if (c == punt3)      { labelConCaracter(c, Posicion.N3_1); }
		else if (c == punt3 + 1)  { labelConCaracter(c, Posicion.N3_2); }
		else if (c == punt3 + 2)  { labelConCaracter(c, Posicion.N3_3); }

		else if (c == punt3 + 4)  { labelConCaracter(c, Posicion.S3_1); }
		else if (c == punt3 + 5)  { labelConCaracter(c, Posicion.S3_2); }
		else if (c == punt3 + 6)  { labelConCaracter(c, Posicion.S3_3); }
		else if (c == punt3 + 7)  { labelConCaracter(c, Posicion.S3_4); }
		else if (c == punt3 + 8) { labelConCaracter(c, Posicion.S3_5); }
		else if (c == punt3 + 9) { labelConCaracter(c, Posicion.S3_6); }

		/* --- PUNTUACION 4 --- */
		else if (c == punt4)      { labelConCaracter(c, Posicion.N4_1); }
		else if (c == punt4 + 1)  { labelConCaracter(c, Posicion.N4_2); }
		else if (c == punt4 + 2)  { labelConCaracter(c, Posicion.N4_3); }

		else if (c == punt4 + 4)  { labelConCaracter(c, Posicion.S4_1); }
		else if (c == punt4 + 5)  { labelConCaracter(c, Posicion.S4_2); }
		else if (c == punt4 + 6)  { labelConCaracter(c, Posicion.S4_3); }
		else if (c == punt4 + 7)  { labelConCaracter(c, Posicion.S4_4); }
		else if (c == punt4 + 8)  { labelConCaracter(c, Posicion.S4_5); }
		else if (c == punt4 + 9) { labelConCaracter(c, Posicion.S4_6); }

		/* --- PUNTUACION 5 --- */
		else if (c == punt5)      { labelConCaracter(c, Posicion.N5_1); }
		else if (c == punt5 + 1)  { labelConCaracter(c, Posicion.N5_2); }
		else if (c == punt5 + 2)  { labelConCaracter(c, Posicion.N5_3); }

		else if (c == punt5 + 4)  { labelConCaracter(c, Posicion.S5_1); }
		else if (c == punt5 + 5)  { labelConCaracter(c, Posicion.S5_2); }
		else if (c == punt5 + 6)  { labelConCaracter(c, Posicion.S5_3); }
		else if (c == punt5 + 7)  { labelConCaracter(c, Posicion.S5_4); }
		else if (c == punt5 + 8) { labelConCaracter(c, Posicion.S5_5); }
		else if (c == punt5 + 9) { labelConCaracter(c, Posicion.S5_6); }

		/* --- GUIONES --- */
		else if (c == punt1 + 3) { labelConCaracter(c, Posicion.guion); }
		else if (c == punt2 + 3) { labelConCaracter(c, Posicion.guion); }
		else if (c == punt3 + 3) { labelConCaracter(c, Posicion.guion); }
		else if (c == punt4 + 3) { labelConCaracter(c, Posicion.guion); }
		else if (c == punt5 + 3) { labelConCaracter(c, Posicion.guion); }

		/* --- FONDO TRANSPARENTE --- */
		else labelVacio(c);

		frame.repaint();

	}

	private void actualizarCoordenadas() {

		if (x != (frameX - labelX)) {
			x += labelX;
		}
		else {
			x = 0;
			y += labelY;
		}

	}

	private void imagenDeFondo() {

//		Graphics2D g = new Graphics2D();
//		BufferedImage img = null;
//		try {
//		    img = ImageIO.read(new File("/Menu/fondoScores.gif"));
//		} catch (IOException e) {
//		}
//		g.drawImage(img, 0, 0, frameX, frameY, null);
		
		JLabel labelDeFondoAtras = new JLabel();
		labelDeFondoAtras.setIcon(obtenerImagenPathAbsoluto("/Menu/fondoScores.jpg"));
		labelDeFondoAtras.setBounds(0,-10,1000,1100);
		JLabel labelDeFondo = new JLabel();
		labelDeFondo = new JLabel();
		//labelDeFondo.setHorizontalAlignment(JLabel.CENTER);
		labelDeFondo.setBounds(0, 0, frameX, frameY);
		labelDeFondo.setOpaque(false);
		//labelDeFondo.setBackground(Color.cyan);
		//labelDeFondo.setForeground(Color.red);
		
		//labelDeFondo.setIcon(obtenerImagen("ajedre.gif"));
		labelDeFondo.setIcon(obtenerImagenPathAbsoluto("/Menu/Scores13.png"));
		
//		labelDeFondoAtras.setOpaque(true);
//		frame.add(labelDeFondoAtras);
		frame.add(labelDeFondo);
		frame.add(labelDeFondoAtras);
		
		frame.repaint();

	}

	/* --- METODOS SECUNDARIOS --- */
	private void labelConCaracter(int c, Posicion posicion) {

		String caracter = "";
		String pathImagen = "";

		caracter = seleccionarCaracter(posicion);
		pathImagen = seleccionarPathImagen(caracter);

		lista[c] = new JLabel();
		lista[c].setBounds(x, y, labelX, labelY);
		lista[c].setOpaque(false);
		lista[c].setHorizontalAlignment(JLabel.CENTER);
		lista[c].setIcon(obtenerImagenPathAbsoluto(pathImagen));

		Border borde1 = BorderFactory.createLineBorder(Color.blue, 1);
		//lista[c].setBorder(borde1);
		//lista[c].setBackground(Color.blue);
		//lista[c].setForeground(Color.red);

		frame.add(lista[c]);
	}

	private void labelVacio(int c) {

		lista[c] = new JLabel();
		lista[c].setBounds(x, y, labelX, labelY);
		lista[c].setOpaque(false);
		lista[c].setIcon(null);

		Border borde1 = BorderFactory.createLineBorder(Color.blue, 1);
		//lista[c].setBorder(borde1);
		//lista[c].setBackground(Color.cyan);
		//lista[c].setForeground(Color.red);		

		frame.add(lista[c]);
	}

	/* --- METODOS TERCIARIOS --- */
	private String seleccionarCaracter(Posicion posicion) {

		String caracter = "";

		/* --- PUNTUACION 1 --- */
		if (posicion.equals(posicion.N1_1)) { caracter = cogerCadena(puntuacion[0], 0, 1); }
		if (posicion.equals(posicion.N1_2)) { caracter = cogerCadena(puntuacion[0], 1, 2); }
		if (posicion.equals(posicion.N1_3)) { caracter = cogerCadena(puntuacion[0], 2, 3); }
		if (posicion.equals(posicion.S1_1)) { caracter = cogerCadena(puntuacion[0], 3, 4); }
		if (posicion.equals(posicion.S1_2)) { caracter = cogerCadena(puntuacion[0], 4, 5); }
		if (posicion.equals(posicion.S1_3)) { caracter = cogerCadena(puntuacion[0], 5, 6); }
		if (posicion.equals(posicion.S1_4)) { caracter = cogerCadena(puntuacion[0], 6, 7); }
		if (posicion.equals(posicion.S1_5)) { caracter = cogerCadena(puntuacion[0], 7, 8); }
		if (posicion.equals(posicion.S1_6)) { caracter = cogerCadena(puntuacion[0], 8, 9); }

		/* --- PUNTUACION 2 --- */
		if (posicion.equals(posicion.N2_1)) { caracter = cogerCadena(puntuacion[1], 0, 1); }
		if (posicion.equals(posicion.N2_2)) { caracter = cogerCadena(puntuacion[1], 1, 2); }
		if (posicion.equals(posicion.N2_3)) { caracter = cogerCadena(puntuacion[1], 2, 3); }
		if (posicion.equals(posicion.S2_1)) { caracter = cogerCadena(puntuacion[1], 3, 4); }
		if (posicion.equals(posicion.S2_2)) { caracter = cogerCadena(puntuacion[1], 4, 5); }
		if (posicion.equals(posicion.S2_3)) { caracter = cogerCadena(puntuacion[1], 5, 6); }
		if (posicion.equals(posicion.S2_4)) { caracter = cogerCadena(puntuacion[1], 6, 7); }
		if (posicion.equals(posicion.S2_5)) { caracter = cogerCadena(puntuacion[1], 7, 8); }
		if (posicion.equals(posicion.S2_6)) { caracter = cogerCadena(puntuacion[1], 8, 9); }

		/* --- PUNTUACION 3 --- */
		if (posicion.equals(posicion.N3_1)) { caracter = cogerCadena(puntuacion[2], 0, 1); }
		if (posicion.equals(posicion.N3_2)) { caracter = cogerCadena(puntuacion[2], 1, 2); }
		if (posicion.equals(posicion.N3_3)) { caracter = cogerCadena(puntuacion[2], 2, 3); }
		if (posicion.equals(posicion.S3_1)) { caracter = cogerCadena(puntuacion[2], 3, 4); }
		if (posicion.equals(posicion.S3_2)) { caracter = cogerCadena(puntuacion[2], 4, 5); }
		if (posicion.equals(posicion.S3_3)) { caracter = cogerCadena(puntuacion[2], 5, 6); }
		if (posicion.equals(posicion.S3_4)) { caracter = cogerCadena(puntuacion[2], 6, 7); }
		if (posicion.equals(posicion.S3_5)) { caracter = cogerCadena(puntuacion[2], 7, 8); }
		if (posicion.equals(posicion.S3_6)) { caracter = cogerCadena(puntuacion[2], 8, 9); }

		/* --- PUNTUACION 4 --- */
		if (posicion.equals(posicion.N4_1)) { caracter = cogerCadena(puntuacion[3], 0, 1); }
		if (posicion.equals(posicion.N4_2)) { caracter = cogerCadena(puntuacion[3], 1, 2); }
		if (posicion.equals(posicion.N4_3)) { caracter = cogerCadena(puntuacion[3], 2, 3); }
		if (posicion.equals(posicion.S4_1)) { caracter = cogerCadena(puntuacion[3], 3, 4); }
		if (posicion.equals(posicion.S4_2)) { caracter = cogerCadena(puntuacion[3], 4, 5); }
		if (posicion.equals(posicion.S4_3)) { caracter = cogerCadena(puntuacion[3], 5, 6); }
		if (posicion.equals(posicion.S4_4)) { caracter = cogerCadena(puntuacion[3], 6, 7); }
		if (posicion.equals(posicion.S4_5)) { caracter = cogerCadena(puntuacion[3], 7, 8); }
		if (posicion.equals(posicion.S4_6)) { caracter = cogerCadena(puntuacion[3], 8, 9); }

		/* --- PUNTUACION 5 --- */
		if (posicion.equals(posicion.N5_1)) { caracter = cogerCadena(puntuacion[4], 0, 1); }
		if (posicion.equals(posicion.N5_2)) { caracter = cogerCadena(puntuacion[4], 1, 2); }
		if (posicion.equals(posicion.N5_3)) { caracter = cogerCadena(puntuacion[4], 2, 3); }
		if (posicion.equals(posicion.S5_1)) { caracter = cogerCadena(puntuacion[4], 3, 4); }
		if (posicion.equals(posicion.S5_2)) { caracter = cogerCadena(puntuacion[4], 4, 5); }
		if (posicion.equals(posicion.S5_3)) { caracter = cogerCadena(puntuacion[4], 5, 6); }
		if (posicion.equals(posicion.S5_4)) { caracter = cogerCadena(puntuacion[4], 6, 7); }
		if (posicion.equals(posicion.S5_5)) { caracter = cogerCadena(puntuacion[4], 7, 8); }
		if (posicion.equals(posicion.S5_6)) { caracter = cogerCadena(puntuacion[4], 8, 9); }

		if (posicion.equals(posicion.guion)) { caracter = "-"; }

		return caracter;
	}

	private String seleccionarPathImagen(String caracter) {

		String pathImagen = "/Menu/NumerosyLetras/Point.png";

		/* --- LETRAS --- */
		if (caracter.equals("A")) { pathImagen = "/Menu/NumerosyLetras/A.png"; }
		if (caracter.equals("B")) { pathImagen = "/Menu/NumerosyLetras/B.png"; }
		if (caracter.equals("C")) { pathImagen = "/Menu/NumerosyLetras/C.png"; }
		if (caracter.equals("D")) { pathImagen = "/Menu/NumerosyLetras/D.png"; }
		if (caracter.equals("E")) { pathImagen = "/Menu/NumerosyLetras/E.png"; }
		if (caracter.equals("F")) { pathImagen = "/Menu/NumerosyLetras/F.png"; }
		if (caracter.equals("G")) { pathImagen = "/Menu/NumerosyLetras/G.png"; }
		if (caracter.equals("H")) { pathImagen = "/Menu/NumerosyLetras/H.png"; }
		if (caracter.equals("I")) { pathImagen = "/Menu/NumerosyLetras/1.png"; }
		if (caracter.equals("J")) { pathImagen = "/Menu/NumerosyLetras/J.png"; }
		if (caracter.equals("K")) { pathImagen = "/Menu/NumerosyLetras/K.png"; }
		if (caracter.equals("L")) { pathImagen = "/Menu/NumerosyLetras/L.png"; }
		if (caracter.equals("M")) { pathImagen = "/Menu/NumerosyLetras/M.png"; }
		if (caracter.equals("N")) { pathImagen = "/Menu/NumerosyLetras/N.png"; }
		if (caracter.equals("O")) { pathImagen = "/Menu/NumerosyLetras/O.png"; }
		if (caracter.equals("P")) { pathImagen = "/Menu/NumerosyLetras/P.png"; }
		if (caracter.equals("Q")) { pathImagen = "/Menu/NumerosyLetras/Q.png"; }
		if (caracter.equals("R")) { pathImagen = "/Menu/NumerosyLetras/R.png"; }
		if (caracter.equals("S")) { pathImagen = "/Menu/NumerosyLetras/S.png"; }
		if (caracter.equals("T")) { pathImagen = "/Menu/NumerosyLetras/T.png"; }
		if (caracter.equals("U")) { pathImagen = "/Menu/NumerosyLetras/U.png"; }
		if (caracter.equals("V")) { pathImagen = "/Menu/NumerosyLetras/V.png"; }
		if (caracter.equals("W")) { pathImagen = "/Menu/NumerosyLetras/W.png"; }
		if (caracter.equals("X")) { pathImagen = "/Menu/NumerosyLetras/X.png"; }
		if (caracter.equals("Y")) { pathImagen = "/Menu/NumerosyLetras/Y.png"; }
		if (caracter.equals("Z")) { pathImagen = "/Menu/NumerosyLetras/Z.png"; }

		/* --- NUMEROS --- */
		if (caracter.equals("0")) { pathImagen = "/Menu/NumerosyLetras/0.png"; }
		if (caracter.equals("1")) { pathImagen = "/Menu/NumerosyLetras/1.png"; }
		if (caracter.equals("2")) { pathImagen = "/Menu/NumerosyLetras/2.png"; }
		if (caracter.equals("3")) { pathImagen = "/Menu/NumerosyLetras/3.png"; }
		if (caracter.equals("4")) { pathImagen = "/Menu/NumerosyLetras/4.png"; }
		if (caracter.equals("5")) { pathImagen = "/Menu/NumerosyLetras/5.png"; }
		if (caracter.equals("6")) { pathImagen = "/Menu/NumerosyLetras/6.png"; }
		if (caracter.equals("7")) { pathImagen = "/Menu/NumerosyLetras/7.png"; }
		if (caracter.equals("8")) { pathImagen = "/Menu/NumerosyLetras/8.png"; }
		if (caracter.equals("9")) { pathImagen = "/Menu/NumerosyLetras/9.png"; }

		if (caracter.equals("-")) { pathImagen = "/Menu/NumerosyLetras/-.png"; }

		return pathImagen;
	}

	private ImageIcon obtenerImagenPathAbsoluto(String pathImagen) {

		return new ImageIcon(getClass().getResource(pathImagen));
	}

	/* --- METODOS CUATERNARIOS --- */
	private String cogerCadena(String cadena, int posicion_inicial, int posicion_final) {

		String nombreTabla = "";									// Este metodo devuelve un conjunto de caracteres concretos a partir de un String.

		for (int i=posicion_inicial ; i<posicion_final ; i++) {
			nombreTabla = (nombreTabla + cadena.charAt(i));
		}
		return nombreTabla;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE) {
			exit.exit();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scoresOn=false;
			frame.removeKeyListener(this);
			frame.setVisible(false);
			scoresOn=false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE) {
			exit.exit();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scoresOn=false;
			frame.removeKeyListener(this);
			frame.setVisible(false);
			scoresOn=false;
		}	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE) {
			exit.exit();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scoresOn=false;
			frame.removeKeyListener(this);
			frame.setVisible(false);
			scoresOn=false;
		}
	}

}

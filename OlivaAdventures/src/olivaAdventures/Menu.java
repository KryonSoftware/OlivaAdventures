package olivaAdventures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import olivaAdventures.PlantillaVentana.TipoVentana;


public class Menu implements KeyListener {

	
	/* --- VARIABLES --- */
	private JFrame ventana;
	private PlantillaVentana plantillaVentana;
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JLabel etiqueta;

	private int contadorTecla = 1;

	private String newGameImagen = "resources/media/Menú/newGame.png";
	private String scoresImagen = "resources/media/Menú/Scores.png";
	private String exitImagen = "resources/media/Menú/exit.png";
	private String puntuaciones = "resources/media/Menú/puntuaciones.txt";
	private ImageIcon newGame = new ImageIcon(newGameImagen);
	private ImageIcon scores = new ImageIcon(scoresImagen);
	private ImageIcon exit = new ImageIcon(exitImagen);


	/* --- CONSTRUCTOR --- */
	public Menu(JFrame ventana, PlantillaVentana plantillaVentana) {
		this.ventana = ventana;
		this.plantillaVentana = plantillaVentana;
        ventana.setPreferredSize(new Dimension(1000,800)); //El menu tiene puesto un 1000 x 800 porque las imagenes de Carlos estan en esta dimension, antes de tardar mas, se peude hacer este apa�o.
		
		etiqueta = new JLabel(newGame);

		label = new JLabel("Oliva Adventures");

		etiqueta.add(label);
		ventana.add(etiqueta);
		
		ventana.addKeyListener(this);
		
		ventana.pack();		// Comando para que todo el contenido se ajuste al tama�o de la ventana.


	}

	public void keyTyped(KeyEvent e) {
		/**/
	}

	public void keyReleased(KeyEvent e) {
		/**/
	}

	public void keyPressed(KeyEvent e) {

		controlDeContador(e);
		controlDeImagen(e);

	}

	private void lecturaPuntuaciones() {

		try {
			String linea;
			BufferedReader br = new BufferedReader(new FileReader(puntuaciones));

			linea = br.readLine();
			System.out.println(linea);
			for (int i = 1; i < 5; i++) {
				linea = br.readLine();
				System.out.println(linea);//??????????????????????????????????????????????????????????????
			}
			br.close();

		}
		catch (IOException e1) {
			e1.printStackTrace();//Está como muy a pelo ésto no??????????????????????????????????????????????????????????????
		}

	}

	private void controlDeContador(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {			// Si la Tecla pulsada es "flecha hacia ABAJO" >>> +1 a contadorTecla.
			contadorTecla++;
		}

		else if (e.getKeyCode() == KeyEvent.VK_UP) {		// Si la tecla pulsada es "flecha hacia ARRIBA" >>> -1 a contadorTecla.
			contadorTecla--;
		}

	}

	private void controlDeImagen(KeyEvent e) {

		if (contadorTecla == 1) {
			etiqueta.setIcon(newGame);

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				etiqueta.setIcon(null);
				ventana.removeKeyListener(this);
				plantillaVentana.cambiarTipodeVentana(TipoVentana.Nivel); // Aqui ira el comienzo del juego.
			}
		}

		else if (contadorTecla == 2) {
			etiqueta.setIcon(scores);

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				lecturaPuntuaciones();
			}

		}

		else if (contadorTecla == 3) {
			etiqueta.setIcon(exit);

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {						 // Aqui ira el codigo para quitar la ventana grafica.
				System.exit(0);
			}
		}

		/* --- CORRECTOR DEL CONTADOR --- */
		else if (contadorTecla == 0) {
			contadorTecla ++;
		}

		else if (contadorTecla == 4) {
			contadorTecla --;
		}

	}

	

}
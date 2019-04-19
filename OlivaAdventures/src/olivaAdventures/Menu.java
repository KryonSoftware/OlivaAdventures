package olivaAdventures;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import olivaAdventures.PlantillaVentana.TipoVentana;



public class Menu implements KeyListener {
	
	 public boolean nivel = false;
	
	/* --- VARIABLES --- */
	public JFrame ventana;
	private PlantillaVentana plantillaVentana;
	private JLabel label;
	public JLabel etiqueta;

	public Puntuaciones prueba;
	
	private int contadorTecla = 1;

	private String newGameImagen = "resources/Menu/newGame.png";
	private String scoresImagen = "resources/Menu/Scores.png";
	private String exitImagen = "resources/Menu/exit.png";
	private ImageIcon newGame = new ImageIcon(newGameImagen);
	private ImageIcon scores = new ImageIcon(scoresImagen);
	private ImageIcon exit = new ImageIcon(exitImagen);
	public boolean scoresTF=false;


	/* --- CONSTRUCTOR --- */
	public Menu(JFrame ventana, PlantillaVentana plantillaVentana) {
		this.ventana = ventana;
		this.plantillaVentana = plantillaVentana;
        this.ventana.setPreferredSize(new Dimension(1000,1000)); 
		
		etiqueta = new JLabel(newGame);

		label = new JLabel("Oliva Adventures");

		etiqueta.add(label);
		this.ventana.add(etiqueta);
		this.ventana.addKeyListener(this);
		
		this.ventana.pack();		// Comando para que todo el contenido se ajuste al tama�o de la ventana.


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
				
				ventana.remove(etiqueta);
				
				ventana.removeKeyListener(this);
				nivel = true;
				 
			}
		}

		else if (contadorTecla == 2) {
			etiqueta.setIcon(scores);

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//				Puntuaciones prueba = new Puntuaciones("ZZZ", 8888);
				ventana.removeKeyListener(this);
				ventana.remove(etiqueta);
//				VentanaScore prueba2 = new VentanaScore();
				scoresTF=true;
				
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e2) {
//					System.out.println("Ha petado esperando en Menu a que VentanaScore nos devuelva scoresOn en true: ");
//					e2.printStackTrace();
//				}
//				while(prueba2.scoresOn) {
//					System.out.println("Scores - Esperando");
//					try {
//						Thread.sleep(4000);
//					} catch (InterruptedException e1) {
//						System.out.println("Ha petado esperando en Menu a que VentanaScore nos devuelva scoresOn en true: ");
//						e1.printStackTrace();
//					}
//					prueba2.frame.setVisible(false);
//
//				}
//				
//				ventana.addKeyListener(this);
//				ventana.setVisible(true);
//				etiqueta.setIcon(scores);
				
			}

		}

		else if (contadorTecla == 3) {
			etiqueta.setIcon(exit);

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {						 // Aqui ira el codigo para quitar la ventana grafica.
				System.exit(1);
				
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
	
	public void meterPuntuacion(String nombre,int punt) {
		
		prueba = new Puntuaciones(nombre, punt);
		
	}

}
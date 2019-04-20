package olivaAdventures;

import javax.swing.JFrame;

import java.awt.Dimension;


public class PlantillaVentana {
	


	private static JFrame ventana;
	private static final int ANCHO = 1000;
	private static final int ALTO = 1000;
	private static final String NOMBRE = "Oliva Adventures";
	Menu menu;
	public int puntuacion=0;
	public String nombrePuntuacion="AAA";
	VentanaScore prueba2;
	
	public enum TipoVentana {
		Menu,
		Nivel,
		Puntuacion
	}
	                          
	public PlantillaVentana() {
		
		ventana = new JFrame(NOMBRE);
		ventana.setPreferredSize(new Dimension(ANCHO, ALTO));
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Al cerrar la ventana, la aplicacion no continuara en segundo plano
		ventana.setResizable(false); //El usuario no podra arrastrar los bordes para hacer la ventana mas grande o pequeña
		ventana.pack(); //Comando para que todo el contenido se ajuste al tamaño de la ventana
		ventana.setLocationRelativeTo(null); //Fijar la ventana en el centro del escritorio, escribiendo otra cosa se fijaria al centro de esa cosa
		ventana.setVisible(true);
		
		
	}
	
	public void cambiarTipodeVentana(TipoVentana tipus) {
		
		if (tipus.equals(TipoVentana.Menu)) {
			ventana.setVisible(true);
			menu = new Menu(ventana, this);
			

		} 
		else if (tipus.equals(TipoVentana.Nivel)) {
			GameEngineLVL1 nivel = new GameEngineLVL1(ventana);
			puntuacion=nivel.runGame();
			nombrePuntuacion=nivel.getNombreJugador();
			pasarPuntuacion();
			ventana.setVisible(false);
		}
		else if (tipus.equals(TipoVentana.Puntuacion)) {
			prueba2 = new VentanaScore(menu.ventana);
		
		}
	}
	
	public void pasarPuntuacion() {
		
		menu.meterPuntuacion(nombrePuntuacion,puntuacion);
		
	}
	
	
}


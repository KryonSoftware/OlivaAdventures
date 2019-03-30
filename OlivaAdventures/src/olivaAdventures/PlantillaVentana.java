package olivaAdventures;

import javax.swing.JFrame;

import olivaAdventures.PlantillaVentana.TipoVentana;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Canvas;

public class PlantillaVentana implements Runnable {
	

	private static final long serialVersionUID = 1L;
	private static JFrame ventana;
	private static final int ANCHO = 1000;
	private static final int ALTO = 1000;
	private static final String NOMBRE = "Oliva Adventures";
	private static int aps = 0;
	private static int fps = 0;
	private static volatile boolean enFuncionamiento = false; //Lo dejamos en falso por defecto
	
	public enum TipoVentana {
		Menu,
		Nivel,
		puntuacion
	}
	                          
	public PlantillaVentana() {
		
		//Líneas viejas hay que quitarlas para evitar confusiones cuando lo lea quien no tenga ni flies de este código?????????????????????????????????????????????????
		//setPreferredSize(new Dimension(ANCHO, ALTO)); //Fijamos el tama�o de la ventana
		ventana = new JFrame(NOMBRE);
		ventana.setPreferredSize(new Dimension(ANCHO, ALTO));
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Al cerrar la ventana, la aplicacion no continuara en segundo plano
		ventana.setResizable(false); //El usuario no podra arrastrar los bordes para hacer la ventana mas grande o peque�a
		ventana.setLayout(new BorderLayout()); //A�adirle un dise�o a la ventana
		ventana.pack(); //Comando para que todo el contenido se ajuste al tama�o de la ventana
		ventana.setLocationRelativeTo(null); //Fijar la ventana en el centro del escritorio, escribiendo otra cosa se fijaria al centro de esa cosa
		
		enFuncionamiento = true;
		
		ventana.setVisible(true);
		
}
	
	private void actualizar() { //Todo lo necesario para actualizar las variables del juego
		
		aps++; //Sumamos a APS uno cada vez que actualice
		
	}
	
	private void mostrar() { //Todos los metodos para ir redibujando los graficos
		
		fps++; //Sumamos a FPS uno cada vez que se ejecute mostrar
		
	}

	@Override
	public void run() {
		
		final int NS_POR_SEGUNDO = 1000000000; //9 Ceros, equivalencia de nanosegundos por segundo
		
		final byte APS_OBJETIVO = 60; //Actualizaciones por segundo objetivo, cuanto mas bajo el numero, menos veces se actualiza el juego y menos carga en el equipo.
									  // En un futuro intentar bajar el numero sin generar problemas de rendimiento, un numero fluido y con buena funcionalidad seria alrededor de 30
		
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO; //Descubrir cuantos nanosegundos deben pasar para cumplir el objetivo de actualizar 60 veces por segundo
		
		long referenciaActualizacion = System.nanoTime(); //Se atribuira una cantidad de tiempo en nanosegundos. nanoTime es un metodo fiable, no depende del sistema operativo, sino del
														  //Procesador, mide en nanosegundos tomando como referencia los ciclos del reloj del procesador.
		long referenciaContador = System.nanoTime();
		
		double tiempoTranscurrido;
		
		double delta = 0; //Cantidad de tiempo transcurrido hasta una actualizacion
		while (enFuncionamiento = true) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle; //Cada vuelta del bucle, restamos diferencias con inicioBucle, haciendo mediciones precisas.
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
			
				while (delta >= 1) {
					actualizar(); //Actualiza el estado del juego
					delta--;
				}
			
			mostrar();    //Redibuja los graficos que se ven en pantalla
			
			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) { //Se toma el tiempo preciso de nanoTime y se resta a la diferencia de contador, si es mas de 1 segundo, se realiza
																		   //una actualizacion al contador
				
				ventana.setTitle(NOMBRE + " || APS: "+aps+" ||FPS: "+fps); //Se mostrara cuantos APS y FPS hay y luego se reiniciara a 0 de cara a la siguiente actualizacion
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime(); //Vuelve a tomar el contador, cada vez que el bucle vuelva a iniciarse tendremos medicion precisa de tiempo.
				
			}
		}
	}
	
	public void cambiarTipodeVentana(TipoVentana tipus) {
		if (tipus.equals(TipoVentana.Menu)) {
			Menu menu = new Menu(ventana, this);
			//
		} else if (tipus.equals(TipoVentana.Nivel)) {
			//QUEDA COMENTADO PARA EVITAR ERRORES DE COMPIACIÓN A LA ESPERA DEL BUGFIXING
			//GameEngineLVL1 nivel = new GameEngineLVL1(ventana, this);
			
		}
		
		
	}
	
	
}

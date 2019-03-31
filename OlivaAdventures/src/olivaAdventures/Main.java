package olivaAdventures;

//import olivaAdventures.PlantillaVentana.TipoVentana;

public class Main {
	
	public static void main(String[] args) {
		
		/*
		 * Código de David NO FUNCIONAL. A LA ESPERA DE BUGFIXING
		 *
		 * // PlantillaVentana implementa Runnable (es un hilo y debe ejecutarse como tal)
		PlantillaVentana pv = new PlantillaVentana();
		Thread thread = new Thread(pv);
		thread.start();
		pv.cambiarTipodeVentana(TipoVentana.Menu);
		 *
		 */
		
		GameEngineLVL1 ge=new GameEngineLVL1();
		ge.arrancar();
		
	}

}

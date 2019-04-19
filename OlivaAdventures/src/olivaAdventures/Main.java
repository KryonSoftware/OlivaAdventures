package olivaAdventures;

import olivaAdventures.PlantillaVentana.TipoVentana;

public class Main {
	
	public static void main(String[] args) {
		
		PlantillaVentana pv = new PlantillaVentana();
		pv.cambiarTipodeVentana(TipoVentana.Menu);
		
		while(true) {
			
			while (!(pv.menu.nivel) && !(pv.menu.scoresTF)) {
				System.out.println("Menú - Esperando");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					System.out.println("Ha petado en el main esperando a que PlantillaVentana.Menu nos devuelva nivel o scroesTF en true para continuar: ");
					e.printStackTrace();
				}
			}
			
			if(pv.menu.nivel) {
				pv.cambiarTipodeVentana(TipoVentana.Nivel);
			}
			else {
				pv.cambiarTipodeVentana(TipoVentana.Puntuacion);
				while (pv.prueba2.scoresOn) {
					System.out.println("Scores - Esperando");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						System.out.println("Ha petado en el main esperando a que PlantillaVentana.Menu nos devuelva nivel o scroesTF en true para continuar: ");
						e.printStackTrace();
					}
				}
			}
			
//			pv.cambiarTipodeVentana(TipoVentana.Nivel);
//			pv.pasarPuntuacion();
			pv=new PlantillaVentana();
			pv.cambiarTipodeVentana(TipoVentana.Menu);
			
		}
/*		
		GameEngineLVL1 ge=new GameEngineLVL1();
		int puntuacionFinal=ge.runGame();
		System.out.println(puntuacionFinal+"eoeoeoeoe");
*/		
		
		
	}

}

package olivaAdventures;

import olivaAdventures.PlantillaVentana.TipoVentana;

public class Main {
	
	public static void main(String[] args) {
		
		PlantillaVentana pv = new PlantillaVentana();
		pv.cambiarTipodeVentana(TipoVentana.Menu);
		
		while(true) {
			
			while (!(pv.menu.nivel)) {
				System.out.println("Menú - Esperando");
			}
			
			pv.cambiarTipodeVentana(TipoVentana.Nivel);
			
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

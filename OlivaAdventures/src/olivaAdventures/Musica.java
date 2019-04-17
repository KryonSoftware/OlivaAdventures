package olivaAdventures;

import java.io.File;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Musica {

    /* ATTRIBUTES */
    private BasicPlayer fondo,disparo;
    //Musica fondo = new Musica();

    /* CONSTRUCTOR */
    public Musica() {
        fondo=new BasicPlayer();
        disparo=new BasicPlayer();
        cargarFondo();
        cargarDisparo();
    }

    /* BASIC FUNCTIONS */
    public void playFondo() {
        try {fondo.play();} catch (BasicPlayerException e) {/*A MAMARLA LOS PUTOS LOGS EN FORMA DE EXCEPCIÓN*/}
    }
    
    public void playDisparo() {
        try {disparo.play();} catch (BasicPlayerException e) {/*A MAMARLA LOS PUTOS LOGS EN FORMA DE EXCEPCIÓN*/}
    }

    public void pausaFondo() {
        try {fondo.pause();} catch (BasicPlayerException e) {/*A MAMARLA LOS PUTOS LOGS EN FORMA DE EXCEPCIÓN*/}
    }

    public void continuarFondo() {
        try {fondo.resume();} catch (BasicPlayerException e) {/*A MAMARLA LOS PUTOS LOGS EN FORMA DE EXCEPCIÓN*/}
    }

    public void stop() {
        try {
			fondo.stop();
			disparo.stop();
		} catch (BasicPlayerException e) {/*A MAMARLA LOS PUTOS LOGS EN FORMA DE EXCEPCIÓN*/}
    }

    /* FUNCTIONS */
    public void cargarFondo(){

        try {
        	fondo.open(new File("resources/sonido/musica_fondo/welcome_to_the_jungle_8_bit.mp3"));
        } catch (Exception e) {
            System.out.println("Error cargando la música de fondo. Posible error en el path. Error: "+e);
        }
    }

    public void cargarDisparo(){
    	
        try {
        	disparo.open(new File("resources/sonido/sonido_pistola/realistic_gunshot_sound_effect.mp3"));
        } catch (Exception e) {
            System.out.println("Error cargando la música de disparo. Posible error en el path. Error: "+e);
        }
    }

}
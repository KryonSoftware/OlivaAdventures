package olivaAdventures;

import java.io.File;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Musica {

    /* ATTRIBUTES */
    private BasicPlayer fondo,disparo,click,salto,ouch,enemigoOuch,lose;
    //Musica fondo = new Musica();

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
		} catch (BasicPlayerException e) {/*A MAMARLA LOS PUTOS LOGS EN FORMA DE EXCEPCIÓN*/}
    }
    public void click() {
        try {click.play();} catch (BasicPlayerException e) {/*A MAMARLA LOS PUTOS LOGS EN FORMA DE EXCEPCIÓN*/}
    }
    public void lose() {
    	try {
        	lose.play();
        } catch (Exception e) {
            System.out.println("Error cargando la música de click. Posible error en el path. Error: "+e);
        }
    }

    /* FUNCTIONS */
    public void cargarFondo(){

        try {
            fondo=new BasicPlayer();
            lose=new BasicPlayer();

        	lose.open(new File("resources/sonido/musica_fondo/lose.mp3"));
        	fondo.open(new File("resources/sonido/musica_fondo/welcome_to_the_jungle_8_bit.mp3"));
        } catch (Exception e) {
            System.out.println("Error cargando la música de fondo. Posible error en el path. Error: "+e);
        }
    }

    public void cargarDisparo(){
    	
        try {
            disparo=new BasicPlayer();

        	disparo.open(new File("resources/sonido/sonido_pistola/shot.mp3"));
        } catch (Exception e) {
            System.out.println("Error cargando la música de disparo. Posible error en el path. Error: "+e);
        }
    }
    
    public void cargarClick(){
    	
        try {
            click=new BasicPlayer();

        	click.open(new File("resources/sonido/Menu/click.mp3"));
        } catch (Exception e) {
            System.out.println("Error cargando la música de click. Posible error en el path. Error: "+e);
        }
    }
    
}
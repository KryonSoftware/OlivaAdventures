package olivaAdventures;

import java.io.File;

import javazoom.jlgui.basicplayer.BasicPlayer;

public class Musica {

    /* ATTRIBUTES */
    private BasicPlayer player;

    /* CONSTRUCTOR */
    public Musica() {
        player=new BasicPlayer();
    }

    /* BASIC FUNCTIONS */
    public void play() throws Exception {
        player.play();
    }

    public void abrirFichero(String ruta) throws Exception {
        player.open(new File(ruta));
    }

    public void pausa() throws Exception {
        player.pause();
    }

    public void continuar() throws Exception {
        player.resume();
    }

    public void stop() throws Exception {
        player.stop();
    }

    /* FUNCTIONS */
    public void cargarMusicaFondo(){

        Musica muscia = new Musica();
        try {

            muscia.abrirFichero("resources/media/sonido/musica_fondo/" +
                    "welcome_to_the_jungle_8_bit.mp3");
            muscia.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarSonidoPistola(){

        Musica muscia = new Musica();
        try {

            muscia.abrirFichero("resources/media/sonido/sonido_pistola/" +
                    "realistic_gunshot_sound_effect.mp3");
            muscia.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
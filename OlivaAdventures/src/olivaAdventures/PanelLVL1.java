package olivaAdventures;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Clase JPanel propia. Además de actuar de panel, se encarga de recolocar ella sola, a través de los parámetros que le pasamos, el resto del mapa.
 * Será necesario que haya una por nivel, al igual de la clase GameEngine.
 * @author kryon
 *
 */
@SuppressWarnings("serial")
public class PanelLVL1 extends JPanel {

	private ArrayList<Platform> listaPlataformas=new ArrayList<>();

    private int x;

    private int y;

    public void setX(int x){this.x=x;}

    public void setY(int y){this.y=y;}

    private int getX(int x) {return x;}

    private int getY(int y) {return y;}

    /**
     * Método para generar y añadir una nueva plataforma a la lista, que es borrada a cada repintado.
     * @param ejeX
     * @param ejeY
     * @param ancho
     * @param alto
     */
    private void addPlatformToList(int ejeX,int ejeY,int ancho,int alto){

        listaPlataformas.add(new Platform(ejeX,ejeY,ancho));

    }

    /**
     * Método que nos indica mediante un boolean si los ejes que le hemos pasado por parámetro están sobra la superficie de una plataforma o la ha atravesado.
     * @param ejeX
     * @param ejeY
     * @param prevY
     * @return false -> si sigue cayendo | true -> si se posado sobre una plataforma
     * @TODO Arreglar las colisiones, a veces se pinta en mitad de la plataforma.
     */
    public boolean isGround(int ejeX,int ejeY,int prevY){

        int z;
        int y;
        int g;
        boolean colision=false;

        for(int x=0;x<listaPlataformas.size();x++){

            z=listaPlataformas.get(x).getEjeX();
            y=listaPlataformas.get(x).getEjeY();
            g=listaPlataformas.get(x).getAncho();

            if(350>=z&&350<=(z+g)) {

                if(700-ejeY+35>=y&&prevY<y){

                    colision=true;

                    this.y=y;

                }

            }

        }

        return colision;

    }

    /**
     * Método para pintar nuestro panel
     */
    public void paint(Graphics g) {

    	//Borramos nuestras plataformas, ya que puede que ahora se vayan a mover
        listaPlataformas.clear();

        //Fondo por ahora (se sustituirá con una imagen y se lo hará desplazarse también
        g.setColor(Color.gray);
        g.fillRect(0,0,1000,1000);

        //Plataforma suelo
        g.setColor(Color.green);
        g.fillRect(0-x, 720, 1500, 50);
        addPlatformToList(0-x,720,1500,50);

        //Plataformas para saltar
        g.setColor(Color.black);
        g.fillRect(700-x,300,100,10);
        addPlatformToList(700-x,300,100,10);

        g.setColor(Color.black);
        g.fillRect(900-x,150,100,10);
        addPlatformToList(900-x,150,100,10);

        g.setColor(Color.black);
        g.fillRect(450-x,450,100,10);
        addPlatformToList(450-x,450,100,10);

        g.setColor(Color.black);
        g.fillRect(160-x,600,100,10);
        addPlatformToList(160-x,600,100,10);

        //Nuestro keko
        g.setColor(Color.yellow);
        g.fillRect(350,700-y,20,35);

    }
}

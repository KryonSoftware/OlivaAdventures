package olivaAdventures;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelLVL1 extends JPanel {

    private ArrayList<Platform> listaPlataformas=new ArrayList<>();

    private int x;

    private int y;

    public void setX(int x){this.x=x;}

    public void setY(int y){this.y=y;}

    private int getX(int x) {return x;}

    private int getY(int y) {return y;}

    private void addPlatformToList(int ejeX,int ejeY,int ancho,int alto){

        listaPlataformas.add(new Platform(ejeX,ejeY,ancho));

    }

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


                if(700-ejeY+35==y){

                    colision=true;

                }
                if(700-ejeY+35>y&&prevY<y){

                    colision=true;

                    this.y=y;

                }

            }

        }

        return colision;

    }

    public void paint(Graphics g) {

        listaPlataformas.clear();

        g.setColor(Color.gray);
        g.fillRect(0,0,1000,1000);

        g.setColor(Color.green);
        g.fillRect(0-x, 720, 1500, 50);
        addPlatformToList(0-x,720,1500,50);

        g.setColor(Color.black);
        g.fillRect(700-x,300,100,10);
        addPlatformToList(700-x,300,100,10);

        g.setColor(Color.black);
        g.fillRect(900-x,100,100,10);
        addPlatformToList(900-x,100,100,10);

        g.setColor(Color.black);
        g.fillRect(450-x,450,100,10);
        addPlatformToList(450-x,450,100,10);

        g.setColor(Color.black);
        g.fillRect(160-x,600,100,10);
        addPlatformToList(160-x,600,100,10);

        g.setColor(Color.yellow);
        g.fillRect(350,700-y,20,35);
//        addPlatformToList(350-x,700-y,20,35);

    }
}

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

    private int x,y;

    public void setEjeX(int x){this.x=x;}

    public void setEjeY(int y){this.y=y;}
    
    public int getEjeX() {return this.x;}

    public int getEjeY() {return this.y;}

    /**
     * Método para generar y añadir una nueva plataforma a la lista, que es borrada a cada repintado.
     * @param ejeX
     * @param ejeY
     * @param ancho
     * @param alto
     */
    private void addPlatformToList(int ejeX,int ejeY,int ancho,int alto,Tipo tipo){

        listaPlataformas.add(new Platform(ejeX,ejeY,ancho,alto,tipo));

    }

    /**
     * Método que nos indica mediante un boolean si los ejes que le hemos pasado por parámetro están sobra la superficie de una plataforma o la ha atravesado.
     * @param ejeX
     * @param ejeY
     * @param prevY
     * @return false -> si sigue cayendo | true -> si se posado sobre una plataforma
     * @TODO Arreglar las colisiones, a veces se pinta en mitad de la plataforma.
     */
    public boolean isGround(int ejeX,int ejeY,int prevY,int prevX){

    	boolean foundPlatform=false;
        int y,z,g,k;
        boolean colision=false;

        for(int x=0;x<listaPlataformas.size();x++){
        	
        	if(listaPlataformas.get(x).getTipo()==Tipo.PLATFORM) {

	        	if(!foundPlatform) {
	            
	        		z=listaPlataformas.get(x).getEjeX();
		            y=listaPlataformas.get(x).getEjeY();
		            g=listaPlataformas.get(x).getAncho();
		
		            if(350+5>=z&&350+11<=(z+g)) {
		
		                if(720-ejeY+35>=y&&prevY<y){
		
		                    colision=true;
		                    
		                    foundPlatform=true;
		
		                    this.y=y;
		
		                }
		
		            }
		            
	        	}
	        	
        	}
        	else if(listaPlataformas.get(x).getTipo()==Tipo.BOTH) {
        		
        		if(!foundPlatform) {
    	            
	        		z=listaPlataformas.get(x).getEjeX();
		            y=listaPlataformas.get(x).getEjeY();
		            g=listaPlataformas.get(x).getAncho();
		            k=listaPlataformas.get(x).getAlto();
		
		            if(350+5>=z&&350+11<=(z+g)) {
		
		                if(720-ejeY+35>=y&&prevY<y+k){
		
		                    colision=true;
		                    
		                    foundPlatform=true;
		                    
		                    this.y=y;
		
		                }
		
		            }
		            
	        	}
        		
        	}
        	else if(listaPlataformas.get(x).getTipo()==Tipo.GROUND) {
        		
        		if(!foundPlatform) {
    	            
	        		z=listaPlataformas.get(x).getEjeX();
		            y=listaPlataformas.get(x).getEjeY();
		            g=listaPlataformas.get(x).getAncho();
		            k=listaPlataformas.get(x).getAlto();
		
		            if(350+5>=z&&350+11<=(z+g)) {
		
		                if(720-ejeY+35>=y&&prevY<y+k){
		
		                    colision=true;
		                    
		                    foundPlatform=true;
		                    
		                    this.y=y;
		
		                }
		
		            }
		            
	        	}
        		
        	}

        }

        return colision;

    }

    public int isWall(int id) {
    	
    	boolean foundPlatform=false;
        int y,z,g,k,s=id;
        int colision=id;

        for(int x=0;x<listaPlataformas.size();x++){
        	
        	if(listaPlataformas.get(x).getTipo()==Tipo.BOTH) {
	            
        		z=listaPlataformas.get(x).getEjeX();
	            y=listaPlataformas.get(x).getEjeY();
	            g=listaPlataformas.get(x).getAncho();
	            k=listaPlataformas.get(x).getAlto();
	
	            if(350+15+s>=z&&350+s<=(z+g)) {
	            	
	            	if(s>0) {
	            		
	            		 colision-=370-(z-s);
	            		
	            	}
	            	else {
	            		
	            		 colision-=350-(z+g-s);
	            		
	            	}
		            
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
        g.fillRect(0-x,400,50,370);
        addPlatformToList(0-x,400,50,370,Tipo.BOTH);
        g.setColor(Color.green);
        g.fillRect(0-x, 720, 10000, 50);
        addPlatformToList(0-x,720,10000,50,Tipo.GROUND);
        g.setColor(Color.green);
        g.fillRect(1000-x,400,50,370);
        addPlatformToList(1000-x,400,50,370,Tipo.BOTH);

        //Plataformas para saltar
        g.setColor(Color.black);
        g.fillRect(700-x,300,100,10);
        addPlatformToList(700-x,300,100,10,Tipo.PLATFORM);

        g.setColor(Color.black);
        g.fillRect(900-x,150,100,10);
        addPlatformToList(900-x,150,100,10,Tipo.PLATFORM);

        g.setColor(Color.black);
        g.fillRect(450-x,450,100,10);
        addPlatformToList(450-x,450,100,10,Tipo.PLATFORM);

        g.setColor(Color.black);
        g.fillRect(160-x,600,100,10);
        addPlatformToList(160-x,600,100,10,Tipo.PLATFORM);

        //Nuestro keko
        g.setColor(Color.yellow);
        g.fillRect(350,720-y-34,20,35);

    }
}

package olivaAdventures;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

	private BufferedImage fondo,keko_right,keko_right2,keko_stand_right,keko_left,keko_left2,keko_stand_left,hud,barraVidaCompleta;
	
	private BufferedImage[] animKeko = new BufferedImage[6];
	
    private int x,y,arrPosKeko;

    public int getArrPosKeko() {return arrPosKeko;}

	public void setArrPosKeko(int arrPosKeko) {this.arrPosKeko = arrPosKeko;}

	public void setEjeX(int x){this.x=x;}

    public void setEjeY(int y){this.y=y;}
    
    public int getEjeX() {return this.x;}

    public int getEjeY() {return this.y;}
    
    /**
     * Constructor del panel. Nos aseguramos de que cadda vez que sea
     *  instanciado va a cargar los buffer de las imágenes que le pertoque.
     */
	public PanelLVL1() {
			
		cargarImagenes();
		cargarRaizImagenesKeko();
	    	
	}
	
	/**
	 * Método para cargar las imágenes desde la carpeta de recursos.
	 */
	private void cargarImagenes() {
		
		try {    
			
			//Sacamos las imágenes desde nuestra carpeta de recursos
			fondo = ImageIO.read(new File("resources/Mapa/Mapa_reajustado.png"));
			hud = ImageIO.read(new File("resources/Hud/hud/proxy.duckduckgo.com.png"));
			keko_right = ImageIO.read(new File("resources/Personaje/pjDerecha/pjDer2.png"));
			keko_right2 = ImageIO.read(new File("resources/Personaje/pjDerecha/pjDer4.png"));
			keko_stand_right= ImageIO.read(new File("resources/Personaje/pjDerecha/pjDer3.png"));
			keko_left= ImageIO.read(new File("resources/Personaje/pjIzquierda/pjIzq2.png"));
			keko_left2= ImageIO.read(new File("resources/Personaje/pjIzquierda/pjIzq4.png"));
			keko_stand_left= ImageIO.read(new File("resources/Personaje/pjIzquierda/pjIzq3.png"));
			barraVidaCompleta = ImageIO.read(new File("resources/Hud/BarraExp/BarraExpTerminada/barraExpFull.png"));
          
       } catch (IOException e) {
       
    	   System.out.println("Error cargando las imágenes desde la carpeta de recursos. Comprueba los path, la carpeta de recuros y que no "+
    	   "estén corruptos los datos. Error:"+e);
    	   
       }
	
	}

	private void cargarRaizImagenesKeko() {
		
		animKeko[0]=keko_right;
		animKeko[1]=keko_right2;
		animKeko[2]=keko_stand_right;
		animKeko[3]=keko_left;
		animKeko[4]=keko_left2;
		animKeko[5]=keko_stand_left;
		
	}
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
     */
    public boolean isGround(int ejeX,int ejeY,int prevY,int prevX){

    	
    	/*
    	 * QUEDAN CAMBIOS POR HACER Y AJUSTAR. LUEGO HAY QUE COMENTARLO
    	 */
    	
    	
    	boolean foundPlatform=false;
        int y,z,g,k;
        boolean colision=false;

        for(int x=0;x<listaPlataformas.size();x++){
        	
        	if(listaPlataformas.get(x).getTipo()==Tipo.TUBE) {

	        	
	        	
        	}
        	else {
        		
        		if(!foundPlatform) {
    	            
	        		z=listaPlataformas.get(x).getEjeX();
		            y=listaPlataformas.get(x).getEjeY();
		            g=listaPlataformas.get(x).getAncho();
		
		            if(350+15>=z&&350+22<=(z+g)) {
		
		                if(720-ejeY+35>=y&&prevY<y){
		
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

    /**
     * Método para comprobar si hay choque contra una pared y a qué distancia está.
     * @param id
     * @return Distancia que puedes mover sin colisionar.
     */
    public int isWall(int id) {
    	
    	boolean foundPlatform=false;
        int y,z,g,k,s=id;
        int colision=id;

        for(int x=0;x<listaPlataformas.size();x++){
        	
        	if(listaPlataformas.get(x).getTipo()==Tipo.BOTH || listaPlataformas.get(x).getTipo()==Tipo.BOTH) {
	            
        		z=listaPlataformas.get(x).getEjeX();
	            y=listaPlataformas.get(x).getEjeY();
	            g=listaPlataformas.get(x).getAncho();
	            k=listaPlataformas.get(x).getAlto();
	
	            if((350+15+s>=z-5&&350+s<=(z+g-15)) && (720-this.y>y&&720-this.y+34<(y+k))) {
	            	
	            	if(s>0) {
	            		
	            		 colision-=370-(z-s-5);
	            		
	            	}
	            	else {
	            		
	            		 colision-=350-(z+g-s-15);
	            		
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

        //Fondo por ahora (se sustituirá con una imagen y se lo hará desplazarse también)
		
        g.setColor(Color.gray);
        g.fillRect(0,0,1000,1000);
		
        //Imagen móvil de fondo
		g.drawImage(fondo, -350-(x/2), 0,4000,720, this);
		//Imagen provisional del hud
		g.drawImage(hud,0,698,1010,350,this);
		//Aquí habrá que programar las reproducciones las barras de vida
		g.drawImage(barraVidaCompleta, 700,750,250,125,this);

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
        g.drawImage(animKeko[arrPosKeko],350,720-y-69,40,70,this);

    }
}

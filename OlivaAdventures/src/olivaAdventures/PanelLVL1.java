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

	private BufferedImage fondo,keko_right,keko_right2,keko_right3,keko_right4,keko_stand_right,keko_stand_right2,keko_stand_right3,
	keko_stand_right4,keko_left,keko_left2,keko_left3,keko_left4,keko_stand_left,keko_stand_left2,keko_stand_left3,keko_stand_left4,hud,barraExp100,barraExp75,
	barraExp50,barraExp25,barraExp0,vidas0,vidas1,vidas2,vidas3,monstruo1,monstruo2,monstruo3,monstruo4,plataforma1,plataforma2,
	reloj0,reloj1,reloj2,reloj3,reloj4,reloj5,reloj6,reloj7,reloj8,reloj9,reloj10,reloj11,reloj12,reloj13,reloj14,reloj15,reloj16,reloj17,reloj18,
	reloj19,reloj20,reloj21,reloj22,reloj23,reloj24,reloj25;
	
	private BufferedImage[] animKeko = new BufferedImage[16],animBarra=new BufferedImage[5],animMonstruo=new BufferedImage[4], animCorazones=new BufferedImage[4];
	
    private int x,y,arrPosKeko=2,arrPosBarra=4,arrPosCor=0;

    public int getArrPosCor() {return arrPosCor;}

	public void setArrPosCor(int arrPosCor) {this.arrPosCor = arrPosCor;}

	public int getArrPosBarra() {return arrPosBarra;}

	public void setArrPosBarra(int arrPosBarra) {this.arrPosBarra = arrPosBarra;}

	public int getArrPosKeko() {return arrPosKeko;}

	public void setArrPosKeko(int arrPosKeko) {this.arrPosKeko = arrPosKeko;}

	public void setEjeX(int x){this.x=x;}

    public void setEjeY(int y){this.y=y;}
    
    public int getEjeX() {return this.x;}

    public int getEjeY() {return this.y;}
    
    /**
     * Constructor del panel. Nos aseguramos de que cada vez que sea
     *  instanciado va a cargar los buffer de las imágenes que le pertoque.
     */
	public PanelLVL1() {
			
		cargarImagenes();
		cargarRaizImagenesKeko();
		cargarRaizImagenesBarra();
		cargarRaizImagenesCorazones();
		cargarRaizImagenesMonstruo();
	    	
	}
	
	/**
	 * Método para cargar las imágenes desde la carpeta de recursos.
	 */
	private void cargarImagenes() {
		
		try {    
			
			//Sacamos las imágenes desde nuestra carpeta de recursos
			fondo = ImageIO.read(new File("resources/media/Mapa/fondo.jpg"));
			hud = ImageIO.read(new File("resources/media/Hud/hud/proxy.duckduckgo.com.png"));
			keko_right = ImageIO.read(new File("resources/media/Personaje/olivaWalkDer/olivaWalkDer1.png"));
			keko_right2 = ImageIO.read(new File("resources/media/Personaje/olivaWalkDer/olivaWalkDer2.png"));
			keko_right3 = ImageIO.read(new File("resources/media/Personaje/olivaWalkDer/olivaWalkDer3.png"));
			keko_right4 = ImageIO.read(new File("resources/media/Personaje/olivaWalkDer/olivaWalkDer4.png"));
			keko_left = ImageIO.read(new File("resources/media/Personaje/olivaWalkIzq/olivaWalkIzq1.png"));
			keko_left2 = ImageIO.read(new File("resources/media/Personaje/olivaWalkIzq/olivaWalkIzq2.png"));
			keko_left3 = ImageIO.read(new File("resources/media/Personaje/olivaWalkIzq/olivaWalkIzq3.png"));
			keko_left4 = ImageIO.read(new File("resources/media/Personaje/olivaWalkIzq/olivaWalkIzq4.png"));
			keko_stand_right= ImageIO.read(new File("resources/media/Personaje/olivaDer/oliva1.png"));
			keko_stand_right2= ImageIO.read(new File("resources/media/Personaje/olivaDer/oliva2.png"));
			keko_stand_right3= ImageIO.read(new File("resources/media/Personaje/olivaDer/oliva3.png"));
			keko_stand_right4= ImageIO.read(new File("resources/media/Personaje/olivaDer/oliva4.png"));
			keko_stand_left= ImageIO.read(new File("resources/media/Personaje/olivaIzq/olivaizq1.png"));
			keko_stand_left2= ImageIO.read(new File("resources/media/Personaje/olivaIzq/olivaizq2.png"));
			keko_stand_left3= ImageIO.read(new File("resources/media/Personaje/olivaIzq/olivaizq3.png"));
			keko_stand_left4= ImageIO.read(new File("resources/media/Personaje/olivaIzq/olivaizq4.png"));
			reloj0=ImageIO.read(new File("resources/media/contadorTiempo/reloj0.png"));
			reloj1=ImageIO.read(new File("resources/media/contadorTiempo/reloj1.png"));
			reloj2=ImageIO.read(new File("resources/media/contadorTiempo/reloj2.png"));
			reloj3=ImageIO.read(new File("resources/media/contadorTiempo/reloj3.png"));
			reloj4=ImageIO.read(new File("resources/media/contadorTiempo/reloj4.png"));
			reloj5=ImageIO.read(new File("resources/media/contadorTiempo/reloj5.png"));
			reloj6=ImageIO.read(new File("resources/media/contadorTiempo/reloj6.png"));
			reloj7=ImageIO.read(new File("resources/media/contadorTiempo/reloj7.png"));
			reloj8=ImageIO.read(new File("resources/media/contadorTiempo/reloj8.png"));
			reloj9=ImageIO.read(new File("resources/media/contadorTiempo/reloj9.png"));
			reloj10=ImageIO.read(new File("resources/media/contadorTiempo/reloj10.png"));
			reloj11=ImageIO.read(new File("resources/media/contadorTiempo/reloj11.png"));
			reloj12=ImageIO.read(new File("resources/media/contadorTiempo/reloj12.png"));
			reloj13=ImageIO.read(new File("resources/media/contadorTiempo/reloj13.png"));
			reloj14=ImageIO.read(new File("resources/media/contadorTiempo/reloj14.png"));
			reloj15=ImageIO.read(new File("resources/media/contadorTiempo/reloj15.png"));
			reloj16=ImageIO.read(new File("resources/media/contadorTiempo/reloj16.png"));
			reloj17=ImageIO.read(new File("resources/media/contadorTiempo/reloj17.png"));
			reloj18=ImageIO.read(new File("resources/media/contadorTiempo/reloj18.png"));
			reloj19=ImageIO.read(new File("resources/media/contadorTiempo/reloj19.png"));
			reloj20=ImageIO.read(new File("resources/media/contadorTiempo/reloj20.png"));
			reloj21=ImageIO.read(new File("resources/media/contadorTiempo/reloj21.png"));
			reloj22=ImageIO.read(new File("resources/media/contadorTiempo/reloj22.png"));
			reloj23=ImageIO.read(new File("resources/media/contadorTiempo/reloj23.png"));
			reloj24=ImageIO.read(new File("resources/media/contadorTiempo/reloj24.png"));
			reloj25=ImageIO.read(new File("resources/media/contadorTiempo/reloj25.png"));
			barraExp100 = ImageIO.read(new File("resources/media/Hud/BarraExp/barraExpFull.png"));
			barraExp75 = ImageIO.read(new File("resources/media/Hud/BarraExp/barraExp75.png"));
			barraExp50 = ImageIO.read(new File("resources/media/Hud/BarraExp/barraExp50.png"));
			barraExp25 = ImageIO.read(new File("resources/media/Hud/BarraExp/barraExp25.png"));
			barraExp0 = ImageIO.read(new File("resources/media/Hud/BarraExp/barraExpEmpty.png"));
			vidas0=ImageIO.read(new File("resources/media/Hud/Salud/0vidas.png"));
			vidas1=ImageIO.read(new File("resources/media/Hud/Salud/1vida.png"));
			vidas2=ImageIO.read(new File("resources/media/Hud/Salud/2vidas.png"));
			vidas3=ImageIO.read(new File("resources/media/Hud/Salud/3vidas.png"));
			monstruo1=ImageIO.read(new File("resources/media/monstruo/monstruo1.png"));
			monstruo2=ImageIO.read(new File("resources/media/monstruo/monstruo2.png"));
			monstruo3=ImageIO.read(new File("resources/media/monstruo/monstruo3.png"));
			monstruo4=ImageIO.read(new File("resources/media/monstruo/monstruo4.png"));
			plataforma1=ImageIO.read(new File("resources/media/plataforma/plataformaGrande.png"));
			plataforma2=ImageIO.read(new File("resources/media/plataforma/plataformaMediana.png"));
			
          
       } catch (IOException e) {
       
    	   System.out.println("Error cargando las imágenes desde la carpeta de recursos. Comprueba los path, la carpeta de recuros y que no "+
    	   "estén corruptos los datos. Error:\n"+e);
    	   
       }
	
	}

	private void cargarRaizImagenesKeko() {
		
		animKeko[0]=keko_right;
		animKeko[1]=keko_right2;
		animKeko[2]=keko_right3;
		animKeko[3]=keko_right4;
		animKeko[4]=keko_left;
		animKeko[5]=keko_left2;
		animKeko[6]=keko_left3;
		animKeko[7]=keko_left4;
		animKeko[8]=keko_stand_right;
		animKeko[9]=keko_stand_right2;
		animKeko[10]=keko_stand_right3;
		animKeko[11]=keko_stand_right4;
		animKeko[12]=keko_stand_left;
		animKeko[13]=keko_stand_left2;
		animKeko[14]=keko_stand_left3;
		animKeko[15]=keko_stand_left4;
		
	}
	
	private void cargarRaizImagenesMonstruo() {
		
		animMonstruo[0]=monstruo1;
		animMonstruo[1]=monstruo2;
		animMonstruo[2]=monstruo3;
		animMonstruo[3]=monstruo4;
		
	}
	
	private void cargarRaizImagenesBarra() {
		
		animBarra[0]=barraExp0;
		animBarra[1]=barraExp25;
		animBarra[2]=barraExp50;
		animBarra[3]=barraExp75;
		animBarra[4]=barraExp100;
		
	}
	
	private void cargarRaizImagenesCorazones() {
		
		animCorazones[0]=vidas0;
		animCorazones[1]=vidas1;
		animCorazones[2]=vidas2;
		animCorazones[3]=vidas3;
		
	}
	
	private void cargarRaizImagenesReloj() {
		
	}
	
	private void cargarRaizImagenesNumeros() {
		
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
        	
        	if(listaPlataformas.get(x).getTipo()==Tipo.BOTH || listaPlataformas.get(x).getTipo()==Tipo.TUBE) {
	            
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
        for(int x=-350,x<) {
        	
        }
		g.drawImage(fondo, -350-(x/2), 0,750,720, this);
		//Imagen provisional del hud
		g.drawImage(hud,0,698,1010,350,this);
		//Aquí habrá que programar las reproducciones las barras de vida
		g.drawImage(animBarra[arrPosBarra], 10,750,250,125,this);
		//Aquí habrá que programar las reproducciones de los corazones
		g.drawImage(animCorazones[arrPosCor], 10,875,250,75,this);

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
        g.drawImage(plataforma1,700-x,300,100,35,this);
        addPlatformToList(700-x,300,100,35,Tipo.PLATFORM);

        g.drawImage(plataforma1,900-x,150,100,35,this);
        addPlatformToList(900-x,150,100,35,Tipo.PLATFORM);

        g.drawImage(plataforma1,450-x,450,100,35,this);
        addPlatformToList(450-x,450,100,35,Tipo.PLATFORM);

        g.drawImage(plataforma1,160-x,600,100,35,this);
        addPlatformToList(160-x,600,100,35,Tipo.PLATFORM);

        //Nuestro keko
        g.drawImage(animKeko[arrPosKeko],350,720-y-89,50,90,this);
        

    }
}

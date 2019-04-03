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
	reloj19,reloj20,reloj21,reloj22,reloj23,reloj24,reloj25,nube1,nube2,suelo1,suelo2,arbol1,arbol2,arbusto,pausa;
	
	private BufferedImage[] animKeko = new BufferedImage[16],animBarra=new BufferedImage[5],animMonstruo=new BufferedImage[4], animCorazones=new BufferedImage[4],
			animReloj=new BufferedImage[26];
	
    private int x,y,arrPosKeko=2,arrPosBarra=4,arrPosCor=0,arrPosReloj=0,arrPosMonstruo=0,m11=0,m12=0,posYIniKeko=720-89,posXIniKeko=350,posXIniEnemy1_1=700,
    		posXIniEnemy1_2=1700,posYIniEnemy1_1=670,posYIniEnemy1_2=670;
    
    private boolean pause=false;
    
    public boolean isPause() {return pause;}

	public void setPause(boolean pause) {this.pause = pause;}

	public int getPosXIniKeko() {return posXIniKeko;}

	public void setPosXIniKeko(int posXIniKeko) {this.posXIniKeko = posXIniKeko;}

	public int getPosXIniEnemy1_2() {return posXIniEnemy1_2;}

	public void setPosXIniEnemy1_2(int posIniEnemy1_2) {this.posXIniEnemy1_2 = posIniEnemy1_2;}

    public int getM12() {return m12;}

	public void setM12(int m12) {this.m12 = m12;}

	public int getPosYIniKeko() {return posYIniKeko;}

	public void setPosYIniKeko(int posIniKeko) {this.posYIniKeko = posIniKeko;}

	public int getPosXIniEnemy1_1() {return posXIniEnemy1_1;}

	public void setPosXIniEnemy1_1(int posIniEnemy1_1) {this.posXIniEnemy1_1 = posIniEnemy1_1;}

	public int getM11() {return m11;}

	public void setM11(int m1) {this.m11 = m1;}

	public int getArrPosMonstruo() {return arrPosMonstruo;}

	public void setArrPosMonstruo(int arrPosMonstruo) {this.arrPosMonstruo = arrPosMonstruo;}

	public int getArrPosReloj() {return arrPosReloj;}

	public void setArrPosReloj(int arrPosReloj) {this.arrPosReloj = arrPosReloj;}

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
		cargarRaizImagenesReloj();
	    	
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
			nube1=ImageIO.read(new File("resources/media/Mapa/Nubes/nubeGrande.png"));
			nube2=ImageIO.read(new File("resources/media/Mapa/Nubes/nubePeque.png"));
			arbol1=ImageIO.read(new File("resources/media/Mapa/Arboles/arbolGrande.png"));
			arbol2=ImageIO.read(new File("resources/media/Mapa/Arboles/arbolMediano.png"));
			arbusto=ImageIO.read(new File("resources/media/Mapa/Arboles/arbustito.png"));
			suelo1=ImageIO.read(new File("resources/media/Suelos/cuadrado1.png"));
			suelo2=ImageIO.read(new File("resources/media/Suelos/cuadrado2.png"));
			pausa=ImageIO.read(new File("resources/media/Hud/pausa.png"));
			
          
       } catch (IOException e) {
       
    	   System.out.println("Error cargando las imágenes desde la carpeta de recursos. Comprueba los path, la carpeta de recuros y que no "+
    	   "estén corruptos los datos. Error:\n"+e);
    	   
       }
	
	}

	/**
	 * Método encargado de rellenar la raíz de movimientos del keko.
	 */
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
	
	/**
	 * Método encargado de rellenar la raíz de movimientos del monstruo POW.
	 */
	private void cargarRaizImagenesMonstruo() {
		
		animMonstruo[0]=monstruo1;
		animMonstruo[1]=monstruo2;
		animMonstruo[2]=monstruo3;
		animMonstruo[3]=monstruo4;
		
	}
	
	/**
	 * Método encargado de rellenar la raíz de animaciones de la barra-estrella.
	 */
	private void cargarRaizImagenesBarra() {
		
		animBarra[0]=barraExp0;
		animBarra[1]=barraExp25;
		animBarra[2]=barraExp50;
		animBarra[3]=barraExp75;
		animBarra[4]=barraExp100;
		
	}
	
	/**
	 * Método relleno raíz animaciones corazones.
	 */
	private void cargarRaizImagenesCorazones() {
		
		animCorazones[0]=vidas0;
		animCorazones[1]=vidas1;
		animCorazones[2]=vidas2;
		animCorazones[3]=vidas3;
		
	}
	
	/**
	 * Método relleno imágenes reloj.
	 */
	private void cargarRaizImagenesReloj() {
		
		animReloj[0]=reloj0;
		animReloj[1]=reloj1;
		animReloj[2]=reloj2;
		animReloj[3]=reloj3;
		animReloj[4]=reloj4;
		animReloj[5]=reloj5;
		animReloj[6]=reloj6;
		animReloj[7]=reloj7;
		animReloj[8]=reloj8;
		animReloj[9]=reloj9;
		animReloj[10]=reloj10;
		animReloj[11]=reloj11;
		animReloj[12]=reloj12;
		animReloj[13]=reloj13;
		animReloj[14]=reloj14;
		animReloj[15]=reloj15;
		animReloj[16]=reloj16;
		animReloj[17]=reloj17;
		animReloj[18]=reloj18;
		animReloj[19]=reloj19;
		animReloj[20]=reloj20;
		animReloj[21]=reloj21;
		animReloj[22]=reloj22;
		animReloj[23]=reloj23;
		animReloj[24]=reloj24;
		animReloj[25]=reloj25;
		
	}
	
	/**
	 * Método relleno imágenes números y letras.
	 */
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
     * @param entidad -> 1 si es un keko, cualquier otro número si no lo es
     * @param ejeX
     * @param ejeY
     * @param prevY
     * @return false -> si sigue cayendo | true -> si se ha posado sobre una plataforma
     */
    public boolean isGround(int entidad,int ejeX,int ejeY,int prevY,int prevX){

    	/*
    	 * QUEDAN CAMBIOS POR HACER Y AJUSTAR. LUEGO HAY QUE COMENTARLO
    	 */
    	
    	boolean foundPlatform=false;
        int y,z,g,k;
        boolean colision=false;

        for(int x=0;x<listaPlataformas.size();x++){

        	if(!foundPlatform) {

        		z=listaPlataformas.get(x).getEjeX()+prevX-ejeX;
        		y=listaPlataformas.get(x).getEjeY();
        		g=listaPlataformas.get(x).getAncho();
        		
        		switch(entidad) {
        		
        		case 1://Nuestro keko:
        			
        			if(350+30>=z&&350+15<=(z+g)) {

            			if(720-ejeY+30>=y&&prevY<y){

            				colision=true;

            				foundPlatform=true;

            				this.y=y;

            			}

            		}
        			
       			break;
        			
        		case 2://Los pom:
        			
        			if(ejeX+35>=z&&ejeX+20<=(z+g)) {

            			if(ejeY+49>=y&&prevY<y){

            				colision=true;

            				foundPlatform=true;

            				this.y=y;

            			}

            		}
        			
        			default:;
        			
        		}

        	}

        }

        return colision;
        
/*			if(350+30>=z&&350+15<=(z+g)) {

    			if(720-ejeY+30>=y&&prevY<y){

    				colision=true;

    				foundPlatform=true;

    				this.y=y;

    			}

    		}*/

    }

    /**
     * Método para comprobar si hay choque contra una pared y a qué distancia está.
     * @param id
     * @return Distancia que puedes mover sin colisionar.
     */
    public int isWall(int id,int ejeX,int ejeYCabeza,int ejeYPies,int anchoDerecha,int anchoIzquierda) {
    	
        int y,z,g,k,s=id;
        int colision=id;

        for(int x=0;x<listaPlataformas.size();x++){
        	
        	if(listaPlataformas.get(x).getTipo()==Tipo.BOTH) {
	            
        		z=listaPlataformas.get(x).getEjeX();
	            y=listaPlataformas.get(x).getEjeY();
	            g=listaPlataformas.get(x).getAncho();
	            k=listaPlataformas.get(x).getAlto();
	
	            if((ejeX+anchoDerecha+s>=z-5&&ejeX+s<=(z+g-15))) {
	            	
	            	//if(((720-this.y)>y)&&((posYIniKeko-this.y)<(y+k))){
            		if(((ejeYPies)>y)&&((ejeYCabeza)<(y+k))){
	            	
		            	if(s>0) {
		            		
		            		 colision-=ejeX+anchoDerecha-(z-s-15);
		            		
		            	}
		            	else {
		            		
		            		 colision-=ejeX-anchoIzquierda-(z+g-s-15);
		            		
		            	}
	            	
	            	}
		            
	        	}
        		
        	}
        	
        }
        
        return colision;
    	
    }
    
    /**
     * Método para comprobar las colisiones hacia arriba (cuando se salta y se golpea con la cabeza).
     * @param newCabezaPos
     * @param prevY
     * @param ejeX
     * @param prevX
     * @return Distancia que puedes mover en vertical sin golpearte contra nada.
     */
    public int isTop(int newCabezaPos,int prevY,int ejeX,int prevX) {
    	
    	int y,z,g,k;
        int colision=newCabezaPos;

        for(int x=0;x<listaPlataformas.size();x++){
        	
	    	if(listaPlataformas.get(x).getTipo()==Tipo.BOTH) {
	
	    	z=listaPlataformas.get(x).getEjeX()+prevX-ejeX;
	    	y=listaPlataformas.get(x).getEjeY();
	    	g=listaPlataformas.get(x).getAncho();
	    	k=listaPlataformas.get(x).getAlto();
		
		    	if(350+30>=z&&350+15<=(z+g)) {
		
		    		if(posYIniKeko-prevY>=y+k&&posYIniKeko-prevY-newCabezaPos<y+k){
		
		    			colision=(posYIniKeko-prevY)-(y+k);
		
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
		g.fillRect(0, 0, 1000, 1000);

		//Imagen móvil de fondo
		for (int ñ = -350; ñ < 2975; ñ += 750) {

			g.drawImage(fondo, ñ - (x / 4), 0, 750, 770, this);

		}
		//Imagen móvil de las nubes
		g.drawImage(nube1, 500 - (x / 2), 200, 100, 50, this);
		g.drawImage(nube2, 700 - (x / 3), 100, 50, 30, this);
		g.drawImage(nube1, 1200 - (x / 2), 75, 100, 50, this);
		g.drawImage(nube2, 1500 - (x / 3), 100, 50, 30, this);
		g.drawImage(nube1, 1800 - (x / 2), 150, 100, 50, this);
		g.drawImage(nube2, 2500 - (x / 3), 180, 50, 30, this);
		g.drawImage(nube1, 3230 - (x / 2), 60, 100, 50, this);
		g.drawImage(nube2, 3950 - (x / 3), 100, 50, 30, this);
		g.drawImage(nube1, 4500 - (x / 2), 55, 100, 50, this);
		g.drawImage(nube1, 3800 - (x / 2), 80, 100, 50, this);
		g.drawImage(nube1, 2000 - (x / 2), 120, 100, 50, this);

		//Plataforma suelo
		g.setColor(Color.green);
		g.fillRect(0 - x, 200, 50, 370);
		addPlatformToList(0 - x, 200, 50, 370, Tipo.BOTH);

		//Nuestros bloques de suelo
		for (int o = -350; o < 10350; o += 90) {
			if (o % 20 == 0) {
				g.drawImage(suelo2, o - x, 710, 90, 92, this);
			} else {
				g.drawImage(suelo1, o - x, 710, 90, 92, this);
			}
		}
		//Colisión del suelo:
		addPlatformToList(-350 - x, 720, 10350, 50, Tipo.BOTH);

		//Plataformas para saltar
		g.drawImage(plataforma1, 700 - x, 300, 100, 35, this);
		addPlatformToList(700 - x, 300, 100, 35, Tipo.PLATFORM);

		g.drawImage(plataforma1, 700 - x, 650, 100, 35, this);
		addPlatformToList(700 - x, 650, 100, 35, Tipo.PLATFORM);

		g.drawImage(plataforma2, 900 - x, 150, 75, 25, this);
		addPlatformToList(900 - x, 150, 75, 25, Tipo.PLATFORM);

		g.drawImage(plataforma1, 450 - x, 450, 100, 35, this);
		addPlatformToList(450 - x, 450, 100, 35, Tipo.PLATFORM);

		g.drawImage(plataforma1, 160 - x, 600, 100, 35, this);
		addPlatformToList(160 - x, 600, 100, 35, Tipo.PLATFORM);

		//PRUEBAS MONSTRUO:++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//Pom 1:
		if(!Enemies_lvl1.enemies.get(0).isDead()) {
			g.drawImage(animMonstruo[arrPosMonstruo], posXIniEnemy1_1 - x + m11, posYIniEnemy1_1, 70, 50, this);
		}
		//Pom 2:
		if(!Enemies_lvl1.enemies.get(1).isDead()) {
			g.drawImage(animMonstruo[arrPosMonstruo], posXIniEnemy1_2 - x + m12, posYIniEnemy1_2, 70, 50, this);
		}
		//Nuestro keko
		g.drawImage(animKeko[arrPosKeko], posXIniKeko, posYIniKeko-y, 50, 90, this);

		
		//Árboles colisionables:
		g.drawImage(arbol1,918-x,355,200,370,this);
		addPlatformToList(1000 - x, 400, 35, 330, Tipo.BOTH);
		
		//Árboles no colisionables:
		g.drawImage(arbusto,500-x,660,70,70,this);

		//Imagen del hud
		g.drawImage(hud, 0, 698, 1010, 350, this);
		//Aquí habrá que programar las reproducciones las barras de vida
		g.drawImage(animBarra[arrPosBarra], 10, 790, 300, 160, this);
		//Aquí habrá que programar las reproducciones de los corazones
		g.drawImage(animCorazones[arrPosCor], 725, 835, 250, 75, this);
		//Aquí habrá que programar las reproducciones de los corazones
		g.drawImage(animReloj[arrPosReloj], 475, 820, 75, 100, this);
		
		//Pantalla de pausa
		if(pause) {
			g.drawImage(pausa,0,0,1000,1000,this);
		}
	}

}

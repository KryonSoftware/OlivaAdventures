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
 * Ciertas lógicas se hacen aquí. Es arriesgado dado que esta clase va en su propio hilo por ser un JPanel, pero funciona bien.
 * @author kryon
 *
 */
@SuppressWarnings("serial")
public class PanelLVL1 extends JPanel {

	private ArrayList<Platform> listaPlataformas=new ArrayList<>();

	//Imágenes
	private BufferedImage fondo,keko_right,keko_right2,keko_right3,keko_right4,keko_stand_right,keko_stand_right2,keko_stand_right3,
	keko_stand_right4,keko_left,keko_left2,keko_left3,keko_left4,keko_stand_left,keko_stand_left2,keko_stand_left3,keko_stand_left4,hud,barraExp100,barraExp75,
	barraExp50,barraExp25,barraExp0,vidas0,vidas1,vidas2,vidas3,monstruo1,monstruo2,monstruo3,monstruo4,plataforma1,plataforma2,
	reloj0,reloj1,reloj2,reloj3,reloj4,reloj5,reloj6,reloj7,reloj8,reloj9,reloj10,reloj11,reloj12,reloj13,reloj14,reloj15,reloj16,reloj17,reloj18,
	reloj19,reloj20,reloj21,reloj22,reloj23,reloj24,reloj25,nube1,nube2,suelo1,suelo2,arbol1,arbol2,arbusto,pausa,cargando;
	
	//Arrays de imágenes
	private BufferedImage[] animKeko = new BufferedImage[16],animBarra=new BufferedImage[5],animMonstruo=new BufferedImage[4], animCorazones=new BufferedImage[4],
			animReloj=new BufferedImage[26];
	
    private int x,y,arrPosKeko=2,arrPosBarra=4,arrPosReloj=0,arrPosMonstruo=0,avanceDisparo,alturaDisparo;
    
    private char direccionDisparo='D';
    
    private long momentoDisparo=0;
    
    private boolean pause=false,disparo,disparado=false,impacto=false,loading=false;
    
    //Instanciamos el player:
    public Player keko = new Player(350,720-89);
    
    //Getters y Setters
    public boolean isPause() {return pause;}

	public void setPause(boolean pause) {this.pause = pause;}

	public boolean isDisparo() {return disparo;}

	public void setDisparo(boolean disparo) {this.disparo = disparo;}

	public long getMomentoDisparo() {return momentoDisparo;}

	public void setMomentoDisparo(long contador) {this.momentoDisparo = contador;}

	public int getArrPosMonstruo() {return arrPosMonstruo;}

	public void setArrPosMonstruo(int arrPosMonstruo) {this.arrPosMonstruo = arrPosMonstruo;}

	public boolean isLoading() {return loading;}

	public void setLoading(boolean loading) {this.loading = loading;}

	public int getArrPosReloj() {return arrPosReloj;}

	public void setArrPosReloj(int arrPosReloj) {this.arrPosReloj = arrPosReloj;}

	public int getArrPosBarra() {return arrPosBarra;}

	public void setArrPosBarra(int arrPosBarra) {this.arrPosBarra = arrPosBarra;}

	public int getArrPosKeko() {return arrPosKeko;}

	public void setArrPosKeko(int arrPosKeko) {this.arrPosKeko = arrPosKeko;}

	public void setEjeX(int x){this.x=x;}

    public void setEjeY(int y){this.y=y;}
    
    public boolean isDisparado() {return disparado;}

	public void setDisparado(boolean disparado) {this.disparado = disparado;}

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
			cargando=ImageIO.read(new File("resources/media/Menú/OlivaAdventuresLibro.png"));
			
          
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
    public void addPlatformToList(int ejeX,int ejeY,int ancho,int alto,Tipo tipo){

        listaPlataformas.add(new Platform(ejeX,ejeY,ancho,alto,tipo));

    }

    /**
     * Método que nos indica mediante un boolean si los ejes que le hemos pasado por parámetro están sobre la superficie de una plataforma o la ha atravesado.
     * @param entidad -> 1 si es un keko, cualquier otro número si no lo es
     * @param ejeX
     * @param ejeY
     * @param prevY
     * @return false -> si sigue cayendo | true -> si se ha posado sobre una plataforma
     */
    public boolean isGround(int entidad,int posLista,int ejeX,int ejeY,int prevY,int prevX){
    	
    	boolean foundPlatform=false;
        int y,z,g;
        boolean colision=false;

        for(int x=0;x<listaPlataformas.size();x++){
        	
        	if(listaPlataformas.get(x).getTipo()==Tipo.PLATFORM || listaPlataformas.get(x).getTipo()==Tipo.BOTH) {

        		//Le indicamos que si previamente ya ha encontrado algo contra lo que está chocando que deje de mirar apra mejorar el rendimiento
	        	if(!foundPlatform) {
	
	        		z=listaPlataformas.get(x).getEjeX()+prevX-ejeX;
	        		y=listaPlataformas.get(x).getEjeY();
	        		g=listaPlataformas.get(x).getAncho();
	        		
	        		switch(entidad) {
	        		
	        		case 1://Nuestro keko:
	        			
	        			//Comprobamos si estamos en el mismo eje X
	        			if(350+30>=z&&350+15<=(z+g)) {
	
	        				//Comprobamos si estamos a la misma altura
	            			if(720-ejeY+30>=y&&prevY<y){
	
	            				colision=true;
	
	            				foundPlatform=true;
	
	            				this.y=y;
	
	            			}
	
	            		}
	        			
	       			break;
	        			
	        		case 2://Los pow:
	        			
	        			if(!(z==ejeX) || !(listaPlataformas.get(x).getTipo()==Tipo.ENEMY)) {
	        				
	        				if(!(y==ejeY-49) || !(listaPlataformas.get(x).getTipo()==Tipo.ENEMY)) {
	        				
			    				if(ejeX+25>=z&&ejeX+40<=(z+g)) {
			
			            			if(ejeY+49>=y&&prevY<y){
			
			            				colision=true;
			
			            				foundPlatform=true;
			            				
			            				//AQUÍ HAY QUE REASIGNAR LA Y DEL MONSTRUO QUE TOQUE,AHORA REASIGNA LA DEL KEKO
			            				if(listaPlataformas.get(x).getTipo()==Tipo.ENEMY) {
			            					Entities.enemies.get(posLista).setPosYEnemy(y-51);
			            				}
			            				else {
			            					Entities.enemies.get(posLista).setPosYEnemy(y-49);
			            				}
			
			            			}
			
			    				}
			    				
	        				}
		    				
	        			}
					break;
	        			
	        			//SIGUIENTES MONSTRUOS POR IMPLEMENTAR
	        			
	        			default:;
	        			
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
    public int isWall(int entidad,int posLista,int intentoMovimiento,int ejeX,int ejeYCabeza,int ejeYPies,int anchoDerecha,int anchoIzquierda) {
    	
    	boolean foundWall=false;
    	int y,z,g,k,s=intentoMovimiento;
    	int colision=intentoMovimiento;

    	for(int x=0;x<listaPlataformas.size();x++){

    		if(listaPlataformas.get(x).getTipo()==Tipo.BOTH || listaPlataformas.get(x).getTipo()==Tipo.ENEMY || listaPlataformas.get(x).getTipo()==Tipo.BALA) {

    			if(!foundWall) {
	    			
	    			z=listaPlataformas.get(x).getEjeX();
	    			y=listaPlataformas.get(x).getEjeY();
	    			g=listaPlataformas.get(x).getAncho();
	    			k=listaPlataformas.get(x).getAlto();
	    			
	    			switch(entidad) {
	    			
	    			case 1: //Caso keko:
	
	    				if(!(listaPlataformas.get(x).getTipo()==Tipo.ENEMY)) {
	    					
	    					if(!(listaPlataformas.get(x).getTipo()==Tipo.BALA)) {
	
		    					if((ejeX+anchoDerecha+s>=z-15&&ejeX+s<=(z+g-15))) {
		    						
		    						if(((ejeYPies)>y)&&((ejeYCabeza)<(y+k))){
		    							
		    							//Si mueve a derecha o izquierda
		    							if(s>0) {
	        								
		    								//Si está situado a derecha o izquierda del obstáculo (así evitamos algunos bugs durante caídas)
	        								if(ejeX<z) {
	        									
	        									//Lo recolocamos pegado al obstáculo
	        									colision-=ejeX+anchoDerecha-(z-s-18);
	        									foundWall=true;
	        									
	        								}
	        								else {
	        									
	        									colision-=ejeX-anchoIzquierda-(z+g-s-15);
	        									foundWall=true;
	        									
	        								}
	        								
	        							}
	        							else {
	        								
	        								if(ejeX>z) {
	        									
	        									colision-=ejeX-anchoIzquierda-(z+g-s-15);
	        									foundWall=true;
	        									
	        								}
	        								else {
	        									
	        									colision-=ejeX+anchoDerecha-(z-s-18);
	        									foundWall=true;
	        									
	        								}
	        								
	        							}
		
		    						}
		
		    					}
	    					
	    					 }
	
	    				}
	    				
	    				break;
	    				
	    			case 2: //Caso pow:
	    				
	    				if(!(listaPlataformas.get(x).getTipo()==Tipo.BALA)) {
		    				
		    				if(!(z==ejeX) ||  !(listaPlataformas.get(x).getTipo()==Tipo.ENEMY)) {
		
		        				if(!(y==ejeYCabeza && z==ejeX) || !(listaPlataformas.get(x).getTipo()==Tipo.ENEMY)) {
	        							
	    							if((ejeX+anchoDerecha+s>=z-35&&ejeX+s<=(z+g-15))) {
	    								
		        						if(((ejeYPies)>y)&&((ejeYCabeza)<(y+k))){
		        							
		        							//TODO aquí le especificaremos que si está chocando contra el keko, que le quite vida
		        							
		        							if(s>0) {
		        								
		        								if(ejeX<z) {
		        									
		        									colision-=ejeX+anchoIzquierda-(z-s-10);
		        									foundWall=true;
		        									
		        								}
		        								else {
		        									
		        									colision-=ejeX-anchoDerecha-(z+g-s-30);
		        									foundWall=true;
		        									
		        								}
		        								
		        							}
		        							else {
		        								
		        								if(ejeX>z) {
		        									
		        									colision-=ejeX-anchoDerecha-(z+g-s-30);
		        									foundWall=true;
		        									
		        								}
		        								else {
		        									
		        									colision-=ejeX+anchoIzquierda-(z-s-10);
		        									foundWall=true;
		        									
		        								}
		        								
		        							}
			
		        						}
		
		        					}
		
		        				}
		
		        			}
		    				
	    				}
	    				
	    				//Evitamos que colisione contra enemigos cuando está en el aire
	    				if((listaPlataformas.get(x).getTipo()==Tipo.ENEMY)) {
	    					if(Entities.enemies.get(posLista).isJumping()) {
	    						colision=intentoMovimiento;
	    					}
	    				}
	    				
	    				break;
	    				
	    			case 42: //Caso bala:
	    				
	    				if(listaPlataformas.get(x).getTipo()==Tipo.ENEMY || listaPlataformas.get(x).getTipo()==Tipo.BOTH) {
	    					
	    					if(!(listaPlataformas.get(x).getTipo()==Tipo.PLAYER)) {
	    						
	    						if(!(listaPlataformas.get(x).getTipo()==Tipo.BALA)) {
		    						
			    					if(((ejeX+anchoDerecha+s>=z) && (ejeX+s<=(z+g))) || (((ejeX+s<=z) && (ejeX+s>z)) && s>0) || ((ejeX+s>=z+g) && (ejeX+s<=z+g)) && s<0) {
			
			    						if(((ejeYPies)>y)&&((ejeYCabeza)<(y+k))){
			
			    							switch(listaPlataformas.get(x).getTipo()) {
			    							
			    							case BOTH:
			    								colision-= s>0 ? ejeX-(z-s) : ejeX-anchoIzquierda-(z+g-s);
			    								foundWall=true;
			    								impacto=true;
			    								
			    								break;
			    								
			    							case ENEMY:
			    								
			    								//TODO le indicaremos que en este caso le quite vida a dicho enemigo
			    								colision-= s>0 ? ejeX-(z-s) : ejeX-anchoIzquierda-(z+g-s);
			    								foundWall=true;
			    								impacto=true;
			    								
			    								break;
			    								
											default:;
											
			    							}
			    							
			    						}
			    						
			    					}
		    					
	    						}
	    					
	    					}
	    					
	    				}
	    				
	    				break;
	    			
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
    public int isTop(int entidad,int posLista,int newCabezaPos,int prevY,int ejeX,int prevX) {
    	
    	boolean foundTop=false;
    	int y,z,g,k;
        int colision=0;
        
        switch(entidad) {
        case 1:
        	colision=newCabezaPos;
        	break;
        default:
        	colision=prevY-newCabezaPos;
        	break;
        }

        for(int x=0;x<listaPlataformas.size();x++){
        	
        	if(!foundTop) {
        	
		    	if(listaPlataformas.get(x).getTipo()==Tipo.BOTH) {
		
		    	z=listaPlataformas.get(x).getEjeX()+prevX-ejeX;
		    	y=listaPlataformas.get(x).getEjeY();
		    	g=listaPlataformas.get(x).getAncho();
		    	k=listaPlataformas.get(x).getAlto();
		    	
			    	switch(entidad) {
			    	
			    	case 1:
				
				    	if(keko.getPosXPlayer()+30>=z&&keko.getPosXPlayer()+15<=(z+g)) {
				
				    		if(keko.getPosYPlayer()-this.y>=y+k&&keko.getPosYPlayer()-prevY-newCabezaPos<y+k){
				
				    			colision=(keko.getPosYPlayer()-prevY)-(y+k);
				    			foundTop=true;
				
				    		}
				
				    	}
				    	
				    	break;
				    	
			    	case 2:
			    		
			    		if(Entities.enemies.get(posLista).getPosXEnemy()-this.x + Entities.enemies.get(posLista).getMoveEnemy()+50>=z&&Entities.enemies.get(posLista).getPosXEnemy()-this.x + Entities.enemies.get(posLista).getMoveEnemy()+20<=(z+g)) {
			    			
				    		if(prevY>=y+k&&newCabezaPos<=y+k){
				
				    			colision=(prevY)-(y+k);
				    			foundTop=true;
				
				    		}
				
				    	}
				    	
			    		
				    	break;
				    	
			    	}
		        	
	        	}
	    	
        	}

        }
        
        return colision;
    }
    
    /**
     * Método para desplazar la bala y llamar a isWall para comprobar sus colisiones.
     * @param g
     * @param disparo
     * @param x
     */
    private void movimientoBala(Graphics g,boolean disparo,int x) {
    	
    	//Variables de control de estado del disparo/bala
    	if(disparo) {
    		
    		if(!disparado) {
	    		 //TODO Hay que sustituir el cuadrado rojo por la array de imágenes de la bala
    			
    			//si miraba hacia un lado u otro al disparar
	    		switch(keko.getLastSide()) {
	    		
	    		case 'D':
	    			g.setColor(Color.red);
	        		g.fillRect(keko.getPosXPlayer()+30,keko.getPosYPlayer()+50-y,10,10);
	        		addPlatformToList(keko.getPosXPlayer()+30,keko.getPosYPlayer()+50-y,10,0, Tipo.BALA);
	        		direccionDisparo='D';
	    			break;
	    		case 'I':
	    			g.setColor(Color.red);
	        		g.fillRect(keko.getPosXPlayer()-10,keko.getPosYPlayer()+50-y,10,10);
	        		addPlatformToList(keko.getPosXPlayer()-10,keko.getPosYPlayer()+50-y,10,0, Tipo.BALA);
	        		direccionDisparo='I';
	    			break;
	    			default:;
	    		
	    		}
	    		//Guardamos la altura a la que fue disparada
	    		alturaDisparo=y;
	    		avanceDisparo=0;
	    		//No volveremos a entrar en el apartado de generar la bala
	    		impacto=false;
	    		disparado=true;
    		}
    		else {
    			
    			//Mientras no choque
    			if(!impacto) {
	    			
	    			switch(direccionDisparo) {
	    			
	    			case 'D':
	    				avanceDisparo+=isWall(42,1,30,keko.getPosXPlayer()+20+avanceDisparo,keko.getPosYPlayer()+50-alturaDisparo,
	    						keko.getPosYPlayer()+50-alturaDisparo+20,10,0);
	    				g.setColor(Color.red);
		        		g.fillRect(keko.getPosXPlayer()+20+avanceDisparo,keko.getPosYPlayer()+50-alturaDisparo,10,10);
		        		addPlatformToList(keko.getPosXPlayer()+20+avanceDisparo,keko.getPosYPlayer()+50-alturaDisparo,10,0, Tipo.BALA);
	    				break;
	    			case'I':
	    				avanceDisparo+=isWall(42,1,-30,keko.getPosXPlayer()-10+avanceDisparo,keko.getPosYPlayer()+50-alturaDisparo,
	    						keko.getPosYPlayer()+50-alturaDisparo+20,10,0);
	    				g.setColor(Color.red);
		        		g.fillRect(keko.getPosXPlayer()-20+avanceDisparo,keko.getPosYPlayer()+50-alturaDisparo,10,10);
		        		addPlatformToList(keko.getPosXPlayer()+20+avanceDisparo,keko.getPosYPlayer()+50-alturaDisparo,10,0, Tipo.BALA);
	    				break;
	    			
	    			}
    			
    			}
    			
    		}
    		
    	}
    	
    }
    /**
     * Método para pintar nuestro panel
     */
    public void paint(Graphics g) {
    	
    	//Si estamos cargando no refresques
    	if(!loading) {

		//Borramos nuestras plataformas, ya que puede que ahora se vayan a mover
		listaPlataformas.clear();

		//Fondo por ahora de por si las moscas

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
		addPlatformToList(-350 - x, 720, 10700, 50, Tipo.BOTH);

		//Plataformas para saltar
		g.drawImage(plataforma1, 700 - x, 300, 100, 35, this);
		addPlatformToList(700 - x, 300, 100, 35, Tipo.PLATFORM);

		g.drawImage(plataforma1, 700 - x, 650, 100, 35, this);
		addPlatformToList(700 - x, 650, 100, 35, Tipo.PLATFORM);

		g.drawImage(plataforma2, 900 - x, 150, 75, 25, this);
		addPlatformToList(900 - x, 150, 75, 25, Tipo.PLATFORM);

		g.drawImage(plataforma1, 550 - x, 450, 100, 35, this);
		addPlatformToList(550 - x, 450, 100, 35, Tipo.PLATFORM);

		g.drawImage(plataforma1, 400 - x, 600, 100, 35, this);
		addPlatformToList(400 - x, 600, 100, 35, Tipo.PLATFORM);

		//PRUEBAS MONSTRUO:++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//Pows:
		
		for(int x=0;x<Entities.enemies.size();x++) {
		
			if(!Entities.enemies.get(x).isDead()) {
				g.drawImage(animMonstruo[arrPosMonstruo], Entities.enemies.get(x).getPosXEnemy() -this.x + Entities.enemies.get(x).getMoveEnemy(),
						Entities.enemies.get(x).getPosYEnemy(), 70, 50, this);
				addPlatformToList(Entities.enemies.get(x).getPosXEnemy() - this.x + Entities.enemies.get(x).getMoveEnemy(),
						Entities.enemies.get(x).getPosYEnemy(), 70, 50, Tipo.ENEMY);
			}
			
		}
		
		//Nuestro keko
		g.drawImage(animKeko[arrPosKeko], keko.getPosXPlayer(), keko.getPosYPlayer()-y, 50, 90, this);
		addPlatformToList( keko.getPosXPlayer(), keko.getPosYPlayer()-y, 49, 89,Tipo.PLAYER);
		
		//Árboles colisionables:
		g.drawImage(arbol1,918-x,355,200,370,this);
		addPlatformToList(1000 - x, 400, 35, 330, Tipo.BOTH);
		
		//g.drawImage(arbol1,192-x,355,200,370,this);
		//addPlatformToList(279 - x, 400, 35, 330, Tipo.BOTH);
		
		//Importante que sea lo último para poder ver y colisionar con todo
		movimientoBala(g, disparo,x);
		
		//Árboles no colisionables:
		g.drawImage(arbusto,500-x,660,70,70,this);

		//Imagen del hud
		g.drawImage(hud, 0, 698, 1010, 350, this);
		//Aquí habrá que programar las reproducciones las barras de vida
		g.drawImage(animBarra[arrPosBarra], 10, 790, 300, 160, this);
		//Aquí habrá que programar las reproducciones de los corazones
		g.drawImage(animCorazones[keko.getLives()], 725, 835, 250, 75, this);
		//Aquí habrá que programar las reproducciones de los corazones
		g.drawImage(animReloj[arrPosReloj], 475, 820, 75, 100, this);
		
		//Pantalla de pausa
		if(pause) {
			g.drawImage(pausa,0,0,1000,1000,this);
		}
		
    	}
    	else {
    		g.drawImage(cargando,0,0,1000,1000,this);
    	}
	}

}

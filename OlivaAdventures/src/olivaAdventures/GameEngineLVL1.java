package olivaAdventures;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * Clase de motor del juego. Físicas.
 * 
 * @author kryon
 */
public class GameEngineLVL1 implements KeyListener {

    private boolean saltando=false,arriba=false,derecha=false,izquierda=false,pausa=false,gatillo=false,gameOver=false,pidiendoNombre=false;
    private long contador=0;
    private int ejeX=0,ejeY=0,prevY=720-89,prevX=0,puntuacion=360,segundosCambiarImagenTiempo=this.puntuacion/25,contarSegundosCambiarImagenTiempo;
	private byte contadorSalto=0,cambio=0,respirando=0,compruebaDistanciaSalto=0,animacionesDe8=0,anMons=0,letraOK=0,impulso=0,anDisp=26;
	private String nombre="XXX";
	public JFrame ventana;
    
    //Inicializamos el panel que va dentro del Frame:
    private PanelLVL1 panel = new PanelLVL1();

	//La música que usaremos:
	private Musica musica= new Musica(),fxKeko=new Musica(),disparo=new Musica(),fxPow=new Musica(),fxZubat=new Musica(),fxEn2=new Musica(),fxBoss=new Musica();
    
	//Getter del nombre para que lo recoja PlantillaVentana:
	public String getNombre() {return nombre;}

/**
    * Constructor de la clase. Le pasamos el JFrame que deberá usar el juego.
    * El constructor simplemente se encarga de meter el panel en el frame que se le ha pasado y cargar sus listeners.
    * @param frame  JFrame del menú que usará el juego.
    */
    public GameEngineLVL1(JFrame ventana){

    	this.ventana=ventana;
    	//Valores del frame:
        this.ventana.setSize(1000,1000);
        this.ventana.setResizable(false);
        this.ventana.addKeyListener(this);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLocationRelativeTo(null);

        //Añadimos el panel que instanciamos antes:
        this.ventana.add(panel);

        //Dejamos el frame visible al final para evitar problemas:
        ventana.setVisible(true);

    }

    /**
     * Sin usar.
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {/*HOLA ME LLAMO MÉTODO TYPED Y SOY UN VAGO*/}

    /**
     * Estos eventos los usamos para activar las booleanas de presión de tecla. Gracias a esto podemos tener varias teclas pulsadas a la vez y controlamos
     *  el efecto "pulso hasta el día del alzamiento de Skynet"
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {

    	//Mientras la partida continúe:
    	if(!gameOver) {
    		
    		switch(keyEvent.getKeyCode()){

            case KeyEvent.VK_D:
                derecha=true;
                break;
            case KeyEvent.VK_A:
                izquierda=true;
                break;
            case KeyEvent.VK_W:
                arriba=true;
                break;               
            case KeyEvent.VK_SPACE: //Caso de disparar
            	gatillo=true;
            	break;
            case KeyEvent.VK_ESCAPE:
            	if(pausa) {
            		pausa=false;
            	}
            	else {
            		pausa=true;
            	}
            	break;
            default:;
    		}
    		
    	}
    	//Cuando nos pida el nombre para la puntuación:
    	else {
    		
    		panel.setLetra(keyEvent.getKeyCode());
    		try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Error deteniendo el hilo para dar tiempo a cargar la imagen de la letra seleccionada "
						+ "(eligiendo letras y repintando desde los listeners de GameEngine)/n/n Log: "+e);
			}
    		panel.repaint();
    		
    		if(keyEvent.getKeyCode()==KeyEvent.VK_ENTER) {
    			letraOK++;
    			if(letraOK>2) {
    				pidiendoNombre=false;
    			}
    		}
    		
    	}

    }

    /**
     * Usamos estos eventos para desactivar las booleanas de presión de teclas.
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    	if(!gameOver) {
    	
    		switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_D:
                derecha=false;
                break;
            case KeyEvent.VK_A:
                izquierda=false;
                break;
            case KeyEvent.VK_W:
                arriba=false;
                break;
            case KeyEvent.VK_SPACE:
            	gatillo=false;
            default:;
            }
    		
    	}
    	
    	cambio=0;
    }
    
    /**
     * Método para leer las booleanas y enemyType1_1 función de su estado, alterar los movimientos de nuestro personaje
     * (sus coordenadas de colocación) ANTES de repintarlo enemyType1_1 la pantalla.
     */
    private void PJMove() {
    	
    	//Si no pulsa nada o lo pulsa todo a la vez:
    	if(((!derecha && !izquierda) || (derecha && izquierda)) && !arriba) {
    		
	    	switch(panel.keko.getLastSide()) {
	    	
	    	case 'D':
	    		
	    		if(!saltando) {
	    			
	    			if(anDisp==26) {
	    				//Creamos secuencia de la animación de la respiración teniendo en cuenta el último lado hacia el que miraba
		    			if(respirando==0) {
		    				
		    				switch(panel.getArrPosKeko()) {
		    				
		    				case 8:
		    					panel.setArrPosKeko(9);
		    					break;
		    				case 9:
		    					panel.setArrPosKeko(10);
		    					break;
		    				case 10:
		    					panel.setArrPosKeko(11);
		    					break;
		    				case 11:
		    					panel.setArrPosKeko(8);
		    					break;
		    					default:
		    						panel.setArrPosKeko(8);
		    						
		    				}
		    				respirando++;
		    			} else if(respirando==1){respirando=2;} //Lo que hacemos es hacer girar un contador al paso de las iteraciones 
		    			else if(respirando==2){respirando=3;} //para dar un tiempo extra entre cada imagen de la animación
		    			else {respirando=0;}
	    			}
	    		}
	    		else {
	    			if(anDisp==26) {
	    				panel.setArrPosKeko(17);
	    			}
	    		}
	    		break;
	    	case 'I':
	    		
	    		if(!saltando) {
	    			
	    			if(anDisp==26) {
	    			
		    			if(respirando==0) {
		    				
		    				switch(panel.getArrPosKeko()) {
		    				
		    				case 12:
		    					panel.setArrPosKeko(13);
		    					break;
		    				case 13:
		    					panel.setArrPosKeko(14);
		    					break;
		    				case 14:
		    					panel.setArrPosKeko(15);
		    					break;
		    				case 15:
		    					panel.setArrPosKeko(12);
		    					break;
		    					default:
		    						panel.setArrPosKeko(12);
		    						
		    				}
		    				respirando++;
		    			} else if(respirando==1){respirando=2;}
		    			else if(respirando==2){respirando=3;}
		    			else {respirando=0;}
	    			}
	    		}
	    		else {
	    			if(anDisp==26) {
	    				panel.setArrPosKeko(16);
	    			}
	    		}
	    		break;
			default:;
	    	}
	    		
	    	if(!saltando) {
	    		saltando=true;
	    		contadorSalto=16;
	    	}
    	}
    	
    	//Guardamos su posición lateral antes de volver a moverse
    	prevX=ejeX;
    	
    	//Cuando la tecla arriba esté presionada:
    	if(arriba) {
    		//Si no está saltando ya:
    		 if(!saltando) {
                 ejeY+=40;
                 saltar();
             }
    	}
    	//Si la tecla izquierda está presionada:
    	if(izquierda && !derecha) {
    		
    		if(!saltando) {
    			
    			if(anDisp==26) {
    			
	    			if(cambio==0) {
	    				
	    				switch(panel.getArrPosKeko()) {
	    				
	    				case 4:
	    					panel.setArrPosKeko(5);
	    					break;
	    				case 5:
	    					panel.setArrPosKeko(6);
	    					break;
	    				case 6:
	    					panel.setArrPosKeko(7);
	    					break;
	    				case 7:
	    					panel.setArrPosKeko(4);
	    					break;
	    					default:
	    						panel.setArrPosKeko(5);
	    						
	    				}
	    				cambio++;
	    			} else if(cambio==1){cambio=0;}
	    			else {cambio=0;}
	    			
	    			panel.keko.setLastSide('I');
	    			
	    			//Nos desplazamos en el eje X y a continuación comprobamos si deberíamos estar cayendo:
	            	ejeX+=panel.isWall(1,1,-10,panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-panel.getEjeY(),panel.keko.getPosYPlayer()+89-panel.getEjeY(),30,10);
	            	//Estas dos líneas de código deberán implementarse otra vez cuando hagamos el suelo desaparecer bajo sus pies:
	                contadorSalto = 16;
	                saltando = true;
    			}
            }
    		else {
            	//Si está saltando hacemos que vaya reduciendo su avance lateral cuando esté cayendo, pero no cuando esté subiendo (aún mantiene su impulso):
            	if(contadorSalto>17 && contadorSalto<20) {
            		ejeX-=-panel.isWall(1,1,-26,panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-panel.getEjeY(),panel.keko.getPosYPlayer()+89-panel.getEjeY(),30,10)-contadorSalto;
            	}
            	else if(contadorSalto>19) {
            		ejeX-=-panel.isWall(1,1,-6,panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-panel.getEjeY(),panel.keko.getPosYPlayer()+89-panel.getEjeY(),30,10);
            	}
            	else {
            		ejeX-=-panel.isWall(1,1,-10,panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-panel.getEjeY(),panel.keko.getPosYPlayer()+89-panel.getEjeY(),30,10);
            	}
            	panel.keko.setLastSide('I');
            	if(anDisp==26) {
            		panel.setArrPosKeko(16);
            	}
            }
    		
    	}
	    if(derecha && !izquierda) {
	    	
    		if(!saltando) {
    			
    			if(anDisp==26) {
    			
	    			if(cambio==0) {
	    				
	    				switch(panel.getArrPosKeko()) {
	    				
	    				case 0:
	    					panel.setArrPosKeko(1);
	    					break;
	    				case 1:
	    					panel.setArrPosKeko(2);
	    					break;
	    				case 2:
	    					panel.setArrPosKeko(3);
	    					break;
	    				case 3:
	    					panel.setArrPosKeko(0);
	    					break;
	    					default:
	    						panel.setArrPosKeko(1);
	    						
	    				}
	    				cambio++;
	    			} else if(cambio==1){cambio=0;}
	    			else {cambio=0;}
	    			
	    			panel.keko.setLastSide('D');
	    			
	    			//Nos desplazamos en el eje X y a continuación comprobamos si deberíamos estar cayendo:
	            	ejeX+=panel.isWall(1,1,10,panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-panel.getEjeY(),panel.keko.getPosYPlayer()+89-panel.getEjeY(),30,10);
	            	//Estas dos líneas de código deberán implementarse otra vez cuando hagamos el suelo desaparecer bajo sus pies:
	                contadorSalto = 16;
	                saltando = true;
    			}
            }
    		else {
            	//Si está saltando hacemos que vaya reduciendo su avance lateral cuando esté cayendo, pero no cuando esté subiendo (aún mantiene su impulso):
            	if(contadorSalto>17 && contadorSalto<20) {
            		ejeX+=panel.isWall(1,1,26,panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-panel.getEjeY(),panel.keko.getPosYPlayer()+89-panel.getEjeY(),30,10)-contadorSalto;
            	}
            	else if(contadorSalto>19) {
            		ejeX+=panel.isWall(1,1,6,panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-panel.getEjeY(),panel.keko.getPosYPlayer()+89-panel.getEjeY(),30,10);
            	}
            	else {
            		ejeX+=panel.isWall(1,1,10,panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-panel.getEjeY(),panel.keko.getPosYPlayer()+89-panel.getEjeY(),30,10);
            	}
            	panel.keko.setLastSide('D');
            	if(anDisp==26) {
            		panel.setArrPosKeko(17);
            	}
            }
	    		
	    }
	    
    }

    /**
     * Método simple que nos reinicializa a cero el contador de Salto y activa el saltando. 
     * @TODO Habrá que modificarlo y pasarle un parámetro para reutilizarlo
     * enemyType1_1 los suelos que desaparecen o como método también para comporbar si debería caerse. Posible cambio de nombre a gravedad.
     */
    private void saltar(){
        contadorSalto=0;
        saltando=true;
    }
    
    /**
     * Método que gestiona la secuenciación del salto. El impulso a cada momento.
     */
    private void ejecutarSalto() {
    	
         if(saltando){
        	 
        	 //La secuencia de salto consiste en llamar a panel y decirle cuánto queremos movernos hacia arriba y él nos contesta diciendo cuánto podemos hacerlo
        	  
            if(contadorSalto>=0&&contadorSalto<5) {
            	compruebaDistanciaSalto=(byte) panel.isTop(1,1,20,ejeY,ejeX,prevX);
                ejeY += compruebaDistanciaSalto;
                //Y si su respuesta es menor a la petición, sabemos que hemos chocado, por lo que pasamos el contador al punto de comenzar a acelerar hacia abajo
                if(!(compruebaDistanciaSalto==20)) {
                	contadorSalto=16;
                }
            }
            else if(contadorSalto>4&&contadorSalto<8){
            	compruebaDistanciaSalto=(byte) panel.isTop(1,1,10,ejeY,ejeX,prevX);
                ejeY+=compruebaDistanciaSalto;
                if(!(compruebaDistanciaSalto==10)) {
                	contadorSalto=16;
                }
                switch(panel.keko.getLastSide()) {
                case 'I':
                	if(anDisp==26) {
                		panel.setArrPosKeko(16);
                	}
               	 break;
                case 'D':
                	if(anDisp==26) {
                		panel.setArrPosKeko(17);
                	}               
                	break;
               	 default:;
                }
            }
            else if(contadorSalto>7&&contadorSalto<12){
            	compruebaDistanciaSalto=(byte) panel.isTop(1,1,5,ejeY,ejeX,prevX);
                ejeY+=compruebaDistanciaSalto;
                if(!(compruebaDistanciaSalto==5)) {
                	contadorSalto=16;
                }
                switch(panel.keko.getLastSide()) {
                case 'I':
                	if(anDisp==26) {
                		panel.setArrPosKeko(16);
                	}
               	 break;
                case 'D':
                	if(anDisp==26) {
                		panel.setArrPosKeko(17);
                	}
               	 break;
               	 default:;
                }
            }
            else if(contadorSalto>11&&contadorSalto<15){
            	compruebaDistanciaSalto=(byte) panel.isTop(1,1,1,ejeY,ejeX,prevX);
                ejeY+=compruebaDistanciaSalto;
                if(!(compruebaDistanciaSalto==1)) {
                	contadorSalto=16;
                }
                switch(panel.keko.getLastSide()) {
                case 'I':
                	if(anDisp==26) {
                		panel.setArrPosKeko(16);
                	}
               	 break;
                case 'D':
                	if(anDisp==26) {
                		panel.setArrPosKeko(17);
                	}
               	 break;
               	 default:;
                }
            }
            else if(contadorSalto==15){
                prevY=720-ejeY;
                switch(panel.keko.getLastSide()) {
                case 'I':
                	if(anDisp==26) {
                		panel.setArrPosKeko(16);
                	}
               	 break;
                case 'D':
                	if(anDisp==26) {
                		panel.setArrPosKeko(17);
                	}
               	 break;
               	 default:;
                }
            }
            else if(contadorSalto>15) {
	            if(!panel.isGround(1,0,ejeX, ejeY, prevY,prevX)) {
	            	 //A partir de este punto comienza a caer, pasamos a comprobar si algo detiene su caída:
	                if(contadorSalto>15&&contadorSalto<18){
	                    prevY=720-ejeY;
	                    ejeY-=5;
	                }
	                else if(contadorSalto>17&&contadorSalto<20){
	                    prevY=720-ejeY;
	                    ejeY-=10;
	                }
	                else if(contadorSalto>19&&contadorSalto<25){
	                    prevY=720-ejeY;
	                    ejeY-=16;
	                }
	                else if(contadorSalto>24&&contadorSalto<30){
	                    prevY=720-ejeY;
	                    ejeY-=21;
	                }
	                else if(contadorSalto>29&&contadorSalto<41){
	                    prevY=720-ejeY;
	                    ejeY-=26;
	                }
	                else if(contadorSalto>40&&contadorSalto<46){
	                    prevY=720-ejeY;
	                    ejeY-=28;
	                }
	                else if(contadorSalto>45&&contadorSalto<52){
	                    prevY=720-ejeY;
	                    ejeY-=32;
	                }
	                else if(contadorSalto>51){
	                    prevY=720-ejeY;
	                    ejeY-=35;
	                }
	            }
	            else{
	            	ejeY=720-panel.getEjeY();
	                saltando=false;
	            }
            }
            contadorSalto++;
        }
    }
    
    private void disparar() {
    	
    	//Si estamos en proceso de disparo
    	if(panel.isDisparo()) {
    		//Limitamos el tiempo entre disparos
    		if(contador>=panel.getMomentoDisparo()+60) {
    			panel.setDisparo(false);
    			panel.setDisparado(false);
    		}
    		
    		if(anDisp<26) {
    			
        		switch(panel.keko.getLastSide()) {
        		case 'I':
        			if(impulso==0) {
        	    		switch(anDisp) {
        	        	case 21:
        	        		panel.setArrPosKeko(anDisp);
        	        		anDisp++;
        	        		break;
        	        	case 22:
        	        		panel.setArrPosKeko(anDisp);
        	        		anDisp++;
        	        		break;
        	        	case 23:
        	        		panel.setArrPosKeko(anDisp);
        	        		anDisp++;
        	        		break;
        	        	case 24:
        	        		panel.setArrPosKeko(anDisp-2);
        	        		anDisp++;
        	        		break;
        	        	case 25:
        	        		panel.setArrPosKeko(anDisp-4);
        	        		anDisp++;
        	        		break;
        	        	default:;
        	        	}
        	    		impulso++;
        	    	}
        	    	else{
        	    		impulso=0;
        	    	}
        			
        			break;
        		case 'D':
        			if(impulso==0) {
        	    		switch(anDisp) {
        	        	case 21:
        	        		panel.setArrPosKeko(anDisp-3);
        	        		anDisp++;
        	        		break;
        	        	case 22:
        	        		panel.setArrPosKeko(anDisp-3);
        	        		anDisp++;
        	        		break;
        	        	case 23:
        	        		panel.setArrPosKeko(anDisp-3);
        	        		anDisp++;
        	        		break;
        	        	case 24:
        	        		panel.setArrPosKeko(anDisp-2-3);
        	        		anDisp++;
        	        		break;
        	        	case 25:
        	        		panel.setArrPosKeko(anDisp-4-3);
        	        		anDisp++;
        	        		break;
        	        	default:;
        	        	}
        	    		impulso++;
        	    	}
        	    	else{
        	    		impulso=0;
        	    	}
        			
        			break;
        		}
    		}
    			
    		
    		
    	}
    	
    	//Si está apretando la tecla disparo
    	if(gatillo) {
    		//Si no estamos en proceso de disparo ya
    		if(!panel.isDisparo()) {
    			panel.keko.setEnergy(panel.keko.getEnergy()-1);
    			//Si no intenta disparar durante la pausa
	        	if(!pausa) {
	        		//Sonido disparo
	        		disparo.playDisparo();
	        		//Le indicamos cuándo efectuamos el disparo.Necesitamos saberlo porque limitamos la capacidad de disparar en el tiempo
	        		panel.setMomentoDisparo(this.contador);
	        		//Variables de control internas
	        		panel.setDisparado(false);
	        		panel.setDisparo(true);
	        		
	        		switch(panel.keko.getLastSide()) {
	        		case 'I':
	        			panel.setArrPosKeko(21);
	        			anDisp=21;
	        			break;
	        		case 'D':
	        			panel.setArrPosKeko(18);
	        			anDisp=21;
	        			break;
        			default:;
	        		}
	        	}
	    	}
    	}
    	
    }
    
    /**
     * Animaciones de mosntruos, fuegos, hierbas, trampas,...
     */
    private void animacionesOtros() {
    	
    	//Animaciones respiraciones pow
    	if(animacionesDe8==0) {
    		switch(anMons) {
        	case 0:
        		panel.setArrPosMonstruo(anMons);
        		panel.setArrPosFly(anMons);
        		anMons++;
        		break;
        	case 1:
        		panel.setArrPosMonstruo(anMons);
        		panel.setArrPosFly(anMons);
        		anMons++;
        		break;
        	case 2:
        		panel.setArrPosMonstruo(anMons);
        		panel.setArrPosFly(anMons);
        		anMons++;
        		break;
        	case 3:
        		panel.setArrPosMonstruo(anMons);
        		panel.setArrPosFly(anMons);
        		anMons++;
        		break;
        	case 4:
        		panel.setArrPosMonstruo(anMons-2);
        		panel.setArrPosFly(anMons-2);
        		anMons++;
        		break;
        	case 5:
        		panel.setArrPosMonstruo(anMons-4);
        		panel.setArrPosFly(anMons-4);
        		anMons++;
        		break;
        	case 6:
        		panel.setArrPosMonstruo(anMons-6);
        		panel.setArrPosFly(anMons-6);
        		anMons=0;
        		break;
        	default:;
        	}
    		animacionesDe8++;
    	}
    	else if(animacionesDe8==2) {
    		animacionesDe8=0;
    	}
    	else {
    		animacionesDe8++;
    	}
    	
    }
 
    /**
     * Método de saltos enemigos. Hecho para llamarse dentro de un for, donde a cada iteración le pasamos
     *  un enemigo nuevo de los que hay dentro de la array correspondiente.
     * @param enemigo El enemigo en cuestión
     * @param posLista La posición que tiene dentro de la array de enemigos
     */
   private void saltoMonstruos(Enemy enemigo,int posLista) {
	   
	   //Si está saltando
       if(enemigo.isJumping()) {
       
      	 //La secuencia de salto consiste en llamar a panel y decirle cuánto queremos movernos hacia arriba y él nos contesta diciendo cuánto podemos hacerlo
      	  
          if(enemigo.getContJumping()>=0&&enemigo.getContJumping()<5) {
        	  
        	  //isTop nos devuelve cuánto puede moverse en vertical hasta chocar contra algo
        	  compruebaDistanciaSalto=(byte) panel.isTop(2,posLista,enemigo.getPosYEnemy()-20,enemigo.getPosYEnemy(),ejeX,prevX);
        	  
	          enemigo.setPosYEnemy(enemigo.getPosYEnemy()-compruebaDistanciaSalto);
	          //Y si su respuesta es menor a la petición, sabemos que hemos chocado, por lo que pasamos el contador al punto de comenzar a acelerar hacia abajo
	          if(!(compruebaDistanciaSalto==20)) {
	          	enemigo.setContJumping(16);
	          }
          }
          else if(enemigo.getContJumping()>4&&enemigo.getContJumping()<8){
          	compruebaDistanciaSalto=(byte) panel.isTop(2,posLista,enemigo.getPosYEnemy()-10,enemigo.getPosYEnemy(),ejeX,prevX);
          	enemigo.setPosYEnemy(enemigo.getPosYEnemy()-compruebaDistanciaSalto);
              if(!(compruebaDistanciaSalto==10)) {
              	contadorSalto=16;
              }
          }
          else if(enemigo.getContJumping()>7&&enemigo.getContJumping()<12){
          	compruebaDistanciaSalto=(byte) panel.isTop(2,posLista,enemigo.getPosYEnemy()-5,enemigo.getPosYEnemy(),ejeX,prevX);
          	enemigo.setPosYEnemy(enemigo.getPosYEnemy()-compruebaDistanciaSalto);
              if(!(compruebaDistanciaSalto==5)) {
              	contadorSalto=16;
              }
          }
          else if(enemigo.getContJumping()>11&&enemigo.getContJumping()<15){
          	compruebaDistanciaSalto=(byte) panel.isTop(2,posLista,enemigo.getPosYEnemy()-1,enemigo.getPosYEnemy(),ejeX,prevX);
          	enemigo.setPosYEnemy(enemigo.getPosYEnemy()-compruebaDistanciaSalto);
              if(!(compruebaDistanciaSalto==1)) {
              	contadorSalto=16;
              }
          }
          else if(enemigo.getContJumping()==15) {
              enemigo.setPrevYEnemy(enemigo.getPosYEnemy());
          }
          else if(enemigo.getContJumping()>15) {
          
          	//LE PASAMOS LOS DATOS DE ESTE MONSTRUO EN CONCRETO
          	 
	            if(!panel.isGround(enemigo.getEntidad(),posLista,enemigo.getPosXEnemy()-ejeX + enemigo.getMoveEnemy(), enemigo.getPosYEnemy()+49, enemigo.getPrevYEnemy()+49,enemigo.getPosXEnemy()-ejeX + enemigo.getMoveEnemy())) {
	            	 //A partir de este punto comienza a caer, pasamos a comprobar si algo detiene su caída:
	                if(enemigo.getContJumping()>15&&enemigo.getContJumping()<18){
	                	enemigo.setPrevYEnemy(enemigo.getPosYEnemy());
	                    enemigo.setPosYEnemy(enemigo.getPosYEnemy()+5);
	                }
	                else if(enemigo.getContJumping()>17&&enemigo.getContJumping()<20){
	                	enemigo.setPrevYEnemy(enemigo.getPosYEnemy());
	                    enemigo.setPosYEnemy(enemigo.getPosYEnemy()+10);
	                }
	                else if(enemigo.getContJumping()>19&&enemigo.getContJumping()<25){
	                	enemigo.setPrevYEnemy(enemigo.getPosYEnemy());
	                    enemigo.setPosYEnemy(enemigo.getPosYEnemy()+16);
	                }
	                else if(enemigo.getContJumping()>24&&enemigo.getContJumping()<30){
	                	enemigo.setPrevYEnemy(enemigo.getPosYEnemy());
	                    enemigo.setPosYEnemy(enemigo.getPosYEnemy()+21);
	                }
	                else if(enemigo.getContJumping()>29&&enemigo.getContJumping()<41){
	                	enemigo.setPrevYEnemy(enemigo.getPosYEnemy());
	                    enemigo.setPosYEnemy(enemigo.getPosYEnemy()+26);
	                }
	                else if(enemigo.getContJumping()>40&&enemigo.getContJumping()<46){
	                	enemigo.setPrevYEnemy(enemigo.getPosYEnemy());
	                    enemigo.setPosYEnemy(enemigo.getPosYEnemy()+28);
	                }
	                else if(enemigo.getContJumping()>45&&enemigo.getContJumping()<52){
	                	enemigo.setPrevYEnemy(enemigo.getPosYEnemy());
	                    enemigo.setPosYEnemy(enemigo.getPosYEnemy()+32);
	                }
	                else if(enemigo.getContJumping()>51){
	                	enemigo.setPrevYEnemy(enemigo.getPosYEnemy());
	                    enemigo.setPosYEnemy(enemigo.getPosYEnemy()+35);
	                }
	            }
	            else{
	            	//Si ya está sobre suelo firme
	                enemigo.setJumping(false);
	            }
          }
          enemigo.setContJumping(enemigo.getContJumping()+1);
      }
	   
   }
   
   /**
    * Método que se encarga de mover a los monstruos y de comprobar sus caídas.
    */
   private void movimientosMonstruos() {
	   
	   int movLateral=0,movLateralAire=0;

	   for(int x=0;x<panel.entities.enemies.size();x++) {
		   
		   switch(panel.entities.enemies.get(x).getTypeEnemy()) {
		   case "1":
			   movLateral=5;
			   movLateralAire=3;
			   break;
		   case "2":
			   movLateral=7;
			   movLateralAire=5;
			   break;
		   case "fly":
			   movLateral=13;
			   break;
		   case "boss":
			   movLateral=13;
			   movLateralAire=10;
			   break;
			   default:;
		   }
				   		   
		   //Comprobamos si se ha caído por un precipicio. Si es así, lo matamos
		   if(panel.entities.enemies.get(x).getPosYEnemy()>780) {
			   panel.entities.enemies.get(x).setDead(true);
		   }
			   
	/*		   //ESTO ES PARA HACERLOS SALTAR SIN PARAR******************SE APLICARÁ A LOS SALTARINES******************************************************
			   
			   if(!Enemies_lvl1.enemies.get(x).isJumping()) {
					   Enemies_lvl1.enemies.get(x).setJumping(true);
					   Enemies_lvl1.enemies.get(x).setContJumping(0);
				   }
	*/

		   if (!panel.entities.enemies.get(x).isDead()){
			   
			   //Llamamos al método que comprueba/ejecuta las caídas de éstos
			   saltoMonstruos(panel.entities.enemies.get(x),x);

			   //Sólo actúa si está en el radio de acción
			   if(panel.entities.enemies.get(x).getPosXEnemy()-panel.getEjeX()+ panel.entities.enemies.get(x).getMoveEnemy()<panel.keko.getPosXPlayer()+600 
					   && panel.entities.enemies.get(x).getPosXEnemy()-panel.getEjeX()+ panel.entities.enemies.get(x).getMoveEnemy()>panel.keko.getPosXPlayer()-400) {

				   //Llamamos a su método para tomar decisión, que nos devolverá un char diciéndonos a dónde quiere ir
				   switch (panel.entities.enemies.get(x).getDecission(panel.entities.enemies.get(x).getPosXEnemy()-ejeX+panel.entities.enemies.get(x).getMoveEnemy(),panel.entities.enemies.get(x).getPosYEnemy()+ panel.entities.enemies.get(x).getMoveYEnemy(),
						   panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-ejeY)) {
					   case 'D':
						   //Si no está saltando movemos 5, si no sólo 3
						   if(!panel.entities.enemies.get(x).isJumping() || panel.entities.enemies.get(x).getTypeEnemy().equals("fly")) {
							   //Comprobamos si es de los que se tiran por las plataformas o no
							   if(panel.entities.enemies.get(x).isSuicida()) {
								   panel.entities.enemies.get(x).setMoveEnemy(panel.entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,movLateral,
										   panel.entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + panel.entities.enemies.get(x).getMoveEnemy(),
										   panel.entities.enemies.get(x).getPosYEnemy()+ panel.entities.enemies.get(x).getMoveYEnemy(),
										   panel.entities.enemies.get(x).getPosYEnemy()+49,20,50));
							   }
							   else {
								   //Si no es suicida, primero comprobamos si se va a caer o no si se mueve y se lo permitimos o no
								   if(panel.isGround(panel.entities.enemies.get(x).getEntidad(),x,panel.entities.enemies.get(x).getPosXEnemy()+movLateral-ejeX + panel.entities.enemies.get(x).getMoveEnemy(),
										   panel.entities.enemies.get(x).getPosYEnemy()+49,panel.entities.enemies.get(x).getPrevYEnemy()+49,
										   panel.entities.enemies.get(x).getPosXEnemy()-ejeX + panel.entities.enemies.get(x).getMoveEnemy())) {
									   panel.entities.enemies.get(x).setMoveEnemy(panel.entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,movLateral,
											   panel.entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + panel.entities.enemies.get(x).getMoveEnemy(),
											   panel.entities.enemies.get(x).getPosYEnemy()+ panel.entities.enemies.get(x).getMoveYEnemy(), panel.entities.enemies.get(x).getPosYEnemy()+49,20,50));
								   }
							   }
							   //Si debe estar cayendo, con ésto lo haemos caer
							   if(!(panel.entities.enemies.get(x).getTypeEnemy().equals("fly"))) {
								   panel.entities.enemies.get(x).setJumping(true);
								   panel.entities.enemies.get(x).setContJumping(16);
							   }
						   }
						   else {
							   panel.entities.enemies.get(x).setMoveEnemy(panel.entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,movLateralAire,
									   panel.entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + panel.entities.enemies.get(x).getMoveEnemy(),
									   panel.entities.enemies.get(x).getPosYEnemy()+ panel.entities.enemies.get(x).getMoveYEnemy(), panel.entities.enemies.get(x).getPosYEnemy()+49,20,50));
						   }
						   panel.entities.enemies.get(x).setIzDer(true);
						   break;
					   case 'I':
						   if(!panel.entities.enemies.get(x).isJumping() || panel.entities.enemies.get(x).getTypeEnemy().equals("fly")) {
							   
							   if(panel.entities.enemies.get(x).isSuicida()) {
								   panel.entities.enemies.get(x).setMoveEnemy(panel.entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,-movLateral,
										   panel.entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + panel.entities.enemies.get(x).getMoveEnemy(),
										   panel.entities.enemies.get(x).getPosYEnemy()+ panel.entities.enemies.get(x).getMoveYEnemy(), panel.entities.enemies.get(x).getPosYEnemy()+49,20,50));
							   }
							   else {
								   if(panel.isGround(panel.entities.enemies.get(x).getEntidad(),x,panel.entities.enemies.get(x).getPosXEnemy()-movLateral-ejeX + panel.entities.enemies.get(x).getMoveEnemy(),
										   panel.entities.enemies.get(x).getPosYEnemy()+49,panel.entities.enemies.get(x).getPrevYEnemy()+49,
										   panel.entities.enemies.get(x).getPosXEnemy()-ejeX + panel.entities.enemies.get(x).getMoveEnemy())) {
									   panel.entities.enemies.get(x).setMoveEnemy(panel.entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,-movLateral,
											   panel.entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + panel.entities.enemies.get(x).getMoveEnemy(),
											   panel.entities.enemies.get(x).getPosYEnemy(), panel.entities.enemies.get(x).getPosYEnemy()+49,20,50));
								   }
							   }
							 //Si debe estar cayendo, con ésto lo haemos caer
							   if(!(panel.entities.enemies.get(x).getTypeEnemy().equals("fly"))) {
								   panel.entities.enemies.get(x).setJumping(true);
								   panel.entities.enemies.get(x).setContJumping(16);
							   }
						   }
						   else {
							   panel.entities.enemies.get(x).setMoveEnemy(panel.entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,-movLateralAire,
									   panel.entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + panel.entities.enemies.get(x).getMoveEnemy(),
									   panel.entities.enemies.get(x).getPosYEnemy()+ panel.entities.enemies.get(x).getMoveYEnemy(), panel.entities.enemies.get(x).getPosYEnemy()+49,50,20));
						   }
						   panel.entities.enemies.get(x).setIzDer(false);
						   break;
					   case 'W':
						   
						   compruebaDistanciaSalto=(byte) panel.isTop(2,x,panel.entities.enemies.get(x).getPosYEnemy()-13,panel.entities.enemies.get(x).getPosYEnemy(),ejeX,prevX);
						   if(panel.entities.enemies.get(x).getPosYEnemy()+panel.entities.enemies.get(x).getMoveYEnemy()-compruebaDistanciaSalto <= panel.keko.getPosYPlayer()-panel.getEjeY() && 
								   panel.entities.enemies.get(x).getPosYEnemy()+panel.entities.enemies.get(x).getMoveYEnemy() >= panel.keko.getPosYPlayer()-panel.getEjeY()) {
							   panel.entities.enemies.get(x).setPosYEnemy(panel.keko.getPosYPlayer()-panel.getEjeY());
						   }
						   else {
							   panel.entities.enemies.get(x).setPosYEnemy(panel.entities.enemies.get(x).getPosYEnemy()-compruebaDistanciaSalto);
						   }
						   break;
					   case 'S':
				        	  
						   if(panel.entities.enemies.get(x).getPosYEnemy()+panel.entities.enemies.get(x).getMoveYEnemy()+13 >= panel.keko.getPosYPlayer()-panel.getEjeY() && 
						   panel.entities.enemies.get(x).getPosYEnemy()+panel.entities.enemies.get(x).getMoveYEnemy() <= panel.keko.getPosYPlayer()-panel.getEjeY()) {
							   panel.entities.enemies.get(x).setPosYEnemy(panel.keko.getPosYPlayer()-panel.getEjeY());
						   }
						   else {
							   panel.entities.enemies.get(x).setPosYEnemy(panel.entities.enemies.get(x).getPosYEnemy()+13);
						   }
						   break;
					   default:
						   //Si está compartiendo ejeX con el Keko no querrá moverse más, pero debemos comprobar si debe estar cayendo igualmente
						   if(!panel.entities.enemies.get(x).isJumping()) {
							 //Si debe estar cayendo, con ésto lo haemos caer
							   if(!(panel.entities.enemies.get(x).getTypeEnemy().equals("fly"))) {
								   panel.entities.enemies.get(x).setJumping(true);
								   panel.entities.enemies.get(x).setContJumping(16);
							   }
						   }
						   
						   if(panel.isWall(2,x,1,
								   panel.entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + panel.entities.enemies.get(x).getMoveEnemy(),
								   panel.entities.enemies.get(x).getPosYEnemy()+ panel.entities.enemies.get(x).getMoveYEnemy(), panel.entities.enemies.get(x).getPosYEnemy()+49,20,50)==0) {
									   panel.entities.enemies.get(x).doDamage(panel.keko);
								   }
						   
				   }

			   }

		   }

	   }

   }

   /**
    * Método que nos sirve para ejecutar o no la pausa y que además usamos para hacer el repaint del panel.
    */
   private void pausa() {
   
	   //Si estamos en pausa
    	if(pausa) {
    		//Le decimos a panel que pinte la pantalla de pausa
    		panel.setPause(true);
    		//Hacemos un repaint con la pantalla de pausa
    		panel.repaint();
    		//Paramos la música
    		try {musica.pausaFondo();} catch (Exception e) {System.out.println("Fallo al pausar la música. Error: "+e);}
    	}
    	while(pausa) {
    		//ÑAPA detenemos el hilo mientras estamos en pausa
    		System.out.println("pausa");
    		//Estamos obligados a hacerle hacer algo dentro del while o se queda sordo a la variable pausa, imagino que es cosa de que el procesador se pone a full
    	}
    	if(!pausa) {
    		panel.setPause(false);
    		panel.repaint();
    		try {musica.continuarFondo();} catch (Exception e) {System.out.println("Fallo al reanudar la música. Error: "+e);}
    	}
    	
    }

    
    /**
     * Método que ejecuta los demás métodos importantes y se encarga de decirle al panel las posiciones X e Y y ordenarle el repintado.
     */
    private void fps(){
    	
    	//Dejamos que contador suba a 10 antes de permitirnos jugar para dar más tiempo de carga
    	if(!(contador<10)) {
	    	
	    	movimientosMonstruos();
	    	
	        animacionesOtros();
	    	
	    	PJMove();
	
	    	ejecutarSalto();
	    	
	    	disparar();
    	
    	}
        
        panel.setEjeX(ejeX);
        panel.setEjeY(ejeY);
        pausa();

    }
    
    /**
     * Método que nos devuelve el nombre elegido por el jugador.
     * @return Tres carácteres en forma de String.
     */
    public String getNombreJugador() {
    	return panel.getNombreElegido();
    }
    
    /**
     * Método de inicialización del nuevo nivel. Contiene un bucle (infinito por ahora) que se encarga de ordenar el repintado mediante el llamado
     *  al método fps() cada 11 milisegundos.
     */
    public int runGame(){
        
        try {
        	//Detenemos el hilo para darle tiempo al panel a cargar y ponemos la imagen de cargando
        	panel.setLoading(true);
        	//Hacemos que la música de fondo empiece unos milisegundos después de poner la pantalla de carga
        	musica.cargarFondo();
        	disparo.cargarDisparo();
        	musica.playFondo();
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            System.out.println("Error de interrupción del Thread.sleep contador="+contador+". Error log: "+e);
        }
        
        panel.setLoading(false);
        
        while(!gameOver){
        	
        	//30 veces por segundo
        	if(contador%3==0) {
        		fps();	
        	}
        	
        	if(panel.keko.getPosYPlayer()-panel.getEjeY()>780 || panel.keko.getEnergy() < 0) {
        		if(panel.keko.getLives()-1>=0) {
        			int tempVidas=panel.keko.getLives()-1;
        			panel.reset(tempVidas);
        			ejeX=0;ejeY=0;prevY=720-89;prevX=0;
        			panel.setEjeX(ejeX);
        	        panel.setEjeY(ejeY);
        	       
        			try {
                	//Detenemos el hilo para darle tiempo al panel a cargar y ponemos la imagen de cargando
                	panel.setLoading(true);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Error de interrupción del Thread.sleep contador="+contador+". Error log: "+e);
                }
        			pausa();
        			panel.setLoading(false);

        		}
        		else {
        			panel.keko.setDead(true);
        		}
        	}

        	//Si ha muerto o se ha acabado el tiempo
        	if(puntuacion < 0 || panel.keko.isDead()) {
        		gameOver = true;
        		}

            try {
            	//Ésto nos permite detener el hilo y darnos el control sobre cuántas veces iteramos por segundo (aproximadamente)
                Thread.sleep(11);
            } catch (InterruptedException e) {
                System.out.println("Error de interrupción del Thread.sleep contador="+contador+". Error log: "+e);
            }

            //Test por consola reducción de puntuación basada el tiempo transcurrido (máso menos un segundo cada vez que entra):
            if(contador%90==0){
                puntuacion--;
                
				contarSegundosCambiarImagenTiempo++;
				
				if (contarSegundosCambiarImagenTiempo % segundosCambiarImagenTiempo == 0){
					if (panel.getArrPosReloj() <= 25){
						panel.setArrPosReloj(panel.getArrPosReloj() + 1);
					}
				}

            }
            contador++;
        }
        
        try {
			musica.stop();
			
			//Pedimos el nombre del jugador:
		    panel.setPedirNombre(true);
		    panel.repaint();
		    pidiendoNombre=true;
		    musica.lose();
		    
		    while(pidiendoNombre) {
		    	
		    	System.out.println("GameEngine - Pidiendo nombre - Esperando");
			
		    }
		    
		    panel.setPedirNombre(false);
			panel.setLoading(true);
			panel.repaint();
			Thread.sleep(1500);
			
		} catch (Exception e) {
			System.out.println("Error deteniendo la reproducciónd e la música o deteniendo "
					+ "el tiempo al final de la partida. Log: "+e);
			e.printStackTrace();
		}
        
        //Calculamos la puntuación final:   
        puntuacion+=panel.keko.getEnergy()*100;
        puntuacion+=panel.getEjeX();
    	puntuacion*=panel.keko.getLives()+1;
        
        return puntuacion;
        
    }
    
}
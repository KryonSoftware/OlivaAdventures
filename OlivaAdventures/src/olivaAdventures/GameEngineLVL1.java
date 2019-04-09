package olivaAdventures;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * Clase de motor del juego. Físicas. Lvl 1.
 * 
 * @author kryon
 */
public class GameEngineLVL1 implements KeyListener {

    private boolean saltando=false,arriba=false,derecha=false,izquierda=false,pausa=false;
    private long contador=0,contadorDisparo=0;
    private int ejeX=0,ejeY=0,prevY=720-89,prevX=0;
	private byte contadorSalto=0,cambio=0,respirando=0,compruebaDistanciaSalto=0,animacionesDe8=0,anMons=0;
    
    //Inicializamos el panel que va dentro del Frame:
    private PanelLVL1 panel = new PanelLVL1();

	//La música que usaremos:
	Musica musica = new Musica();
 
    /*

     * Código de David NO FUNCIONAL, A LA ESPERA DE BUGFIXING
     * 
     *     private PlantillaVentana plantillaVentana;

     *
     * //Constructor de nuestro nivel 1. Establece autoáticamente el Frame. CAMBIAR PARA LE FUTURO: TODO FRAME EN CLASE APARTE.
     *
    public GameEngineLVL1(JFrame frame, PlantillaVentana plantillaVentana){
		this.plantillaVentana = plantillaVentana;

    	
        frame.setSize(1000,1000);
        frame.setResizable(false);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //Añadimos el panel que instanciamos antes:
        frame.add(panel);

        //Dejamos el frame visible al final para evitar problemas:
        frame.setVisible(true);

    }
     */

    
    /**
     * Constructor de nuestro nivel 1. Establece automáticamente el Frame. CAMBIAR PARA LE FUTURO: TODO FRAME EN CLASE APARTE.
     */
    public GameEngineLVL1(){

    	//Creamos e instanciamos el Frame:
        JFrame frame = new JFrame("Oliva Adventures");
        //Valores del frame:
        frame.setSize(1000,1000);
        frame.setResizable(false);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //Añadimos el panel que instanciamos antes:
        frame.add(panel);

        //Dejamos el frame visible al final para evitar problemas:
        frame.setVisible(true);

    }

    /**
     * Sin usar.
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    	//HOLA ME LLAMO MÉTODO TYPED Y SOY UN VAGO
    	
    }

    /**
     * Estos eventos los usamos para activar las booleanas de presión de tecla. Gracias a esto podemos tener varias teclas pulsadas a la vez.
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {

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
            case KeyEvent.VK_SPACE:
            	if(!panel.isDisparo()) {
                	if(!pausa) {
                		musica.cargarSonidoPistola();
                		panel.setMomentoDisparo(this.contador);
                		panel.setDisparado(false);
                		panel.setDisparo(true);
                	}
            	}
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

    /**
     * Usamos estos eventos para desactivar las booleanas de presión de teclas.
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {

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
        	break;
        default:;
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
	    		break;
	    	case 'I':
	    		
	    		if(!saltando) {
	    			
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
            }
    	}
	    if(derecha && !izquierda) {
	    	
    		if(!saltando) {
    			
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
            }
            else if(contadorSalto>7&&contadorSalto<12){
            	compruebaDistanciaSalto=(byte) panel.isTop(1,1,5,ejeY,ejeX,prevX);
                ejeY+=compruebaDistanciaSalto;
                if(!(compruebaDistanciaSalto==5)) {
                	contadorSalto=16;
                }
            }
            else if(contadorSalto>11&&contadorSalto<15){
            	compruebaDistanciaSalto=(byte) panel.isTop(1,1,1,ejeY,ejeX,prevX);
                ejeY+=compruebaDistanciaSalto;
                if(!(compruebaDistanciaSalto==1)) {
                	contadorSalto=16;
                }
            }
            else if(contadorSalto==15){
                prevY=720-ejeY;
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
    	
    	/*
		 * Cuando disparo sea true, lanzamos una bala en la dirección que estemos mirando. La bala se meterá en la lista de
		 * plataformas y se la hará colisionable contra ENEMY y BOTH
		 */   	
    	
    	if(panel.isDisparo()) {
    	
    		if(contador>=panel.getMomentoDisparo()+150) {
    			panel.setDisparo(false);
    		}
    		
    	}
    	
    }
    
    private void animacionesOtros() {
    	
    	
    	if(animacionesDe8==0) {
    		switch(anMons) {
        	case 0:
        		panel.setArrPosMonstruo(anMons);
        		anMons++;
        		break;
        	case 1:
        		panel.setArrPosMonstruo(anMons);
        		anMons++;
        		break;
        	case 2:
        		panel.setArrPosMonstruo(anMons);
        		anMons++;
        		break;
        	case 3:
        		panel.setArrPosMonstruo(anMons);
        		anMons++;
        		break;
        	case 4:
        		panel.setArrPosMonstruo(anMons-2);
        		anMons++;
        		break;
        	case 5:
        		panel.setArrPosMonstruo(anMons-4);
        		anMons++;
        		break;
        	case 6:
        		panel.setArrPosMonstruo(anMons-6);
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
     * Método hecho para llamarse dentro de un for, donde a cada iteración le pasamos un enemigo nuevo de los que hay dentro de la array correspondiente.
     * @param enemigo
     */
   private void saltoMonstruos(Enemy enemigo,int posLista) {
	   
       if ( enemigo.isJumping() ) {
       
      	 //La secuencia de salto consiste en llamar a panel y decirle cuánto queremos movernos hacia arriba y él nos contesta diciendo cuánto podemos hacerlo
      	  
          if(enemigo.getContJumping()>=0&&enemigo.getContJumping()<5) {
        	  
        	  //HAY QUE ARREGLAR EL ISTOP*************************************************************************
          	compruebaDistanciaSalto=(byte) panel.isTop(2,posLista,enemigo.getPosYEnemy()-20,enemigo.getPosYEnemy(),ejeX,prevX);
          	//HAY QUE ARREGLAR EL ISTOP*************************************************************************
          	
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
	                enemigo.setJumping(false);
	            }
          }
          enemigo.setContJumping(enemigo.getContJumping()+1);
      }
	   
   }
   

   private void movimientosMonstruos() {

	   //ÑAPAS PARA PRUEBAS:

	   for(int x=0;x<Entities.enemies.size();x++) {

		   saltoMonstruos(Entities.enemies.get(x),x);
		   
/*		   //ESTO ES PARA HACERLOS SALTAR SIN PARAR************************************************************************
		   
		   if(!Enemies_lvl1.enemies.get(x).isJumping()) {
				   Enemies_lvl1.enemies.get(x).setJumping(true);
				   Enemies_lvl1.enemies.get(x).setContJumping(0);
			   }
*/
		   
		   if (!Entities.enemies.get(x).isDead()){
			   switch (Entities.enemies.get(x).getDecission(Entities.enemies.get(x).getPosXEnemy()-ejeX+Entities.enemies.get(x).getMoveEnemy(),Entities.enemies.get(x).getPosYEnemy(),
					   panel.keko.getPosXPlayer(),panel.keko.getPosYPlayer()-ejeY)) {
					   case 'D':
						   if(!Entities.enemies.get(x).isJumping()) {
							   if(Entities.enemies.get(x).isSuicida()) {
								   Entities.enemies.get(x).setMoveEnemy(Entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,5,
										   Entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + Entities.enemies.get(x).getMoveEnemy(),
										   Entities.enemies.get(x).getPosYEnemy(), Entities.enemies.get(x).getPosYEnemy()+49,50,20));
							   }
							   else {
								   if(panel.isGround(Entities.enemies.get(x).getEntidad(),x,Entities.enemies.get(x).getPosXEnemy()+5-ejeX + Entities.enemies.get(x).getMoveEnemy(),
										   Entities.enemies.get(x).getPosYEnemy()+49,Entities.enemies.get(x).getPrevYEnemy()+49,
										   Entities.enemies.get(x).getPosXEnemy()-ejeX + Entities.enemies.get(x).getMoveEnemy())) {
									   Entities.enemies.get(x).setMoveEnemy(Entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,5,
											   Entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + Entities.enemies.get(x).getMoveEnemy(),
											   Entities.enemies.get(x).getPosYEnemy(), Entities.enemies.get(x).getPosYEnemy()+49,50,20));
								   }
							   }
							   Entities.enemies.get(x).setJumping(true);
							   Entities.enemies.get(x).setContJumping(16);
						   }
						   else {
							   Entities.enemies.get(x).setMoveEnemy(Entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,3,
									   Entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + Entities.enemies.get(x).getMoveEnemy(),
									   Entities.enemies.get(x).getPosYEnemy(), Entities.enemies.get(x).getPosYEnemy()+49,50,20));
						   }
						   break;
					   case 'I':
						   if(!Entities.enemies.get(x).isJumping()) {
							   if(Entities.enemies.get(x).isSuicida()) {
								   Entities.enemies.get(x).setMoveEnemy(Entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,-5,
										   Entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + Entities.enemies.get(x).getMoveEnemy(),
										   Entities.enemies.get(x).getPosYEnemy(), Entities.enemies.get(x).getPosYEnemy()+49,50,20));
							   }
							   else {
								   if(panel.isGround(Entities.enemies.get(x).getEntidad(),x,Entities.enemies.get(x).getPosXEnemy()-5-ejeX + Entities.enemies.get(x).getMoveEnemy(),
										   Entities.enemies.get(x).getPosYEnemy()+49,Entities.enemies.get(x).getPrevYEnemy()+49,
										   Entities.enemies.get(x).getPosXEnemy()-ejeX + Entities.enemies.get(x).getMoveEnemy())) {
									   Entities.enemies.get(x).setMoveEnemy(Entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,-5,
											   Entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + Entities.enemies.get(x).getMoveEnemy(),
											   Entities.enemies.get(x).getPosYEnemy(), Entities.enemies.get(x).getPosYEnemy()+49,50,20));
								   }
							   }
							   Entities.enemies.get(x).setJumping(true);
							   Entities.enemies.get(x).setContJumping(16);
						   }
						   else {
							   Entities.enemies.get(x).setMoveEnemy(Entities.enemies.get(x).getMoveEnemy()+panel.isWall(2,x,-3,
									   Entities.enemies.get(x).getPosXEnemy() -panel.getEjeX() + Entities.enemies.get(x).getMoveEnemy(),
									   Entities.enemies.get(x).getPosYEnemy(), Entities.enemies.get(x).getPosYEnemy()+49,50,20));
						   }
						   break;
					   default:
						   if(!Entities.enemies.get(x).isJumping()) {
							   Entities.enemies.get(x).setJumping(true);
							   Entities.enemies.get(x).setContJumping(16);
						   }
						   //	panel.keko.doDamge(Enemies_lvl1.enemies.get(x));
			   }
			   
		   }
		   
	   }

    }

   
   private void pausa() {
   
    	if(pausa) {
    		panel.setPause(true);
    		panel.repaint();
    		try {musica.pausa();} catch (Exception e) {System.out.println("Fallo al pausar la música. Error: "+e);}
    	}
    	while(pausa) {
    		System.out.println("pausa");
    	}
    	if(!pausa) {
    		panel.setPause(false);
    		panel.repaint();
    		try {musica.continuar();} catch (Exception e) {System.out.println("Fallo al reanudar la música. Error: "+e);}
    	}
    	
    }

    
    /**
     * Método que ejecuta los demás métodos importantes y se encarga de decirle al panel las posiciones X e Y y ordenarle el repintado.
     */
    private void fps(){
    	
    	if(panel.keko.getPosYPlayer()-panel.getEjeY()>900) {
    		panel.keko.setDead(true);
    	}
    	
    	movimientosMonstruos();
    	
        animacionesOtros();
    	
    	PJMove();

    	ejecutarSalto();
    	
    	disparar();
        
        panel.setEjeX(ejeX);
        panel.setEjeY(ejeY);
        pausa();

    }
    
    /**
     * Método de inicialización del nuevo nivel. Contiene un bucle (infinito por ahora) que se encarga de ordenar el repintado mediante el llamado
     *  al método fps() cada 11 milisegundos.
     */
    public void arrancar(){

        boolean gameOver = false;
        int puntuacion = 360;
        
        musica.cargarMusicaFondo();
        
        while(!gameOver){
        	
        	if(contador%3==0) {
        		fps();	
        	}
        	
        	if(contador%18==0) {
        		//pruebas:+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            	if(!(panel.getArrPosBarra()==4)) {
            		panel.setArrPosBarra(panel.getArrPosBarra()+1);
            	} else {
            		panel.setArrPosBarra(0);
            	}
            	if(!(panel.getArrPosReloj()==25)) {
            		panel.setArrPosReloj(panel.getArrPosReloj()+1);
            	} else {
            		panel.setArrPosReloj(0);
            	}
            	//pruebas+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        	}

        	if(puntuacion < 0 || panel.keko.getEnergy() < 0 || panel.keko.isDead()) {
        		gameOver = true;
        		}

            try {
            	//Ésto nos permite detener el hilo y darnos el control sobre cuántas veces iteramos por segundo (aproximadamente)
                Thread.sleep(11);
            } catch (InterruptedException e) {
                System.out.println("Error de interrupción del Thread.sleep contador="+contador+". Error log: "+e);
            }

            //Test por consola reducción de puntuación basada el tiempo transcurrido:
            if(contador%90==0){
                puntuacion--;
                System.out.println(puntuacion);
            }
            contador++;
        }
        
        try {
			musica.stop();
			System.exit(1000);
		} catch (Exception e) {
			System.out.println("Error deteniendo la reproducciónd e la música. Log: "+e);
		}
        
    }
    
}
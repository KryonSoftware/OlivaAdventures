package olivaAdventures;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.ArrayList;

import javax.swing.*;

/**
 * Clase de motor del juego. Físicas. Lvl 1.
 * 
 * @author kryon
 */
public class GameEngineLVL1 implements KeyListener {

    private boolean saltando=false,arriba=false,derecha=false,izquierda=false;
    private int contador=0,ejeX=0,ejeY=0,contadorSalto=0,prevY=0,prevX=0,cambio=0,respirando=0;
    private char lastSide='D';
    //Inicializamos el panel que va dentro del Frame:
    private PanelLVL1 panel = new PanelLVL1();
    //Instanciamos el player (NO USADO ACTUALMENTE, TODO PARA EL FUTURO):
    private Player player = new Player();

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
                //Implementar en el futuro los casos de las teclas de activación de golpe CaC y/o disparo
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
            //Implementar en el futuro los casos de las teclas de activación de golpe CaC y/o disparo
        default:;
        }
    	cambio=0;
    }
    
    /**
     * Método para leer las booleanas y en función de su estado, alterar los movimientos de nuestro personaje 
     * (sus coordenadas de colocación) ANTES de repintarlo en la pantalla.
     */
    private void PJMove() {
    	
    	if((!derecha && !izquierda) || (derecha && izquierda)) {
    		
	    	switch(lastSide) {
	    	
	    	case 'D':
	    		
	    		if(!saltando) {
	    			
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
	    			} else if(respirando==1){respirando=2;}
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
	    			else {respirando=0;}
	    		}
	    		break;
			default:;
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
    						panel.setArrPosKeko(4);
    						
    				}
    				cambio++;
    			} else if(cambio==1){cambio=2;}
    			else {cambio=0;}
    			
    			lastSide='I';
    			
    			//Nos desplazamos en el eje X y a continuación comprobamos si deberíamos estar cayendo:
            	ejeX-=-panel.isWall(-10);
            	//Estas dos líneas de código deberán implementarse otra vez cuando hagamos el suelo desaparecer bajo sus pies:
                contadorSalto = 16;
                saltando = true;
            }
    		else {
            	//Si está saltando hacemos que vaya reduciendo su avance lateral cuando esté cayendo, pero no cuando esté subiendo (aún mantiene su impulso):
            	if(contadorSalto>17 && contadorSalto<20) {
            		ejeX-=(-panel.isWall(-26))-contadorSalto;
            	}
            	else if(contadorSalto>19) {
            		ejeX-=(-panel.isWall(-6));
            	}
            	else {
            		ejeX-=(-panel.isWall(-10));
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
    						panel.setArrPosKeko(0);
    						
    				}
    				cambio++;
    			} else if(cambio==1){cambio=2;}
    			else {cambio=0;}
    			
    			lastSide='D';
    			
    			//Nos desplazamos en el eje X y a continuación comprobamos si deberíamos estar cayendo:
            	ejeX+=panel.isWall(10);
            	//Estas dos líneas de código deberán implementarse otra vez cuando hagamos el suelo desaparecer bajo sus pies:
                contadorSalto = 16;
                saltando = true;
            }
    		else {
            	//Si está saltando hacemos que vaya reduciendo su avance lateral cuando esté cayendo, pero no cuando esté subiendo (aún mantiene su impulso):
            	if(contadorSalto>17 && contadorSalto<20) {
            		ejeX+=panel.isWall(26)-contadorSalto;
            	}
            	else if(contadorSalto>19) {
            		ejeX+=panel.isWall(6);
            	}
            	else {
            		ejeX+=panel.isWall(10);
            	}
            }
    	}
    }

    /**
     * Método simple que nos reinicializa a cero el contador de Salto y activa el saltando. 
     * @TODO Habrá que modificarlo y pasarle un parámetro para reutilizarlo
     * en los suelos que desaparecen o como método también para comporbar si debería caerse. Posible cambio de nombre a gravedad.
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
            if(contadorSalto>=0&&contadorSalto<5) {
                ejeY += 20;
            }
            else if(contadorSalto>4&&contadorSalto<8){
                ejeY+=10;
            }
            else if(contadorSalto>7&&contadorSalto<12){
                ejeY+=5;
            }
            else if(contadorSalto>11&&contadorSalto<15){
                ejeY+=1;
            }
            else if(contadorSalto==15){
                prevY=720-ejeY-70;
            }
            else if(contadorSalto>15) {
	            if(!panel.isGround(ejeX, ejeY, prevY,prevX)) {
	            	 //A partir de este punto comienza a caer, pasamos a comprobar si algo detiene su caída:
	                if(contadorSalto>15&&contadorSalto<20){
	                    prevY=720-ejeY-70;
	                    ejeY-=5;
	                }
	                else if(contadorSalto>19&&contadorSalto<25){
	                    prevY=720-ejeY-70;
	                    ejeY-=10;
	                }
	                else if(contadorSalto>24&&contadorSalto<30){
	                    prevY=720-ejeY-70;
	                    ejeY-=20;
	                }
	                else if(contadorSalto>29&&contadorSalto<36){
	                    prevY=720-ejeY-70;
	                    ejeY-=25;
	                }
	                else if(contadorSalto>35&&contadorSalto<42){
	                    prevY=720-ejeY-70;
	                    ejeY-=30;
	                }
	                else if(contadorSalto>41){
	                    prevY=720-ejeY-70;
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

    /**
     * Método que ejecuta los demás métodos importantes y se encarga de decirle al panel las posiciones X e Y y ordenarle el repintado.
     */
    private void fps(){
        
    	PJMove();

    	ejecutarSalto();
        
        panel.setEjeX(ejeX);
        panel.setEjeY(ejeY);
        panel.repaint();

    }
    
    /**
     * Método de inicialización del nuevo nivel. Contiene un bucle (infinito por ahora) que se encarga de ordenar el repintado mediante el llamado
     *  al método fps() cada 11 milisegundos.
     */
    public void arrancar(){

        boolean gameOver = false;
        int puntuacion = 360;
        
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
            	if(!(panel.getArrPosCor()==3)) {
            		panel.setArrPosCor(panel.getArrPosCor()+1);
            	} else {
            		panel.setArrPosCor(0);
            	}
        	}
        	
        	
        	
        	/*
        	 * ESTO SE DEJA COMENTADO A LA ESPERA DE LAS IMPLEMENTACIONES DEFINITIVAS DE ENERGÍAS Y CAMBIOS DE PUNTUACIÓN
        	 * 
        	 * if(puntuacion <= 0 || player.getEnergy() <= 0) gameOver = true;
        	 * 
        	 */

            try {
                Thread.sleep(11);
            } catch (InterruptedException e) {
                System.out.println("Error de interrupción del Thread.sleep en contador="+contador+". Error log: "+e);
            }

            //Test por consola reducción de puntuación basada en el tiempo transcurrido:
            if(contador%90==0){
                puntuacion--;
                System.out.println(puntuacion);
            }
            contador++;
        }
    }
}
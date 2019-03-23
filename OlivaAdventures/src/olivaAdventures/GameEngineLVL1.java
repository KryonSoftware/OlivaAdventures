package olivaAdventures;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Clase de motor del juego. Físicas. Lvl 1.
 * 
 * @author kryon
 */
public class GameEngineLVL1 implements KeyListener {

    private boolean saltando=false,arriba=false,derecha=false,izquierda=false;
    private int contador=0,ejeX=0,ejeY=0,contadorSalto=0,prevY=0,prevX=0;
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

    	
    }
    
    /**
     * Método para leer las booleanas y en función de su estado, alterar los movimientos de nuestro personaje 
     * (sus coordenadas de colocación) ANTES de repintarlo en la pantalla.
     */
    private void PJMove() {
    	
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
    	if(izquierda) {
    		
    		if(!saltando) {
    			
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
	    if(derecha) {
	    	
    		if(!saltando) {

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

                prevY=720-ejeY-35;

            }
            else if(contadorSalto>15) {
            
	            if(!panel.isGround(ejeX, ejeY, prevY,prevX)) {
	            	
	            	 //A partir de este punto comienza a caer, pasamos a comprobar si algo detiene su caída:
	                if(contadorSalto>15&&contadorSalto<20){
	
	                    prevY=720-ejeY-35;
	                    ejeY-=5;
	
	                }
	                else if(contadorSalto>19&&contadorSalto<25){
	
	                    prevY=720-ejeY-35;
	                    ejeY-=10;
	
	                }
	                else if(contadorSalto>24&&contadorSalto<30){
	
	                    prevY=720-ejeY-35;
	                    ejeY-=20;
	
	                }
	                else if(contadorSalto>29&&contadorSalto<36){
	
	                    prevY=720-ejeY-35;
	                    ejeY-=25;
	
	                }
	                else if(contadorSalto>35&&contadorSalto<42){
	
	                    prevY=720-ejeY-35;
	                    ejeY-=30;
	
	                }
	                else if(contadorSalto>41){
	
	                    prevY=720-ejeY-35;
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
        int puntuacion = 100;
        
        while(!gameOver){
        	
        	if(contador%3==0) {
        		
        		fps();
        		
        	}

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
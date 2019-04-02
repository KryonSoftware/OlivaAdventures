package olivaAdventures;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

//import olivaAdventures.Enemy.typeEnemies;

/**
 * Clase de motor del juego. Físicas. Lvl 1.
 * 
 * @author kryon
 */
public class GameEngineLVL1 implements KeyListener {

    private boolean saltando=false,arriba=false,derecha=false,izquierda=false,pausa=false;
    private int contador=0,ejeX=0,ejeY=0,contadorSalto=0,prevY=720-89,prevX=0,cambio=0,respirando=0,compruebaDistanciaSalto=0,animacionesDe8=0,anMons=0,
    		movM11=5,movM12=5;
    private char lastSide='D';
    
    //Inicializamos el panel que va dentro del Frame:
    private PanelLVL1 panel = new PanelLVL1();
    //Instanciamos el player (NO USADO ACTUALMENTE, TODO PARA EL FUTURO):
    private Player player = new Player();

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
            case KeyEvent.VK_ESCAPE:
            	if(pausa) {
            		pausa=false;
            	}
            	else {
            		pausa=true;
            	}
            	break;
            default://Implementar enemyType1_1 el futuro los casos de las teclas de activación de golpe CaC y/o disparo;
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
        	musica.cargarSonidoPistola();
        	break;
            //Implementar enemyType1_1 el futuro los casos de las teclas de activación de golpe CaC y/o disparo
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
    	if((!derecha && !izquierda) || (derecha && izquierda)) {
    		
	    	switch(lastSide) {
	    	
	    	case 'D':
	    		
	    		if(!saltando) {
	    			//Creamos secuencia de la animación de la respiración teniendo enemyType1_1 cuenta el último lado hacia el que miraba
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
    			
    			lastSide='I';
    			
    			//Nos desplazamos enemyType1_1 el eje X y a continuación comprobamos si deberíamos estar cayendo:
            	ejeX-=-panel.isWall(-10);
            	if(panel.isGround(ejeX, ejeY, prevY,prevX)) {
            		
            	}
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
    						panel.setArrPosKeko(1);
    						
    				}
    				cambio++;
    			} else if(cambio==1){cambio=0;}
    			else {cambio=0;}
    			
    			lastSide='D';
    			
    			//Nos desplazamos enemyType1_1 el eje X y a continuación comprobamos si deberíamos estar cayendo:
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
        	 
        	 
        	 //La secuencia de salto consiste enemyType1_1 llamar a panel y decirle cuánto queremos movernos hacia arriba y él nos contesta diciendo cuánto podemos hacerlo
        	  
            if(contadorSalto>=0&&contadorSalto<5) {
            	compruebaDistanciaSalto=panel.isTop(20,ejeY,ejeX,prevX);
                ejeY += compruebaDistanciaSalto;
                //Y si su respuesta es menor a la petición, sabemos que hemos chocado, por lo que pasamos el contador al punto de comenzar a acelerar hacia abajo
                if(!(compruebaDistanciaSalto==20)) {
                	contadorSalto=16;
                }
            }
            else if(contadorSalto>4&&contadorSalto<8){
            	compruebaDistanciaSalto=panel.isTop(10,ejeY,ejeX,prevX);
                ejeY+=compruebaDistanciaSalto;
                if(!(compruebaDistanciaSalto==10)) {
                	contadorSalto=16;
                }
            }
            else if(contadorSalto>7&&contadorSalto<12){
            	compruebaDistanciaSalto=panel.isTop(5,ejeY,ejeX,prevX);
                ejeY+=compruebaDistanciaSalto;
                if(!(compruebaDistanciaSalto==5)) {
                	contadorSalto=16;
                }
            }
            else if(contadorSalto>11&&contadorSalto<15){
            	compruebaDistanciaSalto=panel.isTop(1,ejeY,ejeX,prevX);
                ejeY+=compruebaDistanciaSalto;
                if(!(compruebaDistanciaSalto==1)) {
                	contadorSalto=16;
                }
            }
            else if(contadorSalto==15){
                prevY=720-ejeY;
            }
            else if(contadorSalto>15) {
	            if(!panel.isGround(ejeX, ejeY, prevY,prevX)) {
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

    private void movimientosMonstruos() {

    	if (!Enemies_lvl1.enemies.get(0).isDead()){
			switch (Enemies_lvl1.enemies.get(0).getDecission(panel.getPosIniEnemy1_1()-ejeX+panel.getM11(),670,350,720-ejeY-89)) {
				case 'D':
					movM11+=5;
					panel.setM11(movM11);
					break;
				case 'I':
					movM11-=5;
					panel.setM11(movM11);
					break;
				default:
					player.doDamge(Enemies_lvl1.enemies.get(0));
			}
		}
    	if (!Enemies_lvl1.enemies.get(1).isDead()){
			switch (Enemies_lvl1.enemies.get(1).getDecission(panel.getPosIniEnemy1_2()-ejeX+panel.getM12(),670,350,720-ejeY-89)) {
				case 'D':
					movM12+=5;
					panel.setM12(movM12);
					break;
				case 'I':
					movM12-=5;
					panel.setM12(movM12);
					break;
				default:
					player.doDamge(Enemies_lvl1.enemies.get(1));
			}
		}

    }

    private void pausa() {
   
    	if(pausa) {
    		panel.setPause(true);
    		panel.repaint();
    	}
    	while(pausa) {
    		System.out.println("pausa");
    	}
    	if(!pausa) {
    		panel.setPause(false);
    		panel.repaint();
    	}
    	
    }

    
    /**
     * Método que ejecuta los demás métodos importantes y se encarga de decirle al panel las posiciones X e Y y ordenarle el repintado.
     */
    private void fps(){
    	
    	movimientosMonstruos();
    	
        animacionesOtros();
    	
    	PJMove();

    	ejecutarSalto();
        
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
            	if(!(panel.getArrPosCor()==3)) {
            		panel.setArrPosCor(panel.getArrPosCor()+1);
            	} else {
            		panel.setArrPosCor(0);
            	}
            	if(!(panel.getArrPosReloj()==25)) {
            		panel.setArrPosReloj(panel.getArrPosReloj()+1);
            	} else {
            		panel.setArrPosReloj(0);
            	}
            	//pruebas+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        	}

        	if(puntuacion <= 0 || player.getEnergy() <= 0) {gameOver = true;}

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
        
    }
    
}
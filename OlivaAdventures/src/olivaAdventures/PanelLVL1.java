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
	
	public Entities entities= new Entities();

	//Imágenes
	private BufferedImage fondo,keko_right,keko_right2,keko_right3,keko_right4,keko_stand_right,keko_stand_right2,keko_stand_right3,
	keko_stand_right4,keko_left,keko_left2,keko_left3,keko_left4,keko_stand_left,keko_stand_left2,keko_stand_left3,keko_stand_left4,hud,barraExp100,barraExp75,
	barraExp50,barraExp25,barraExp0,vidas0,vidas1,vidas2,vidas3,monstruo1,monstruo2,monstruo3,monstruo4,plataforma1,plataforma2,
	reloj0,reloj1,reloj2,reloj3,reloj4,reloj5,reloj6,reloj7,reloj8,reloj9,reloj10,reloj11,reloj12,reloj13,reloj14,reloj15,reloj16,reloj17,reloj18,
	reloj19,reloj20,reloj21,reloj22,reloj23,reloj24,reloj25,nube1,nube2,suelo1,suelo2,arbol1,arbol2,arbusto,pausa,cargando,bossAngryLeft1,bossAngryLeft2,
	bossAngryRight1,bossAngryRight2,bossLeft1,bossLeft2,bossLeft3,bossRight1,bossRight2,bossRight3,bossWalkLeft1,bossWalkLeft2,
	bossWalkLeft3,bossWalkLeft4,bossWalkRight1,bossWalkRight2,bossWalkRight3,bossWalkRight4,enemyTwo1,enemyTwo2,enemyTwo3,enemyTwo4,
	murcielago1,murcielago2,murcielago3,murcielago4,oliva_salto_derecha,oliva_salto_izquierda,disparo1,disparo2,disparo3,disparo4,
	keko_disparo_derecha1,keko_disparo_izquierda1,keko_disparo_derecha2,keko_disparo_izquierda2,keko_disparo_inicial_derecha,
	keko_disparo_inicial_izquieda,energia0,energia1,energia2,energia3,energia4,energia5,energia6,energia7,energia8,energia9,
	energia10,energia11,energia12,energia13,energia14,energia15,energia16,energia17,energia18,energia19,energia20,
	energia21,energia22,energia23,energia24,energia25,energia26,energia27,energia28,energia29,energia30,
	energia31,energia32,energia33,energia34,energia35,energia36,energia37,energia38,energia39,energia40,
	energia41,energia42,energia43,energia44,energia45,energia46,energia47,energia48,energia49,energia50,
	energia51,energia52,energia53,energia54,energia55,energia56,energia57,energia58,energia59,energia60,
	energia61,energia62,energia63,energia64,energia65,energia66,energia67,energia68,energia69,energia70,
	energia71,energia72,energia73,energia74,energia75,energia76,energia77,energia78,energia79,energia80,
	energia81,energia82,energia83,energia84,energia85,energia86,energia87,energia88,energia89,energia90,
	energia91,energia92,energia93,energia94,energia95,energia96,energia97,energia98,energia99,energia100;
	
	//Arrays de imágenes
	private BufferedImage[] animKeko = new BufferedImage[24],animBarra=new BufferedImage[5],animMonstruo=new BufferedImage[4], animCorazones=new BufferedImage[4],
			animReloj=new BufferedImage[26],anim_boss = new BufferedImage[18],anim_enemyTwo = new BufferedImage[4],anim_murcielago = new BufferedImage[4],bala = new BufferedImage[4],
							barra_energia = new BufferedImage[101];
	
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
		cargarRaizImagenEnergia();
		cargarRaizImagenesCorazones();
		cargarRaizImagenesMonstruo();
		cargarRaizImagenesReloj();
		cargarRaizImagenesBala();
	    	
	}
	
	/**
	 * Método para cargar las imágenes desde la carpeta de recursos.
	 */
	private void cargarImagenes() {
	
		try { 

			//Sacamos las imágenes desde nuestra carpeta de recursos
			
			//FONDO
			fondo = ImageIO.read(new File("resources/Mapa/fondo.jpg"));

			//FONDO DEL HUD
			hud = ImageIO.read(new File("resources/Hud/hud/proxy.duckduckgo.com.png"));

			//IMÁGENES DEL PERSONAJE PRINCIPAL
			keko_right = ImageIO.read(new File("resources/Personaje/olivaWalkDer/olivaWalkDer1.png"));
			keko_right2 = ImageIO.read(new File("resources/Personaje/olivaWalkDer/olivaWalkDer2.png"));
			keko_right3 = ImageIO.read(new File("resources/Personaje/olivaWalkDer/olivaWalkDer3.png"));
			keko_right4 = ImageIO.read(new File("resources/Personaje/olivaWalkDer/olivaWalkDer4.png"));
			keko_left = ImageIO.read(new File("resources/Personaje/olivaWalkIzq/olivaWalkIzq1.png"));
			keko_left2 = ImageIO.read(new File("resources/Personaje/olivaWalkIzq/olivaWalkIzq2.png"));
			keko_left3 = ImageIO.read(new File("resources/Personaje/olivaWalkIzq/olivaWalkIzq3.png"));
			keko_left4 = ImageIO.read(new File("resources/Personaje/olivaWalkIzq/olivaWalkIzq4.png"));
			keko_stand_right= ImageIO.read(new File("resources/Personaje/olivaDer/oliva1.png"));
			keko_stand_right2= ImageIO.read(new File("resources/Personaje/olivaDer/oliva2.png"));
			keko_stand_right3= ImageIO.read(new File("resources/Personaje/olivaDer/oliva3.png"));
			keko_stand_right4= ImageIO.read(new File("resources/Personaje/olivaDer/oliva4.png"));
			keko_stand_left= ImageIO.read(new File("resources/Personaje/olivaIzq/olivaizq1.png"));
			keko_stand_left2= ImageIO.read(new File("resources/Personaje/olivaIzq/olivaizq2.png"));
			keko_stand_left3= ImageIO.read(new File("resources/Personaje/olivaIzq/olivaizq3.png"));
			keko_stand_left4= ImageIO.read(new File("resources/Personaje/olivaIzq/olivaizq4.png"));
			
			//SALTOS DEL KEKO
			oliva_salto_derecha = ImageIO.read(new File("resources/Personaje/oliva_salto/OlivaSaltoDerecha.png"));
			oliva_salto_izquierda = ImageIO.read(new File("resources/Personaje/oliva_salto/OlivaSaltoIzq.png"));
			
			//DISPAROS DEL KEKO
			keko_disparo_derecha1 = ImageIO.read(new File("resources/Personaje/keko_disparo/olivaDisparaDerecha1.png"));
			keko_disparo_derecha2 = ImageIO.read(new File("resources/Personaje/keko_disparo/olivaDisparaDerecha2.png"));
			keko_disparo_izquierda1 = ImageIO.read(new File("resources/Personaje/keko_disparo/olivaDisparaIzquierda1.png"));
			keko_disparo_izquierda2 = ImageIO.read(new File("resources/Personaje/keko_disparo/olivaDisparaIzquierda2.png"));
			keko_disparo_inicial_derecha = ImageIO.read(new File("resources/Personaje/keko_disparo/olivaDisparaDerechaInicial.png"));
			keko_disparo_inicial_izquieda =  ImageIO.read(new File("resources/Personaje/keko_disparo/olivaDisparaIzquierdaInicial.png"));
			
			//BALAS
			disparo1 = ImageIO.read(new File("resources/Personaje/oliva_disparo/oliva1.png"));
			disparo2 = ImageIO.read(new File("resources/Personaje/oliva_disparo/oliva2.png"));
			disparo3 = ImageIO.read(new File("resources/Personaje/oliva_disparo/oliva3.png"));
			disparo4 = ImageIO.read(new File("resources/Personaje/oliva_disparo/oliva4.png"));
			
			//IMÁGENES DEL RELOJ DE ARENA
			reloj0=ImageIO.read(new File("resources/contadorTiempo/reloj0.png"));
			reloj1=ImageIO.read(new File("resources/contadorTiempo/reloj1.png"));
			reloj2=ImageIO.read(new File("resources/contadorTiempo/reloj2.png"));
			reloj3=ImageIO.read(new File("resources/contadorTiempo/reloj3.png"));
			reloj4=ImageIO.read(new File("resources/contadorTiempo/reloj4.png"));
			reloj5=ImageIO.read(new File("resources/contadorTiempo/reloj5.png"));
			reloj6=ImageIO.read(new File("resources/contadorTiempo/reloj6.png"));
			reloj7=ImageIO.read(new File("resources/contadorTiempo/reloj7.png"));
			reloj8=ImageIO.read(new File("resources/contadorTiempo/reloj8.png"));
			reloj9=ImageIO.read(new File("resources/contadorTiempo/reloj9.png"));
			reloj10=ImageIO.read(new File("resources/contadorTiempo/reloj10.png"));
			reloj11=ImageIO.read(new File("resources/contadorTiempo/reloj11.png"));
			reloj12=ImageIO.read(new File("resources/contadorTiempo/reloj12.png"));
			reloj13=ImageIO.read(new File("resources/contadorTiempo/reloj13.png"));
			reloj14=ImageIO.read(new File("resources/contadorTiempo/reloj14.png"));
			reloj15=ImageIO.read(new File("resources/contadorTiempo/reloj15.png"));
			reloj16=ImageIO.read(new File("resources/contadorTiempo/reloj16.png"));
			reloj17=ImageIO.read(new File("resources/contadorTiempo/reloj17.png"));
			reloj18=ImageIO.read(new File("resources/contadorTiempo/reloj18.png"));
			reloj19=ImageIO.read(new File("resources/contadorTiempo/reloj19.png"));
			reloj20=ImageIO.read(new File("resources/contadorTiempo/reloj20.png"));
			reloj21=ImageIO.read(new File("resources/contadorTiempo/reloj21.png"));
			reloj22=ImageIO.read(new File("resources/contadorTiempo/reloj22.png"));
			reloj23=ImageIO.read(new File("resources/contadorTiempo/reloj23.png"));
			reloj24=ImageIO.read(new File("resources/contadorTiempo/reloj24.png"));
			reloj25=ImageIO.read(new File("resources/contadorTiempo/reloj25.png"));

			//ENERGÍA
			energia0 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia0.png"));
			energia1 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia1.png"));
			energia2 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia2.png"));
			energia3 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia3.png"));
			energia4 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia4.png"));
			energia5 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia5.png"));
			energia6 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia6.png"));
			energia7 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia7.png"));
			energia8 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia8.png"));
			energia9 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia9.png"));
			energia10 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia10.png"));
			energia11 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia11.png"));
			energia12 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia12.png"));
			energia13 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia13.png"));
			energia14 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia14.png"));
			energia15 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia15.png"));
			energia16 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia16.png"));
			energia17 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia17.png"));
			energia18 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia18.png"));
			energia19 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia19.png"));
			energia20 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia20.png"));
			energia21 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia21.png"));
			energia22 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia22.png"));
			energia23 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia23.png"));
			energia24 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia24.png"));
			energia25 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia25.png"));
			energia26 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia26.png"));
			energia27 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia27.png"));
			energia28 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia28.png"));
			energia29 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia29.png"));
			energia30 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia30.png"));
			energia31 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia31.png"));
			energia32 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia32.png"));
			energia33 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia33.png"));
			energia34 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia34.png"));
			energia35 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia35.png"));
			energia36 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia36.png"));
			energia37 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia37.png"));
			energia38 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia38.png"));
			energia39 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia39.png"));
			energia40 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia40.png"));
			energia41 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia41.png"));
			energia42 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia42.png"));
			energia43 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia43.png"));
			energia44 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia44.png"));
			energia45 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia45.png"));
			energia46 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia46.png"));
			energia47 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia47.png"));
			energia48 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia48.png"));
			energia49 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia49.png"));
			energia50 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia50.png"));
			energia51 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia51.png"));
			energia52 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia52.png"));
			energia53 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia53.png"));
			energia54 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia54.png"));
			energia55 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia55.png"));
			energia56 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia56.png"));
			energia57 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia57.png"));
			energia58 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia58.png"));
			energia59 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia59.png"));
			energia60 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia60.png"));
			energia61 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia61.png"));
			energia62 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia62.png"));
			energia63 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia63.png"));
			energia64 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia64.png"));
			energia65 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia65.png"));
			energia66 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia66.png"));
			energia67 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia67.png"));
			energia68 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia68.png"));
			energia69 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia69.png"));
			energia70 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia70.png"));
			energia71 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia71.png"));
			energia72 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia72.png"));
			energia73 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia73.png"));
			energia74 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia74.png"));
			energia75 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia75.png"));
			energia76 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia76.png"));
			energia77 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia77.png"));
			energia78 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia78.png"));
			energia79 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia79.png"));
			energia80 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia80.png"));
			energia81 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia81.png"));
			energia82 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia82.png"));
			energia83 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia83.png"));
			energia84 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia84.png"));
			energia85 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia85.png"));
			energia86 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia86.png"));
			energia87 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia87.png"));
			energia88 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia88.png"));
			energia89 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia89.png"));
			energia90 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia90.png"));
			energia91 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia91.png"));
			energia92 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia92.png"));
			energia93 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia93.png"));
			energia94 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia94.png"));
			energia95 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia95.png"));
			energia96 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia96.png"));
			energia97 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia97.png"));
			energia98 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia98.png"));
			energia99 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia99.png"));
			energia100 = ImageIO.read(new File("resources/Hud/hud/EnergiaNueva/Energia100.png"));


			//IMÁGENES DE LA BARRA DE VIDA
			vidas0=ImageIO.read(new File("resources/Hud/Salud/0vidas.png"));
			vidas1=ImageIO.read(new File("resources/Hud/Salud/1vida.png"));
			vidas2=ImageIO.read(new File("resources/Hud/Salud/2vidas.png"));
			vidas3=ImageIO.read(new File("resources/Hud/Salud/3vidas.png"));

			//IMÁGENES DE LOS MONSTRUOS
			
			//Enemigo tipo 1
			monstruo1 = ImageIO.read(new File("resources/monstruo/monstruo1.png"));
			monstruo2 = ImageIO.read(new File("resources/monstruo/monstruo2.png"));
			monstruo3 = ImageIO.read(new File("resources/monstruo/monstruo3.png"));
			monstruo4 = ImageIO.read(new File("resources/monstruo/monstruo4.png"));
			
			//Enemigo tipo 2
			enemyTwo1 = ImageIO.read(new File("resources/monstruo/Enemigo2/enemigoDos_1.png"));
			enemyTwo2 = ImageIO.read(new File("resources/monstruo/Enemigo2/enemigoDos_2.png"));
			enemyTwo3 = ImageIO.read(new File("resources/monstruo/Enemigo2/enemigoDos_3.png"));
			enemyTwo4 = ImageIO.read(new File("resources/monstruo/Enemigo2/enemigoDos_4.png"));
			
			//Enemigo murciélago
			murcielago1 = ImageIO.read(new File("resources/monstruo/fly_enemy/murcielago1.png"));
			murcielago2 = ImageIO.read(new File("resources/monstruo/fly_enemy/murcielago2.png"));
			murcielago3 = ImageIO.read(new File("resources/monstruo/fly_enemy/murcielago3.png"));
			murcielago4 = ImageIO.read(new File("resources/monstruo/fly_enemy/murcielago4.png"));

			//Enemigo tipo boss
			bossAngryLeft1 = ImageIO.read(new File("resources/monstruo/Boss/Angry/Left/BossAngryLeft1.png"));
			bossAngryLeft2 = ImageIO.read(new File("resources/monstruo/Boss/Angry/Left/BossAngryLeft2.png"));
			bossAngryRight1 = ImageIO.read(new File("resources/monstruo/Boss/Angry/Right/BossAngryRight1.png"));
			bossAngryRight2 = ImageIO.read(new File("resources/monstruo/Boss/Angry/Right/BossAngryRight2.png"));
			bossLeft1 = ImageIO.read(new File("resources/monstruo/Boss/Idle/Left/bossLeft1.png"));
			bossLeft2 = ImageIO.read(new File("resources/monstruo/Boss/Idle/Left/bossLeft2.png"));
			bossLeft3 = ImageIO.read(new File("resources/monstruo/Boss/Idle/Left/bossLeft3.png"));
			bossRight1 = ImageIO.read(new File("resources/monstruo/Boss/Idle/Right/bossRight1.png"));
			bossRight2 = ImageIO.read(new File("resources/monstruo/Boss/Idle/Right/bossRight2.png"));
			bossRight3 = ImageIO.read(new File("resources/monstruo/Boss/Idle/Right/bossRight3.png"));
			bossWalkLeft1 = ImageIO.read(new File("resources/monstruo/Boss/Walking/Left/bossWalkLeft.png"));
			bossWalkLeft2 = ImageIO.read(new File("resources/monstruo/Boss/Walking/Left/bossWalkLeft2.png"));
			bossWalkLeft3 = ImageIO.read(new File("resources/monstruo/Boss/Walking/Left/bossWalkLeft3.png"));
			bossWalkLeft4 = ImageIO.read(new File("resources/monstruo/Boss/Walking/Left/bossWalkLeft4.png"));
			bossWalkRight1 = ImageIO.read(new File("resources/monstruo/Boss/Walking/Right/bossWalkRight1.png"));
			bossWalkRight2 = ImageIO.read(new File("resources/monstruo/Boss/Walking/Right/bossWalkRight2.png"));
			bossWalkRight3 = ImageIO.read(new File("resources/monstruo/Boss/Walking/Right/bossWalkRight3.png"));
			bossWalkRight4 = ImageIO.read(new File("resources/monstruo/Boss/Walking/Right/bossWalkRight4.png"));


			//IMAGENES PLATAFORMAS: TODO importar todas las futuras imagenes
			plataforma1 = ImageIO.read(new File("resources/plataforma/plataformaFINAL.png"));
			plataforma2 = ImageIO.read(new File("resources/plataforma/plataformaFINAL.png"));

			//IMAGENES NUEBES
			nube1=ImageIO.read(new File("resources/Mapa/Nubes/nubeGrande.png"));
			nube2=ImageIO.read(new File("resources/Mapa/Nubes/nubePeque.png"));

			//IMAGENES ARBOLES
			arbol1=ImageIO.read(new File("resources/Mapa/Arboles/arbolGrande.png"));
			arbol2=ImageIO.read(new File("resources/Mapa/Arboles/arbolMediano.png"));
			arbusto=ImageIO.read(new File("resources/Mapa/Arboles/arbustito.png"));

			//IMAGENES SUELOS
			suelo1=ImageIO.read(new File("resources/Suelos/cuadrado1.png"));
			suelo2=ImageIO.read(new File("resources/Suelos/cuadrado2.png"));

			//IMAGEN PAUSA
			pausa=ImageIO.read(new File("resources/Hud/pausa.png"));

			cargando=ImageIO.read(new File("resources/Menu/OlivaAdventuresLibro.png"));
			
          
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
		animKeko[16]=oliva_salto_izquierda;
		animKeko[17]=oliva_salto_derecha;
		animKeko[18]=keko_disparo_derecha1;
		animKeko[19]=keko_disparo_derecha2;
		animKeko[20]=keko_disparo_izquierda1;
		animKeko[21]=keko_disparo_izquierda2;
		animKeko[22]=keko_disparo_inicial_derecha;
		animKeko[23]=keko_disparo_inicial_izquieda;
		
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
	 * Método encargado de rellenar la raíz de animaciones de la barra-rayo.
	 */
	private void cargarRaizImagenEnergia() {

		barra_energia[0] = energia0;
		barra_energia[1] = energia1;
		barra_energia[2] = energia2;
		barra_energia[3] = energia3;
		barra_energia[4] = energia4;
		barra_energia[5] = energia5;
		barra_energia[6] = energia6;
		barra_energia[7] = energia7;
		barra_energia[8] = energia8;
		barra_energia[9] = energia9;
		barra_energia[10] = energia10;
		barra_energia[11] = energia11;
		barra_energia[12] = energia12;
		barra_energia[13] = energia13;
		barra_energia[14] = energia14;
		barra_energia[15] = energia15;
		barra_energia[16] = energia16;
		barra_energia[17] = energia17;
		barra_energia[18] = energia18;
		barra_energia[19] = energia19;
		barra_energia[20] = energia20;
		barra_energia[21] = energia21;
		barra_energia[22] = energia22;
		barra_energia[23] = energia23;
		barra_energia[24] = energia24;
		barra_energia[25] = energia25;
		barra_energia[26] = energia26;
		barra_energia[27] = energia27;
		barra_energia[28] = energia28;
		barra_energia[29] = energia29;
		barra_energia[30] = energia30;
		barra_energia[31] = energia31;
		barra_energia[32] = energia32;
		barra_energia[33] = energia33;
		barra_energia[34] = energia34;
		barra_energia[35] = energia35;
		barra_energia[36] = energia36;
		barra_energia[37] = energia37;
		barra_energia[38] = energia38;
		barra_energia[39] = energia39;
		barra_energia[40] = energia40;
		barra_energia[41] = energia41;
		barra_energia[42] = energia42;
		barra_energia[43] = energia43;
		barra_energia[44] = energia44;
		barra_energia[45] = energia45;
		barra_energia[46] = energia46;
		barra_energia[47] = energia47;
		barra_energia[48] = energia48;
		barra_energia[49] = energia49;
		barra_energia[50] = energia50;
		barra_energia[51] = energia51;
		barra_energia[52] = energia52;
		barra_energia[53] = energia53;
		barra_energia[54] = energia54;
		barra_energia[55] = energia55;
		barra_energia[56] = energia56;
		barra_energia[57] = energia57;
		barra_energia[58] = energia58;
		barra_energia[59] = energia59;
		barra_energia[60] = energia60;
		barra_energia[61] = energia61;
		barra_energia[62] = energia62;
		barra_energia[63] = energia63;
		barra_energia[64] = energia64;
		barra_energia[65] = energia65;
		barra_energia[66] = energia66;
		barra_energia[67] = energia67;
		barra_energia[68] = energia68;
		barra_energia[69] = energia69;
		barra_energia[70] = energia70;
		barra_energia[71] = energia71;
		barra_energia[72] = energia72;
		barra_energia[73] = energia73;
		barra_energia[74] = energia74;
		barra_energia[75] = energia75;
		barra_energia[76] = energia76;
		barra_energia[77] = energia77;
		barra_energia[78] = energia78;
		barra_energia[79] = energia79;
		barra_energia[80] = energia80;
		barra_energia[81] = energia81;
		barra_energia[82] = energia82;
		barra_energia[83] = energia83;
		barra_energia[84] = energia84;
		barra_energia[85] = energia85;
		barra_energia[86] = energia86;
		barra_energia[87] = energia87;
		barra_energia[88] = energia88;
		barra_energia[89] = energia89;
		barra_energia[90] = energia90;
		barra_energia[91] = energia91;
		barra_energia[92] = energia92;
		barra_energia[93] = energia93;
		barra_energia[94] = energia94;
		barra_energia[95] = energia95;
		barra_energia[96] = energia96;
		barra_energia[97] = energia97;
		barra_energia[98] = energia98;
		barra_energia[99] = energia99;
		barra_energia[100] = energia100;

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
	 * Método relleno imágenes de laa bala en movimiento.
	 */
	private void cargarRaizImagenesBala(){
		//Bala
		bala[0] = disparo1;
		bala[1] = disparo2;
		bala[2] = disparo3;
		bala[3] = disparo4;

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
			            					entities.enemies.get(posLista).setPosYEnemy(y-51);
			            				}
			            				else {
			            					entities.enemies.get(posLista).setPosYEnemy(y-49);
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
	    					if(entities.enemies.get(posLista).isJumping()) {
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
			    		
			    		if(entities.enemies.get(posLista).getPosXEnemy()-this.x + entities.enemies.get(posLista).getMoveEnemy()+50>=z&&entities.enemies.get(posLista).getPosXEnemy()-this.x + entities.enemies.get(posLista).getMoveEnemy()+20<=(z+g)) {
			    			
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
		
		for(int x=0;x<entities.enemies.size();x++) {
		
			if(!entities.enemies.get(x).isDead()) {
				g.drawImage(animMonstruo[arrPosMonstruo], entities.enemies.get(x).getPosXEnemy() -this.x + entities.enemies.get(x).getMoveEnemy(),
						entities.enemies.get(x).getPosYEnemy(), 70, 50, this);
				addPlatformToList(entities.enemies.get(x).getPosXEnemy() - this.x + entities.enemies.get(x).getMoveEnemy(),
						entities.enemies.get(x).getPosYEnemy(), 70, 50, Tipo.ENEMY);
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
		//Aquí habrá que programar las reproducciones las barras de energía
		g.drawImage(barra_energia[50], 10, 790, 300, 160, this);
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

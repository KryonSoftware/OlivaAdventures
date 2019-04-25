package olivaAdventures;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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
public class Panel extends JPanel {

	private ArrayList<Platform> listaPlataformas=new ArrayList<>();
	
	private Musica fxKeko=new Musica();
	
	public Entities entities= new Entities();

	//Imágenes
	private BufferedImage fondo,keko_right,keko_right2,keko_right3,keko_right4,keko_stand_right,keko_stand_right2,keko_stand_right3,
	keko_stand_right4,keko_left,keko_left2,keko_left3,keko_left4,keko_stand_left,keko_stand_left2,keko_stand_left3,keko_stand_left4,hud,
	vidas0,vidas1,vidas2,vidas3,monstruo1,monstruo2,monstruo3,monstruo4,plataforma1,reloj0,reloj1,reloj2,reloj3,reloj4,reloj5,reloj6,
	reloj7,reloj8,reloj9,reloj10,reloj11,reloj12,reloj13,reloj14,reloj15,reloj16,reloj17,reloj18,
	reloj19,reloj20,reloj21,reloj22,reloj23,reloj24,reloj25,nube1,nube2,suelo1,suelo2,suelo3,sueloInicio,sueloFinal,arbol1,arbol2,arbol3,arbolPino,
	arbusto,pausa,cargando,bossAngryLeft1,bossAngryLeft2,
	bossAngryRight1,bossAngryRight2,bossLeft1,bossLeft2,bossLeft3,bossRight1,bossRight2,bossRight3,bossWalkLeft1,bossWalkLeft2,
	bossWalkLeft3,bossWalkLeft4,bossWalkRight1,bossWalkRight2,bossWalkRight3,bossWalkRight4,enemyTwo1,enemyTwo2,enemyTwo3,enemyTwo4,
	murcielago1,murcielago2,murcielago3,murcielago4,murcielago5,murcielago6,murcielago7,murcielago8,
	oliva_salto_derecha,oliva_salto_izquierda,disparo1,disparo2,disparo3,disparo4,
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
	energia91,energia92,energia93,energia94,energia95,energia96,energia97,energia98,energia99,energia100,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,
	s,t,u,v,w,equis,yGriega,z,muerto,letraUno,letraDos,vivo,creditosScroll,box;
	
	//Arrays de imágenes
	private BufferedImage[] animKeko = new BufferedImage[24],animMonstruo=new BufferedImage[4], animCorazones=new BufferedImage[4],
			animReloj=new BufferedImage[26],animBoss = new BufferedImage[24],bala = new BufferedImage[4],
							barra_energia = new BufferedImage[101],animFly = new BufferedImage[8],animLlama = new BufferedImage[8];
	
    private int x,y,arrPosKeko=2,arrPosBarra=4,arrPosReloj=0,arrPosMonstruo=0,avanceDisparo,alturaDisparo,letra,posicionLetra=350,posPrimeraLetra,posSegundaLetra,
    		altoLetraUno,altoLetraDos,ejeYLetraUno,ejeYLetraDos,posicionBala,arrPosFly=0,arrPosLlama=0,arrPosBoss=0,animaciones=0,anMons=0,lado=0,estados=0,movCreditos=0;
    
    public String nombreElegido="",tercLetra="",segLetra="";
    
    private char direccionDisparo='D';
    
    private long momentoDisparo=0;
    
    private boolean pause=false,disparo,disparado=false,impacto=false,loading=false,pedirNombre=false,primeraLetra=false,segundaLetra=false,
    		terceraLetra=false,ganador=false,creditos=false;
    
    //Instanciamos el player:
    public Player keko = new Player(350,720-89);
    
    //Getters y Setters
    public boolean isPause() {return pause;}

	public void setPause(boolean pause) {this.pause = pause;}

	public int getLetra() {return letra;}

	public void setLetra(int letra) {this.letra = letra;}

	public boolean isPedirNombre() {return pedirNombre;}

	public void setPedirNombre(boolean pedirNombre) {this.pedirNombre = pedirNombre;}

	public int getArrPosFly() {return arrPosFly;}

	public void setArrPosFly(int arrPosFly) {this.arrPosFly = arrPosFly;}

	public boolean isDisparo() {return disparo;}

	public void setDisparo(boolean disparo) {this.disparo = disparo;}

	public long getMomentoDisparo() {return momentoDisparo;}

	public void setMomentoDisparo(long contador) {this.momentoDisparo = contador;}

	public int getArrPosMonstruo() {return arrPosMonstruo;}

	public void setArrPosMonstruo(int arrPosMonstruo) {this.arrPosMonstruo = arrPosMonstruo;}

	public int getArrPosBoss() {return arrPosBoss;}

	public void setArrPosBoss(int arrPosBoss) {this.arrPosBoss = arrPosBoss;}

	public int getArrPosLlama() {return arrPosLlama;}

	public void setArrPosLlama(int arrPosLlama) {this.arrPosLlama = arrPosLlama;}

	public boolean isLoading() {return loading;}

	public void setLoading(boolean loading) {this.loading = loading;}

	public String getNombreElegido() {return nombreElegido;}

	public void setNombreElegido(String nombreElegido) {this.nombreElegido = nombreElegido;}

	public int getArrPosReloj() {return arrPosReloj;}

	public void setArrPosReloj(int arrPosReloj) {this.arrPosReloj = arrPosReloj;}

	public int getArrPosBarra() {return arrPosBarra;}

	public void setArrPosBarra(int arrPosBarra) {this.arrPosBarra = arrPosBarra;}

	public int getArrPosKeko() {return arrPosKeko;}

	public void setArrPosKeko(int arrPosKeko) {this.arrPosKeko = arrPosKeko;}

	public boolean isGanador() {return ganador;}

	public void setGanador(boolean ganador) {this.ganador = ganador;}

	public void setEjeX(int x){this.x=x;}

    public void setEjeY(int y){this.y=y;}
    
    public boolean isDisparado() {return disparado;}

	public void setDisparado(boolean disparado) {this.disparado = disparado;}

	public boolean isCreditos() {return creditos;}

	public void setCreditos(boolean creditos) {this.creditos = creditos;}

	public int getMovCreditos() {return movCreditos;}

	public void setMovCreditos(int movCreditos) {this.movCreditos = movCreditos;}

	public int getEjeX() {return this.x;}

    public int getEjeY() {return this.y;}
    
    
    /**
     * Constructor del panel. Nos aseguramos de que cada vez que sea
     *  instanciado va a cargar los buffer de las imágenes que le pertoque.
     */
	public Panel() {

		cargarImagenes();
		cargarRaizImagenesKeko();
		cargarRaizImagenEnergia();
		cargarRaizImagenesCorazones();
		cargarRaizImagenesMonstruo();
		cargarRaizImagenesReloj();
		cargarRaizImagenesBala();
		fxKeko.cargarOuch();
	    	
	}
	
	/**
	 * Método para cargar las imágenes desde la carpeta de recursos.
	 */
	private void cargarImagenes() {
	
		try { 

			//Sacamos las imágenes desde nuestra carpeta de recursos
			
			//FONDO
			fondo = ImageIO.read(new File("resources/Mapa/fondo.jpg"));
			muerto = ImageIO.read(new File("resources/Menu/MUERTO.png"));
			vivo = ImageIO.read(new File("resources/Mapa/fondo.jpg"));
			creditosScroll = ImageIO.read(new File("resources/Menu/fondoScores.jpg"));

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
			murcielago5 = ImageIO.read(new File("resources/monstruo/fly_enemy/murcielago5.png"));
			murcielago6 = ImageIO.read(new File("resources/monstruo/fly_enemy/murcielago6.png"));
			murcielago7 = ImageIO.read(new File("resources/monstruo/fly_enemy/murcielago7.png"));
			murcielago8 = ImageIO.read(new File("resources/monstruo/fly_enemy/murcielago8.png"));

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


			//IMÁGENES PLATAFORMAS
			plataforma1 = ImageIO.read(new File("resources/plataforma/plataformaFINAL.png"));

			//IMÁGENES NUBES
			nube1=ImageIO.read(new File("resources/Mapa/Nubes/nubeGrande.png"));
			nube2=ImageIO.read(new File("resources/Mapa/Nubes/nubePeque.png"));

			//IMÁGENES ÁBOLES
			arbol1=ImageIO.read(new File("resources/Mapa/Arboles/arbolGrande.png"));
			arbol2=ImageIO.read(new File("resources/Mapa/Arboles/arbolMediano.png"));
			arbusto=ImageIO.read(new File("resources/Mapa/Arboles/arbustito.png"));
			arbol3=ImageIO.read(new File("resources/Mapa/Arboles/arbustito.png"));
			arbolPino=ImageIO.read(new File("resources/Mapa/Arboles/arbustito.png"));

			//IMÁGENES SUELOS
			suelo1=ImageIO.read(new File("resources/Suelos/cuadrado1.png"));
			suelo2=ImageIO.read(new File("resources/Suelos/cuadrado2.png"));
			suelo3=ImageIO.read(new File("resources/Suelos/cuadrado3.png"));
			sueloInicio=ImageIO.read(new File("resources/Suelos/cuadradoinicio.png"));
			sueloFinal=ImageIO.read(new File("resources/Suelos/cuadradofinal.png"));

			//IMAGEN PAUSA
			pausa=ImageIO.read(new File("resources/Hud/pausa.png"));
			cargando=ImageIO.read(new File("resources/Menu/OlivaAdventuresLibro.png"));
			
			//IMÁGENES LETRAS
			a=ImageIO.read(new File("resources/Menu/NumerosyLetras/A.png"));
			b=ImageIO.read(new File("resources/Menu/NumerosyLetras/B.png"));
			c=ImageIO.read(new File("resources/Menu/NumerosyLetras/C.png"));
			d=ImageIO.read(new File("resources/Menu/NumerosyLetras/D.png"));
			e=ImageIO.read(new File("resources/Menu/NumerosyLetras/E.png"));
			f=ImageIO.read(new File("resources/Menu/NumerosyLetras/F.png"));
			g=ImageIO.read(new File("resources/Menu/NumerosyLetras/G.png"));
			h=ImageIO.read(new File("resources/Menu/NumerosyLetras/H.png"));
			i=ImageIO.read(new File("resources/Menu/NumerosyLetras/L.png"));
			j=ImageIO.read(new File("resources/Menu/NumerosyLetras/J.png"));
			k=ImageIO.read(new File("resources/Menu/NumerosyLetras/K.png"));
			l=ImageIO.read(new File("resources/Menu/NumerosyLetras/L.png"));
			m=ImageIO.read(new File("resources/Menu/NumerosyLetras/M.png"));
			n=ImageIO.read(new File("resources/Menu/NumerosyLetras/N.png"));
			o=ImageIO.read(new File("resources/Menu/NumerosyLetras/O.png"));
			p=ImageIO.read(new File("resources/Menu/NumerosyLetras/P.png"));
			q=ImageIO.read(new File("resources/Menu/NumerosyLetras/Q.png"));
			r=ImageIO.read(new File("resources/Menu/NumerosyLetras/R.png"));
			s=ImageIO.read(new File("resources/Menu/NumerosyLetras/S.png"));
			t=ImageIO.read(new File("resources/Menu/NumerosyLetras/T.png"));
			u=ImageIO.read(new File("resources/Menu/NumerosyLetras/U.png"));
			v=ImageIO.read(new File("resources/Menu/NumerosyLetras/V.png"));
			w=ImageIO.read(new File("resources/Menu/NumerosyLetras/W.png"));
			equis=ImageIO.read(new File("resources/Menu/NumerosyLetras/X.png"));
			yGriega=ImageIO.read(new File("resources/Menu/NumerosyLetras/Y.png"));
			z=ImageIO.read(new File("resources/Menu/NumerosyLetras/Z.png"));
			
			//Imágenes cajas
			box=ImageIO.read(new File("resources/boxes/box_2d.png"));
			
			
          
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
		animKeko[18]=keko_disparo_inicial_derecha;
		animKeko[19]=keko_disparo_derecha1;
		animKeko[20]=keko_disparo_derecha2;
		animKeko[21]=keko_disparo_inicial_izquieda;
		animKeko[22]=keko_disparo_izquierda1;
		animKeko[23]=keko_disparo_izquierda2;
		
	}
	
	/**
	 * Método encargado de rellenar la raíz de movimientos del monstruo POW.
	 */
	private void cargarRaizImagenesMonstruo() {
		
		//Pows
		animMonstruo[0]=monstruo1;
		animMonstruo[1]=monstruo2;
		animMonstruo[2]=monstruo3;
		animMonstruo[3]=monstruo4;
		
		//Murciélago
		animFly[0]=murcielago1;
		animFly[1]=murcielago2;
		animFly[2]=murcielago3;
		animFly[3]=murcielago4;
		animFly[4]=murcielago5;
		animFly[5]=murcielago6;
		animFly[6]=murcielago7;
		animFly[7]=murcielago8;
		
		//Llamitas
		animLlama[0]=enemyTwo1;
		animLlama[1]=enemyTwo2;
		animLlama[2]=enemyTwo3;
		animLlama[3]=enemyTwo4;
		
		//Boss
		animBoss[0]=bossAngryLeft1;
		animBoss[1]=bossAngryLeft2;
		animBoss[2]=bossAngryLeft1;
		animBoss[3]=bossAngryLeft2;
		animBoss[4]=bossAngryRight1;
		animBoss[5]=bossAngryRight2;
		animBoss[6]=bossAngryRight1;
		animBoss[7]=bossAngryRight2;
		animBoss[8]=bossLeft1;
		animBoss[9]=bossLeft2;
		animBoss[10]=bossLeft3;
		animBoss[11]=bossLeft2;
		animBoss[12]=bossRight1;
		animBoss[13]=bossRight2;
		animBoss[14]=bossRight3;
		animBoss[15]=bossRight2;
		animBoss[16]=bossWalkLeft1;
		animBoss[17]=bossWalkLeft2;
		animBoss[18]=bossWalkLeft3;
		animBoss[19]=bossWalkLeft4;
		animBoss[20]=bossWalkRight1;
		animBoss[21]=bossWalkRight2;
		animBoss[22]=bossWalkRight3;
		animBoss[23]=bossWalkRight4;
		
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
	
	public void reset(int nuevasVidas) {
		
		entities=new Entities();
		keko=new Player(350,720-89);
		keko.setLives(nuevasVidas);
		x=0;
		
	}

	
	/**
     * Método para generar y añadir una nueva plataforma a la lista, que es borrada a cada repintado.
     * @param ejeX
     * @param ejeY
     * @param ancho
     * @param alto
	 * @param posListaEnemies 
     */
    public void addPlatformToList(int ejeX,int ejeY,int ancho,int alto,Tipo tipo, int posListaEnemies){

        listaPlataformas.add(new Platform(ejeX,ejeY,ancho,alto,tipo,posListaEnemies));

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
	        			
	        		default://Los pow:
	        			
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

    		if(listaPlataformas.get(x).getTipo()==Tipo.BOTH || listaPlataformas.get(x).getTipo()==Tipo.ENEMY || 
    				listaPlataformas.get(x).getTipo()==Tipo.BALA || listaPlataformas.get(x).getTipo()==Tipo.PLAYER
    				|| listaPlataformas.get(x).getTipo()==Tipo.BOX) {

    			if(!foundWall) {
	    			
	    			z=listaPlataformas.get(x).getEjeX();
	    			y=listaPlataformas.get(x).getEjeY();
	    			g=listaPlataformas.get(x).getAncho();
	    			k=listaPlataformas.get(x).getAlto();
	    			
	    			switch(entidad) {
	    			
	    			case 1: //Caso keko:
	    				
	    				if(!(listaPlataformas.get(x).getTipo()==Tipo.PLAYER)) {
	
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
	    				
	    				}
	    				
	    				break;
	    				
	    			case 42: //Caso bala:
	    				
	    				if(listaPlataformas.get(x).getTipo()==Tipo.ENEMY || listaPlataformas.get(x).getTipo()==Tipo.BOTH || listaPlataformas.get(x).getTipo()==Tipo.BOX) {
	    			
	    					if(!(listaPlataformas.get(x).getTipo()==Tipo.PLAYER)) {
	    						
	    						if(!(listaPlataformas.get(x).getTipo()==Tipo.BALA)) {
		    						
			    					if(((ejeX+anchoDerecha+s>=z) && (ejeX+s<=(z+g))) || (((ejeX+s<=z) && (ejeX+s>z)) && s>0) || ((ejeX+s>=z+g) && (ejeX+s<=z+g)) && s<0) {
			
			    						if(((ejeYPies)>y)&&((ejeYCabeza)<=(y+k))){
			
			    							switch(listaPlataformas.get(x).getTipo()) {
			    							
			    							case BOTH:
			    								colision-= s>0 ? ejeX-(z-s) : ejeX-anchoIzquierda-(z+g-s);
			    								foundWall=true;
			    								impacto=true;
			    								
			    								break;
			    								
			    							case ENEMY:
			    								
			    								keko.doDamage(entities.enemies.get(listaPlataformas.get(x).getPosListaEnemies()));
			    								colision-= s>0 ? ejeX-(z-s) : ejeX-anchoIzquierda-(z+g-s);
			    								foundWall=true;
			    								impacto=true;
			    								
			    								break;
			    								
			    							case BOX:
			    								
			    								keko.doBoxDamage(entities.cajas.get(listaPlataformas.get(x).getPosListaEnemies()));
			    								colision-= s>0 ? ejeX-(z-s) : ejeX-anchoIzquierda-(z+g-s);
			    								foundWall=true;
			    								impacto=true;
			    								
			    								
											default:;
											
			    							}
			    							
			    						}
			    						
			    					}
		    					
	    						}
	    					
	    					}
	    					
	    				}
	    				
	    				break;
	    				
	    			default:
	    				
	    				if(!(listaPlataformas.get(x).getTipo()==Tipo.BALA) && (!(listaPlataformas.get(x).getTipo()==Tipo.BOX))) {
		    				
		    				if(!(z==ejeX) ||  !(listaPlataformas.get(x).getTipo()==Tipo.ENEMY)) {
		
		        				if(!(y==ejeYCabeza && z==ejeX) || !(listaPlataformas.get(x).getTipo()==Tipo.ENEMY)) {
	        							
	    							if((ejeX+anchoDerecha+s>=z-35&&ejeX+s<=(z+g-15))) {
	    								
		        						if(((ejeYPies)>y)&&((ejeYCabeza)<(y+k))){
		        							
	        								int correccionDerecha=0,correccionIzquierda=0;
	        								
	        								switch(entities.enemies.get(posLista).getTypeEnemy()) {
	        								case "1":
	        									correccionDerecha=10;
	        									correccionIzquierda=30;
	        									break;
	        								case "2":
	        									correccionDerecha=0;
	        									correccionIzquierda=70;
	        									break;
	        								case "boss":
	        									correccionDerecha=10;
	        									correccionIzquierda=260;
	        									break;
	        								case "fly":
	        									correccionDerecha=10;
	        									correccionIzquierda=30;
	        									break;
	        									default:;
	        								}
		        							
		        							//Si choca contra el keko le quitamos vida, y en el caso del volador, colisionamos contra él
		        							
		        							if(listaPlataformas.get(x).getTipo()==Tipo.PLAYER) {
		        								entities.enemies.get(posLista).doDamage(keko);
		        								
		        								try {
		        									fxKeko.ouch();
		        								}catch(Exception e) {}
		        								
		        								if(entities.enemies.get(posLista).getTypeEnemy().equals("fly")) {
		        									
		        									if(s>0) {
				        								
				        								if(ejeX<z) {
				        									
				        									colision-=ejeX+anchoIzquierda-(z-s-correccionDerecha);
				        									foundWall=true;
				        									
				        								}
				        								else {
				        									
				        									colision-=ejeX-anchoDerecha-(z+g-s-correccionIzquierda);
				        									foundWall=true;
				        									
				        								}
				        								
				        							}
				        							else {
				        								
				        								if(ejeX>z) {
				        									
				        									colision-=ejeX-anchoDerecha-(z+g-s-correccionIzquierda);
				        									foundWall=true;
				        									
				        								}
				        								else {
				        									
				        									colision-=ejeX+anchoIzquierda-(z-s-correccionDerecha);
				        									foundWall=true;
				        									
				        								}
				        								
				        							}
		        									
		        								}
		        							}
		        							else {
		        								
		        								if(!(entities.enemies.get(posLista).getTypeEnemy().equals("boss")) &&
		        										!(entities.enemies.get(posLista).getTypeEnemy().equals("fly")) || 
		        										listaPlataformas.get(x).getTipo()==Tipo.BOTH) {
		        									
		        									if(s>0) {
				        								
				        								if(ejeX<z) {
				        									
				        									colision-=ejeX+anchoIzquierda-(z-s-correccionDerecha);
				        									foundWall=true;
				        									
				        								}
				        								else {
				        									if(!(entities.enemies.get(posLista).getTypeEnemy().equals("boss"))) {
					        									colision-=ejeX-anchoDerecha-(z+g-s-correccionIzquierda);
					        									foundWall=true;
				        									}
				        									
				        								}
				        								
				        							}
				        							else {
				        								
				        								if(ejeX>z) {
				        									
				        									colision-=ejeX-anchoDerecha-(z+g-s-correccionIzquierda);
				        									foundWall=true;
				        									
				        								}
				        								else {
				        									if(!(entities.enemies.get(posLista).getTypeEnemy().equals("boss"))) {
				        										colision-=ejeX+anchoIzquierda-(z-s-correccionDerecha);
					        									foundWall=true;
				        									}
				        									
				        								}
				        								
				        							}
		        									
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
    private void movimientoBala(Graphics g,boolean disparo) {
    	
    	switch(posicionBala) {
    	case 0:
    		posicionBala=1;
    		break;
    	case 1:
    		posicionBala=2;
    		break;
    	case 2:
    		posicionBala=3;
    		break;
    	case 3:
    		posicionBala=0;
    		break;
    		default:;
    		
    	}
    	
    	//Variables de control de estado del disparo/bala
    	if(disparo) {
    		
    		if(!disparado) {
    			
    			//si miraba hacia un lado u otro al disparar
	    		switch(keko.getLastSide()) {
	    		
	    		case 'D':
	    			g.drawImage(bala[posicionBala],keko.getPosXPlayer()+30,keko.getPosYPlayer()+50-y,10,10,this);
	        		addPlatformToList(keko.getPosXPlayer()+30,keko.getPosYPlayer()+40-y,10,30, Tipo.BALA);
	        		direccionDisparo='D';
	    			break;
	    		case 'I':
	    			g.drawImage(bala[posicionBala],keko.getPosXPlayer()-10,keko.getPosYPlayer()+50-y,10,10,this);
	        		addPlatformToList(keko.getPosXPlayer()-10,keko.getPosYPlayer()+40-y,10,30, Tipo.BALA);
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
	    				g.drawImage(bala[posicionBala],keko.getPosXPlayer()+20+avanceDisparo,keko.getPosYPlayer()+50-alturaDisparo,10,10,this);
		        		addPlatformToList(keko.getPosXPlayer()+20+avanceDisparo,keko.getPosYPlayer()+40-alturaDisparo,10,30, Tipo.BALA);
	    				break;
	    			case'I':
	    				avanceDisparo+=isWall(42,1,-30,keko.getPosXPlayer()-10+avanceDisparo,keko.getPosYPlayer()+50-alturaDisparo,
	    						keko.getPosYPlayer()+50-alturaDisparo+20,10,0);
	    				g.drawImage(bala[posicionBala],keko.getPosXPlayer()-20+avanceDisparo,keko.getPosYPlayer()+50-alturaDisparo,10,10,this);
		        		addPlatformToList(keko.getPosXPlayer()+20+avanceDisparo,keko.getPosYPlayer()+40-alturaDisparo,10,30, Tipo.BALA);
	    				break;
	    			
	    			}
    			
    			}
    			
    		}
    		
    	}
    	
    }
    
    /**
     * Método para ir pintando las letras que vaya eligiendo el jugador para poner su nombre.
     * @param g
     * @param letra
     */
    private void meterNombrePuntuacion(Graphics g, int letra) {
    	
    	switch(letra){
    	
    	case KeyEvent.VK_A:
    		g.drawImage(a,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=a;
    			nombreElegido="A";
    		}
    		if(!segundaLetra) {
    			letraDos=a;
    			segLetra="A";
    		}
    		else {
    			tercLetra="A";
    		}
    		break;
    	case KeyEvent.VK_B:
    		g.drawImage(b,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=b;
    			nombreElegido="B";
    		}
    		if(!segundaLetra) {
    			letraDos=b;
    			segLetra="B";
    		}
    		else {
    			tercLetra="B";
    		}
    		break;
    	case KeyEvent.VK_C:
    		g.drawImage(c,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=c;
    			nombreElegido="C";
    		}
    		if(!segundaLetra) {
    			letraDos=c;
    			segLetra="C";
    		}
    		else {
    			tercLetra="C";
    		}
    		break;
    	case KeyEvent.VK_D:
    		g.drawImage(d,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=d;
    			nombreElegido="D";
    		}
    		if(!segundaLetra) {
    			letraDos=d;
    			segLetra="D";
    		}
    		else {
    			tercLetra="D";
    		}
    		break;
    	case KeyEvent.VK_E:
    		g.drawImage(e,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=e;
    			nombreElegido="E";
    		}
    		if(!segundaLetra) {
    			letraDos=e;
    			segLetra="E";
    		}
    		else {
    			tercLetra="E";
    		}
    		break;
    	case KeyEvent.VK_F:
    		g.drawImage(f,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=f;
    			nombreElegido="F";
    		}
    		if(!segundaLetra) {
    			letraDos=f;
    			segLetra="F";
    		}
    		else {
    			tercLetra="F";
    		}
    		break;
    	case KeyEvent.VK_G:
    		g.drawImage(this.g,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=this.g;
    			nombreElegido="G";
    		}
    		if(!segundaLetra) {
    			letraDos=this.g;
    			segLetra="G";
    		}
    		else {
    			tercLetra="G";
    		}
    		break;
    	case KeyEvent.VK_H:
    		g.drawImage(h,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=h;
    			nombreElegido="H";
    		}
    		if(!segundaLetra) {
    			letraDos=h;
    			segLetra="H";
    		}
    		else {
    			tercLetra="H";
    		}
    		break;
    	case KeyEvent.VK_I:
    		g.drawImage(i,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=i;
    			nombreElegido="I";
    		}
    		if(!segundaLetra) {
    			letraDos=i;
    			segLetra="I";
    		}
    		else {
    			tercLetra="I";
    		}
    		break;
    	case KeyEvent.VK_J:
    		g.drawImage(j,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=j;
    			nombreElegido="J";
    		}
    		if(!segundaLetra) {
    			letraDos=j;
    			segLetra="J";
    		}
    		else {
    			tercLetra="J";
    		}
    		break;
    	case KeyEvent.VK_K:
    		g.drawImage(k,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=k;
    			nombreElegido="K";
    		}
    		if(!segundaLetra) {
    			letraDos=k;
    			segLetra="K";
    		}
    		else {
    			tercLetra="K";
    		}
    		break;
    	case KeyEvent.VK_L:
    		g.drawImage(l,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=l;
    			nombreElegido="L";
    		}
    		if(!segundaLetra) {
    			letraDos=l;
    			segLetra="L";
    		}
    		else {
    			tercLetra="L";
    		}
    		break;
    	case KeyEvent.VK_M:
    		g.drawImage(m,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=m;
    			nombreElegido="M";
    		}
    		if(!segundaLetra) {
    			letraDos=m;
    			segLetra="M";
    		}
    		else {
    			tercLetra="M";
    		}
    		break;
    	case KeyEvent.VK_N:
    		g.drawImage(n,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=n;
    			nombreElegido="N";
    		}
    		if(!segundaLetra) {
    			letraDos=n;
    			segLetra="N";
    		}
    		else {
    			tercLetra="N";
    		}
    		break;
    	case KeyEvent.VK_O:
    		g.drawImage(o,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=o;
    			nombreElegido="O";
    		}
    		if(!segundaLetra) {
    			letraDos=o;
    			segLetra="O";
    		}
    		else {
    			tercLetra="O";
    		}
    		break;
    	case KeyEvent.VK_P:
    		g.drawImage(p,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=p;
    			nombreElegido="P";
    		}
    		if(!segundaLetra) {
    			letraDos=p;
    			segLetra="P";
    		}
    		else {
    			tercLetra="P";
    		}
    		break;
    	case KeyEvent.VK_Q:
    		g.drawImage(q,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=q;
    			nombreElegido="Q";
    		}
    		if(!segundaLetra) {
    			letraDos=q;
    			segLetra="Q";
    		}
    		else {
    			tercLetra="Q";
    		}
    		break;
    	case KeyEvent.VK_R:
    		g.drawImage(r,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=r;
    			nombreElegido="R";
    		}
    		if(!segundaLetra) {
    			letraDos=r;
    			segLetra="R";
    		}
    		else {
    			tercLetra="R";
    		}
    		break;
    	case KeyEvent.VK_S:
    		g.drawImage(s,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=s;
    			nombreElegido="S";
    		}
    		if(!segundaLetra) {
    			letraDos=s;
    			segLetra="S";
    		}
    		else {
    			tercLetra="S";
    		}
    		break;
    	case KeyEvent.VK_T:
    		g.drawImage(t,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=t;
    			nombreElegido="T";
    		}
    		if(!segundaLetra) {
    			letraDos=t;
    			segLetra="T";
    		}
    		else {
    			tercLetra="T";
    		}
    		break;
    	case KeyEvent.VK_U:
    		g.drawImage(u,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=u;
    			nombreElegido="U";
    		}
    		if(!segundaLetra) {
    			letraDos=u;
    			segLetra="U";
    		}
    		else {
    			tercLetra="U";
    		}
    		break;
    	case KeyEvent.VK_V:
    		g.drawImage(v,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=v;
    			nombreElegido="V";
    		}
    		if(!segundaLetra) {
    			letraDos=v;
    			segLetra="V";
    		}
    		else {
    			tercLetra="V";
    		}
    		break;
    	case KeyEvent.VK_W:
    		g.drawImage(w,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=w;
    			nombreElegido="W";
    		}
    		if(!segundaLetra) {
    			letraDos=w;
    			segLetra="W";
    		}
    		else {
    			tercLetra="W";
    		}
    		break;
    	case KeyEvent.VK_X:
    		g.drawImage(equis,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=equis;
    			nombreElegido="X";
    		}
    		if(!segundaLetra) {
    			letraDos=equis;
    			segLetra="X";
    		}
    		else {
    			tercLetra="X";
    		}
    		break;
    	case KeyEvent.VK_Y:
    		g.drawImage(yGriega,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=yGriega;
    			nombreElegido="Y";
    		}
    		if(!segundaLetra) {
    			letraDos=yGriega;
    			segLetra="Y";
    		}
    		else {
    			tercLetra="Y";
    		}
    		break;
    	case KeyEvent.VK_Z:
    		g.drawImage(z,posicionLetra,750,66,114,this);
    		if(!primeraLetra) {
    			letraUno=z;
    			nombreElegido="Z";
    		}
    		if(!segundaLetra) {
    			letraDos=z;
    			segLetra="Z";
    		}
    		else {
    			tercLetra="Z";
    		}
    		break;
    	case KeyEvent.VK_ENTER:
    		posicionLetra+=100;
    		if(primeraLetra) {
    			segundaLetra=true;
    		}
    		if(terceraLetra) {
    			nombreElegido+=tercLetra;
    		}
    		if(segundaLetra) {
    			if(posicionLetra==550) {
    				nombreElegido+=segLetra;
    				terceraLetra=true;
    			}
    		}
    		
    		primeraLetra=true;
    		break;
    	default:;
    	}
    }
    
    
    /**
     * Animaciones de mosntruos, fuegos, hierbas, trampas,...
     */
    private void animacionesOtros() {
    	
    	//Animaciones monstruos
    	if(animaciones==0) {
    		switch(anMons) {
        	case 0:
        		setArrPosMonstruo(anMons);
        		setArrPosFly(anMons);
        		setArrPosLlama(anMons);
        		setArrPosBoss(anMons);
        		anMons++;
        		break;
        	case 1:
        		setArrPosMonstruo(anMons);
        		setArrPosFly(anMons);
        		setArrPosLlama(anMons);
        		setArrPosBoss(anMons);
        		anMons++;
        		break;
        	case 2:
        		setArrPosMonstruo(anMons);
        		setArrPosFly(anMons);
        		setArrPosLlama(anMons);
        		setArrPosBoss(anMons);
        		anMons++;
        		break;
        	case 3:
        		setArrPosMonstruo(anMons);
        		setArrPosFly(anMons);
        		setArrPosLlama(anMons);
        		setArrPosBoss(anMons);
        		anMons++;
        		break;
        	case 4:
        		setArrPosMonstruo(anMons-2);
        		setArrPosFly(anMons-2);
        		setArrPosLlama(anMons-2);
        		setArrPosBoss(anMons-2);
        		anMons++;
        		break;
        	case 5:
        		setArrPosMonstruo(anMons-4);
        		setArrPosFly(anMons-4);
        		setArrPosLlama(anMons-4);
        		setArrPosBoss(anMons-4);
        		anMons++;
        		break;
        	case 6:
        		setArrPosMonstruo(anMons-6);
        		setArrPosFly(anMons-6);
        		setArrPosLlama(anMons-6);
        		setArrPosBoss(anMons-6);
        		anMons=0;
        		break;
        	default:;
        	}
    		animaciones++;
    	}
    	else if(animaciones==2) {
    		animaciones=0;
    	}
    	else {
    		animaciones++;
    	}
    	
    }
    
    /**
     * Método para controlar el comportamiento del boss.
     */
    private void estadosDeAnimoBoss() {
    	
    	int posBoss=69;
    	
    	for(int r=0;r<entities.enemies.size();r++) {
			
			if(entities.enemies.get(r).getTypeEnemy().equals("boss")) {
				
				posBoss=r;
				
			}
		}
    	if(!(posBoss==69)) {
    		
        	if(estados>450 && estados<900) {
				
    			entities.enemies.get(posBoss).setCansado(false);
    			entities.enemies.get(posBoss).setEnfadado(true);
    			
        	}
        	else if(estados>899 && estados<1350) {
        		
    			entities.enemies.get(posBoss).setEnfadado(false);
    			entities.enemies.get(posBoss).setCansado(true);
    			
    			if(estados==1349) {
    				estados=-1;
    			}
        		
        	}
        	else {
        		
        		entities.enemies.get(posBoss).setCansado(false);
        		entities.enemies.get(posBoss).setEnfadado(false);
        		
        	}
    		
    	}
    	
    	estados++;
    	
    }
 
    
    /**
     * Método para pintar nuestro panel.
     */
    public void paint(Graphics g) {
    	
    	//Hacemos que sus imágenes vayan cambiando
    	animacionesOtros();
    	
    	estadosDeAnimoBoss();
    	
    	//Si estamos cargando no refresques
    	if(!loading) {
    		
    		if(!pedirNombre) {
    			


    			//Borramos nuestras plataformas, ya que puede que ahora se vayan a mover
    			listaPlataformas.clear();
    	
    			//Fondo por ahora de por si las moscas
    	
    			g.setColor(Color.gray);
    			g.fillRect(0, 0, 1000, 1000);
    	
    			//Imagen móvil de fondo
    			for (int ñ = -1000; ñ < 4500; ñ += 750) {
    	
    				g.drawImage(fondo, ñ - (x / 4), 0, 750, 775, this);
    	
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
    			g.drawImage(nube2, 5000 - (x / 3), 100, 50, 30, this);
    			g.drawImage(nube1, 6800 - (x / 2), 55, 100, 50, this);
    	
    			//Nuestros bloques de suelo
    			for (int o = -350; o < 2000; o += 90) {
    				if (o % 20 == 0 && (!(o==-350) && !(o+90>2000))) {
    					g.drawImage(suelo2, o - x, 710, 90, 92, this);
    				} else if(!(o==-350) && o+90<2000) {
    					g.drawImage(suelo1, o - x, 710, 90, 92, this);
    				}
    				if(o==-350) {
    					g.drawImage(sueloInicio, o-x,710,90,92,this);
    				}
    				if(o+90>2000) {
    					g.drawImage(sueloFinal, o-x,710,90,92,this);
    				}
    			}
    			//Colisiones del suelo:
    			addPlatformToList(-350 - x, 720, 2438, 500, Tipo.BOTH);
    			for (int o = 2500; o < 5000; o += 90) {
    				if (o % 20 == 0 && (!(o==2500) && !(o+90>5000))) {
    					g.drawImage(suelo3, o - x, 710, 90, 92, this);
    				} else if(!(o==2500) && o+90<5000) {
    					g.drawImage(suelo1, o - x, 710, 90, 92, this);
    				}
    				if(o==2500) {
    					g.drawImage(sueloInicio, o-x,710,90,92,this);
    				}
    				if(o+90>5000) {
    					g.drawImage(sueloFinal, o-x,710,90,92,this);
    				}
    			}
    			//Colisiones del suelo:
    			addPlatformToList(2500 - x, 720, 2508, 500, Tipo.BOTH);
    			for (int o = 6000; o < 7000; o += 90) {
    				if (o % 20 == 0 && (!(o==6000) && !(o+90>7000))) {
    					g.drawImage(suelo2, o - x, 710, 90, 92, this);
    				} else if(!(o==6000) && o+90<7000) {
    					g.drawImage(suelo1, o - x, 710, 90, 92, this);
    				}
    				if(o==6000) {
    					g.drawImage(sueloInicio, o-x,710,90,92,this);
    				}
    				if(o+90>7000) {
    					g.drawImage(sueloFinal, o-x,710,90,92,this);
    				}
    			}
    			//Colisiones del suelo:
    			addPlatformToList(6000 - x, 720, 1075, 500, Tipo.BOTH);
    			for (int o = 7300; o < 9000; o += 90) {
    				if (o % 20 == 0 && (!(o==7300) && !(o+90>9000))) {
    					g.drawImage(suelo3, o - x, 710, 90, 92, this);
    				} else if(!(o==7300) && o+90<9000) {
    					g.drawImage(suelo1, o - x, 710, 90, 92, this);
    				}
    				if(o==7300) {
    					g.drawImage(sueloInicio, o-x,710,90,92,this);
    				}
    				if(o+90>9000) {
    					g.drawImage(sueloFinal, o-x,710,90,92,this);
    				}
    			}
    			//Colisiones del suelo:
    			addPlatformToList(7300 - x, 720, 1695, 500, Tipo.BOTH);
    			for (int o = 10000; o < 10350; o += 90) {
    				if (o % 20 == 0 && (!(o==10000) && !(o+90>10350))) {
    					g.drawImage(suelo2, o - x, 710, 90, 92, this);
    				} else if(!(o==10000) && o+90<10350) {
    					g.drawImage(suelo1, o - x, 710, 90, 92, this);
    				}
    				if(o==10000) {
    					g.drawImage(sueloInicio, o-x,710,90,92,this);
    				}
    				if(o+90>10350) {
    					g.drawImage(sueloFinal, o-x,710,90,92,this);
    				}
    			}
    			//Colisiones del suelo:
    			addPlatformToList(10000 - x, 720, 355, 500, Tipo.BOTH);
    			for (int o = 13500; o < 14800; o += 90) {
    				if (o % 20 == 0 && (!(o==13500) && !(o+90>14800))) {
    					g.drawImage(suelo3, o - x, 710, 90, 92, this);
    				} else if(!(o==13500) && o+90<14800) {
    					g.drawImage(suelo1, o - x, 710, 90, 92, this);
    				}
    				if (o % 50 == 0 && (!(o==13500) && !(o+90>14800))) {
    					g.drawImage(suelo2, o - x, 710, 90, 92, this);
    				}
    				if(x>13000) {
    					System.out.println("hola");
    				}
    				if(o==13500) {
    					g.drawImage(sueloInicio, o-x,710,90,92,this);
    				}
    				if(o+90>14800) {
    					g.drawImage(sueloFinal, o-x,710,90,92,this);
    				}
    			}
    			//Colisiones del suelo:
    			addPlatformToList(13500 - x, 720, 1350, 500, Tipo.BOTH);
    			
    			addPlatformToList(-350 - x, 775, 18000, 500, Tipo.BOTH);
    	
//       			//Árboles colisionables:
//    			g.drawImage(arbol1,918-x,355,200,370,this);
////    			addPlatformToList(1000 - x, 400, 35, 330, Tipo.BOTH);
//    			g.drawImage(arbol1,2918-x,355,200,370,this);
////    			addPlatformToList(3000 - x, 400, 35, 330, Tipo.BOTH);
//    			g.drawImage(arbol1,3918-x,355,200,370,this);
////    			addPlatformToList(4000 - x, 400, 35, 330, Tipo.BOTH);
//    			g.drawImage(arbol1,6218-x,355,200,370,this);
////    			addPlatformToList(6300 - x, 400, 35, 330, Tipo.BOTH);
//    			g.drawImage(arbol1,8518-x,355,200,370,this);
////    			addPlatformToList(8600 - x, 400, 35, 330, Tipo.BOTH);
    			
    			//Plataformas para saltar
    			g.drawImage(plataforma1, 700 - x, 300, 100, 35, this);
    			addPlatformToList(700 - x, 305, 100, 35, Tipo.PLATFORM);
    	
    			g.drawImage(plataforma1, 550 - x, 450, 100, 35, this);
    			addPlatformToList(550 - x, 455, 100, 35, Tipo.PLATFORM);
    	
    			g.drawImage(plataforma1, 400 - x, 600, 100, 35, this);
    			addPlatformToList(400 - x, 600, 100, 35, Tipo.PLATFORM);
    			
    			//PRUEBAS MONSTRUO:++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    			
    			for(int x=0;x<entities.enemies.size();x++) {
    			
    				if(!entities.enemies.get(x).isDead()) {
    					
    					if(entities.enemies.get(x).getTypeEnemy().equals("fly")) {
    						
    						if(animaciones==1) {
    							lado=entities.enemies.get(x).isIzDer()? 4: 0;
    						}
    						
    						g.drawImage(animFly[arrPosFly+lado], entities.enemies.get(x).getPosXEnemy() -this.x + entities.enemies.get(x).getMoveEnemy(),
        							entities.enemies.get(x).getPosYEnemy()+entities.enemies.get(x).getMoveYEnemy(), 100, 100, this);
        					addPlatformToList(entities.enemies.get(x).getPosXEnemy() - this.x + entities.enemies.get(x).getMoveEnemy(),
        							entities.enemies.get(x).getPosYEnemy()+entities.enemies.get(x).getMoveYEnemy(), 70, 50, Tipo.ENEMY,x);
    						
    					}
    					else if(entities.enemies.get(x).getTypeEnemy().equals("1")){
    						
    						g.drawImage(animMonstruo[arrPosMonstruo], entities.enemies.get(x).getPosXEnemy() -this.x + entities.enemies.get(x).getMoveEnemy(),
        							entities.enemies.get(x).getPosYEnemy(), 70, 50, this);
        					addPlatformToList(entities.enemies.get(x).getPosXEnemy() - this.x + entities.enemies.get(x).getMoveEnemy(),
        							entities.enemies.get(x).getPosYEnemy(), 70, 50, Tipo.ENEMY,x);
    						
    					}
    					else if(entities.enemies.get(x).getTypeEnemy().equals("2")) {
    						
    						g.drawImage(animLlama[arrPosLlama], entities.enemies.get(x).getPosXEnemy() -this.x + entities.enemies.get(x).getMoveEnemy(),
        							entities.enemies.get(x).getPosYEnemy()-50, 50, 100, this);
        					addPlatformToList(entities.enemies.get(x).getPosXEnemy() - this.x + entities.enemies.get(x).getMoveEnemy(),
        							entities.enemies.get(x).getPosYEnemy(), 50, 50, Tipo.ENEMY,x);
    						
    					}
    					
    				}
    				
    			}
    			
    			//Las cajas
    			for(int k=0;k<entities.cajas.size();k++) {
    				if(!(entities.cajas.get(k).isDestroyed())){    					
    					g.drawImage(box,entities.cajas.get(k).getPosX()-x,entities.cajas.get(k).getPosY(),50,50,this);
    					addPlatformToList( entities.cajas.get(k).getPosX()-x, entities.cajas.get(k).getPosY(), 50, 50,Tipo.BOX,k);
    				}
    			}
    			
    			
    			//Nuestro keko
    			g.drawImage(animKeko[arrPosKeko], keko.getPosXPlayer(), keko.getPosYPlayer()-y, 50, 90, this);
    			addPlatformToList( keko.getPosXPlayer(), keko.getPosYPlayer()-y, 49, 89,Tipo.PLAYER);
    			
    			//El Boss (pintándolo aquí haremos que sólo él esté por delante del keko
    			for(int x=0;x<entities.enemies.size();x++) {
        			
    				if(!entities.enemies.get(x).isDead()) {
    					if(entities.enemies.get(x).getTypeEnemy().equals("boss")) {
    						
    						if(animaciones==1) {
    							
    							if(entities.enemies.get(x).isEnfadado()) {
        							arrPosBoss+=0;
        						}
        						else {
        							if(!(entities.enemies.get(x).isCansado())) {
        								arrPosBoss+=16;
        							}
        						}
        						
        						if(entities.enemies.get(x).isCansado()) {
        							arrPosBoss+=8;
        						}
        						
        						if(entities.enemies.get(x).isIzDer()) {
        							
        							arrPosBoss+=4;
        						}
    							
    						}
    						
    						g.drawImage(animBoss[arrPosBoss], entities.enemies.get(x).getPosXEnemy() -this.x + entities.enemies.get(x).getMoveEnemy(),
        							entities.enemies.get(x).getPosYEnemy()-250, 250, 300, this);
        					addPlatformToList(entities.enemies.get(x).getPosXEnemy() - this.x + entities.enemies.get(x).getMoveEnemy(),
        							entities.enemies.get(x).getPosYEnemy()-250, 250, 300, Tipo.ENEMY,x);
    						
    					}
    				}
				}
    			
    			//Árboles colisionables:
    			g.drawImage(arbol1,918-x,355,200,370,this);
//    			addPlatformToList(1000 - x, 400, 35, 330, Tipo.BOTH);
    			g.drawImage(arbol1,2918-x,355,200,370,this);
//    			addPlatformToList(3000 - x, 400, 35, 330, Tipo.BOTH);
    			g.drawImage(arbol1,3918-x,355,200,370,this);
//    			addPlatformToList(4000 - x, 400, 35, 330, Tipo.BOTH);
    			g.drawImage(arbol1,6218-x,355,200,370,this);
//    			addPlatformToList(6300 - x, 400, 35, 330, Tipo.BOTH);
    			g.drawImage(arbol1,8518-x,355,200,370,this);
//    			addPlatformToList(8600 - x, 400, 35, 330, Tipo.BOTH);
    			
    			//g.drawImage(arbol1,192-x,355,200,370,this);
    			//addPlatformToList(279 - x, 400, 35, 330, Tipo.BOTH);
    			
    			//Importante que sea lo último para poder ver y colisionar con todo
    			movimientoBala(g, disparo);
    			
    			//Árboles no colisionables:
    			g.drawImage(arbusto,500-x,680,50,50,this);
    			g.drawImage(arbusto,1500-x,680,50,50,this);
    			g.drawImage(arbusto,4500-x,680,50,50,this);
    			g.drawImage(arbusto,8000-x,680,50,50,this);
    			g.drawImage(arbusto,14000-x,680,50,50,this);
    	
    			//Imagen del hud
    			g.drawImage(hud, -5, 695, 1010, 390, this);
    			//Aquí habrá que programar las reproducciones las barras de energía
    			g.drawImage(barra_energia[keko.getEnergy()<=99? keko.getEnergy():99], 10, 790, 300, 160, this);
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
    			
    			if(!creditos) {
    				g.drawImage(ganador? vivo: muerto,0,0,1010,1010,this);
    				
    				meterNombrePuntuacion(g,letra);
    				
    				if(primeraLetra) {
    					g.drawImage(letraUno,posPrimeraLetra,ejeYLetraUno,66,altoLetraUno,this);
    				}
    				else {
    					posPrimeraLetra=posicionLetra;
    					ejeYLetraUno=letra==KeyEvent.VK_MINUS ? 800 : 750;
    					altoLetraUno=letra==KeyEvent.VK_MINUS ? 20 : 114;
    				}
    				if(segundaLetra) {
    					g.drawImage(letraDos,posSegundaLetra,ejeYLetraDos,66,altoLetraDos,this);
    				}
    				else {
    					posSegundaLetra=posicionLetra;
    					ejeYLetraDos=letra==KeyEvent.VK_MINUS ? 800 : 750;
    					altoLetraDos=letra==KeyEvent.VK_MINUS ? 20 : 114;
    				}
        			
        		}
    			else {
    				g.drawImage(creditosScroll,-5,-movCreditos,1010,4000,this);
    			}
    		}
		
    	}
    	else {
    		g.drawImage(cargando,0,0,1000,1000,this);
    	}
	}

}

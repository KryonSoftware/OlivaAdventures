package olivaAdventures;

public class Platform {

    private int ejeX;
    private int ejeY;
    private int ancho;
    private int alto;
    private Tipo tipo;
    private int posListaEnemies;

    public Platform(int ejeX,int ejeY,int ancho,int alto,Tipo tipo){this.ejeX=ejeX;this.ejeY=ejeY;this.ancho=ancho;this.alto=alto;this.setTipo(tipo);}
    
    public Platform(int ejeX,int ejeY,int ancho,int alto,Tipo tipo,int posListaEnemies){this.ejeX=ejeX;this.ejeY=ejeY;this.ancho=ancho;this.alto=alto;this.setTipo(tipo);this.posListaEnemies=posListaEnemies;}

    public int getEjeX() {return ejeX;}

    public void setEjeX(int ejeX) {this.ejeX = ejeX;}

    public int getEjeY() {return ejeY;}

    public void setEjeY(int ejeY) {this.ejeY = ejeY;}

    public int getAncho() {return ancho;}

    public void setAncho(int ancho) {this.ancho = ancho;}

    public int getAlto() {return alto;}

    public void setAlto(int alto) {this.alto = alto;}

	public Tipo getTipo() {return tipo;}

	public void setTipo(Tipo tipo) {this.tipo = tipo;}

	public int getPosListaEnemies() {return posListaEnemies;}

	public void setPosListaEnemies(int posListaEnemies) {this.posListaEnemies = posListaEnemies;}
}

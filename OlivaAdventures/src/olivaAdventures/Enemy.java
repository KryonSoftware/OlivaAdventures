package olivaAdventures;

import olivaAdventures.Enemy.typeEnemies;

public class Enemy {

    //ATTRIBUTTES
    private byte lives;
    private String typeEnemy;
    private boolean dead = false;
    private int energy;

    //Movimientos de los enemigos para sus IAs
    private int posXEnemy;
    private int posYEnemy;
    private int moveEnemy;
    private int moveYEnemy;
    private boolean jumping=false;
    private boolean suicida=true;

	private int contJumping;
    private int prevYEnemy;
    
    private boolean izDer=false;
    private boolean cansado=false;
    private boolean enfadado=false;

    private boolean wakeUp;
    private int contador;
    private boolean voladorVertical=true;
    private int entidad;


        /* Type of enemies */
    enum typeEnemies{
        type1,
        fly,
        type2,
        boss
    }

    //CONSTRUCTOR
    public Enemy(typeEnemies typeEnemies, int posX, int posY, boolean suicida){
        switch (typeEnemies){
            case type1:
                setLives((byte) 2);
                setTypeEnemy(String.valueOf(1));
                this.suicida=suicida;
                break;
            case fly:
                setLives((byte) 1);
                setTypeEnemy("fly");
                break;
            case type2:
                setLives((byte) 1);
                setTypeEnemy(String.valueOf(2));
                break;
            case boss:
                setLives((byte) 35);
                setTypeEnemy("boss");
                break;
        }
        /*
        Cuando instanciamos un enemigo directamente llamaremos al setter de energia, posiciones y su entidad esta se encargará de darle el valor
        adecuado de energia según el tipo de enemigo
         */
        this.posXEnemy = posX;
        this.posYEnemy = posY;
        setEnergy();
        setEntidad();
    }

    //GETTERS AND SETTERS
    byte getLives() { return lives; }

    void setLives(byte lives) { this.lives = lives; }

    String getTypeEnemy() { return typeEnemy; }

    private void setTypeEnemy(String typeEnemy) { this.typeEnemy = typeEnemy; }

    boolean isDead() { return dead; }

    void setDead(boolean dead) { this.dead = dead; }

    private int getEnergy() { return energy; }

    public int getPosXEnemy() { return posXEnemy; }

    public void setPosXEnemy(int posXEnemy) { this.posXEnemy = posXEnemy; }

    public int getPosYEnemy() { return posYEnemy; }

    public void setPosYEnemy(int posYEnemy) { this.posYEnemy = posYEnemy; }

    public boolean isIzDer() {
		return izDer;
	}

	public void setIzDer(boolean izDer) {
		this.izDer = izDer;
	}

	public boolean isEnfadado() {
		return enfadado;
	}

	public void setEnfadado(boolean enfadado) {
		this.enfadado = enfadado;
	}

	public boolean isCansado() {
		return cansado;
	}

	public void setCansado(boolean cansado) {
		this.cansado = cansado;
	}

	public int getMoveYEnemy() {
		return moveYEnemy;
	}

	public void setMoveYEnemy(int moveYEnemy) {
		this.moveYEnemy = moveYEnemy;
	}

	public boolean isSuicida() { return suicida; }

	public void setSuicida(boolean suicida) { this.suicida = suicida; }

	public int getMoveEnemy() { return moveEnemy; }

    public void setMoveEnemy(int moveEnemy) { this.moveEnemy = moveEnemy; }

    public boolean isJumping() { return jumping; }

    public void setJumping(boolean jumping) { this.jumping = jumping; }

    public int getContJumping() { return contJumping; }

    public void setContJumping(int contJumping) { this.contJumping = contJumping; }

    public boolean isWakeUp() { return wakeUp; }

    public void setWakeUp(boolean wakeUp) { this.wakeUp = wakeUp; }

    public int getEntidad() { return entidad; }

    public int getPrevYEnemy() { return prevYEnemy; }

    public void setPrevYEnemy(int prevYEnemy) { this.prevYEnemy = prevYEnemy; }

    public void setEntidad() {

        switch (typeEnemy){
            case "1":
                this.entidad = 2; break;
            case "2":
                this.entidad = 3; break;
            case "boss":
                this.entidad = 4; break;
            case "fly":
                this.entidad = 5; break;
        }

    }

    private void setEnergy(){
        //Según el tipo de enemigo se le dará un valor determinado de energia
        switch (typeEnemy){
            case "1":
                energy = 10; break;
            case "fly":
                energy = 10; break;
            case "2":
                energy = 20; break;
            case "boss":
                energy = 50; break;
        }
    }

    //FUNCTIONS//

    public char getDecission(int posXEnemy, int posYEnemy, int posXPlayer, int posYPlayer){

    	switch (typeEnemy){
        case "1":
        	if (posXEnemy > posXPlayer+5){
                return 'I';
            } else if (posXPlayer-5 > posXEnemy) {
                return 'D';
            }
            break;
        case "fly":
        	if(voladorVertical) {
        		if (posXEnemy > posXPlayer+5){
        			voladorVertical=false;
                    return 'I';
                } else if (posXPlayer-5 > posXEnemy){
                	voladorVertical=false;
                    return 'D';
                }
        		voladorVertical=false;
        	}else {
        		if (posYEnemy> posYPlayer){
        			voladorVertical=true;
                    return 'W';
                } else if (posYPlayer > posYEnemy){
                	voladorVertical=true;
                    return 'S';
                }
        		voladorVertical=true;
        	}
            break;
        case "2":
        	if (posXEnemy > posXPlayer+5){
                return 'I';
            } else if (posXPlayer-5 > posXEnemy) {
                return 'D';
            }
            break;
        case "boss":
            if (posXEnemy > posXPlayer-100){
                return 'I';
            } else if (posXPlayer-140 > posXEnemy) {
                return 'D';
            }
            else if(posYEnemy<=posYPlayer){
            	if(posYEnemy+300>=posYPlayer+80)
            	return 'K';
            }
            break;
    }

        contador++;

        return '0';
    }

    /*
    Esta es la función a la que llamaremos cuando un enemigo muera y tenga de darle la energia al personaje, viene
    heredada de Energy
     */
    public int dropEnergyEnemy(Enemy enemy){
        return Energy.dropEnergy(enemy);
    }

    /*
    Esta función lo que hará será restar al personaje una cantidad determinada de energía, dependiendo del tipo que sea
    el enemigo, cuando el personaje tenga 0 o menos de energia significará que el personaje está muerto
     */
    public void doDamage(Player player){

        try {

            if (player.isDead()) throw new Exception();

            int playerEnergy = player.getEnergy();
            switch (typeEnemy){
                case "1":
                    player.setEnergy(playerEnergy - 2); break;
                case "2":
                    player.setEnergy(playerEnergy - 1); break;
                case "boss":
                    player.setEnergy(playerEnergy - 3); break;
                case "fly":
                    player.setEnergy(playerEnergy - 1); break;
            }

        } catch (Exception e){
            System.out.println("Estas disparando a un muerto");
        }

    }

    //TO STRING
    @Override
    public String toString() {
        return "Enemy{" +
                "\nlives = " + getLives() +
                "\ntype = " + getTypeEnemy() +
                "\nisDead = " + dead +
                "\nEnergy = " + getEnergy() +
                "\n}\n";
    }

}

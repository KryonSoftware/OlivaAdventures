package olivaAdventures;

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
    private boolean jumping=false;
    private boolean suicida=false;

	private int contJumping;
    private int prevYEnemy;

    private boolean wakeUp;
    private int contador;

    private int entidad;


        /* Type of enemies */
    enum typeEnemies{
        type1,
        typeTrap,
        type2,
        boss
    }

    //CONSTRUCTOR
    Enemy(typeEnemies typeEnemies, int posX, int posY, boolean suicida){
        switch (typeEnemies){
            case type1:
                setLives((byte) 5);
                setTypeEnemy(String.valueOf(1));
                this.suicida=suicida;
                break;
            case typeTrap:
                setLives((byte) 120);
                setTypeEnemy("typeTrap");
                this.suicida=suicida;
                break;
            case type2:
                setLives((byte) 10);
                setTypeEnemy(String.valueOf(2));
                this.suicida=suicida;
                break;
            case boss:
                setLives((byte) 30);
                setTypeEnemy("boss");
                this.suicida=suicida;
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
    byte getLives() {
        return lives;
    }

    void setLives(byte lives) {
        this.lives = lives;
    }

    String getTypeEnemy() {
        return typeEnemy;
    }

    private void setTypeEnemy(String typeEnemy) {
        this.typeEnemy = typeEnemy;
    }

    boolean isDead() {
        return dead;
    }

    void setDead(boolean dead) {
        this.dead = dead;
    }

    private int getEnergy() {
        return energy;
    }

    public int getPosXEnemy() { return posXEnemy; }

    public void setPosXEnemy(int posXEnemy) { this.posXEnemy = posXEnemy; }

    public int getPosYEnemy() { return posYEnemy; }

    public void setPosYEnemy(int posYEnemy) { this.posYEnemy = posYEnemy; }

    public boolean isSuicida() {
		return suicida;
	}

	public void setSuicida(boolean suicida) {
		this.suicida = suicida;
	}

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
            case "typeTrap":
                this.entidad = 5; break;
        }

    }

    private void setEnergy(){
        //Según el tipo de enemigo se le dará un valor determinado de energia
        switch (typeEnemy){
            case "1":
                energy = 10; break;
            case "typeTrap":
                energy = 80000000; break;
            case "2":
                energy = 20; break;
            case "boss":
                energy = 50; break;
        }
    }

    //FUNCTIONS//

    public char getDecission(int posXEnemy, int posYEnemy, int posXPlayer, int posYPlayer){

        if (/*contador % 150 == 0 && */this.typeEnemy.equals("boss")){
            generarEnemigo();
        } else {

            if (posXEnemy > posXPlayer+5){
                return 'I';
            } else if (posXPlayer-5 > posXEnemy) {
                return 'D';
            }

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
    public void doDamge(Player player, Enemy enemy){

        try {

            if (player.isDead()) throw new Exception();

            int playerEnergy = player.getEnergy();
            switch (enemy.typeEnemy){
                case "1":
                    player.setEnergy(playerEnergy - 20); break;
                case "2":
                    player.setEnergy(playerEnergy - 40); break;
                case "boss":
                    player.setEnergy(playerEnergy - 50); break;
            }

            if (player.getEnergy() <= 0){
                player.setDead(true);
            }

        } catch (Exception e){
            System.out.println("Estas disparando a un muerto");
        }

    }

    /* Esta función se encargará de que el boss pueda generar enemigos */
    private void generarEnemigo(){

        if (isWakeUp()){
            Entities.enemies.add(new Enemy(typeEnemies.type1, this.posXEnemy, this.posYEnemy, true));
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

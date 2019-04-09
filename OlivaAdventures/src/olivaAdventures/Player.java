package olivaAdventures;

public class Player {

    //ATTRIBUTES
    private int lives;
    private int energy;
    private boolean dead;

    //TODO ARREGLAR ESTA MIERDA
    private int posXPlayer;
    private int posYPlayer;
    private int movePlayer;
    private boolean jumpingPlayer;
    private int contJumpingPlayer;
    private int prevYEnemyPlayer;
    private char lastSide='D';


    //CONSTRUCTOR
    public Player(int posX, int posY){
        this.lives = 3;
        this.energy = 50;
        this.dead = false;
        this.posXPlayer = posX;
        this.posYPlayer = posY;
    }

    //GETTERS AND SETTERS
    int getEnergy() {
        return energy;
    }

    void setEnergy(int energy) {
        this.energy = energy;
    }

    boolean isDead() {
        return dead;
    }

    void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getLives() { return lives; }

    public void setLives(int lives) { this.lives = lives; }

    public char getLastSide() {
		return lastSide;
	}

	public void setLastSide(char lastSide) {
		this.lastSide = lastSide;
	}

	public int getPosXPlayer() { return posXPlayer; }

    public void setPosXPlayer(int posXPlayer) { this.posXPlayer = posXPlayer; }

    public int getPosYPlayer() { return posYPlayer; }

    public void setPosYPlayer(int posYPlayer) { this.posYPlayer = posYPlayer; }

    public int getMovePlayer() { return movePlayer; }

    public void setMovePlayer(int movePlayer) { this.movePlayer = movePlayer; }

    public boolean isJumpingPlayer() { return jumpingPlayer; }

    public void setJumpingPlayer(boolean jumpingPlayer) { this.jumpingPlayer = jumpingPlayer; }

    public int getContJumpingPlayer() { return contJumpingPlayer; }

    public void setContJumpingPlayer(int contJumpingPlayer) { this.contJumpingPlayer = contJumpingPlayer; }

    public int getPrevYEnemyPlayer() { return prevYEnemyPlayer; }

    public void setPrevYEnemyPlayer(int prevYEnemyPlayer) { this.prevYEnemyPlayer = prevYEnemyPlayer; }

    //FUNCTIONS
    public char shot(){

        return 'F';
    }

    /* Esta función lo que hará será hacerle daño a un enemigo cuando este sea alcanzado, se le restará una vida al
     * enemigo, una vez que el enemigo al que hayamos disparado se haya quedado sin vidas, morirá y la energía que tenia
     * este enemigo pasará totalmente al jugador */
    public void doDamge(Enemy enemy){
        try {
            if (enemy.isDead()) throw new Exception();
            enemy.setLives((byte) (enemy.getLives() - 1));
            if (enemy.getLives() <= 0) {
                enemy.setDead(true);
                setEnergy(getEnergy() + enemy.dropEnergyEnemy(enemy));
            }

        } catch (Exception e){
            System.out.println("Estas disparando a un muerto");
        }

    }

    public void doBoxDamage(Box box){
        try {

            int numVidas = box.getLives();
            if (numVidas>0){
                box.setLives(box.getLives() - 1);
                if (box.getLives() <= 0){
                    box.setDestroyed(true);
                    this.setEnergy(this.getEnergy() + box.getEnergy());
                }
            }

        } catch (Exception e){
            System.err.println("Ha petado en 'doBoxDamage'");
        }
    }

    //TO STRING
    @Override
    public String toString() {

        return "Player{" +
                "\nEnergy = " + energy +
                "\nisDead = " + dead +
                "\n}\n";

    }
}

package olivaAdventures;

import olivaAdventures.Enemy.typeEnemies;

public class Box {

    /* ATTRIBUTES */
    private int energy;
    private int lives;
    private boolean destroyed=false;
	private int posX;
	private int posY;

    /* CONSTRUCTOR */
    public Box(int posX, int posY){
    	this.setPosX(posX);
    	this.setPosY(posY);
        setEnergy();
        setLives(5);
    }

    /* GETTERS AND SETTERS */

    public int getEnergy() {
        return energy;
    }

    private void setEnergy() {
        this.energy = (int) ((Math.random() * 30) + 30);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /* toString */

    @Override
    public String toString() {
        return "Box{" +
                "energy=" + energy +
                ", lives=" + lives +
                ", destroyed=" + destroyed +
                '}';
    }

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}

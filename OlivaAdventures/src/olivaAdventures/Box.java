package olivaAdventures;

public class Box {

    /* ATTRIBUTES */
    private int energy;
    private int lives;
    private boolean destroyed;

    /* CONSTRUCTOR */
    Box(){
        setEnergy();
        setLives(5);
    }

    /* GETTERS AND SETTERS */

    public int getEnergy() {
        return energy;
    }

    private void setEnergy() {
        this.energy = (int) ((Math.random() * 70) + 30);
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
}

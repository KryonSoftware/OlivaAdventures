package olivaAdventures;

import java.awt.event.KeyEvent;

public class Player {

    //ATTRIBUTES
    private int energy;
    private boolean dead;

    //CONSTRUCTOR
    public Player(){
        this.energy = 30;
        this.dead = false;
    }

    //GETTERS AND SETTERS
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    //FUNCTIONS
    public void getDecission(KeyEvent keyEvent){

    }

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

    //TO STRING
    @Override
    public String toString() {

        return "Player{" +
                "\nEnergy = " + energy +
                "\nisDead = " + dead +
                "\n}\n";

    }
}

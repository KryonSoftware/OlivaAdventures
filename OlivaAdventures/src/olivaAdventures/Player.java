package olivaAdventures;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {

    //ATTRIBUTES
    private int lives;
    private int energy;
    private boolean dead;

    //CONSTRUCTOR
    public Player(){
        this.lives = 3;
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

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

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

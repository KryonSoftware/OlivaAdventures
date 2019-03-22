package olivaAdventures;

import java.awt.event.KeyEvent;

public class Player {

    //ATTRIBUTES
    private byte lives;
    private boolean dead;

    //CONSTRUCTOR
    public Player(){
        this.lives = 3;
        this.dead = false;
    }

    //GETTERS AND SETTERS
    public byte getLives() {
        return lives;
    }

    public void setLives(byte lives) {
        this.lives = lives;
    }

    public boolean isDead() {
        return dead;
    }

    //FUNCTIONS
    public void getDecission(KeyEvent keyEvent){

    }

    public void getHurt(){

        try {
            //Si recibe daño mientras esta muerto lanzará una exception
            if (isDead()) throw new Exception();

            //Cuando reciba daño se le restará una vida al jugador
            setLives((byte) (getLives() - 1));
            //Cuando llegue a 0 se le encenderá el boolean de que el jugador está muerto
            if (getLives() <= 0){
                this.dead = true;
            }

        } catch (Exception e){
            System.out.println("Ha petado en getHurt()");
            e.printStackTrace();
        }
    }

    //TO STRING
    @Override
    public String toString() {

        return "Player{" +
                "\nlives=" + lives +
                "\nisDead = " + dead +
                "\n}\n";

    }
}

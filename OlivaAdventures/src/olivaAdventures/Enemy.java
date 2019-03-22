package olivaAdventures;

public class Enemy {

    //ATTRIBUTTES
    private byte lives;
    private String typeEnemy;

        /* Type of enemies */
    enum typeEnemies{
        type1,
        type2,
        boss
    }

    //CONSTRUCTOR
    Enemy(typeEnemies typeEnemies){
        switch (typeEnemies){
            case type1:
                setLives((byte) 1);
                setTypeEnemy(String.valueOf(1));
                break;
            case type2:
                setLives((byte) 2);
                setTypeEnemy(String.valueOf(2));
                break;
            case boss:
                setLives((byte) 10);
                setTypeEnemy("boss");
                break;
        }
    }

    //GETTERS AND SETTERS
    public byte getLives() {
        return lives;
    }

    public void setLives(byte lives) {
        this.lives = lives;
    }

    public String getTypeEnemy() {
        return typeEnemy;
    }

    public void setTypeEnemy(String typeEnemy) {
        this.typeEnemy = typeEnemy;
    }

    //FUNCTIONS
    public void getPosition(){
        //getPositionPlayer();
    }

    //TO STRING
    @Override
    public String toString() {
        return "Enemy{" +
                "\nlives=" + getLives() +
                "\ntype=" + getTypeEnemy() +
                "\n}\n";
    }
}

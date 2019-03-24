package olivaAdventures;

public class Enemy {

    //ATTRIBUTTES
    private byte lives;
    private String typeEnemy;
    private boolean dead = false;
    private int energy;

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
                setLives((byte) 2);
                setTypeEnemy(String.valueOf(1));
                break;
            case type2:
                setLives((byte) 3);
                setTypeEnemy(String.valueOf(2));
                break;
            case boss:
                setLives((byte) 10);
                setTypeEnemy("boss");
                break;
        }
        setEnergy();
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

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(){
        switch (typeEnemy){
            case "1":
                energy = 10; break;
            case "2":
                energy = 20; break;
            case "boss":
                energy = 50; break;
        }
    }

    //FUNCTIONS
    public void getPosition(){
        //getPositionPlayer();
    }

    public int dropEnergyEnemy(Enemy enemy){
        return Energy.dropEnergy(enemy);
    }

    public void doDamge(Player player, Enemy enemy){

        try {

            if (player.isDead()) throw new Exception();

            int playerEnergy = player.getEnergy();
            switch (enemy.typeEnemy){
                case "1":
                    player.setEnergy(playerEnergy - 10); break;
                case "2":
                    player.setEnergy(playerEnergy - 15); break;
                case "boss":
                    player.setEnergy(playerEnergy - 20); break;
            }

            if (player.getEnergy() <= 0){
                player.setDead(true);
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

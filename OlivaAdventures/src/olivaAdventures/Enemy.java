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
                setLives((byte) 5);
                setTypeEnemy(String.valueOf(1));
                break;
            case type2:
                setLives((byte) 10);
                setTypeEnemy(String.valueOf(2));
                break;
            case boss:
                setLives((byte) 30);
                setTypeEnemy("boss");
                break;
        }
        /*
        Cuando instanciamos un enemigo directamente llamaremos al setter de energia, esta se encargará de darle el valor
        adecuado de energia según el tipo de enemigo
         */
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
        //Según el tipo de enemigo se le dará un valor determinado de energia
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
    public char getDecission(int posXEnemy, int posYEnemy, int posXPlayer, int posYPlayer){

        if (posXEnemy > posXPlayer+5){
            return 'I';
        } else if (posXPlayer-5 > posXEnemy) {
            return 'D';
        }

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

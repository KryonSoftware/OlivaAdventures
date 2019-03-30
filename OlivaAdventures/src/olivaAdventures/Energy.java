package olivaAdventures;

public class Energy {

    /* ATTRIBUTES */
    private int quantityEnergy;

    /* CONSTRUCTOR */
    public Energy() { }

    /* GETTERS AND SETTERS */
    public int getQuantityEnergy() {
        return quantityEnergy;
    }

    public void setQuantityEnergy(int quantityEnergy) {
        this.quantityEnergy = quantityEnergy;
    }

    /* FUNCTIONS */
    static int dropEnergy(Enemy enemy){
        switch (enemy.getTypeEnemy()){
            case "1":
                return 10;
            case "2":
                return 20;
            case "boss":
                return 50;

        }
        return -1;
    }

}

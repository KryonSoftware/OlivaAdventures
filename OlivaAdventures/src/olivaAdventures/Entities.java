package olivaAdventures;

import java.util.ArrayList;

public class Entities {

    public ArrayList<Enemy> enemies =  loadEnemies();
    public ArrayList<Box> cajas =  loadBoxes();

    private ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(new Enemy(Enemy.typeEnemies.type1, 1000, 400,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 1550, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 2000, 300,false));

        


        return enemies;
    }

    private ArrayList<Box> loadBoxes(){
    	
    	ArrayList<Box> cajas = new ArrayList<>();
    	
//    	cajas.add(new Box(400, 670));
    	cajas.add(new Box(-800,670));
    	
    	return cajas;
    }
}

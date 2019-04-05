package olivaAdventures;

import java.util.ArrayList;

public class Entities {

    public static ArrayList<Enemy> enemies =  loadEnemies();

    public static ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        Enemy enemy = new Enemy(Enemy.typeEnemies.type1, 600, 670);
        Enemy enemy1 = new Enemy(Enemy.typeEnemies.type1, 1700, 670);


        enemies.add(enemy);
        enemies.add(enemy1);


        return enemies;
    }

}

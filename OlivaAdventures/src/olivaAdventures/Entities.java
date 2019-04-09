package olivaAdventures;

import java.util.ArrayList;

public class Entities {

    public static ArrayList<Enemy> enemies =  loadEnemies();

    public static ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        Enemy enemy = new Enemy(Enemy.typeEnemies.type1, 600, 0,false);
        Enemy enemy1 = new Enemy(Enemy.typeEnemies.type1, 950, 0,true);
        Enemy enemy2 = new Enemy(Enemy.typeEnemies.type1, 390, 450,false);
        Enemy enemy3 = new Enemy(Enemy.typeEnemies.type1, 250, 0,true);


        enemies.add(enemy);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);


        return enemies;
    }

}

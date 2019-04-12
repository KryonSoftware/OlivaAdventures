package olivaAdventures;

import java.util.ArrayList;

public class Entities {

    public static ArrayList<Enemy> enemies =  loadEnemies();

    private static ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        Enemy enemy = new Enemy(Enemy.typeEnemies.type1, 600, 0,true);
        Enemy enemy1 = new Enemy(Enemy.typeEnemies.type1, 949, 0,true);
        Enemy enemy2 = new Enemy(Enemy.typeEnemies.type1, 410, 450,true);
        Enemy enemy3 = new Enemy(Enemy.typeEnemies.type1, 800, 0,true);


        enemies.add(enemy);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);


        return enemies;
    }

}

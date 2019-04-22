package olivaAdventures;

import java.util.ArrayList;

public class Entities {

    public ArrayList<Enemy> enemies =  loadEnemies();

    private ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        Enemy enemy = new Enemy(Enemy.typeEnemies.type1, 600, 0,true);
        Enemy enemy1 = new Enemy(Enemy.typeEnemies.type1, 949, 0,true);
        Enemy enemy2 = new Enemy(Enemy.typeEnemies.type1, 410, 450,true);
        Enemy enemy3 = new Enemy(Enemy.typeEnemies.type1, 800, 0,true);
        Enemy enemyFly1 = new Enemy(Enemy.typeEnemies.fly, 430, 50,true);


        enemies.add(enemy);
        enemies.add(enemyFly1);


        return enemies;
    }

}

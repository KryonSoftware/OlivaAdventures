package olivaAdventures;

import java.util.ArrayList;

public class Enemies_lvl1 {

    public static ArrayList<Enemy> enemies =  loadEnemies();

    public static ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        Enemy enemy = new Enemy(Enemy.typeEnemies.type1,750,150);
        Enemy enemy1 = new Enemy(Enemy.typeEnemies.type1,980,0);

        enemies.add(enemy);
        enemies.add(enemy1);

        return enemies;
    }

}

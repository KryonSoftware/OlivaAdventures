package olivaAdventures;

import java.util.ArrayList;

public class Enemies_lvl1 {

    public static ArrayList<Enemy> enemies =  loadEnemies();

    public static ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        Enemy enemy = new Enemy(Enemy.typeEnemies.type1,750,150);
        Enemy enemy1 = new Enemy(Enemy.typeEnemies.type1,980,0);
        Enemy enemy2 = new Enemy(Enemy.typeEnemies.type1,300,0);
        Enemy enemy3 = new Enemy(Enemy.typeEnemies.type1,450,500);
        Enemy enemy4 = new Enemy(Enemy.typeEnemies.type1,980,700);
        Enemy enemy5 = new Enemy(Enemy.typeEnemies.type1,350,100);
        Enemy enemy6 = new Enemy(Enemy.typeEnemies.type1,500,500);

        enemies.add(enemy);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
       // enemies.add(enemy4);
       // enemies.add(enemy5);
       // enemies.add(enemy6);

        return enemies;
    }

}

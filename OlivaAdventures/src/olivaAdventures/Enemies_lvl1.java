package olivaAdventures;

import java.util.ArrayList;

public class Enemies_lvl1 {

    public static ArrayList<Enemy> enemies =  loadEnemies();

    public static ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        Enemy enemy = new Enemy(Enemy.typeEnemies.type1);
        Enemy enemy1 = new Enemy(Enemy.typeEnemies.type1);
        Enemy enemy2 = new Enemy(Enemy.typeEnemies.type1);
        Enemy enemy3 = new Enemy(Enemy.typeEnemies.type1);
        Enemy enemy4 = new Enemy(Enemy.typeEnemies.type1);
        Enemy enemy5 = new Enemy(Enemy.typeEnemies.type1);

        Enemy enemy6 = new Enemy(Enemy.typeEnemies.type2);
        Enemy enemy7 = new Enemy(Enemy.typeEnemies.type2);
        Enemy enemy8 = new Enemy(Enemy.typeEnemies.type2);
        Enemy enemy9 = new Enemy(Enemy.typeEnemies.type2);

        Enemy enemy10 = new Enemy(Enemy.typeEnemies.boss);

        enemies.add(enemy);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        enemies.add(enemy4);
        enemies.add(enemy5);

        enemies.add(enemy6);
        enemies.add(enemy7);
        enemies.add(enemy8);
        enemies.add(enemy9);

        enemies.add(enemy10);

        return enemies;
    }

    public static void main(String[] args) {

        System.out.println(Enemies_lvl1.enemies.get(10).toString());

    }

}

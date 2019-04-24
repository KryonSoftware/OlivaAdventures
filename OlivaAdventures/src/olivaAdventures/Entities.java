package olivaAdventures;

import java.util.ArrayList;

public class Entities {

    public ArrayList<Enemy> enemies =  loadEnemies();
    public ArrayList<Platform> plataformas =  loadPlataformas();

    private ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        Enemy enemy = new Enemy(Enemy.typeEnemies.type1, 600, 0,true);
        Enemy enemy1 = new Enemy(Enemy.typeEnemies.type1, 949, 0,true);
        Enemy enemy2 = new Enemy(Enemy.typeEnemies.type1, 410, 450,true);
        Enemy enemy3 = new Enemy(Enemy.typeEnemies.type1, 800, 0,true);
        Enemy enemyFly1 = new Enemy(Enemy.typeEnemies.fly, 430, 50,true);
        Enemy enemyLlama = new Enemy(Enemy.typeEnemies.type2, 430, 450,true);
        Enemy enemyLlama2 = new Enemy(Enemy.typeEnemies.type2, 530, 450,true);
        Enemy enemyLlama3 = new Enemy(Enemy.typeEnemies.type2, 490, 450,true);
        Enemy enemyBoss = new Enemy(Enemy.typeEnemies.boss, 430, 450,true);
        Enemy enemyFly2 = new Enemy(Enemy.typeEnemies.fly, 130, 50,true);
        enemyBoss.setMoveYEnemy(-250);


//        enemies.add(enemy);
//        enemies.add(enemy1);
//        enemies.add(enemy2);
//        enemies.add(enemy3);
//        enemies.add(enemyFly1);
//        enemies.add(enemyFly2);

//        enemies.add(enemyLlama);
//        enemies.add(enemyLlama2);
//        enemies.add(enemyLlama3);

        enemies.add(enemyBoss);
        


        return enemies;
    }

    private ArrayList<Platform> loadPlataformas(){
    	
    	ArrayList<Platform> plataformas = new ArrayList<>();
    	
    	return plataformas;
    }
}

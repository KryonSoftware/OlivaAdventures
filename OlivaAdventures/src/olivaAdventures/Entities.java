package olivaAdventures;

import java.util.ArrayList;

public class Entities {

    public ArrayList<Enemy> enemies =  loadEnemies();
    public ArrayList<Box> cajas =  loadBoxes();

    private ArrayList<Enemy> loadEnemies(){

        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(new Enemy(Enemy.typeEnemies.type1, -300, 400,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 1000, 400,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 1550, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 1750, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 2100, 300,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 2600, 300,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 3250, 350,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 3050, 500,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 3050, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 3250, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 4130, 400,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 4000, 650,false));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 3400, 300,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 4200, 300,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 4840, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 4940, 500,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 5920, 400,false));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 5700, 300,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 5920, 0,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 6300, 650,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 6500, 400,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 6900, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 7300, 650,false));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 7300, 100,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 7400, 0,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 7400, 650,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 7550, 650,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 7700, 650,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 7900, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 8500, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 8600, 0,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 8700, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type2, 8800, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type2, 8900, 450,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 9000, 400,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 9500, 0,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 9650, 75,false));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 9800, 600,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 10125, 650,false));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 10300, 650,false));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 11000, 0,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 11500, 600,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 12000, 0,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 12500, 700,true));
        enemies.add(new Enemy(Enemy.typeEnemies.fly, 13000, 0,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type2, 13800, 450,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type2, 13850, 550,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type2, 13900, 650,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type2, 14000, 450,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type2, 14100, 400,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type2, 14200, 300,true));
        enemies.add(new Enemy(Enemy.typeEnemies.type1, 14300, 650,false));
        
        enemies.add(new Enemy(Enemy.typeEnemies.boss, -800, 500,false));


        return enemies;
    }

    private ArrayList<Box> loadBoxes(){
    	
    	ArrayList<Box> cajas = new ArrayList<>();
    	
    	cajas.add(new Box(-800,670));
    	cajas.add(new Box(6700,670));
    	cajas.add(new Box(14020,500));
    	
    	return cajas;
    }
}

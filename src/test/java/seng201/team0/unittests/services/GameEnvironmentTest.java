package seng201.team0.unittests.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.services.CartService;
import seng201.team0.services.TowerService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class GameEnvironmentTest {

    private GameEnvironment gameEnvironment;

    void consumer1(GameEnvironment gameEnvironment){}
    void consumer2(GameEnvironment gameEnvironment){}
    void consumer3(GameEnvironment gameEnvironment){}
    void consumer4(GameEnvironment gameEnvironment){}
    void consumer5(GameEnvironment gameEnvironment){}
    void clear(){}
    @BeforeEach
    public void setupTest(){
        gameEnvironment = new GameEnvironment(this::consumer1, this::consumer2, this::consumer3, this::consumer4, this::consumer5, this::clear);
    }

    @Test
    void testResetLabelSaves(){

        gameEnvironment.getCartFillLabelSaves().add("100/100");
        gameEnvironment.getCartTimeLabelSaves().add("Filled");
        assertEquals(1, gameEnvironment.getCartFillLabelSaves().size());
        assertEquals(1, gameEnvironment.getCartTimeLabelSaves().size());
        gameEnvironment.resetCartLabelSaves();
        assertEquals(0, gameEnvironment.getCartFillLabelSaves().size());
        assertEquals(0, gameEnvironment.getCartTimeLabelSaves().size());

    }

    @Test
    void testTowerBreakCalculator(){

        for(int i = 0; i < 50; i++){
            gameEnvironment = new GameEnvironment(this::consumer1, this::consumer2, this::consumer3, this::consumer4, this::consumer5, this::clear);
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
            if(-1 == gameEnvironment.towerBreakCalculator()){
                assertEquals(5, gameEnvironment.getTowerInventory().size());
            } else {
                assertEquals(4, gameEnvironment.getTowerInventory().size());
            }
        }
    }

    @Test
    void testUpdatePooledResourcesPerSecond(){

        gameEnvironment.updatePooledResourcesPerSecond();
        assertEquals(0.0, gameEnvironment.getPooledResourcesPerSecond().get(0));
        assertEquals(0.0, gameEnvironment.getPooledResourcesPerSecond().get(1));
        assertEquals(0.0, gameEnvironment.getPooledResourcesPerSecond().get(2));
        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Sweat")), 10,  "Blood Tower"));
        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Sweat")), 10,  "Blood Tower"));
        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Tears")), 10,  "Blood Tower"));
        gameEnvironment.updatePooledResourcesPerSecond();
        assertEquals(20.0, gameEnvironment.getPooledResourcesPerSecond().get(0));
        assertEquals(20.0, gameEnvironment.getPooledResourcesPerSecond().get(1));
        assertEquals(10.0, gameEnvironment.getPooledResourcesPerSecond().get(2));

    }

    @Test
    void testResetCountOfEachTypeOfCount(){
        gameEnvironment.getCountOfEachTypeCart().set(0, 2);
        gameEnvironment.getCountOfEachTypeCart().set(1, 2);
        gameEnvironment.getCountOfEachTypeCart().set(2, 2);
        gameEnvironment.resetCountOfEachTypeOfCount();
        assertEquals(0, gameEnvironment.getCountOfEachTypeCart().get(0));
        assertEquals(0, gameEnvironment.getCountOfEachTypeCart().get(1));
        assertEquals(0, gameEnvironment.getCountOfEachTypeCart().get(2));

    }

    @Test
    void testUpgradeTowers(){

        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Sweat")), 10,  "Blood Tower"));
        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Sweat")), 10,  "Blood Tower"));
        gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Tears")), 10,  "Blood Tower"));
        for (int i = 0; i<5; i++){
            TowerService tower = (TowerService) gameEnvironment.getTowerInventory().get(i);
            assertEquals(1, tower.getLevel());
        }
        gameEnvironment.levelUpTowers();
        for (int i = 0; i<5; i++){
            TowerService tower = (TowerService) gameEnvironment.getTowerInventory().get(i);
            assertEquals(2, tower.getLevel());
        }
    }

    @Test
    void testRandomTowerLevelUp(){

        for(int i = 0; i < 100; i++){
            gameEnvironment = new GameEnvironment(this::consumer1, this::consumer2, this::consumer3, this::consumer4, this::consumer5, this::clear);
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower"));
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Sweat")), 10,  "Blood Tower"));
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Sweat")), 10,  "Blood Tower"));
            gameEnvironment.getTowerInventory().add(new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Tears")), 10,  "Blood Tower"));
            int index = gameEnvironment.randomTowerLevelUpCalculator();
            if (index != -1){
                TowerService tower = (TowerService) gameEnvironment.getTowerInventory().get(index);
                assertEquals(2, tower.getLevel());
            }

        }
    }
}

package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Purchasable;
import seng201.team0.services.UpgradeService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UpgradeServiceTest {
    
    void consumer1(GameEnvironment gameEnvironment){}
    void consumer2(GameEnvironment gameEnvironment){}
    void consumer3(GameEnvironment gameEnvironment){}
    void consumer4(GameEnvironment gameEnvironment){}
    void consumer5(GameEnvironment gameEnvironment){}
    void clear(){}

    UpgradeService loadSizeTest;
    UpgradeService lifeTest;
    UpgradeService speedTest;
    GameEnvironment gameEnvironment;

    @BeforeEach
    void setupTests(){

        loadSizeTest = new UpgradeService("LoadSize", 1.1, 5);
        lifeTest = new UpgradeService(20);
        speedTest = new UpgradeService("Speed", 1.1, 5);
        gameEnvironment = new GameEnvironment(this::consumer1, this::consumer2, this::consumer3, this::consumer4, this::consumer5, this::clear);

    }

    @Test
    void testBuy(){

        gameEnvironment.setCoins(35);

        loadSizeTest.buy(gameEnvironment, gameEnvironment.getLoadSizeUpgrades());
        lifeTest.buy(gameEnvironment, gameEnvironment.getLifeUpgrades());
        speedTest.buy(gameEnvironment, gameEnvironment.getSpeedUpgrades());

        assertEquals(5, gameEnvironment.getCoins());
        assertEquals(1, gameEnvironment.getLoadSizeUpgrades().size());
        assertEquals(1, gameEnvironment.getLifeUpgrades().size());
        assertEquals(1, gameEnvironment.getSpeedUpgrades().size());

        assertTrue(gameEnvironment.getLoadSizeUpgrades().contains(loadSizeTest));
        assertTrue(gameEnvironment.getLifeUpgrades().contains(lifeTest));
        assertTrue(gameEnvironment.getSpeedUpgrades().contains(speedTest));

    }

    @Test
    void testBuyNoCoins(){

        gameEnvironment.setCoins(2);

        loadSizeTest.buy(gameEnvironment, gameEnvironment.getLoadSizeUpgrades());
        assertEquals(0, gameEnvironment.getLoadSizeUpgrades().size());

    }

    @Test
    void testMakeClone(){

        UpgradeService loadSizeClone = loadSizeTest.makeClone();
        UpgradeService lifeClone = lifeTest.makeClone();
        UpgradeService speedClone = speedTest.makeClone();

        assertEquals(loadSizeTest.getLoadSizeMultiplier(), loadSizeClone.getLoadSizeMultiplier());
        assertEquals(loadSizeTest.getUpgradeType(), loadSizeClone.getUpgradeType());
        assertEquals(loadSizeTest.getPrice(), loadSizeClone.getPrice());

        assertEquals(speedTest.getSpeedMultiplier(), speedClone.getSpeedMultiplier());
        assertEquals(speedTest.getUpgradeType(), speedClone.getUpgradeType());
        assertEquals(speedTest.getPrice(), speedClone.getPrice());

        assertEquals(lifeTest.getPrice(), lifeClone.getPrice());
        assertEquals(lifeTest.getUpgradeType(), lifeClone.getUpgradeType());

    }
    @Test
    void testSell(){

        gameEnvironment.setCoins(0);
        gameEnvironment.getLifeUpgrades().add(lifeTest.makeClone());
        gameEnvironment.getLoadSizeUpgrades().add(loadSizeTest.makeClone());
        gameEnvironment.getSpeedUpgrades().add(speedTest.makeClone());

        loadSizeTest.sell(gameEnvironment, gameEnvironment.getLoadSizeUpgrades(), 0);
        speedTest.sell(gameEnvironment, gameEnvironment.getSpeedUpgrades(), 1);
        lifeTest.sell(gameEnvironment, gameEnvironment.getLifeUpgrades(), 2);

        assertEquals(0, gameEnvironment.getLoadSizeUpgrades().size());
        assertEquals(0, gameEnvironment.getSpeedUpgrades().size());
        assertEquals(0, gameEnvironment.getLifeUpgrades().size());

        assertFalse(gameEnvironment.getLoadSizeUpgrades().contains(loadSizeTest));
        assertFalse(gameEnvironment.getSpeedUpgrades().contains(speedTest));
        assertFalse(gameEnvironment.getLifeUpgrades().contains(lifeTest));

        assertEquals(14, gameEnvironment.getCoins());

    }

    @Test
    void testSellNoInventory(){

        gameEnvironment.setCoins(10);

        loadSizeTest.sell(gameEnvironment, gameEnvironment.getLoadSizeUpgrades(), 0);
        speedTest.sell(gameEnvironment, gameEnvironment.getSpeedUpgrades(), 1);
        lifeTest.sell(gameEnvironment, gameEnvironment.getLifeUpgrades(), 2);

        assertEquals(0, gameEnvironment.getLoadSizeUpgrades().size());
        assertEquals(0, gameEnvironment.getSpeedUpgrades().size());
        assertEquals(0, gameEnvironment.getLifeUpgrades().size());

        assertEquals(10, gameEnvironment.getCoins());

    }

    @Test
    void testSellWrongIndices(){

        gameEnvironment.setCoins(10);

        gameEnvironment.getLifeUpgrades().add(lifeTest.makeClone());
        gameEnvironment.getLoadSizeUpgrades().add(loadSizeTest.makeClone());
        gameEnvironment.getSpeedUpgrades().add(speedTest.makeClone());

        loadSizeTest.sell(gameEnvironment, gameEnvironment.getLoadSizeUpgrades(), 4);
        speedTest.sell(gameEnvironment, gameEnvironment.getSpeedUpgrades(), 4);
        lifeTest.sell(gameEnvironment, gameEnvironment.getLifeUpgrades(), 4);

        assertEquals(1, gameEnvironment.getLoadSizeUpgrades().size());
        assertEquals(1, gameEnvironment.getSpeedUpgrades().size());
        assertEquals(1, gameEnvironment.getLifeUpgrades().size());

        assertEquals(10, gameEnvironment.getCoins());

    }

}

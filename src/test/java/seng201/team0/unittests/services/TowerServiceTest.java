package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Purchasable;
import seng201.team0.services.TowerService;
import seng201.team0.services.UpgradeService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TowerServiceTest {

    void consumer1(GameEnvironment gameEnvironment){}
    void consumer2(GameEnvironment gameEnvironment){}
    void consumer3(GameEnvironment gameEnvironment){}
    void consumer4(GameEnvironment gameEnvironment){}
    void consumer5(GameEnvironment gameEnvironment){}
    void clear(){}

    TowerService bloodTest;
    TowerService allTest;

    UpgradeService loadSizeTest = new UpgradeService("LoadSize", 1.1, 5);
    UpgradeService speedTest = new UpgradeService("Speed", 1.1, 5);
    GameEnvironment gameEnvironment;

    @BeforeEach
    void setupTests(){

        bloodTest = new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood")), 10,  "Blood Tower");
        allTest = new TowerService(10, 1, new ArrayList<String>(Arrays.asList("Blood", "Sweat", "Tears")), 50, "All Tower");
        gameEnvironment = new GameEnvironment(this::consumer1, this::consumer2, this::consumer3, this::consumer4, this::consumer5, this::clear);

    }

    @Test
    void testLevelUp(){

        bloodTest.levelUp();
        assertEquals(11, bloodTest.getLoadSize());
        assertEquals(1.1, bloodTest.getLoadSpeed());

    }

    @Test
    void testBuy(){

        gameEnvironment.setCoins(20);
        bloodTest.buy(gameEnvironment, gameEnvironment.getTowerInventory());

        assertEquals(1, gameEnvironment.getTowerInventory().size());
        assertTrue(gameEnvironment.getTowerInventory().contains(bloodTest));
        assertEquals(10, gameEnvironment.getCoins());

    }

    @Test
    void testBuyNoCoins(){

        gameEnvironment.setCoins(5);
        bloodTest.buy(gameEnvironment, gameEnvironment.getTowerInventory());
        assertEquals(0, gameEnvironment.getTowerInventory().size());

    }
    @Test
    void testBuyFullInventory(){

        gameEnvironment.setCoins(100);
        for(int i = 1; i < 11; i++) {
            gameEnvironment.getTowerInventory().add(bloodTest.makeClone());
        }
        bloodTest.buy(gameEnvironment, gameEnvironment.getTowerInventory());
        assertEquals(10, gameEnvironment.getTowerInventory().size());

    }
    @Test
    void testSell(){

        gameEnvironment.setCoins(20);
        gameEnvironment.getTowerInventory().add(bloodTest);
        bloodTest.sell(gameEnvironment, gameEnvironment.getTowerInventory(), 0);

        assertEquals(0, gameEnvironment.getTowerInventory().size());
        assertEquals(25, gameEnvironment.getCoins());

    }

    @Test
    void testSellWrongIndex(){

        gameEnvironment.setCoins(20);
        gameEnvironment.getTowerInventory().add(bloodTest);
        bloodTest.sell(gameEnvironment, gameEnvironment.getTowerInventory(), 1);

        assertEquals(1, gameEnvironment.getTowerInventory().size());
        assertEquals(20, gameEnvironment.getCoins());
        assertTrue(gameEnvironment.getTowerInventory().contains(bloodTest));

    }

    @Test
    void testSellNoInventory(){

        gameEnvironment.setCoins(20);
        bloodTest.sell(gameEnvironment, gameEnvironment.getTowerInventory(), 1);

        assertEquals(0, gameEnvironment.getTowerInventory().size());
        assertEquals(20, gameEnvironment.getCoins());

    }

    @Test
    void testMakeClone(){

        TowerService bloodClone = bloodTest.makeClone();
        assertEquals(bloodTest.getLoadSize(), bloodClone.getLoadSize());
        assertEquals(bloodTest.getLoadSpeed(), bloodClone.getLoadSpeed());
        assertEquals(bloodTest.getTypes(), bloodClone.getTypes());
        assertEquals(bloodTest.getPrice(), bloodClone.getPrice());
        assertEquals(bloodTest.getTowerName(), bloodClone.getTowerName());

    }

    @Test
    void testUpgrade(){

        bloodTest.upgradeTower(loadSizeTest);
        allTest.upgradeTower(speedTest);
        assertEquals(11, bloodTest.getLoadSize());
        assertEquals(1.1, allTest.getLoadSpeed());

    }

    @Test
    void testTowerBreakHighLevel(){

        for(int i = 1; i <= 16; i++){

            bloodTest.levelUp();

        }

        assertTrue(bloodTest.doesTowerBreak());

    }
}

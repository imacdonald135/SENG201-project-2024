package seng201.team0;

import seng201.team0.models.Cart;
import seng201.team0.models.Purchasable;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;
import seng201.team0.services.CartService;
import seng201.team0.services.RoundService;
import seng201.team0.services.TowerService;
import seng201.team0.services.UpgradeService;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This game environment class contains all the back round logic and information of the game being played.
 */
public class GameEnvironment {

    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Consumer<GameEnvironment> mainScreenLauncher;
    private final Consumer<GameEnvironment> shopScreenLauncher;
    private final Consumer<GameEnvironment> inventoryScreenLauncher;
    private final Consumer<GameEnvironment> selectionScreenLauncher;
    private final Runnable clearScreen;
    private int currentRound = 0;
    private int coins = 30;
    private int numOfRounds = 5;
    private int points;
    private ArrayList<Double> pooledResourcesPerSecond = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0));
    private ArrayList<Integer> countOfEachTypeCart = new ArrayList<Integer>(Arrays.asList(0,0,0));
    private ArrayList<String> cartTimeLabelSaves = new ArrayList<String>();
    private ArrayList<String> cartFillLabelSaves = new ArrayList<String>();
    private String playerName = null;
    private boolean hardMode = false;
    private boolean startingShop = false;
    private boolean shopToInventory = false;
    private boolean mainToInventory = false;
    private boolean selectionShorterDistance = false;
    private boolean selectionHigherPrice = false;
    private boolean mainScreenFromSelection = false;
    private boolean setupDifficultySelected;
    private boolean setupNameSelected;
    private CartService easyBloodCartService = new CartService(100, new ArrayList<String>(Arrays.asList("Blood")), 5);
    private CartService easySweatCartService = new CartService(100, new ArrayList<String>(Arrays.asList("Sweat")), 5);
    private CartService easyTearsCartService = new CartService(100, new ArrayList<String>(Arrays.asList("Tears")), 5);
    private CartService easyNoBloodCartService = new CartService(100, new ArrayList<String>(Arrays.asList("Tears", "Sweat")), 10);
    private CartService easyNoSweatCartService = new CartService(100, new ArrayList<String>(Arrays.asList("Tears", "Blood")), 10);
    private CartService easyNoTearsCartService = new CartService(100, new ArrayList<String>(Arrays.asList("Blood", "Sweat")), 10);
    private CartService hardBloodCartService = new CartService(200, new ArrayList<String>(Arrays.asList("Blood")), 5);
    private CartService hardSweatCartService = new CartService(200, new ArrayList<String>(Arrays.asList("Sweat")), 5);
    private CartService hardTearsCartService = new CartService(200, new ArrayList<String>(Arrays.asList("Tears")), 5);
    private CartService hardNoBloodCartService = new CartService(200, new ArrayList<String>(Arrays.asList("Tears", "Sweat")), 10);
    private CartService hardNoSweatCartService = new CartService(200, new ArrayList<String>(Arrays.asList("Tears", "Blood")), 10);
    private CartService hardNoTearsCartService = new CartService(200, new ArrayList<String>(Arrays.asList("Blood", "Sweat")), 10);
    private CartService bossCartService = new CartService(2000, new ArrayList<>(Arrays.asList("Tears", "Blood", "Sweat")), 1);
    private RoundService round1 = new RoundService(new ArrayList<>(Arrays.asList(easyBloodCartService.makeClone())));
    private RoundService round2 = new RoundService(new ArrayList<>(Arrays.asList(easyBloodCartService.makeClone(), easyTearsCartService.makeClone())));
    private RoundService round3 = new RoundService(new ArrayList<>(Arrays.asList(easyBloodCartService.makeClone(), easyNoBloodCartService.makeClone())));
    private RoundService round4 = new RoundService(new ArrayList<>(Arrays.asList(easyTearsCartService.makeClone(), easyNoSweatCartService.makeClone())));
    private RoundService round5 = new RoundService(new ArrayList<>(Arrays.asList(easyTearsCartService.makeClone(), easyBloodCartService.makeClone())));
    private RoundService round6 = new RoundService(new ArrayList<>(Arrays.asList(hardBloodCartService.makeClone(), easyNoBloodCartService.makeClone())));
    private RoundService round7 = new RoundService(new ArrayList<>(Arrays.asList(easyNoBloodCartService.makeClone(), hardBloodCartService.makeClone(), easyTearsCartService.makeClone())));
    private RoundService round8 = new RoundService(new ArrayList<>(Arrays.asList(hardBloodCartService.makeClone(), hardTearsCartService.makeClone(), hardSweatCartService.makeClone())));
    private RoundService round9 = new RoundService(new ArrayList<>(Arrays.asList(hardNoBloodCartService.makeClone(), hardBloodCartService.makeClone(), hardTearsCartService.makeClone())));
    private RoundService round10 = new RoundService(new ArrayList<>(Arrays.asList(hardNoBloodCartService.makeClone(), hardNoSweatCartService.makeClone())));
    private RoundService round11 = new RoundService(new ArrayList<>(Arrays.asList(hardNoBloodCartService.makeClone(), hardNoTearsCartService.makeClone(), hardSweatCartService.makeClone())));
    private RoundService round12 = new RoundService(new ArrayList<>(Arrays.asList(hardNoTearsCartService.makeClone(), hardNoBloodCartService.makeClone(), hardNoSweatCartService.makeClone())));
    private RoundService round13 = new RoundService(new ArrayList<>(Arrays.asList(hardNoBloodCartService.makeClone(), hardNoBloodCartService.makeClone(), hardSweatCartService.makeClone(), easySweatCartService.makeClone())));
    private RoundService round14 = new RoundService(new ArrayList<>(Arrays.asList(hardSweatCartService.makeClone(), hardBloodCartService.makeClone(), hardTearsCartService.makeClone(), hardNoBloodCartService.makeClone(), hardNoTearsCartService.makeClone(), hardNoSweatCartService.makeClone())));
    private RoundService round15 = new RoundService(new ArrayList<>(Arrays.asList(bossCartService.makeClone())));
    private ArrayList<RoundService> rounds = new ArrayList<>(Arrays.asList(round1, round2, round3, round4, round5, round6, round7, round8, round9, round10, round11, round12, round13, round14, round15));
    private ArrayList<Purchasable<TowerService>> towerInventory = new ArrayList<>();
    private ArrayList<Purchasable<UpgradeService>> speedUpgrades = new ArrayList<>();
    private ArrayList<Purchasable<UpgradeService>> loadSizeUpgrades = new ArrayList<>();
    private ArrayList<Purchasable<UpgradeService>> lifeUpgrades = new ArrayList<>();

    /** The constructor for a game environment
     *
     * @param setupScreenLauncher the setup screen launcher
     * @param mainScreenLauncher  the main screen launcher
     * @param shopScreenLauncher the shop screen launcher
     * @param inventoryScreenLauncher the inventory screen launcher
     * @param selectionScreenLauncher the selection screen launcher
     * @param clearScreen clears the screen
     */
    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Consumer<GameEnvironment> mainScreenLauncher, Consumer<GameEnvironment> shopScreenLauncher, Consumer<GameEnvironment> inventoryScreenLauncher, Consumer<GameEnvironment> selectionScreenLauncher,  Runnable clearScreen){

        this.mainScreenLauncher = mainScreenLauncher;
        this.setupScreenLauncher = setupScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.selectionScreenLauncher = selectionScreenLauncher;
        this.clearScreen = clearScreen;
        launchSetupScreen();

    }

    /**
     * Launcher for setup screen.
     */
    public void launchSetupScreen(){

        setupScreenLauncher.accept(this);

    }

    /**
     * Launcher for main screen.
     */
    public void launchMainScreen(){

        mainScreenLauncher.accept(this);

    }

    /**
     * Launcher for shop screen.
     */
    public void launchShopScreen(){

        shopScreenLauncher.accept(this);

    }

    /**
     * Launcher for inventory screen.
     */
    public void launchInventoryScreen(){

        inventoryScreenLauncher.accept(this);

    }

    /**
     * Launcher for selection screen.
     */
    public void launchSelectionScreen(){

        selectionScreenLauncher.accept(this);

    }

    /**
     * Closes setup screen.
     */
    public void closeSetupScreen(){

        clearScreen.run();

    }

    /**
     * Closes main screen.
     */
    public void closeMainScreen(){

        clearScreen.run();

    }

    /**
     * Closes shop screen.
     */
    public void closeShopScreen(){

        clearScreen.run();

    }

    /**
     * Closes inventory screen.
     */
    public void closeInventoryScreen(){

        clearScreen.run();

    }

    /**
     * Closes selection screen.
     */
    public void closeSelectionScreen(){

        clearScreen.run();

    }


    /**
     * The getter method for player name.
     * @return the players name.
     */
    public String getPlayerName(){

        return playerName;

    }

    /**
     * The setter method for player name.
     * @param s the String the user has entered.
     */
    public void setPlayerName(String s){

        playerName = s;

    }

    /**
     * The getter method for hard mode.
     * @return hardMode boolean depending on user input.
     */
    public boolean getHardMode(){

        return hardMode;

    }

    /**
     * The setter method for hard mode.
     * @param b boolean depending on user input.
     */
    public void setHardMode(boolean b){

        hardMode = b;

    }

    /**
     * The setter method to determine whether the user is in the starting shop.
     * @param b depending on current state of app.
     */
    public void setStartingShop(boolean b){

        startingShop = b;

    }

    /**
     * The getter method for the starting shop boolean.
     * @return startingShop boolean.
     */
    public boolean getStartingShop(){

        return startingShop;

    }

    /**
     * The setter method for shopToInventory.
     * @param b the new boolean for shopToInventory.
     */
    public void setShopToInventory(boolean b){

        shopToInventory = b;

    }

    /**
     * The getter method for shopToInventory.
     * @return boolean of if player moved from shopToInventory.
     */
    public boolean getShopToInventory(){

        return shopToInventory;

    }

    /**
     * The setter method for mainToInventory.
     * @param b the new boolean for mainToInventory.
     */
    public void setMainToInventory(boolean b){

        mainToInventory = b;

    }

    /**
     * The getter method for mainToInventory.
     * @return boolean of if player moved from mainToInventory.
     */
    public boolean getMainToInventory(){

        return mainToInventory;

    }

    /**
     * The getter method for cart time label saves.
     * @return the cart time labels from round played last.
     */
    public ArrayList<String> getCartTimeLabelSaves(){

        return cartTimeLabelSaves;

    }

    /**
     * The getter method for cart fill label saves.
     * @return the cart fill labels from round played last.
     */
    public ArrayList<String> getCartFillLabelSaves(){

        return cartFillLabelSaves;

    }

    /**
     * This method resets the cart label saves to 0.
     */
    public void resetCartLabelSaves(){

        cartTimeLabelSaves = new ArrayList<String>();
        cartFillLabelSaves = new ArrayList<String>();

    }

    /**
     * The getter method for tower inventory.
     * @return the tower inventory.
     */
    public ArrayList<Purchasable<TowerService>> getTowerInventory(){

        return towerInventory;

    }

    /**
     * The getter method for speed upgrade inventory.
     * @return the speed upgrade inventory.
     */
    public ArrayList<Purchasable<UpgradeService>> getSpeedUpgrades(){

        return speedUpgrades;

    }

    /**
     * The getter method for load size upgrade inventory.
     * @return the load size upgrade inventory.
     */
    public ArrayList<Purchasable<UpgradeService>> getLoadSizeUpgrades(){

        return loadSizeUpgrades;

    }

    /**
     * The getter method for life upgrade inventory.
     * @return the life upgrade inventory.
     */
    public ArrayList<Purchasable<UpgradeService>> getLifeUpgrades(){

        return lifeUpgrades;

    }

    /**
     * The getter method for current round.
     * @return the current round.
     */
    public int getCurrentRound(){

        return currentRound;

    }

    /**
     * The getter method for rounds.
     * @return the rounds arraylist.
     */
    public ArrayList<RoundService> getRounds(){

        return rounds;

    }

    /**
     * This method iterates through the towers in your active towers and calculates if a tower broke.
     * @return -1 if tower didn't break, otherwise returns index of the tower breaking.
     */
    public int towerBreakCalculator(){

        for (int i = 0 ; i < 5 && i < towerInventory.size(); i++){

            TowerService tower = (TowerService) towerInventory.get(i);
            boolean towerBroke = tower.doesTowerBreak();

            if(towerBroke){

                towerInventory.remove(i);
                return i;

            }

        }

        return -1;

    }

    /**
     * This method levels up every tower once.
     */
    public void levelUpTowers(){

        for (int i = 0; i<5 && i<towerInventory.size(); i ++){

            TowerService tower = (TowerService) towerInventory.get(i);
            tower.levelUp();

        }

    }

    /**
     * This method has a 1/5 chance of leveling up one of the towers in the players main slots.
     * @return the index of the tower that leveled up, or -1 if no tower leveled up.
     */
    public int randomTowerLevelUpCalculator(){

        Random random = new Random();

        int index = random.nextInt(Math.min(3*towerInventory.size(), 15));

        if (index < towerInventory.size()) {

            TowerService tower = (TowerService) towerInventory.get(index);
            tower.levelUp();
            return index;

        }

        return -1;

    }

    /**
     * The getter method for coins.
     * @return coin amount.
     */
    public int getCoins(){

        return coins;

    }

    /**
     * The setter method for coin amount.
     * @param c is new coin amount.
     */
    public void setCoins(int c){

        coins = c;

    }

    /**
     * The getter method for num of rounds.
     * @return the number of rounds.
     */
    public int getNumOfRounds(){

        return numOfRounds;

    }

    /**
     * The setter method for number of rounds.
     * @param newNumOfRounds the new number of rounds in the game.
     */
    public void setNumOfRounds(int newNumOfRounds){

        numOfRounds = newNumOfRounds;

    }

    /**
     * The getter method for selectionShorterDistance.
     * @return boolean for difficulty.
     */
    public boolean getSelectionShorterDistance(){

        return selectionShorterDistance;

    }

    /**
     * The getter method for selectionHigherPrice.
     * @return boolean for difficulty.
     */
    public boolean getSelectionHigherPrice(){

        return selectionHigherPrice;

    }

    /**
     * The setter method for selectionShorterDistance.
     * @param b boolean from selection screen.
     */
    public void setSelectionShorterDistance(boolean b){

        selectionShorterDistance = b;

    }

    /**
     * The setter method for selectionHigherPrice.
     * @param b boolean from selection screen.
     */
    public void setSelectionHigherPrice(boolean b){

        selectionHigherPrice = b;

    }

    /**
     * The getter method for whether the user got to the main screen from the selection screen.
     * @return boolean from selection screen.
     */
    public boolean getMainScreenFromSelection(){

        return mainScreenFromSelection;

    }

    /**
     * The setter method for mainScreenFromSelection.
     * @param b the new boolean for mainScreenFromSelection.
     */
    public void setMainScreenFromSelection(boolean b){

        mainScreenFromSelection = b;

    }

    /**
     * The getter method for whether the user has selected a name in the setup screen.
     * @return boolean setupNameSelected.
     */
    public boolean getSetupNameSelected(){

        return setupNameSelected;

    }

    /**
     * The getter method for whether the user has selected a difficulty in the setup screen.
     * @return boolean setupDifficultySelected.
     */
    public boolean getSetupDifficultySelected(){

        return setupDifficultySelected;

    }

    /**
     * The setter method for whether the user has selected a name in the setup screen.
     * @param b true if name has been selected.
     */
    public void setSetupNameSelected(boolean b){

        setupNameSelected = b;

    }

    /**
     * The setter method for whether the user has selected a difficulty in the setup screen.
     * @param b true if difficulty has been selected.
     */
    public void setSetupDifficultySelected(boolean b){

        setupDifficultySelected = b;

    }

    /**
     * The getter for pooled recourses per second.
     * @return pooledResourcesPerSecond.
     */
    public ArrayList<Double> getPooledResourcesPerSecond(){

        return pooledResourcesPerSecond;

    }

    /**
     * This method recalculates how much of each resource the players towers and producing per second.
     */
    public void updatePooledResourcesPerSecond(){

        pooledResourcesPerSecond = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0));

        for(int i = 0; i < towerInventory.size() && i < 5; i ++) {

            TowerService t = (TowerService) towerInventory.get(i);

            if (t.getTypes().contains("Blood")) {

                pooledResourcesPerSecond.set(0, pooledResourcesPerSecond.get(0) + t.getLoadSize() / t.getLoadSpeed());

            }

            if (t.getTypes().contains("Sweat")) {

                pooledResourcesPerSecond.set(1, pooledResourcesPerSecond.get(1) + t.getLoadSize() / t.getLoadSpeed());

            }

            if (t.getTypes().contains("Tears")) {

                pooledResourcesPerSecond.set(2, pooledResourcesPerSecond.get(2) + t.getLoadSize() / t.getLoadSpeed());

            }

        }

    }

    /**
     * The getter method for countOfEachTypeOfTower
     * @return the counts of each type of tower in arraylist.
     */
    public ArrayList<Integer> getCountOfEachTypeCart(){

        return countOfEachTypeCart;

    }

    /**
     * This method resets the counts for each type of tower the player has.
     */
    public void resetCountOfEachTypeOfCount(){

        countOfEachTypeCart = new ArrayList<Integer>(Arrays.asList(0,0,0));

    }

    /**
     * The setter method for current round.
     * @param newRound the new round number.
     */
    public void setCurrentRound(int newRound){

        this.currentRound = newRound;

    }

    /**
     * The getter method for points.
     * @return the amount of points a player has.
     */
    public int getPoints(){

        return points;

    }

    /**
     * The setter method for points.
     * @param newPoints the new number of points.
     */
    public void setPoint(int newPoints){

        points = newPoints;

    }

}

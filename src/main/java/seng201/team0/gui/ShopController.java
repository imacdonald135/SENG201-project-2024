package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import seng201.team0.GameEnvironment;
import seng201.team0.services.TowerService;
import seng201.team0.services.UpgradeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for the shop.fxml window.
 */

public class ShopController {
    private final GameEnvironment gameEnvironment;
    int selectedTower;
    int selectedUpgrade;
    boolean towerSelected = false;
    boolean upgradeSelected = false;
    @FXML
    Button towerButton1;
    @FXML
    Button towerButton2;
    @FXML
    Button towerButton3;
    @FXML
    Button towerButton4;
    @FXML
    Label costInfoTower1;
    @FXML
    Label costInfoTower2;
    @FXML
    Label costInfoTower3;
    @FXML
    Label costInfoTower4;
    @FXML
    Label loadSpeedInfoTower1;
    @FXML
    Label loadSpeedInfoTower2;
    @FXML
    Label loadSpeedInfoTower3;
    @FXML
    Label loadSpeedInfoTower4;
    @FXML
    Label loadSizeInfoTower1;
    @FXML
    Label loadSizeInfoTower2;
    @FXML
    Label loadSizeInfoTower3;
    @FXML
    Label loadSizeInfoTower4;
    @FXML
    Button upgradeButton1;
    @FXML
    Button upgradeButton2;
    @FXML
    Button upgradeButton3;
    @FXML
    Label costInfoUpgrade1;
    @FXML
    Label costInfoUpgrade2;
    @FXML
    Label costInfoUpgrade3;
    @FXML
    Button backButton;
    @FXML
    Button buyButton;
    @FXML
    Button inventoryButton;
    @FXML
    Label coinsValue;

    private TowerService bloodTower = new TowerService(10, 1, new ArrayList<>(List.of("Blood")), 10,  "Blood Tower");
    private TowerService sweatTower = new TowerService(10, 1, new ArrayList<>(List.of("Sweat")), 10, "Sweat Tower");
    private TowerService tearsTower = new TowerService(10, 1, new ArrayList<>(List.of("Tears")), 10, "Tears Tower");
    private TowerService allTower = new TowerService(10, 1, new ArrayList<>(Arrays.asList("Blood", "Sweat", "Tears")), 50, "All Tower");

    private UpgradeService loadSizeUpgrade = new UpgradeService("LoadSize", 1.1, 5);
    private UpgradeService speedUpgrade = new UpgradeService("Speed", 1.1, 5);
    private UpgradeService lifeUpgrade = new UpgradeService(20);

    private ArrayList<TowerService> towerServiceArrayList = new ArrayList<>(Arrays.asList(bloodTower, sweatTower, tearsTower, allTower));
    private ArrayList<UpgradeService> upgradeServiceArrayList = new ArrayList<>(Arrays.asList(loadSizeUpgrade, speedUpgrade, lifeUpgrade));

    /** The constructor for the ShopController, takes a game environment as a parameter.
     * @param g GameEnvironment object.
     */
    public ShopController(GameEnvironment g){

        gameEnvironment = g;

    }

    /**
     * Initializer for ShopController, determines whether the user has come from the setup screen and initializes accordingly.
     */
    public void initialize() {

        updateBuy();

        coinsValue.setText(String.valueOf(gameEnvironment.getCoins()));

        costInfoTower1.setText(String.valueOf(bloodTower.getPrice()));
        costInfoTower2.setText(String.valueOf(sweatTower.getPrice()));
        costInfoTower3.setText(String.valueOf(tearsTower.getPrice()));
        costInfoTower4.setText(String.valueOf(allTower.getPrice()));
        buyButton.setDisable(true);

        selectedTower = -1;
        selectedUpgrade = -1;

        costInfoUpgrade1.setText(String.valueOf(loadSizeUpgrade.getPrice()));
        costInfoUpgrade2.setText(String.valueOf(speedUpgrade.getPrice()));
        costInfoUpgrade3.setText(String.valueOf(lifeUpgrade.getPrice()));

        upgradeButton1.setText("Increase load size by 1.1X");
        upgradeButton2.setText("Increase load speed by 1.1X");
        upgradeButton3.setText("Gain an extra life");

        loadSpeedInfoTower1.setText(String.valueOf(bloodTower.getLoadSpeed()));
        loadSpeedInfoTower2.setText(String.valueOf(sweatTower.getLoadSpeed()));
        loadSpeedInfoTower3.setText(String.valueOf(tearsTower.getLoadSpeed()));
        loadSpeedInfoTower4.setText(String.valueOf(allTower.getLoadSpeed()));

        loadSizeInfoTower1.setText(String.valueOf(bloodTower.getLoadSize()));
        loadSizeInfoTower2.setText(String.valueOf(sweatTower.getLoadSize()));
        loadSizeInfoTower3.setText(String.valueOf(tearsTower.getLoadSize()));
        loadSizeInfoTower4.setText(String.valueOf(allTower.getLoadSize()));

        towerButton1.setText(bloodTower.getTowerName());
        towerButton2.setText(sweatTower.getTowerName());
        towerButton3.setText(tearsTower.getTowerName());
        towerButton4.setText(allTower.getTowerName());

        if (gameEnvironment.getStartingShop()) {

            upgradeButton1.setDisable(true);
            upgradeButton2.setDisable(true);
            upgradeButton3.setDisable(true);

        }

    }

    /**
     * On Action method for the back button, opens the appropriate screen.
     */
    public void backPressed(){

        gameEnvironment.closeShopScreen();

        if (gameEnvironment.getStartingShop()) {
            gameEnvironment.launchSetupScreen();
        } else{
            gameEnvironment.setMainScreenFromSelection(false);
            gameEnvironment.launchMainScreen();
        }

    }

    /**
     * On Action button for tower 1, shows the tower as selected.
     */
    public void tower1Pressed(){

        towerSelected = true;
        upgradeSelected = false;
        selectedTower = 0;
        buyButton.setDisable(false);

        towerButton1.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        towerButton2.setStyle("");
        towerButton3.setStyle("");
        towerButton4.setStyle("");
        upgradeButton1.setStyle("");
        upgradeButton2.setStyle("");
        upgradeButton3.setStyle("");

        updateBuy();

    }

    /**
     * On Action button for tower 2, shows the tower as selected.
     */
    public void tower2Pressed(){

        towerSelected = true;
        upgradeSelected = false;
        selectedTower = 1;
        buyButton.setDisable(false);

        towerButton2.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        towerButton1.setStyle("");
        towerButton3.setStyle("");
        towerButton4.setStyle("");
        upgradeButton1.setStyle("");
        upgradeButton2.setStyle("");
        upgradeButton3.setStyle("");

        updateBuy();

    }

    /**
     * On Action button for tower 3, shows the tower as selected.
     */
    public void tower3Pressed(){

        towerSelected = true;
        upgradeSelected = false;
        selectedTower = 2;
        buyButton.setDisable(false);

        towerButton3.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        towerButton2.setStyle("");
        towerButton1.setStyle("");
        towerButton4.setStyle("");
        upgradeButton1.setStyle("");
        upgradeButton2.setStyle("");
        upgradeButton3.setStyle("");

        updateBuy();

    }

    /**
     * On Action button for tower 4, shows the tower as selected.
     */
    public void tower4Pressed(){

        towerSelected = true;
        upgradeSelected = false;
        selectedTower = 3;
        buyButton.setDisable(false);

        towerButton4.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        towerButton2.setStyle("");
        towerButton3.setStyle("");
        towerButton1.setStyle("");
        upgradeButton1.setStyle("");
        upgradeButton2.setStyle("");
        upgradeButton3.setStyle("");

        updateBuy();

    }
    /**
     * On Action button for upgrade 1, shows the upgrade as selected.
     */
    public void upgrade1Pressed(){

        towerSelected = false;
        upgradeSelected = true;
        selectedUpgrade = 0;
        buyButton.setDisable(false);

        towerButton4.setStyle("");
        towerButton2.setStyle("");
        towerButton3.setStyle("");
        towerButton1.setStyle("");
        upgradeButton1.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        upgradeButton2.setStyle("");
        upgradeButton3.setStyle("");

        updateBuy();

    }

    /**
     * On Action button for upgrade 2, shows the upgrade as selected.
     */
    public void upgrade2Pressed(){

        towerSelected = false;
        upgradeSelected = true;
        selectedUpgrade = 1;
        buyButton.setDisable(false);

        towerButton4.setStyle("");
        towerButton2.setStyle("");
        towerButton3.setStyle("");
        towerButton1.setStyle("");
        upgradeButton1.setStyle("");
        upgradeButton2.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        upgradeButton3.setStyle("");

        updateBuy();

    }

    /**
     * On Action button for upgrade 3, shows the upgrade as selected.
     */
    public void upgrade3Pressed(){

        towerSelected = false;
        upgradeSelected = true;
        selectedUpgrade = 2;
        buyButton.setDisable(false);

        towerButton4.setStyle("");
        towerButton2.setStyle("");
        towerButton3.setStyle("");
        towerButton1.setStyle("");
        upgradeButton1.setStyle("");
        upgradeButton2.setStyle("");
        upgradeButton3.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");

        updateBuy();

    }

    /**
     * On Action method for the inventory button, sets the shopToInventory method in GameEnvironment.
     */
    public void inventoryPressed(){

        gameEnvironment.closeShopScreen();
        gameEnvironment.launchInventoryScreen();
        gameEnvironment.setShopToInventory(true);
        gameEnvironment.setMainToInventory(false);

    }

    /**
     * On Action method for the buy button, clones a tower or upgrade and adds it to the users inventory.
     */
    public void onBuyPressed(){

        if (towerSelected) {

            TowerService clone = towerServiceArrayList.get(selectedTower).makeClone();
            clone.buy(gameEnvironment, gameEnvironment.getTowerInventory());
            coinsValue.setText(String.valueOf(gameEnvironment.getCoins()));

        }

        if (upgradeSelected) {

            if (selectedUpgrade == 0) {

                UpgradeService clone = loadSizeUpgrade.makeClone();
                clone.buy(gameEnvironment, gameEnvironment.getLoadSizeUpgrades());

            }

            if (selectedUpgrade == 1) {

                UpgradeService clone = speedUpgrade.makeClone();
                clone.buy(gameEnvironment, gameEnvironment.getSpeedUpgrades());

            }

            if (selectedUpgrade == 2) {

                UpgradeService clone = lifeUpgrade.makeClone();
                clone.buy(gameEnvironment, gameEnvironment.getLifeUpgrades());

            }

            coinsValue.setText(String.valueOf(gameEnvironment.getCoins()));

        }

        updateBuy();

    }

    /**
     * Helper function for determining whether the user can buy another tower or upgrade.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public void updateBuy(){

        boolean buyable = true;

        if (gameEnvironment.getCoins() <= 0){

            buyable = false;

        }

        if (towerSelected) {

            if (gameEnvironment.getCoins() - towerServiceArrayList.get(selectedTower).getPrice() < 0) {

                buyable = false;

            }

            if (gameEnvironment.getTowerInventory().size() > 9) {

                buyable = false;

            }

        }

        if(upgradeSelected){

            if (gameEnvironment.getCoins() - upgradeServiceArrayList.get(selectedUpgrade).getPrice() < 0) {
                buyable = false;

            }

        }

        if(!buyable){

            buyButton.setDisable(true);

        } else {

            buyButton.setDisable(false);

        }

    }

}

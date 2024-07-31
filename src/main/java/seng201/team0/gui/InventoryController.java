package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.GameEnvironment;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import seng201.team0.services.TowerService;
import seng201.team0.services.UpgradeService;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Controller for the inventory.fxml window.
 */

public class InventoryController {
    private final GameEnvironment gameEnvironment;

    /**
     * The constructor for the InventoryController, takes a game environment as a parameter
     * @param g GameEnvironment for current game.
     */
    public InventoryController(GameEnvironment g){

        gameEnvironment = g;

    }
    @FXML
    Label inventoryLabel;
    @FXML
    Button towerButton1;
    @FXML
    Button towerButton2;
    @FXML
    Button towerButton3;
    @FXML
    Button towerButton4;
    @FXML
    Button towerButton5;
    @FXML
    Button backupTowerButton1;
    @FXML
    Button backupTowerButton2;
    @FXML
    Button backupTowerButton3;
    @FXML
    Button backupTowerButton4;
    @FXML
    Button backupTowerButton5;
    @FXML
    Button upgradeButton1;
    @FXML
    Button upgradeButton2;
    @FXML
    Button upgradeButton3;
    @FXML
    Button backButton;
    @FXML
    Button applyUpgradeButton;
    @FXML
    Button swapTowersButton;
    @FXML
    Button sellButton;
    @FXML
    Label upgradesBanner;
    @FXML
    Label backupsBanner;
    @FXML
    Label currentTowersBanner;
    @FXML
    Label upgradeQuantity1;
    @FXML
    Label upgradeQuantity2;
    @FXML
    Label upgradeQuantity3;
    @FXML
    Label backup1LoadSpeed;
    @FXML
    Label backup2LoadSpeed;
    @FXML
    Label backup3LoadSpeed;
    @FXML
    Label backup4LoadSpeed;
    @FXML
    Label backup5LoadSpeed;
    @FXML
    Label backup1LoadSize;
    @FXML
    Label backup2LoadSize;
    @FXML
    Label backup3LoadSize;
    @FXML
    Label backup4LoadSize;
    @FXML
    Label backup5LoadSize;
    @FXML
    Label tower1LoadSpeed;
    @FXML
    Label tower2LoadSpeed;
    @FXML
    Label tower3LoadSpeed;
    @FXML
    Label tower4LoadSpeed;
    @FXML
    Label tower5LoadSpeed;
    @FXML
    Label tower1LoadSize;
    @FXML
    Label tower2LoadSize;
    @FXML
    Label tower3LoadSize;
    @FXML
    Label tower4LoadSize;
    @FXML
    Label tower5LoadSize;
    @FXML
    Label swappingInfo;
    @FXML
    Label upgradeInfo;
    int selectedTower;
    int selectedCurrentTower;
    int selectedBackupTower;
    int selectedUpgrade;
    boolean upgradeSelected = false;
    boolean towerSelected = false;
    boolean backupSelected = false;

    /**
     * Initializer for InventoryController, updates the GUI.
     */
    public void initialize(){

        applyUpgradeButton.setDisable(true);
        swapTowersButton.setDisable(true);
        upgradeButton1.setText("Load Size");
        upgradeButton2.setText("Speed");
        upgradeButton3.setText("Extra Life");
        updateValues();
        updateButtonsHelper();

    }

    /**
     * Helper function for updating all the labels in the inventory screen.
     */
    public void updateValues(){

        ArrayList<Button> inventoryTowerButtons = new ArrayList<>(Arrays.asList(towerButton1, towerButton2, towerButton3, towerButton4, towerButton5, backupTowerButton1, backupTowerButton2, backupTowerButton3, backupTowerButton4, backupTowerButton5));
        ArrayList<Button> inventoryUpgradeButtons = new ArrayList<>(Arrays.asList(upgradeButton1, upgradeButton2, upgradeButton3));
        ArrayList<Label> inventoryLoadSizeLabels = new ArrayList<>(Arrays.asList(tower1LoadSize, tower2LoadSize, tower3LoadSize, tower4LoadSize, tower5LoadSize, backup1LoadSize, backup2LoadSize, backup3LoadSize, backup4LoadSize, backup5LoadSize));
        ArrayList<Label> inventoryLoadSpeedLabels = new ArrayList<>(Arrays.asList(tower1LoadSpeed, tower2LoadSpeed, tower3LoadSpeed, tower4LoadSpeed, tower5LoadSpeed, backup1LoadSpeed, backup2LoadSpeed, backup3LoadSpeed, backup4LoadSpeed, backup5LoadSpeed));

        for (int i = 0; i < 10; i++) {

            inventoryTowerButtons.get(i).setDisable(true);
            inventoryTowerButtons.get(i).setStyle("");
            inventoryTowerButtons.get(i).setText("");
            inventoryLoadSizeLabels.get(i).setText("");
            inventoryLoadSpeedLabels.get(i).setText("");

        }

        for (int i = 0; i < 3; i++) {

            inventoryUpgradeButtons.get(i).setDisable(true);
            inventoryUpgradeButtons.get(i).setStyle("");

        }

        if (!gameEnvironment.getTowerInventory().isEmpty()) {

            for (int i = 0; i < gameEnvironment.getTowerInventory().size(); i++) {

                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                inventoryTowerButtons.get(i).setDisable(false);
                inventoryTowerButtons.get(i).setText(((TowerService)gameEnvironment.getTowerInventory().get(i)).getTowerName());
                inventoryLoadSizeLabels.get(i).setText(decimalFormat.format(((TowerService)gameEnvironment.getTowerInventory().get(i)).getLoadSize()));
                inventoryLoadSpeedLabels.get(i).setText(decimalFormat.format(((TowerService)gameEnvironment.getTowerInventory().get(i)).getLoadSpeed()));

            }

        }

        if (!gameEnvironment.getLoadSizeUpgrades().isEmpty()) {

            inventoryUpgradeButtons.get(0).setDisable(false);

        }

        if (!gameEnvironment.getSpeedUpgrades().isEmpty()) {

            inventoryUpgradeButtons.get(1).setDisable(false);

        }

        if (!gameEnvironment.getLifeUpgrades().isEmpty()) {

            inventoryUpgradeButtons.get(2).setDisable(false);

        }

        upgradeQuantity1.setText(String.valueOf(gameEnvironment.getLoadSizeUpgrades().size()));
        upgradeQuantity2.setText(String.valueOf(gameEnvironment.getSpeedUpgrades().size()));
        upgradeQuantity3.setText(String.valueOf(gameEnvironment.getLifeUpgrades().size()));

    }

    /**
     * On Action method for the back button, closes the inventory screen and opens the appropriate screen.
     */
    public void backPressed(){

        gameEnvironment.closeInventoryScreen();
        if (gameEnvironment.getMainToInventory()) {

            gameEnvironment.setMainScreenFromSelection(false);
            gameEnvironment.launchMainScreen();

        }

        if (gameEnvironment.getShopToInventory()) {

            gameEnvironment.launchShopScreen();

        }

    }

    /**
     * On Action method for the first upgrade button, shows this button as selected and deselects last button.
     */
    public void upgrade1Pressed(){

        if (selectedUpgrade == 0 && upgradeSelected) {

            upgradeButton1.setStyle("");
            upgradeSelected = false;
            applyUpgradeButton.setDisable(true);

        } else {

            upgradeButton1.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");
            upgradeSelected = true;
            applyUpgradeButton.setDisable(false);

        }

        selectedUpgrade = 0;
        updateButtonsHelper();
        upgradeButton2.setStyle("");
        upgradeButton3.setStyle("");

    }

    /**
     * On Action method for the second upgrade button, shows this button as selected and deselects last button.
     */
    public void upgrade2Pressed(){

        if (selectedUpgrade == 1 && upgradeSelected) {

            upgradeButton2.setStyle("");
            upgradeSelected = false;
            applyUpgradeButton.setDisable(true);

        } else {
            upgradeButton2.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");
            upgradeSelected = true;
            applyUpgradeButton.setDisable(false);
        }

        selectedUpgrade = 1;
        updateButtonsHelper();
        upgradeButton1.setStyle("");
        upgradeButton3.setStyle("");

    }

    /**
     * On Action method for the third upgrade button, shows this button as selected and deselects last button.
     */
    public void upgrade3Pressed(){

        if (selectedUpgrade == 2 && upgradeSelected) {

            upgradeButton3.setStyle("");
            upgradeSelected = false;
            applyUpgradeButton.setDisable(true);

        } else {

            upgradeButton3.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");
            upgradeSelected = true;
            applyUpgradeButton.setDisable(false);

        }

        selectedUpgrade = 2;
        updateButtonsHelper();
        upgradeButton1.setStyle("");
        upgradeButton2.setStyle("");

    }

    /**
     * On Action method for the first tower button, shows this button as selected and deselects last button.
     */
    public void tower1Pressed(){

        if (selectedTower == 0 && towerSelected) {

            towerButton1.setStyle("");
            towerSelected = false;

        } else {

            towerButton1.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            towerSelected = true;

        }

        selectedTower = 0;
        selectedCurrentTower = 0;
        updateButtonsHelper();
        towerButton2.setStyle("");
        towerButton3.setStyle("");
        towerButton4.setStyle("");
        towerButton5.setStyle("");

    }

    /**
     * On Action method for the second tower button, shows this button as selected and deselects last button.
     */
    public void tower2Pressed(){

        if (selectedTower == 1 && towerSelected) {

            towerButton2.setStyle("");
            towerSelected = false;

        } else {

            towerButton2.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            towerSelected = true;

        }

        selectedTower = 1;
        selectedCurrentTower = 1;
        updateButtonsHelper();
        towerButton1.setStyle("");
        towerButton3.setStyle("");
        towerButton4.setStyle("");
        towerButton5.setStyle("");

    }

    /**
     * On Action method for the third tower button, shows this button as selected and deselects last button.
     */
    public void tower3Pressed(){

        if (selectedTower == 2 && towerSelected) {

            towerButton3.setStyle("");
            towerSelected = false;

        } else {

            towerButton3.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            towerSelected = true;

        }

        selectedTower = 2;
        selectedCurrentTower = 2;
        updateButtonsHelper();
        towerButton2.setStyle("");
        towerButton1.setStyle("");
        towerButton4.setStyle("");
        towerButton5.setStyle("");

    }

    /**
     * On Action method for the first fourth button, shows this button as selected and deselects last button.
     */
    public void tower4Pressed(){

        if (selectedTower == 3 && towerSelected) {

            towerButton4.setStyle("");
            towerSelected = false;

        } else {

            towerButton4.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            towerSelected = true;

        }

        selectedTower = 3;
        selectedCurrentTower = 3;
        updateButtonsHelper();
        towerButton2.setStyle("");
        towerButton3.setStyle("");
        towerButton1.setStyle("");
        towerButton5.setStyle("");

    }

    /**
     * On Action method for the fifth tower button, shows this button as selected and deselects last button.
     */
    public void tower5Pressed(){

        if (selectedTower == 4 && towerSelected) {

            towerButton5.setStyle("");
            towerSelected = false;

        } else {

            towerButton5.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            towerSelected = true;

        }

        selectedTower = 4;
        selectedCurrentTower = 4;
        updateButtonsHelper();
        towerButton2.setStyle("");
        towerButton3.setStyle("");
        towerButton4.setStyle("");
        towerButton1.setStyle("");

    }

    /**
     * On Action method for the first backup tower button, shows this button as selected and deselects last button.
     */
    public void backup1Pressed(){

        if (selectedTower == 5 && backupSelected) {

            backupTowerButton1.setStyle("");
            backupSelected = false;

        } else {

            backupTowerButton1.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            backupSelected = true;

        }

        selectedTower = 5;
        selectedBackupTower = 0;
        updateButtonsHelper();
        backupTowerButton2.setStyle("");
        backupTowerButton3.setStyle("");
        backupTowerButton4.setStyle("");
        backupTowerButton5.setStyle("");

    }

    /**
     * On Action method for the second backup tower button, shows this button as selected and deselects last button.
     */
    public void backup2Pressed(){

        if (selectedTower == 6 && backupSelected) {

            backupTowerButton2.setStyle("");
            backupSelected = false;

        } else {

            backupTowerButton2.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            backupSelected = true;

        }

        selectedTower = 6;
        selectedBackupTower = 1;
        updateButtonsHelper();
        backupTowerButton1.setStyle("");
        backupTowerButton3.setStyle("");
        backupTowerButton4.setStyle("");
        backupTowerButton5.setStyle("");

    }

    /**
     * On Action method for the third backup tower button, shows this button as selected and deselects last button.
     */
    public void backup3Pressed(){

        if (selectedTower == 7 && backupSelected) {

            backupTowerButton3.setStyle("");
            backupSelected = false;

        } else {

            backupTowerButton3.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            backupSelected = true;

        }

        selectedTower = 7;
        selectedBackupTower = 2;
        updateButtonsHelper();
        backupTowerButton2.setStyle("");
        backupTowerButton1.setStyle("");
        backupTowerButton4.setStyle("");
        backupTowerButton5.setStyle("");

    }

    /**
     * On Action method for the fourth backup tower button, shows this button as selected and deselects last button.
     */
    public void backup4Pressed(){

        if (selectedTower == 8 && backupSelected) {

            backupTowerButton4.setStyle("");
            backupSelected = false;

        } else {

            backupTowerButton4.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            backupSelected = true;

        }

        selectedTower = 8;
        selectedBackupTower = 3;
        updateButtonsHelper();
        backupTowerButton2.setStyle("");
        backupTowerButton3.setStyle("");
        backupTowerButton1.setStyle("");
        backupTowerButton5.setStyle("");

    }

    /**
     * On Action method for the fifth backup tower button, shows this button as selected and deselects last button.
     */
    public void backup5Pressed(){

        if (selectedTower == 9 && backupSelected) {

            backupTowerButton5.setStyle("");
            backupSelected = false;

        } else {

            backupTowerButton5.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
            backupSelected = true;

        }

        selectedTower = 9;
        selectedBackupTower = 4;
        updateButtonsHelper();
        backupTowerButton2.setStyle("");
        backupTowerButton3.setStyle("");
        backupTowerButton4.setStyle("");
        backupTowerButton1.setStyle("");

    }

    /**
     * On Action method for the apply upgrade button, determines what upgrade it is and then applies it to the selected tower.
     */
    public void applyUpgradePressed(){

        TowerService tower = (TowerService) gameEnvironment.getTowerInventory().get(selectedTower);

        if (selectedUpgrade == 0) {

            tower.upgradeTower((UpgradeService)gameEnvironment.getLoadSizeUpgrades().get(0));
            gameEnvironment.getLoadSizeUpgrades().remove(0);

        }

        if (selectedUpgrade == 1) {

            tower.upgradeTower((UpgradeService)gameEnvironment.getSpeedUpgrades().get(0));
            gameEnvironment.getSpeedUpgrades().remove(0);

        }

        upgradeSelected = false;
        towerSelected = false;
        backupSelected = false;
        updateValues();
        updateButtonsHelper();

    }

    /**
     * On Action method for the swap towers button, swaps the two selected towers in the GUI and in GameEnvironment.
     */
    public void swapTowersPressed(){

        Collections.swap(gameEnvironment.getTowerInventory(), selectedCurrentTower, selectedBackupTower + 5);
        updateValues();
        towerSelected = false;
        backupSelected = false;
        updateButtonsHelper();

    }

    /**
     * Helper function for updating the apply upgrade, swap towers, and sell button.
     */
    public void updateButtonsHelper(){

        applyUpgradeButton.setDisable(((!towerSelected || !upgradeSelected) && (!backupSelected || !upgradeSelected)) || (selectedUpgrade != 0 && selectedUpgrade != 1));
        swapTowersButton.setDisable(!towerSelected || !backupSelected);

        if (towerSelected && backupSelected && upgradeSelected) {

            applyUpgradeButton.setDisable(true);
            swapTowersButton.setDisable(true);

        }

        sellButton.setDisable((!towerSelected || backupSelected || upgradeSelected) && (towerSelected || !backupSelected || upgradeSelected) && (towerSelected || backupSelected || !upgradeSelected));

    }

    /**
     * On Action method for the sell button, determines what object is selected and sells the object. Removes the object from GameEnvironment and calls updateValues() to remove from GUI.
     */
    public void sellPressed(){

        if (towerSelected || backupSelected) {

            gameEnvironment.getTowerInventory().get(selectedTower).sell(gameEnvironment, gameEnvironment.getTowerInventory(), selectedTower);
            towerSelected = false;
            backupSelected = false;

        }

        if (upgradeSelected) {

            if (selectedUpgrade == 0) {

                gameEnvironment.getLoadSizeUpgrades().get(0).sell(gameEnvironment, gameEnvironment.getLoadSizeUpgrades(), selectedUpgrade);

            }

            if (selectedUpgrade == 1) {

                gameEnvironment.getSpeedUpgrades().get(0).sell(gameEnvironment, gameEnvironment.getSpeedUpgrades(), selectedUpgrade);

            }

            if (selectedUpgrade == 2) {

                gameEnvironment.getLifeUpgrades().get(0).sell(gameEnvironment, gameEnvironment.getLifeUpgrades(), selectedUpgrade);

            }

            upgradeSelected = false;

        }

        updateValues();
        updateButtonsHelper();

    }

}
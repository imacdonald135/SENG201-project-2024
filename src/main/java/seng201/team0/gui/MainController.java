package seng201.team0.gui;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng201.team0.GameEnvironment;
import seng201.team0.exceptions.RoundLostException;
import seng201.team0.exceptions.RoundWonException;
import seng201.team0.services.CartService;
import seng201.team0.services.RoundService;
import seng201.team0.services.TowerService;

import javax.swing.text.html.ImageView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Controller for the main.fxml window
 * @author seng201 teaching team
 */
public class MainController {

    @FXML
    Label wonLostLabel;
    @FXML
    Label finalScreenLabel;
    @FXML
    Label pointsLabel;
    @FXML
    Label mainCoinsLabel;
    @FXML
    Label playerMessageLabel;
    @FXML
    Label mainPointsLabel;
    @FXML
    Button mainShopButton;
    @FXML
    Button mainInventoryButton;
    @FXML
    Button mainTowerButton1;
    @FXML
    Button mainTowerButton2;
    @FXML
    Button mainTowerButton3;
    @FXML
    Button mainTowerButton4;
    @FXML
    Button mainTowerButton5;
    @FXML
    Label mainRoundNumberLabel;
    @FXML
    Label cartTimeLeft1;
    @FXML
    Label cartTimeLeft2;
    @FXML
    Label cartTimeLeft3;
    @FXML
    Label cartTimeLeft4;
    @FXML
    Label cartTimeLeft5;
    @FXML
    Label cartTimeLeft6;
    @FXML
    Label cartTimeLeft7;
    @FXML
    Label cartTimeLeft8;
    @FXML
    Label cartTimeLeft9;
    @FXML
    Label cartTimeLeft10;
    @FXML
    Label cartCapacityLabel1;
    @FXML
    Label cartCapacityLabel2;
    @FXML
    Label cartCapacityLabel3;
    @FXML
    Label cartCapacityLabel4;
    @FXML
    Label cartCapacityLabel5;
    @FXML
    Label cartCapacityLabel6;
    @FXML
    Label cartCapacityLabel7;
    @FXML
    Label cartCapacityLabel8;
    @FXML
    Label cartCapacityLabel9;
    @FXML
    Label cartCapacityLabel10;
    @FXML
    Label mainMessageOutLabel1;
    @FXML
    Label mainMessageOutLabel2;
    @FXML
    Label mainTowerReloadSpeed1;
    @FXML
    Label mainTowerReloadSpeed2;
    @FXML
    Label mainTowerReloadSpeed3;
    @FXML
    Label mainTowerReloadSpeed4;
    @FXML
    Label mainTowerReloadSpeed5;
    @FXML
    Label mainTowerLoadSize1;
    @FXML
    Label mainTowerLoadSize2;
    @FXML
    Label mainTowerLoadSize3;
    @FXML
    Label mainTowerLoadSize4;
    @FXML
    Label mainTowerLoadSize5;
    @FXML
    Label mainTowerLevel1;
    @FXML
    Label mainTowerLevel2;
    @FXML
    Label mainTowerLevel3;
    @FXML
    Label mainTowerLevel4;
    @FXML
    Label mainTowerLevel5;
    @FXML
    Label mainCartsLabel;
    @FXML
    Label mainTowersLabel;
    @FXML
    Button mainCartButton1;
    @FXML
    Button mainCartButton2;
    @FXML
    Button mainCartButton3;
    @FXML
    Button mainCartButton4;
    @FXML
    Button mainCartButton5;
    @FXML
    Button mainCartButton6;
    @FXML
    Button mainCartButton7;
    @FXML
    Button mainCartButton8;
    @FXML
    Button mainCartButton9;
    @FXML
    Button mainCartButton10;
    @FXML
    Button startButton;
    @FXML
    Label playerNameLabel;
    private final GameEnvironment gameEnvironment;


    /**
     * The constructor for the SetupController, takes a game environment as a parameter.
     * @param g the game environment.
     */
    public MainController(GameEnvironment g){

        gameEnvironment = g;

    }

    /**
     * The On Action method for the shop button, takes user to the shop.
     */

    public void onMainShopButtonClicked(){

        gameEnvironment.closeMainScreen();
        gameEnvironment.launchShopScreen();

    }

    /**
     * The On Action method for the inventory button, takes user to the inventory.
     */

    public void onMainInventoryButtonClicked(){

        gameEnvironment.closeMainScreen();
        gameEnvironment.launchInventoryScreen();
        gameEnvironment.setMainToInventory(true);
        gameEnvironment.setShopToInventory(false);

    }

    /**
     * The On Action method for the start button, takes user to the selection screen.
     */
    public void startPressed(){

        gameEnvironment.closeMainScreen();
        gameEnvironment.launchSelectionScreen();

    }

    /**
     * Initializes the main screen at the primary stage.
     * @param primaryStage the primary stage
     */
    public void init(Stage primaryStage) {}

    /**
     * The initializer for the main screen.
     */
    public void initialize(){

        wonLostLabel.setDisable(true);
        finalScreenLabel.setDisable(true);
        pointsLabel.setDisable(true);
        wonLostLabel.setOpacity(0);
        finalScreenLabel.setOpacity(0);
        pointsLabel.setOpacity(0);
        playerNameLabel.setOpacity(0);
        gameEnvironment.updatePooledResourcesPerSecond();
        mainMessageOutLabel1.setText("");
        updateValues();
        ArrayList<Label> cartTimes = new ArrayList<>(Arrays.asList(cartTimeLeft1, cartTimeLeft2,cartTimeLeft3, cartTimeLeft4, cartTimeLeft5,cartTimeLeft6, cartTimeLeft7,cartTimeLeft8,cartTimeLeft9, cartTimeLeft10));
        ArrayList<Label> cartCapacities = new ArrayList<>(Arrays.asList(cartCapacityLabel1, cartCapacityLabel2,cartCapacityLabel3, cartCapacityLabel4, cartCapacityLabel5,cartCapacityLabel6, cartCapacityLabel7,cartCapacityLabel8,cartCapacityLabel9, cartCapacityLabel10));
        ArrayList<Button> cartButtons = new ArrayList<>((Arrays.asList(mainCartButton1,mainCartButton2,mainCartButton3,mainCartButton4,mainCartButton5,mainCartButton6, mainCartButton7,mainCartButton8,mainCartButton9, mainCartButton10)));

        for (int i = 0; i < 10; i++) {

            cartButtons.get(i).setDisable(true);
            cartCapacities.get(i).setText("");
            cartTimes.get(i).setText("");

        }

        for (int i = 0; i < gameEnvironment.getCartFillLabelSaves().size(); i ++){

            cartButtons.get(i).setDisable(false);
            cartCapacities.get(i).setText(gameEnvironment.getCartFillLabelSaves().get(i));
            cartTimes.get(i).setText(gameEnvironment.getCartTimeLabelSaves().get(i));

        }

        if (gameEnvironment.getMainScreenFromSelection()) {

            startRound();

        }

        mainRoundNumberLabel.setText("Next Round: "+(gameEnvironment.getCurrentRound()+1)+"/"+gameEnvironment.getNumOfRounds());

        if (gameEnvironment.getCurrentRound() == gameEnvironment.getNumOfRounds()) {

            startButton.setDisable(true);
            clearAndDisableEverything(true);

        }

    }

    /**
     * This method sets up all the labels on the main screen before the round calculations start.
     */
    public void initialiseRound(){

        int roundNum = gameEnvironment.getCurrentRound();
        RoundService round = gameEnvironment.getRounds().get(roundNum);
        ArrayList<Label> cartTimes = new ArrayList<>(Arrays.asList(cartTimeLeft1, cartTimeLeft2,cartTimeLeft3, cartTimeLeft4, cartTimeLeft5,cartTimeLeft6, cartTimeLeft7,cartTimeLeft8,cartTimeLeft9, cartTimeLeft10));
        ArrayList<Label> cartCapacities = new ArrayList<>(Arrays.asList(cartCapacityLabel1, cartCapacityLabel2,cartCapacityLabel3, cartCapacityLabel4, cartCapacityLabel5,cartCapacityLabel6, cartCapacityLabel7,cartCapacityLabel8,cartCapacityLabel9, cartCapacityLabel10));
        ArrayList<Button> cartButtons = new ArrayList<>((Arrays.asList(mainCartButton1,mainCartButton2,mainCartButton3,mainCartButton4,mainCartButton5,mainCartButton6, mainCartButton7,mainCartButton8,mainCartButton9, mainCartButton10)));

        for (int i = 0; i < round.getCarts().size(); i++){

            CartService cart = round.getCarts().get(i);

            if (gameEnvironment.getSelectionShorterDistance()) {

                cart.setTimeLeft((int) (cart.getTimeLeft()*0.75));

            }

            cartTimes.get(i).setText(String.valueOf(cart.getTimeLeft()));
            ArrayList<String> types = cart.getTypes();

            if (types.contains("Blood")) {

                gameEnvironment.getCountOfEachTypeCart().set(0, gameEnvironment.getCountOfEachTypeCart().get(0)+1);

            }

            if (types.contains("Sweat")) {

                gameEnvironment.getCountOfEachTypeCart().set(1, gameEnvironment.getCountOfEachTypeCart().get(1)+1);

            }

            if (types.contains("Tears")) {

                gameEnvironment.getCountOfEachTypeCart().set(2, gameEnvironment.getCountOfEachTypeCart().get(2)+1);

            }

            cartCapacities.get(i).setText("0/"+round.getCarts().get(i).getSize());
            cartButtons.get(i).setDisable(false);

        }

    }

    /**
     * This method clears and disables every button, and enables the final screen labels. It was done this way as the launcher
     * for the final screen wasn't working.
     * @param b true if the player won.
     */
    public void clearAndDisableEverything(boolean b){

        playerMessageLabel.setOpacity(0);
        mainPointsLabel.setOpacity(0);
        mainCoinsLabel.setOpacity(0);
        mainShopButton.setOpacity(0);
        mainInventoryButton.setOpacity(0);
        mainTowerButton1.setOpacity(0);
        mainTowerButton2.setOpacity(0);
        mainTowerButton3.setOpacity(0);
        mainTowerButton4.setOpacity(0);
        mainTowerButton5.setOpacity(0);
        mainRoundNumberLabel.setOpacity(0);
        cartTimeLeft1.setOpacity(0);
        cartTimeLeft2.setOpacity(0);
        cartTimeLeft3.setOpacity(0);
        cartTimeLeft4.setOpacity(0);
        cartTimeLeft5.setOpacity(0);
        cartTimeLeft6.setOpacity(0);
        cartTimeLeft7.setOpacity(0);
        cartTimeLeft8.setOpacity(0);
        cartTimeLeft9.setOpacity(0);
        cartTimeLeft10.setOpacity(0);
        cartCapacityLabel1.setOpacity(0);
        cartCapacityLabel2.setOpacity(0);
        cartCapacityLabel3.setOpacity(0);
        cartCapacityLabel4.setOpacity(0);
        cartCapacityLabel5.setOpacity(0);
        cartCapacityLabel6.setOpacity(0);
        cartCapacityLabel7.setOpacity(0);
        cartCapacityLabel8.setOpacity(0);
        cartCapacityLabel9.setOpacity(0);
        cartCapacityLabel10.setOpacity(0);
        mainMessageOutLabel1.setOpacity(0);
        mainMessageOutLabel2.setOpacity(0);
        mainTowerReloadSpeed1.setOpacity(0);
        mainTowerReloadSpeed2.setOpacity(0);
        mainTowerReloadSpeed3.setOpacity(0);
        mainTowerReloadSpeed4.setOpacity(0);
        mainTowerReloadSpeed5.setOpacity(0);
        mainTowerLoadSize1.setOpacity(0);
        mainTowerLoadSize2.setOpacity(0);
        mainTowerLoadSize3.setOpacity(0);
        mainTowerLoadSize4.setOpacity(0);
        mainTowerLoadSize5.setOpacity(0);
        mainCartsLabel.setOpacity(0);
        mainTowersLabel.setOpacity(0);
        mainCartButton1.setOpacity(0);
        mainCartButton2.setOpacity(0);
        mainCartButton3.setOpacity(0);
        mainCartButton4.setOpacity(0);
        mainCartButton5.setOpacity(0);
        mainCartButton6.setOpacity(0);
        mainCartButton7.setOpacity(0);
        mainCartButton8.setOpacity(0);
        mainCartButton9.setOpacity(0);
        mainCartButton10.setOpacity(0);
        startButton.setOpacity(0);
        mainTowerLevel1.setOpacity(0);
        mainTowerLevel2.setOpacity(0);
        mainTowerLevel3.setOpacity(0);
        mainTowerLevel4.setOpacity(0);
        mainTowerLevel5.setOpacity(0);
        mainShopButton.setDisable(true);
        mainInventoryButton.setDisable(true);
        mainTowerButton1.setDisable(true);
        mainTowerButton2.setDisable(true);
        mainTowerButton3.setDisable(true);
        mainTowerButton4.setDisable(true);
        mainTowerButton5.setDisable(true);
        mainCartButton1.setDisable(true);
        mainCartButton2.setDisable(true);
        mainCartButton3.setDisable(true);
        mainCartButton4.setDisable(true);
        mainCartButton5.setDisable(true);
        mainCartButton6.setDisable(true);
        mainCartButton7.setDisable(true);
        mainCartButton8.setDisable(true);
        mainCartButton9.setDisable(true);
        mainCartButton10.setDisable(true);
        startButton.setDisable(true);
        finalScreenLabel.setDisable(false);
        finalScreenLabel.setOpacity(100);
        playerNameLabel.setOpacity(100);
        pointsLabel.setDisable(false);
        pointsLabel.setOpacity(100);
        pointsLabel.setText("You got " + gameEnvironment.getPoints() + " points");
        playerNameLabel.setText(gameEnvironment.getPlayerName() + "!");

        if (b) {

            wonLostLabel.setDisable(false);
            wonLostLabel.setOpacity(100);
            wonLostLabel.setText("Congrats! You beat all " + gameEnvironment.getNumOfRounds() + " rounds!");

        } else {

            wonLostLabel.setDisable(false);
            wonLostLabel.setOpacity(100);
            wonLostLabel.setText("You lost the game on round " + (gameEnvironment.getCurrentRound() + 1) + "/" + gameEnvironment.getNumOfRounds() + "!");

        }

    }

    /**
     * This method works out the slowest cart in a round's time ie the length of a round.
     * @return the time of a round.
     */
    public int getMaxTime(){

        int maxTime = 0;
        int roundNum = gameEnvironment.getCurrentRound();
        RoundService round = gameEnvironment.getRounds().get(roundNum);

        for(int i = 0; i < round.getCarts().size(); i++){

            if (round.getCarts().get(i).getTimeLeft()>maxTime) {

                maxTime = round.getCarts().get(i).getTimeLeft();

            }

        }

        return maxTime;

    }

    /**
     * This method is used to start a round, calls oneSecondUpdate() and works out on each tick if the player won, lost or neither.
     */
    public void startRound(){

        gameEnvironment.resetCartLabelSaves();
        ArrayList<Label> cartTimes = new ArrayList<>(Arrays.asList(cartTimeLeft1, cartTimeLeft2,cartTimeLeft3, cartTimeLeft4, cartTimeLeft5,cartTimeLeft6, cartTimeLeft7,cartTimeLeft8,cartTimeLeft9, cartTimeLeft10));
        ArrayList<Label> cartCapacities = new ArrayList<>(Arrays.asList(cartCapacityLabel1, cartCapacityLabel2,cartCapacityLabel3, cartCapacityLabel4, cartCapacityLabel5,cartCapacityLabel6, cartCapacityLabel7,cartCapacityLabel8,cartCapacityLabel9, cartCapacityLabel10));
        initialiseRound();
        int maxTime = getMaxTime();
        int seconds = 0;

        if (gameEnvironment.getTowerInventory().isEmpty()) {

            if (gameEnvironment.getCoins() < 10 && (gameEnvironment.getLoadSizeUpgrades().size() + gameEnvironment.getSpeedUpgrades().size() < 5 || gameEnvironment.getLifeUpgrades().isEmpty())) {

                clearAndDisableEverything(false);

            }

        }

        try {

            while(seconds < maxTime) {

                oneSecondUpdate();
                seconds++;

            }

        } catch (RoundLostException e) {

            if (!gameEnvironment.getLifeUpgrades().isEmpty()) {

                playerMessageLabel.setText("You beat round " + (gameEnvironment.getCurrentRound()+1) + " using one of your extra lives.");
                gameEnvironment.getLifeUpgrades().remove(0);

            } else {

                clearAndDisableEverything(false);

            }

        } catch (RoundWonException e) {

            playerMessageLabel.setText("You beat round " + (gameEnvironment.getCurrentRound()+1) + "!");

        }

        for(int i = 0; i < gameEnvironment.getRounds().get(gameEnvironment.getCurrentRound()).getCarts().size(); i++){

            gameEnvironment.getCartTimeLabelSaves().add(cartTimes.get(i).getText());
            gameEnvironment.getCartFillLabelSaves().add(cartCapacities.get(i).getText());


        }

        gameEnvironment.setCurrentRound(gameEnvironment.getCurrentRound()+1);
        gameEnvironment.resetCountOfEachTypeOfCount();

        if (gameEnvironment.getHardMode()) {

            if (gameEnvironment.getSelectionShorterDistance() || gameEnvironment.getSelectionHigherPrice()) {

                gameEnvironment.setPoint(gameEnvironment.getPoints() + (20 * (gameEnvironment.getCurrentRound() + 1)));
                gameEnvironment.setCoins(gameEnvironment.getCoins() + (10 * (gameEnvironment.getCurrentRound() + 1)));

            } else {

                gameEnvironment.setPoint(gameEnvironment.getPoints() + (15 * (gameEnvironment.getCurrentRound() + 1)));
                gameEnvironment.setCoins(gameEnvironment.getCoins() + (7 * (gameEnvironment.getCurrentRound() + 1)));

            }

        } else {

            if (gameEnvironment.getSelectionShorterDistance() || gameEnvironment.getSelectionHigherPrice()) {

                gameEnvironment.setPoint(gameEnvironment.getPoints() + (15 * (gameEnvironment.getCurrentRound() + 1)));
                gameEnvironment.setCoins(gameEnvironment.getCoins() + (15 * (gameEnvironment.getCurrentRound() + 1)));

            } else {

                gameEnvironment.setPoint(gameEnvironment.getPoints() + (10 * (gameEnvironment.getCurrentRound() + 1)));
                gameEnvironment.setCoins(gameEnvironment.getCoins() + (10 * (gameEnvironment.getCurrentRound() + 1)));

            }

        }

        int towerBrokeIndex = gameEnvironment.towerBreakCalculator();

        if (towerBrokeIndex != -1) {

            mainMessageOutLabel1.setText("Tower at slot " + (towerBrokeIndex + 1) + " broke, sorry!");

        }

        gameEnvironment.levelUpTowers();

        if (!gameEnvironment.getTowerInventory().isEmpty()) {

            int towerBonusUpgradeIndex = gameEnvironment.randomTowerLevelUpCalculator();

            if (towerBonusUpgradeIndex != -1) {

                mainMessageOutLabel2.setText("Tower at slot " + (towerBonusUpgradeIndex + 1) + " leveled up again!");

            }

        }

        updateValues();

    }

    /**
     * This method calculates and updated cart values, based on what would happen in one second of the game.
     * @throws RoundLostException this is thrown if the player loses the round on a game tick.
     * @throws RoundWonException this is thrown if a player fill every cart in a round.
     */
    public void oneSecondUpdate() throws RoundLostException, RoundWonException{

        boolean roundBeat = true;
        int roundNum = gameEnvironment.getCurrentRound();
        RoundService round = gameEnvironment.getRounds().get(roundNum);
        ArrayList<Label> cartTimes = new ArrayList<>(Arrays.asList(cartTimeLeft1, cartTimeLeft2,cartTimeLeft3, cartTimeLeft4, cartTimeLeft5,cartTimeLeft6, cartTimeLeft7,cartTimeLeft8,cartTimeLeft9, cartTimeLeft10));
        ArrayList<Label> cartCapacities = new ArrayList<>(Arrays.asList(cartCapacityLabel1, cartCapacityLabel2,cartCapacityLabel3, cartCapacityLabel4, cartCapacityLabel5,cartCapacityLabel6, cartCapacityLabel7,cartCapacityLabel8,cartCapacityLabel9, cartCapacityLabel10));

        for(int i = 0; i < round.getCarts().size(); i++){

            CartService cart = round.getCarts().get(i);

            if (cart.getFilled()) {

                cartTimes.get(i).setText("Filled!");
                continue;

            } else {

                roundBeat = false;

            }

            cart.decreaseTimeLeft();

             if (cart.getTimeRanOut()) {

                cartTimes.get(i).setText("Time Ran Out!");
                throw new RoundLostException();

            }

            cartTimes.get(i).setText(("Time Left: "+cart.getTimeLeft()));
            int amountToFill = 0;

            if (cart.getTypes().contains("Blood") && gameEnvironment.getCountOfEachTypeCart().get(0) != 0) {

                amountToFill += gameEnvironment.getPooledResourcesPerSecond().get(0)/gameEnvironment.getCountOfEachTypeCart().get(0);

            }

            if (cart.getTypes().contains("Sweat") && gameEnvironment.getCountOfEachTypeCart().get(1) != 0) {

                amountToFill += gameEnvironment.getPooledResourcesPerSecond().get(1)/gameEnvironment.getCountOfEachTypeCart().get(1);

            }

            if (cart.getTypes().contains("Tears") && gameEnvironment.getCountOfEachTypeCart().get(2) != 0) {

                amountToFill += gameEnvironment.getPooledResourcesPerSecond().get(2)/gameEnvironment.getCountOfEachTypeCart().get(2);

            }

            if (gameEnvironment.getSelectionHigherPrice()) {

                cart.setCurrentFill((int)(cart.getCurrentFill() + amountToFill*0.75));

            } else {

                cart.setCurrentFill(cart.getCurrentFill() + amountToFill);

            }

            cartCapacities.get(i).setText(cart.getCurrentFill() + "/" + cart.getSize());

        }

        if (roundBeat) {

            throw new RoundWonException();

        }

    }

    /**
     * This method updates all the values of labels and buttons on the main screen.
     */
    public void updateValues(){
        ArrayList<Button> towerButtons = new ArrayList<>(Arrays.asList(mainTowerButton1, mainTowerButton2, mainTowerButton3, mainTowerButton4, mainTowerButton5));
        ArrayList<Label> loadSpeedLabels = new ArrayList<>(Arrays.asList(mainTowerReloadSpeed1, mainTowerReloadSpeed2, mainTowerReloadSpeed3, mainTowerReloadSpeed4, mainTowerReloadSpeed5));
        ArrayList<Label> loadSizeLabels = new ArrayList<>(Arrays.asList(mainTowerLoadSize1, mainTowerLoadSize2, mainTowerLoadSize3, mainTowerLoadSize4, mainTowerLoadSize5));
        ArrayList<Label> levelLabels = new ArrayList<>(Arrays.asList(mainTowerLevel1, mainTowerLevel2, mainTowerLevel3, mainTowerLevel4, mainTowerLevel5));

        for(int i = 0; i < 5; i++){

            towerButtons.get(i).setDisable(true);
            towerButtons.get(i).setText("");
            loadSpeedLabels.get(i).setText("Reload Speed:");
            loadSizeLabels.get(i).setText("Load Size:");
            levelLabels.get(i).setText("Level:");

        }

        int cap = Math.min(5, gameEnvironment.getTowerInventory().size());

        for(int i = 0; i < cap; i++){

            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            towerButtons.get(i).setDisable(false);
            towerButtons.get(i).setText(((TowerService)gameEnvironment.getTowerInventory().get(i)).getTowerName());
            loadSpeedLabels.get(i).setText("Reload Speed: " + decimalFormat.format(((TowerService)gameEnvironment.getTowerInventory().get(i)).getLoadSpeed()));
            loadSizeLabels.get(i).setText("Load Size: " + decimalFormat.format(((TowerService)gameEnvironment.getTowerInventory().get(i)).getLoadSize()));
            levelLabels.get(i).setText("Level: " + ((TowerService)gameEnvironment.getTowerInventory().get(i)).getLevel());

        }

        mainPointsLabel.setText("Points: "+gameEnvironment.getPoints());
        mainCoinsLabel.setText("Coins: "+gameEnvironment.getCoins());

    }

}



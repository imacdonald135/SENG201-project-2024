package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import seng201.team0.GameEnvironment;
import seng201.team0.services.CartService;
import seng201.team0.services.RoundService;

import java.util.ArrayList;

/**
 * Controller for the selection.fxml window.
 */


public class SelectionController {
    private final GameEnvironment gameEnvironment;
    @FXML
    Button distanceButton;
    @FXML
    Button priceButton;
    @FXML
    Button noChangesButton;
    @FXML
    Button backButton;
    @FXML
    Button startButton;
    @FXML
    Label cartInfoTo4;
    @FXML
    Label cartInfoTo10;

    /**
     * Constructor for SelectionController.
     * @param g GameEnvironment
     */
    public SelectionController(GameEnvironment g){

        gameEnvironment = g;

    }

    /**
     * Initializer for SelectionController, displays the carts for the next round.
     */
    public void initialize() {

        startButton.setDisable(true);

        int currentRoundNum = gameEnvironment.getCurrentRound();
        RoundService currentRound = gameEnvironment.getRounds().get(currentRoundNum);
        String outputTo4 = "";
        String outputTo10 = "";
        int cap = Math.min(4, currentRound.getCarts().size());

        for (int i = 0; i < cap; i ++) {

            CartService cart = currentRound.getCarts().get(i);
            ArrayList<String> types = cart.getTypes();
            String typeString = "";

            for (int j = 0; j < types.size(); j ++) {

                typeString += types.get(j);

                if (j != types.size() - 1) {

                    typeString += ", ";

                } else {

                    typeString += " ";

                }

            }

            String temp = "Cart " + (i + 1) + ":\n   Size: " + cart.getSize() + "\n   Types: " + typeString + "\n   Speed: " + cart.getSpeed();
            outputTo4 += temp + "\n";

        }

        if (currentRound.getCarts().size() > 4) {

            for (int i = 4; i < currentRound.getCarts().size(); i++) {

                CartService cart = currentRound.getCarts().get(i);
                ArrayList<String> types = cart.getTypes();
                String typeString = "";

                for (int j = 0; j < types.size(); j++) {

                    typeString += types.get(j);

                    if (j != types.size() - 1) {

                        typeString += ", ";

                    } else {

                        typeString += " ";

                    }
                }

                String temp = "Cart " + (i + 1) + ":\n   Size: " + cart.getSize() + "\n   Types: " + typeString + "\n   Speed: " + cart.getSpeed();
                outputTo10 += temp + "\n";

            }

        }

        cartInfoTo4.setText(outputTo4);
        cartInfoTo10.setText(outputTo10);

    }

    /**
     * On Action method for the back button, takes user to main screen.
     */
    public void backPressed(){

        gameEnvironment.setMainScreenFromSelection(false);
        gameEnvironment.closeSelectionScreen();
        gameEnvironment.launchMainScreen();

    }

    /**
     * On Action method for start button, takes user to main screen and starts the round.
     */
    public void startPressed(){

        gameEnvironment.setMainScreenFromSelection(true);
        gameEnvironment.closeSelectionScreen();
        gameEnvironment.launchMainScreen();

    }

    /**
     * On Action method for priceButton, updates booleans in GameEnvironment
     */
    public void higherPricePressed(){

        startButton.setDisable(false);
        gameEnvironment.setSelectionHigherPrice(true);
        gameEnvironment.setSelectionShorterDistance(false);
        priceButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        distanceButton.setStyle("");
        noChangesButton.setStyle("");

    }

    /**
     * On Action method for distanceButton, updates booleans in GameEnvironment.
     */
    public void shorterDistancePressed(){

        startButton.setDisable(false);
        gameEnvironment.setSelectionHigherPrice(false);
        gameEnvironment.setSelectionShorterDistance(true);
        distanceButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        priceButton.setStyle("");
        noChangesButton.setStyle("");

    }

    /**
     * On Action method for noChangesButton, updates booleans in GameEnvironment.
     */
    public void noChangesPressed(){

        startButton.setDisable(false);
        gameEnvironment.setSelectionHigherPrice(false);
        gameEnvironment.setSelectionShorterDistance(false);
        noChangesButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        distanceButton.setStyle("");
        priceButton.setStyle("");

    }

}
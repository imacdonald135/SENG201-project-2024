package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seng201.team0.GameEnvironment;

/**
 * Controller for the setup.fxml window.
 */

public class SetupController {

    private final GameEnvironment gameEnvironment;

    @FXML
    Label welcomeLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label difficultyLabel;
    @FXML
    Label roundsLabel;
    @FXML
    Label hardModeDescription;
    @FXML
    Button difficultyButtonNormal;
    @FXML
    Button difficultyButtonHard;
    @FXML
    Button selectTowers;
    @FXML
    Slider roundsSlider;
    @FXML
    Button enterButton;
    @FXML
    Label helloLabel;
    @FXML
    TextField nameText;
    @FXML
    Button startButton;
    @FXML
    Label nameErrorLabel;


    /**
     * The constructor for the SetupController, takes a game environment as a parameter.
     * @param g GameEnvironment
     */
    public SetupController(GameEnvironment g){

        gameEnvironment = g;

    }

    /**
     * The initializer for SetupController, initializes depending on previous user inputs.
     */
    public void initialize(){

        hardModeDescription.setOpacity(0);

        if (gameEnvironment.getPlayerName() != null) {

            helloLabel.setText("Rise and Shine " + gameEnvironment.getPlayerName() + "!");

        }

        nameErrorLabel.setOpacity(0);

        if (gameEnvironment.getSetupDifficultySelected()) {

            if (gameEnvironment.getHardMode()) {

                hardModePushed();

            } else {

                normalModePushed();

            }

        }

        roundsSlider.setValue(gameEnvironment.getNumOfRounds());
        nameText.setText(gameEnvironment.getPlayerName());

        updateStart();

    }

    /**
     * On Action method for the hard mode button, displays information when pushed.
     */
    public void hardModePushed(){

        gameEnvironment.setHardMode(true);
        gameEnvironment.setSetupDifficultySelected(true);

        updateStart();

        hardModeDescription.setOpacity(100);
        difficultyButtonHard.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        difficultyButtonNormal.setStyle("");

    }

    /**
     * Drag method for the hard mode button, displays the hard mode information.
     */
    public void hardModeDragged(){

        hardModeDescription.setOpacity(100);

    }

    /**
     * Drag exit method for the hard mode button, removes the hard mode information.
     */
    public void hardModeDragExit(){

        if (!gameEnvironment.getHardMode()) {

            hardModeDescription.setOpacity(0);

        }

    }

    /**
     * On Action method for the normal mode button, removes hard mode description.
     */
    public void normalModePushed(){

        gameEnvironment.setHardMode(false);
        gameEnvironment.setSetupDifficultySelected(true);

        updateStart();

        hardModeDescription.setOpacity(0);
        difficultyButtonNormal.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        difficultyButtonHard.setStyle("");
    }

    /**
     * On Action method for the enter button when the player finishes writing their name, shows errors if name is not suitable.
     */
    public void enterPushed(){

        if (nameText.getText() != null) {

            if (nameText.getText().trim().length() >= 3 && nameText.getText().trim().length() <= 15) {

                Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
                Matcher matcher = pattern.matcher(nameText.getText().trim());

                if (matcher.find()) {

                    helloLabel.setText("Rise and Shine Private!");
                    nameErrorLabel.setText("Name must not contain special characters");
                    nameErrorLabel.setOpacity(100);
                    gameEnvironment.setPlayerName(null);
                    gameEnvironment.setSetupNameSelected(false);

                } else {

                    gameEnvironment.setPlayerName(nameText.getText().trim());
                    helloLabel.setText("Rise and Shine " + gameEnvironment.getPlayerName() + "!");
                    gameEnvironment.setSetupNameSelected(true);
                    nameErrorLabel.setOpacity(0);

                }

            } else {

                helloLabel.setText("Rise and Shine Private!");
                nameErrorLabel.setText("Name must contain between 3-15 characters");
                nameErrorLabel.setOpacity(100);
                gameEnvironment.setPlayerName(null);
                gameEnvironment.setSetupNameSelected(false);

            }

        } else {

            nameErrorLabel.setText("Name must contain between 3-15 characters");

        }

        updateStart();

    }

    /**
     * On Action method for the start button, launches the main screen and sets variables in GameEnvironment.
     */
    public void startPushed(){

        gameEnvironment.setNumOfRounds((int)roundsSlider.getValue());
        gameEnvironment.setMainScreenFromSelection(false);
        gameEnvironment.setPlayerName(nameText.getText());
        gameEnvironment.setStartingShop(false);

        gameEnvironment.closeSetupScreen();
        gameEnvironment.launchMainScreen();

    }

    /**
     * On Action method for the select starting towers button, takes the user to shop screen and sets variables in GameEnvironment.
     */
    public void startingTowersButtonPushed(){

        gameEnvironment.setNumOfRounds((int) roundsSlider.getValue());
        gameEnvironment.setStartingShop(true);

        gameEnvironment.closeSetupScreen();
        gameEnvironment.launchShopScreen();

    }

    /**
     * Helper function for updating the start button depending on whether the user can start their game or not.
     */
    public void updateStart(){

        startButton.setDisable(!gameEnvironment.getSetupNameSelected() || !gameEnvironment.getSetupDifficultySelected() || !(gameEnvironment.getTowerInventory().size() >= 3));

    }

}
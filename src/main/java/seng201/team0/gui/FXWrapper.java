package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.GameEnvironment;

import java.io.IOException;

/**
 * The FXWrapper class, handles the launching of windows.
 */
public class FXWrapper {

    @FXML
    private Pane pane;

    private Stage stage;

    /**
     * Initialises the stage and the game environment.
     * @param stage the stage that the app is launched from.
     */
    public void init(Stage stage) {
        this.stage = stage;
        new GameEnvironment(this::launchSetupScreen, this::launchMainScreen, this:: launchShopScreen, this:: launchInventoryScreen, this:: launchSelectionScreen, this::clearPane);
    }

    /**
     * The launcher for the setup screen.
     * @param gameEnvironment the game environment that is launched with it.
     */
    public void launchSetupScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new SetupController(gameEnvironment));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Blood Sweat Tears setup");
            stage.setHeight(425);
            stage.setWidth(600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the pane to launch a new screen.
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    /**
     * The launcher for the main screen.
     * @param gameEnvironment the game environment that is launched with it.
     */
    public void launchMainScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            mainScreenLoader.setControllerFactory(param -> new MainController(gameEnvironment));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Blood Sweat Tears Main Screen");
            stage.setHeight(600);
            stage.setWidth(800);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The launcher for the shop screen.
     * @param gameEnvironment the game environment that is launched with it.
     */
    public void launchShopScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader shopScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
            shopScreenLoader.setControllerFactory(param -> new ShopController(gameEnvironment));
            Parent setupParent  = shopScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Blood Sweat Tears Shop Screen");
            stage.setHeight(425);
            stage.setWidth(600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The launcher for the inventory screen.
     * @param gameEnvironment the game environment that is launched with it.
     */
    public void launchInventoryScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader inventoryScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory.fxml"));
            inventoryScreenLoader.setControllerFactory(param -> new InventoryController(gameEnvironment));
            Parent setupParent  = inventoryScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Blood Sweat Tears Inventory Screen");
            stage.setHeight(425);
            stage.setWidth(600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The launcher for the selection screen.
     * @param gameEnvironment the game environment that is launched with it.
     */
    public void launchSelectionScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader selectionScreenLoader = new FXMLLoader(getClass().getResource("/fxml/selection.fxml"));
            selectionScreenLoader.setControllerFactory(param -> new SelectionController(gameEnvironment));
            Parent setupParent  = selectionScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Blood Sweat Tears Selection Screen");
            stage.setHeight(425);
            stage.setWidth(600);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package View;

import Controller.Controller;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageRequestsController implements Initializable {

    public javafx.scene.control.Button ApplyPurchaseButton;
    public javafx.scene.control.Button RefusePurchaseButton;
    public javafx.scene.control.Button ApplyExchangeButton;
    public javafx.scene.control.Button RefuseExchangeButton;
    public javafx.scene.control.Button BackButton;
    protected static Controller controller;

    /**
     * Sets the static controller for all of the user windows controllers
     */
    public void setController(Controller _controller) {
        controller = _controller;
    }

    /**
     * Opens mainWindow when the user press back button
     */
    public void backHome() {
        // back to home stage from the current window
        // close this window and change a stage/scene

        // get a handle to the stage
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method calls an apply of the user on a request
     */
    public void applyPurchaseRequest() {

    }


    /**
     * Method calls a refuse of the user on a request
     */
    public void refusePurchaseRequest() {

    }


    /**
     * Method calls an apply of the user on a request
     */
    public void applyExchangeRequest() {

    }


    /**
     * Method calls a refuse of the user on a request
     */
    public void refuseExchangeRequest() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

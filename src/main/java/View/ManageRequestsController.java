package View;

import Controller.Controller;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageRequestsController implements Initializable {

    public javafx.scene.control.Button ApplyButton;
    public javafx.scene.control.Button RefuseButton;
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
     * Method calls an applyC of the user on a request
     */
    public void applyRequest() {

    }


    /**
     * Method calls a refuse of the user on a request
     */
    public void refuseRequest() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

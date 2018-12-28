package View;

import Controller.Controller;
import Model.User;
import javafx.stage.Stage;

public class RequestView {

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

}

package View;

import javafx.stage.Stage;

public class RequestView {

    public javafx.scene.control.Button BackButton;

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

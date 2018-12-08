package View;
/**
 * ViewController is the father class for each window controller of the CRUD GUI
 */

import Controller.Controller;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ViewController {
    public javafx.scene.control.TextField userName;
    public javafx.scene.control.TextField userPassword;
    public javafx.scene.control.TextField userFirstName;
    public javafx.scene.control.TextField userLastName;
    public javafx.scene.control.TextField userCity;
    public javafx.scene.control.DatePicker userBirthDate;
    public javafx.scene.control.Button show;
    public javafx.scene.control.Button clear;
    public javafx.scene.control.Button BackButton;
    protected static Controller controller;
    protected static User currentUser;

    /**
     * Sets the static controller for all of the user windows controllers
     */
    public void setController(Controller _controller) {
        controller = _controller;
    }

    /**
     * Shows relevant info for the given user (currentUser) for relevant windows(Read User, Update User)
     */
    public void show() {
        if (invalidUserName()) {
            userName.setDisable(true);
            userPassword.setText(currentUser.getPassword());
            userPassword.setDisable(false);
            userFirstName.setText(currentUser.getFirst_name());
            userFirstName.setDisable(false);
            userLastName.setText(currentUser.getLast_name());
            userLastName.setDisable(false);
            userCity.setText(currentUser.getCity());
            userCity.setDisable(false);
            userBirthDate.setValue(LocalDate.parse(currentUser.getDate()));
            userBirthDate.setDisable(false);
        }
    }

    /**
     * Validation checks for the typed user name
     */
    public boolean invalidUserName() {
        String name = userName.getText();
        currentUser = searchUserData(name);
        if (name.isEmpty() || currentUser == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Invalid User Name, please try again.");
            a.show();
            return false;
        }
        return true;
    }

    /**
     * Search the user name string and if exist in the database display the user data
     */
    public User searchUserData(String name) {
        return controller.searchUserData(name);
    }

    /**
     * Clears all fields from user data, to start new search
     */
    public void clearUserData() {
        userName.setDisable(false);
        userName.clear();
        userPassword.clear();
        userFirstName.clear();
        userLastName.clear();
        userCity.clear();
        userBirthDate.setValue(LocalDate.of(2000, 01, 01));
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
     * Checks whether the user name and the passwords are exist in the DB
     */
    public boolean IsCorrectPassword(String userName, String password) {
        return controller.IsCorrectPassword(userName, password);
    }

}

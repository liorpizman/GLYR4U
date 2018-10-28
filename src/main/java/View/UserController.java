package View;

import Controller.Controller;
import Model.User;
import javafx.scene.control.Alert;

import java.time.LocalDate;

public class UserController {
    public javafx.scene.control.TextField userName;
    public javafx.scene.control.TextField userPassword;
    public javafx.scene.control.TextField userFirstName;
    public javafx.scene.control.TextField userLastName;
    public javafx.scene.control.TextField userCity;
    public javafx.scene.control.DatePicker userBirthDate;
    public javafx.scene.control.Button show;
    public javafx.scene.control.Button clear;
    protected static Controller controller;
    public static User currentUser ;

    public void setController(Controller _controller){
        controller = _controller;
    }

    public void show() {
        if (invalidUserName())
        {
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

    // Validation checks for the typed user name
    public boolean invalidUserName() {
        String name = userName.getText();
        currentUser = searchUserData(name);
        if (name.isEmpty() || currentUser == null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Invalid User Name, please try again.");
            a.show();
            return false;
        }
        return true;
    }

    // Search the user name string and if exist in the database display the user data
    public User searchUserData(String name)
    {
        // User userDetails = controller.searchUserData(name);
        return controller.searchUserData(name) ;
    }

    // clear all fields from user data, to start new search
    public void clearUserData() {
        userName.setDisable(false);
        userName.clear();
        userPassword.clear();
        userFirstName.clear();
        userLastName.clear();
        userCity.clear();
        userBirthDate.setValue(LocalDate.of(2000, 01, 01));
    }
}

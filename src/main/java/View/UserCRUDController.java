package View;

import Controller.Controller;
import Model.RegisteredUser;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.time.LocalDate;

/**
 * This class represents User CRUD Controller
 */
public class UserCRUDController {

    /**
     * fields of UserCRUDController
     */
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
    protected static RegisteredUser currentUser;

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
        currentUser = searchUserData(controller.getCurrentUser().getUser_name());
        userName.setText(currentUser.getUser_name());
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

    /**
     * Validation checks for the typed user name
     */
    public boolean validUserName() {
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
    public RegisteredUser searchUserData(String name) {
        return controller.searchUserData(name);
    }


    /**
     * Opens mainWindow when the user press back button
     */
    public void backHome() {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Checks whether the user name and the passwords are exist in the DB
     */
    public boolean IsCorrectPassword(String userName, String password) {
        return controller.IsCorrectPassword(userName, password);
    }

    /**
     * Clears all fields from user data, to start new search
     */
    public void clearUserData() {
        userName.setDisable(true);
        userPassword.clear();
        userFirstName.clear();
        userLastName.clear();
        userCity.clear();
        userBirthDate.setValue(LocalDate.of(2000, 01, 01));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Create User Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * fields of UserCRUDController
     */
    public javafx.scene.control.TextField userNameC;
    public javafx.scene.control.TextField userPasswordC;
    public javafx.scene.control.TextField userFirstNameC;
    public javafx.scene.control.TextField userLastNameC;
    public javafx.scene.control.TextField userCityC;
    public javafx.scene.control.DatePicker userBirthDateC;
    public javafx.scene.control.Button applyC;

    /**
     * When users applies for registration, gets all the data and sends it to the mvc controller
     */
    public void apply() {
        String _userName = userNameC.getText();
        String _password = userPasswordC.getText();
        String _firstName = userFirstNameC.getText();
        String _lastName = userLastNameC.getText();
        String _userCity = userCityC.getText();
        LocalDate date = userBirthDateC.getValue();
        LocalDate currentDate = LocalDate.now();
        if (_userName.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your user name, please enter.");
            a.show();
            return;
        }else if (_userName.contains(" ")) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("The user name can not contain spaces");
            a.show();
            return;
        } else if (_password.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your password, please enter.");
            a.show();
            return;
        }else if(_password.length()!=6 || !_password.matches("^[a-zA-Z0-9]*$")){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("The password must be 6 letters or digits (without any special characters)");
            a.show();
            return;
        } else if (_firstName.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your first name, please enter.");
            a.show();
            return;
        } else if (_lastName.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your last name, please enter.");
            a.show();
            return;
        } else if (_userCity.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your city, please enter.");
            a.show();
            return;
        } else if (date == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid birth date or you didn't fill your birth date at all, please try again.");
            a.show();
            return;
        }else if(date.compareTo(currentDate) > 0) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("This date is a future date. Please Try Again!");
            a.show();
            return;
        } else if (date.getYear() > 2001) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Minimum age allowed for a user is 18.");
            a.show();
            return;
        } else {
            if (searchUserData(_userName) != null) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("This user already exist in the system.");
                a.show();
                return;
            } else {
                controller.insertUser(_userName, _password, _firstName, _lastName, _userCity, date);
                if (searchUserData(_userName) != null) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("The user created successfully.");
                    a.show();
                } else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Error occurred! Please try again.");
                    a.show();
                    return;
                }
            }
            backHome();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Delete User Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * fields of UserCRUDController
     */
    public javafx.scene.control.TextField userPasswordD;
    public javafx.scene.control.Button deleteD;

    /**
     * This method controls the deleteD of the user and shows a suitable alert message
     * for each state
     */
    public void deleteUser() {
        if (controller.deleteUser(controller.getCurrentUser().getUser_name(), userPasswordD.getText())) {
            controller.deleteVacationOfDeletedUser(controller.getCurrentUser());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("The user is deleted.");
            a.show();
            controller.setCurrentUserInSystem(null);
            backHome();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("The password is wrong! Please try again.");
            a.show();
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Update User Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * field of UserCRUDController
     */
    public javafx.scene.control.Button updateU;

    /**
     * Implementing show function in order to manipulate the updateU button
     */
    public void updateShow() {
        this.show();
        userName.setDisable(true);
        updateU.setDisable(false);
    }

    /**
     * Gets the data that the user input's in order to updateU user,
     * alerts if data wasn't insert correctly
     */
    public void updateUserData() {
        String password = userPassword.getText();
        String firstName = userFirstName.getText();
        String lastName = userLastName.getText();
        String city = userCity.getText();
        String birthDate = userBirthDate.getValue().toString();
        if (password.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your password, please entered.");
            a.show();
        } else if (firstName.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your first name, please entered.");
            a.show();
        } else if (lastName.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your last name, please entered.");
            a.show();
        } else if (city.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your city, please entered.");
            a.show();
        } else if (birthDate == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your birth date, please entered.");
            a.show();
        } else {
            RegisteredUser updatedUser = new RegisteredUser(currentUser.getUser_name(), password, firstName, lastName, city, birthDate);
            if (controller.updateUser(updatedUser)) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The user updated successfully.");
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Error occurred! Please try again.");
                a.show();
            }
            clearUserData();
            updateU.setDisable(true);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Read User Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Implementing show function in order to manipulate the updateU button
     */
    public void readShow() {
        this.show();
        userName.setDisable(true);
        userPassword.setDisable(true);
        userFirstName.setDisable(true);
        userLastName.setDisable(true);
        userCity.setDisable(true);
        userBirthDate.setDisable(true);
    }
}

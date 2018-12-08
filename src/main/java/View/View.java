package View;
/**
 * View class is managing the windows of the crud gui
 */

import Controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {
    public javafx.scene.control.Button createUser;
    public javafx.scene.control.Button readUser;
    public javafx.scene.control.Button updateUser;
    public javafx.scene.control.Button deleteUser;
    private ViewController viewController;
    public SearchVacationController searchViewController;
    public VacationController vacationController;
    public static Controller controller;
    public javafx.scene.control.TextField userName;
    public javafx.scene.control.TextField userPassword;
    public javafx.scene.control.Button BackButton;

    /**
     * Setting the view's controller. implement mvc paradigm
     *
     * @param _controller mvc's controller
     */
    public void setController(Controller _controller) {
        this.controller = _controller;
        this.viewController = new ViewController();
        this.viewController.setController(controller);
        this.searchViewController = new SearchVacationController();
        searchViewController.setController(controller);
        this.vacationController = new VacationController();
        vacationController.setController(controller);
    }


    /**
     * Opens create user window when "create user" button is pushed
     */
    public void createUser() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Create New User");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CreateUser.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * Opens read user window when "read user" button is pushed
     */
    public void readUser() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Read User");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ReadUser.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * Opens update user window when "update user" button is pushed
     */
    public void updateUser() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Update User");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UpdateUser.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * Opens delete user window when "delete user" button is pushed
     */
    public void deleteUser() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Delete User");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DeleteUser.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }


    /**
     * Opens search vacations window
     */
    public void searchVacation() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Search Vacation");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SearchVaction.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 1030, 652);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * check whether the user's details are correct
     */
    public void logIn() {
        controller.logIn(userName.getText(), userPassword.getText());
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
     * Opens vacation info window when the vacation info button was pressed
     */
    public void VacationInfoRun() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Vacation Info");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("VacationInfoWindow.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * Opens update vacation window when the update vacation button was pressed
     */
    public void UpdateVacationRun() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Update Vacation");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UpdateVacationWindow.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * Opens delete vacation window when the delete vacation button was pressed
     */
    public void DeleteVacationRun() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Delete Vacation");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DeleteVacationWindow.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }


}

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
    public UserController userController;
    public Controller controller;

    /**
     * Setting the view's controller. implement mvc paradigm
     *
     * @param _controller mvc's controller
     */
    public void setController(Controller _controller) {
        controller = _controller;
        userController = new UserController();
        userController.setController(controller);
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
            root.getStylesheets().add(getClass().getClassLoader().getResource("vacationCSS.css").toExternalForm());
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
            root.getStylesheets().add(getClass().getClassLoader().getResource("vacationCSS.css").toExternalForm());
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
            root.getStylesheets().add(getClass().getClassLoader().getResource("vacationCSS.css").toExternalForm());
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
            root.getStylesheets().add(getClass().getClassLoader().getResource("vacationCSS.css").toExternalForm());
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
            root.getStylesheets().add(getClass().getClassLoader().getResource("vacationCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }


    /**
     * Opens log in window for the user
     */
    public void logIn() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Log In");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LogInWindow.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("vacationCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }
}

package View;

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

    public void setController(Controller _controller) {
        controller = _controller;
        userController = new UserController();
        userController.setController(controller);
    }

    public void createUser()
    {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Create New User");
        try
        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CreateUser.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/CreateUser.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("backGroundCSS.css").toExternalForm());
            //root.getStylesheets().add(getClass().getResource("backGroundCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    public void readUser()
    {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Read User");
        try
        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ReadUser.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/ReadUser.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("backGroundCSS.css").toExternalForm());
            //root.getStylesheets().add(getClass().getResource("backGroundCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    public void updateUser()
    {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Update User");
        try
        {
            //Parent root = FXMLLoader.load(getClass().getResource("/UpdateUser.fxml"));
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UpdateUser.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("backGroundCSS.css").toExternalForm());
            //root.getStylesheets().add(getClass().getResource("backGroundCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    public void deleteUser()
    {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Delete User");
        try
        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("DeleteUser.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/DeleteUser.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("backGroundCSS.css").toExternalForm());
            //root.getStylesheets().add(getClass().getResource("backGroundCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

}

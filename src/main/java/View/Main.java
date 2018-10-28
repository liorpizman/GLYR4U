package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Model;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        model.createNewTable("Users");
        View view = new View();
        Controller controller = new Controller(model, view);
        view.setController(controller);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));
        root.getStylesheets().add(getClass().getClassLoader().getResource("vacationCSS.css").toExternalForm());
        primaryStage.setTitle("Welcome To Vacation4U!");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package View;

import Controller.Controller;
import Model.RegisteredUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;
/**
 * View class is managing the windows of the crud gui
 */

public class View {
    /**
     * fields of View
     */
    public javafx.scene.control.Button createUser;
    public javafx.scene.control.Button readUser;
    public javafx.scene.control.Button updateUser;
    public javafx.scene.control.Button deleteUser;
    public SearchVacationController searchViewController;
    public VacationCRUDController vacationCrudController;
    public static Controller controller;
    public javafx.scene.control.TextField userName;
    public javafx.scene.control.TextField userPassword;
    public javafx.scene.control.Button BackButton;
    public javafx.scene.control.Button ExitButton;
    private UserCRUDController userCrudController;
    private ExchangeRequestController exchangeRequestController;
    private PurchaseRequestController purchaseRequestController;
    private ManageRequestsController manageRequestsController;

    /**
     * Setting the view's controller. implement mvc paradigm
     * @param _controller mvc's controller
     */
    public void setController(Controller _controller) {
        controller = _controller;
        this.userCrudController = new UserCRUDController();
        this.userCrudController.setController(controller);
        this.searchViewController = new SearchVacationController();
        searchViewController.setController(controller);
        this.vacationCrudController = new VacationCRUDController();
        vacationCrudController.setController(controller);
        this.purchaseRequestController = new PurchaseRequestController();
        purchaseRequestController.setController(controller);
        this.exchangeRequestController = new ExchangeRequestController();
        exchangeRequestController.setController(controller);
        this.manageRequestsController = new ManageRequestsController();
        manageRequestsController.setController(controller);
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
     * Opens updateU user window when "updateU user" button is pushed
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
     * Opens deleteD user window when "deleteD user" button is pushed
     */
    public void deleteUser() {
        if (controller.getCurrentUser() != null) {
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
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You must be logged in to deleteD your user!");
            a.show();
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
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getClassLoader().getResource("SearchVacation.fxml").openStream());
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            searchViewController = (SearchVacationController) fxmlLoader.getController();
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
        if (controller.logIn(userName.getText(), userPassword.getText())) {
            RegisteredUser currUser = controller.searchUserData(userName.getText());
            controller.setCurrentUserInSystem(currUser);
            searchVacation();
        }
    }

    /**
     * Opens mainWindow when the user press back button
     */
    public void backHome() {
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
            Scene scene = new Scene(root, 600, 550);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * Opens update vacation window when the updateU vacation button was pressed
     */
    public void UpdateVacationRun() {
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Update Vacation");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UpdateVacationWindow.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 600);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * Opens delete vacation window when the deleteD vacation button was pressed
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

    /**
     * Exit the system
     */
    public void exitSystem() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) ExitButton.getScene().getWindow();
            stage.close();
        }
    }


}

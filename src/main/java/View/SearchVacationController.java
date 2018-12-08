package View;

import Controller.Controller;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchVacationController {

    public javafx.scene.control.Button LogoutButton;
    public javafx.scene.control.Button accountSettings;
    public javafx.scene.control.Button publishButton;

    public javafx.scene.control.Button BackButton;

    public javafx.scene.control.Button PurchaseButton1;
    public javafx.scene.control.Button PurchaseButton2;
    public javafx.scene.control.Button PurchaseButton3;
    public javafx.scene.control.TextField publishedBy1;
    public javafx.scene.control.TextField publishedBy2;
    public javafx.scene.control.TextField publishedBy3;

    protected static Controller controller;

    private String vacationID1;
    private String vacationID2;
    private String vacationID3;
    private Vacation vacationList;

    /**
     * Constructor, different view for connected user and guest
     */
    public void setController(Controller _controller) {
        controller = _controller;
    }


    public void setDisable() {
        if (!controller.isUserConnected()) {
            PurchaseButton1.setDisable(true);
            PurchaseButton1.setDisable(true);
            PurchaseButton1.setDisable(true);
            LogoutButton.setDisable(true);
            accountSettings.setDisable(true);
            publishButton.setDisable(true);
        }
    }

    /**
     * Handles logout event
     */
    public void logOut() {
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You can't log out if you aren't connected");
            a.show();
            return;
        }
        controller.setCurrentUserInSystem(null);
        PurchaseButton1.setDisable(true);
        PurchaseButton1.setDisable(true);
        PurchaseButton1.setDisable(true);
        LogoutButton.setDisable(true);
        accountSettings.setDisable(true);
        publishButton.setDisable(true);
    }


    public void back() {

    }

    public void next() {

    }

    /**
     * Opens publishing of a vacation window
     */
    public void publishVacation() {
        /*
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You can't Publish if you aren't connected");
            a.show();
            return;
        }
        */
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Publish Vacation");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("PublishVacation.fxml"));
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
     * Opens Account settings window
     */
    public void AccountSettings() {
        /*
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You are not logged in");
            a.show();
            return;
        }
        */
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Account Settings");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AccountSettings.fxml"));
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
     * Notifying the Purchase window which vacationID is sold and who is the Seller
     */
    public void Purchase1() {
        openPurchase(publishedBy1.getText(), vacationID1);
    }

    public void Purchase2() {
        openPurchase(publishedBy2.getText(), vacationID2);
    }

    public void Purchase3() {
        openPurchase(publishedBy3.getText(), vacationID3);
    }

    /**
     * Opens Purchase window
     */
    private void openPurchase(String sellerID, String vacationID) {
        /*
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You can't purchase vacations if you aren't connected");
            a.show();
            return;
        }
        */
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Purchase Window, VacationID:" + vacationID + ", SellerID:" + sellerID);
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("PurchaseWindow.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    public void backHome() {
        // back to home stage from the current window
        // close this window and change a stage/scene

        // get a handle to the stage
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

}


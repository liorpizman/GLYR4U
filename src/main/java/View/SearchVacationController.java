package View;

import Controller.Controller;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchVacationController {

    public javafx.scene.control.TextField fromCountry;
    public javafx.scene.control.TextField fromCity;
    public javafx.scene.control.TextField toCountry;
    public javafx.scene.control.TextField toCity;
    public javafx.scene.control.DatePicker arrivalDate;
    public javafx.scene.control.DatePicker departureDate;
    public javafx.scene.control.CheckBox roundTripCheck;
    public javafx.scene.control.ChoiceBox flightClassChoice;
    public javafx.scene.control.ChoiceBox accommodationChoice;
    public javafx.scene.control.ChoiceBox adultsChoice;
    public javafx.scene.control.ChoiceBox childrenChoice;
    public javafx.scene.control.ChoiceBox babiesChoice;

    public javafx.scene.control.TextField fromCountry1;
    public javafx.scene.control.TextField fromCity1;
    public javafx.scene.control.TextField toCountry1;
    public javafx.scene.control.TextField toCity1;
    public javafx.scene.control.TextField airline1;
    public javafx.scene.control.TextField arrivalDate1;
    public javafx.scene.control.TextField departureDate1;
    public javafx.scene.control.TextField flightClass1;
    public javafx.scene.control.TextField accomodationType1;
    public javafx.scene.control.TextField accomodationRank1;
    public javafx.scene.control.TextField vacationType1;
    public javafx.scene.control.TextField baggage1;
    public javafx.scene.control.TextField price1;
    public javafx.scene.control.CheckBox transfersCheck1;
    public javafx.scene.control.TextField publishedBy1;


    public javafx.scene.control.TextField fromCountry2;
    public javafx.scene.control.TextField fromCity2;
    public javafx.scene.control.TextField toCountry2;
    public javafx.scene.control.TextField toCity2;
    public javafx.scene.control.TextField airline2;
    public javafx.scene.control.TextField arrivalDate2;
    public javafx.scene.control.TextField departureDate2;
    public javafx.scene.control.TextField flightClass2;
    public javafx.scene.control.TextField accomodationType2;
    public javafx.scene.control.TextField accomodationRank2;
    public javafx.scene.control.TextField vacationType2;
    public javafx.scene.control.TextField baggage2;
    public javafx.scene.control.TextField price2;
    public javafx.scene.control.CheckBox transfersCheck2;
    public javafx.scene.control.TextField publishedBy2;

    public javafx.scene.control.TextField fromCountry3;
    public javafx.scene.control.TextField fromCity3;
    public javafx.scene.control.TextField toCountry3;
    public javafx.scene.control.TextField toCity3;
    public javafx.scene.control.TextField airline3;
    public javafx.scene.control.TextField arrivalDate3;
    public javafx.scene.control.TextField departureDate3;
    public javafx.scene.control.TextField flightClass3;
    public javafx.scene.control.TextField accomodationType3;
    public javafx.scene.control.TextField accomodationRank3;
    public javafx.scene.control.TextField vacationType3;
    public javafx.scene.control.TextField baggage3;
    public javafx.scene.control.TextField price3;
    public javafx.scene.control.CheckBox transfersCheck3;
    public javafx.scene.control.TextField publishedBy3;

    public javafx.scene.control.Button PurchaseButton1;
    public javafx.scene.control.Button PurchaseButton2;
    public javafx.scene.control.Button PurchaseButton3;

    public javafx.scene.control.Button LogoutButton;
    public javafx.scene.control.Button accountSettings;
    public javafx.scene.control.Button publishButton;
    public javafx.scene.control.Button SearchButton;
    public javafx.scene.control.Button BackButton;
    public javafx.scene.control.Button nextButton;
    public javafx.scene.control.Button previousButton;


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

    public void search() {
        HashMap<String,String> askedFields = new HashMap<String,String>();
        ArrayList<Integer> vacationId = new ArrayList<>();
        vacationId = controller.GetVacationsIdByField(askedFields);
        ArrayList<Vacation> vacationsList = controller.GetVacationsInformation(vacationId);
        for (Vacation vacation:vacationsList) {

        }
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


package View;

import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchVacationController extends ViewController {

    public javafx.scene.control.Button LogoutButton;
    public javafx.scene.control.Button accountSettings;
    public javafx.scene.control.Button publishButton;

    public javafx.scene.control.Button PurchaseButton1;
    public javafx.scene.control.Button PurchaseButton2;
    public javafx.scene.control.Button PurchaseButton3;

    public javafx.scene.control.TextField publishedBy1;
    public javafx.scene.control.TextField publishedBy2;
    public javafx.scene.control.TextField publishedBy3;

    private String vacationID1;
    private String vacationID2;
    private String vacationID3;
    private Vacation vacationList;

    public SearchVacationController() {
        if(!controller.isUserConnected()) {
          PurchaseButton1.setDisable(true);
          PurchaseButton1.setDisable(true);
          PurchaseButton1.setDisable(true);
          LogoutButton.setDisable(true);
          accountSettings.setDisable(true);
          publishButton.setDisable(true);
        }
    }

    public void logOut(){
        controller.setCurrentUserInSystem(null);
        PurchaseButton1.setDisable(true);
        PurchaseButton1.setDisable(true);
        PurchaseButton1.setDisable(true);
        LogoutButton.setDisable(true);
        accountSettings.setDisable(true);
        publishButton.setDisable(true);
    }
    /**
     * Opens publishing of a vacation window
     */
    public void publishVacation() {
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
     * Opens Purchase window
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


    private void openPurchase(String sellerID, String vacationID){
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Purchase Window, VacationID:"+vacationID+", SellerID:" + sellerID);
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





}


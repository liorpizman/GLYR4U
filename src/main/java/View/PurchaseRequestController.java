package View;

import Controller.Controller;
import Model.PurchaseRequest;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PurchaseRequestController implements Initializable {

    public javafx.scene.control.Button BackButton;
    protected static Controller controller;


    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Sets the static controller for all of the user windows controllers
     */
    public void setController(Controller _controller) {
        controller = _controller;
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Purchase Request Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public javafx.scene.control.Button applyPurchaseButton;
    public javafx.scene.control.TextField phoneNumber;

    public void applyPurchaseRequest() {
        if (validPhoneNumber(phoneNumber.getText())) {
            Stage stage = (Stage) BackButton.getScene().getWindow();
            String[] vacationDetails = stage.getTitle().split(",");
            String VacationID = vacationDetails[1].split("VacationID:")[1].trim();
            String sellerUserName = vacationDetails[2].split("SellerID:")[1].trim();
            controller.insertNewPurchaseRequest(new PurchaseRequest(Integer.parseInt(VacationID), sellerUserName, controller.getCurrentUser().getUser_name(),
                    LocalDate.now().toString(), 0, phoneNumber.getText()));
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("PurchaseRequest Sent!");
            a.show();
            stage.close();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Your phone number is not valid! It should be 10 digits!");
            a.show();
            return;
        }
    }

    private boolean validPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 10) {
            return true;
        } else {
            return false;
        }
    }

}

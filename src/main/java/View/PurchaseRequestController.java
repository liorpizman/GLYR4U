
package View;

import Controller.Controller;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class represents Purchase Request Controller
 */
public class PurchaseRequestController implements Initializable {

    /**
     * fields of PurchaseRequestController
     */
    public javafx.scene.control.Button BackButton;
    protected static Controller controller;

    /**
     * Method which happens when the window initialize
     *
     * @param location  location
     * @param resources resources
     */
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
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Purchase Request Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * fields of PurchaseRequestController
     */
    public javafx.scene.control.Button applyPurchaseButton;
    public javafx.scene.control.TextField phoneNumber;

    /**
     * Method to apply purchase request
     */
    public void applyPurchaseRequest() {
        if (validPhoneNumber(phoneNumber.getText())) {
            Stage stage = (Stage) BackButton.getScene().getWindow();
            String[] vacationDetails = stage.getTitle().split(",");
            String VacationID = vacationDetails[1].split("VacationID:")[1].trim();
            String sellerUserName = vacationDetails[2].split("SellerID:")[1].trim();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to send this request?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() != ButtonType.OK) {
                return;
            }

            if (controller.insertNewPurchaseRequest(Integer.parseInt(VacationID), sellerUserName, LocalDate.now().toString(), phoneNumber.getText())) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("PurchaseRequest Sent!");
                a.show();
                stage.close();
                return;
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You have already got a purchase request!");
                a.show();
                stage.close();
                return;
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Your phone number is not valid! It should be 10 digits!");
            a.show();
            return;
        }
    }

    /**
     * Method to check if phone number is valid
     *
     * @param phoneNumber
     * @return if true or false
     */
    private boolean validPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 10) {
            return true;
        } else {
            return false;
        }
    }

}

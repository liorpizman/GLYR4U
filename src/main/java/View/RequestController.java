package View;

import Controller.Controller;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RequestController implements Initializable {

    public javafx.scene.control.Button BackButton;
    protected static Controller controller;

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
    /* Exchange Request Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public javafx.scene.control.Button ApplyExchangeButton;
    public javafx.scene.control.ComboBox<String> vacationsListBox;

    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Vacation> vacationsList = controller.getCurrentUserVacations();
        ArrayList<String> vacations_Info = new ArrayList<String>();
        for (Vacation vacation : vacationsList) {
        }
        ObservableList<String> data = FXCollections.observableArrayList(vacations_Info);
        vacationsListBox.setItems(data);

    }

    public void applyExchange() {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Purchase Request Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public javafx.scene.control.Button applyPurchaseButton;
    public javafx.scene.control.TextField phoneNumber;
    public javafx.scene.control.TextArea message;


    public void applyPurchaseRequest() {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        String[] vacationDetails = stage.getTitle().split("\\s+");
        String userID = vacationDetails[4];
        String vacationID = vacationDetails[2];
        controller.createPurchaseRequest(phoneNumber.getText(), message.getText());
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("PurchaseRequest Sent!");
        a.show();
        stage.close();
    }

}

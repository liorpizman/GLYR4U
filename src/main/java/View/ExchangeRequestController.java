package View;

import Controller.Controller;
import Model.ExchangeRequest;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class represents Exchange Request Controller
 */
public class ExchangeRequestController implements Initializable {

    /**
     * fields of ExchangeRequestController
     */
    public javafx.scene.control.Button BackButton;
    public javafx.scene.control.ComboBox<String> vacationsListBox;
    protected static Controller controller;
    public javafx.scene.control.TextField phoneNumber;

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

    /**
     * Method which happens when the window initialize
     * @param location location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> askedValues = new HashMap<>();
        askedValues.put("user_name", controller.getCurrentUser().getUser_name());
        ArrayList<Integer> vacationIDS = controller.GetVacationsIdByField(askedValues);
        ArrayList<Vacation> vacationsList = controller.GetVacationsInformation(vacationIDS);
        ArrayList<String> vacations_Info = new ArrayList<String>();
        String newLine = "";
        for (Vacation vacation : vacationsList) {
            newLine = vacation.toString() + " from: " + vacation.getOVacationCountry() + " , " + vacation.getOVacationCity() +
                    "   to: " + vacation.getDVacationCountry() + " , " + vacation.getDVacationCity();
            if (vacation.getFromDestFlight() != null) {
                newLine+= " -  2 way ticket";
            }
            else{
                newLine+= " -  1 way ticket";
            }
            vacations_Info.add(newLine);
        }
        ObservableList<String> data = FXCollections.observableArrayList(vacations_Info);
        vacationsListBox.setItems(data);
    }


    /**
     * field of ExchangeRequestController
     */
    public javafx.scene.control.Button ApplyExchangeButton;

    /**
     * Method to apply exchange request
     */
    public void applyExchange() {
        if (validPhoneNumber(phoneNumber.getText())) {
            Stage stage = (Stage) BackButton.getScene().getWindow();
            String[] vacationDetails = stage.getTitle().split(",");
            String VacationID = vacationDetails[1].split("VacationID:")[1].trim();
            String sellerUserName = vacationDetails[2].split("SellerID:")[1].trim();
            String selected = vacationsListBox.getValue();
            if(selected== null || selected.isEmpty()){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Please choose vacation before applying!");
                a.show();
                return;
            }
            String vacationIDBuyer = selected.split("From")[0].split("VacationId:")[1].trim();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to send this request?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() != ButtonType.OK) {
                return;
            }

            if(controller.insertNewExchangeRequest(Integer.parseInt(VacationID), sellerUserName, Integer.parseInt(vacationIDBuyer),
                    LocalDate.now().toString(),phoneNumber.getText())){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("ExchangeRequest Sent!");
                a.show();
                stage.close();
            }
            else{
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You have already got an exchange request!");
                a.show();
                stage.close();
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

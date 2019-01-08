package View;

import Controller.Controller;
import Model.RegisteredUser;
import Model.Vacation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Class for handling search events
 */
public class SearchVacationController implements Initializable {

    /**
     * fields of SearchVacationController
     */
    public javafx.scene.control.TextField fromCountry;
    public javafx.scene.control.TextField fromCity;
    public javafx.scene.control.TextField toCountry;
    public javafx.scene.control.TextField toCity;
    public javafx.scene.control.DatePicker arrivalDate;
    public javafx.scene.control.DatePicker departureDate;
    public javafx.scene.control.CheckBox roundTripCheck;
    public javafx.scene.control.ChoiceBox accommodationChoice;

    public javafx.scene.control.TitledPane titledPane1;
    public javafx.scene.control.TextField fromCountry1;
    public javafx.scene.control.TextField fromCity1;
    public javafx.scene.control.TextField toCountry1;
    public javafx.scene.control.TextField toCity1;
    public javafx.scene.control.TextField airline1;
    public javafx.scene.control.TextField arrivalDate1;
    public javafx.scene.control.TextField departureDate1;
    public javafx.scene.control.TextField flightClass1;
    public javafx.scene.control.TextField amounts1;
    public javafx.scene.control.TextField accommodationType1;
    public javafx.scene.control.TextField accommodationRank1;
    public javafx.scene.control.TextField vacationType1;
    public javafx.scene.control.TextField baggage1;
    public javafx.scene.control.TextField price1;
    public javafx.scene.control.CheckBox transfersCheck1;
    public javafx.scene.control.TextField publishedBy1;
    public javafx.scene.control.TextField ticketType1;

    public javafx.scene.control.TitledPane titledPane2;
    public javafx.scene.control.TextField fromCountry2;
    public javafx.scene.control.TextField fromCity2;
    public javafx.scene.control.TextField toCountry2;
    public javafx.scene.control.TextField toCity2;
    public javafx.scene.control.TextField airline2;
    public javafx.scene.control.TextField arrivalDate2;
    public javafx.scene.control.TextField departureDate2;
    public javafx.scene.control.TextField flightClass2;
    public javafx.scene.control.TextField amounts2;
    public javafx.scene.control.TextField accommodationType2;
    public javafx.scene.control.TextField accommodationRank2;
    public javafx.scene.control.TextField vacationType2;
    public javafx.scene.control.TextField baggage2;
    public javafx.scene.control.TextField price2;
    public javafx.scene.control.CheckBox transfersCheck2;
    public javafx.scene.control.TextField publishedBy2;
    public javafx.scene.control.TextField ticketType2;

    public javafx.scene.control.TitledPane titledPane3;
    public javafx.scene.control.TextField fromCountry3;
    public javafx.scene.control.TextField fromCity3;
    public javafx.scene.control.TextField toCountry3;
    public javafx.scene.control.TextField toCity3;
    public javafx.scene.control.TextField airline3;
    public javafx.scene.control.TextField arrivalDate3;
    public javafx.scene.control.TextField departureDate3;
    public javafx.scene.control.TextField flightClass3;
    public javafx.scene.control.TextField amounts3;
    public javafx.scene.control.TextField accommodationType3;
    public javafx.scene.control.TextField accommodationRank3;
    public javafx.scene.control.TextField vacationType3;
    public javafx.scene.control.TextField baggage3;
    public javafx.scene.control.TextField price3;
    public javafx.scene.control.CheckBox transfersCheck3;
    public javafx.scene.control.TextField publishedBy3;
    public javafx.scene.control.TextField ticketType3;

    public javafx.scene.control.Button PurchaseButton1;
    public javafx.scene.control.Button PurchaseButton2;
    public javafx.scene.control.Button PurchaseButton3;

    public javafx.scene.control.Button Exchange1;
    public javafx.scene.control.Button Exchange2;
    public javafx.scene.control.Button Exchange3;

    public javafx.scene.control.Button LogoutButton;
    public javafx.scene.control.Button accountSettings;
    public javafx.scene.control.Button publishButton;
    public javafx.scene.control.Button SearchButton;
    public javafx.scene.control.Button BackButton;
    public javafx.scene.control.Button nextButton;
    public javafx.scene.control.Button previousButton;
    public javafx.scene.control.Button manageRequestsButton;


    protected static Controller controller;
    private int firstVacationIndex = 0;
    private ArrayList<Vacation> vacationsList;

    /**
     * Constructor, different view for connected user and guest
     */
    public void setController(Controller _controller) {
        controller = _controller;
    }

    /**
     * Method which happens when the window initialize
     * @param location location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateVacationsList();
    }

    /**
     * Method which update vacation list in opening of search window
     */
    public void updateVacationsList() {
        vacationsList = controller.Search(null);
        SetAllResults(0);
    }

    /**
     * handles search button clicked event, setting search results to tabs.
     */
    public void search() {
        clearAllFields();
        HashMap<String, String> askedFields = new HashMap<String, String>();
        if ((fromCountry == null || fromCountry.getText().isEmpty()) ||
                (fromCity == null || fromCity.getText().isEmpty()) ||
                (toCountry == null || toCountry.getText().isEmpty()) ||
                (toCity == null || toCity.getText().isEmpty()) ||
                (arrivalDate == null || arrivalDate.getValue() == null || arrivalDate.getValue().toString().isEmpty()) ||
                (departureDate == null || departureDate.getValue() == null || departureDate.getValue().toString().isEmpty())
                ) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Not all fields have been filled correctly, please fill all fields ");
            a.show();
            return;
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate inputArrivalDate = arrivalDate.getValue();
        if (inputArrivalDate.compareTo(currentDate) < 0) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please Enter a valid arrival date");
            a.show();
            return;
        }
        LocalDate inputDepartureDate = departureDate.getValue();
        if (inputDepartureDate.compareTo(currentDate) < 0) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please Enter a valid departure date");
            a.show();
            return;
        }
        if (inputDepartureDate.compareTo(inputArrivalDate) < 0) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Your departure date is before your arrival date");
            a.show();
            return;
        }
        askedFields.put("OVacationCountry", fromCountry.getText().toLowerCase());
        askedFields.put("OVacationCity", fromCity.getText().toLowerCase());
        askedFields.put("DVacationCountry", toCountry.getText().toLowerCase());
        askedFields.put("DVacationCity", toCity.getText().toLowerCase());
        askedFields.put("StartDate", arrivalDate.getValue().toString());
        askedFields.put("EndDate", departureDate.getValue().toString());
        askedFields.put("AccommodationType", (String) accommodationChoice.getValue());
        ArrayList<Integer> removeKeys = new ArrayList<Integer>();
        vacationsList = controller.Search(askedFields);
        if (vacationsList.size() == 0) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("No Search Results, please try different options ");
            a.show();
            return;
        }
        for (int i = 0; i < vacationsList.size(); i++) {
            if (roundTripCheck.isSelected())
                if (vacationsList.get(i).getFromDestFlight() == null)
                    removeKeys.add(i);
        }
        for (int i = 0; i < removeKeys.size(); i++) {
            vacationsList.remove(removeKeys.get(i));
        }
        SetAllResults(0);
    }

    /**
     * Sets results to the vacations tabs
     * @param i - num of vacation in the list
     */
    public void SetAllResults(int i) {
        RegisteredUser currentUser = controller.getCurrentUser();
        if (i < vacationsList.size()) {
            if (currentUser != null && vacationsList.get(i).getUserID().equals(currentUser.getUser_name())) {
                PurchaseButton1.setDisable(true);
                Exchange1.setDisable(true);
            } else {
                Exchange1.setDisable(false);
                PurchaseButton1.setDisable(false);
            }
            SetResultFields1(vacationsList.get(i));
        } else {
            clearFirstFields();
            PurchaseButton1.setDisable(true);
            Exchange1.setDisable(true);
        }
        if (i + 1 < vacationsList.size()) {
            if (currentUser != null && vacationsList.get(i + 1).getUserID().equals(controller.getCurrentUser().getUser_name())) {
                PurchaseButton2.setDisable(true);
                Exchange2.setDisable(true);
            } else {
                PurchaseButton2.setDisable(false);
                Exchange2.setDisable(false);
            }
            SetResultFields2(vacationsList.get(i + 1));

        } else {
            clearSecondFields();
            PurchaseButton2.setDisable(true);
            Exchange2.setDisable(true);
        }
        if (i + 2 < vacationsList.size()) {
            if (currentUser != null && vacationsList.get(i+2).getUserID().equals(controller.getCurrentUser().getUser_name())) {
                PurchaseButton3.setDisable(true);
                Exchange3.setDisable(true);
            } else {
                PurchaseButton3.setDisable(false);
                Exchange3.setDisable(false);
            }
            SetResultFields3(vacationsList.get(i + 2));
        } else {
            clearThirdFields();
            PurchaseButton3.setDisable(true);
            Exchange3.setDisable(true);
        }
        if (i + 3 >= vacationsList.size()) {
            nextButton.setDisable(true);
        } else {
            nextButton.setDisable(false);
        }
        if (i - 3 < 0) {
            previousButton.setDisable(true);
        } else {
            previousButton.setDisable(false);
        }
    }

    /**
     * Sets results to the vacations tab1
     * @param _currentVacation current vacation
     */
    public void SetResultFields1(Vacation _currentVacation) {
        titledPane1.setText(_currentVacation.toString()); // _currentVacation.toString()
        fromCountry1.setText(_currentVacation.getOVacationCountry());
        fromCity1.setText((_currentVacation.getOVacationCity()));
        toCountry1.setText(_currentVacation.getDVacationCountry());
        toCity1.setText(_currentVacation.getDVacationCity());
        airline1.setText(_currentVacation.getFromOriginFlightAirline());
        amounts1.setText(Integer.toString(_currentVacation.getFromOriginFlight().getAmountOfTickets()));
        arrivalDate1.setText((_currentVacation.getStartDate()));
        flightClass1.setText(_currentVacation.getFromOriginFlightClass());
        accommodationType1.setText((_currentVacation.getAccommodationType()));
        accommodationRank1.setText(Integer.toString(_currentVacation.getAccommodationRank()));
        vacationType1.setText((_currentVacation.getVacationType()));
        baggage1.setText((_currentVacation.getBaggageType()));
        price1.setText(Double.toString(_currentVacation.getPrice()));
        publishedBy1.setText(_currentVacation.getUserID());
        departureDate1.setText(_currentVacation.getEndDate());
        if (_currentVacation.getFromDestFlight() == null) {
            ticketType1.setText("One Way Flight");
        } else {
            ticketType1.setText("Round Trip");
        }

        if (_currentVacation.isTransfers()) {
            transfersCheck1.setText("Yes");
        } else {
            transfersCheck1.setText("No");
        }
    }

    /**
     * Sets results to the vacations tab2
     * @param _currentVacation current vacation
     */
    public void SetResultFields2(Vacation _currentVacation) {
        titledPane2.setText(_currentVacation.toString()); // _currentVacation.toString()
        fromCountry2.setText(_currentVacation.getOVacationCountry());
        fromCity2.setText((_currentVacation.getOVacationCity()));
        fromCity2.setText((_currentVacation.getOVacationCity()));
        toCountry2.setText(_currentVacation.getDVacationCountry());
        toCity2.setText(_currentVacation.getDVacationCity());
        airline2.setText(_currentVacation.getFromOriginFlightAirline());
        arrivalDate2.setText((_currentVacation.getStartDate()));
        flightClass2.setText(_currentVacation.getFromOriginFlightClass());
        amounts2.setText(Integer.toString(_currentVacation.getFromOriginFlight().getAmountOfTickets()));
        accommodationType2.setText((_currentVacation.getAccommodationType()));
        accommodationRank2.setText(Integer.toString(_currentVacation.getAccommodationRank()));
        vacationType2.setText((_currentVacation.getVacationType()));
        baggage2.setText((_currentVacation.getBaggageType()));
        price2.setText(Double.toString(_currentVacation.getPrice()));
        publishedBy2.setText(_currentVacation.getUserID());
        departureDate2.setText(_currentVacation.getEndDate());
        if (_currentVacation.getFromDestFlight() == null) {
            ticketType2.setText("One Way Flight");
        } else {
            ticketType2.setText("Round Trip");
        }
        if (_currentVacation.isTransfers()) {
            transfersCheck2.setText("Yes");
        } else {
            transfersCheck2.setText("No");
        }
    }

    /**
     * Sets results to the vacations tab3
     * @param _currentVacation current vacation
     */
    public void SetResultFields3(Vacation _currentVacation) {
        titledPane3.setText(_currentVacation.toString()); // _currentVacation.toString()
        fromCountry3.setText(_currentVacation.getOVacationCountry());
        fromCity3.setText((_currentVacation.getOVacationCity()));
        fromCity3.setText((_currentVacation.getOVacationCity()));
        toCountry3.setText(_currentVacation.getDVacationCountry());
        toCity3.setText(_currentVacation.getDVacationCity());
        airline3.setText(_currentVacation.getFromOriginFlightAirline());
        arrivalDate3.setText((_currentVacation.getStartDate()));
        flightClass3.setText(_currentVacation.getFromOriginFlightClass());
        amounts3.setText(Integer.toString(_currentVacation.getFromOriginFlight().getAmountOfTickets()));
        accommodationType3.setText((_currentVacation.getAccommodationType()));
        accommodationRank3.setText(Integer.toString(_currentVacation.getAccommodationRank()));
        vacationType3.setText((_currentVacation.getVacationType()));
        baggage3.setText((_currentVacation.getBaggageType()));
        price3.setText(Double.toString(_currentVacation.getPrice()));
        publishedBy3.setText(_currentVacation.getUserID());
        departureDate3.setText(_currentVacation.getEndDate());
        if (_currentVacation.getFromDestFlight() == null) {
            ticketType3.setText("One Way Flight");
        } else {
            ticketType3.setText("Round Trip");
        }
        if (_currentVacation.isTransfers()) {
            transfersCheck3.setText("Yes");
        } else {
            transfersCheck3.setText("No");
        }
    }

    /**
     * handles next button event
     */
    public void next() {
        firstVacationIndex += 3;
        SetAllResults(firstVacationIndex);
        if (firstVacationIndex + 3 >= vacationsList.size()) {
            nextButton.setDisable(true);
        }
    }

    /**
     * handles previous button event
     */
    public void previous() {
        firstVacationIndex -= 3;
        SetAllResults(firstVacationIndex);
        if (firstVacationIndex == 0) {
            previousButton.setDisable(true);
        }
    }

    /**
     * Notifying the Purchase window which vacationID is sold and who is the Seller
     */
    public void Purchase1() {
        purchaseRequest(publishedBy1.getText(), vacationsList.get(firstVacationIndex).getVactionId(), Double.parseDouble(price1.getText()));
    }

    /**
     * Notifying the Purchase window which vacationID is sold and who is the Seller
     */
    public void Purchase2() {
        purchaseRequest(publishedBy2.getText(), vacationsList.get(firstVacationIndex + 1).getVactionId(), Double.parseDouble(price2.getText()));
    }

    /**
     * Notifying the Purchase window which vacationID is sold and who is the Seller
     */
    public void Purchase3() {
        purchaseRequest(publishedBy3.getText(), vacationsList.get(firstVacationIndex + 2).getVactionId(), Double.parseDouble(price3.getText()));
    }

    /**
     * Opens Purchase window
     */
    private void purchaseRequest(String sellerID, int vacationID, double price) {
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You can't purchase vacations if you aren't connected");
            a.show();
            return;
        }
        controller.setCurrentVacation(Integer.toString(vacationID));
        controller.setCurrrentSeller(sellerID);
        controller.setCurrentPrice(price);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Purchase Window, VacationID: " + vacationID + " ,SellerID: " + sellerID);
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("PurchaseRequest.fxml"));
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
     * switches back to home window
     */
    public void backHome() {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Opens publishing of a vacation window
     */
    public void publishVacation() {
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You can't Publish if you aren't connected");
            a.show();
            return;
        }
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
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You are not logged in");
            a.show();
            return;
        }
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("General Settings");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AccountSettings.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 400, 470);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
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
        backHome();
    }


    /**
     * clear all fields of first tab
     */
    public void clearFirstFields() {
        titledPane1.setText("");
        fromCountry1.setText("");
        fromCity1.setText("");
        toCountry1.setText("");
        toCity1.setText("");
        airline1.setText("");
        amounts1.setText("");
        arrivalDate1.setText("");
        flightClass1.setText("");
        accommodationType1.setText("");
        accommodationRank1.setText("");
        vacationType1.setText("");
        baggage1.setText("");
        price1.setText("");
        publishedBy1.setText("");
        departureDate1.setText("");
        ticketType1.setText("");
    }

    /**
     * clear all fields of second tab
     */
    public void clearSecondFields() {
        titledPane2.setText("");
        fromCountry2.setText("");
        fromCity2.setText("");
        toCountry2.setText("");
        toCity2.setText("");
        airline2.setText("");
        amounts2.setText("");
        arrivalDate2.setText("");
        flightClass2.setText("");
        accommodationType2.setText("");
        accommodationRank2.setText("");
        vacationType2.setText("");
        baggage2.setText("");
        price2.setText("");
        publishedBy2.setText("");
        departureDate2.setText("");
        ticketType2.setText("");
    }

    /**
     * clear all fields of third tab
     */
    public void clearThirdFields() {
        titledPane3.setText("");
        fromCountry3.setText("");
        fromCity3.setText("");
        toCountry3.setText("");
        toCity3.setText("");
        airline3.setText("");
        amounts3.setText("");
        arrivalDate3.setText("");
        flightClass3.setText("");
        accommodationType3.setText("");
        accommodationRank3.setText("");
        vacationType3.setText("");
        baggage3.setText("");
        price3.setText("");
        publishedBy3.setText("");
        departureDate3.setText("");
        ticketType3.setText("");
    }

    /**
     * clear all tabs from previous search
     */
    private void clearAllFields() {
        clearFirstFields();
        clearSecondFields();
        clearThirdFields();
    }


    /**
     * Method which calls an exchange request function to handle the first tab request
     * @param actionEvent actionEvent
     */
    public void ExchangeRequest1(ActionEvent actionEvent) {
        exchangeRequest(publishedBy1.getText(), vacationsList.get(firstVacationIndex).getVactionId(), Double.parseDouble(price1.getText()));
    }

    /**
     * Method which calls an exchange request function to handle the second tab request
     * @param actionEvent actionEvent
     */
    public void ExchangeRequest2(ActionEvent actionEvent) {
        exchangeRequest(publishedBy2.getText(), vacationsList.get(firstVacationIndex + 1).getVactionId(), Double.parseDouble(price2.getText()));
    }

    /**
     * Method which calls an exchange request function to handle the third tab request
     * @param actionEvent actionEvent
     */
    public void ExchangeRequest3(ActionEvent actionEvent) {
        exchangeRequest(publishedBy3.getText(), vacationsList.get(firstVacationIndex + 2).getVactionId(), Double.parseDouble(price3.getText()));
    }

    /**
     * Opens Purchase window
     */
    private void exchangeRequest(String sellerID, int vacationID, double price) {
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You can't exchange vacations if you aren't connected");
            a.show();
            return;
        }
        controller.setCurrentVacation(Integer.toString(vacationID));
        controller.setCurrrentSeller(sellerID);
        controller.setCurrentPrice(price);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Exchange Window, VacationID: " + vacationID + " ,SellerID: " + sellerID);
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ExchangeVacationRequest.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 750, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * handles pressing on manage requests button
     */
    public void manageRequests() {
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You can't Manage Requests if you aren't connected");
            a.show();
            return;
        }
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Manage Requests Window");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ManageRequests.fxml"));
            root.getStylesheets().add(getClass().getClassLoader().getResource("flightCSS.css").toExternalForm());
            Scene scene = new Scene(root, 1000, 500);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.getCause().printStackTrace();
        }
    }

    /**
     * handles pressing on all vacations button
     */
    public void refreshAllVacations(){
        updateVacationsList();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("The page refreshed.\nAll the vacations are represented now.");
        a.show();
    }
}


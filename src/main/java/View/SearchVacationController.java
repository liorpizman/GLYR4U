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

    public javafx.scene.control.TitledPane titledPane1;
    public javafx.scene.control.TextField fromCountry1;
    public javafx.scene.control.TextField fromCity1;
    public javafx.scene.control.TextField toCountry1;
    public javafx.scene.control.TextField toCity1;
    public javafx.scene.control.TextField airline1;
    public javafx.scene.control.TextField arrivalDate1;
    public javafx.scene.control.TextField departureDate1;
    public javafx.scene.control.TextField flightClass1;
    public javafx.scene.control.TextField adults1;
    public javafx.scene.control.TextField children1;
    public javafx.scene.control.TextField babies1;
    public javafx.scene.control.TextField accomodationType1;
    public javafx.scene.control.TextField accomodationRank1;
    public javafx.scene.control.TextField vacationType1;
    public javafx.scene.control.TextField baggage1;
    public javafx.scene.control.TextField price1;
    public javafx.scene.control.CheckBox transfersCheck1;
    public javafx.scene.control.TextField publishedBy1;

    public javafx.scene.control.TitledPane titledPane2;
    public javafx.scene.control.TextField fromCountry2;
    public javafx.scene.control.TextField fromCity2;
    public javafx.scene.control.TextField toCountry2;
    public javafx.scene.control.TextField toCity2;
    public javafx.scene.control.TextField airline2;
    public javafx.scene.control.TextField arrivalDate2;
    public javafx.scene.control.TextField departureDate2;
    public javafx.scene.control.TextField flightClass2;
    public javafx.scene.control.TextField adults2;
    public javafx.scene.control.TextField children2;
    public javafx.scene.control.TextField babies2;
    public javafx.scene.control.TextField accomodationType2;
    public javafx.scene.control.TextField accomodationRank2;
    public javafx.scene.control.TextField vacationType2;
    public javafx.scene.control.TextField baggage2;
    public javafx.scene.control.TextField price2;
    public javafx.scene.control.CheckBox transfersCheck2;
    public javafx.scene.control.TextField publishedBy2;

    public javafx.scene.control.TitledPane titledPane3;
    public javafx.scene.control.TextField fromCountry3;
    public javafx.scene.control.TextField fromCity3;
    public javafx.scene.control.TextField toCountry3;
    public javafx.scene.control.TextField toCity3;
    public javafx.scene.control.TextField airline3;
    public javafx.scene.control.TextField arrivalDate3;
    public javafx.scene.control.TextField departureDate3;
    public javafx.scene.control.TextField flightClass3;
    public javafx.scene.control.TextField adults3;
    public javafx.scene.control.TextField children3;
    public javafx.scene.control.TextField babies3;
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
    private int firstVacationIndex = 0;
    private ArrayList<Vacation> vacationsList;
    String[] vacationIDs;

    /**
     * Constructor, different view for connected user and guest
     */
    public void setController(Controller _controller) {
        controller = _controller;
    }

    /**
     * handles search button clicked event, setting search results to tabs.
     */
    public void search() {
        HashMap<String, String> askedFields = new HashMap<String, String>();
//        searchTest();
        askedFields.put("OVacationCountry", fromCountry.getText());
        askedFields.put("OVacationCity", fromCity.getText());
        askedFields.put("DVacationCountry", toCountry.getText());
        askedFields.put("DVacationCity", toCity.getText());
        askedFields.put("BabyTickets", (String) babiesChoice.getValue());
        askedFields.put("ChildTickets", (String) childrenChoice.getValue());
        askedFields.put("AdultTickets", (String) adultsChoice.getValue());
        askedFields.put("StartDate", arrivalDate.getValue().toString());
        askedFields.put("EndDate", departureDate.getValue().toString());
        askedFields.put("AccommodationType", (String) accommodationChoice.getValue());
        askedFields.put("TicketType", (String) flightClassChoice.getValue());
        ArrayList<Integer> vacationIntIDs = controller.GetVacationsIdByField(askedFields);

        for (int i = 0; i < vacationIntIDs.size(); i++) {
            vacationIDs[i] = vacationIntIDs.get(i).toString();
        }
        vacationsList = controller.GetVacationsInformation(vacationIntIDs);
        SetAllResults(0);
        // Vacation [] VacationsArray = (Vacation[])vacationsList.toArray();

    }

    /**
     * public void searchTest() {
     * HashMap<String,String> askedFields = new HashMap<String,String>();
     * askedFields.put("OVacationCountry", "Israel");
     * askedFields.put("OVacationCity", "Tel-Aviv");
     * askedFields.put("DVacationCountry", "France");
     * askedFields.put("DVacationCity", "Paris");
     * <p>
     * /////////////////////////////////////////////////////
     * //askedFields.put("BabyTickets","1");
     * //  askedFields.put("ChildTickets","2");
     * // askedFields.put("AdultTickets",("3"));
     * askedFields.put("StartDate", "2019-01-03");
     * askedFields.put("EndDate", "2019-01-01");
     * // askedFields.put("AccommodationType","Hotel");
     * // askedFields.put("TicketType", "BasicClass");
     * ArrayList<Integer> vacationIDs = controller.GetVacationsIdByField(askedFields);
     * vacationsList = controller.GetVacationsInformation(vacationIDs);
     * SetAllResults(0);
     * // Vacation [] VacationsArray = (Vacation[])vacationsList.toArray();
     * <p>
     * }
     **/


    public void SetAllResults(int i) {
        if (i < vacationsList.size()) {
            SetResultFields1(vacationsList.get(i));
            PurchaseButton1.setDisable(false);
        }
        if (i + 1 < vacationsList.size()) {
            SetResultFields2(vacationsList.get(i + 1));
            PurchaseButton2.setDisable(false);

        }
        if (i + 2 < vacationsList.size()) {
            SetResultFields3(vacationsList.get(i + 2));
            PurchaseButton3.setDisable(false);
        }
        if (i + 3 >= vacationsList.size()) {
            nextButton.setDisable(true);
        } else {
            nextButton.setDisable(false);
        }

    }

    public void SetResultFields1(Vacation _currentVacation) {
        titledPane1.setText(""); // _currentVacation.toString()
        fromCountry1.setText(_currentVacation.getOVacationCountry());
        fromCity1.setText((_currentVacation.getOVacationCity()));
        airline1.setText(_currentVacation.getFromOriginFlightAirline());
        arrivalDate1.setText((_currentVacation.getStartDate()));
        flightClass1.setText(_currentVacation.getFromOriginFlightClass());
        accomodationType1.setText((_currentVacation.getAccommodationType()));
        accomodationRank1.setText(Integer.toString(_currentVacation.getAccommodationRank()));
        vacationType1.setText((_currentVacation.getVacationType()));
        baggage1.setText((_currentVacation.getBaggageType()));
        price1.setText(Double.toString(_currentVacation.getPrice()));
        if (_currentVacation.isTransfers()) {
            transfersCheck1.setText("Yes");
        } else {
            transfersCheck1.setText("No");
        }
        //publishedBy1.setText((VacationsArray[i].))
    }

    public void SetResultFields2(Vacation _currentVacation) {
        titledPane2.setText(""); // _currentVacation.toString()
        fromCountry2.setText(_currentVacation.getOVacationCountry());
        fromCity2.setText((_currentVacation.getOVacationCity()));
        airline2.setText(_currentVacation.getFromOriginFlightAirline());
        arrivalDate2.setText((_currentVacation.getStartDate()));
        flightClass2.setText(_currentVacation.getFromOriginFlightClass());
        accomodationType2.setText((_currentVacation.getAccommodationType()));
        accomodationRank2.setText(Integer.toString(_currentVacation.getAccommodationRank()));
        vacationType2.setText((_currentVacation.getVacationType()));
        baggage2.setText((_currentVacation.getBaggageType()));
        price2.setText(Double.toString(_currentVacation.getPrice()));
        if (_currentVacation.isTransfers()) {
            transfersCheck2.setText("Yes");
        } else {
            transfersCheck2.setText("No");
        }
        //publishedBy1.setText((VacationsArray[i].))
    }

    public void SetResultFields3(Vacation _currentVacation) {
        titledPane3.setText(""); // _currentVacation.toString()
        fromCountry3.setText(_currentVacation.getOVacationCountry());
        fromCity3.setText((_currentVacation.getOVacationCity()));
        airline3.setText(_currentVacation.getFromOriginFlightAirline());
        arrivalDate3.setText((_currentVacation.getStartDate()));
        flightClass3.setText(_currentVacation.getFromOriginFlightClass());
        accomodationType3.setText((_currentVacation.getAccommodationType()));
        accomodationRank3.setText(Integer.toString(_currentVacation.getAccommodationRank()));
        vacationType3.setText((_currentVacation.getVacationType()));
        baggage3.setText((_currentVacation.getBaggageType()));
        price3.setText(Double.toString(_currentVacation.getPrice()));
        if (_currentVacation.isTransfers()) {
            transfersCheck3.setText("Yes");
        } else {
            transfersCheck3.setText("No");
        }
        //publishedBy1.setText((VacationsArray[i].))
    }

    public void next() {
        firstVacationIndex += 3;
        SetAllResults(firstVacationIndex);
        if (firstVacationIndex + 3 >= vacationsList.size()) {
            previousButton.setDisable(true);
        }
    }

    public void previous() {
        firstVacationIndex -= 3;
        SetAllResults(firstVacationIndex);
        if (firstVacationIndex == 0) {
            nextButton.setDisable(true);
        }
    }

    /**
     * Notifying the Purchase window which vacationID is sold and who is the Seller
     */
    public void Purchase1() {
        openPurchase(publishedBy1.getText(), vacationIDs[firstVacationIndex]);
    }

    public void Purchase2() {
        openPurchase(publishedBy2.getText(), vacationIDs[firstVacationIndex + 1]);
    }

    public void Purchase3() {
        openPurchase(publishedBy3.getText(), vacationIDs[firstVacationIndex + 2]);
    }

    /**
     * Opens Purchase window
     */
    private void openPurchase(String sellerID, String vacationID) {
        if (!controller.isUserConnected()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You can't purchase vacationse if you aren't connected");
            a.show();
            return;
        }
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
}


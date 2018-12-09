package View;

import Model.FlightTickets;
import Model.Location;
import Model.Vacation;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class PublishVacationController extends ViewController {
    private static int VactionID = 200;
    public javafx.scene.control.Button PublishButton;
    public javafx.scene.control.TextField FromCountry;
    public javafx.scene.control.TextField FromCity;
    public javafx.scene.control.TextField ToCountry;
    public javafx.scene.control.TextField ToCity;
    public javafx.scene.control.TextField Price;

    public javafx.scene.control.DatePicker Arrival;
    public javafx.scene.control.DatePicker Departure;

    public javafx.scene.control.ChoiceBox Accommodation;
    public javafx.scene.control.ChoiceBox AccommodationRank;
    public javafx.scene.control.ChoiceBox FlightClass;

    public javafx.scene.control.ChoiceBox Adults;
    public javafx.scene.control.ChoiceBox Children;
    public javafx.scene.control.ChoiceBox Babies;


    public javafx.scene.control.ChoiceBox Baggage;
    public javafx.scene.control.ChoiceBox VacationType;

    public javafx.scene.control.TextField Airline;

    public javafx.scene.control.CheckBox Transfers;

    public void publishVacation() {
        Random r = new Random();
        int low = VactionID;
        int high = VactionID * 5;
        int _vactionId = r.nextInt(high - low) + low;
        VactionID += 500;
        String _fromCountry = FromCountry.getText();
        String _fromCity = FromCity.getText();
        String _toCountry = ToCountry.getText();
        String _toCity = ToCity.getText();

        LocalDate _arrival = Arrival.getValue();
        LocalDate _departure = Departure.getValue();

        String _airline = Airline.getText();
        String _accommodation = Accommodation.getValue().toString();
        String _accommodationRank = AccommodationRank.getValue().toString();
        String _flightClass = FlightClass.getValue().toString();


        int RANK = 0;
        if (_accommodationRank.equals("Bad")) {
            RANK = 0;
        } else if (_accommodationRank.equals("Likely")) {
            RANK = 1;
        } else if (_accommodationRank.equals("Good")) {
            RANK = 2;
        } else if (_accommodationRank.equals("Very Good")) {
            RANK = 3;
        } else if (_accommodationRank.equals("Excellent")) {
            RANK = 4;
        }

        int _adults, _children, _babies;
        try {
            _adults = Integer.parseInt(Adults.getValue().toString());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid adults amount.\nPlease fill this field.");
            a.show();
            return;
        }

        try {
            _children = Integer.parseInt(Children.getValue().toString());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid children amount.\nPlease fill this field.");
            a.show();
            return;
        }

        try {
            _babies = Integer.parseInt(Babies.getValue().toString());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid babies amount.\nPlease fill this field.");
            a.show();
            return;
        }

        double _price = 0;
        try {
            _price = Double.parseDouble(Price.getText());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid price.\nPlease fill this field.");
            a.show();
            return;
        }

        String _baggage = Baggage.getValue().toString();
        String _vacationType = VacationType.getValue().toString();

        boolean _transfers = Transfers.isSelected();

        if (_fromCountry.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered the origin country.\nPlease fill this field.");
            a.show();
            return;
        } else if (_fromCity.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered the origin city.\nPlease fill this field.");
            a.show();
            return;
        } else if (_toCountry.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered the destination country.\nPlease fill this field.");
            a.show();
            return;
        } else if (_toCity.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered the destination city.\nPlease fill this field.");
            a.show();
            return;
        } else {
            ArrayList<Integer> VacationsID = new ArrayList<>();
            Location originLocation = new Location(_fromCountry, _fromCity);
            Location destLocation = new Location(_toCountry, _toCity);
            int[] travelersType = new int[3];
            travelersType[0] = _babies;
            travelersType[1] = _children;
            travelersType[2] = _adults;
            FlightTickets originTicket = new FlightTickets(_airline, destLocation, originLocation, travelersType, _flightClass, _vactionId);
            FlightTickets destTicket = new FlightTickets(_airline, originLocation, destLocation, travelersType, _flightClass, _vactionId);
            controller.insertFlightTickets(originTicket);
            controller.insertFlightTickets(destTicket);
            Vacation newVacation = new Vacation(_vactionId, originTicket, destTicket, destLocation, originLocation,
                    _arrival.toString(), _departure.toString(), _price, _baggage, _vacationType, _accommodation,
                    true, _transfers, RANK, controller.getCurrentUserName());
            controller.insertVacation(newVacation);
            VacationsID.add(_vactionId);
            if ((controller.GetVacationsInformation(VacationsID)).size() != 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The vacation published successfully.\n" + "Your Vacation ID in the system is: " + _vactionId);
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Error occurred! Please try again.");
                a.show();
            }
        }
        //clearVacationData();

    }
}

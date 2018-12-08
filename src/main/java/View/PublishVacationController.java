package View;

import javafx.scene.control.Alert;

import java.time.LocalDate;

public class PublishVacationController extends VacationController {

    public javafx.scene.control.TextField FromCountry;
    public javafx.scene.control.TextField FromCity;
    public javafx.scene.control.TextField ToCountry;
    public javafx.scene.control.TextField ToCity;

    public javafx.scene.control.DatePicker Arrival;
    public javafx.scene.control.DatePicker Departure;

    public javafx.scene.control.ChoiceBox Accommodation;
    public javafx.scene.control.ChoiceBox AccommodationRank;
    public javafx.scene.control.ChoiceBox FlightClass;

    public javafx.scene.control.ChoiceBox Adults;
    public javafx.scene.control.ChoiceBox Children;
    public javafx.scene.control.ChoiceBox Babies;

    public javafx.scene.control.ChoiceBox Price;
    public javafx.scene.control.ChoiceBox Baggage;
    public javafx.scene.control.ChoiceBox VacationType;

    public javafx.scene.control.CheckBox Transfers;


    public void publishVacation() {
        String _fromCountry = FromCountry.getText();
        String _fromCity = FromCity.getText();
        String _toCountry = ToCountry.getText();
        String _toCity = ToCity.getText();

        LocalDate _arrival = Arrival.getValue();
        LocalDate _departure = Departure.getValue();

        String _accommodation = Accommodation.getValue().toString();
        String _accommodationRank = AccommodationRank.getValue().toString();
        String _flightClass = FlightClass.getValue().toString();


        String _adults = Adults.getValue().toString();
        String _children = Children.getValue().toString();
        String _babies = Babies.getValue().toString();

        String _price = Price.getValue().toString();
        String _baggage = Baggage.getValue().toString();
        String _vacationType = VacationType.getValue().toString();

        boolean _transfers = Transfers.isSelected();

        if (_fromCountry.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered the origin country.\nPlease fill this field.");
            a.show();
        } else if (_fromCity.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered the origin city.\nPlease fill this field.");
            a.show();
        } else if (_toCountry.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered the destination country.\nPlease fill this field.");
            a.show();
        } else if (_toCity.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered the destination city.\nPlease fill this field.");
            a.show();
        }



    }
}

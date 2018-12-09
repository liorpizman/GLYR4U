package View;

import Model.FlightTickets;
import Model.Location;
import Model.Vacation;
import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.util.ArrayList;

public class PublishVacationController extends ViewController {
    private static int VactionID = 200;
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
        int _vactionId = VactionID++;
        String _fromCountry = FromCountry.getText();
        String _fromCity = FromCity.getText();
        String _toCountry = ToCountry.getText();
        String _toCity = ToCity.getText();

        LocalDate _arrival = Arrival.getValue();
        LocalDate _departure = Departure.getValue();

        String _airline = "el-al";
        String _accommodation = Accommodation.getValue().toString();
        String _accommodationRank = AccommodationRank.getValue().toString();
        String _flightClass = FlightClass.getValue().toString();

        int _adults = (int)Adults.getValue();
        int _children = (int)Children.getValue();
        int _babies = (int)Babies.getValue();

        double _price = (double)Price.getValue();
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
        else
        {
            ArrayList<Integer> VacationsID = new ArrayList<>();
            Location originLocation = new Location(_fromCountry, _fromCity);
            Location destLocation = new Location(_toCountry, _toCity);
            int[] travelersType = new int[3];
            travelersType[0] = _babies;
            travelersType[1] = _children;
            travelersType[2] = _adults;
            FlightTickets originTicket = new FlightTickets(_airline, destLocation, originLocation, travelersType, _flightClass, _vactionId);
            FlightTickets destTicket = new FlightTickets(_airline, originLocation, destLocation, travelersType, _flightClass, _vactionId);
            Vacation newVacation = new Vacation(_vactionId, originTicket, destTicket, destLocation, originLocation,
                    _arrival.toString(), _departure.toString(), _price, _baggage, _vacationType, _accommodation,
                    true,  _transfers);
            controller.insertVacation(newVacation);
            VacationsID.add(_vactionId);
            if ((controller.GetVacationsInformation(VacationsID)).size() != 0)
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The user created successfully.");
                a.show();
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Error occurred! Please try again.");
                a.show();
            }
        }
        //clearVacationData();

    }
}

package View;

import Model.FlightTickets;
import Model.Vacation;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for handling update vacation events
 */
public class UpdateVacationView extends VacationView {

    public javafx.scene.control.TextField VacationIDUpdate;
    public javafx.scene.control.TextField FromCountryUpdate;
    public javafx.scene.control.TextField FromCityUpdate;
    public javafx.scene.control.TextField ToCountyUpdate;
    public javafx.scene.control.TextField ToCityUpdate;
    public javafx.scene.control.DatePicker ArrivalUpdate;
    public javafx.scene.control.DatePicker DepartureUpdate;
    public javafx.scene.control.ChoiceBox AccommodationUpdate;
    public javafx.scene.control.ChoiceBox AccommodationRankUpdate;
    public javafx.scene.control.ChoiceBox FlightClassUpdate;
    public javafx.scene.control.ChoiceBox AdultsUpdate;
    public javafx.scene.control.ChoiceBox ChildrenUpdate;
    public javafx.scene.control.ChoiceBox BabiesUpdate;
    public javafx.scene.control.TextField PriceUpdate;
    public javafx.scene.control.ChoiceBox BaggageUpdate;
    public javafx.scene.control.ChoiceBox VacationTypeUpdate;
    public javafx.scene.control.CheckBox TransfersUpdate;
    public javafx.scene.control.TextField AirlineUpdate;

    public javafx.scene.control.Button showUpdateButton;
    public javafx.scene.control.Button updateButton;

    /**
     * Shows relevant info for vacation id for update
     */
    public void showVacationUpdate() {
        if (validVacationID(VacationIDUpdate.getText())) {
            ArrayList<Vacation> OneVacation = searchVacationData(VacationIDUpdate.getText());
            if (OneVacation.size() < 1) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Invalid Vacation ID, please try again.");
                a.show();
            } else {
                Vacation currVacation = OneVacation.get(0);
                FromCountryUpdate.setText(currVacation.getFromOriginFlight().getOriginCountry());
                FromCountryUpdate.setDisable(true);

                FromCityUpdate.setText(currVacation.getFromOriginFlight().getOriginCity());
                FromCityUpdate.setDisable(true);

                if (currVacation.getFromDestFlight() != null) {
                    ToCountyUpdate.setText(currVacation.getFromDestFlight().getOriginCountry());
                }
                ToCountyUpdate.setDisable(true);

                if (currVacation.getFromDestFlight() != null) {
                    ToCityUpdate.setText(currVacation.getFromDestFlight().getOriginCity());
                }
                ToCityUpdate.setDisable(true);

                String[] StartDate = currVacation.getStartDate().split("-");
                LocalDate Sdate = LocalDate.of(Integer.parseInt(StartDate[0]), Integer.parseInt(StartDate[1]), Integer.parseInt(StartDate[2]));
                ArrivalUpdate.setValue(Sdate);
                ArrivalUpdate.setDisable(false);

                String[] EndDate = currVacation.getEndDate().split("-");
                LocalDate Edate = LocalDate.of(Integer.parseInt(EndDate[0]), Integer.parseInt(EndDate[1]), Integer.parseInt(EndDate[2]));
                DepartureUpdate.setValue(Edate);
                DepartureUpdate.setDisable(false);

                AccommodationUpdate.setValue(currVacation.getAccommodationType());
                AccommodationUpdate.setDisable(false);

                AccommodationRankUpdate.setValue(Integer.toString(currVacation.getAccommodationRank()));
                AccommodationRankUpdate.setDisable(false);

                FlightClassUpdate.setValue(currVacation.getFromOriginFlight().getTicketType());
                FlightClassUpdate.setDisable(true);

                AdultsUpdate.setValue(Integer.toString(currVacation.getFromOriginFlight().getAdultTicketsAmount()));
                AdultsUpdate.setDisable(true);

                ChildrenUpdate.setValue(Integer.toString(currVacation.getFromOriginFlight().getChildTicketsAmount()));
                ChildrenUpdate.setDisable(true);

                BabiesUpdate.setValue(Integer.toString(currVacation.getFromOriginFlight().getBabyTicketsAmount()));
                BabiesUpdate.setDisable(true);

                PriceUpdate.setText(Double.toString(currVacation.getPrice()));
                PriceUpdate.setDisable(false);

                BaggageUpdate.setValue(currVacation.getBaggageType());
                BaggageUpdate.setDisable(false);

                VacationTypeUpdate.setValue(currVacation.getVacationType());
                VacationTypeUpdate.setDisable(false);


                AirlineUpdate.setText(currVacation.getFromOriginFlight().getAirline());
                AirlineUpdate.setDisable(true);

                TransfersUpdate.setSelected(currVacation.isTransfers());
                TransfersUpdate.setDisable(false);

                updateButton.setDisable(false);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid vacation id.\nPlease try again!.");
            a.show();
        }
    }


    public void updateVacation() {
        int _vactionId;
        if (isInteger(VacationIDUpdate.getText())) {
            _vactionId = Integer.parseInt(VacationIDUpdate.getText());

            String _fromCountry = FromCountryUpdate.getText();
            String _fromCity = FromCityUpdate.getText();
            String _toCountry = ToCountyUpdate.getText();
            String _toCity = ToCityUpdate.getText();

            LocalDate _arrival = ArrivalUpdate.getValue();
            LocalDate _departure = DepartureUpdate.getValue();

            String _airline = AirlineUpdate.getText();
            String _accommodation = AccommodationUpdate.getValue().toString();
            String _accommodationRank = AccommodationRankUpdate.getValue().toString();
            String _flightClass = FlightClassUpdate.getValue().toString();


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
                _adults = Integer.parseInt(AdultsUpdate.getValue().toString());
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a valid adults amount.\nPlease fill this field.");
                a.show();
                return;
            }

            try {
                _children = Integer.parseInt(ChildrenUpdate.getValue().toString());
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a valid children amount.\nPlease fill this field.");
                a.show();
                return;
            }

            try {
                _babies = Integer.parseInt(BabiesUpdate.getValue().toString());
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a valid babies amount.\nPlease fill this field.");
                a.show();
                return;
            }


            double _price = 0;
            try {
                _price = Double.parseDouble(PriceUpdate.getText());
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a valid price.\nPlease fill this field.");
                a.show();
                return;
            }
            String _baggage = BaggageUpdate.getValue().toString();
            String _vacationType = VacationTypeUpdate.getValue().toString();

            boolean _transfers = TransfersUpdate.isSelected();

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

                int[] travelersType = new int[3];
                travelersType[0] = _babies;
                travelersType[1] = _children;
                travelersType[2] = _adults;

                FlightTickets originTicket = new FlightTickets(_airline, _toCountry, _toCity, _fromCountry, _fromCity, travelersType, _flightClass, _vactionId);
                FlightTickets destTicket = new FlightTickets(_airline, _fromCountry, _fromCity, _toCountry, _toCity, travelersType, _flightClass, _vactionId);
                //controller.updateFlightTickets(originTicket);
                //controller.updateFlightTickets(destTicket);
                Vacation newVacation = new Vacation(_vactionId, originTicket, destTicket, _fromCountry, _fromCity, _toCountry, _toCity,
                        _arrival.toString(), _departure.toString(), _price, _baggage, _vacationType, _accommodation,
                        true, _transfers, RANK, controller.getCurrentUserName());

                if (controller.updateVacation(newVacation)) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("The vacation updated successfully.");
                    a.show();
                } else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Error occurred! Please try again.");
                    a.show();
                    return;
                }
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Invalid vacation id! Please try again.");
            a.show();
        }
    }
}

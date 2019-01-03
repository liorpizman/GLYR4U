package View;

import Controller.Controller;
import Model.FlightTickets;
import Model.Vacation;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * This class represents Vacation CRUD Controller
 */
public class VacationCRUDController {

    /**
     * fields of VacationCRUDController
     */
    public javafx.scene.control.Button BackButton;
    protected static Controller controller;

    /**
     * Sets the static controller for all of the user windows controllers
     */
    public void setController(Controller _controller) {
        controller = _controller;
    }

    /**
     * Validation checks for the vacation id
     */
    public boolean validVacationID(String id) {
        if (id.isEmpty() || !isInteger(id)) {
            return false;
        }
        return true;
    }

    /**
     * Search the vacation data and if exist in the database display the data
     */
    public ArrayList<Vacation> searchVacationData(String vacationID) {
        return controller.searchVacationData(vacationID);
    }

    /**
     * This method go back to previous window
     */
    public void backHome() {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method to check if current string is integer
     * @param s string
     * @return if is integer or not
     */
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Publish Vacation Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * fields of VacationCRUDController
     */
    private static int VactionID = 200;
    public javafx.scene.control.Button PublishButtonP;
    public javafx.scene.control.TextField FromCountryP;
    public javafx.scene.control.TextField FromCityP;
    public javafx.scene.control.TextField ToCountryP;
    public javafx.scene.control.TextField ToCityP;
    public javafx.scene.control.TextField PriceP;

    public javafx.scene.control.DatePicker ArrivalP;
    public javafx.scene.control.DatePicker DepartureP;

    public javafx.scene.control.ChoiceBox AccommodationP;
    public javafx.scene.control.ChoiceBox AccommodationRankP;
    public javafx.scene.control.ChoiceBox FlightClassP;

    public javafx.scene.control.ChoiceBox AdultsP;
    public javafx.scene.control.ChoiceBox ChildrenP;
    public javafx.scene.control.ChoiceBox BabiesP;


    public javafx.scene.control.ChoiceBox BaggageP;
    public javafx.scene.control.ChoiceBox VacationTypeP;

    public javafx.scene.control.TextField AirlineP;

    public javafx.scene.control.CheckBox TransfersP;

    /**
     * Validation of inserted fields, sending vacation details to the controller
     */
    public void publishVacation() {
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = timeFormat.format(time);
        String currTime = formattedDate.replace(":", "");
        int _vacationId = Integer.parseInt(currTime);

        String _fromCountry = FromCountryP.getText().toLowerCase();
        String _fromCity = FromCityP.getText().toLowerCase();
        String _toCountry = ToCountryP.getText().toLowerCase();
        String _toCity = ToCityP.getText().toLowerCase();

        LocalDate _arrival = ArrivalP.getValue();
        LocalDate _departure = DepartureP.getValue();

        String _airline = AirlineP.getText();
        String _accommodation = AccommodationP.getValue().toString();
        String _accommodationRank = AccommodationRankP.getValue().toString();
        String _flightClass = FlightClassP.getValue().toString();


        LocalDate currentDate = LocalDate.now();
        LocalDate inputArrivalDate = ArrivalP.getValue();
        if (inputArrivalDate.compareTo(currentDate) < 0) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please Enter a valid future arrival date");
            a.show();
            return;
        }
        LocalDate inputDepartureDate = DepartureP.getValue();
        if (inputDepartureDate.compareTo(currentDate) < 0) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please Enter a valid future departure date");
            a.show();
            return;
        }
        if (inputDepartureDate.compareTo(inputArrivalDate) < 0) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Your departure date is before your arrival date");
            a.show();
            return;
        }

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
            _adults = Integer.parseInt(AdultsP.getValue().toString());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid adults amount.\nPlease fill this field.");
            a.show();
            return;
        }

        try {
            _children = Integer.parseInt(ChildrenP.getValue().toString());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid children amount.\nPlease fill this field.");
            a.show();
            return;
        }

        try {
            _babies = Integer.parseInt(BabiesP.getValue().toString());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid babies amount.\nPlease fill this field.");
            a.show();
            return;
        }

        double _price = 0;
        try {
            _price = Double.parseDouble(PriceP.getText());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid price.\nPlease fill this field.");
            a.show();
            return;
        }

        String _baggage = BaggageP.getValue().toString();
        String _vacationType = VacationTypeP.getValue().toString();

        boolean _transfers = TransfersP.isSelected();

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

            FlightTickets originTicket = new FlightTickets(_airline, _toCountry, _toCity, _fromCountry, _fromCity, travelersType, _flightClass, _vacationId);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            FlightTickets destTicket = new FlightTickets(_airline, _fromCountry, _fromCity, _toCountry, _toCity, travelersType, _flightClass, _vacationId);

            controller.insertFlightTickets(originTicket);
            controller.insertFlightTickets(destTicket);
            Vacation newVacation = new Vacation(_vacationId, originTicket, destTicket, _fromCountry, _fromCity, _toCountry, _toCity,
                    _arrival.toString(), _departure.toString(), _price, _baggage, _vacationType, _accommodation,
                    true, _transfers, RANK, controller.getCurrentUser().getUser_name());
            controller.insertVacation(newVacation);
            VacationsID.add(_vacationId);
            if ((controller.GetVacationsInformation(VacationsID)).size() != 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The vacation published successfully.\n" + "Your Vacation ID in the system is: " + _vacationId);
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Error occurred! Please try again.");
                a.show();
            }
        }
        controller.vacationsUpdate();
        backHome();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Delete Vacation Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * field of VacationCRUDController
     */
    public javafx.scene.control.TextField VacationIdDeleteD;

    /**
     * Method to delete vacation by id
     */
    public void deleteById() {
        if (validVacationID(VacationIdDeleteD.getText())) {
            ArrayList<Vacation> vacations = controller.searchVacationData(VacationIdDeleteD.getText());
            if (vacations.size() > 0) {
                if (controller.deleteVacationRecord(VacationIdDeleteD.getText())) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("The Vacation deleted successfully!");
                    a.show();
                } else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("You don't owned this vacation ID!");
                    a.show();
                }

            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a valid vacation id.\nPlease try again!");
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid vacation id.\nPlease try again!");
            a.show();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Update Vacation Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * fields of VacationCRUDController
     */
    public javafx.scene.control.TextField VacationIDUpdateU;
    public javafx.scene.control.TextField FromCountryUpdateU;
    public javafx.scene.control.TextField FromCityUpdateU;
    public javafx.scene.control.TextField ToCountyUpdateU;
    public javafx.scene.control.TextField ToCityUpdateU;
    public javafx.scene.control.DatePicker ArrivalUpdateU;
    public javafx.scene.control.DatePicker DepartureUpdateU;
    public javafx.scene.control.ChoiceBox AccommodationUpdateU;
    public javafx.scene.control.ChoiceBox AccommodationRankUpdateU;
    public javafx.scene.control.ChoiceBox FlightClassUpdateU;
    public javafx.scene.control.ChoiceBox AdultsUpdateU;
    public javafx.scene.control.ChoiceBox ChildrenUpdateU;
    public javafx.scene.control.ChoiceBox BabiesUpdateU;
    public javafx.scene.control.TextField PriceUpdateU;
    public javafx.scene.control.ChoiceBox BaggageUpdateU;
    public javafx.scene.control.ChoiceBox VacationTypeUpdateU;
    public javafx.scene.control.CheckBox TransfersUpdateU;
    public javafx.scene.control.TextField AirlineUpdateU;

    public javafx.scene.control.Button showUpdateButtonU;
    public javafx.scene.control.Button updateButtonU;

    /**
     * Shows relevant info for vacation id for updateU
     */
    public void showVacationUpdate() {
        if (validVacationID(VacationIDUpdateU.getText())) {
            ArrayList<Vacation> OneVacation = searchVacationData(VacationIDUpdateU.getText());
            if (OneVacation.size() < 1) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Invalid Vacation ID, please try again.");
                a.show();
                return;
            } else {
                Vacation currVacation = OneVacation.get(0);
                FromCountryUpdateU.setText(currVacation.getFromOriginFlight().getOriginCountry());
                FromCountryUpdateU.setDisable(true);

                FromCityUpdateU.setText(currVacation.getFromOriginFlight().getOriginCity());
                FromCityUpdateU.setDisable(true);

                if (currVacation.getFromDestFlight() != null) {
                    ToCountyUpdateU.setText(currVacation.getFromDestFlight().getOriginCountry());
                }
                ToCountyUpdateU.setDisable(true);

                if (currVacation.getFromDestFlight() != null) {
                    ToCityUpdateU.setText(currVacation.getFromDestFlight().getOriginCity());
                }
                ToCityUpdateU.setDisable(true);

                String[] StartDate = currVacation.getStartDate().split("-");
                LocalDate Sdate = LocalDate.of(Integer.parseInt(StartDate[0]), Integer.parseInt(StartDate[1]), Integer.parseInt(StartDate[2]));
                ArrivalUpdateU.setValue(Sdate);
                ArrivalUpdateU.setDisable(false);

                String[] EndDate = currVacation.getEndDate().split("-");
                LocalDate Edate = LocalDate.of(Integer.parseInt(EndDate[0]), Integer.parseInt(EndDate[1]), Integer.parseInt(EndDate[2]));
                DepartureUpdateU.setValue(Edate);
                DepartureUpdateU.setDisable(false);

                AccommodationUpdateU.setValue(currVacation.getAccommodationType());
                AccommodationUpdateU.setDisable(false);

                AccommodationRankUpdateU.setValue(Integer.toString(currVacation.getAccommodationRank()));
                AccommodationRankUpdateU.setDisable(false);

                FlightClassUpdateU.setValue(currVacation.getFromOriginFlight().getTicketType());
                FlightClassUpdateU.setDisable(true);

                AdultsUpdateU.setValue(Integer.toString(currVacation.getFromOriginFlight().getAdultTicketsAmount()));
                AdultsUpdateU.setDisable(true);

                ChildrenUpdateU.setValue(Integer.toString(currVacation.getFromOriginFlight().getChildTicketsAmount()));
                ChildrenUpdateU.setDisable(true);

                BabiesUpdateU.setValue(Integer.toString(currVacation.getFromOriginFlight().getBabyTicketsAmount()));
                BabiesUpdateU.setDisable(true);

                PriceUpdateU.setText(Double.toString(currVacation.getPrice()));
                PriceUpdateU.setDisable(false);

                BaggageUpdateU.setValue(currVacation.getBaggageType());
                BaggageUpdateU.setDisable(false);

                VacationTypeUpdateU.setValue(currVacation.getVacationType());
                VacationTypeUpdateU.setDisable(false);


                AirlineUpdateU.setText(currVacation.getFromOriginFlight().getAirline());
                AirlineUpdateU.setDisable(true);

                TransfersUpdateU.setSelected(currVacation.isTransfers());
                TransfersUpdateU.setDisable(false);

                updateButtonU.setDisable(false);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid vacation id.\nPlease try again!.");
            a.show();
            return;
        }
    }

    /**
     * method to update vacation by fields
     */
    public void updateVacation() {
        int _vactionId;
        if (isInteger(VacationIDUpdateU.getText())) {
            _vactionId = Integer.parseInt(VacationIDUpdateU.getText());

            String _fromCountry = FromCountryUpdateU.getText();
            String _fromCity = FromCityUpdateU.getText();
            String _toCountry = ToCountyUpdateU.getText();
            String _toCity = ToCityUpdateU.getText();

            LocalDate _arrival = ArrivalUpdateU.getValue();
            LocalDate _departure = DepartureUpdateU.getValue();

            String _airline = AirlineUpdateU.getText();
            String _accommodation = AccommodationUpdateU.getValue().toString();
            String _accommodationRank = AccommodationRankUpdateU.getValue().toString();
            String _flightClass = FlightClassUpdateU.getValue().toString();


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
                _adults = Integer.parseInt(AdultsUpdateU.getValue().toString());
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a valid adults amount.\nPlease fill this field.");
                a.show();
                return;
            }

            try {
                _children = Integer.parseInt(ChildrenUpdateU.getValue().toString());
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a valid children amount.\nPlease fill this field.");
                a.show();
                return;
            }

            try {
                _babies = Integer.parseInt(BabiesUpdateU.getValue().toString());
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a valid babies amount.\nPlease fill this field.");
                a.show();
                return;
            }


            double _price = 0;
            try {
                _price = Double.parseDouble(PriceUpdateU.getText());
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a valid price.\nPlease fill this field.");
                a.show();
                return;
            }
            String _baggage = BaggageUpdateU.getValue().toString();
            String _vacationType = VacationTypeUpdateU.getValue().toString();

            boolean _transfers = TransfersUpdateU.isSelected();

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
            } else if (_arrival == null) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a arrival date or you didn't fill arrival date at all, please try again.");
                a.show();
                return;
            } else if (_departure == null) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You didn't entered a departure date or you didn't fill your departure at all, please try again.");
                a.show();
                return;
            } else {
                LocalDate currentDate = LocalDate.now();
                LocalDate inputArrivalDate = _arrival;
                if (inputArrivalDate.compareTo(currentDate) < 0) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Please Enter a valid future arrival date");
                    a.show();
                    return;
                }
                LocalDate inputDepartureDate = _departure;
                if (inputDepartureDate.compareTo(currentDate) < 0) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Please Enter a valid future departure date");
                    a.show();
                    return;
                }
                if (inputDepartureDate.compareTo(inputArrivalDate) < 0) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Your departure date is before your arrival date");
                    a.show();
                    return;
                }
                ArrayList<Integer> VacationsID = new ArrayList<>();
                int[] travelersType = new int[3];
                travelersType[0] = _babies;
                travelersType[1] = _children;
                travelersType[2] = _adults;

                FlightTickets originTicket = new FlightTickets(_airline, _toCountry, _toCity, _fromCountry, _fromCity, travelersType, _flightClass, _vactionId);
                FlightTickets destTicket = new FlightTickets(_airline, _fromCountry, _fromCity, _toCountry, _toCity, travelersType, _flightClass, _vactionId);
                Vacation newVacation = new Vacation(_vactionId, originTicket, destTicket, _fromCountry, _fromCity, _toCountry, _toCity,
                        _arrival.toString(), _departure.toString(), _price, _baggage, _vacationType, _accommodation,
                        true, _transfers, RANK, controller.getCurrentUser().getUser_name());
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Read Vacation Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * fields of VacationCRUDController
     */
    public javafx.scene.control.TextField VacationIDRead;
    public javafx.scene.control.TextField FromCountryRead;
    public javafx.scene.control.TextField FromCityRead;
    public javafx.scene.control.TextField ToCountyRead;
    public javafx.scene.control.TextField ToCityRead;
    public javafx.scene.control.TextField ArrivalRead;
    public javafx.scene.control.TextField DepartureRead;
    public javafx.scene.control.TextField AccommodationRead;
    public javafx.scene.control.TextField AccommodationRankRead;
    public javafx.scene.control.TextField FlightClassRead;
    public javafx.scene.control.TextField AdultsRead;
    public javafx.scene.control.TextField ChildrenRead;
    public javafx.scene.control.TextField BabiesRead;
    public javafx.scene.control.TextField PriceRead;
    public javafx.scene.control.TextField BaggageRead;
    public javafx.scene.control.TextField VacationTypeRead;
    public javafx.scene.control.CheckBox TransfersRead;
    public javafx.scene.control.TextField AirlineRead;
    public javafx.scene.control.Button showReadButton;

    /**
     * Shows relevant info for vacation id
     */
    public void showVacationRead() {
        if (validVacationID(VacationIDRead.getText())) {
            ArrayList<Vacation> OneVacation = searchVacationData(VacationIDRead.getText());
            if (OneVacation.size() < 1) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Invalid Vacation ID, please try again.");
                a.show();
            } else {
                Vacation currVacation = OneVacation.get(0);
                FromCountryRead.setText(currVacation.getFromOriginFlight().getOriginCountry());
                FromCountryRead.setDisable(true);

                FromCityRead.setText(currVacation.getFromOriginFlight().getOriginCity());
                FromCityRead.setDisable(true);

                if (currVacation.getFromDestFlight() != null) {
                    ToCountyRead.setText(currVacation.getFromDestFlight().getOriginCountry());
                }
                ToCountyRead.setDisable(true);

                if (currVacation.getFromDestFlight() != null) {
                    ToCityRead.setText(currVacation.getFromDestFlight().getOriginCity());
                }
                ToCityRead.setDisable(true);

                ArrivalRead.setText(currVacation.getStartDate());
                ArrivalRead.setDisable(true);

                DepartureRead.setText(currVacation.getEndDate());
                DepartureRead.setDisable(true);

                AccommodationRead.setText(currVacation.getAccommodationType());
                AccommodationRead.setDisable(true);

                AccommodationRankRead.setText(Integer.toString(currVacation.getAccommodationRank()));
                AccommodationRankRead.setDisable(true);

                FlightClassRead.setText(currVacation.getFromOriginFlight().getTicketType());
                FlightClassRead.setDisable(true);

                AdultsRead.setText(Integer.toString(currVacation.getFromOriginFlight().getAdultTicketsAmount()));
                AdultsRead.setDisable(true);

                ChildrenRead.setText(Integer.toString(currVacation.getFromOriginFlight().getChildTicketsAmount()));
                ChildrenRead.setDisable(true);

                BabiesRead.setText(Integer.toString(currVacation.getFromOriginFlight().getBabyTicketsAmount()));
                BabiesRead.setDisable(true);

                PriceRead.setText(Double.toString(currVacation.getPrice()));
                PriceRead.setDisable(true);

                BaggageRead.setText(currVacation.getBaggageType());
                BaggageRead.setDisable(true);

                VacationTypeRead.setText(currVacation.getVacationType());
                VacationTypeRead.setDisable(true);

                AirlineRead.setText(currVacation.getFromOriginFlight().getAirline());
                AirlineRead.setDisable(true);

                TransfersRead.setSelected(currVacation.isTransfers());
                TransfersRead.setDisable(true);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid vacation id.\nPlease try again!.");
            a.show();
        }
    }
}

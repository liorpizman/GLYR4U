package View;

import Controller.Controller;
import Model.User;
import Model.Vacation;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class VacationController {

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

    public javafx.scene.control.Button BackButton;
    public javafx.scene.control.Button showUpdateButton;

    public javafx.scene.control.Button showReadButton;
    public javafx.scene.control.Button updateButton;

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
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Invalid Vacation ID, please try again.");
            a.show();
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
                FromCountryRead.setDisable(false);

                FromCityRead.setText(currVacation.getFromOriginFlight().getOriginCity());
                FromCityRead.setDisable(false);

                if (currVacation.getFromDestFlight() != null) {
                    ToCountyRead.setText(currVacation.getFromDestFlight().getOriginCountry());
                }
                ToCountyRead.setDisable(false);

                if (currVacation.getFromDestFlight() != null) {
                    ToCityRead.setText(currVacation.getFromDestFlight().getOriginCity());
                }
                ToCityRead.setDisable(false);

                ArrivalRead.setText(currVacation.getStartDate());
                ArrivalRead.setDisable(false);

                DepartureRead.setText(currVacation.getEndDate());
                DepartureRead.setDisable(false);

                AccommodationRead.setText(currVacation.getAccommodationType());
                AccommodationRead.setDisable(false);

                AccommodationRankRead.setText(Integer.toString(currVacation.getAccommodationRank()));
                AccommodationRankRead.setDisable(false);

                FlightClassRead.setText(currVacation.getFromOriginFlight().getTicketType());
                FlightClassRead.setDisable(false);

                AdultsRead.setText(Integer.toString(currVacation.getFromOriginFlight().getAdultTicketsAmount()));
                AdultsRead.setDisable(false);

                ChildrenRead.setText(Integer.toString(currVacation.getFromOriginFlight().getChildTicketsAmount()));
                ChildrenRead.setDisable(false);

                BabiesRead.setText(Integer.toString(currVacation.getFromOriginFlight().getBabyTicketsAmount()));
                BabiesRead.setDisable(false);

                PriceRead.setText(Double.toString(currVacation.getPrice()));
                PriceRead.setDisable(false);

                BaggageRead.setText(currVacation.getBaggageType());
                BaggageRead.setDisable(false);

                VacationTypeRead.setText(currVacation.getVacationType());
                VacationTypeRead.setDisable(false);


                TransfersRead.setSelected(currVacation.isTransfers());
                TransfersRead.setDisable(false);
            }
        }
    }

    public void backHome() {
        // back to home stage from the current window
        // close this window and change a stage/scene

        // get a handle to the stage
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

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
                FromCountryUpdate.setDisable(false);

                FromCityUpdate.setText(currVacation.getFromOriginFlight().getOriginCity());
                FromCityUpdate.setDisable(false);

                if (currVacation.getFromDestFlight() != null) {
                    ToCountyUpdate.setText(currVacation.getFromDestFlight().getOriginCountry());
                }
                ToCountyUpdate.setDisable(false);

                if (currVacation.getFromDestFlight() != null) {
                    ToCityUpdate.setText(currVacation.getFromDestFlight().getOriginCity());
                }
                ToCityUpdate.setDisable(false);

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
                FlightClassUpdate.setDisable(false);

                AdultsUpdate.setValue(Integer.toString(currVacation.getFromOriginFlight().getAdultTicketsAmount()));
                AdultsUpdate.setDisable(false);

                ChildrenUpdate.setValue(Integer.toString(currVacation.getFromOriginFlight().getChildTicketsAmount()));
                ChildrenUpdate.setDisable(false);

                BabiesUpdate.setValue(Integer.toString(currVacation.getFromOriginFlight().getBabyTicketsAmount()));
                BabiesUpdate.setDisable(false);

                PriceUpdate.setText(Double.toString(currVacation.getPrice()));
                PriceUpdate.setDisable(false);

                BaggageUpdate.setValue(currVacation.getBaggageType());
                BaggageUpdate.setDisable(false);

                VacationTypeUpdate.setValue(currVacation.getVacationType());
                VacationTypeUpdate.setDisable(false);


                TransfersUpdate.setSelected(currVacation.isTransfers());
                TransfersUpdate.setDisable(false);
            }
        }
    }


    public void updateVacation(){

    }
}

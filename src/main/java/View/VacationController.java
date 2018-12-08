package View;

import Model.User;
import Model.Vacation;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

public class VacationController extends ViewController {

    public javafx.scene.control.TextField VacationIDUpdate;
    public javafx.scene.control.TextField FromCountryUpdate;
    public javafx.scene.control.TextField FromCityUpdate;
    public javafx.scene.control.TextField ToCountyUpdate;
    public javafx.scene.control.TextField ToCityUpdate;
    public javafx.scene.control.TextField ArrivalUpdate;
    public javafx.scene.control.TextField DepartureUpdate;
    public javafx.scene.control.TextField AccommodationUpdate;
    public javafx.scene.control.TextField AccommodationRankUpdate;
    public javafx.scene.control.TextField FlightClassUpdate;
    public javafx.scene.control.TextField AdultsUpdate;
    public javafx.scene.control.TextField ChildernUpdate;
    public javafx.scene.control.TextField BabiesUpdate;
    public javafx.scene.control.TextField PriceUpdate;
    public javafx.scene.control.TextField BaggageUpdate;
    public javafx.scene.control.TextField VacationTypeUpdate;
    public javafx.scene.control.CheckBox TransfersUpdate;

    /**
     * Validation checks for the vacation id
     */
    public boolean validVacationID() {
        String id = VacationIDUpdate.getText();
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
    public void showVacation() {
        if (validVacationID()) {
            ArrayList<Vacation> OneVacation = searchVacationData(VacationIDUpdate.getText());
            Vacation currVacation = OneVacation.get(0);
            FromCountryUpdate.setText(currVacation.getFromOriginFlight().getOriginCountry());
            FromCountryUpdate.setDisable(false);

            FromCityUpdate.setText(currVacation.getFromOriginFlight().getOriginCity());
            FromCityUpdate.setDisable(false);

            ToCountyUpdate.setText(currVacation.getFromDestFlight().getOriginCountry());
            ToCountyUpdate.setDisable(false);

            ToCityUpdate.setText(currVacation.getFromDestFlight().getOriginCity());
            ToCityUpdate.setDisable(false);

            ArrivalUpdate.setText(currVacation.getStartDate());
            ArrivalUpdate.setDisable(false);

            DepartureUpdate.setText(currVacation.getEndDate());
            DepartureUpdate.setDisable(false);

            AccommodationUpdate.setText(currVacation.getAccommodationType());
            AccommodationUpdate.setDisable(false);

            AccommodationRankUpdate.setText(Integer.toString(currVacation.getAccommodationRank()));
            AccommodationRankUpdate.setDisable(false);

            FlightClassUpdate.setText(currVacation.getFromOriginFlight().getTicketType());
            FlightClassUpdate.setDisable(false);

            AdultsUpdate.setText(Integer.toString(currVacation.getFromOriginFlight().getAdultTicketsAmount()));
            AdultsUpdate.setDisable(false);

            ChildernUpdate.setText(Integer.toString(currVacation.getFromOriginFlight().getChildTicketsAmount()));
            ChildernUpdate.setDisable(false);

            BabiesUpdate.setText(Integer.toString(currVacation.getFromOriginFlight().getBabyTicketsAmount()));
            BabiesUpdate.setDisable(false);

            PriceUpdate.setText(Double.toString(currVacation.getPrice()));
            PriceUpdate.setDisable(false);

            BaggageUpdate.setText(currVacation.getBaggageType());
            BaggageUpdate.setDisable(false);

            VacationTypeUpdate.setText(currVacation.getVacationType());
            VacationTypeUpdate.setDisable(false);


            TransfersUpdate.setSelected(currVacation.isTransfers());
            TransfersUpdate.setDisable(false);


        }
    }


}

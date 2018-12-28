package View;

import Model.Vacation;
import javafx.scene.control.Alert;

import java.util.ArrayList;
/**
 * This class controls the Read Vacation Window
 */
public class ReadVacationController extends VacationController {
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
        }
        else{
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered a valid vacation id.\nPlease try again!.");
            a.show();
        }
    }

}

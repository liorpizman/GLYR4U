package View;

import Model.Vacation;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

public class UpdateVacationController extends VacationController {


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

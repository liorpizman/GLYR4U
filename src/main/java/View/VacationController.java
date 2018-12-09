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


}

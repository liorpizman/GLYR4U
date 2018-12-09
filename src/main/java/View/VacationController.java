package View;

import Controller.Controller;
import Model.User;
import Model.Vacation;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class VacationController {

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

package View;

import Controller.Controller;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ExchangeRequestController implements Initializable {

    public javafx.scene.control.Button BackButton;
    public javafx.scene.control.ComboBox<String> vacationsListBox;
    protected static Controller controller;

    /**
     * Sets the static controller for all of the user windows controllers
     */
    public void setController(Controller _controller) {
        controller = _controller;
    }

    /**
     * Opens mainWindow when the user press back button
     */
    public void backHome() {
        // back to home stage from the current window
        // close this window and change a stage/scene

        // get a handle to the stage
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> askedValues = new HashMap<>();
        askedValues.put("user_name", controller.getCurrentUser().getUser_name());
        ArrayList<Integer> vacationIDS = controller.GetVacationsIdByField(askedValues);
        ArrayList<Vacation> vacationsList = controller.GetVacationsInformation(vacationIDS);
        ArrayList<String> vacations_Info = new ArrayList<String>();
        for (Vacation vacation : vacationsList) {
            vacations_Info.add(vacation.toString());
        }
        ObservableList<String> data = FXCollections.observableArrayList(vacations_Info);
        vacationsListBox.setItems(data);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Exchange Request Controller */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public javafx.scene.control.Button ApplyExchangeButton;

    public void applyExchange() {

    }

}

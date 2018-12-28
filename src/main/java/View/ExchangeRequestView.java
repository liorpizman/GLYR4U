package View;

import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExchangeRequestView extends RequestView implements Initializable {

    public javafx.scene.control.Button ApplyExchangeButton;
    public javafx.scene.control.ComboBox<String> vacationsListBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Vacation> vacationsList = controller.getCurrentUserVacations();
        ArrayList<String> vacations_Info = new ArrayList<String>();
        for (Vacation vacation:vacationsList) {
        }
        ObservableList<String> data = FXCollections.observableArrayList(vacations_Info);
        vacationsListBox.setItems(data);

    }
    public void applyExchange() {

    }

}

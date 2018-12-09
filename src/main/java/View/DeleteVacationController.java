package View;

import Controller.Controller;
import Model.Vacation;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class DeleteVacationController extends VacationController {


    public javafx.scene.control.TextField VacationIdDelete;


    public void deleteById() {
        if (validVacationID(VacationIdDelete.getText())) {
            ArrayList<Vacation> vacations = controller.searchVacationData(VacationIdDelete.getText());
            if (vacations.size() > 0) {
                controller.deleteVacationRecord(VacationIdDelete.getText());
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The Vacation deleted successfully!");
                a.show();
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


}

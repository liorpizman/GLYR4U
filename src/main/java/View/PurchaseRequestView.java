package View;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PurchaseRequestView extends RequestView {

    public javafx.scene.control.Button applyPurchaseButton;
    public javafx.scene.control.TextField phoneNumber;
    public javafx.scene.control.TextArea message;


    public void applyPurchaseRequest() {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        String[] vacationDetails = stage.getTitle().split("\\s+");
        String userID = vacationDetails[4];
        String vacationID = vacationDetails[2];
        controller.createPurchaseRequest(phoneNumber.getText(),message.getText());
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("PurchaseRequest Sent!");
        a.show();
        stage.close();
    }


}

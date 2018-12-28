package View;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
/**
 * Class for handling payment events
 */
public class PaymentView extends ViewController {

    public javafx.scene.control.ChoiceBox PaymentMethodChoice;
    public javafx.scene.control.TextField CreditNumber;
    public javafx.scene.control.DatePicker PaymentDate;
    public javafx.scene.control.TextField AmountToPay;
    public javafx.scene.control.Button ConfirmPayment;
    public javafx.scene.control.Button showPrice;


    /**
     * Confirm payment for vacation, sending detailes to the payments table
     */
    public void ConfirmVacationPayment() {
        String _paymentMethodChoice = PaymentMethodChoice.getValue().toString();
        String _creditNumber = CreditNumber.getText();
        LocalDate _paymentDate = PaymentDate.getValue();

        if (!isInteger(_creditNumber)) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("The Credit Number you have entered is illegal.\nCredit Number contains only numbers.");
            a.show();
            return;
        }
        controller.insertNewPayment(controller.getCurrentUser().getUser_name(), _paymentMethodChoice,
                _creditNumber, _paymentDate.toString());
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Payment Successful!");
        a.show();
        Stage stage =(Stage) ConfirmPayment.getScene().getWindow();
        stage.close();

    }

    /**
     * Show the price of the vacation
     */
    public void showPrice(){
        AmountToPay.setText(Double.toString(controller.getCurrentPrice()));
    }
}

package View;

import javafx.scene.control.Alert;

import java.time.LocalDate;

public class PaymentController extends ViewController {

    public javafx.scene.control.ChoiceBox PaymentMethodChoice;
    public javafx.scene.control.TextField CreditNumber;
    public javafx.scene.control.DatePicker PaymentDate;
    public javafx.scene.control.TextField AmountToPay;
    public javafx.scene.control.Button ConfirmPayment;

    public void ConfirmVacationPayment() {
        String _paymentMethodChoice = PaymentMethodChoice.getValue().toString();
        String _creditNumber = CreditNumber.getText();
        LocalDate _paymentDate = PaymentDate.getValue();
        String _amountToPay = AmountToPay.getText();

        if (!isInteger(_creditNumber)) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("The Credit Number you have entered is illegal.\nCredit Number contains only numbers.");
            a.show();
            return;
        }

        controller.insertNewPayment( int VacationId, String Seller, controller.getCurrentUserName(), _paymentMethodChoice,
                _creditNumber, _paymentDate.toString())
    }


}

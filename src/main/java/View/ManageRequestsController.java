package View;

import Controller.Controller;
import Model.ExchangeRequest;
import Model.PurchaseRequest;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class represents Manage Requests Controller
 */
public class ManageRequestsController implements Initializable {

    /**
     * fields of ManageRequestsController
     */
    public javafx.scene.control.Button ApplyPurchaseButton;
    public javafx.scene.control.Button RefusePurchaseButton;
    public javafx.scene.control.Button ApplyExchangeButton;
    public javafx.scene.control.Button RefuseExchangeButton;
    public javafx.scene.control.Button BackButton;
    protected static Controller controller;

    public javafx.scene.control.ComboBox<String> purchaseListBox;
    public javafx.scene.control.ComboBox<String> exchangeListBox;

    public javafx.scene.control.ComboBox<String> approvedListBox;
    public javafx.scene.control.ComboBox<String> rejectedListBox;
    public javafx.scene.control.ComboBox<String> inProgressListBox;

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
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method calls an apply of the user on a request
     */
    public void applyPurchaseRequest() {
        String selected = purchaseListBox.getValue();
        if (selected == null || selected.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You must choose any purchase request");
            a.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to accept this request?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            return;
        }

        String VacationID = selected.split("VacationId:")[1].split("From")[0].trim();
        String buyer = selected.split("buyer:")[1].split(" , contact")[0].trim();
        controller.AcceptPurchaseRequest(Integer.parseInt(VacationID), buyer);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("This request has been approved!\nYour other requests for this vacation have been rejected.");
        a.show();
        controller.vacationsUpdate();
        backHome();
    }

    /**
     * Method calls a refuse of the user on a request
     */
    public void refusePurchaseRequest() {
        String selected = purchaseListBox.getValue();
        if (selected == null || selected.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You must choose any purchase request");
            a.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to reject this request?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            return;
        }

        String VacationID = selected.split("VacationId:")[1].split("From")[0].trim();
        String buyer = selected.split("buyer:")[1].split(" , contact")[0].trim();
        controller.RejectPurchaseRequest(Integer.parseInt(VacationID), buyer);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("The request has been rejected!");
        a.show();
        controller.vacationsUpdate();
        backHome();
    }

    /**
     * Method calls an apply of the user on a request
     */
    public void applyExchangeRequest() {
        String selected = exchangeListBox.getValue();
        if (selected == null || selected.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You must choose any exchange request");
            a.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to accept this request?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            return;
        }

        String SellerVacationID = selected.split("VacationId:")[1].split("From")[0].trim();
        String BuyerVacationID = selected.split("VacationId:")[2].split(" From")[0].trim();
        controller.AcceptExchangeRequest(Integer.parseInt(SellerVacationID), Integer.parseInt(BuyerVacationID));
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("This request has been approved!\nYour other requests for this vacation have been rejected.");
        a.show();
        controller.vacationsUpdate();
        backHome();
    }

    /**
     * Method calls a refuse of the user on a request
     */
    public void refuseExchangeRequest() {
        String selected = exchangeListBox.getValue();
        if (selected == null || selected.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You must choose any exchange request");
            a.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to reject this request?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            return;
        }

        String SellerVacationID = selected.split("VacationId:")[1].split("From")[0].trim();
        String BuyerVacationID = selected.split("VacationId:")[2].split(" From")[0].trim();
        controller.RejectExchangeRequest(Integer.parseInt(SellerVacationID), Integer.parseInt(BuyerVacationID));
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("The request has been rejected!");
        a.show();
        controller.vacationsUpdate();
        backHome();
    }

    /**
     * Method which happens when the window initialize
     * @param location location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdatePurchaseOffersForMe();
        UpdateExchangeOffersForMe();
        UpdateMyOffersApproved();
        UpdateMyOffersRejected();
        UpdateInProgress();
    }

    /**
     * Method which Updates Purchase Offers For User
     */
    private void UpdatePurchaseOffersForMe() {
        ArrayList<Integer> purchaseVacationIDS = controller.GetPurchaseRequestsForUser(0);
        ArrayList<PurchaseRequest> purchaseList = controller.GetPurchaseRequestInformation(purchaseVacationIDS);
        ArrayList<String> purchase_Info = new ArrayList<String>();
        Vacation currVacation;
        for (PurchaseRequest purchaseRequest : purchaseList) {
            currVacation = controller.GetVacationByVacationID(purchaseRequest.getVacationIdSeller());
            purchase_Info.add(currVacation.toString() + " " + purchaseRequest.toString());
        }
        ObservableList<String> purchaseData = FXCollections.observableArrayList(purchase_Info);
        purchaseListBox.setItems(purchaseData);
    }

    /**
     * Method which Updates Exchange Offers For User
     */
    private void UpdateExchangeOffersForMe() {
        ArrayList<Integer> exchangeVacationIDS = controller.GetExchangeRequestForUser(0);
        ArrayList<ExchangeRequest> exchangeList = controller.GetExchangeRequestInformation(exchangeVacationIDS);
        ArrayList<String> exchange_Info = new ArrayList<String>();
        Vacation sellerVacation, buyerVacation;
        String newLine = "";
        for (ExchangeRequest exchangeRequest : exchangeList) {
            sellerVacation = controller.GetVacationByVacationID(exchangeRequest.getVacationIdSeller());
            buyerVacation = controller.GetVacationByVacationID(exchangeRequest.getVacationIdBuyer());
            newLine = sellerVacation.toString() + " exchange to " + buyerVacation.toString() + exchangeRequest.toString();
            exchange_Info.add(newLine);
        }
        ObservableList<String> exchangeData = FXCollections.observableArrayList(exchange_Info);
        exchangeListBox.setItems(exchangeData);
    }

    /**
     * Method which Updates User's Offers which have been approved
     */
    private void UpdateMyOffersApproved() {
        ArrayList<Integer> approvePurchaseVacationIDS = controller.GetPurchaseRequestsForUser(1);
        ArrayList<PurchaseRequest> purchaseApproveList = controller.GetPurchaseRequestInformation(approvePurchaseVacationIDS);
        ArrayList<String> approve_Info = new ArrayList<String>();
        Vacation currentVacation;
        approve_Info.add("Purchase Requests: ");
        for (PurchaseRequest purchaseRequest : purchaseApproveList) {
            currentVacation = controller.GetVacationByVacationID(purchaseRequest.getVacationIdSeller());
            approve_Info.add("  " + currentVacation.toString() + " " + purchaseRequest.toStringFromSeller());
        }
        approve_Info.add("Exchange Requests: ");
        ArrayList<Integer> approveExchangeVacationIDS = controller.GetExchangeRequestForUser(1);
        ArrayList<ExchangeRequest> exchangeApproveList = controller.GetExchangeRequestInformation(approveExchangeVacationIDS);
        Vacation sellerVacation;
        String newLine = "";
        for (ExchangeRequest exchangeRequest : exchangeApproveList) {
            sellerVacation = controller.GetVacationByVacationID(exchangeRequest.getVacationIdSeller());
            newLine = sellerVacation.toString() + exchangeRequest.toStringFromSeller();
            approve_Info.add("  " + newLine);
        }
        ObservableList<String> approveData = FXCollections.observableArrayList(approve_Info);
        approvedListBox.setItems(approveData);
    }

    /**
     * Method which Updates User's Offers which have been rejected
     */
    private void UpdateMyOffersRejected() {
        ArrayList<Integer> rejectedPurchaseVacationIDS = controller.GetPurchaseRequestsForUser(2);
        ArrayList<PurchaseRequest> purchaseRejectedList = controller.GetPurchaseRequestInformation(rejectedPurchaseVacationIDS);
        ArrayList<String> rejected_Info = new ArrayList<String>();
        rejected_Info.add("Purchase Requests: ");
        Vacation currentVacation, sellerVacation;
        String newLine = "";
        for (PurchaseRequest purchaseRequest : purchaseRejectedList) {
            currentVacation = controller.GetVacationByVacationID(purchaseRequest.getVacationIdSeller());
            rejected_Info.add("  " + currentVacation.toString() + " " + purchaseRequest.toStringFromSeller());
        }
        rejected_Info.add("Exchange Requests: ");
        ArrayList<Integer> rejectExchangeVacationIDS = controller.GetExchangeRequestForUser(2);
        ArrayList<ExchangeRequest> exchangeRejectList = controller.GetExchangeRequestInformation(rejectExchangeVacationIDS);
        for (ExchangeRequest exchangeRequest : exchangeRejectList) {
            sellerVacation = controller.GetVacationByVacationID(exchangeRequest.getVacationIdSeller());
            newLine = sellerVacation.toString() + exchangeRequest.toStringFromSeller();
            rejected_Info.add("  " + newLine);
        }
        ObservableList<String> rejectedData = FXCollections.observableArrayList(rejected_Info);
        rejectedListBox.setItems(rejectedData);
    }

    /**
     * Method which Updates User's Offers which are in progress
     */
    private void UpdateInProgress() {
        ArrayList<Integer> inProgressPurchaseVacationIDS = controller.GetPurchaseRequestsForUser(3);
        ArrayList<PurchaseRequest> inProgressPurchaseList = controller.GetPurchaseRequestInformation(inProgressPurchaseVacationIDS);
        ArrayList<String> inProgress_Info = new ArrayList<String>();
        Vacation currentVacation;
        inProgress_Info.add("Purchase Requests: ");
        for (PurchaseRequest purchaseRequest : inProgressPurchaseList) {
            currentVacation = controller.GetVacationByVacationID(purchaseRequest.getVacationIdSeller());
            inProgress_Info.add("  " + currentVacation.toString() + " " + purchaseRequest.toStringFromSeller());
        }
        inProgress_Info.add("Exchange Requests: ");
        ArrayList<Integer> inProgressExchangeVacationIDS = controller.GetExchangeRequestForUser(3);
        ArrayList<ExchangeRequest> inProgressExchangeList = controller.GetExchangeRequestInformation(inProgressExchangeVacationIDS);
        String newLine = "";
        for (ExchangeRequest exchangeRequest : inProgressExchangeList) {
            currentVacation = controller.GetVacationByVacationID(exchangeRequest.getVacationIdSeller());
            newLine = currentVacation.toString() + exchangeRequest.toStringFromSeller();
            inProgress_Info.add("  " + newLine);
        }
        ObservableList<String> inProgressData = FXCollections.observableArrayList(inProgress_Info);
        inProgressListBox.setItems(inProgressData);
    }
}






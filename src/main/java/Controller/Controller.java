package Controller;
/**
 * This class represents a controller which is responsible for returning the response to a request.
 */

import Model.RegisteredUser;
import Model.Vacation;
import Model.FlightTickets;
import Model.Model;
import Model.PurchaseRequest;
import Model.ExchangeRequest;
import Model.AUser;
import View.View;
import javafx.scene.control.Alert;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private Model model;
    private View view;

    private String currentSeller;
    private String currentVacation;
    private double currentPrice = 0;

    /**
     * This is a default constructor to create the controller
     *
     * @param _model
     * @param _view
     */
    public Controller(Model _model, View _view) {
        model = _model;
        view = _view;

    }

    /**
     * This method returning the response to an updateU request
     *
     * @param updatedUser
     * @return
     */
    public boolean updateUser(RegisteredUser updatedUser) {
        return model.updateUser(updatedUser);
    }

    /**
     * This method returning the response to search request
     *
     * @param userName
     * @return
     */
    public RegisteredUser searchUserData(String userName) {
        return model.searchUserData(userName);
    }

    /**
     * This method returning the response to an insert of a user request;
     *
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     * @param userCity
     * @param date
     */
    public void insertUser(String userName, String password, String firstName, String lastName, String userCity,
                           LocalDate date) {
        model.insertUser(userName, password, firstName, lastName, userCity, date);
    }

    /**
     * This method returning the response to deleteD of a user request;
     *
     * @param userToDelete
     * @param password
     * @return
     */
    public boolean deleteUser(String userToDelete, String password) {
        return model.deleteUser(userToDelete, password);
    }

    /**
     * This method returning the response to an insert of a vacation request;
     *
     * @param newVacation
     */
    public void insertVacation(Vacation newVacation) {
        model.insertVacation(newVacation);
    }


    /**
     * This method returning the response to an insert of a flightTickets request;
     *
     * @param newFlightTickets
     */
    public void insertFlightTickets(FlightTickets newFlightTickets) {
        model.insertFlightTickets(newFlightTickets);
    }


    /**
     * This method returning the response if a current user exists in the DB
     *
     * @param userName
     * @param password
     */
    public boolean IsCorrectPassword(String userName, String password) {
        return model.IsCorrectPassword(userName, password);
    }


    /**
     * This method sets the current user which logged to the app
     *
     * @param currUser
     */
    public void setCurrentUserInSystem(RegisteredUser currUser) {
        model.setCurrentUser(currUser);
    }

    public RegisteredUser getCurrentUser() {
        return (RegisteredUser) model.getCurrentUser();
    }

    public ArrayList<Vacation> Search(HashMap<String, String> askedValues) {
        return model.Search(askedValues);
    }

    /**
     * When users applies for log in
     */
    public boolean logIn(String _userName, String _password) {
        if (_userName.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your user name,Please enter.");
            a.show();
            return false;
        } else if (_password.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your password, Please enter.");
            a.show();
            return false;
        } else {
            RegisteredUser user = searchUserData(_userName);
            if (user != null) {
                if (IsCorrectPassword(_userName, _password)) {
                    setCurrentUserInSystem(user);
                    return true;
                    //Model.CurrentUser = _userName;
                    //close current stage and move to LoggedUserWindow (MAYBE SHOULD EXIT FROM MAIN STAGE TOO)
                } else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("The user name or password is incorrect.\nPlease try again!");
                    a.show();
                    return false;
                }
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The user name or password is incorrect.\nPlease try again!");
                a.show();
            }
        }
        return false;
    }

    public boolean isUserConnected() {
        return model.getCurrentUser() != null;
    }

    /**
     * This method calls the DB function to get a list of all Vacations ID's that suitable to the user search
     *
     * @param askedValues
     */
    public ArrayList<Integer> GetVacationsIdByField(HashMap<String, String> askedValues) {
        return model.GetVacationsIdByField(askedValues);
    }

    /**
     * This method get a list of Vacations ID's and return a list of the suitable vacation objects
     *
     * @param VacationsID
     */
    public ArrayList<Vacation> GetVacationsInformation(ArrayList<Integer> VacationsID) {//,String FieldToFind){
        return model.GetVacationsInformation(VacationsID);
    }

    /**
     * Search the vacation data and if exist in the database display the data
     */
    public ArrayList<Vacation> searchVacationData(String vacationID) {
        ArrayList<Integer> tmpList = new ArrayList<>();
        tmpList.add(Integer.parseInt(vacationID));
        return model.GetVacationsInformation(tmpList);
    }

    /**
     * This method updates vacation
     *
     * @param newVacation
     * @return
     */
    public boolean updateVacation(Vacation newVacation) {
        return model.updateVacation(newVacation);
    }

    /**
     * This method deletes all the Vacations records in table that belong to user that was deleted and not sold yet.
     *
     * @param user
     */
    public void deleteVacationOfDeletedUser(RegisteredUser user) {
        model.deleteVacationOfDeletedUser(user);
    }

    /**
     * This method deletes a record from the Vacations table in the DB by the VacationId
     *
     * @param VacationIdToDelete
     */
    public boolean deleteVacationRecord(String VacationIdToDelete) {
        return model.deleteVacationRecord(VacationIdToDelete);
    }

    /**
     * Setters for current seller and vacation ids
     *
     * @param currrentSeller
     */

    public void setCurrrentSeller(String currrentSeller) {
        this.currentSeller = currrentSeller;
    }

    public void setCurrentVacation(String currentVacation) {
        this.currentVacation = currentVacation;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    /*
    public ArrayList<Vacation> getCurrentUserVacations() {
        return null;
    }
*/
    /*
    public void createPurchaseRequest(String text, String text1) {
    }
*/
    public void vacationAdded() {
        view.searchViewController.updateVacationsList();
    }


    public boolean insertNewPurchaseRequest(PurchaseRequest purchaseRequest) {
        return model.insertNewPurchaseRequest(purchaseRequest);
    }

    public ArrayList<Integer> GetPurchaseRequestsForUser() {
        return model.GetPurchaseRequestsForUser();
    }


    public ArrayList<PurchaseRequest> GetPurchaseRequestInformation(ArrayList<Integer> PurchaseRequestID) {
        return model.GetPurchaseRequestInformation(PurchaseRequestID);
    }

    public void AcceptPurchaseRequest(int VacationIdSeller) {
        model.AcceptPurchaseRequest(VacationIdSeller);
    }

    public void RejectPurchaseRequest(int VacationIdSeller) {
        model.RejectPurchaseRequest(VacationIdSeller);
    }

    public boolean insertNewExchangeRequest(ExchangeRequest exchangeRequest) {
        return model.insertNewExchangeRequest(exchangeRequest);
    }

    public ArrayList<Integer> GetExchangeRequestForUser() {
        return model.GetExchangeRequestForUser();
    }

    public ArrayList<ExchangeRequest> GetExchangeRequestInformation(ArrayList<Integer> ExchangeRequestID) {
        return model.GetExchangeRequestInformation(ExchangeRequestID);
    }

    public void AcceptExchangeRequest(int VacationIdSeller, int VacationIdBuyer) {
        model.AcceptExchangeRequest(VacationIdSeller, VacationIdBuyer);
    }

    public void RejectExchangeRequest(int VacationIdSeller, int VacationIdBuyer) {
        model.RejectExchangeRequest(VacationIdSeller,VacationIdBuyer);
    }

    public Vacation GetVacationByVacationID(int id){
        return model.GetVacationByVacationID(id);
    }
}

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
import View.View;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represent Controller of the system
 */
public class Controller {
    /**
     * fields of Controller
     */
    private Model model;
    private View view;

    private String currentSeller;
    private String currentVacation;
    private double currentPrice = 0;

    /**
     * This is a default constructor to create the controller
     *
     * @param _model model class
     * @param _view  view class
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
     * @return RegisteredUser
     */
    public RegisteredUser searchUserData(String userName) {
        return model.searchUserData(userName);
    }

    /**
     * This method returning the response to an insert of a user request;
     *
     * @param userName  user's name
     * @param password  user's password
     * @param firstName first name
     * @param lastName  last name
     * @param userCity  user's city
     * @param date      birthdate
     */
    public void insertUser(String userName, String password, String firstName, String lastName, String userCity,
                           LocalDate date) {
        model.insertUser(userName, password, firstName, lastName, userCity, date);
    }

    /**
     * This method returning the response to deleteD of a user request;
     *
     * @param userToDelete the user to delete
     * @param password     user's password
     * @return boolean
     */
    public boolean deleteUser(String userToDelete, String password) {
        return model.deleteUser(userToDelete, password);
    }

    /**
     * This method returning the response to an insert of a vacation request;
     *
     * @param newVacation new vacation
     */
    public void insertVacation(Vacation newVacation) {
        model.insertVacation(newVacation);
    }


    /**
     * This method returning the response to an insert of a flightTickets request;
     *
     * @param newFlightTickets new flight tickets
     */
    public void insertFlightTickets(FlightTickets newFlightTickets) {
        model.insertFlightTickets(newFlightTickets);
    }


    /**
     * This method returning the response if a current user exists in the DB
     *
     * @param userName user name
     * @param password user's password
     */
    public boolean IsCorrectPassword(String userName, String password) {
        return model.IsCorrectPassword(userName, password);
    }


    /**
     * This method sets the current user which logged to the app
     *
     * @param currUser current user
     */
    public void setCurrentUserInSystem(RegisteredUser currUser) {
        model.setCurrentUser(currUser);
    }

    /**
     * Method to get current user in system
     *
     * @return urrent user in system
     */
    public RegisteredUser getCurrentUser() {
        return (RegisteredUser) model.getCurrentUser();
    }

    /**
     * Method for search by values
     *
     * @param askedValues
     * @return
     */
    public ArrayList<Vacation> Search(HashMap<String, String> askedValues) {
        return model.Search(askedValues);
    }

    /**
     * When users applies for log in
     *
     * @param _userName user name
     * @param _password user's password
     * @return
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

    /**
     * Method to check whether user is connected
     *
     * @return whether user is connected
     */
    public boolean isUserConnected() {
        return model.getCurrentUser() != null;
    }

    /**
     * This method calls the DB function to get a list of all Vacations ID's that suitable to the user search
     *
     * @param askedValues values
     */
    public ArrayList<Integer> GetVacationsIdByField(HashMap<String, String> askedValues) {
        return model.GetVacationsIdByField(askedValues);
    }

    /**
     * This method get a list of Vacations ID's and return a list of the suitable vacation objects
     *
     * @param VacationsID id of vacation
     */
    public ArrayList<Vacation> GetVacationsInformation(ArrayList<Integer> VacationsID) {//,String FieldToFind){
        return model.GetVacationsInformation(VacationsID);
    }

    /**
     * Search the vacation data and if exist in the database display the data
     *
     * @param vacationID id of vacation
     * @return vacation data
     */
    public ArrayList<Vacation> searchVacationData(String vacationID) {
        ArrayList<Integer> tmpList = new ArrayList<>();
        tmpList.add(Integer.parseInt(vacationID));
        return model.GetVacationsInformation(tmpList);
    }

    /**
     * This method updates vacation
     *
     * @param newVacation updated vacation
     * @return updated vacation
     */
    public boolean updateVacation(Vacation newVacation) {
        return model.updateVacation(newVacation);
    }

    /**
     * This method deletes all the Vacations records in table that belong to user that was deleted and not sold yet.
     *
     * @param user user
     */
    public void deleteVacationOfDeletedUser(RegisteredUser user) {
        model.deleteVacationOfDeletedUser(user);
    }

    /**
     * This method deletes a record from the Vacations table in the DB by the VacationId
     *
     * @param VacationIdToDelete id of vacation
     */
    public boolean deleteVacationRecord(String VacationIdToDelete) {
        return model.deleteVacationRecord(VacationIdToDelete);
    }

    /**
     * Setters for currentSeller
     *
     * @param currentSeller
     */
    public void setCurrrentSeller(String currentSeller) {
        this.currentSeller = currentSeller;
    }

    /**
     * Setters for currentVacation
     *
     * @param currentVacation
     */
    public void setCurrentVacation(String currentVacation) {
        this.currentVacation = currentVacation;
    }

    /**
     * Setters for currentPrice
     *
     * @param currentPrice
     */
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * Getter for currentPrice
     *
     * @return currentPrice
     */
    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Method to update vacation in search
     */
    public void vacationsUpdate() {
        view.searchViewController.updateVacationsList();
    }

    /**
     * Method to insert new purchase request
     *
     *  pass purchase request info
     * @return if succeeded
     */
    public boolean insertNewPurchaseRequest(int vacationIdSeller, String seller,
                                            String paymentDate, String cellPhone) {
        return model.insertNewPurchaseRequest(vacationIdSeller, seller, paymentDate, cellPhone);
    }

    /**
     * Method to get all purchase requests for user
     *
     * @param processVacation current status
     * @return
     */
    public ArrayList<Integer> GetPurchaseRequestsForUser(int processVacation) {
        return model.GetPurchaseRequestsForUser(processVacation);
    }

    /**
     * Method to get purchase requests info
     *
     * @param PurchaseRequestID id of purchase request
     * @return list of purchase requests
     */
    public ArrayList<PurchaseRequest> GetPurchaseRequestInformation(ArrayList<Integer> PurchaseRequestID) {
        return model.GetPurchaseRequestInformation(PurchaseRequestID);
    }

    /**
     * Method to accept purchase request
     *
     * @param VacationIdSeller id of vacation of the seller
     * @param Buyer            buyer user name
     */
    public void AcceptPurchaseRequest(int VacationIdSeller, String Buyer) {
        model.AcceptPurchaseRequest(VacationIdSeller, Buyer);
    }

    /**
     * Method to reject purchase request
     *
     * @param VacationIdSeller id of vacation of the seller
     * @param buyer            buyer user name
     */
    public void RejectPurchaseRequest(int VacationIdSeller, String buyer) {
        model.RejectPurchaseRequest(VacationIdSeller, buyer);
    }

    /**
     * Method to insert new exchange request
     *
     * exchange request info
     * @return if succeeded
     */
    public boolean insertNewExchangeRequest(int vacationIdSeller, String seller, int vacationIdBuyer, String paymentDate, String cellPhone) {
        return model.insertNewExchangeRequest(vacationIdSeller, seller, vacationIdBuyer, paymentDate, cellPhone);
    }

    /**
     * Method to get all exchange requests for user
     *
     * @param processVacation current status
     * @return
     */
    public ArrayList<Integer> GetExchangeRequestForUser(int processVacation) {
        return model.GetExchangeRequestForUser(processVacation);
    }

    /**
     * Method to get exchange requests info
     *
     * @param ExchangeRequestID id of exchange request
     * @return list of exchange requests
     */
    public ArrayList<ExchangeRequest> GetExchangeRequestInformation(ArrayList<Integer> ExchangeRequestID) {
        return model.GetExchangeRequestInformation(ExchangeRequestID);
    }

    /**
     * Method to accept exchange request
     *
     * @param VacationIdSeller id of vacation of the seller
     * @param VacationIdBuyer  id of vacation of the buyer
     */
    public void AcceptExchangeRequest(int VacationIdSeller, int VacationIdBuyer) {
        model.AcceptExchangeRequest(VacationIdSeller, VacationIdBuyer);
    }

    /**
     * Method to reject purchase request
     *
     * @param VacationIdSeller id of vacation of the seller
     * @param VacationIdBuyer  id of vacation of the buyer
     */
    public void RejectExchangeRequest(int VacationIdSeller, int VacationIdBuyer) {
        model.RejectExchangeRequest(VacationIdSeller, VacationIdBuyer);
    }

    /**
     * Method to get vacation by id
     *
     * @param id of vacation
     * @return suitable vacation
     */
    public Vacation GetVacationByVacationID(int id) {
        return model.GetVacationByVacationID(id);
    }
}

package Controller;

/**
 * This class represents a controller which is responsible for returning the response to a request.
 */

import Model.User;
import Model.Vacation;
import Model.FlightTickets;
import Model.Model;
import View.View;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private Model model;
    private View view;

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
     * This method returning the response to an update request
     *
     * @param updatedUser
     * @return
     */
    public boolean updateUser(User updatedUser) {
        return model.updateUser(updatedUser);
    }

    /**
     * This method returning the response to search request
     *
     * @param userName
     * @return
     */
    public User searchUserData(String userName) {
        return model.searchUserData(userName);
    }

    /**
     * This method returning the response to an insert of a user request;
     *
     * @param newUser
     */
    public void insertUser(User newUser) {
        model.insertUser(newUser);
    }

    /**
     * This method returning the response to delete of a user request;
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
     * @param userName
     */
    public void setCurrentUserInSystem(String userName) {
        model.setCurrentUser(userName);
    }

    public String getCurrentUserName() {
        return model.CurrentUser;
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
            if (searchUserData(_userName) != null) {
                if (IsCorrectPassword(_userName, _password)) {
                    setCurrentUserInSystem(_userName);
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
     * This method deletes a record from the Vacations table in the DB by the VacationId
     *
     * @param VacationIdToDelete
     */
    public void deleteVacationRecord(String VacationIdToDelete) {
        model.deleteVacationRecord(VacationIdToDelete);
    }



    /**
     * This method get the details of a vacation purchase and update the DB payment table
     *
     * @param VacationId
     * @param Seller
     * @param Buyer
     * @param PaymentMethod
     * @param CreditNumber
     * @param PaymentDate
     */
    public void insertNewPayment(int VacationId, String Seller, String Buyer, String PaymentMethod,
                                 String CreditNumber, String PaymentDate) {
        model.insertNewPayment(VacationId, Seller, Buyer, PaymentMethod, CreditNumber, PaymentDate);
    }

}

package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class preforming the logic of the project
 */

public class Model {

    private DBManagement dbManagement;

    public String getCurrentUser() {
        return CurrentUser;
    }

    private String CurrentUser;  // the user that logged in the DB

    public void createNewDatabase(String fileName) {
        dbManagement.createNewDatabase(fileName);
    }

    /**
     * This is a default constructor of the model which holds the data base
     */
    public Model() {
        dbManagement = new DBManagement();
    }

    /**
     * This method calls the function of table's creation in the data base
     *
     * @param tableName
     */
    public void createNewTable(String tableName) {
        dbManagement.createNewTable(tableName);
    }

    /**
     * This method calls the function to insert new user
     *
     * @param newUser
     */
    public void insertUser(User newUser) {
        dbManagement.insertNewUser(newUser.getUser_name(), newUser.getPassword(), newUser.getFirst_name(),
                newUser.getLast_name(), newUser.getCity(), newUser.getDate());
    }

    /**
     * This method calls a function to update for each field which was changed
     *
     * @param updatedUser
     * @return
     */
    public boolean updateUser(User updatedUser) {
        int count = 0;
        if (dbManagement.updateUser(updatedUser.getUser_name(), "password", updatedUser.getPassword()))
            count++;
        if (dbManagement.updateUser(updatedUser.getUser_name(), "first_name", updatedUser.getFirst_name()))
            count++;
        if (dbManagement.updateUser(updatedUser.getUser_name(), "last_name", updatedUser.getLast_name()))
            count++;
        if (dbManagement.updateUser(updatedUser.getUser_name(), "city", updatedUser.getCity()))
            count++;
        if (dbManagement.updateUser(updatedUser.getUser_name(), "date", updatedUser.getDate()))
            count++;
        if (count > 0)
            return true;
        return false;
    }

    /**
     * This method calls a function to search for user's data
     *
     * @param userName
     * @return
     */
    public User searchUserData(String userName) {
        return dbManagement.find_User_Exists(userName);
    }

    /**
     * This method calls a function to delete a user
     *
     * @param userToDelete
     * @param password
     * @return
     */
    public boolean deleteUser(String userToDelete, String password) {
        if (dbManagement.confirmPassword(userToDelete, password)) {
            dbManagement.deleteRecord(userToDelete, "DELETE FROM Users WHERE user_name = ?");
            return true;
        } else {
            return false;
        }

    }

    /**
     * This method calls the function to insert new vacation
     *
     * @param newVacation
     */
    public void insertVacation(Vacation newVacation) {
        dbManagement.insertNewVacation(newVacation.getVactionId(), newVacation.getFromOriginFlightId(), newVacation.getFromDestFlightId(),
                newVacation.getDVacationCountry(), newVacation.getDVacationCity(), newVacation.getOVacationCountry(), newVacation.getOVacationCity(),
                newVacation.getStartDate(), newVacation.getEndDate(), newVacation.getPrice(), newVacation.getBaggageType(), newVacation.isHotVacation(),
                newVacation.getStatus(), newVacation.getVacationType(), newVacation.getAccommodationType(), newVacation.isAccommodationIncluded(),
                newVacation.getAccommodationRank(), newVacation.isTransfers(), CurrentUser);
    }

    /**
     * This method deletes a record from the Vacations table in the DB by the VacationId
     *
     * @param VacationIdToDelete
     */
    public void deleteVacationRecord(String VacationIdToDelete) {
        dbManagement.deleteVacationRecord(VacationIdToDelete);
    }
    /**
     * This method calls a function to update each field which was changed
     *
     * @param updatedVacation
     * @return True or False
     */
    public boolean updateVacation(Vacation updatedVacation) {
        int count = 0;
        String OriginFlightIdStr = (new StringBuilder(updatedVacation.getFromOriginFlightId())).toString();
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "OriginFlightId", OriginFlightIdStr))
            count++;
        String DestFlightIdStr = (new StringBuilder(updatedVacation.getFromOriginFlightId())).toString();
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "DestFlightId", DestFlightIdStr))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "DVacationCountry", updatedVacation.getDVacationCountry()))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "DVacationCity", updatedVacation.getDVacationCity()))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "OVacationCountry", updatedVacation.getOVacationCountry()))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "OVacationCity", updatedVacation.getOVacationCity()))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "StartDate", updatedVacation.getStartDate()))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "EndDate", updatedVacation.getEndDate()))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "Price", String.valueOf(updatedVacation.getPrice())))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "BaggageType", updatedVacation.getBaggageType()))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "VacationType", updatedVacation.getVacationType()))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "AccommodationType", updatedVacation.getAccommodationType()))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "AccommodationIncluded", String.valueOf(updatedVacation.isAccommodationIncluded())))
            count++;
        String AccommodationRankStr = (new StringBuilder(updatedVacation.getAccommodationRank())).toString();
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "AccommodationRank", AccommodationRankStr))
            count++;
        if (dbManagement.updateVacationRecord(updatedVacation.getVactionId(), "Transfers", String.valueOf(updatedVacation.isTransfers())))
            count++;
        if (count > 0)
            return true;
        return false;
    }

    /**
     * This method calls the function to insert new FlightTickets
     *
     * @param newFlightTickets
     */
    public void insertFlightTickets(FlightTickets newFlightTickets) {
        dbManagement.insertNewFlightTickets(newFlightTickets.getTicketId(), newFlightTickets.getAirline(), newFlightTickets.getDestinationCountry(),
                newFlightTickets.getDestinationCity(), newFlightTickets.getOriginCountry(), newFlightTickets.getOriginCity(),
                newFlightTickets.getBabyTicketsAmount(), newFlightTickets.getChildTicketsAmount(), newFlightTickets.getAdultTicketsAmount(),
                newFlightTickets.getTicketType(), newFlightTickets.getAmountOfTickets(), newFlightTickets.getVacationId());
    }

    /**
     * This method calls the DB function to get a list of all Vacations ID's that suitable to the user search
     *
     * @param askedValues
     */
    public ArrayList<Integer> GetVacationsIdByField(HashMap<String, String> askedValues) {
        return dbManagement.GetVacationsIdByField("Vacations", askedValues);
    }

    /**
     * This method get a list of Vacations ID's and return a list of the suitable vacation objects
     *
     * @param VacationsID
     */
    public ArrayList<Vacation> GetVacationsInformation(ArrayList<Integer> VacationsID) {//,String FieldToFind){
        return dbManagement.GetVacationsInformation(VacationsID);
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
        dbManagement.insertNewPayment(VacationId, Seller, Buyer, PaymentMethod, CreditNumber, PaymentDate);
    }

    public void UserLogIn(String UserName) {
        this.CurrentUser = UserName;
    }

    public void UserLogOut() {
        this.CurrentUser = null;
    }

    /**
     * This method calls the function which checks if a current user exists in the DB
     *
     * @param userName
     * @param password
     */
    public boolean IsCorrectPassword(String userName, String password) {
        return dbManagement.IsCorrectPassword(userName, password);
    }

    /**
     * This method sets the current user which logged to the app
     *
     * @param userName
     */
    public void setCurrentUser(String userName) {
        this.CurrentUser = userName;
    }
}
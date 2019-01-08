
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class preforming the logic of the project
 */

public class Model {
    /**
     * fields of Model
     */
    private DBManagement dbManagement;
    private AUser currentUser;  // the user that logged in the DB

    /**
     * Method to create new db
     *
     * @param fileName db name
     */
    public void createNewDatabase(String fileName) {
        dbManagement.createNewDatabase(fileName);
    }

    /**
     * This is a default constructor of the model which holds the data base
     */
    public Model() {
        currentUser = new UnRegisteredUser();
        dbManagement = new DBManagement();
    }

    /**
     * This method calls the function of table's creation in the data base
     *
     * @param tableName new table name
     */
    public void createNewTable(String tableName) {
        dbManagement.createNewTable(tableName);
    }

    /**
     * Method to get current user
     *
     * @return current user
     */
    public RegisteredUser getCurrentUser() {
        if (this.currentUser instanceof UnRegisteredUser)
            return null;
        return (RegisteredUser) currentUser;
    }

    /**
     * This method calls the function to insert new user
     *
     * @param userName  user name
     * @param password  password of user
     * @param firstName first name
     * @param lastName  last name
     * @param userCity  city
     * @param date      birthdate
     */
    public void insertUser(String userName, String password, String firstName, String lastName, String userCity,
                           LocalDate date) {

        RegisteredUser newUser = new RegisteredUser(userName, password, firstName, lastName, userCity, date.toString());
        dbManagement.insertNewUser(newUser);
    }

    /**
     * This method calls a function to updateU for each field which was changed
     *
     * @param updatedUser updated user
     * @return if succeeded
     */
    public boolean updateUser(RegisteredUser updatedUser) {
        RegisteredUser user = (RegisteredUser)currentUser;
        user.setPassword(updatedUser.getPassword());
        user.setFirst_name(updatedUser.getFirst_name());
        user.setLast_name(updatedUser.getLast_name());
        user.setCity(updatedUser.getCity());
        user.setDate(updatedUser.getDate());
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
     * @param userName user name
     * @return RegisteredUser
     */
    public RegisteredUser searchUserData(String userName) {
        HashMap<String, String> askedFields = new HashMap<String, String>();
        askedFields.put("user_name",userName);
        ArrayList<Vacation> vacations = this.currentUser.Search(askedFields, dbManagement);
        HashMap<Integer,Vacation> vacations_dict = new HashMap<>();
        for (Vacation vacation:vacations) {
            vacations_dict.put(vacation.getVactionId(),vacation);
        }
        RegisteredUser user = dbManagement.findExistsUser(userName);
        if (user!=null)
            user.setVacations(vacations_dict);
        return user;
    }

    /**
     * This method calls a function to deleteD a user
     *
     * @param userToDelete user to delete
     * @param password     password of user
     * @return if succeeded
     */
    public boolean deleteUser(String userToDelete, String password) {
        RegisteredUser currUser = (RegisteredUser) currentUser;
        if (dbManagement.confirmPassword(currUser, password)) {
            dbManagement.deleteRecord(userToDelete, "DELETE FROM Users WHERE user_name = ?");
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method calls the function to insert new vacation
     *
     * @param newVacation new vacation
     */
    public void insertVacation(Vacation newVacation) {
        ((RegisteredUser)currentUser).publishVacation(newVacation);
        dbManagement.insertNewVacation(newVacation.getVactionId(), newVacation.getFromOriginFlightId(), newVacation.getFromDestFlightId(),
                newVacation.getDVacationCountry(), newVacation.getDVacationCity(), newVacation.getOVacationCountry(), newVacation.getOVacationCity(),
                newVacation.getStartDate(), newVacation.getEndDate(), newVacation.getPrice(), newVacation.getBaggageType(), newVacation.isHotVacation(),
                newVacation.getStatus(), newVacation.getVacationType(), newVacation.getAccommodationType(), newVacation.isAccommodationIncluded(),
                newVacation.getAccommodationRank(), newVacation.isTransfers(), (RegisteredUser) currentUser);
    }

    /**
     * This method deletes a record from the Vacations table in the DB by the VacationId
     *
     * @param VacationIdToDelete id of vacation
     */
    public boolean deleteVacationRecord(String VacationIdToDelete) {
        ((RegisteredUser)currentUser).deleteVacation(VacationIdToDelete);

        return dbManagement.deleteVacationRecord(VacationIdToDelete, ((RegisteredUser) currentUser).getUser_name());
    }

    /**
     * This method deletes all the Vacations records in table that belong to user that was deleted and not sold yet.
     *
     * @param user curr user
     */
    public void deleteVacationOfDeletedUser(RegisteredUser user) {
        dbManagement.deleteVacationOfDeletedUser(user);
    }

    /**
     * This method calls a function to updateU each field which was changed
     *
     * @param updatedVacation updated vacation
     * @return True or False
     */
    public boolean updateVacation(Vacation updatedVacation) {
        int count = 0;
        ((RegisteredUser)currentUser).updateVacation(updatedVacation);
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
     * @param newFlightTickets the new flight tickets to insert
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
     * @param askedValues values
     */
    public ArrayList<Integer> GetVacationsIdByField(HashMap<String, String> askedValues) {
        return dbManagement.GetVacationsIdByField("Vacations", askedValues);
    }

    /**
     * This method get a list of Vacations ID's and return a list of the suitable vacation objects
     *
     * @param VacationsID list of ids
     */
    public ArrayList<Vacation> GetVacationsInformation(ArrayList<Integer> VacationsID) {//,String FieldToFind){
        return dbManagement.GetVacationsInformation(VacationsID);
    }

    /**
     * Method to search by the user
     *
     * @param askedValues values
     * @return all vacations
     */
    public ArrayList<Vacation> Search(HashMap<String, String> askedValues) {
        return this.currentUser.Search(askedValues, dbManagement);
    }

    /**
     * This method calls the function which checks if a current user exists in the DB
     *
     * @param userName user name
     * @param password password of user
     */
    public boolean IsCorrectPassword(String userName, String password) {
        return dbManagement.IsCorrectPassword(userName, password);
    }

    /**
     * This method sets the current user which logged to the app
     *
     * @param currUser current user to set
     */
    public void setCurrentUser(RegisteredUser currUser) {
        if (currUser == null)
            currentUser = new UnRegisteredUser();
        else
            currentUser = currUser;
    }

    public Vacation getUserVacation(String vID){
        return ((RegisteredUser)currentUser).ReadVacation(vID);
    }


    /**
     * calls db to insert new purchase request
     *
     * @return if succeeded
     * @params purchaseRequest det new purchase request
     */
    public boolean insertNewPurchaseRequest(int vacationIdSeller, String seller,
                                            String paymentDate, String cellPhone) {
        PurchaseRequest purchaseRequest = ((RegisteredUser) this.currentUser).RequestForPurchase(vacationIdSeller, seller, paymentDate, cellPhone);
        return dbManagement.insertNewPurchaseRequest(purchaseRequest);
    }

    /**
     * calls db to get all ids of vacation in current status of this user
     *
     * @param processVacation current status
     * @return all ids of vacation in current status of this user
     */
    public ArrayList<Integer> GetPurchaseRequestsForUser(int processVacation) {
        return dbManagement.GetPurchaseRequestForUser(((RegisteredUser) currentUser).getUser_name(), processVacation);
    }

    /**
     * calls db to get all purchase requests of current ids
     *
     * @param purchaseRequestID list of ids
     * @return all purchase requests of current ids
     */
    public ArrayList<PurchaseRequest> GetPurchaseRequestInformation(ArrayList<Integer> purchaseRequestID) {
        return dbManagement.GetPurchaseRequestInformation(purchaseRequestID);
    }

    /**
     * calls db to accept current purchase request
     *
     * @param vacationIdSeller id of vacation
     * @param buyer            user name
     */
    public void AcceptPurchaseRequest(int vacationIdSeller, String buyer) {
        ((RegisteredUser) currentUser).acceptPurchaseRequest(vacationIdSeller);
        dbManagement.AcceptPurchaseRequest(vacationIdSeller, buyer);
    }

    /**
     * calls db to reject current purchase request
     *
     * @param vacationIdSeller id of vacation
     * @param buyer            user name
     */
    public void RejectPurchaseRequest(int vacationIdSeller, String buyer) {
        dbManagement.RejectPurchaseRequest(vacationIdSeller, buyer);
    }

    /**
     * calls db to insert new exchange request
     *
     * @return if succeeded
     */
    public boolean insertNewExchangeRequest(int vacationIdSeller, String seller, int vacationIdBuyer, String paymentDate, String cellPhone) {
        ExchangeRequest exchangeRequest = ((RegisteredUser)currentUser).RequestForExchange( vacationIdSeller,  seller,  vacationIdBuyer,  paymentDate,  cellPhone);
        return dbManagement.insertNewExchangeRequest(exchangeRequest);
    }

    /**
     * calls db to get all ids of vacation for current user of this status
     *
     * @param processVacation current status
     * @return all ids of vacation for current user of this status
     */
    public ArrayList<Integer> GetExchangeRequestForUser(int processVacation) {
        return dbManagement.GetExchangeRequestForUser(((RegisteredUser) currentUser).getUser_name(), processVacation);
    }

    /**
     * calls db to get all exchange requests for current ids
     *
     * @param exchangeRequestID list of ids
     * @return all exchange requests for current ids
     */
    public ArrayList<ExchangeRequest> GetExchangeRequestInformation(ArrayList<Integer> exchangeRequestID) {
        return dbManagement.GetExchangeRequestInformation(exchangeRequestID);
    }

    /**
     * calls db to accept exchange request
     *
     * @param vacationIdSeller id of vacation
     * @param vacationIdBuyer  id of vacation
     */
    public void AcceptExchangeRequest(int vacationIdSeller, int vacationIdBuyer) {
        ((RegisteredUser) currentUser).acceptExchangeRequest(vacationIdSeller);
        dbManagement.AcceptExchangeRequest(vacationIdSeller, vacationIdBuyer);
    }

    /***
     * calls db to reject exchange request
     * @param vacationIdSeller id of vacation
     * @param vacationIdBuyer id of vacation
     */
    public void RejectExchangeRequest(int vacationIdSeller, int vacationIdBuyer) {
        dbManagement.RejectExchangeRequest(vacationIdSeller, vacationIdBuyer);
    }

    /**
     * calls db to get vacation by id
     *
     * @param id of vacation
     * @return vacation
     */
    public Vacation GetVacationByVacationID(int id) {
        return dbManagement.GetVacationByVacationID(id);
    }

}

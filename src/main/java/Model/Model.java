package Model;

/**
 * This class preforming the logic of the project
 */

public class Model {

    private DBManagement dbManagement;
    private String CurrentUser;  // the user that logged in the DB

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
                newVacation.getVacationCountry(), newVacation.getVacationCity(),
                newVacation.getStartDate(), newVacation.getEndDate(), newVacation.getPrice(), newVacation.getBaggageType(), newVacation.isHotVacation(),
                newVacation.getStatus(), newVacation.getVacationType(), newVacation.getAccommodationType(), newVacation.isAccommodationIncluded(),
                newVacation.getAccommodationRank(), newVacation.isParking(), CurrentUser);
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

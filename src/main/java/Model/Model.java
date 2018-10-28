package Model;

/**
 * This class preforming the logic of the project
 */

public class Model {

    private DBManagement dbManagement;

    /**
     * This is a default constructor of the model which holds the data base
     */
    public Model() {
        dbManagement = new DBManagement();
    }

    /**
     * This method calls the function of table's creation in the data base
     * @param tableName
     */
    public void createNewTable(String tableName) {
        dbManagement.createNewTable(tableName);
    }

    /**
     * This method calls the function to insert new user
     * @param newUser
     */
    public void insertUser(User newUser) {
        dbManagement.insert(newUser.getUser_name(), newUser.getPassword(), newUser.getFirst_name(),
                newUser.getLast_name(), newUser.getCity(), newUser.getDate());
    }

    /**
     * This method calls a function to update for each field which was changed
     * @param updatedUser
     * @return
     */
    public boolean updateUser(User updatedUser) {
        int count = 0;
        if (dbManagement.update(updatedUser.getUser_name(), "password", updatedUser.getPassword()))
            count++;
        if (dbManagement.update(updatedUser.getUser_name(), "first_name", updatedUser.getFirst_name()))
            count++;
        if (dbManagement.update(updatedUser.getUser_name(), "last_name", updatedUser.getLast_name()))
            count++;
        if (dbManagement.update(updatedUser.getUser_name(), "city", updatedUser.getCity()))
            count++;
        if (dbManagement.update(updatedUser.getUser_name(), "date", updatedUser.getDate()))
            count++;
        if (count > 0)
            return true;
        return false;
    }

    /**
     * This method calls a function to search for user's data
     * @param userName
     * @return
     */
    public User searchUserData(String userName) {
        return dbManagement.find_User_Exists(userName);
    }

    /**
     * This method calls a function to delete a user
     * @param userToDelete
     * @param password
     * @return
     */
    public boolean deleteUser(String userToDelete, String password) {
        if (dbManagement.confirmPassword(userToDelete, password)) {
            dbManagement.delete(userToDelete);
            return true;
        } else {
            return false;
        }

    }
}

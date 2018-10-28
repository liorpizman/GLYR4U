package Model;

public class Model {

    private DBManagement dbManagement;

    public Model() {
        dbManagement = new DBManagement();
    }

    public void createNewTable(String tableName) {
        dbManagement.createNewTable(tableName);
    }

    public void insertUser(User newUser) {
        dbManagement.insert(newUser.getUser_name(), newUser.getPassword(), newUser.getFirst_name(),
                newUser.getLast_name(), newUser.getCity(), newUser.getDate());
    }

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

    public User searchUserData(String userName) {
        return dbManagement.find_User_Exists(userName);
    }

    public boolean deleteUser(String userToDelete, String password) {
        if (dbManagement.confirmPassword(userToDelete, password)) {
            dbManagement.delete(userToDelete);
            return true;
        } else {
            return false;
        }

    }
}

package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents any user
 */
public abstract class AUser {

    /**
     * Method to search vacations
     * @return ArrayList of Vacations
     */
    public ArrayList<Vacation> searchVacation() {
        ArrayList<Vacation> searchedVacations = null;
        return searchedVacations;
    }

    /**
     *  Method for searching by the user
     * @param askedValues the values to search
     * @param dbManagement db to search in
     * @return ArrayList of Vacations
     */
    public ArrayList<Vacation> Search(HashMap<String, String> askedValues, DBManagement dbManagement) {
        ArrayList<Integer> vacationIntIDs = dbManagement.GetVacationsIdByField("Vacations", askedValues);
        String[] vacationIDs = vacationIDs = new String[vacationIntIDs.size()];
        ArrayList<Vacation> vacationsList;
        for (int i = 0; i < vacationIntIDs.size(); i++) {
            vacationIDs[i] = vacationIntIDs.get(i).toString();
        }
        ArrayList<Integer> removeKeys = new ArrayList<>();
        vacationsList = dbManagement.GetVacationsInformation(vacationIntIDs);
        return vacationsList;
    }

    /**
     * Method to create new account
     * @return new account
     */
    public RegisteredUser createAcount() { // Ron : I think only UnRegisteredUser should have this !
        RegisteredUser a = null;
        return a;
    }

}

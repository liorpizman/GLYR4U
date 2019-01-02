package Model;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AUser {

    public ArrayList<Vacation> searchVacation() {
        ArrayList<Vacation> searchedVacations = null;
        return searchedVacations;
    }

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

    // Ron : I think only UnRegisteredUser should have this !
    public RegisteredUser createAcount() {
        RegisteredUser a = null;
        return a;
    }

}

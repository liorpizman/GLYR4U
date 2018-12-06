package Controller;

/**
 * This class represents a controller which is responsible for returning the response to a request.
 */

import Model.User;
import Model.Vacation;
import Model.FlightTickets;
import Model.Model;
import View.View;

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


}

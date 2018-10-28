package Controller;

import Model.User;
import Model.Model;
import View.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model _model, View _view) {
        model = _model;
        view = _view;
    }

    public boolean updateUser(User updatedUser) {
        return model.updateUser(updatedUser);
    }

    public User searchUserData(String userName) {
        return model.searchUserData(userName);
    }

    public void insertUser(User newUser) {
        model.insertUser(newUser);
    }

    public boolean deleteUser(String userToDelete, String password) {
        return model.deleteUser(userToDelete, password);
    }
}

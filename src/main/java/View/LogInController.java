package View;

import javafx.scene.control.Alert;

public class LogInController extends UserController  {

    public javafx.scene.control.TextField userName;
    public javafx.scene.control.TextField userPassword;

    /**
     * When users applies for log in
     */
    public void logIn() {
        String _userName = userName.getText();
        String _password = userPassword.getText();

        if (_userName.isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your user name, please entered.");
            a.show();
        }
        else if (_password.isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your password, please entered.");
            a.show();
        }
        else{
            if (searchUserData(_userName) != null)
            {
                //check that user's name and password are in the db and connect the user to the app
                //Model.CurrentUser = _userName;
            }
        }
    }

}

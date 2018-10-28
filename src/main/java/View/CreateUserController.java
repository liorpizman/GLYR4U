package View;
import Model.User;
import javafx.scene.control.Alert;

import java.time.LocalDate;

public class CreateUserController extends  UserController{
    public javafx.scene.control.TextField userName;
    public javafx.scene.control.TextField userPassword;
    public javafx.scene.control.TextField userFirstName;
    public javafx.scene.control.TextField userLastName;
    public javafx.scene.control.TextField userCity;
    public javafx.scene.control.DatePicker userBirthDate;
    public javafx.scene.control.Button apply;

    public void apply(){
        String _userName = userName.getText();
        String _password = userPassword.getText();
        String _firstName = userFirstName.getText();
        String _lastName = userLastName.getText();
        String _userCity = userCity.getText();
        LocalDate date = userBirthDate.getValue();

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
        else if (_firstName.isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your first name, please entered.");
            a.show();
        }
        else if (_lastName.isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your last name, please entered.");
            a.show();
        }
        else if (_userCity.isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your city, please entered.");
            a.show();
        }
        else if (date == null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your birth date, please entered.");
            a.show();
        }
        else
        {
            if (searchUserData(_userName) != null)
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("This user already exist in the system.");
                a.show();
            }
            else
            {
                User newUser = new User(_userName,_password,_firstName,_lastName,_userCity,date.toString());
                controller.insertUser(newUser);
                if (searchUserData(_userName) != null)
                {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("The user created successfully.");
                    a.show();
                }
                else
                {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Error occurred! Please try again.");
                    a.show();
                }
            }
            clearUserData();
        }
    }

    public void invalidUserDataError(String errorMsg){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(errorMsg);
        a.show();
    }

}

package View;

import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.*;
import java.time.LocalDate;

public class UpdateUserController extends UserController {
    public javafx.scene.control.Button update;

    public void show() {
        super.show();
        if (invalidUserName()) {
            update.setDisable(false);
        }
    }

    public void updateUserData() {
        String password = userPassword.getText();
        String firstName = userFirstName.getText();
        String lastName = userLastName.getText();
        String city = userCity.getText();
        String birthDate = userBirthDate.getValue().toString();
        if (password.isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your password, please entered.");
            a.show();
        }
        else if (firstName.isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your first name, please entered.");
            a.show();
        }
        else if (lastName.isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your last name, please entered.");
            a.show();
        }
        else if (city.isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your city, please entered.");
            a.show();
        }
        else if (birthDate == null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You didn't entered your birth date, please entered.");
            a.show();
        }
        else
        {
            User updatedUser = new User(currentUser.getUser_name(), password, firstName, lastName,city , birthDate);
            if (controller.updateUser(updatedUser))
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The user updated successfully.");
                a.show();
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Error occurred! Please try again.");
                a.show();
            }
            clearUserData();
            update.setDisable(true);
        }
    }
}
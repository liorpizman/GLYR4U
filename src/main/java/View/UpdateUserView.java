package View;
/**
 * This class controls the Update User Window
 */
import Model.User;
import javafx.scene.control.Alert;

public class UpdateUserView extends ViewController {
    public javafx.scene.control.Button update;

    /**
     * Implementing show function in order to manipulate the update button
     */
    @Override
    public void show() {
        super.show();
        userName.setDisable(true);
        update.setDisable(false);
    }

    /**
     * Gets the data that the user input's in order to update user,
     * alerts if data wasn't insert correctly
     */
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
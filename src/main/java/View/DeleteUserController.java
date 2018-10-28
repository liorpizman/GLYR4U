package View;

import javafx.scene.control.Alert;

public class DeleteUserController extends UserController {
    public javafx.scene.control.TextField userName;
    public javafx.scene.control.TextField userPassword;
    public javafx.scene.control.Button delete;
    public javafx.scene.control.Button clear;


    public void deleteUser() {
        if (searchUserData(userName.getText()) != null)
        {
            if (controller.deleteUser(userName.getText(),userPassword.getText())){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The user is deleted.");
                a.show();
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The password is wrong! Please try again.");
                a.show();
            }
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You entered user that not exist, please enter again");
            a.show();
            return;
        }
        return;
    }

    // clear all fields from user data, to start new search
    public void clearUserData() {
        userName.clear();
        userPassword.clear();
    }
}

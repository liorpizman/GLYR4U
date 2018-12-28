package View;
/**
 * This class controls the delete user window
 */

import javafx.scene.control.Alert;

public class DeleteUserView extends ViewController {
    public javafx.scene.control.TextField userPassword;
    public javafx.scene.control.Button delete;

    /**
     * This method controls the delete of the user and shows a suitable alert message
     * for each state
     */
    public void deleteUser() {
        if (controller.deleteUser(controller.getCurrentUser().getUser_name(), userPassword.getText())) {
            controller.deleteVacationOfDeletedUser(controller.getCurrentUser());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("The user is deleted.");
            a.show();
            controller.setCurrentUserInSystem(null);
            backHome();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("The password is wrong! Please try again.");
            a.show();
        }

    }


    /**
     * This method clears all fields from user data, to start new search
     */
    public void clearUserData() {
        userPassword.clear();
    }
}

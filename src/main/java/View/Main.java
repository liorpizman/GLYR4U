package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Model;

public class Main extends Application {

    // SQL statement for creating a Users table
    private String UsersSql = "CREATE TABLE IF NOT EXISTS " + "Users" + " (\n"
            + " user_name varchar(10) NOT NULL PRIMARY KEY,\n"
            + " password varchar(10) NOT NULL,\n"
            + " first_name varchar(10) NOT NULL,\n "
            + " last_name varchar(10) NOT NULL,\n "
            + " city varchar(10) NOT NULL,\n "
            + " date varchar(15) NOT NULL\n "
            + ");";

    // SQL statement for creating a Vacations table
    private String VacationsSql = "CREATE TABLE IF NOT EXISTS " + "Vacations" + " (\n"
            + " VacationId int NOT NULL PRIMARY KEY,\n"
            + " OriginFlightId int NOT NULL,\n"
            + " DestFlightId int,\n "
            + " VacationCountry varchar(10) NOT NULL,\n "
            + " VacationCity varchar(10) NOT NULL,\n "
            + " StartDate varchar(15) NOT NULL,\n "
            + " EndDate varchar(15) NOT NULL,\n "
            + " Price double NOT NULL,\n "
            + " BaggageType varchar(15) NOT NULL,\n "
            + " HotVacation boolean NOT NULL,\n "
            + " Status int NOT NULL,\n "
            + " VacationType varchar(15) NOT NULL,\n"
            + " AccommodationType varchar(15) NOT NULL,\n"
            + " AccommodationIncluded boolean NOT NULL,\n"
            + " AccommodationRank int NOT NULL,\n"
            + " Parking boolean NOT NULL,\n"
            + " user_name varchar(10) NOT NULL,\n "
            /*
            + " CONSTRAINT FK_UserVacation FOREIGN KEY (user_name)\n"
            + " REFERENCES Users(user_name)\n "
            + " ON DELETE CASCADE\n"
            + " ON UPDATE CASCADE\n "
            */
            + ");";

    // SQL statement for creating a FlightTickets table
    private String FlightTicketsSql = "CREATE TABLE IF NOT EXISTS " + "FlightTickets" + " (\n"
            + " TicketId INTEGER NOT NULL PRIMARY KEY,\n"
            + " Airline varchar(10) NOT NULL,\n"
            + " DestinationCountry varchar(10) NOT NULL,\n "
            + " DestinationCity varchar(10) NOT NULL,\n "
            + " OriginCountry varchar(10) NOT NULL,\n "
            + " OriginCity varchar(10) NOT NULL,\n "
            + " BabyTickets INTEGER NOT NULL,\n "
            + " ChildTickets INTEGER NOT NULL,\n "
            + " AdultTickets INTEGER NOT NULL,\n "
            + " TicketType INTEGER NOT NULL,\n "
            + " AmountOfTickets INTEGER NOT NULL,\n "
            + " VacationId INTEGER NOT NULL,\n "
            /*
            + " CONSTRAINT FK_VacationUser FOREIGN KEY (VacationId)\n"
            + " REFERENCES Vacations(VacationId)\n "
            + " ON DELETE CASCADE\n"
            + " ON UPDATE CASCADE\n "
            */
            + ");";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        model.createNewTable(UsersSql);
        model.createNewTable(VacationsSql);
        model.createNewTable(FlightTicketsSql);
        View view = new View();
        Controller controller = new Controller(model, view);
        view.setController(controller);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));
        root.getStylesheets().add(getClass().getClassLoader().getResource("vacationCSS.css").toExternalForm());
        primaryStage.setTitle("Welcome To Vacation4U!");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

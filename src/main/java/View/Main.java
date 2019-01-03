package View;

import Controller.Controller;
import View.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Model.Model;

/**
 * This class is main class for running of the application
 */
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
            + " DVacationCountry varchar(10) NOT NULL,\n "
            + " DVacationCity varchar(10) NOT NULL,\n "
            + " OVacationCountry varchar(10) NOT NULL,\n "
            + " OVacationCity varchar(10) NOT NULL,\n "
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
            + " Transfers boolean NOT NULL,\n"
            + " user_name varchar(10) NOT NULL\n "
            + ");";
            /*
                       + " FOREIGN KEY (user_name)\n"
            + " REFERENCES Users(user_name)\n"
            + " ON DELETE CASCADE\n"
            + " ON UPDATE CASCADE\n "
             */


    // SQL statement for creating a FlightTickets table
    private String FlightTicketsSql = "CREATE TABLE IF NOT EXISTS " + "FlightTickets" + " (\n"
            + " TicketId int NOT NULL PRIMARY KEY,\n"
            + " Airline varchar(10) NOT NULL,\n"
            + " DestinationCountry varchar(10) NOT NULL,\n "
            + " DestinationCity varchar(10) NOT NULL,\n "
            + " OriginCountry varchar(10) NOT NULL,\n "
            + " OriginCity varchar(10) NOT NULL,\n "
            + " BabyTickets int NOT NULL,\n "
            + " ChildTickets int NOT NULL,\n "
            + " AdultTickets int NOT NULL,\n "
            + " TicketType int NOT NULL,\n "
            + " AmountOfTickets int NOT NULL,\n "
            + " VacationId int NOT NULL\n "
            + ");";
            /* + " CONSTRAINT FK_VacationUser FOREIGN KEY (VacationId)\n"
            + " REFERENCES Vacations(VacationId)\n "
            + " ON DELETE CASCADE\n"
            + " ON UPDATE CASCADE\n " */

    // SQL statement for creating a FlightTickets table
    private String PaymentsSql = "CREATE TABLE IF NOT EXISTS " + "Payments" + " (\n"
            + " VacationId int NOT NULL PRIMARY KEY,\n"
            + " Seller varchar(15) NOT NULL,\n"
            + " Buyer varchar(15) NOT NULL,\n "
            + " PaymentMethod varchar(15) NOT NULL,\n "
            + " CreditNumber varchar(20) NOT NULL,\n "
            + " PaymentDate varchar(15) NOT NULL\n "
            + ");";

    private String ExchangeRequestSql = "CREATE TABLE IF NOT EXISTS " + "ExchangeRequest" + " (\n"
            + " VacationExchangeID int NOT NULL PRIMARY KEY,\n"
            + " VacationIdSeller int NOT NULL,\n "
            + " Seller varchar(15) NOT NULL,\n "
            + " VacationIdBuyer int NOT NULL,\n "
            + " Buyer varchar(15) NOT NULL,\n "
            + " PaymentDate varchar(15) NOT NULL,\n "
            + " RequestStatus int NOT NULL,\n "
            + " CellPhone varchar(15) NOT NULL\n "
            + ");";

    private String PurchaseRequestSql = "CREATE TABLE IF NOT EXISTS " + "PurchaseRequest" + " (\n"
            + " PurchaseRequestID int NOT NULL PRIMARY KEY,\n "
            + " VacationIdSeller int NOT NULL,\n "
            + " Seller varchar(15) NOT NULL,\n "
            + " Buyer varchar(15) NOT NULL,\n "
            + " PaymentDate varchar(15) NOT NULL,\n "
            + " RequestStatus int NOT NULL,\n "
            + " CellPhone varchar(15) NOT NULL\n "
            + ");";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        //model.createNewDatabase("v4uDB");
        model.createNewTable(UsersSql);
        model.createNewTable(VacationsSql);
        model.createNewTable(FlightTicketsSql);
        model.createNewTable(PaymentsSql);
        model.createNewTable(ExchangeRequestSql);
        model.createNewTable(PurchaseRequestSql);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getClassLoader().getResource("MainView.fxml").openStream());
        View view = (View)fxmlLoader.getController();
        Controller controller = new Controller(model, view);
        view.setController(controller);
        root.getStylesheets().add(getClass().getClassLoader().getResource("vacationCSS.css").toExternalForm());
        primaryStage.setTitle("Welcome To EveryVacation4U!");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

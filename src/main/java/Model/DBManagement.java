package Model;
/**
 * This class represents the activity of the data base
 */

import java.sql.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class DBManagement {
    /**
     * This method create new dataBase
     *
     * @param fileName -the name of the dataBase
     */
    public void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:" + "D:\\Studies\\BGU\\semesterE\\NITUZ\\Part1\\GLYR4U" + "\\" + fileName + ".db";
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method create new table in the dataBase
     *
     * @param sql -  statement for creating a new table
     */
    public static void createNewTable(String sql) {
        // SQLite connection string
        System.out.println(System.getProperty("user.dir"));
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\v4uDB.db";
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method connect to the dataBase,and return the connection
     *
     * @return- the connection to the dataBase
     */
    private Connection connect() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\v4uDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * This method insert a new user to the table in the dataBase
     * by his private fields
     *
     * @param user_name
     * @param password
     * @param first_name
     * @param last_name
     * @param city
     * @param date
     */
    public void insertNewUser(String user_name, String password, String first_name, String last_name, String city, String date) {
        String sql = "INSERT INTO Users(user_name,password, first_name, last_name, city,date) VALUES(?,?, ?,?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_name);
            pstmt.setString(2, password);
            pstmt.setString(3, first_name);
            pstmt.setString(4, last_name);
            pstmt.setString(5, city);
            pstmt.setString(6, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method deletes a record from the table in the dataBase
     * by its primary key
     *
     * @param PrimaryKeyToDelete
     */
    public void deleteRecord(String PrimaryKeyToDelete, String sql) {   /// to delete user: sql="DELETE FROM Users WHERE user_name = ?" . to delete vacation sql=""
        // String sql = "DELETE FROM Users WHERE user_name = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, PrimaryKeyToDelete);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method updates user's field,and returns True or False
     * if the update is done (or not)
     *
     * @param user_name
     * @param field     -The field that be update
     * @param newData   -The new value of the field
     * @return
     */
    public boolean updateUser(String user_name, String field, String newData) {
        String sql = "UPDATE Users SET " + field + " = ? WHERE user_name = ? ";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param [ instead of question marks (?) ]
            pstmt.setString(2, user_name);
            pstmt.setString(1, newData);
            // update
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This method search a user in the dataBase and return the user if he exists
     * and Null if not
     *
     * @param userName
     * @return User
     */
    public User find_User_Exists(String userName) {
        String sql = "SELECT user_name, password, first_name, last_name, city,date "
                + "FROM Users WHERE user_name = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                return new User(rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("city"),
                        rs.getString("date")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    /**
     * This method gets a user name and  a password
     * and checks whether they are suitable
     *
     * @param userName
     * @param password
     * @return -True or False
     */
    public boolean confirmPassword(String userName, String password) {
        User currUser = find_User_Exists(userName);
        return (password.equals(currUser.getPassword())) ? true : false;
    }

    /**
     * This method checks if a current user exists in the DB
     *
     * @param userName
     * @param password
     * @return -True or False
     */
    public boolean IsCorrectPassword(String userName, String password) {
        String sql = "SELECT password "
                + "FROM Users WHERE user_name = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            String tmp =rs.getString("password");
            if (!rs.getString("password").equals(password)) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This method insert a new vacation for sale to the vacation table in the dataBase
     *
     * @param VacationId
     * @param OriginFlightId
     * @param DestFlightId
     * @param DVacationCountry
     * @param DVacationCity
     * @param OVacationCountry
     * @param OVacationCity
     * @param StartDate
     * @param EndDate
     * @param Price
     * @param BaggageType
     * @param HotVacation
     * @param Status
     * @param VacationType
     * @param AccommodationType
     * @param AccommodationIncluded
     * @param AccommodationRank
     * @param Transfers
     * @param user_name
     */
    public void insertNewVacation(int VacationId, int OriginFlightId, int DestFlightId, String DVacationCountry, String DVacationCity,
                                  String OVacationCountry, String OVacationCity, String StartDate, String EndDate, double Price, String BaggageType, boolean HotVacation, int Status,
                                  String VacationType, String AccommodationType, boolean AccommodationIncluded,
                                  int AccommodationRank, boolean Transfers, String user_name) {
        String sql = "INSERT INTO Vacations(VacationId,OriginFlightId,DestFlightId,DVacationCountry,DVacationCity," +
                "OVacationCountry,OVacationCity,StartDate,EndDate,Price,BaggageType,HotVacation,Status," +
                "VacationType,AccommodationType,AccommodationIncluded," +
                "AccommodationRank,Transfers,user_name) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, VacationId);
            pstmt.setInt(2, OriginFlightId);
            pstmt.setInt(3, DestFlightId);
            pstmt.setString(4, DVacationCountry);
            pstmt.setString(5, DVacationCity);
            pstmt.setString(6, OVacationCountry);
            pstmt.setString(7, OVacationCity);
            pstmt.setString(8, StartDate);
            pstmt.setString(9, EndDate);
            pstmt.setDouble(10, Price);
            pstmt.setString(11, BaggageType);
            pstmt.setBoolean(12, HotVacation);
            pstmt.setInt(13, Status);
            pstmt.setString(14, VacationType);
            pstmt.setString(15, AccommodationType);
            pstmt.setBoolean(16, AccommodationIncluded);
            pstmt.setInt(17, AccommodationRank);
            pstmt.setBoolean(18, Transfers);
            pstmt.setString(19, user_name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method deletes a record from the Vacations table in the DB by the VacationId
     *
     * @param VacationIdToDelete
     */
    public void deleteVacationRecord(String VacationIdToDelete) {
        String sql = "DELETE FROM Vacations WHERE VacationId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, VacationIdToDelete);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method updates vacation details by the user that insert this vacation,
     * and returns True or False if the update is done (or not)
     *
     * @param vacationId
     * @param field     -The field that be update
     * @param newData   -The new value of the field
     * @return True or False
     */
    public boolean updateVacationRecord(int vacationId, String field, String newData) {
        String sql = "UPDATE Vacations SET " + field + " = ? WHERE VacationId = ? ";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param [ instead of question marks (?) ]
            pstmt.setString(1, newData);
            pstmt.setInt(2, vacationId);
            // update
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This method insert a new ticket flight for sale to the FlightTickets table in the dataBase
     *
     * @param TicketId
     * @param Airline
     * @param DestinationCountry
     * @param DestinationCity
     * @param OriginCountry
     * @param OriginCity
     * @param BabyTickets
     * @param ChildTickets
     * @param AdultTickets
     * @param TicketType
     * @param AmountOfTickets
     * @param VacationId
     */
    public void insertNewFlightTickets(int TicketId, String Airline, String DestinationCountry, String DestinationCity, String OriginCountry,
                                       String OriginCity, int BabyTickets, int ChildTickets, int AdultTickets, String TicketType,
                                       int AmountOfTickets, int VacationId) {
        String sql = "INSERT INTO FlightTickets(TicketId,Airline,DestinationCountry,DestinationCity,OriginCountry," +
                "OriginCity,BabyTickets,ChildTickets,AdultTickets,TicketType," +
                "AmountOfTickets,VacationId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, TicketId);
            pstmt.setString(2, Airline);
            pstmt.setString(3, DestinationCountry);
            pstmt.setString(4, DestinationCity);
            pstmt.setString(5, OriginCountry);
            pstmt.setString(6, OriginCity);
            pstmt.setInt(7, BabyTickets);
            pstmt.setInt(8, ChildTickets);
            pstmt.setInt(9, AdultTickets);
            pstmt.setString(10, TicketType);
            pstmt.setInt(11, AmountOfTickets);
            pstmt.setInt(12, VacationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method select from the DB vacation tables all Vacations ID's that suitable to the user search
     *
     * @param TableName
     * @param askedValues
     * @return ArrayList<Integer> of vacations ID's
     */
    public ArrayList<Integer> GetVacationsIdByField(String TableName, HashMap<String, String> askedValues) {//,String FieldToFind){
        ArrayList<Integer> VacationsID = new ArrayList<>();
        HashMap<Integer, String> searchValues = new HashMap<>();
        int numOfValues = 0;
        String whereStatement = "";

        for (String key : askedValues.keySet()) {
            if (!(askedValues.get(key).equals("")))
            {
                numOfValues++;
                whereStatement = whereStatement + " AND " + key + " =?";
                searchValues.put(numOfValues, askedValues.get(key));
            }
        }

        String sql = "SELECT * FROM " + TableName + " WHERE Status =0" + whereStatement;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 1; i <= numOfValues; i++)
            {
                // set the value
                pstmt.setString(i, searchValues.get(i));
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VacationsID.add((int) rs.getObject(("VacationId")));
            }
            return VacationsID;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return VacationsID;
    }

    /**
     * This method get a list of Vacations ID's and return a list of the suitable vacation objects
     *
     * @param VacationsID
     * @return ArrayList<Vacation> of suitable vacations objects
     */
    public ArrayList<Vacation> GetVacationsInformation(ArrayList<Integer> VacationsID) {
        Object[] Ids = VacationsID.toArray();
        ArrayList<Vacation> AllVacations = new ArrayList<>();
        for (int i = 0; i < VacationsID.size(); i++) {
            String sql = "SELECT * FROM " + "Vacations" + " WHERE " + "VacationId" + " =?";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // set the value
                pstmt.setString(1, Ids[i].toString());
                ResultSet rs = pstmt.executeQuery();
                // loop through the result set
                AllVacations.add(CreateVacationFromDB(rs.getInt("VacationId"),
                        rs.getString("OriginFlightId"),
                        rs.getString("DestFlightId"),
                        rs.getString("DVacationCountry"),
                        rs.getString("DVacationCity"),
                        rs.getString("OVacationCountry"),
                        rs.getString("OVacationCity"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate"),
                        rs.getString("Price"),
                        rs.getString("BaggageType"),
                        rs.getString("HotVacation"),
                        rs.getString("Status"),
                        rs.getString("VacationType"),
                        rs.getString("AccommodationType"),
                        rs.getString("AccommodationIncluded"),
                        rs.getString("AccommodationRank"),
                        rs.getString("Transfers"),
                        rs.getString("user_name")));

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return AllVacations;
    }

    /**
     * This method get a list of Vacation parameters and return a new object containing them
     *
     * @param VacationId
     * @param OriginFlightId
     * @param DestFlightId
     * @param DVacationCountry
     * @param DVacationCity
     * @param OVacationCountry
     * @param OVacationCity
     * @param StartDate
     * @param EndDate
     * @param Price
     * @param BaggageType
     * @param HotVacation
     * @param Status
     * @param VacationType
     * @param AccommodationType
     * @param AccommodationIncluded
     * @param AccommodationRank
     * @param Transfers
     * @param user_name
     * @return Vacation object
     */
    public Vacation CreateVacationFromDB(int VacationId, String OriginFlightId, String DestFlightId,
                                         String DVacationCountry, String DVacationCity, String OVacationCountry,
                                         String OVacationCity,String StartDate, String EndDate, String Price,
                                         String BaggageType, String HotVacation, String Status, String VacationType,
                                         String AccommodationType, String AccommodationIncluded,
                                         String AccommodationRank, String Transfers, String user_name) {
        return new Vacation(VacationId, createFlightTicket(OriginFlightId), createFlightTicket(DestFlightId),
                new Location(DVacationCountry, DVacationCity), new Location(OVacationCountry, OVacationCity),
                StartDate, EndDate, Double.parseDouble(Price), BaggageType, VacationType, AccommodationType,
                Boolean.parseBoolean(AccommodationIncluded), Boolean.parseBoolean(Transfers));
    }

    /**
     * This method get a FlightTicket Id and return a new object of FlightTicket
     *
     * @param FlightTicketValue
     * @return FlightTickets object
     */
    public FlightTickets createFlightTicket(String FlightTicketValue) {
        String sql = "SELECT * FROM " + "FlightTickets" + " WHERE " + "TicketId" + " =?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the value
            pstmt.setString(1, FlightTicketValue);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set
            int[] Tickets = new int[]{rs.getInt("BabyTickets"), rs.getInt("ChildTickets"),
                    rs.getInt("AdultTickets")};
            return new FlightTickets(
                    rs.getString("Airline"),new Location(rs.getString("DestinationCountry"),
                    rs.getString("DestinationCity")), new Location(rs.getString("OriginCountry"),
                    rs.getString("OriginCity")), Tickets, rs.getString("TicketType"),
                    rs.getInt("VacationId"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method insert a new payment details of a vacation purchase to the dataBase
     *
     * @param VacationId
     * @param Seller
     * @param Buyer
     * @param PaymentMethod
     * @param CreditNumber
     * @param PaymentDate
     */
    public void insertNewPayment(int VacationId, String Seller, String Buyer, String PaymentMethod,
                                 String CreditNumber, String PaymentDate) {
        String paySql = "INSERT INTO Payments(VacationId, Seller, Buyer, PaymentMethod, CreditNumber, PaymentDate) " +
                "VALUES(?,?,?,?,?,?)";
        String vacationSql = "UPDATE Vacations SET Status =1 WHERE VacationId =?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(paySql);
            pstmt.setInt(1, VacationId);
            pstmt.setString(2, Seller);
            pstmt.setString(3, Buyer);
            pstmt.setString(4, PaymentMethod);
            pstmt.setString(5, CreditNumber);
            pstmt.setString(6, PaymentDate);

            pstmt.executeUpdate();
            Connection conn2 = this.connect();
            PreparedStatement pstmt2 = conn2.prepareStatement(vacationSql);
            pstmt2.setInt(1, VacationId);
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
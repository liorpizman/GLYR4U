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

    public static void addConstraintToTable(String tableName, String constraintName,
                                            String constraintType, String fieldName, String constraintCondition) {
        // SQLite connection string
        System.out.println(System.getProperty("user.dir"));
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\v4uDB.db";
        String sql = "ALTER TABLE " + tableName + " ADD CONSTRAINT " + constraintName + " " +
                constraintType + " (" + fieldName + ") " + "REFERENCES " + constraintCondition;
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
     * @return
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
     */
    public boolean IsCorrectPassword(String userName, String password) {
        String sql = "SELECT password "
                + "FROM Users WHERE user_name = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.getObject(password) != password) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public void insertNewVacation(int VacationId, int OriginFlightId, int DestFlightId, String VacationCountry, String VacationCity,
                                  String StartDate, String EndDate, double Price, String BaggageType, boolean HotVacation, int Status,
                                  String VacationType, String AccommodationType, boolean AccommodationIncluded,
                                  int AccommodationRank, boolean Parking, String user_name) {
        String sql = "INSERT INTO Vacations(VacationId,OriginFlightId,DestFlightId,VacationCountry,VacationCity," +
                "StartDate,EndDate,Price,BaggageType,HotVacation,Status," +
                "VacationType,AccommodationType,AccommodationIncluded," +
                "AccommodationRank,Parking,user_name) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, VacationId);
            pstmt.setInt(2, OriginFlightId);
            pstmt.setInt(3, DestFlightId);
            pstmt.setString(4, VacationCountry);
            pstmt.setString(5, VacationCity);
            pstmt.setString(6, StartDate);
            pstmt.setString(7, EndDate);
            pstmt.setDouble(8, Price);
            pstmt.setString(9, BaggageType);
            pstmt.setBoolean(10, HotVacation);
            pstmt.setInt(11, Status);
            pstmt.setString(12, VacationType);
            pstmt.setString(13, AccommodationType);
            pstmt.setBoolean(14, AccommodationIncluded);
            pstmt.setInt(15, AccommodationRank);
            pstmt.setBoolean(16, Parking);
            pstmt.setString(17, user_name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertNewFlightTickets(int TicketId, String Airline, String DestinationCountry, String DestinationCity, String OriginCountry,
                                       String OriginCity, int BabyTickets, int ChildTickets, int AdultTickets, String TicketType,
                                       int AmountOfTickets, int VacationId) {
        String sql = "INSERT INTO Vacations(TicketId,Airline,DestinationCountry,DestinationCity,OriginCountry," +
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

    public ArrayList<Integer> GetVacationsIdByField(String TableName, HashMap<String, String> askedValues) {//,String FieldToFind){
        ArrayList<Integer> VacationsID = new ArrayList<>();
        HashMap<Integer, String> searchValues = new HashMap<>();
        int numOfValues = 0;
        String whereStatement = "";

        for (String key : askedValues.keySet()) {
            if (!(askedValues.get(key).equals("")))
            {
                numOfValues++;
                if (numOfValues == 1)
                    whereStatement = whereStatement + " " + key + " =?";
                else
                    whereStatement = whereStatement + " AND " + key + " =?";
                searchValues.put(numOfValues, askedValues.get(key));
            }
        }

        String sql = "SELECT * FROM " + TableName + " WHERE" + whereStatement;

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

    public ArrayList<Vacation> GetVacationsInformation(ArrayList<Integer> VacationsID) {

        Object[] Ids = VacationsID.toArray();
        ArrayList<Vacation> AllVacations = new ArrayList<>();
        for (int i = 0; i < VacationsID.size(); i++) {
            String sql = "SELECT * FROM " + "Vacations" + " WHERE " + "VacationId" + " =?";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // set the value
                pstmt.setString(1, Ids[i].toString());
                //
                ResultSet rs = pstmt.executeQuery();
                //rs.getObject(FieldToFind);
                // loop through the result set

                AllVacations.add(CreateVacationFromDB((rs.getObject("VacationId")).toString(),
                        (rs.getObject("OriginFlightId")).toString(),
                        (rs.getObject("DestFlightId")).toString(),
                        (rs.getObject("DVacationCountry")).toString(),
                        (rs.getObject("DVacationCity")).toString(),
                        (rs.getObject("OVacationCountry")).toString(),
                        (rs.getObject("OVacationCity")).toString(),
                        (rs.getObject("StartDate")).toString(),
                        (rs.getObject("EndDate")).toString(),
                        (rs.getObject("Price")).toString(),
                        (rs.getObject("BaggageType")).toString(),
                        (rs.getObject("HotVacation")).toString(),
                        (rs.getObject("Status")).toString(),
                        (rs.getObject("VacationType")).toString(),
                        (rs.getObject("AccommodationType")).toString(),
                        (rs.getObject("AccommodationIncluded")).toString(),
                        (rs.getObject("AccommodationRank")).toString(),
                        (rs.getObject("Parking")).toString(),
                        (rs.getObject("user_name")).toString()));

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return AllVacations;
    }


    public Vacation CreateVacationFromDB(String vacationId, String OriginFlightId, String DestFlightId, String DVacationCountry,
                                         String DVacationCity, String OVacationCountry, String OVacationCity,String StartDate, String EndDate, String Price,
                                         String BaggageType, String HotVacation, String Status, String VacationType,
                                         String AccommodationType, String AccommodationIncluded, String AccommodationRank, String Transfers,
                                         String user_name) {
        return new Vacation(createFlightTicket(OriginFlightId), createFlightTicket(DestFlightId),
                new Location(DVacationCountry, DVacationCity), new Location(OVacationCountry, OVacationCity),
                StartDate, EndDate, Double.parseDouble(Price), BaggageType, VacationType, AccommodationType,
                Boolean.parseBoolean(AccommodationIncluded), Boolean.parseBoolean(Transfers));
    }

    public FlightTickets createFlightTicket(String FlightTicketValue) {
        String sql = "SELECT * FROM " + "FlightTickets" + " WHERE " + "TicketId" + " =?";


        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1, FlightTicketValue);
            //
            ResultSet rs = pstmt.executeQuery();
            //rs.getObject(FieldToFind);
            // loop through the result set

            int[] Tickets = new int[]{Integer.parseInt((rs.getObject("BabyTickets")).toString()),
                    Integer.parseInt((rs.getObject("ChildTickets")).toString()),
                    Integer.parseInt((rs.getObject("AdultTickets").toString()))};
            return new FlightTickets(
                    (rs.getObject("Airline")).toString(),
                    new Location((rs.getObject("DestinationCountry")).toString(),
                            (rs.getObject("DestinationCity")).toString()),
                    new Location((rs.getObject("OriginCountry")).toString(),
                            (rs.getObject("OriginCity")).toString()),
                    Tickets,
                    (rs.getObject("TicketType")).toString(),
                    Integer.parseInt((rs.getObject("VacationId")).toString()));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}


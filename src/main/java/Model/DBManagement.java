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
import java.util.HashMap;

public class DBManagement {
    /**
     * This method create new dataBase
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
     * @param newUser
     */
    public void insertNewUser(RegisteredUser newUser) {
        String sql = "INSERT INTO Users(user_name, password, first_name, last_name, city, date) VALUES(?,?,?,?,?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getUser_name());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getFirst_name());
            pstmt.setString(4, newUser.getLast_name());
            pstmt.setString(5, newUser.getCity());
            pstmt.setString(6, newUser.getDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method deletes a record from the table in the dataBase
     * by its primary key
     * @param PrimaryKeyToDelete
     */
    public void deleteRecord(String PrimaryKeyToDelete, String sql) {   /// to deleteD user: sql="DELETE FROM Users WHERE user_name = ?" . to deleteD vacation sql=""
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
     * if the updateU is done (or not)
     * @param user_name
     * @param field     -The field that be updateU
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
            // updateU
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
     * @param userName user name
     * @return User
     */
    public RegisteredUser findExistsUser(String userName) {
        String sql = "SELECT user_name, password, first_name, last_name, city,date "
                + "FROM Users WHERE user_name = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                return new RegisteredUser(rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("city"),
                        rs.getString("date"));
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
     * @param currUser current user
     * @param password password of user
     * @return -True or False
     */
    public boolean confirmPassword(RegisteredUser currUser, String password) {
        return (password.equals(currUser.getPassword())) ? true : false;
    }

    /**
     * This method checks if a current user exists in the DB
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
            String tmp = rs.getString("password");
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
     * @param VacationId id of vacation
     * @param OriginFlightId id of origin flight
     * @param DestFlightId id of dest flight
     * @param DVacationCountry dest country
     * @param DVacationCity dest city
     * @param OVacationCountry origin country
     * @param OVacationCity origin city
     * @param StartDate arrival date
     * @param EndDate departure date
     * @param Price price
     * @param BaggageType type of baggage
     * @param HotVacation hot vacation
     * @param Status status
     * @param VacationType type of vacation
     * @param AccommodationType type of accommodation
     * @param AccommodationIncluded accommodation included or not
     * @param AccommodationRank accommodation rank
     * @param Transfers transfers included or not
     * @param currentUser current user
     */
    public void insertNewVacation(int VacationId, int OriginFlightId, int DestFlightId, String DVacationCountry, String DVacationCity,
                                  String OVacationCountry, String OVacationCity, String StartDate, String EndDate, double Price, String BaggageType, boolean HotVacation, int Status,
                                  String VacationType, String AccommodationType, boolean AccommodationIncluded,
                                  int AccommodationRank, boolean Transfers, RegisteredUser currentUser) {
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
            pstmt.setString(19, currentUser.getUser_name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method deletes all the Vacations records in table that belong to user that was deleted and not sold yet.
     * @param user username
     */
    public void deleteVacationOfDeletedUser(RegisteredUser user) {
        ArrayList<Integer> VacationsID = new ArrayList<>();
        String sql = "SELECT * FROM Vacations WHERE user_name=? AND Status =0";
        String sql1 = "DELETE FROM Vacations WHERE user_name=? AND Status =0";
        String sql2 = "DELETE FROM FlightTickets WHERE VacationId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUser_name());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VacationsID.add((int) rs.getObject(("VacationId")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try (Connection conn1 = this.connect();
             PreparedStatement pstmt1 = conn1.prepareStatement(sql1)) {
            pstmt1.setString(1, user.getUser_name());
            pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try (Connection conn2 = this.connect();
             PreparedStatement pstmt2 = conn2.prepareStatement(sql2)) {
            for (int i = 0; i < VacationsID.size(); i++) {
                pstmt2.setString(1, VacationsID.get(i).toString());
                pstmt2.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method deletes a record from the Vacations table in the DB by the VacationId
     * @param VacationIdToDelete id of vacation
     */
    public boolean deleteVacationRecord(String VacationIdToDelete, String userName) {
        ArrayList<Integer> ID = new ArrayList<>();
        ID.add(Integer.parseInt(VacationIdToDelete));
        ArrayList<Vacation> vacations = GetVacationsInformation(ID);
        Vacation v = vacations.get(0);
        if (v.getUserID().equals(userName)) {
            String sql1 = "DELETE FROM Vacations WHERE VacationId = ? AND user_name=?";
            String sql2 = "DELETE FROM FlightTickets WHERE VacationId = ?";
            try (Connection conn1 = this.connect();
                 PreparedStatement pstmt1 = conn1.prepareStatement(sql1)) {
                pstmt1.setString(1, VacationIdToDelete);
                pstmt1.setString(2, userName);
                pstmt1.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            try (Connection conn2 = this.connect();
                 PreparedStatement pstmt2 = conn2.prepareStatement(sql2)) {
                pstmt2.setString(1, VacationIdToDelete);
                pstmt2.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }

    /**
     * This method updates vacation details by the user that insert this vacation,
     * and returns True or False if the updateU is done (or not)
     * @param vacationId id of vacation
     * @param field      -The field that be updateU
     * @param newData    -The new value of the field
     * @return True or False
     */
    public boolean updateVacationRecord(int vacationId, String field, String newData) {
        String sql = "UPDATE Vacations SET " + field + " = ? WHERE VacationId = ? ";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param [ instead of question marks (?) ]
            pstmt.setString(1, newData);
            pstmt.setInt(2, vacationId);
            // updateU
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This method insert a new ticket flight for sale to the FlightTickets table in the dataBase
     * @param TicketId id of ticket
     * @param Airline company
     * @param DestinationCountry dest country
     * @param DestinationCity dest city
     * @param OriginCountry origin country
     * @param OriginCity origin city
     * @param BabyTickets baby ticket
     * @param ChildTickets child ticket
     * @param AdultTickets adult ticket
     * @param TicketType type ticket
     * @param AmountOfTickets count tickets
     * @param VacationId id of vacation
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
     * @param TableName name of table
     * @param askedValues values
     * @return ArrayList<Integer> of vacations ID's
     */
    public ArrayList<Integer> GetVacationsIdByField(String TableName, HashMap<String, String> askedValues) {//,String FieldToFind){
        ArrayList<Integer> VacationsID = new ArrayList<>();
        HashMap<Integer, String> searchValues = new HashMap<>();
        int numOfValues = 0;
        String whereStatement = "";
        String sql = "";
        if (askedValues == null) {
            sql = "SELECT * FROM " + TableName + " WHERE Status =0";
        } else {
            for (String key : askedValues.keySet()) {
                if (askedValues.get(key) != null && !(askedValues.get(key).equals(""))) {
                    numOfValues++;
                    whereStatement = whereStatement + " AND " + key + " =?";
                    searchValues.put(numOfValues, askedValues.get(key));
                }
            }
            sql = "SELECT * FROM " + TableName + " WHERE Status =0" + whereStatement;
        }
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 1; i <= numOfValues; i++) {
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
     * @param VacationsID id of vacation
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
     * @param VacationId id of vacation
     * @param OriginFlightId origin id
     * @param DestFlightId dest id
     * @param DVacationCountry dest country
     * @param DVacationCity dest city
     * @param OVacationCountry origin country
     * @param OVacationCity origin city
     * @param StartDate arrival
     * @param EndDate departure
     * @param Price price
     * @param BaggageType type of baggage
     * @param HotVacation hot vacation
     * @param Status status
     * @param VacationType type of vacation
     * @param AccommodationType type of accommodation
     * @param AccommodationIncluded if accommodation included
     * @param AccommodationRank accommodation rank
     * @param Transfers if transfers included
     * @param user_name user name
     * @return Vacation object
     */
    public Vacation CreateVacationFromDB(int VacationId, String OriginFlightId, String DestFlightId,
                                         String DVacationCountry, String DVacationCity, String OVacationCountry,
                                         String OVacationCity, String StartDate, String EndDate, String Price,
                                         String BaggageType, String HotVacation, String Status, String VacationType,
                                         String AccommodationType, String AccommodationIncluded,
                                         String AccommodationRank, String Transfers, String user_name) {
        return new Vacation(VacationId, createFlightTicket(OriginFlightId), createFlightTicket(DestFlightId),
                DVacationCountry, DVacationCity, OVacationCountry, OVacationCity,
                StartDate, EndDate, Double.parseDouble(Price), BaggageType, VacationType, AccommodationType,
                Boolean.parseBoolean(AccommodationIncluded), Boolean.parseBoolean(Transfers), Integer.parseInt(AccommodationRank), user_name);
    }

    /**
     * This method get a FlightTicket Id and return a new object of FlightTicket
     * @param FlightTicketValue val
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
                    rs.getString("Airline"), (rs.getString("DestinationCountry")),
                    rs.getString("DestinationCity"), (rs.getString("OriginCountry")),
                    rs.getString("OriginCity"), Tickets, rs.getString("TicketType"),
                    rs.getInt("VacationId"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * Method to insert new purchase request
     * @param Purchase purchase request
     * @return if succeeded
     */
    public boolean insertNewPurchaseRequest(PurchaseRequest Purchase) {
        String paySql = "INSERT INTO PurchaseRequest(PurchaseRequestID,VacationIdSeller, Seller, Buyer, PaymentDate,RequestStatus,CellPhone) " +
                "VALUES(?,?,?,?,?,?,?)";
        if (ExistPurchaseRequestForUser(Purchase.getVacationIdSeller(), Purchase.getSeller(), Purchase.getBuyer())) {
            return false;
        }
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(paySql);
            pstmt.setInt(1, Purchase.getPurchaseRequestID());
            pstmt.setInt(2, Purchase.getVacationIdSeller());
            pstmt.setString(3, Purchase.getSeller());
            pstmt.setString(4, Purchase.getBuyer());
            pstmt.setString(5, Purchase.getPaymentDate());
            pstmt.setInt(6, Purchase.getRequestStatus());  // 0
            pstmt.setString(7, Purchase.getCellPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * Method to check wether a purchase requests exists
     * @param VacationIdSeller id of vacation
     * @param Seller user name
     * @param Buyer user name
     * @return if exists or not
     */
    public boolean ExistPurchaseRequestForUser(int VacationIdSeller, String Seller, String Buyer) {
        String query = "SELECT * FROM PurchaseRequest WHERE VacationIdSeller =? AND Seller =? AND Buyer =? AND RequestStatus=?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, VacationIdSeller);
            pstmt.setString(2, Seller);
            pstmt.setString(3, Buyer);
            pstmt.setInt(4, 0);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Method to get all purchase requests ids' for current user
     * @param UserName curr user
     * @param processVacation curr status
     * @return
     */
    public ArrayList<Integer> GetPurchaseRequestForUser(String UserName, int processVacation) {
        ArrayList<Integer> PurchaseRequestID = new ArrayList<>();
        String sql;
        if (processVacation == 0) {
            sql = "SELECT * FROM PurchaseRequest WHERE Seller=? AND RequestStatus =?";
        } else {
            sql = "SELECT * FROM PurchaseRequest WHERE Buyer=? AND RequestStatus =?";
        }
        if (processVacation == 3) {
            processVacation = 0;
        }
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, UserName);
            pstmt.setInt(2, processVacation);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PurchaseRequestID.add((int) rs.getObject(("PurchaseRequestID")));
            }
            return PurchaseRequestID;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return PurchaseRequestID;
    }

    /**
     * Method to get all purchase requests for current ids'
     * @param PurchaseRequestID list of ids'
     * @return all purchase requests for current ids'
     */
    public ArrayList<PurchaseRequest> GetPurchaseRequestInformation(ArrayList<Integer> PurchaseRequestID) {
        Object[] PurchaseIds = PurchaseRequestID.toArray();
        ArrayList<PurchaseRequest> AllPurchaseRequests = new ArrayList<>();
        for (int i = 0; i < PurchaseRequestID.size(); i++) {
            String sql = "SELECT * FROM PurchaseRequest WHERE PurchaseRequestID =?";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // set the value
                pstmt.setInt(1, (int) PurchaseIds[i]);
                ResultSet rs = pstmt.executeQuery();
                // loop through the result set
                AllPurchaseRequests.add(createPurchaseRequestFromDB((int) PurchaseIds[i],
                        rs.getInt("VacationIdSeller"),
                        rs.getString("Seller"),
                        rs.getString("Buyer"),
                        rs.getString("PaymentDate"),
                        rs.getInt("RequestStatus"),
                        rs.getString("CellPhone")));

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return AllPurchaseRequests;
    }

    /**
     * Method to create purchase request from db
     * @param purchaseRequestID id of purchase
     * @param vacationIdSeller id of vacation
     * @param seller user name
     * @param buyer user name
     * @param paymentDate curr date
     * @param requestStatus curr satus
     * @param cellPhone buyers'
     * @return purchase request
     */
    public PurchaseRequest createPurchaseRequestFromDB(int purchaseRequestID, int vacationIdSeller, String seller, String buyer,
                                                       String paymentDate, int requestStatus, String cellPhone) {
        return new PurchaseRequest(purchaseRequestID, vacationIdSeller, seller, buyer, paymentDate, requestStatus, cellPhone);
    }


    /**
     * Method to accept purchase request
     * @param VacationIdSeller id of vacation
     * @param Buyer user name
     */
    public void AcceptPurchaseRequest(int VacationIdSeller, String Buyer) {  // set status to '2' to reject and '1' to accept
        String RejectRequestSql = "UPDATE PurchaseRequest SET RequestStatus =? WHERE VacationIdSeller =?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(RejectRequestSql);
            pstmt.setInt(1, 2);
            pstmt.setInt(2, VacationIdSeller);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String AcceptRequestSql = "UPDATE PurchaseRequest SET RequestStatus =? WHERE Buyer =? AND VacationIdSeller =?";
        try {
            Connection conn2 = this.connect();
            PreparedStatement pstmt2 = conn2.prepareStatement(AcceptRequestSql);
            pstmt2.setInt(1, 1);
            pstmt2.setString(2, Buyer);
            pstmt2.setInt(3, VacationIdSeller);
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String UpdtevacationSql = "UPDATE Vacations SET Status =1 WHERE VacationId =?";
        try {
            Connection conn4 = this.connect();
            PreparedStatement pstmt4 = conn4.prepareStatement(UpdtevacationSql);
            pstmt4.setInt(1, VacationIdSeller);
            pstmt4.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String RejectExchangeRequestSql = "UPDATE ExchangeRequest SET RequestStatus =? WHERE VacationIdSeller =?";
        try {
            Connection conn3 = this.connect();
            PreparedStatement pstmt3 = conn3.prepareStatement(RejectExchangeRequestSql);
            pstmt3.setInt(1, 2);
            pstmt3.setInt(2, VacationIdSeller);
            pstmt3.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Method to reject purchase request
     * @param VacationIdSeller id of vacation
     * @param Buyer user name
     */
    public void RejectPurchaseRequest(int VacationIdSeller, String Buyer) { // set status to '2' to reject and '1' to accept
        String RejectRequestSql = "UPDATE PurchaseRequest SET RequestStatus =? WHERE Buyer =? AND VacationIdSeller =?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(RejectRequestSql);
            pstmt.setInt(1, 2);
            pstmt.setString(2, Buyer);
            pstmt.setInt(3, VacationIdSeller);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to insert new exchange request
     * @param exchangeRequest exchange request
     * @return if succeeded
     */
    public boolean insertNewExchangeRequest(ExchangeRequest exchangeRequest) {
        String paySql = "INSERT INTO ExchangeRequest(VacationExchangeID,VacationIdSeller,Seller,VacationIdBuyer,Buyer," +
                "PaymentDate,RequestStatus,CellPhone) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        if (ExistExchangeRequestForUsers(exchangeRequest.getVacationIdSeller(), exchangeRequest.getVacationIdBuyer(),
                exchangeRequest.getSeller(), exchangeRequest.getBuyer())) {
            return false;
        }
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(paySql);
            pstmt.setInt(1, exchangeRequest.getVacationExchangeID());
            pstmt.setInt(2, exchangeRequest.getVacationIdSeller());
            pstmt.setString(3, exchangeRequest.getSeller());
            pstmt.setInt(4, exchangeRequest.getVacationIdBuyer());
            pstmt.setString(5, exchangeRequest.getBuyer());
            pstmt.setString(6, exchangeRequest.getPaymentDate());
            pstmt.setInt(7, exchangeRequest.getRequestStatus());//0
            pstmt.setString(8, exchangeRequest.getCellPhone());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * Method to check whether current exchange request exists
     * @param VacationIdSeller id of vacation
     * @param VacationIdBuyer if of vacation
     * @param Seller user name
     * @param Buyer user name
     * @return if exists
     */
    public boolean ExistExchangeRequestForUsers(int VacationIdSeller, int VacationIdBuyer, String Seller, String Buyer) {
        String query = "SELECT * FROM ExchangeRequest WHERE VacationIdSeller =?" +
                " AND VacationIdBuyer =? AND Seller =? AND Buyer =? AND RequestStatus=?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, VacationIdSeller);
            pstmt.setInt(2, VacationIdBuyer);
            pstmt.setString(3, Seller);
            pstmt.setString(4, Buyer);
            pstmt.setInt(5, 0);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Method to get all ids of exchange requests for current user
     * @param UserName current user
     * @param processVacation current status
     * @return all ids of exchange requests for current user
     */
    public ArrayList<Integer> GetExchangeRequestForUser(String UserName, int processVacation) {
        ArrayList<Integer> ExchangeRequestID = new ArrayList<>();
        String sql;
        if (processVacation == 0) {
            sql = "SELECT * FROM ExchangeRequest WHERE Seller =? AND RequestStatus =?";
        } else {
            sql = "SELECT * FROM ExchangeRequest WHERE Buyer =? AND RequestStatus =?";
        }
        if (processVacation == 3) {
            processVacation = 0;
        }
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, UserName);
            pstmt.setInt(2, processVacation);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ExchangeRequestID.add((int) rs.getObject(("VacationExchangeID")));
            }
            return ExchangeRequestID;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ExchangeRequestID;
    }

    /**
     * Method to get all exchange requests of current ids'
     * @param ExchangeRequestID list of ids'
     * @return all exchange requests of current ids'
     */
    public ArrayList<ExchangeRequest> GetExchangeRequestInformation(ArrayList<Integer> ExchangeRequestID) {
        Object[] ExchangeIds = ExchangeRequestID.toArray();
        ArrayList<ExchangeRequest> AllExchangeRequests = new ArrayList<>();
        for (int i = 0; i < ExchangeRequestID.size(); i++) {
            String sql = "SELECT * FROM " + "ExchangeRequest WHERE VacationExchangeID =?";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // set the value
                pstmt.setInt(1, (int) ExchangeIds[i]);
                ResultSet rs = pstmt.executeQuery();
                // loop through the result set
                AllExchangeRequests.add(createExchangeRequestFromDB((int) ExchangeIds[i],
                        rs.getInt("VacationIdSeller"),
                        rs.getString("Seller"),
                        rs.getInt("VacationIdBuyer"),
                        rs.getString("Buyer"),
                        rs.getString("PaymentDate"),
                        rs.getInt("RequestStatus"),
                        rs.getString("CellPhone")));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return AllExchangeRequests;
    }

    /**
     * Method to create exchange request in db
     * @param VacationExchangeID id of exchange
     * @param vacationIdSeller id of vacation
     * @param seller user name
     * @param VacationIdBuyer id of vacation
     * @param buyer user name
     * @param paymentDate curr date
     * @param requestStatus curr status
     * @param cellPhone buyers'
     * @return  exchange request
     */
    public ExchangeRequest createExchangeRequestFromDB(int VacationExchangeID, int vacationIdSeller, String seller, int VacationIdBuyer,
                                                       String buyer, String paymentDate, int requestStatus, String cellPhone) {
        return new ExchangeRequest(VacationExchangeID, vacationIdSeller, seller, VacationIdBuyer, buyer, paymentDate, requestStatus, cellPhone);
    }

    /**
     * Method to accept exchange request
     * @param VacationIdSeller id of seller's vacation
     * @param VacationIdBuyer id of buyer's vacation
     */
    public void AcceptExchangeRequest(int VacationIdSeller, int VacationIdBuyer) {  // set status to '2' to reject and '1' to accept
        String RejectRequestSql = "UPDATE ExchangeRequest SET RequestStatus =? WHERE VacationIdSeller =?";
        try {
            Connection conn2 = this.connect();
            PreparedStatement pstmt2 = conn2.prepareStatement(RejectRequestSql);
            pstmt2.setInt(1, 2);
            pstmt2.setInt(2, VacationIdSeller);
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String AcceptRequestSql = "UPDATE ExchangeRequest SET RequestStatus =? WHERE VacationIdSeller =? AND VacationIdBuyer =?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(AcceptRequestSql);
            pstmt.setInt(1, 1);
            pstmt.setInt(2, VacationIdSeller);
            pstmt.setInt(3, VacationIdBuyer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String UpdteVacationSellerSql = "UPDATE Vacations SET Status =1 WHERE VacationId =?";
        try {
            Connection conn4 = this.connect();
            PreparedStatement pstmt4 = conn4.prepareStatement(UpdteVacationSellerSql);
            pstmt4.setInt(1, VacationIdSeller);
            pstmt4.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String UpdteVacationBuyerSql = "UPDATE Vacations SET Status =1 WHERE VacationId =?";
        try {
            Connection conn5 = this.connect();
            PreparedStatement pstmt5 = conn5.prepareStatement(UpdteVacationBuyerSql);
            pstmt5.setInt(1, VacationIdBuyer);
            pstmt5.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String RejectPurchaseRequestSql = "UPDATE PurchaseRequest SET RequestStatus =? WHERE VacationIdSeller =?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt3 = conn.prepareStatement(RejectPurchaseRequestSql);
            pstmt3.setInt(1, 2);
            pstmt3.setInt(2, VacationIdSeller);
            pstmt3.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to reject exchange request
     * @param VacationIdSeller id of vacation of seller
     * @param VacationIdBuyer id of vacation of buyer
     */
    public void RejectExchangeRequest(int VacationIdSeller, int VacationIdBuyer) { // set status to '2' to reject and '1' to accept
        String RejectRequestSql = "UPDATE ExchangeRequest SET RequestStatus =? WHERE VacationIdSeller =? AND VacationIdBuyer =?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(RejectRequestSql);
            pstmt.setInt(1, 2);
            pstmt.setInt(2, VacationIdSeller);
            pstmt.setInt(3, VacationIdBuyer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to get vacation by a vacation id
     * @param vacationID id of vacation
     * @return vacation
     */
    public Vacation GetVacationByVacationID(int vacationID) {
        ArrayList<Integer> VacationsID = new ArrayList<>();
        VacationsID.add(vacationID);
        ArrayList<Vacation> vacations = GetVacationsInformation(VacationsID);
        return vacations.get(0);
    }

}






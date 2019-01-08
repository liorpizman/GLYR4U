package Model;

import Model.AUser;
import Model.Vacation;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * This class represents a user in the data base
 */
public class RegisteredUser extends AUser {
    /**
     * fields of RegisteredUser
     */
    private String user_name;
    private String password;
    private String first_name;
    private String last_name;
    private String city;
    private String date;
    private HashMap<Integer,Vacation> vacations_dict;


    /**
     * This is a default constructor to create a new user
     *
     * @param user_name
     * @param password
     * @param first_name
     * @param last_name
     * @param city
     * @param date
     */
    public RegisteredUser(String user_name, String password, String first_name, String last_name, String city,
                          String date) {
        this.user_name = user_name;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.date = date;
        this.vacations_dict = new HashMap<>() ;

    }

    /**
     * Add published vacation to user's vacations list
     * @param vacation
     */

    public void publishVacation(Vacation vacation){
        vacations_dict.put(vacation.getVactionId(),vacation);
    }
    /**
     * set purchased vacation status
     * @param vacationId
     */
    public void acceptPurchaseRequest(int vacationId){
        vacations_dict.get(vacationId).setStatus(VacationStatus.Sold);
    }
    /**
     * set exchanged vacation status
     * @param vacationId
     */
    public void acceptExchangeRequest(int vacationId){
        vacations_dict.get(vacationId).setStatus(VacationStatus.Sold);
    }

    /**
     *
     * @param vacationIdSeller
     * @param seller
     * @param paymentDate
     * @param cellPhone
     * @return new purchase request
     */

    public PurchaseRequest RequestForPurchase(int vacationIdSeller, String seller, String paymentDate,  String cellPhone){
        return new PurchaseRequest(vacationIdSeller,  seller, this.user_name, paymentDate,  0,  cellPhone);//(Model inserts the request to db)
    }

    /**
     *
     * @param vacationIdSeller
     * @param seller
     * @param vacationIdBuyer
     * @param paymentDate
     * @param cellPhone
     * @return new Exchange request
     */
    public ExchangeRequest RequestForExchange(int vacationIdSeller, String seller, int vacationIdBuyer, String paymentDate, String cellPhone){
        return new ExchangeRequest(vacationIdSeller, seller,  vacationIdBuyer,  this.user_name,  paymentDate,  0,  cellPhone);//(Model inserts the request to db)
    }

    /**
     * updating user's vacation
     * @param vacation
     */
    public void updateVacation(Vacation vacation){
        vacations_dict.put(vacation.getVactionId(),vacation);
    }

    /**
     *
     * @param vacationId
     */
    public void deleteVacation(String vacationId){
        vacations_dict.remove(vacationId);
    }

    /**
     *
     * @param vacationId
     * @return vacation
     */
    public Vacation ReadVacation(String vacationId){
        return vacations_dict.get(vacationId);
    }
    /**
     * Getter for user_name
     * @return user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for first_name
     * @return first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Getter for last_name
     * @return last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Getter for city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter for date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for user_name
     * @param user_name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Setter for password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for first_name
     * @param first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Setter for last_name
     * @param last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Setter for city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Setter for date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * This method prints all the data of the user
     */
    public void printUser() {
        System.out.println(user_name + ", " + password + ", " + first_name + ", " + last_name + ", " + city + ", " + date);
    }

    public void setVacations(HashMap<Integer,Vacation> vacations) {
        this.vacations_dict = vacations;
    }
}

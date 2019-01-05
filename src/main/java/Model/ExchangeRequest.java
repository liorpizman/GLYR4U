package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  This class represents exchange requests between to users in the system
 */
public class ExchangeRequest {
    /**
     *     fields of ExchangeRequest
     */
    private int VacationExchangeID;
    private int VacationIdSeller;
    private String Seller;
    private int VacationIdBuyer;
    private String Buyer;
    private String PaymentDate;
    private int RequestStatus;
    private String CellPhone;


    /**
     * This is a constructor for ExchangeRequest
     * @param vacationExchangeID id of exchange
     * @param vacationIdSeller id of vacation of the seller
     * @param seller seller username
     * @param vacationIdBuyer id of vacation of the buyer
     * @param buyer buyer username
     * @param paymentDate current date
     * @param requestStatus status of transaction
     * @param cellPhone buyers'
     */
    public ExchangeRequest(int vacationExchangeID, int vacationIdSeller, String seller, int vacationIdBuyer, String buyer, String paymentDate, int requestStatus, String cellPhone) {

        VacationExchangeID = vacationExchangeID;
        VacationIdSeller = vacationIdSeller;
        Seller = seller;
        VacationIdBuyer = vacationIdBuyer;
        Buyer = buyer;
        PaymentDate = paymentDate;
        RequestStatus = requestStatus;
        CellPhone = cellPhone;
    }

    /**
     * This is a constructor for ExchangeRequest
     * @param vacationIdSeller id of vacation of the seller
     * @param seller seller username
     * @param vacationIdBuyer id of vacation of the buyer
     * @param buyer buyer username
     * @param paymentDate current date
     * @param requestStatus status of transaction
     * @param cellPhone buyers'
     */
    public ExchangeRequest(int vacationIdSeller, String seller, int vacationIdBuyer, String buyer, String paymentDate, int requestStatus, String cellPhone) {

        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = timeFormat.format(time);
        String currTime = formattedDate.replace(":", "");
        VacationExchangeID = Integer.parseInt(currTime);
        VacationIdSeller = vacationIdSeller;
        Seller = seller;
        VacationIdBuyer = vacationIdBuyer;
        Buyer = buyer;
        PaymentDate = paymentDate;
        RequestStatus = requestStatus;
        CellPhone = cellPhone;
    }

    /**
     * Getter for VacationExchangeID
     * @return VacationExchangeID
     */
    public int getVacationExchangeID() {
        return VacationExchangeID;
    }

    /**
     * Setter for VacationExchangeID
     * @param vacationExchangeID
     */
    public void setVacationExchangeID(int vacationExchangeID) {
        VacationExchangeID = vacationExchangeID;
    }

    /**
     * Getter for VacationIdSeller
     * @return VacationIdSeller
     */
    public int getVacationIdSeller() {
        return VacationIdSeller;
    }

    /**
     * Setter for VacationIdSeller
     * @param vacationIdSeller
     */
    public void setVacationIdSeller(int vacationIdSeller) {
        VacationIdSeller = vacationIdSeller;
    }

    /**
     * Getter for Seller
     * @return Seller
     */
    public String getSeller() {
        return Seller;
    }

    /**
     * Setter for Seller
     * @param seller
     */
    public void setSeller(String seller) {
        Seller = seller;
    }

    /**
     * Getter for VacationIdBuyer
     * @return VacationIdBuyer
     */
    public int getVacationIdBuyer() {
        return VacationIdBuyer;
    }

    /**
     * Setter for VacationIdBuyer
     * @param vacationIdBuyer
     */
    public void setVacationIdBuyer(int vacationIdBuyer) {
        VacationIdBuyer = vacationIdBuyer;
    }

    /**
     * Getter for Buyer
     * @return Buyer
     */
    public String getBuyer() {
        return Buyer;
    }

    /**
     * Setter for Buyer
     * @param buyer
     */
    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    /**
     * Getter for PaymentDate
     * @return PaymentDate
     */
    public String getPaymentDate() {
        return PaymentDate;
    }

    /**
     * Setter for PaymentDate
     * @param paymentDate
     */
    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    /**
     * Getter for RequestStatus
     * @return RequestStatus
     */
    public int getRequestStatus() {
        return RequestStatus;
    }

    /**
     * Setter for RequestStatus
     * @param requestStatus
     */
    public void setRequestStatus(int requestStatus) {
        RequestStatus = requestStatus;
    }

    /**
     * Getter for CellPhone
     * @return CellPhone
     */
    public String getCellPhone() {
        return CellPhone;
    }

    /**
     * Setter for CellPhone
     * @param cellPhone
     */
    public void setCellPhone(String cellPhone) {
        CellPhone = cellPhone;
    }

    /**
     * toString for ExchangeRequest
     * @return toString with buyer's details
     */
    public String toString() {
        return " , from buyer: " + Buyer + " , contact in phoneNumber: " + CellPhone;
    }

    /**
     * toString for ExchangeRequest
     * @return toString with sellers's details
     */
    public String toStringFromSeller() {
        return " , from seller: " + Seller;
    }
}
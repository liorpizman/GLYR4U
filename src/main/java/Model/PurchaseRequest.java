package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents a purchase request of a buyer to seller
 */
public class PurchaseRequest {
    /**
     * fields of PurchaseRequest
     */
    private int purchaseRequestId;
    private int VacationIdSeller;
    private String Seller;
    private String Buyer;
    private String PaymentDate;
    private int RequestStatus;
    private String CellPhone;

    /**
     * This is a constructor of PurchaseRequest
     * @param vacationIdSeller vacation id of seller
     * @param seller seller username
     * @param buyer buyer username
     * @param paymentDate current date
     * @param requestStatus current status
     * @param cellPhone buyer's
     */
    public PurchaseRequest(int vacationIdSeller, String seller, String buyer,
                           String paymentDate, int requestStatus, String cellPhone) {

        VacationIdSeller = vacationIdSeller;
        Seller = seller;
        Buyer = buyer;
        PaymentDate = paymentDate;
        RequestStatus = requestStatus;
        CellPhone = cellPhone;
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = timeFormat.format(time);
        String currTime = formattedDate.replace(":", "");
        purchaseRequestId = Integer.parseInt(currTime);
    }

    /**
     * This is a constructor of PurchaseRequest
     * @param _purchaseRequestId current purchase request id
     * @param vacationIdSeller vacation id of seller
     * @param seller seller username
     * @param buyer buyer username
     * @param paymentDate current date
     * @param requestStatus current status
     * @param cellPhone buyer's
     */
    public PurchaseRequest(int _purchaseRequestId, int vacationIdSeller, String seller, String buyer,
                           String paymentDate, int requestStatus, String cellPhone) {
        purchaseRequestId = _purchaseRequestId;
        VacationIdSeller = vacationIdSeller;
        Seller = seller;
        Buyer = buyer;
        PaymentDate = paymentDate;
        RequestStatus = requestStatus;
        CellPhone = cellPhone;
    }

    /**
     * Getter for purchaseRequestId
     * @return purchaseRequestId
     */
    public int getPurchaseRequestID() {
        return purchaseRequestId;
    }

    /**
     * Getter for VacationIdSeller
     * @return VacationIdSeller
     */
    public int getVacationIdSeller() {
        return VacationIdSeller;
    }

    /**
     * Getter for Seller
     * @return Seller
     */
    public String getSeller() {
        return Seller;
    }

    /**
     * Getter for Buyer
     * @return Buyer
     */
    public String getBuyer() {
        return Buyer;
    }

    /**
     * Getter for PaymentDate
     * @return PaymentDate
     */
    public String getPaymentDate() {
        return PaymentDate;
    }

    /**
     * Getter for RequestStatus
     * @return RequestStatus
     */
    public int getRequestStatus() {
        return RequestStatus;
    }

    /**
     * Setter for purchaseRequestId
     * @param purchaseRequestID
     */
    public void setPurchaseRequestID(int purchaseRequestID) {
        purchaseRequestId = purchaseRequestID;
    }

    /**
     * Setter for VacationIdSeller
     * @param vacationIdSeller
     */
    public void setVacationIdSeller(int vacationIdSeller) {
        VacationIdSeller = vacationIdSeller;
    }

    /**
     * Setter for Seller
     * @param seller
     */
    public void setSeller(String seller) {
        Seller = seller;
    }

    /**
     * Setter for Buyer
     * @param buyer
     */
    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    /**
     * Setter for PaymentDate
     * @param paymentDate
     */
    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
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
     * @return
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
     * toString for PurchaseRequest with buyer's details
     * @return toString
     */
    public String toString() {
        return ", from buyer: " + Buyer + " , contact in phoneNumber: " + CellPhone;
    }

    /**
     * toString for PurchaseRequest with sellers's details
     * @return toStringFromSeller
     */
    public String toStringFromSeller() {
        return " , from seller: " + Seller;
    }
}









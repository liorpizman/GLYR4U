package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PurchaseRequest {
    private int purchaseRequestId;
    private int VacationIdSeller;
    private String Seller;
    private String Buyer;
    private String PaymentDate;
    private int RequestStatus;
    private String CellPhone;


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

    public int getPurchaseRequestID() {
        return purchaseRequestId;
    }

    public int getVacationIdSeller() {
        return VacationIdSeller;
    }

    public String getSeller() {
        return Seller;
    }

    public String getBuyer() {
        return Buyer;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public int getRequestStatus() {
        return RequestStatus;
    }

    public void setPurchaseRequestID(int purchaseRequestID) {
        purchaseRequestId = purchaseRequestID;
    }

    public void setVacationIdSeller(int vacationIdSeller) {
        VacationIdSeller = vacationIdSeller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    public void setRequestStatus(int requestStatus) {
        RequestStatus = requestStatus;
    }

    public String getCellPhone() {
        return CellPhone;
    }

    public void setCellPhone(String cellPhone) {
        CellPhone = cellPhone;
    }

    public String toString() {
        return ", from buyer: " + Buyer + " , contact in phoneNumber: " + CellPhone;
    }
}









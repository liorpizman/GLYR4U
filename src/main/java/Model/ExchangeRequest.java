package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExchangeRequest {
    private int VacationExchangeID;
    private int VacationIdSeller;
    private String Seller;
    private int VacationIdBuyer;
    private String Buyer;
    private String PaymentDate;
    private int RequestStatus;
    private String CellPhone;


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



    public int getVacationExchangeID() {
        return VacationExchangeID;
    }

    public void setVacationExchangeID(int vacationExchangeID) {
        VacationExchangeID = vacationExchangeID;
    }

    public int getVacationIdSeller() {
        return VacationIdSeller;
    }

    public void setVacationIdSeller(int vacationIdSeller) {
        VacationIdSeller = vacationIdSeller;
    }

    public String getSeller() {
        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public int getVacationIdBuyer() {
        return VacationIdBuyer;
    }

    public void setVacationIdBuyer(int vacationIdBuyer) {
        VacationIdBuyer = vacationIdBuyer;
    }

    public String getBuyer() {
        return Buyer;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    public int getRequestStatus() {
        return RequestStatus;
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
        return " , from buyer: " + Buyer + " , contact in phoneNumber: " + CellPhone ;
    }
}
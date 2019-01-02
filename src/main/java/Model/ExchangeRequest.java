package Model;

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

        VacationExchangeID=vacationExchangeID;
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
}

/*
    private String ExchangeRequestSql = "CREATE TABLE IF NOT EXISTS " + "ExchangeRequest" + " (\n"
            + " VacationExchangeID int NOT NULL PRIMARY KEY,\n"
            + " VacationIdSeller int NOT NULL,\n"
            + " Seller varchar(15) NOT NULL,\n"
            + " VacationIdBuyer int NOT NULL,\n"
            + " Buyer varchar(15) NOT NULL,\n "
            + " PaymentDate varchar(15) NOT NULL\n, "
            + " RequestStatus int NOT NULL\n, "
            + " CellPhone varchar(15) NOT NULL\n "
            + ");";
            */
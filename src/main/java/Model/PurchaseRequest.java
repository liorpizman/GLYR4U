package Model;

public class PurchaseRequest {
    private int PurchaseRequestID;
    private int VacationIdSeller;
    private String Seller;
    private String Buyer;
    private String PaymentDate;
    private int RequestStatus;
    private String CellPhone;



    public PurchaseRequest(int purchaseRequestID, int vacationIdSeller, String seller, String buyer,
                           String paymentDate, int requestStatus, String cellPhone) {
        PurchaseRequestID = purchaseRequestID;
        VacationIdSeller = vacationIdSeller;
        Seller = seller;
        Buyer = buyer;
        PaymentDate = paymentDate;
        RequestStatus = requestStatus;
        CellPhone = cellPhone;
    }

    public int getPurchaseRequestID() {
        return PurchaseRequestID;
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
        PurchaseRequestID = purchaseRequestID;
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
}







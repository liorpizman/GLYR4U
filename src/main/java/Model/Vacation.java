
package Model;

/**
 * enum for vacation status
 */
enum VacationStatus {
    ForSale, InProgress, Sold
}

/**
 * enum for rank
 */
enum Rank {
    bad, likely, good, veryGood, excellent
}

/**
 * Represents a vacation
 */
public class Vacation {
    /**
     * fields of Vacation
     */
    private FlightTickets FromOriginFlight;
    private FlightTickets FromDestFlight;  /// if it is null - it is not exists

    private String DVacationCountry;
    private String DVacationCity;
    private String OVacationCountry;
    private String OVacationCity;

    private String StartDate;
    private String EndDate;               /// use date picker in the gui and convert it to string
    private double Price;
    private int VacationId;
    private String BaggageType; //comboBox with default option that we will choose
    private boolean HotVacation;
    private VacationStatus Status;
    private String VacationType;  //comboBox with default option that we will choose
    private String AccommodationType;  //comboBox with default option that we will choose
    private boolean AccommodationIncluded;
    private Rank AccommodationRank;
    private boolean Transfers;
    private String userID;


    /**
     * This constructor of Vacation
     *
     * @param vacationId            id of vacation
     * @param fromOriginFlight      origin flight
     * @param fromDestFlight        dest flight
     * @param dVacationCountry      dest country
     * @param dVacationCity         dest city
     * @param oVacationCountry      origin country
     * @param oVacationCity         origin city
     * @param startDate             arrival date
     * @param endDate               departure date
     * @param price                 price
     * @param Baggagetype           type of baggage
     * @param vacationType          type of vacation
     * @param accommodationType     type of accommodation
     * @param accommodationIncluded if accommodation included or not
     * @param transfers             included or not
     * @param accommodationRank     accommodation rank
     * @param _userID               id of the user
     */
    public Vacation(int vacationId, FlightTickets fromOriginFlight, FlightTickets fromDestFlight, String dVacationCountry,
                    String dVacationCity, String oVacationCountry, String oVacationCity, String startDate, String endDate, double price, String Baggagetype,
                    String vacationType, String accommodationType, boolean accommodationIncluded, boolean transfers, int accommodationRank, String _userID) {
        FromOriginFlight = fromOriginFlight;
        FromDestFlight = fromDestFlight;

        DVacationCountry = dVacationCountry;
        DVacationCity = dVacationCity;
        OVacationCountry = oVacationCountry;
        OVacationCity = oVacationCity;

        StartDate = startDate;
        EndDate = endDate;
        Price = price;
        VacationId = vacationId;
        BaggageType = Baggagetype;
        HotVacation = false;
        Status = VacationStatus.ForSale;
        VacationType = vacationType;
        AccommodationType = accommodationType;
        AccommodationIncluded = accommodationIncluded;
        AccommodationRank = Rank.values()[accommodationRank];
        Transfers = transfers;
        userID = _userID;
    }

    public void setStatus(VacationStatus status) {
        Status = status;
    }

    /**
     * Getter for FromOriginFlight
     *
     * @return FromOriginFlight
     */
    public FlightTickets getFromOriginFlight() {
        return FromOriginFlight;
    }

    /**
     * Getter for FromDestFlight
     *
     * @return FromDestFlight
     */
    public FlightTickets getFromDestFlight() {
        return FromDestFlight;
    }

    /**
     * Getter for FromOriginFlight TicketId
     *
     * @return FromOriginFlight TicketId
     */
    public int getFromOriginFlightId() {
        return FromOriginFlight.getTicketId();
    }

    /**
     * Getter for FromDestFlight TicketId
     *
     * @return FromDestFlight TicketId
     */
    public int getFromDestFlightId() {
        return FromDestFlight.getTicketId();
    }

    /**
     * Getter for DVacationCountry
     *
     * @return DVacationCountry
     */
    public String getDVacationCountry() {
        return DVacationCountry;
    }

    /**
     * Getter for DVacationCity
     *
     * @return DVacationCity
     */
    public String getDVacationCity() {
        return DVacationCity;
    }

    /**
     * Getter for OVacationCountry
     *
     * @return OVacationCountry
     */
    public String getOVacationCountry() {
        return OVacationCountry;
    }

    /**
     * Getter for OVacationCity
     *
     * @return OVacationCity
     */
    public String getOVacationCity() {
        return OVacationCity;
    }

    /**
     * Getter for StartDate
     *
     * @return StartDate
     */
    public String getStartDate() {
        return StartDate;
    }

    /**
     * Getter for EndDate
     *
     * @return EndDate
     */
    public String getEndDate() {
        return EndDate;
    }

    /**
     * Getter for Price
     *
     * @return Price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * Getter for VacationId
     *
     * @return VacationId
     */
    public int getVactionId() {
        return VacationId;
    }

    /**
     * Getter for BaggageType
     *
     * @return BaggageType
     */
    public String getBaggageType() {
        return BaggageType;
    }

    /**
     * Getter for HotVacation
     *
     * @return HotVacation
     */
    public boolean isHotVacation() {
        return HotVacation;
    }

    /**
     * Getter for Status
     *
     * @return Status
     */
    public int getStatus() {
        return Status.ordinal();
    }

    /**
     * Getter for VacationType
     *
     * @return VacationType
     */
    public String getVacationType() {
        return VacationType;
    }

    /**
     * Getter for AccommodationType
     *
     * @return AccommodationType
     */
    public String getAccommodationType() {
        return AccommodationType;
    }

    /**
     * Getter for AccommodationIncluded
     *
     * @return AccommodationIncluded
     */
    public boolean isAccommodationIncluded() {
        return AccommodationIncluded;
    }

    /**
     * Getter for AccommodationRank
     *
     * @return AccommodationRank
     */
    public int getAccommodationRank() {
        return AccommodationRank.ordinal();
    }

    /**
     * Getter for Transfers
     *
     * @return Transfers
     */
    public boolean isTransfers() {
        return Transfers;
    }

    /**
     * Getter for userID
     *
     * @return userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Getter for FromOriginFlight Airline
     *
     * @return FromOriginFlight Airline
     */
    public String getFromOriginFlightAirline() {
        return FromOriginFlight.getAirline();
    }

    /**
     * Getter for TicketType
     *
     * @return TicketType
     */
    public String getFromOriginFlightClass() {
        return FromOriginFlight.getTicketType();
    }

    /**
     * toString for Vacation
     *
     * @return toString
     */
    public String toString() {
        String sol = "VacationId: " + VacationId + " From " + OVacationCountry + "," + OVacationCity + " to " + DVacationCountry + "," + DVacationCity;
        if (FromDestFlight != null) {
            sol += " -  2 way ticket";
        } else {
            sol += " -  1 way ticket";
        }
        sol += ", Price: " + Price + "$, Publishd by "+ userID;
        return sol;
    }

}

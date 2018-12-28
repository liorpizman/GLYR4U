package Model;

import java.util.HashMap;
import java.util.Map;

enum VacationStatus {
    ForSale, InProgress, Sold
}

enum Rank {
    bad, likely, good, veryGood, excellent
}

/**
 * Represents a vacation
 */
public class Vacation {
    private FlightTickets FromOriginFlight;
    private FlightTickets FromDestFlight;  /// if it is null - it is not exists
    private Location DVacationLocation;
    private Location OVacationLocation;
    private String StartDate;
    private String EndDate;               /// use date picker in the gui and convert it to string
    private double Price;
    private int VactionId;
    private String BaggageType; //comboBox with default option that we will choose
    private boolean HotVacation;
    private VacationStatus Status;
    private String VacationType;  //comboBox with default option that we will choose
    private String AccommodationType;  //comboBox with default option that we will choose
    private boolean AccommodationIncluded;
    private Rank AccommodationRank;
    private boolean Transfers;
    private String userID;

    public Vacation(int vactionId, FlightTickets fromOriginFlight, FlightTickets fromDestFlight, Location dVacationLocation,
                    Location oVacationLocation, String startDate, String endDate, double price, String Baggagetype,
                    String vacationType, String accommodationType, boolean accommodationIncluded, boolean transfers, int accommodationRank, String _userID) {
        FromOriginFlight = fromOriginFlight;
        FromDestFlight = fromDestFlight;
        DVacationLocation = dVacationLocation;
        OVacationLocation = oVacationLocation;
        StartDate = startDate;
        EndDate = endDate;
        Price = price;
        VactionId = vactionId;
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

    public FlightTickets getFromOriginFlight() {
        return FromOriginFlight;
    }

    public FlightTickets getFromDestFlight() {
        return FromDestFlight;
    }

    public int getFromOriginFlightId() {
        return FromOriginFlight.getTicketId();
    }

    public int getFromDestFlightId() {
        return FromDestFlight.getTicketId();
    }

    public String getDVacationCountry() {
        return DVacationLocation.getCountry();
    }

    public String getDVacationCity() {
        return DVacationLocation.getCity();
    }

    public String getOVacationCountry() {
        return OVacationLocation.getCountry();
    }

    public String getOVacationCity() {
        return OVacationLocation.getCity();
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public double getPrice() {
        return Price;
    }

    public int getVactionId() {
        return VactionId;
    }

    public String getBaggageType() {
        return BaggageType;
    }

    public boolean isHotVacation() {
        return HotVacation;
    }

    public int getStatus() {
        return Status.ordinal();
    }

    public String getVacationType() {
        return VacationType;
    }

    public String getAccommodationType() {
        return AccommodationType;
    }

    public boolean isAccommodationIncluded() {
        return AccommodationIncluded;
    }

    public int getAccommodationRank() {
        return AccommodationRank.ordinal();
    }

    public boolean isTransfers() {
        return Transfers;
    }

    public String getUserID() {
        return userID;
    }

    public String getFromOriginFlightAirline() {
        return FromOriginFlight.getAirline();
    }

    public String getFromOriginFlightClass() {
        return FromOriginFlight.getTicketType();
    }

    public String toString() {
        return "From "+ OVacationLocation.getCountry() + " to "+ DVacationLocation.getCountry()+ ", Price: "+ Price + "$";
    }

}

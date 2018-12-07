package Model;

import java.util.HashMap;
import java.util.Map;

enum VacationStatus {
    ForSale, InProgress, Sold
}

enum Rank {
    bad, likely, good, veryGood, excellent;
}

public class Vacation {
    private static int VactionID = 200;
    private FlightTickets FromOriginFlight;
    private FlightTickets FromDestFlight;  /// if it is null - it is not exists
    private Location VacationLocation;
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

    public Vacation(FlightTickets fromOriginFlight, FlightTickets fromDestFlight, Location vacationLocation,
                    String startDate, String endDate, int price, String Baggagetype, String vacationType,
                    String accommodationType, boolean accommodationIncluded, boolean transfers) {
        FromOriginFlight = fromOriginFlight;
        FromDestFlight = fromDestFlight;
        VacationLocation = vacationLocation;
        StartDate = startDate;
        EndDate = endDate;
        Price = price;
        VactionId = VactionID++;
        BaggageType = Baggagetype;
        HotVacation = false;
        Status = VacationStatus.ForSale;
        VacationType = vacationType;
        AccommodationType = accommodationType;
        AccommodationIncluded = accommodationIncluded;
        AccommodationRank = Rank.good;
        Transfers = transfers;
    }


    public int getFromOriginFlightId() {
        return FromOriginFlight.getTicketId();
    }

    public int getFromDestFlightId() {
        return FromDestFlight.getTicketId();
    }

    public String getVacationCountry() {
        return VacationLocation.getCountry();
    }

    public String getVacationCity() {
        return VacationLocation.getCity();
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


}

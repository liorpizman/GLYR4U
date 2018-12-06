package Model;


public class FlightTickets {

    static int TicketID = 100;////// key of the object
    private String Airline;
    private Location Destination;
    private Location Origin;
    private String TicketType; // combo Box -BasicClass, FirstClass, BusinessClass
    private int TicketId;
    private int AmountOfTickets;
    private int[] TravelersType;  // index: 0-Baby , 1-Child , 2-Adult
    private int VacationId;

    public FlightTickets(String airline, Location destination, Location origin, int[] travelersType, String TypeOfTicket, int vacationId) {
        Airline = airline;
        Destination = destination;
        Origin = origin;

        TravelersType = travelersType;
        AmountOfTickets = CalculateAmountOfTickets(travelersType);
        TicketType = TypeOfTicket;
        TicketId = TicketID++;
        VacationId = vacationId;
    }

    private int CalculateAmountOfTickets(int[] travelersType) {
        int Amount = 0;
        for (int i = 0; i < travelersType.length; i++) {
            Amount += travelersType[i];
        }
        return Amount;
    }


    public static int getTicketID() {
        return TicketID;
    }

    public String getAirline() {
        return Airline;
    }

    public String getDestinationCountry() {
        return Destination.getCountry();
    }

    public String getDestinationCity() {
        return Destination.getCity();
    }

    public String getOriginCountry() {
        return Origin.getCountry();
    }

    public String getOriginCity() {
        return Origin.getCity();
    }

    public String getTicketType() {
        return TicketType;
    }

    public int getAmountOfTickets() {
        return AmountOfTickets;
    }

    public int getBabyTicketsAmount() {
        return TravelersType[0];
    }

    public int getChildTicketsAmount() {
        return TravelersType[1];
    }

    public int getAdultTicketsAmount() {
        return TravelersType[2];
    }

    public int getVacationId() {
        return VacationId;
    }

}

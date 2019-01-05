package Model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents Collection of flight tickets
 */
public class FlightTickets {
    /**
     * fields of FlightTickets
     */
    private String Airline;
    private String TicketType; // combo Box -BasicClass, FirstClass, BusinessClass
    private int TicketId;
    private int AmountOfTickets;
    private int[] TravelersType;  // index: 0-Baby , 1-Child , 2-Adult
    private int VacationId;
    private String DestinationCountry;
    private String DestinationCity;
    private String OriginCountry;
    private String OriginCity;

    /**
     * This constructor for FlightTickets
     * @param airline company
     * @param destinationCountry dest country
     * @param destinationCity dest city
     * @param originCountry origin country
     * @param originCity origin city
     * @param travelersType type traveler
     * @param TypeOfTicket type ticket
     * @param vacationId id of vacation
     */
    public FlightTickets(String airline, String destinationCountry, String destinationCity, String originCountry,String originCity, int[] travelersType, String TypeOfTicket, int vacationId) {
        Airline = airline;
        DestinationCountry=destinationCountry;
        DestinationCity=destinationCity;

        OriginCountry=originCountry;
        OriginCity= originCity;

        TravelersType = travelersType;
        AmountOfTickets = CalculateAmountOfTickets(travelersType);
        TicketType = TypeOfTicket;

        VacationId = vacationId;

        Calendar cal = Calendar.getInstance();
        Date time=cal.getTime();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=timeFormat.format(time);
        String currTime =formattedDate.replace(":","");
        TicketId = Integer.parseInt(currTime)+8000;
    }

    /**
     * Method which calculates the amount of tickets
     * @param travelersType
     * @return Total amount of tickets
     */
    private int CalculateAmountOfTickets(int[] travelersType) {
        int Amount = 0;
        for (int i = 0; i < travelersType.length; i++) {
            Amount += travelersType[i];
        }
        return Amount;
    }

    /**
     * Getter for TicketId
     * @return TicketId
     */
    public int getTicketId() {
        return TicketId;
    }

    /**
     * Getter for Airline
     * @return Airline
     */
    public String getAirline() {
        return Airline;
    }

    /**
     * Getter for DestinationCountry
     * @return DestinationCountry
     */
    public String getDestinationCountry() {return DestinationCountry; }

    /**
     * Getter for DestinationCity
     * @return DestinationCity
     */
    public String getDestinationCity() {
        return DestinationCity;
    }

    /**
     * Getter for OriginCountry
     * @return OriginCountry
     */
    public String getOriginCountry() {
        return OriginCountry;
    }

    /**
     * Getter for OriginCity
     * @return OriginCity
     */
    public String getOriginCity() {
        return OriginCity;
    }

    /**
     * Getter for TicketType
     * @return TicketType
     */
    public String getTicketType() {
        return TicketType;
    }

    /**
     * Getter for AmountOfTickets
     * @return AmountOfTickets
     */
    public int getAmountOfTickets() {
        return AmountOfTickets;
    }

    /**
     * Getter for BabyTickets
     * @return BabyTickets
     */
    public int getBabyTicketsAmount() {
        return TravelersType[0];
    }

    /**
     * Getter for ChildTickets
     * @return ChildTickets
     */
    public int getChildTicketsAmount() {
        return TravelersType[1];
    }

    /**
     * Getter for AdultTicket
     * @return AdultTicket
     */
    public int getAdultTicketsAmount() {
        return TravelersType[2];
    }

    /**
     * Getter for VacationId
     * @return VacationId
     */
    public int getVacationId() {
        return VacationId;
    }

}

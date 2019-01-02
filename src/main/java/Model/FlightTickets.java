package Model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Collection of flight tickets
 */
public class FlightTickets {

    private String Airline;
    //private Location Destination;
    //private Location Origin;
    private String TicketType; // combo Box -BasicClass, FirstClass, BusinessClass
    private int TicketId;
    private int AmountOfTickets;
    private int[] TravelersType;  // index: 0-Baby , 1-Child , 2-Adult
    private int VacationId;

    private String DestinationCountry;
    private String DestinationCity;

    private String OriginCountry;
    private String OriginCity;

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
        TicketId = Integer.parseInt(currTime);
    }

  /*
    public FlightTickets(String airline, Location destination, Location origin, int[] travelersType, String TypeOfTicket, int vacationId) {
        AirlineP = airline;
        Destination = destination;
        Origin = origin;

        TravelersType = travelersType;
        AmountOfTickets = CalculateAmountOfTickets(travelersType);
        TicketType = TypeOfTicket;

        VacationId = vacationId;

        Random r = new Random();
        int low = TicketID;
        int high = TicketID * 5;
        TicketId = r.nextInt(high - low) + low;
        TicketID += 500;
    }

*/
    /**
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
     * getters
     * @return properties
     */


    public int getTicketId() {
        return TicketId;
    }

    public String getAirline() {
        return Airline;
    }

    public String getDestinationCountry() {return DestinationCountry; }

    public String getDestinationCity() {
        return DestinationCity;
    }

    public String getOriginCountry() {
        return OriginCountry;
    }

    public String getOriginCity() {
        return OriginCity;
    }



/*
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
    */

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

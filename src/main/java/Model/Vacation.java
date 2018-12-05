package Model;

import java.util.HashMap;
import java.util.Map;

public class Vacation {
    private static int VactionID = 200;
    private  FlightTicket FromOriginFlight;
    private FlightTicket FromDestFlight;  /// if it is null - it is not exists
    private Location VacationLocation;
    private String StartDate;
    private String EndDate;               /// use date picker in the gui and convert it to string
    private int Price;
    private Map<Integer,FlightTicket> AllTickets;
    private int VactionId;


    public Vacation(FlightTicket fromOriginFlight,FlightTicket fromDestFlight, Location vacationLocation,
                    String startDate, String endDate,int price) {
        FromOriginFlight = fromOriginFlight;
        FromDestFlight = fromDestFlight;
        VacationLocation = vacationLocation;
        StartDate = startDate;
        EndDate = endDate;
        Price=price;
        AllTickets = new HashMap<Integer,FlightTicket>();
        VactionId=VactionID++;
    }

    public void AddTicket(FlightTicket flightTicket){
        if (!AllTickets.containsKey(flightTicket.GetTicketId())){
            AllTickets.put(flightTicket.GetTicketId(),flightTicket);
        }
    }

    public int GetAmountOfTickets(){
        return AllTickets.size();
    }

    public int GetVacationId(){
        return VactionId;
    }
}

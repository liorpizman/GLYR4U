package Model;

enum TicketType{
    BasicClass,FirstClass,BusinessClass
}

enum TravelerType{
    Baby,Child,Adult
}


public class FlightTicket {

    static int TicketID=100;////// key of the object
    private AirLineCompany Airline ;
    private Location Destination;
    private Location Origin;
    private int TicketType;
    private int TravelerType;
    private  int TicketId;

    public FlightTicket(AirLineCompany airline,Location destination,Location origin, int ticketType, int travelerType) {
        Airline = airline;
        Destination = destination;
        Origin = origin;
        TicketType = ticketType;
        TravelerType = travelerType;
        TicketId=TicketID++;
    }

    public int GetTicketId(){
        return TicketId;
    }
}

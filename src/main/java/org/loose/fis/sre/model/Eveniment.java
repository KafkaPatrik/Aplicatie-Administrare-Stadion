package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;
import org.loose.fis.sre.services.EventService;

public class Eveniment{
    @Id
    public  int event_Id;
    private int event_max_participants;
    private String event_Title;
    private String event_Location;
    private String event_Date;
    private String event_Description;
    private int event_soldTickets;
    private int event_reservedParkingSpots;
    private int event_maxParkingSpots;
    private int event_parkingPrice;
    private int event_ticketPrice;
    public static int event_cnt;


    public void set_event_Id(int event_Id) {
        this.event_Id =event_Id;
    }
    public void set_event_max_participants(int event_max_participants){this.event_max_participants =event_max_participants;}
    public void set_event_Title(String event_Title) {
        this.event_Title =event_Title;
    }
    public void set_event_Location(String event_Location) {this.event_Location =event_Location;}
    public void set_event_Date(String event_Date) {
        this.event_Date =event_Date;
    }
    public void set_event_Description(String event_Description) {
        this.event_Description =event_Description;
    }
    public void set_event_soldTickets(int event_soldTickets){this.event_soldTickets=event_soldTickets;}
    public void set_event_reservedParkingSpots(int event_reservedParkingSpots){this.event_reservedParkingSpots=event_reservedParkingSpots;}
    public void set_event_maxParkingSpots(int event_maxParkingSpots){this.event_maxParkingSpots=event_maxParkingSpots;}
    public void set_event_parkingPrice(int event_parkingPrice){this.event_parkingPrice=event_parkingPrice;}
    public void set_event_ticketPrice(int event_ticketPrice){this.event_ticketPrice=event_ticketPrice;}


    public Eveniment(int event_Id,int event_max_participants,String event_Title,String event_Location,String event_Date,String event_Description,int event_parkingPrice, int event_maxParkingSpots,int event_ticketPrice)
    {
        this.event_max_participants =event_max_participants;
        this.event_Title =event_Title;
        this.event_Location =event_Location;
        this.event_Date =event_Date;
        this.event_Description =event_Description;
        this.event_Id=event_Id;
        this.event_parkingPrice=event_parkingPrice;
        this.event_maxParkingSpots=event_maxParkingSpots;
        this.event_soldTickets=0;
        this.event_reservedParkingSpots=0;
        this.event_ticketPrice=event_ticketPrice;
    }

    public Eveniment() {
    }

    public int get_event_Id() {return event_Id;}

    public int get_event_max_participants() {
        return event_max_participants;
    }

    public String get_event_Title() {return event_Title;}

    public String get_event_Location() {
        return event_Location;
    }

    public String get_event_Date() {return event_Date;}

    public String get_event_Description() {return event_Description;}

    public int getSoldTickets() { return event_soldTickets; }

    public int getReservedParkingSpots() { return event_reservedParkingSpots; }

    public int getMaxParkingSpots(){return event_maxParkingSpots;}

    public int getParkingPrice(){return event_parkingPrice;}

    public int getTicketPrice(){return event_ticketPrice;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Eveniment eveniment = (Eveniment) o;
        if(!(event_Id==eveniment.event_Id))  return false;
        if (event_Title != null ? !event_Title.equals(eveniment.event_Title) : eveniment.event_Title != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = event_Title != null ? event_Title.hashCode() : 0;
        result = 31 * result + (event_Date != null ? event_Date.hashCode() : 0);
        result = 31 * result + (event_Location != null ? event_Location.hashCode() : 0);
        return result;
    }

    public void updateEventSoldTickets()
    {   this.event_soldTickets++;
        EventService.updateEvent(this);
    }

    public void updateReservedParkingSpots() {
        this.event_reservedParkingSpots++;
        EventService.updateEvent(this);
    }

}

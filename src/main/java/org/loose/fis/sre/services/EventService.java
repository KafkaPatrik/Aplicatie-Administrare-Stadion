package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import org.loose.fis.sre.model.Eveniment;

import java.nio.charset.StandardCharsets;
import javafx.collections.ObservableList;
import java.util.Objects;
import org.loose.fis.sre.exceptions.EventAlreadyExistsException;
import javafx.collections.FXCollections;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class EventService {

    private static ObjectRepository<Eveniment> eventRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("events-database.db").toFile())
                .openOrCreate("test", "test");

        eventRepository = database.getRepository(Eveniment.class);
    }

    public static void addEvent(int event_Id,int event_max_participants,String event_Title,String event_Location,String event_Date,String event_Description, int maxParkingSpots, int parkingPrice) throws EventAlreadyExistsException{
        checkEventDoesNotAlreadyExist(event_Id);
        eventRepository.insert(new Eveniment(event_Id,event_max_participants,event_Title,event_Location,event_Date,event_Description, maxParkingSpots, parkingPrice));
    }

    public static boolean modifyEventInfo (int event_Id,int event_max_participants,String event_Title,String event_Location,String event_Date,String event_Description,int event_ParkingPrice)
    {
        for (Eveniment eveniment : eventRepository.find()) {
            if(event_Id==eveniment.get_event_Id())
            {
                eveniment.set_event_Id(event_Id);
                eveniment.set_event_max_participants(event_max_participants);
                eveniment.set_event_Title(event_Title);
                eveniment.set_event_Location(event_Location);
                eveniment.set_event_Date(event_Date);
                eveniment.set_event_Description(event_Description);
                    eventRepository.update(eveniment);
                    return true;
            }
        }
        return false;
    }

    public static Eveniment returnCurrentEvent (int eventId){
        for (Eveniment eveniment : eventRepository.find()) {
            if(eventId==eveniment.get_event_Id())
            {
                    return eveniment;
            }
        }
        return null;
    }
    private static void checkEventDoesNotAlreadyExist(int event_Id) throws EventAlreadyExistsException {
        for (Eveniment eveniment : eventRepository.find()) {
            if (event_Id==eveniment.get_event_Id())
                throw new EventAlreadyExistsException(event_Id);
        }
    }
    public static ObservableList<String> getTitleList() {
        ObservableList<String> s=FXCollections.observableArrayList();
        for (Eveniment event : eventRepository.find()) {
            s.add(event.get_event_Title());
        }
       return s;
    }
    public static Eveniment returnEventByTitle(String title){
        for (Eveniment event : eventRepository.find()) {
            if(Objects.equals(title, event.get_event_Title()))
                return event;
        }
        return null;
    }
}
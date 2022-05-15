package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import org.loose.fis.sre.model.Eveniment;

import java.nio.charset.StandardCharsets;

import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class EventService {

    private static ObjectRepository<Eveniment> eventRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("users-database.db").toFile())
                .openOrCreate("test", "test");

        eventRepository = database.getRepository(Eveniment.class);
    }

    public static void addEvent(int event_Id,int event_max_participants,String event_Title,String event_Location,String event_Date,String event_Description){
        eventRepository.insert(new Eveniment(event_Id,event_max_participants,event_Title,event_Location,event_Date,event_Description));
    }

    public static boolean modifyEventInfo (int event_Id,int event_max_participants,String event_Title,String event_Location,String event_Date,String event_Description)
    {
        for (Eveniment eveniment : eventRepository.find()) {
            if(event_Id==eveniment.get_event_Id())
            {
                eveniment.set_event_Id(event_Id);
                eveniment.set_event_max_participants(event_max_participants);
                eveniment.set_event_Title(event_Title);
                eveniment.set_event_Location(event_Location);
                eveniment. set_event_Date(event_Date);
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
}
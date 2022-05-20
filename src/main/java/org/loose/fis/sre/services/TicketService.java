package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.TicketAlreadyExistsException;
import org.loose.fis.sre.model.Ticket;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;


public class TicketService {

    private static ObjectRepository<Ticket> ticketRepository;

    public static void initTicketsDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("tickets-database.db").toFile())
                .openOrCreate("test", "test");

        ticketRepository = database.getRepository(Ticket.class);
    }

    public static void addTicket(String id_code, String ticketOwnerName, String phoneNumber, String category, double price, boolean hasParkingSpot, String parkingSpot, int id_event) throws TicketAlreadyExistsException {
        checkTicketDoesNotAlreadyExist(id_code);
        String purchaseDateTimeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        ticketRepository.insert(new Ticket(id_code, ticketOwnerName, phoneNumber, purchaseDateTimeStamp, category, price, hasParkingSpot, parkingSpot, id_event));
    }

    private static void checkTicketDoesNotAlreadyExist(String id_code) throws TicketAlreadyExistsException {
        for (Ticket ticket : ticketRepository.find()) {
            if (id_code.equals(ticket.getIdCode()))
                throw new TicketAlreadyExistsException(id_code);
        }
    }

    public static Ticket returnTicket (String ticketID){
        for (Ticket ticket : ticketRepository.find()) {
            if (ticketID.equals(ticket.getIdCode()))
               return ticket;
        }
        return null;
    }

}

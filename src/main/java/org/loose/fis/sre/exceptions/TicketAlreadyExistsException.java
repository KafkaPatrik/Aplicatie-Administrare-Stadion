package org.loose.fis.sre.exceptions;

import org.loose.fis.sre.model.Ticket;

public class TicketAlreadyExistsException extends Exception {
    private String id_code;

    public TicketAlreadyExistsException(String id_code) {
        super("Există deja un bilet cu ID-Codul: "+ id_code + "!");
        this.id_code = id_code;
    }

    public String getId_code() {
        return id_code;
    }

}

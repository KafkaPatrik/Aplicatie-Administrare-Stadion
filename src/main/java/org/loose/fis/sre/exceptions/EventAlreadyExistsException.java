package org.loose.fis.sre.exceptions;

public class EventAlreadyExistsException extends Exception {

    private int event_Id;

    public EventAlreadyExistsException(int event_Id) {
        super(String.format("Există deja un event cu id-ul: %d!", event_Id));
        this.event_Id = event_Id;
    }

    public int getEventId() {
        return event_Id;
    }
}

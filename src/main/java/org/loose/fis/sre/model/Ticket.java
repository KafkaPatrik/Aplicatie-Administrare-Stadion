package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;
import org.loose.fis.sre.controllers.LoginController;

public class Ticket {
    @Id
    private String idCode;
    private String buyerUsername;
    private String ticketOwnerName;
    private String phoneNumber;
    private String purchaseDateTimeStamp;
    private String category;
    private double price;
    private boolean hasParkingSpot;
    private String parkingSpot;
    private int id_event;

    public Ticket(String idCode, String ticketOwnerName, String phoneNumber, String purchaseDate, String category, double price, boolean hasParkingSpot, String parkingSpot, int id_event) {
        this.idCode = idCode;
        this.ticketOwnerName = ticketOwnerName;
        this.phoneNumber = phoneNumber;
        this.purchaseDateTimeStamp = purchaseDate;
        this.category = category;
        this.price = price;
        this.hasParkingSpot = hasParkingSpot;
        this.parkingSpot = parkingSpot;
        this.id_event=id_event;
        this.buyerUsername=LoginController.current_user.getUsername();
    }

    public  Ticket(){
    }

    public String getIdCode() {
        return idCode;
    }

    public String getTicketOwnerName() {
        return ticketOwnerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPurchaseDate() {
        return purchaseDateTimeStamp;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHasParkingSpot() {
        return hasParkingSpot;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public String getBuyerUsername() { return buyerUsername; }

    public void setBuyerUsername(String buyerUsername) { this.buyerUsername = buyerUsername; }

    public String getPurchaseDateTimeStamp() {
        return purchaseDateTimeStamp;
    }

    public int getId_event() {
        return id_event;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Ticket)
            return (((Ticket) o).getIdCode().equals(idCode));
        else return false;
    }

}

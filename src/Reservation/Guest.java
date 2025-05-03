package Reservation;

import java.time.LocalDate;

public class Guest {
    private static int nextGuestID = 1;
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Double discount;
    private int reservationID;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Guest(String name, String surname, String phone, String email) {
        this.id=nextGuestID;
        nextGuestID++;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.discount = 0.0;

    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        String output="Guest ID: "+id+" Guest Name: "+name+" Guest Surname: "+surname+" Phone: "+phone+" Email: "+email+"\n";
        return output;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}

package Reservation;

import Employee.Receptionist;
import RoomOperations.Room;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private static int nextReservationID = 1;
    private int reservationID;
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Room room;
    private ArrayList<Guest> guests;
    private Receptionist receptionist;
    private double payment;
    private String status;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Room room) {
        this.reservationID = nextReservationID;
        nextReservationID++;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.payment = getPaymentAmount();
        this.status="UNPAID";
        this.guests=new ArrayList<>();
        this.payment=getPaymentAmount();
    }

    public String toString(){

        String output="";
        if (getStatus().equals("UNPAID")) {
            output= "Reservation ID: "+reservationID+" Guest Name: "+getGuest().getName()+" Guest Surname: "+getGuest().getSurname()+" Check-In Date: "+checkInDate+" Check-Out Date: "+checkOutDate+" Room Type: "+room.getRoomType()+" Payment Status: \u001B[1m\u001B[31m"+getStatus()+"\033[0m";

        }else if (getStatus().equals("PAID")) {
            output= "Reservation ID: "+reservationID+" Guest Name: "+getGuest().getName()+" Guest Surname: "+getGuest().getSurname()+" Check-In Date: "+checkInDate+" Check-Out Date: "+checkOutDate+" Room Type: "+room.getRoomType()+" Payment Status: \u001B[1m\u001B[32m"+getStatus()+"\033[0m";
        }
        System.out.println(output);
        return output;
    }
    public double getPaymentAmount() {
        long days= ChronoUnit.DAYS.between(checkInDate,checkOutDate);
        double paymentAmount=room.getPrice()*days;
        return paymentAmount;

    }
    public String getCheckInDateString() {
        return checkInDate.format(formatter);
    }
    public String getCheckOutDateString() {
        return checkOutDate.format(formatter);
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }
    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void addGuest(Guest guest) {
        this.guests.add(guest);
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getReservedDaySize(){
        int days= Period.between(checkInDate,checkOutDate).getDays();
        return days;
    }



}

package RoomOperations;

import Reservation.Guest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Room {
    private int roomNumber;
    private String roomType;
    private int capacity;
    private double price;
    private ArrayList<String> reservedDates;
    private ArrayList<Guest> guests;

    public Room(int roomNumber,double price, int capacity, String roomType) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.price = price;
        this.roomType=roomType;
        reservedDates = new ArrayList<>();
        guests = new ArrayList<>();
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public void showRoomDetails(){
        System.out.println("Room Number: "+roomNumber+" Type: "+roomType+" Capacity: "+capacity+" Price: "+price+" $");
    }

    public void Reserve(LocalDate startDate, LocalDate finishDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (LocalDate date = startDate; date.isBefore(finishDate); date=date.plusDays(1)) {
            String d = date.format(formatter);
            reservedDates.add(d);
        }
    }

    public boolean isReserved(int roomID,LocalDate startDate, LocalDate finishDate) {
        boolean b = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (LocalDate date=startDate;date.isBefore(finishDate);date=date.plusDays(1)) {
            String d = date.format(formatter);
            if (reservedDates!=null && reservedDates.contains(d)) {
                b = true;
                break;
            }
        }
        return b;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }

    public abstract int getCapacity();
    public abstract double getPrice();
    public abstract String getRoomType();
   // public abstract void showRoomDetails();

    public ArrayList<String> getReservedDates() {
        return reservedDates;
    }

}

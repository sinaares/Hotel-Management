package HotelSystem;
import Reservation.Guest;
import Reservation.Reservation;
import RoomOperations.Room;
import Employee.*;
import java.io.File;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import Reservation.Income;
import Boss.Boss;
import RoomOperations.*;

public class Hotel {
    private static Hotel instance;
    private String hotelName;
    private ArrayList<Room> rooms;
    private ArrayList<Guest> guests;
    private ArrayList<Reservation> reservations;
    public ArrayList<Income> incomes;
    public ArrayList<Outcome> outcomes;
    private ArrayList<Employee> employees;
    private ArrayList<Boss> bosses;

    private Hotel(String hotelName) {
        this.hotelName = hotelName;
        constructHotel();
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
    public ArrayList<Outcome> getOutcomes() {
        return outcomes;
    }



    // Public method to provide access to the instance
    public static Hotel getInstance(String hotelName) {
        if (instance == null) {
            instance = new Hotel(hotelName);
        }
        return instance;
    }
    public  void constructHotel() {
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
        employees = new ArrayList<>();
        reservations = new ArrayList<>();
        incomes = new ArrayList<>();
        bosses = new ArrayList<>();
        outcomes=new ArrayList<>();

        // for trial purpose adding some person with different roles
        Boss boss=new Boss("system","system");
        bosses.add(boss);
        /*HumanResource humanResource=new HumanResource("system","system","null","null","system","system");
        humanResource.setHiredDate(LocalDate.of(2024,4,1));
        employees.add(humanResource);
        Receptionist receptionist= new Receptionist("recep1","recep1","null","null","recep1","recep1");
        receptionist.setHiredDate(LocalDate.of(2024,4,1));
        Receptionist receptionist1= new Receptionist("recep2","recep2","null","null","recep2","recep2");
        receptionist1.setHiredDate(LocalDate.of(2024,4,1));*/
        /*employees.add(receptionist);
        employees.add(receptionist1);*/

        StandartRoom standartRoom;
        SuiteRoom suiteRoom;
        DeluxeRoom deluxeRoom;

        for (int i = 1; i < 101; i++) {
            standartRoom = new StandartRoom(i);
            rooms.add(standartRoom);
        }
        for (int i = 101; i < 201; i++) {
            deluxeRoom = new DeluxeRoom(i);
            rooms.add(deluxeRoom);
        }

        for (int i = 201; i < 301; i++) {
            suiteRoom = new SuiteRoom(i);
            rooms.add(suiteRoom);
        }





    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    public ArrayList<Guest> getGuests() {
        return guests;
    }
    public ArrayList<Income> getIncomes() {
        return incomes;
    }
    public ArrayList<Boss> getBosses() {
        return bosses;
    }
    public ArrayList<Room> getRooms() {
        return rooms;
    }




}

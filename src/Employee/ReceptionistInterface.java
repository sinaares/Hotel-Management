package Employee;

import Reservation.Guest;
import Reservation.*;
import RoomOperations.Room;

import java.time.LocalDate;

public interface ReceptionistInterface {
    public Receptionist receptionistAuthentication();
    public Guest getGuestByName(String name, String surname);
    public Reservation createReservation();
    public Room getRoomByRoomNumber(int roomNumber);
    public boolean checkDateValidation(LocalDate from, LocalDate to);
    public void getGuestInformation();
    public Reservation getReservationByReservationIDforPayment();
    public Reservation getReservationByReservationID();
    public Room getRoomByNumber(int number);
    public Reservation getReservationByReservationIDforPayment(int reservationID);
    public void receivePayment(Receptionist receptionist);
    public void removeReservation();
    public void showAllReservations();
    public void calculatePayment();
    public void listReservations(LocalDate from, LocalDate to);
    public void listEmployees();
    public boolean isReserved(int roomID,LocalDate startDate, LocalDate finishDate);
}

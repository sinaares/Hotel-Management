package Reservation;

import Employee.Receptionist;

import java.time.LocalDate;

public class Income {
    private static int nextID=1;
    private int id;
    private double paymentAmount;
    private LocalDate paymentDate;
    private Receptionist receptionist;
    private Guest guest;

    public Income(Double paymentAmount,LocalDate paymentDate, Guest guest, Receptionist receptionist) {
        this.id = nextID;
        nextID++;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.receptionist = receptionist;
        this.guest = guest;
    }
    public Income(Double paymentAmount, LocalDate paymentDate, Guest guest) {
        this.id = nextID;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }
    public double getPaymentAmount() {
        return paymentAmount;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public String toString(){
        String output="";
        output= "Income ID: "+this.id+" Guest Name: "+getGuest().getName()+" Guest Surname"+getGuest().getSurname()+" Payment Amount: "+getPaymentAmount()+" Employee ID: "+getReceptionist().getId()+ " Employee Name: "+getReceptionist().getName()+"\n";
        System.out.println(output);
        return output;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}

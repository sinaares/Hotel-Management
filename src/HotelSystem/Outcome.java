package HotelSystem;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Outcome {
    private double amount;
    private String reason;
    private LocalDate date;
    public Outcome(double amount, String reason, LocalDate date) {
        this.amount = amount;
        this.reason = reason;
        this.date = date;
    }
    public double getAmount() {
        return amount;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.00");

        String output="Outcome: Date: "+date.toString()+", Amount: \u001B[1m\u001B[31m"+df.format(amount)+", \033[0mReason: \u001B[1m\u001B[31m"+reason+"\033[0m";
        return output;
    }
}

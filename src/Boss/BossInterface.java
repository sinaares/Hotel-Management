package Boss;

import Employee.Receptionist;

import java.time.LocalDate;

public interface BossInterface {
    public void listPerformanceOfEmployees();
    public Receptionist getBestEmployee();
    public void listReservations(LocalDate from, LocalDate to);
    public String getOccupancy(LocalDate from, LocalDate to);
    public void  showIncomeList(LocalDate from, LocalDate to);
    public double getIncome(LocalDate from, LocalDate to);
    public double getOutcome(LocalDate from, LocalDate to);
    public void showProfit(LocalDate from, LocalDate to);
    public void listReceptionEmployees();
    public void listHouseKeeperEmployees();
    public void addHumanResourceEmployee();
}

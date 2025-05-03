package Employee;

import HotelSystem.Hotel;

public class Receptionist extends Employee{
    private static final String DEPARTMENT="Receptionist";
    private static final double SALARY=50000.0;
    private String username;
    private String password;
    private double bonus;
    private int performance;



    public Receptionist(String name, String surname, String email, String phone, String username, String password) {
        super(name, surname, email, phone, DEPARTMENT, SALARY);
        this.username=username;
        this.password=password;
        this.performance=0;
    }
    public String getDepartment() {
        return DEPARTMENT;
    }
    public double getSalary(){
        return SALARY;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public Receptionist(){
        super();

    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }
    public void setBonus(double bonus){
        this.bonus = bonus;
    }
}

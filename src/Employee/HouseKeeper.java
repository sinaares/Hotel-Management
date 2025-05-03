package Employee;

public class HouseKeeper extends Employee {
    private static final String DEPARTMENT="HouseKeeper";
    private static final double SALARY=30000.0;


    public HouseKeeper(String name, String surname, String email, String phone) {
        super(name, surname, email, phone, DEPARTMENT, SALARY);
    }

    public String getDepartment() {
        return DEPARTMENT;
    }
    public double getSalary() {
        return SALARY;
    }
    public int getPerformance(){
        return 0;
    }
}

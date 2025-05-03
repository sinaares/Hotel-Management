package Employee;

public class HumanResource extends Employee{
    private static final String DEPARTMENT="Human Resources";
    private static final double SALARY=60000.0;
    private String username;
    private String password;

    public HumanResource(String name, String surname, String email, String phone, String username, String password) {
        super(name, surname, email, phone, DEPARTMENT, SALARY);
        this.username = username;
        this.password = password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getPerformance(){
        return 0;
    }
}

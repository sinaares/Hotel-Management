package Employee;

import java.time.LocalDate;

public abstract class Employee {
    private static int nextId = 1;
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String department;
    private double salary;
    private String username;
    private String password;
    private LocalDate hiredDate;
    public Employee(String name, String surname, String email, String phone, String department, double salary) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.salary = salary;
        this.hiredDate = LocalDate.now();
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

    public Employee(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String toString(){
        String output="Employee ID: "+id+" Name: " + name + " Surname: " + surname + " Email: " + email+" Phone: " + phone+" Department: " + department+" Salary: " + salary;
        System.out.println(output);
        return output;
    }

    public abstract int getPerformance();


    public abstract String getDepartment();
    public abstract double getSalary();

    public int getNextId(){
        return nextId;
    }
    public void setNextId(int nextId){
        this.nextId = nextId;
    }

    public LocalDate getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }
}

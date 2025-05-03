package HotelSystem;

import Employee.Receptionist;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Employee.Employee;
import Employee.*;
import Boss.Boss;
import Reservation.Guest;
import Reservation.Reservation;
import RoomOperations.Room;
import Reservation.Income;
import Boss.BossInterface;

import java.time.temporal.ChronoUnit;

public class CommonHotelSystem implements BossInterface, HumanResourceInterface,ReceptionistInterface {
    Hotel hotel;
    public CommonHotelSystem(Hotel hotel) {
        this.hotel = hotel;
    }
    public void addEmployee(){
        Scanner scanner=new Scanner(System.in);
        String name=getNameFromUser();
        Scanner scanner1=new Scanner(System.in);
        String surname=getSurnameFromUser();
        System.out.println("Enter employee e-mail: ");
        String email=scanner.nextLine();
        String phone =getPhoneFromUser();
        System.out.println("Enter employee department: (1 for Receptionist, 2 for House Keeper");
        int department=scanner.nextInt();
        if (department==1){
            System.out.println("Please assign a username");
            String username=scanner1.nextLine();
            System.out.println("Please assign a password");
            String password=scanner1.nextLine();
            Receptionist receptionist= new Receptionist(name,surname,email,phone,username,password);
            hotel.getEmployees().add(receptionist);
            System.out.println("\u001B[1m\u001B[32mEmployee '"+name+" "+surname+"' added successfully as '"+"Receptionist"+"' .\033[0m");
        } else if (department==2){
            HouseKeeper houseKeeper= new HouseKeeper(name,surname,email,phone);
            hotel.getEmployees().add(houseKeeper);
            System.out.println("\u001B[1m\u001B[32mEmployee '"+name+" "+surname+"' added successfully as '"+"House Keeper"+"' .\033[0m");

        }else{
            System.out.println("\u001B[1m\u001B[31mPlease enter a valid department\033[0m");
        }

    }
    public HumanResource humanResourceAuthentication(){
        ArrayList<Employee> employees=hotel.getEmployees();
        HumanResource humanResource;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username=scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password=scanner.nextLine();

        for (Employee employee : employees) {
            if (employee.getDepartment().equalsIgnoreCase("Human Resources")){
                humanResource=(HumanResource) employee;
                if (humanResource.getUsername().equals(username)&&humanResource.getPassword().equals(password)){
                    System.out.println("\u001B[1m\u001B[32mSession opened as "+humanResource.getName()+"\033[0m");
                    System.out.println("--------------Welcome--------------");
                    return humanResource;
                }
            }
        }
        System.out.println("\u001B[1m\u001B[31mInvalid username or password\033[0m");
        return null;
    }
    public Receptionist receptionistAuthentication(){
        ArrayList<Employee> employees=hotel.getEmployees();
        Receptionist receptionist;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username=scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password=scanner.nextLine();
        for (Employee employee : employees) {
            if (employee.getDepartment().equals("Receptionist")){
                receptionist=(Receptionist) employee;
                if (receptionist.getUsername().equals(username)&&receptionist.getPassword().equals(password)){
                    System.out.println("\u001B[1m\u001B[32mSession opened as "+receptionist.getName()+"\033[0m");
                    System.out.println("--------------Welcome--------------");
                    return receptionist;

                }

            }
        }
        System.out.println("\u001B[1m\u001B[31mInvalid username or password\033[0m");
        return null;
    }
    public Boss bossAuthentication(){
        ArrayList<Boss> bosses=hotel.getBosses();
        Boss boss;

        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username=scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password=scanner.nextLine();
        for (Boss boss_ : bosses) {
                if (boss_.getUsername().equals(username)&&boss_.getPassword().equals(password)){
                    System.out.println("\u001B[1m\u001B[32mSession opened as "+boss_.getName()+"\033[0m");
                    System.out.println("--------------Welcome--------------");
                    boss=boss_;
                    return boss;
                }
        }
        System.out.println("\u001B[1m\u001B[31mInvalid username or password\033[0m");


        return null;

    }
    public static void loginPage(String userType) {
        System.out.println("--------------------------------------------------");
        System.out.println("**** Welcome to " + userType + " Management System ***");
        System.out.println("--------------------------------------------------");
        System.out.println();
        System.out.println("**    "+userType+" Verification    **");
    }
    public Guest getGuestByName(String name, String surname){
        Guest guest_ = null;
        int  counter=0;
        for (Guest guest : hotel.getGuests()) {
            if (guest.getName().equals(name) && guest.getSurname().equals(surname)){
                counter++;
                guest_=guest;
                System.out.println(guest.toString());
            }
        }
        if (counter==1) {
            return guest_;
        } else if (counter>=2){
            System.out.println("Please select a guest id form the list: ");
            Scanner scanner=new Scanner(System.in);
            int guest_id=scanner.nextInt();
            for (Guest guest : hotel.getGuests()) {
                if (guest_id==guest.getId()){
                    return guest;
                }
            }

        }
        return null;

    }
    public Reservation createReservation(){
        boolean intersection=false;
        LocalDate checkin;
        LocalDate checkout;
        Scanner scanner=new Scanner(System.in);
        Guest guestOfReservation=null;
        boolean validRoomType=false;
        System.out.println("Please enter room type ( 1 for Standard, 2 for Deluxe, 3 Suite)");

        while (!scanner.hasNextInt()&& (scanner.nextInt()<1 || scanner.nextInt()>3)){
            System.out.println("Invalid input, please select 1, 2, or 3");
            scanner.next();
        }
        int roomType=scanner.nextInt();

        do {
            if (roomType==1){ // Get only one guest information for Standard room
                validRoomType=true;
                do {
                    System.out.println("Please enter Check-in Date: (in format 'YYYY-MM-DD') ");
                    checkin=getDateFromUser();
                    System.out.println("Please enter Check-out Date: (in format 'YYYY-MM-DD') )");
                    checkout=getDateFromUser();
                }while (!checkDateValidation(checkin,checkout));

                int roomNumber=0;
                Scanner scanner1=new Scanner(System.in);
                do {

                        System.out.println("Please enter a room number ( Between 1 and 100 )");
                        String input=scanner1.nextLine();
                        try {
                            roomNumber=Integer.parseInt(input);
                        }catch (NumberFormatException e){
                            System.out.println("Not valid room number");
                            roomNumber=0;
                        }


                }while (roomNumber<1 || roomNumber>100);
                Room room=getRoomByRoomNumber(roomNumber);
                if (!room.isReserved(roomNumber, checkin, checkout)){
                    System.out.println("--Guest Informations--");
                    Scanner sc=new Scanner(System.in);
                    String name=getNameFromUser();
                    String surname=getSurnameFromUser();
                    String phone=getPhoneFromUser();
                    System.out.println("Please enter guest e-mail: ");
                    String email=sc.nextLine();

                    Guest guestCheck=null;

                    for (Guest guest: hotel.getGuests()) {
                        if (guest.getName().equals(name) && guest.getSurname().equals(surname)&&guest.getPhone().equals(phone)&&guest.getEmail().equals(email)){
                            System.out.println("\u001B[1m\u001B[32mGuest is  already registered in system\033[0m");
                            guest.toString();
                            guestCheck=guest;
                        }
                    }
                    if (guestCheck!=null){ // Konuk zaten sistemde kayıtlı demek
                        guestOfReservation=guestCheck;
                        if (guestOfReservation.getCheckInDate().isBefore(checkout) && guestOfReservation.getCheckOutDate().isAfter(checkin)){
                            intersection=true;
                        }
                    }else{ // Konuk sistemde kayıtlı değil yeni konuk kaydı yapılacak
                        guestOfReservation=new Guest(name,surname,phone,email);
                        hotel.getGuests().add(guestOfReservation);
                    }

                    //Guest guest=getGuestByName(name,surname);
                    if (!intersection){
                        room.Reserve(checkin,checkout);
                        Reservation reservation=new Reservation(checkin,checkout,room);
                        reservation.setGuest(guestOfReservation);
                        guestOfReservation.setReservationID(reservation.getReservationID());
                        guestOfReservation.setCheckInDate(checkin);
                        guestOfReservation.setCheckOutDate(checkout);
                        reservation.setGuest(guestOfReservation);
                        hotel.getReservations().add(reservation);
                        System.out.println("\u001B[1m\u001B[32mReservation created successfully !\033[0m");
                        return reservation;
                    }else{
                        System.out.println("\u001B[1m\u001B[31mThe guest is registered to another room within this date range\033[0m");
                        return null;
                    }



                }else{
                    System.out.println("\u001B[1m\u001B[31mThe room is not available for that days !\033[0m");
                    System.out.println();
                    return null;
                }


            }else if (roomType==2){
                validRoomType=true;

                do {
                    System.out.println("Please enter Check-in Date: (in format 'YYYY-MM-DD') ");
                    checkin=getDateFromUser();
                    System.out.println("Please enter Check-out Date: (in format 'YYYY-MM-DD') )");
                    checkout=getDateFromUser();
                }while (!checkDateValidation(checkin,checkout));

                int roomNumber=0;
                Scanner scanner1=new Scanner(System.in);
                do {

                    System.out.println("Please enter a room number ( Between 101 and 200 )");
                    String input=scanner1.nextLine();
                    try {
                        roomNumber=Integer.parseInt(input);
                    }catch (NumberFormatException e){
                        System.out.println("Not valid room number");
                        roomNumber=0;
                    }


                }while (roomNumber<101 || roomNumber>200);
                Room room=getRoomByRoomNumber(roomNumber);
                if (!room.isReserved(roomNumber, checkin, checkout)){
                    String name1=getNameFromUser();
                    String surname1=getSurnameFromUser();
                    Guest guest1=getGuestByName(name1,surname1);

                    if (guest1==null){
                        scanner1=new Scanner(System.in);
                        String phone=getPhoneFromUser();
                        System.out.println("Please enter guest email: ");
                        String email=scanner1.nextLine();
                        guest1=new Guest(name1,surname1,phone,email);
                        hotel.getGuests().add(guest1);
                    }
                    String name2=getNameFromUser();
                    String surname2=getSurnameFromUser();
                    Guest guest2=getGuestByName(name2,surname2);
                    if (guest2==null){
                        scanner1=new Scanner(System.in);
                        String phone=getPhoneFromUser();
                        System.out.println("Please enter guest email: ");
                        String email=scanner1.nextLine();
                        guest2=new Guest(name2,surname2,phone,email);
                        hotel.getGuests().add(guest2);

                    }
                    room.Reserve(checkin,checkout);
                    Reservation reservation=new Reservation(checkin,checkout,room);
                    reservation.setGuest(guest1);
                    guest1.setReservationID(reservation.getReservationID());
                    guest2.setReservationID(reservation.getReservationID());
                    reservation.addGuest(guest1);
                    reservation.addGuest(guest2);
                    hotel.getReservations().add(reservation);
                    System.out.println("\u001B[1m\u001B[32mReservation is created successfully !\033[0m");
                    return reservation;
                }else{
                    System.out.println("\u001B[1m\u001B[31mThe room is not available for that days !\033[0m");
                    System.out.println();
                    return null;
                }

            }else if (roomType==3){
                validRoomType=true;
                do {
                    System.out.println("Please enter Check-in Date: (in format 'YYYY-MM-DD') ");
                    checkin=getDateFromUser();
                    System.out.println("Please enter Check-out Date: (in format 'YYYY-MM-DD') )");
                    checkout=getDateFromUser();
                }while (!checkDateValidation(checkin,checkout));

                int roomNumber=0;
                Scanner scannerTemp=new Scanner(System.in);
                do {

                    System.out.println("Please enter a room number ( Between 201 and 300 )");
                    String input=scannerTemp.nextLine();
                    try {
                        roomNumber=Integer.parseInt(input);
                    }catch (NumberFormatException e){
                        System.out.println("Not valid room number");
                        roomNumber=0;
                    }


                }while (roomNumber<201 || roomNumber>300);
                Room room=getRoomByRoomNumber(roomNumber);
                if (!room.isReserved(roomNumber, checkin, checkout)){
                    String name1=getNameFromUser();
                    String surname1=getSurnameFromUser();
                    Guest guest1=getGuestByName(name1,surname1);
                    if (guest1==null){
                        Scanner scanner1=new Scanner(System.in);
                        String phone=getPhoneFromUser();
                        System.out.println("Please enter guest email: ");
                        String email=scanner1.nextLine();
                        guest1=new Guest(name1,surname1,phone,email);
                        hotel.getGuests().add(guest1);
                    }
                    System.out.println("Please enter second guest information's");
                    String name2=getNameFromUser();
                    String surname2=getSurnameFromUser();
                    Guest guest2=getGuestByName(name2,surname2);
                    if (guest2==null){
                        Scanner scanner1=new Scanner(System.in);
                        String phone=getPhoneFromUser();
                        System.out.println("Please enter guest email: ");
                        String email=scanner1.nextLine();
                        guest2=new Guest(name2,surname2,phone,email);
                        hotel.getGuests().add(guest2);
                    }
                    System.out.println("Please enter third guest information's");
                    String name3=getNameFromUser();
                    String surname3=getSurnameFromUser();
                    Guest guest3=getGuestByName(name3,surname3);
                    if (guest3==null){
                        Scanner scanner1=new Scanner(System.in);
                        String phone=getPhoneFromUser();
                        System.out.println("Please enter guest email: ");
                        String email=scanner1.nextLine();
                        guest3=new Guest(name3,surname3,phone,email);
                        hotel.getGuests().add(guest3);
                    }
                    room.Reserve(checkin,checkout);
                    Reservation reservation=new Reservation(checkin,checkout,room);
                    reservation.setGuest(guest1);
                    guest1.setReservationID(reservation.getReservationID());
                    guest2.setReservationID(reservation.getReservationID());
                    guest3.setReservationID(reservation.getReservationID());
                    reservation.addGuest(guest1);
                    reservation.addGuest(guest2);
                    reservation.addGuest(guest3);
                    hotel.getReservations().add(reservation);
                    System.out.println("\u001B[1m\u001B[32mThe reservation is done successfully !\033[0m");
                    System.out.println();
                    return reservation;
                }else{
                    System.out.println("\u001B[1m\u001B[31mThe room is not available for that days !\033[0m");
                    System.out.println();
                    return null;
                }

            }else{
                System.out.println("Please select a room type (Standard, Deluxe, Suite)");
                return null;
            }
        }while (!validRoomType);




    }
    public void createReservation(Reservation reservation){
        hotel.getReservations().add(reservation);
    }
    public String getNameFromUser(){
        String name="";
        Scanner scanner=new Scanner(System.in);
        while (!isValidName(name)){
            System.out.println("Please enter name: ");
            name=scanner.nextLine();
            if (!isValidName(name)){
                System.out.println("The name must consists only characters !");
            }
        }
        return name;
    }
    public String getSurnameFromUser(){
        String surname="";
        Scanner scanner=new Scanner(System.in);
        while (!isValidName(surname)){
            System.out.println("Please enter surname: ");
            surname=scanner.nextLine();
            if (!isValidName(surname)){
                System.out.println("The surname must consists only characters !");
            }
        }
        return surname;
    }
    public String getPhoneFromUser(){
        String phone="";
        Scanner scanner=new Scanner(System.in);
        while (!isValidPhoneNumber(phone)){
            System.out.println("Please enter phone number: ");
            phone=scanner.nextLine();
            if (!isValidPhoneNumber(phone)){
                System.out.println("The phone number must consists only digits !");
            }
        }
        return phone;
    }
    public int getIDfromUser(){
        Scanner scanner=new Scanner(System.in);
        int id=0;
        while (!isValidID(id)){
            System.out.println("Please enter id: ");
            String input=scanner.nextLine();
            try{
                id=Integer.parseInt(input);

            }catch(NumberFormatException e){
                System.out.println("Invalid ID, please enter an positive integer !");
            }

        }
        return id;

    }
    public LocalDate getDateFromUser(){
        LocalDate date=null;
        Scanner scanner=new Scanner(System.in);
        do {
            //System.out.println("Please enter a valid date in format (YYYY-MM-DD)");
            String input=scanner.nextLine();
            try{
                date=LocalDate.parse(input);

            }catch (DateTimeParseException e){
                System.out.println("\u001B[1m\u001B[31mInvalid date format, please enter a valid date !\033[0m");
            }
        }while (date==null);

        return date;
    }
    public Room getRoomByRoomNumber(int roomNumber){
        for (Room room: hotel.getRooms()){
            if (room.getRoomNumber()==roomNumber){
                return room;
            }
        }
        return null;
    }
    public boolean checkDateValidation(LocalDate checkIn, LocalDate checkOut){
        LocalDate currentDate=LocalDate.now();
        boolean flag=true;
        if (currentDate.isAfter(checkOut)){
            flag=false;
        }else if (checkOut.isBefore(checkIn)){
            flag=false;
        }else if (currentDate.isAfter(checkIn)){
            flag=false;
        }

        if (!flag){
            System.out.println("\u001B[1m\u001B[31mInvalid date please check the date !\033[0m");
        }

        return flag;
    }
    public void getGuestInformation(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter Guest ID: ");

    }
    public static int bossHomePage() {
        int choice = -1;
        System.out.println("\033[0m");

        System.out.println("* * * Please Select What to do: * * * ");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. List Reservations");
        System.out.println("2. List All Employees");
        System.out.println("3. List Human Resources Employees");
        System.out.println("4. List Reception Employees");
        System.out.println("5. List HouseKeeper Employees");
        System.out.println("6. Add Human Resource Employee");
        System.out.println("7. List Performance of Receptionists");
        System.out.println("8. Get the Best Receptionist");
        System.out.println("9. Show Occupancy");
        System.out.println("10. Show Incomes");
        System.out.println("11. Show Outcomes");
        System.out.println("12. Show Profit");
        System.out.println("13. Set Bonus to Best Employee");
        System.out.println("14.Exit");
        choice = scanner.nextInt();
        if (choice < 1 || choice > 14) {
            System.out.println("\u001B[1m\u001B[31mInvalid choice ! Please select a valid choice from the menu!\033[0m");

            choice = -1;
        }


        return choice;

    }
    public static int humanResourceHomePage() {
        int choice = -1;
        System.out.println("* * * Please Select What to do: * * * ");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add Employee");
        System.out.println("2. Show All Employees");
        System.out.println("3. Change Employee Department ");
        System.out.println("4. Search Employee by ID");
        System.out.println("5. Exit");
        choice = scanner.nextInt();
        if (choice < 1 || choice > 5) {
            System.out.println("\u001B[1m\u001B[31mInvalid choice ! Please select a valid choice from the menu!\033[0m");
            System.out.println();
            choice = -1;
        }


        return choice;


    }
    public static int receptionistHomePage() {
        int choice = -1;
        System.out.println("* * * Please Select What to do: * * * ");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Create Reservation");
        System.out.println("2. Remove Reservation");
        System.out.println("3. Get Reservation by Guest Name ");
        System.out.println("4. Get Reservation by Guest ID");
        System.out.println("5. Show All Reservations");
        System.out.println("6. Calculate Payment");
        System.out.println("7. Room Availability Check");
        System.out.println("8. Get Reservation by Reservation ID");
        System.out.println("9. Get Payment from Guest ");
        System.out.println("10. Exit");
        choice = scanner.nextInt();
        if (choice < 1 || choice > 10) {
            System.out.println("\u001B[1m\u001B[31mInvalid choice ! Please select a valid choice from the menu!\033[0m");
            System.out.println();
            choice = -1;
        }
        return choice;

    }
    public static int HomePage() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        //System.out.println("****  Please select user type  ****");
        System.out.println();
        System.out.println("1. for Boss");
        System.out.println("2. for Human Resource");
        System.out.println("3. for Receptionist");
        System.out.println("4. Close the system");
        choice = scanner.nextInt();
        return choice;
    }
    public Reservation getReservationByGuestName() {
        Guest tempGuest;
        String guestName=getNameFromUser();
        for (Reservation reservation: hotel.getReservations()){
            for (Guest guest: reservation.getGuests()){
                if (guest.getName().equals(guestName)){
                    return reservation;
                }
            }
        }

        return null;
    }


    public static void showOutcomes(Hotel hotel, LocalDate from, LocalDate to) {
        double personalExpenses = 0.0;

        // Çalışanların maaş giderlerini hesapla
        for (Employee employee : hotel.getEmployees()) {
            LocalDate hireDate = employee.getHiredDate();

            if (hireDate.isBefore(from) || hireDate.equals(from)) {
                int inPeriod = (int) ChronoUnit.DAYS.between(from, to.plusDays(1)); // 'to' tarihini de dahil etmek için
                double dailySalary = employee.getSalary() / 30.0;
                double employeeExpense = dailySalary * inPeriod;
                personalExpenses += employeeExpense;
            } else if (hireDate.isAfter(from) && (hireDate.isBefore(to) || hireDate.equals(to))) {
                int inPeriod = (int) ChronoUnit.DAYS.between(hireDate, to.plusDays(1)); // 'to' tarihini de dahil etmek için
                double dailySalary = employee.getSalary() / 30.0;
                double employeeExpense = dailySalary * inPeriod;
                personalExpenses += employeeExpense;
            }
        }

        boolean personalExpensesExists = false;

        // Eğer kişisel giderler zaten varsa, güncelle
        for (Outcome outcome : hotel.getOutcomes()) {
            if (outcome.getReason().equalsIgnoreCase("Personal Expenses") &&
                    (outcome.getDate().isAfter(from.minusDays(1)) && outcome.getDate().isBefore(to.plusDays(1)))) {
                outcome.setAmount(personalExpenses); // Mevcut kişisel giderleri güncelle
                personalExpensesExists = true;
                break;
            }
        }

        // Eğer kişisel giderler yoksa, yeni bir kayıt ekle
        if (!personalExpensesExists) {
            Outcome personalOutcome = new Outcome(personalExpenses, "Personal Expenses", LocalDate.now());
            hotel.getOutcomes().add(personalOutcome);
        }

        boolean personalExpensesPrinted = false;

        // Diğer giderleri ve kişisel giderleri listele
        for (Outcome outcome : hotel.getOutcomes()) {
            if (outcome.getDate().isAfter(from.minusDays(1)) && outcome.getDate().isBefore(to.plusDays(1))) {
                if (!outcome.getReason().equalsIgnoreCase("Personal Expenses")) {
                    System.out.println(outcome.toString());
                } else if (!personalExpensesPrinted) {
                    System.out.println(outcome.toString());
                    personalExpensesPrinted = true;
                }
            }
        }

        if (!personalExpensesPrinted) {
            for (Outcome outcome : hotel.getOutcomes()) {
                if (outcome.getReason().equalsIgnoreCase("Personal Expenses")) {
                    System.out.println(outcome.toString());
                }
            }
        }
    }

    public void listHumanResourceEmployees(){
        int counter=0;
        System.out.println("Human Resource Employees");
        System.out.println();
        for (Employee employee: hotel.getEmployees()){
            if (employee.getDepartment().equalsIgnoreCase("human resources")){
                employee.toString();
                counter++;
            }
        }
        if (counter==0){
            System.out.println("\u001B[1m\u001B[32mThere is no House Keeper\033[0m");
        }
    }
    public void listReceptionEmployees(){
        int counter=0;
        System.out.println("Reception Employees");
        for (Employee employee: hotel.getEmployees()){
            if (employee.getDepartment().equalsIgnoreCase("Receptionist")){
                employee.toString();
                counter++;
            }
        }
        if (counter==0){
            System.out.println("\u001B[1m\u001B[32mThere is no Receptionist\033[0m");
        }
    }
    public void listHouseKeeperEmployees(){
        int counter=0;
        System.out.println("Housekeeper Employees");
        for (Employee employee: hotel.getEmployees()){
            if (employee.getDepartment().equalsIgnoreCase("Housekeeper")){
                employee.toString();
                counter++;
            }
        }
        if (counter==0){
            System.out.println("\u001B[1m\u001B[32mThere is no Housekeeper Employees\033[0m");
        }
    }
    public void addHumanResourceEmployee(){
        boolean isfound=false;
        Scanner scanner = new Scanner(System.in);
        String name="";
        String surname="";
        String phone="";
        while (!isValidName(name)){
            System.out.println("Enter the name of employee: ");
            name = scanner.nextLine();
            if (!isValidName(name)){
                System.out.println("\u001B[1m\u001B[31mThe name must consists of characters only ! \033[0m");
            }
        }

        while (!isValidName(surname)){
            System.out.println("Enter the surname of employee: ");
            surname = scanner.nextLine();
            if (!isValidName(surname)){
                System.out.println("\u001B[1m\u001B[31mThe surname must consists of characters only ! \033[0m");
            }
        }

        while (!isValidPhoneNumber(phone)){
            System.out.println("Enter the phone of employee: ");
            phone = scanner.nextLine();
            if (!isValidPhoneNumber(phone)){
                System.out.println("\u001B[1m\u001B[31mThe phone number must consists of digits only ! \033[0m");
            }
        }


        System.out.println("Enter the e-mail of employee: ");
        String empEmail=scanner.nextLine();
        System.out.println("Please assign an username to employee: ");
        String empUsername=scanner.nextLine();
        System.out.println("Enter assign a password of employee: ");
        String empPassword=scanner.nextLine();
        for (Employee employee: hotel.getEmployees()){
            if (employee.getDepartment().equalsIgnoreCase("human resources")){
                if (employee.getName().equals(name)&&employee.getSurname().equals(surname)&&employee.getEmail().equals(empEmail)&&employee.getPhone().equals(phone)){
                    System.out.println("\u001B[1m\u001B[31mThis employee is already registered in the system\033[0m");
                    isfound=true;
                    break;
                }
            }
        }
        if (!isfound){
            HumanResource humanResource=new HumanResource(name,surname,empEmail,phone,empUsername,empPassword);
            hotel.getEmployees().add(humanResource);
            System.out.println("\u001B[1m\u001B[32mHuman resource employee added to system successfully !\033[0m");
        }

    }
    public boolean isValidName(String name){
        return name.matches("[a-zA-ZçÇğĞıİöÖşŞüÜ]+");
    }
    private static boolean isValidPhoneNumber(String phoneNumber) {
        // Telefon numarası yalnızca sayılardan oluşmalıdır
        return phoneNumber.matches("[0-9]+");
    }
    private static boolean isValidID(int id) {
        // Telefon numarası yalnızca sayılardan oluşmalıdır
        return id>0;
    }
    public Reservation getReservationByReservationIDforPayment() {
        for(Reservation reservation: hotel.getReservations()){
            if (reservation.getStatus().equals("UNPAID")){
                reservation.toString();
            }

        }
        int reservationID = getIDfromUser();
        for (Reservation reservation: hotel.getReservations()){
            if (reservation.getReservationID() == reservationID){
                return reservation;
            }
        }
        return null;
    }
    public Reservation getReservationByReservationID() {
        System.out.println("Please enter reservation id for receiving payment");
        int reservationID = getIDfromUser();
        for (Reservation reservation: hotel.getReservations()){
            if (reservation.getReservationID() == reservationID){
                return reservation;
            }
        }
        return null;
    }
    public Room getRoomByNumber(int number){
        for(Room room: hotel.getRooms()){
            if (room.getRoomNumber() == number){
                return room;
            }
        }

        return null;
    }
    public Reservation getReservationByReservationIDforPayment(int reservationID) {
        for (Reservation reservation: hotel.getReservations()){
            if (reservation.getReservationID() == reservationID){
                return reservation;
            }
        }
        return null;
    }
    public  void checkAndCreateFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File created: " + filename);
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }
    public void receivePayment(Receptionist receptionist){
        Reservation reservation = getReservationByReservationIDforPayment();
        if (reservation!=null&&reservation.getStatus().equals("PAID")){
            System.out.println("\u001B[1m\u001B[32mThis reservation payment has been already received!\033[0m");
            System.out.println();
        }else if (reservation!=null){
            double payment = reservation.getPaymentAmount();
            LocalDate today = LocalDate.now();
            Guest guest = reservation.getGuest();
            Income income= new Income(payment,today,guest, receptionist);
            reservation.setStatus("PAID");
            hotel.getIncomes().add(income);
            System.out.println("\u001B[1m\u001B[32mThe payment received successfully!\033[0m");
        }else{
            System.out.println("\u001B[1m\u001B[31mThere is no such a reservation in system !\033[0m");
        }

    }
    public void removeReservation(){
        LocalDate today = LocalDate.now();
        for(Reservation reservation: hotel.getReservations()){
            reservation.toString();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter reservation id to remove reservation: ");
        int id=getIDfromUser();
        Reservation reservation = getReservationByReservationIDforPayment(id);
        List<Reservation> reservationsToRemove = new ArrayList<>();

        for (Reservation reservation_temp : hotel.getReservations()) {
            if (reservation_temp.getReservationID() == id) {
                reservationsToRemove.add(reservation_temp);
            }
        }
        for (Reservation reservationToRemove : reservationsToRemove) {
            long period=ChronoUnit.DAYS.between(today,reservationToRemove.getCheckInDate());
            if (period>=10){
                hotel.getReservations().remove(reservationToRemove);
                LocalDate startDate=reservationToRemove.getCheckInDate();
                LocalDate finishDate=reservationToRemove.getCheckOutDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                for (LocalDate date = startDate; date.isBefore(finishDate); date = date.plusDays(1)) {
                    String d = date.format(formatter);
                    reservationToRemove.getRoom().getReservedDates().remove(d);
                }
                if (reservationToRemove.getStatus().equalsIgnoreCase("PAID")){
                    Outcome outcome= new Outcome(reservationToRemove.getPayment(),"Reservation Cancellation",today);
                    hotel.getOutcomes().add(outcome);
                }

                System.out.println("The reservation removed successfully !");
            }else{
                System.out.println("\u001B[1m\u001B[31mReservations can be canceled up to 10 days before check-in\033[0m");
                System.out.println("\u001B[1m\u001B[31mReservation not removed !\033[0m");

                // outcome a iptal edilen rezervasyonu basmayı unutma
            }

        }
    }
    public void showAllReservations(){
        for(Reservation reservation: hotel.getReservations()){
            reservation.toString();
        }
    }
    public void calculatePayment(){
        Reservation reservation = getReservationByReservationID();
        System.out.println("The total payment for this reservation is: "+reservation.getPayment());
        System.out.println("Is payment received ? :"+reservation.getStatus());
        System.out.println();

    }
    public void listReservations(LocalDate from, LocalDate to){
        int reservationSize=hotel.getReservations().size();
        if (reservationSize==0){
            System.out.println("There is no reservation in system ");
        }
        System.out.println("**** Reservation List ****");
        for (Reservation reservation : hotel.getReservations()) {
            if (reservation.getCheckInDate().compareTo(from)>0 ||reservation.getCheckInDate().compareTo(from)==0) {
                System.out.print(" Room Number: "+reservation.getRoom().getRoomNumber());
                System.out.print(" Room Type: "+reservation.getRoom().getRoomType());
                System.out.print(" CheckIn Date: "+reservation.getCheckInDate());
                System.out.print(" CheckOut Date: "+reservation.getCheckOutDate());
                System.out.print(" Reserved by: "+reservation.getGuest().getName());
                System.out.print(" Payment: "+reservation.getPayment());
                if (reservation.getStatus().equalsIgnoreCase("PAID")){
                    System.out.print(" Payment Status: \u001B[1m\u001B[32m"+reservation.getStatus()+"\033[0m");
                }else{
                    System.out.print(" Payment Status: \u001B[1m\u001B[31m"+reservation.getStatus()+"\033[0m");
                }
                System.out.println();


            }
        }

    }
    public String getOccupancy(LocalDate from, LocalDate to){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int reservedRoomNumber=0;
        int reservedDaysSize=0;
        String d;
        LocalDate startDate=from;
        LocalDate finishDate=to;
        for (Room room: hotel.getRooms()) {
            startDate=from;
            finishDate=to;
            while (!startDate.isAfter(finishDate)) {
                d = from.format(formatter);
                if (room.getReservedDates().contains(d))
                    reservedDaysSize++;
                startDate=startDate.plusDays(1);
            }

        }
        DecimalFormat df = new DecimalFormat("0.00");
        long period = ChronoUnit.DAYS.between(from,to);
        double occupancy=(double)(reservedDaysSize)/(period*300)*100;
        String occupancyString=df.format(occupancy);
        return occupancyString;
    }
    public void  showIncomeList(LocalDate from, LocalDate to){
        int counter=0;
        for (Income income : hotel.getIncomes()) {
            if (income.getPaymentDate().isAfter(from)&&income.getPaymentDate().isBefore(to)){
                income.toString();
                counter++;
            }
            //income.toString();
        }
        if (counter==0){
            System.out.println("\u001B[1m\u001B[31mThere is no income in that date range !\033[0m");
        }
    }
    public double getIncome(LocalDate from, LocalDate to){
        double totalIncome=0.0;
        for (Income income : hotel.getIncomes()) {
            if (income.getPaymentDate().isAfter(from)&&income.getPaymentDate().isBefore(to)){
                totalIncome+=income.getPaymentAmount();
            }
        }
        return totalIncome;
    }
    public double getOutcome(LocalDate from, LocalDate to) {
        double totalOutcome = 0.0;
        for (Outcome outcome : hotel.getOutcomes()) {
            if ((outcome.getDate().isEqual(from) || outcome.getDate().isAfter(from)) ||
                    (outcome.getDate().isEqual(to) || outcome.getDate().isBefore(to))) {
                totalOutcome += outcome.getAmount();
            }
        }
        return totalOutcome;
    }
    public void showProfit(LocalDate from, LocalDate to){
        double profit=getIncome(from,to)-getOutcome(from,to);
        System.out.println("The net profit between "+from.toString()+" and "+to.toString()+" is: \u001B[1m\u001B[32m"+profit+"\033[0m");
    }
    public void listEmployees(){
        if (hotel.getEmployees().isEmpty()){
            System.out.println("There is no employee in system");
            System.out.println();
        }
        for(Employee employee: hotel.getEmployees()){
            employee.toString();
        }
    }
    public void listHumanResourceDepartmentEmployees(){
        for (Employee employee : hotel.getEmployees()) {
            if (employee instanceof HumanResource) {
                employee.toString();
            }
        }
    }
    public void changeEmployeeDepartment() {
        System.out.println("Employee List");
        listEmployees();
        System.out.println("Please enter employee ID to change department to: ");
        Scanner scanner = new Scanner(System.in);
        int employeeID = scanner.nextInt();
        Employee employeeToChangeDepartment = getEmployeeByID(employeeID);
        if (employeeToChangeDepartment != null) {
            String name = employeeToChangeDepartment.getName();
            String surname = employeeToChangeDepartment.getSurname();
            int id = employeeToChangeDepartment.getId();
            String department = employeeToChangeDepartment.getDepartment();
            String phone=employeeToChangeDepartment.getPhone();
            String email=employeeToChangeDepartment.getEmail();
            System.out.println("What is the new department? : ");
            Scanner scanner1 = new Scanner(System.in);
            String newDepartment = scanner1.nextLine();

            switch (newDepartment) {
                case "Receptionist":
                    hotel.getEmployees().remove(employeeToChangeDepartment);
                    System.out.println("Please assign a username to new Receptionist: ");
                    String username = scanner1.nextLine();
                    System.out.println("Please assign a password to new Receptionist: ");
                    String password = scanner1.nextLine();
                    Receptionist receptionist = new Receptionist(name,surname,email,phone,username,password);
                    receptionist.setId(id);
                    int nextid=receptionist.getNextId();
                    nextid--;
                    receptionist.setNextId(nextid);
                    hotel.getEmployees().add(receptionist);
                    break;
                case "HouseKeeper":
                    hotel.getEmployees().remove(employeeToChangeDepartment);
                    if (department.equals("Receptionist")) {
                        Receptionist receptionist1 = (Receptionist) employeeToChangeDepartment;
                        hotel.getEmployees().remove(receptionist1);
                        int nextID=receptionist1.getNextId();
                        nextID--;
                        receptionist1.setNextId(nextID);
                    }
                    HouseKeeper houseKeeper = new HouseKeeper(name,surname,email,phone);
                    hotel.getEmployees().add(houseKeeper);
                    System.out.println("\u001B[1m\u001B[32mThe employee department has been changed to "+newDepartment+" successfully !\033[0m");
                    System.out.println();
                    break;
                default:
                    System.out.println("\u001B[1m\u001B[31mYou entered an invalid department\033[0m");
                    System.out.println();
                    break;

            }


        }
    }
    public void listPerformanceOfEmployees(){
        for (Employee employee : hotel.getEmployees()) {
            if (employee instanceof Receptionist){
                System.out.println("ID: "+employee.getId()+" Name: "+employee.getName()+" Surname "+employee.getSurname()+" Performance: "+employee.getPerformance());
            }

        }
    }
    public Receptionist getBestEmployee() {
        int bestPerformance = 0;
        List<Receptionist> bestReceptionists = new ArrayList<>();

        for (Employee employee : hotel.getEmployees()) {
            if (employee instanceof Receptionist) {
                int performance = ((Receptionist) employee).getPerformance();
                if (performance > bestPerformance) {
                    bestPerformance = performance;
                    bestReceptionists.clear();
                    bestReceptionists.add((Receptionist) employee);
                } else if (performance == bestPerformance) {
                    bestReceptionists.add((Receptionist) employee);
                }
            }
        }

        Receptionist best=null;

        if (bestReceptionists.isEmpty()) {
            System.out.println("No best receptionist found.");
        } else {
            System.out.println("Best Receptionist(s):");
            for (Receptionist receptionist : bestReceptionists) {
                System.out.println(receptionist);
                best=receptionist;
            }
        }
        return best;

    }
    public Employee getEmployeeByID(int id){
        for (Employee employee : hotel.getEmployees()) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
    public void searchEmployeeById(){
        boolean isFound=false;
        System.out.println("Please enter the employee if for search: ");
        Scanner scanner = new Scanner(System.in);
        int employeeID = scanner.nextInt();
        for (Employee employee : hotel.getEmployees()) {
            if (employee.getId() == employeeID) {
                isFound=true;
                employee.toString();
            }
        }
        if (!isFound){
            System.out.println("\u001B[1m\u001B[31mEmployee not found\033[0m");
        }
    }
    public void showReservationByGuestNameAndSurname(){
        boolean found=false;
        String guestName = getNameFromUser();
        String guestSurname = getSurnameFromUser();
        for (Reservation reservation: hotel.getReservations()){
            if (reservation.getGuest().getName().equalsIgnoreCase(guestName)&& reservation.getGuest().getSurname().equalsIgnoreCase(guestSurname)){
                found=true;
                reservation.toString();
            }

        }
        if (!found){
            System.out.println("\u001B[1m\u001B[31mThere is no reservation with that guest name and surname\033[0m");
            System.out.println();
        }
    }
    public void getReservationByGuestId(){
        boolean found=false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter guest id for search reservation: ");
        int guestID = getIDfromUser();
        for (Reservation reservation: hotel.getReservations()){
            if (reservation.getGuest().getId() == guestID){
                found=true;
                reservation.toString();
            }
        }
        if (!found){
            System.out.println("\u001B[1m\u001B[31mThere is no reservation with that guest id\033[0m");
            System.out.println();

        }
    }
    public boolean roomAvailabilityCheck(){
        int roomNumber;
        //Scanner scanner = new Scanner(System.in);
        do {
            Scanner scanner= new Scanner(System.in);
            System.out.println("Please enter room number for check availability: ");
            roomNumber = scanner.nextInt();
            if (roomNumber < 1 || roomNumber >300){
                System.out.println("\u001B[1m\u001B[31mInvalid room number, please enter a number between 1 and 300\033[0m");
            }
        }while (roomNumber<1 || roomNumber>300);
        System.out.println("Please enter check-in date: ");
        LocalDate from=getDateFromUser();
        System.out.println("Please enter check-out date: ");
        LocalDate to=getDateFromUser();
        boolean result=isReserved(roomNumber,from,to);
        if (result){
            System.out.println("\u001B[1m\u001B[31mThe room is not available for that date ranges !\033[0m");
            System.out.println();
        }else{
            System.out.println("\u001B[1m\u001B[32mThe room is available!\033[0m");
            System.out.println();
        }
        return result;


    }
    public boolean isReserved(int roomID,LocalDate startDate, LocalDate finishDate) {
        boolean flag = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Room room=getRoomByNumber(roomID);
        for (LocalDate date=startDate;date.isBefore(finishDate);date=date.plusDays(1)) {
            String d = date.format(formatter);
            if (room.getReservedDates().contains(d)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}

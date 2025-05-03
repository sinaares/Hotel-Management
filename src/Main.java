import Employee.HumanResource;
import Employee.Receptionist;
import HotelSystem.CommonHotelSystem;
import HotelSystem.Hotel;
import Reservation.Reservation;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Scanner;
import Boss.Boss;


public class Main {
    public static void main(String[] args) {
        Hotel hotel=Hotel.getInstance("DEUCENG");
        CommonHotelSystem hotelSystem=new CommonHotelSystem(hotel);

        System.out.println("Welcome to Hotel Management System");
        System.out.println("----------------------------------");
        System.out.println("Please select user type: ");

        int userType=hotelSystem.HomePage();

        LocalDate from;
        LocalDate to;
        LocalDate dateFrom;
        LocalDate dateTo;



        while (userType > 0 && userType < 5) {
            if (userType == 1) { // user type selected as Boss
                hotelSystem.loginPage("Boss");
                Boss boss = hotelSystem.bossAuthentication();
                int choice;
                Scanner scanner = new Scanner(System.in);
                if (boss != null) {

                    do {
                        choice = hotelSystem.bossHomePage();
                        switch (choice) {
                            case 1: // List reservation
                                System.out.println("Please enter starting date by separating '-' (Year-Month-Day): ");
                                dateFrom = hotelSystem.getDateFromUser();
                                if (dateFrom!=null){
                                    System.out.println("Please enter ending date by separating '-' (Year-Month-Day): ");
                                    dateTo = hotelSystem.getDateFromUser();
                                    if (dateTo!=null){
                                        hotelSystem.listReservations(dateFrom,dateTo);
                                        break;
                                    }else{
                                        System.out.println("\u001B[1m\u001B[31mInvalid date please check the date\033[0m");
                                        break;
                                    }
                                }else{
                                    System.out.println("\u001B[1m\u001B[31mInvalid date please check the date\033[0m");
                                    break;
                                }

                            case 2: // list all employees
                                System.out.println("***  Employees List  ***");
                                hotelSystem.listEmployees();
                                break;
                            case 3: // list human resource employees
                                System.out.println("***  Human Resources Department Employees  ***");
                                hotelSystem.listHumanResourceDepartmentEmployees();
                                break;
                            case 4:
                                hotelSystem.listReceptionEmployees();
                                break;
                            case 5:
                                hotelSystem.listHouseKeeperEmployees();
                                break;
                            case 6:
                                hotelSystem.addHumanResourceEmployee();
                                break;
                            case 7:
                                System.out.println("***  Performances of Receptionists  ***");
                                hotelSystem.listPerformanceOfEmployees();
                                break;

                            case 8:
                                System.out.println("***  Best Employee  ***");
                                hotelSystem.getBestEmployee();
                                break;
                            case 9:
                                System.out.println("***  Get the Occupancy Rate  ***");
                                System.out.println("Please enter starting date by separating '-' (Year-Month-Day): ");
                                dateFrom = hotelSystem.getDateFromUser();
                                System.out.println("Please enter ending date by separating '-' (Year-Month-Day): ");
                                dateTo = hotelSystem.getDateFromUser();
                                System.out.println("The occupancy rate between " + dateFrom.toString() + " and " + dateTo.toString() + " is : % "+hotelSystem.getOccupancy(dateFrom,dateTo));
                                break;

                            case 10:
                                System.out.println("***  Incomes List  ***");
                                System.out.println("Please enter starting date by separating '-' (Year-Month-Day): ");
                                dateFrom = hotelSystem.getDateFromUser();
                                System.out.println("Please enter ending date by separating '-' (Year-Month-Day): ");
                                dateTo = hotelSystem.getDateFromUser();
                                hotelSystem.showIncomeList(dateFrom, dateTo);
                                break;
                            case 11: // show outcome
                                System.out.println("***  Outcome List  ***");
                                System.out.println("Please enter starting date by separating '-': ");
                                LocalDate start=hotelSystem.getDateFromUser();
                                System.out.println("Please enter ending date by separating '-': ");
                                LocalDate end=hotelSystem.getDateFromUser();
                                hotelSystem.showOutcomes(hotel,start,end);
                                Double totalOutcome= hotelSystem.getOutcome(start,end);
                                DecimalFormat df = new DecimalFormat("#.00");
                                String formattedValue = df.format(totalOutcome);
                                System.out.println("Total outcome is: \u001B[1m\u001B[31m"+formattedValue+"\033[0m");
                                break;
                            case 12: // show profit
                                System.out.println("Please enter starting date by separating '-': ");
                                LocalDate start1=hotelSystem.getDateFromUser();
                                System.out.println("Please enter ending date by separating '-': ");
                                LocalDate end1=hotelSystem.getDateFromUser();
                                hotelSystem.showProfit(start1,end1);
                                break;
                            case 13:
                                System.out.println("***  Set Bonus to Best Employee  ***");
                                Receptionist bestEmployee = hotelSystem.getBestEmployee();
                                System.out.println("***  Best Employee  ***");
                                bestEmployee.toString();
                                System.out.println("Please enter the bonus amount: ");
                                double bonusAmount = scanner.nextDouble();
                                bestEmployee.setBonus(bonusAmount);
                                break;
                            case 14:
                                System.out.println("Session Closed !");
                                boss = null;
                                userType = hotelSystem.HomePage();
                                break;
                            default:
                                //System.out.println("\u001B[1m\u001B[31mInvalid choice, please select an operation from the menu");
                                break;

                        }
                    } while (choice!=14);

                }else{
                    System.out.println("Please make a choice: ");
                    System.out.println("1. Try Again");
                    System.out.println("2. Exit");
                    int choice1=scanner.nextInt();
                    if (choice1==1) {
                        userType=1;
                    }else{
                        userType = hotelSystem.HomePage();
                    }

                }

            } else if (userType == 2) {
                hotelSystem.loginPage("Human Resource Department");
                HumanResource humanResource = hotelSystem.humanResourceAuthentication();
                Scanner scanner = new Scanner(System.in);
                int choice;
                if (humanResource != null) {

                    do {
                        System.out.println("\033[0m");
                        choice = hotelSystem.humanResourceHomePage();
                        switch (choice) {
                            case 1:
                                hotelSystem.addEmployee();
                                break;
                            case 2:
                                hotelSystem.listEmployees();
                                break;
                            case 3:
                                hotelSystem.changeEmployeeDepartment();
                                break;
                            case 4:
                                System.out.println("Search for an employee by ID");
                                hotelSystem.searchEmployeeById();
                                break;
                            case 5:
                                System.out.println("Session Closed");
                                userType = hotelSystem.HomePage();
                                break;
                            default:
                                System.out.println();
                                break;
                        }

                    } while (choice!=5);
                    //System.out.println("Session Closed !");
                    //HomePage();

                }else{
                    System.out.println("Please make a choice: ");
                    System.out.println("1. Try Again");
                    System.out.println("2. Exit");
                    int choice1=scanner.nextInt();
                    if (choice1==1) {
                        userType=2;
                    }else{
                        userType = hotelSystem.HomePage();
                    }

                }
            } else if (userType == 3) {
                hotelSystem.loginPage("Reception Department");
                Receptionist receptionist = hotelSystem.receptionistAuthentication();
                Scanner scanner = new Scanner(System.in);
                int choice;
                if (receptionist != null) {
                    do {
                        System.out.println("\033[0m");
                        choice = hotelSystem.receptionistHomePage();
                        switch (choice) {
                            case 1:
                                Reservation reservation=hotelSystem.createReservation();
                                if (reservation != null) {
                                    reservation.setReceptionist(receptionist);
                                    reservation.getReceptionist().setPerformance(reservation.getReceptionist().getPerformance()+1);
                                }
                                break;

                            case 2:
                                hotelSystem.removeReservation();
                                break;

                            case 3:
                                hotelSystem.showReservationByGuestNameAndSurname();
                                break;
                            case 4:
                                hotelSystem.getReservationByGuestId();
                                break;
                            case 5:
                                hotelSystem.showAllReservations();
                                break;
                            case 6:
                                System.out.println("Payment Calculation");
                                hotelSystem.calculatePayment();
                                break;
                            case 7:
                                hotelSystem.roomAvailabilityCheck();
                                break;

                            case 8:
                                Scanner sc=new Scanner(System.in);
                                System.out.println("Please enter reservation id for search:");
                                int reservationId = scanner.nextInt();
                                Reservation reservationTemp;
                                reservationTemp=hotelSystem.getReservationByReservationIDforPayment(reservationId);
                                if (reservationTemp==null){
                                    System.out.println("\u001B[1m\u001B[31mThere is no such a reservation in system !\033[0m");
                                }else{
                                    reservationTemp.toString();
                                }
                                break;
                            case 9:
                                hotelSystem.receivePayment(receptionist);
                                break;
                            case 10:
                                System.out.println("Session Closed");
                                receptionist = null;
                                userType = hotelSystem.HomePage();
                                break;
                            default:
                                break;

                        }


                    } while (choice!=10);


                }else{
                    System.out.println("Please make a choice: ");
                    System.out.println("1. Try Again");
                    System.out.println("2. Exit");
                    int choice1=scanner.nextInt();
                    if (choice1==1) {
                        userType=3;
                    }else{
                        userType = hotelSystem.HomePage();
                    }

                }
            } else if (userType == 4) {
                System.out.println("System is cloosing...");
                System.out.println("System is closed");
                break;


            }
        }


    }
}
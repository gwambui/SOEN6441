package view;
// -----------------------------------------------------
// SOEN 6441 Project Phase 1
// © Omar, William, Wambui
// Written by: (Omar Ahmed ID ,William Alves ID,
// Wambui Josphine Kinyanjui 24600878)
//
// This program accepts input from a list of options
// allows a rental property admin to perform tasks from a menu
//    Options
//    Add a property
//    Add a tenant
//    Rent a unit
//    Display properties ->PropertyView();
//    Display tenants
//    Display rented units
//    Display vacant units
//    Display all leases
//    Exit
// It executes the requests and returns to this main menu
//
//
// -----------------------------------------------------
import controller.TenantController;
import model.Lease;
import controller.LeaseController;
import static controller.LeaseController.leases;
import static controller.TenantController.tenants;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Lease lease = new Lease();

        // Create tenant objects
        TenantView tv = new TenantView();
        TenantController tc = new TenantController();
        LeaseController lc = new LeaseController();
        PayRentView prv = new PayRentView();
        


        System.out.println("Hello and welcome. Please select an action!");
        //    create property MVC
        PropertyView pv = new PropertyView();
//        PropertyController pc = new PropertyController(pv);
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        int userChoice = 0;
        do {

            //Display main menu
            System.out.println("1. Sign in as Administrator");
            System.out.println("2. Sign in as Tenant");
            System.out.println("3. Exit ");

            userChoice = sc.nextInt();


            //Display Admin Menu
            if (userChoice == 1) {
                do {
                    if (userChoice == 1) {
                        printMenu();
                        choice = sc.nextInt();

                        switch (choice) {
                            case 1:
                                pv.addProperty();
                                break;
                            case 2:
                                tv.TenantInput();
                                break;
                            case 3:
                                pv.displayProperties();
                                break;
                            case 4:
                                tc.DisplayCurrentTenants(tenants);
                                break;
                            case 5:
                                tc.DisplayPotentialTenants(tenants);
                                break;
                            case 6:
                                pv.displayRentedUnits();
                                break;
                            case 7:
                                pv.displayVacantUnits();
                                break;
                            case 8:
                                lc.DisplayLeases(leases, tenants);
                                break;
                            case 9:
                                pv.addHistoricalProperty();
                                break;
                            case 10:
                                break;
                            default:
                                System.out.println("Wrong input");
                        }
                    }
                } while (choice != 10);
            }

            //Display tenant view
            else if (userChoice == 2) {
                prv.PayRentView();
            }

            //Close program
            else if (userChoice == 3) {
                closeProg();
                break;
            }

        } while (choice != 3);

    }

    //Method to close program
    private static void closeProg() {
        System.out.println("Thank you! BYE");
        System.exit(0);
    }

    //Method to print admin menu
    public static void printMenu() {
        System.out.println("what do you want to do?");
        System.out.println("1. Add a property");
        System.out.println("2. Add a tenant/Rent a Unit");
        System.out.println("3. Display properties ");
        System.out.println("4. Display tenants");
        System.out.println("5. Display Potential Tenants");
        System.out.println("6. Display rented units");
        System.out.println("7. Display vacant units");
        System.out.println("8. Display all leases");
        System.out.println("9. Load historical property from list");
        System.out.println("10. Go back to main menu");
        System.out.println("Please enter your choice");
    }

}
package view;


import model.Lease;
import model.Tenant;
import model.ExistingTenant;
import model.PotentialTenant;
import controller.TenantController;
import controller.LeaseController;
import java.util.Scanner;

import static controller.LeaseController.leases;
import static controller.TenantController.tenants;

import controller.TenantController;


public class Main {

    public static void main(String[] args) {

        Lease lease = new Lease();

        // Create tenant objects
        Tenant tenant = new Tenant();
        TenantView tv = new TenantView();
        TenantController tc = new TenantController();
        LeaseController lc = new LeaseController();
        ExistingTenant et = new ExistingTenant();
        PotentialTenant pt = new PotentialTenant();
        PayRentView prv = new PayRentView();

        LeaseView lv = new LeaseView();
        while (true) {
            Scanner sc = new Scanner(System.in);
            int userChoice = 0;

            System.out.println("1. Sign into Administrator");
            System.out.println("2. Sign into Tenant");
            userChoice = sc.nextInt();

            if (userChoice == 1) {


                System.out.println("1. Add a Property \n");
                System.out.println("2. Add a Tenant \n");
                System.out.println("3. Rent a Unit \n");
                System.out.println("4. Display Properties \n");
                System.out.println("5. Display Current Tenants \n");
                System.out.println("6. Display Potential Tenants \n");
                System.out.println("7. Display Rented Units \n");
                System.out.println("8. Display Vacant Units \n");
                System.out.println("9. Display all leases \n");
                System.out.println("10. Go back \n");
                System.out.println("11. Exit \n");

                userChoice = sc.nextInt();

                if (userChoice == 2) {
                    tv.TenantInput();
                } else if (userChoice == 5) {
                    tc.DisplayCurrentTenants(tenants);
                } else if (userChoice == 6) {
                    tc.DisplayPotentialTenants(tenants);
                } else if (userChoice == 9) {
                    lc.DisplayLeases(leases, tenants);
                } else if (userChoice == 10) {
                    continue;
                }
                else if (userChoice == 11) {
                    break;
                }
            }
            else if (userChoice == 2) {
                prv.PayRentView();
            }
        }


    }



}
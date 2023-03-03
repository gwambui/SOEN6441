package view;

import controller.LeaseController;
import controller.TenantController;
import model.Tenant;
import static controller.TenantController.tenants;
import static controller.LeaseController.leases;

import java.util.Objects;
import java.util.Scanner;

public class PayRentView {
    public void PayRentView() {
        Tenant tenant = new Tenant();
        TenantController tc = new TenantController();
        LeaseController lc = new LeaseController();

        Scanner sc = new Scanner(System.in);

        int inputtedID;
        double rentAmount;
        double amountPaying;
        boolean result;

        while (true) {
            System.out.println("Please input your tenant ID or type 2 to go back to main menu: ");
            inputtedID = sc.nextInt();

            if (inputtedID == 2) {
                break;
            }

            if (lc.checkIfPaid(leases, tenants, inputtedID)) {
                System.out.println("Your rent has already been paid");
                break;
            }

            if (tc.checkTenantID(tenants, inputtedID)) {
                rentAmount = lc.getRentAmount(leases, tenants, inputtedID);

                    System.out.println("Your monthly rent is: " + rentAmount);
                    System.out.println("Please input the amount you are paying: ");
                    amountPaying = sc.nextInt();

                    result = lc.payRent(leases, inputtedID, tenants, rentAmount, amountPaying);

                    if (result) {
                        System.out.println("You have successfully paid your rent");
                        break;
                    } else {
                        System.out.println("The amount you inputted does not match your monthly rent amount, please try again later");
                    }
            }

            else {
                System.out.println("Error: this tenant ID is invalid or is associated with a potential tenant with no lease");
                continue;
            }


        }


    }
}

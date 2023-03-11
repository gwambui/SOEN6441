package view;


import java.util.ArrayList;
import java.util.Scanner;

import controller.PropertyController;
import controller.TenantController;
import model.Tenant;
import model.Lease;

public class TenantView extends Tenant {
    boolean availabilityFlag;

    //Tenant Input view called when add a tenant choice is chosen
    public void TenantInput() {
        Tenant tenant = new Tenant();
        Lease lease = new Lease();
        LeaseView lv = new LeaseView();
        PropertyView pv = new PropertyView();

        Scanner sc = new Scanner(System.in);

        //All types of tenants will receive the following prompts
        System.out.println("Is this tenant a current tenant? (true/false)");
        isCurrentTenant = sc.nextBoolean();

        System.out.println("Enter Tenant First Name: ");
        tenant.setFirstName(sc.next());
        firstName = tenant.getFirstName();

        System.out.println("Enter Tenant Last Name: ");
        tenant.setLastName(sc.next());
        lastName = tenant.getLastName();

        System.out.println("Input Tenant email address: ");
        tenant.setEmail(sc.next());
        email = tenant.getEmail();

        //Block of code of prompts for potential tenants
        if (isCurrentTenant == false) {

            while (true) {

                System.out.println("Input the building ID the potential tenant is interested in: ");
                buildingTenantID = sc.nextInt();

                if (pv.checkID(buildingTenantID)) {
                    break;
                }
                else {
                    System.out.println("The inputted building ID does not match any building ID in the system. Please try again.");
                }

            }

            while (true) {

                System.out.println("Input unit number the potential tenant is interested in renting:");
                apartmentNum = sc.nextInt();

                if (pv.isCondo(buildingTenantID)) {
                    availabilityFlag = pv.checkCondoAvailability(buildingTenantID, apartmentNum);
                    if (pv.checkCondoNo(buildingTenantID,apartmentNum) == true) {
                        break;
                    }

                    else {
                        System.out.println("The inputted unit number does not match any unit number for this building or is not available. Please try again.");
                    }
                }

                else {
                    //Change availability flag
                    availabilityFlag = pv.checkApartmentAvailability(buildingTenantID, apartmentNum);

                    if (pv.checkApartmentNo(buildingTenantID, apartmentNum) == true) {
                        break;
                    } else {
                        System.out.println("The inputted unit number does not match any unit number for this building or is not available. Please try again.");
                    }
                }
            }

            //Set tenant ID
            tenant.setID();
            tenantID = tenant.getTenantID();
            System.out.println("This tenants ID is: " + tenant.getTenantID());

            //Message to potential tenant indicating whether or not unit is available
            if (availabilityFlag) {
                System.out.println("The unit number the potential tenant is interested in is currently AVAILABLE");
            }

            else {
                System.out.println("The unit number the potential tenant is interested in is currently UNAVAILABLE");
            }


        //Block of code for prompts for current tenants
        } else if (isCurrentTenant == true) {

            while (true) {

                System.out.println("Input the building ID the tenant is living in: ");
                buildingTenantID = sc.nextInt();

                if (pv.checkID(buildingTenantID) == true) {
                    break;
                }
                else {
                    System.out.println("The inputted building ID does not match any building ID in the system. Please try again.");
                }
            }

            while (true) {

                System.out.println("Input unit number tenant is renting:");
                apartmentNum = sc.nextInt();

                //Check if inputted number is a condo or apartment unit and if the condo or apartment number exists within the system before moving on

                if (pv.isCondo(buildingTenantID)) {
                    if ((pv.checkCondoNo(buildingTenantID,apartmentNum) == true) && (pv.checkCondoAvailability(buildingTenantID,apartmentNum))) {
                        break;
                    }

                    else {
                        System.out.println("The inputted unit number does not match any unit number for this building. Please try again.");
                    }
                }

                else {

                    if (pv.checkApartmentNo(buildingTenantID, apartmentNum) == true && pv.checkApartmentAvailability(buildingTenantID,apartmentNum)) {
                        break;
                    } else {
                        System.out.println("The inputted unit number does not match any unit number for this building. Please try again.");
                    }
                }
            }

            //Set tenant ID
            tenant.setID();
            tenantID = tenant.getTenantID();
            System.out.println("This tenants ID is: " + tenant.getTenantID());

            //Change availability flag
            pv.changeAvailableFlag(buildingTenantID,apartmentNum);

            //Call lease input only when a current tenant is being inputted
            lv.LeaseInput();
        }

        //Add a new tenant to the array list by calling controller
        TenantController tc = new TenantController();
        tc.addNewTenant(isCurrentTenant, tenantID, firstName, lastName, email, buildingTenantID, apartmentNum);
    }
}



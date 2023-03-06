package view;


import java.util.ArrayList;
import java.util.Scanner;

import controller.PropertyController;
import controller.TenantController;
import model.Tenant;
import model.Lease;

public class TenantView extends Tenant {


    public void TenantInput() {
        Tenant tenant = new Tenant();
        Lease lease = new Lease();
        LeaseView lv = new LeaseView();
        PropertyView pv = new PropertyView();

        Scanner sc = new Scanner(System.in);

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
                    if (pv.checkCondoNo(buildingTenantID,apartmentNum) == true) {
                        break;
                    }

                    else {
                        System.out.println("The inputted unit number does not match any unit number for this building. Please try again.");
                    }
                }

                else {

                    if (pv.checkApartmentNo(buildingTenantID, apartmentNum) == true) {
                        break;
                    } else {
                        System.out.println("The inputted unit number does not match any unit number for this building. Please try again.");
                    }
                }
            }

            tenant.setID();
            tenantID = tenant.getTenantID();
            System.out.println("This tenants ID is: " + tenant.getTenantID());

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

                if (pv.isCondo(buildingTenantID)) {
                    if (pv.checkCondoNo(buildingTenantID,apartmentNum) == true) {
                        break;
                    }

                    else {
                        System.out.println("The inputted unit number does not match any unit number for this building. Please try again.");
                    }
                }

                else {

                    if (pv.checkApartmentNo(buildingTenantID, apartmentNum) == true) {
                        break;
                    } else {
                        System.out.println("The inputted unit number does not match any unit number for this building. Please try again.");
                    }
                }
            }

            tenant.setID();
            tenantID = tenant.getTenantID();
            System.out.println("This tenants ID is: " + tenant.getTenantID());


            pv.changeAvailableFlag(buildingTenantID,apartmentNum);

            lv.LeaseInput();
        }

        TenantController tc = new TenantController();
        tc.addNewTenant(isCurrentTenant, tenantID, firstName, lastName, email, buildingTenantID, apartmentNum);
    }
}



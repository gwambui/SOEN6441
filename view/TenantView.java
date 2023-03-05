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
        PropertyController pc;

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
            System.out.println("Input the apartment building ID the potential tenant is interested in: ");
            buildingTenantID = sc.nextInt();

            System.out.println("Input apartment number potential tenant is interested in:");
            apartmentNum = sc.nextInt();

            tenant.setID();
            tenantID = tenant.getTenantID();
            System.out.println("This tenants ID is: " + tenant.getTenantID());

        } else if (isCurrentTenant == true) {
            System.out.println("Input the apartment building ID the tenant is living in: ");
            buildingTenantID = sc.nextInt();

            System.out.println("Input apartment number tenant is renting:");
            apartmentNum = sc.nextInt();

            tenant.setID();
            tenantID = tenant.getTenantID();
            System.out.println("This tenants ID is: " + tenant.getTenantID());

            PropertyView pv = new PropertyView();
            pv.changeAvailableFlag(buildingTenantID,apartmentNum);

            lv.LeaseInput();


        }

        TenantController tc = new TenantController();
        tc.addNewTenant(isCurrentTenant, tenantID, firstName, lastName, email, buildingTenantID, apartmentNum);
    }
}



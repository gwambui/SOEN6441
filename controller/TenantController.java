package controller;

<<<<<<< HEAD
import model.ExistingTenant;
import model.Lease;
import model.PotentialTenant;
import model.Tenant;
import view.LeaseView;
import view.TenantView;
import java.util.Scanner;

import java.util.ArrayList;

public class TenantController extends Tenant {
    public static ArrayList<Tenant> tenants = new ArrayList<Tenant>();
    public void TenantController() {

        Tenant tenant = new Tenant();
        Lease lease = new Lease();

        // Create tenant objects
        TenantView tv = new TenantView();
        ExistingTenant et = new ExistingTenant();
        PotentialTenant pt = new PotentialTenant();
        LeaseView lv = new LeaseView();
        Scanner sc = new Scanner(System.in);

}
    public void addNewTenant(boolean isCurrent, int tenantID, String firstName, String lastName, String email, String buildingName, int apartmentNum) {
        Tenant t = new Tenant();

        t.firstName = firstName;
        t.lastName = lastName;
        t.email = email;
        t.isCurrentTenant = isCurrent;
        t.buildingName = buildingName;
        t.apartmentNum = apartmentNum;
        t.tenantID = tenantID;

        tenants.add(t);

    }

    public void DisplayCurrentTenants(ArrayList<Tenant> tenantList) {
        if (tenantList.isEmpty()) {
            System.out.println("There are no tenants inputted in the system");
        }

        else {
            System.out.println("The following is a list of Current Tenants: \n");
            for (Tenant tenant : tenantList) {
                if (tenant.isCurrentTenant == true) {
                    System.out.println("The tenant name is: " + tenant.getFirstName() + " " + tenant.getLastName());
                    System.out.println("Tenant ID " + tenant.getTenantID());
                    System.out.println("The tenants email address is: " + tenant.getEmail());
                    System.out.println("They live in building: " + tenant.getBuildingName());
                    System.out.println("They are renting unit number: " + tenant.getApartmentNum() + "\n");
                }
            }
        }
    }

    public void DisplayPotentialTenants(ArrayList<Tenant> tenantList) {
        if (tenantList.isEmpty()) {
            System.out.println("There are no tenants inputted in the system");
        }

        else {
            System.out.println("The following is a list of Potential Tenants: \n");
            for (Tenant tenant : tenantList) {
                if (tenant.isCurrentTenant == false) {
                    System.out.println("The potential tenant name is: " + tenant.getFirstName() + " " + tenant.getLastName());
                    System.out.println("Tenant ID: " + tenant.getTenantID());
                    System.out.println("The potential tenants' email address is: " + tenant.getEmail());
                    System.out.println("They are interested in apartment number " + tenant.getApartmentNum() + " in building " + tenant.getBuildingName() + "\n");
                }
            }
        }
    }

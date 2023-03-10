package controller;


import model.*;
import view.LeaseView;
import view.TenantView;

import java.util.Objects;
import java.util.Scanner;

import java.util.ArrayList;

public class TenantController {
    PropertyController pc;
    //Set public static array list of object tenants to be used amongst several classes
    public static ArrayList<Tenant> tenants = new ArrayList<Tenant>();

    //Constructor for tenant controller
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

    //Method to add a new tenant, must pass all correct paramaters for a new tenant
    public void addNewTenant(boolean isCurrent, int tenantID, String firstName, String lastName, String email, int buildingTenantID, int apartmentNum) {
        Tenant t = new Tenant();

        t.firstName = firstName;
        t.lastName = lastName;
        t.email = email;
        t.isCurrentTenant = isCurrent;
        t.buildingTenantID = buildingTenantID;
        t.apartmentNum = apartmentNum;
        t.tenantID = tenantID;

        tenants.add(t);

    }

    //Method to display current tenants
    public void DisplayCurrentTenants(ArrayList<Tenant> tenantList) {
        if (tenantList.isEmpty()) {
            System.out.println("There are no tenants inputted in the system");
        } else {
            System.out.println("The following is a list of Current Tenants: \n");
            for (Tenant tenant : tenantList) {
                if (tenant.isCurrentTenant == true) {
                    System.out.println("The tenant name is: " + tenant.getFirstName() + " " + tenant.getLastName());
                    System.out.println("Tenant ID " + tenant.getTenantID());
                    System.out.println("The tenants email address is: " + tenant.getEmail());
                    System.out.println("They live in building: " + tenant.getBuildingTenantID());
                    System.out.println("They are renting unit number: " + tenant.getApartmentNum() + "\n");
                }
            }
        }
    }

    //Method to display potential tenants
    public void DisplayPotentialTenants(ArrayList<Tenant> tenantList) {
        if (tenantList.isEmpty()) {
            System.out.println("There are no tenants inputted in the system");
        } else {
            System.out.println("The following is a list of Potential Tenants: \n");
            for (Tenant tenant : tenantList) {
                if (tenant.isCurrentTenant == false) {
                    System.out.println("The potential tenant name is: " + tenant.getFirstName() + " " + tenant.getLastName());
                    System.out.println("Tenant ID: " + tenant.getTenantID());
                    System.out.println("The potential tenants' email address is: " + tenant.getEmail());
                    System.out.println("They are interested in apartment number " + tenant.getApartmentNum() + " in building " + tenant.getBuildingTenantID() + "\n");
                }
            }
        }
    }

    //Method to check tenant ID, returns boolean
    public boolean checkTenantID(ArrayList<Tenant> tenantList, int inputtedID) {
       boolean out = false;

        for (Tenant tenant : tenantList) {
            if (Objects.equals(tenant.tenantID, inputtedID) && tenant.isCurrentTenant) {
                out = true;
                break;
            }
            else {
                out = false;
            }
        }
        return out;
    }

}

package model;
import java.util.ArrayList;
import java.util.Random;

import controller.TenantController;
import view.TenantView;

public class Tenant {

    //All getter and setter functions for all parameters of tenants
    public String firstName;
    public String lastName;
    public String email;
    public int apartmentNum;
    public int tenantID;

    @Override
    public String toString() {
        return "Tenant{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", apartmentNum=" + apartmentNum +
                ", tenantID=" + tenantID +
                ", buildingName='" + buildingTenantID + '\'' +
                ", isCurrentTenant=" + isCurrentTenant +
                '}';
    }


    public int buildingTenantID;
    public boolean isCurrentTenant;

    public Tenant() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(int apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public int getBuildingTenantID() {
        return buildingTenantID;
    }

    public void setBuildingTenantID(int buildingTenantID) {
        this.buildingTenantID = buildingTenantID;
    }


    //Method to set current tenant flag to true or false
    public boolean setCurrentTenant(String choice) {

        if (choice.equals("yes")) {
            isCurrentTenant = true;
        } else if (choice.equals("no")) {
            isCurrentTenant = false;
        }
        return isCurrentTenant;
    }

    public boolean getCurrentTenant() {
        return isCurrentTenant;
    }

    //Method to set random tenant ID
    public int setID() {
        Random r = new Random();
        tenantID = (250000 + r.nextInt(1000));
        return tenantID;
    }

    public int getTenantID() {
        return tenantID;
    }

}

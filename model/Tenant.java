package model;
import java.util.ArrayList;
import java.util.Random;

import controller.TenantController;
import view.TenantView;

public class Tenant {
    public static ArrayList tenantAttributes = new ArrayList();
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
                ", buildingName='" + buildingName + '\'' +
                ", isCurrentTenant=" + isCurrentTenant +
                '}';
    }

    public String buildingName;
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
    public String getBuildingName() {
        return buildingName;
    }
    public boolean setCurrentTenant(String choice) {

        if (choice.equals("yes")) {
            isCurrentTenant = true;
        }

        else if (choice.equals("no")) {
          isCurrentTenant = false;
        }
        return isCurrentTenant;
    }

    public boolean getCurrentTenant() {
        return isCurrentTenant;
    }

    public int setID() {
        Random r = new Random();
        tenantID = (250000 + r.nextInt(1000));
        return tenantID;
    }
    public int getTenantID() {
        return tenantID;
    }



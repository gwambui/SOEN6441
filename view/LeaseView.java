package view;
import model.Lease;
import controller.LeaseController;

import java.util.ArrayList;
import java.util.Scanner;

import model.Tenant;

public class LeaseView  {
    Lease lease ;
    int leaseID;
    String leaseStartDate;
    String leaseEndDate;
    Double monthlyRentAmount;

    public LeaseView() {
        this.lease = new Lease();
    }

    //Lease input is called when a current tenants inputted
    public void LeaseInput() {
        Lease lease = new Lease();
        Scanner sc = new Scanner(System.in);

        lease.setID();
        leaseID = lease.getLeaseID();

        //Display all prompts for lease input
        System.out.println("The lease ID associated with this tenant is: " + leaseID);

        System.out.println("Enter the lease start date (DD/MM/YYYY): ");
        lease.setLeaseStartDate(sc.next());
        leaseStartDate = lease.getLeaseStartDate();

        System.out.println("Enter the lease end date (DD/MM/YYYY): ");
        lease.setLeaseEndDate(sc.next());
        leaseEndDate = lease.getLeaseEndDate();

        System.out.println("Enter the monthly rent amount ($): ");
        lease.setMonthlyRentAmount(sc.nextDouble());
        monthlyRentAmount = lease.getMonthlyRentAmount();

        //Add a new lease to the array list by calling the controller
        LeaseController lc = new LeaseController();

        lc.addNewLease(leaseID, leaseStartDate, leaseEndDate, monthlyRentAmount, false);

    }
}



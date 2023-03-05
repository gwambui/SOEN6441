package view;
import model.Lease;
import controller.LeaseController;

import java.util.ArrayList;
import java.util.Scanner;

import model.Tenant;

public class LeaseView extends Lease {

    public void LeaseInput() {
        Lease lease = new Lease();
        Scanner sc = new Scanner(System.in);

        lease.setID();
        leaseID = lease.getLeaseID();

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

        LeaseController lc = new LeaseController();

        lc.addNewLease(leaseID, leaseStartDate, leaseEndDate, monthlyRentAmount, false);

    }
}



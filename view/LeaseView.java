package view;
import model.Lease;
import controller.LeaseController;

import java.io.File;
import java.io.FileNotFoundException;
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

    public void addHistoricalLease() {
        Scanner read = null;
        try{
            read = new Scanner(new File("Data\\Leases.txt"));
            read.useDelimiter(",");

            while (read.hasNext()) {
                leaseID = Integer.parseInt(read.next());
                leaseStartDate = read.next();
                leaseEndDate = read.next();
                monthlyRentAmount = Double.valueOf(read.next());
                hasPaidRent = read.nextBoolean();

                LeaseController lc = new LeaseController();

                lc.addNewLease(leaseID, leaseStartDate, leaseEndDate, monthlyRentAmount, false);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        read.close();
    }
}



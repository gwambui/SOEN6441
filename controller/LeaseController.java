package controller;
import model.Lease;
import model.Tenant;

import java.util.ArrayList;

public class LeaseController {
    public static ArrayList<Lease> leases = new ArrayList<Lease>();
    public void addNewLease(int leaseID, String leaseStartDate, String leaseEndDate, Double monthlyRentAmount) {
        Lease l = new Lease();

        l.leaseID = leaseID;
        l.leaseStartDate = leaseStartDate;
        l.leaseEndDate = leaseEndDate;
        l.monthlyRentAmount = monthlyRentAmount;

        leases.add(l);
    }

    public void DisplayLeases(ArrayList<Lease> leaseList, ArrayList<Tenant> tenantList) {
        if (leaseList.isEmpty()) {
            System.out.println("There are no leases inputted in the system");
        }

        else {
            System.out.println("The following is a list of all leases in the system: \n");

            for (int i = 0; i < leaseList.size() && i < tenantList.size(); i++) {
                Lease l1 = leaseList.get(i);
                Tenant t1 = tenantList.get(i);

                System.out.println("The lease ID is: " + l1.getLeaseID());
                System.out.println("The associated tenant ID with this lease is: " + t1.getTenantID());
                System.out.println("The lease start date is: " + l1.getLeaseStartDate());
                System.out.println("The lease end date is: " + l1.getLeaseEndDate());
                System.out.println("The monthly rent for this lease: $" + l1.getMonthlyRentAmount() + "\n");
            }
        }
    }
}

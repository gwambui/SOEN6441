package controller;
import model.Lease;
import model.Tenant;

import java.util.ArrayList;
import java.util.Objects;

public class LeaseController {
    public static ArrayList<Lease> leases = new ArrayList<Lease>();
    
    public void addNewLease(int leaseID, String leaseStartDate, String leaseEndDate, Double monthlyRentAmount, boolean hasPaidRent) {
        Lease l = new Lease();

        l.leaseID = leaseID;
        l.leaseStartDate = leaseStartDate;
        l.leaseEndDate = leaseEndDate;
        l.monthlyRentAmount = monthlyRentAmount;
        l.hasPaidRent = hasPaidRent;

        leases.add(l);
    }

    public void DisplayLeases(ArrayList<Lease> leaseList, ArrayList<Tenant> tenantList) {
        if (leaseList.isEmpty()) {
            System.out.println("There are no leases inputted in the system");
        }

        else {
            System.out.println("The following is a list of all leases in the system: \n");

                for (int i = 0, j=0; i < leaseList.size() && j < tenantList.size();) {

                    Lease l1 = leaseList.get(i);
                    Tenant t1 = tenantList.get(j);

                    if (t1.isCurrentTenant == false) {
                        j++;
                        continue;
                    }

                    System.out.println("The lease ID is: " + l1.getLeaseID());
                    System.out.println("The associated tenant ID with this lease is: " + t1.getTenantID());
                    System.out.println("The lease start date is: " + l1.getLeaseStartDate());
                    System.out.println("The lease end date is: " + l1.getLeaseEndDate());
                    System.out.println("The monthly rent for this lease: $" + l1.getMonthlyRentAmount());
                    System.out.println("The monthly rent has been paid for this lease (true/false): " + l1.getHasPaidRent() + "\n");
                    j++;
                    i++;
                }
            }
    }

    public Double getRentAmount(ArrayList<Lease> leaseList, ArrayList<Tenant> tenantList, int tenantID) {
            Double out = 0.0;

            for (int i = 0, j=0; i < leaseList.size() && j < tenantList.size();) {

                Lease l1 = leaseList.get(i);
                Tenant t1 = tenantList.get(j);

                if (t1.isCurrentTenant == false) {
                    j++;
                    continue;
                }

                if (Objects.equals(t1.getTenantID(), tenantID)) {
                    out = l1.getMonthlyRentAmount();
            }
                j++;
                i++;
        }

        return out;
    }

    public boolean payRent(ArrayList<Lease> leaseList, int tenantID, ArrayList<Tenant> tenantList, double rentAmount, double amountPaid) {
        Double difference = (double) Double.compare(rentAmount, amountPaid);
        boolean out = false;

        if ((difference > 0) || (difference < 0)) {
            out = false;
        }

        else {
            out = true;

            for (int i = 0, j = 0; i < leaseList.size() && j < tenantList.size();) {

                Lease l1 = leaseList.get(i);
                Tenant t1 = tenantList.get(j);

                if (Objects.equals(t1.getTenantID(), tenantID)) {
                    l1.setHasPaidRent(true);
                }

                if (t1.isCurrentTenant == false) {
                    j++;
                    continue;
                }

                j++;
                i++;
            }

        }

        return out;
    }

    public boolean checkIfPaid(ArrayList<Lease> leaseList, ArrayList<Tenant> tenantList, int tenantID) {
        boolean out = false;

        for (int i = 0, j=0; i < leaseList.size() && j < tenantList.size();) {

            Lease l1 = leaseList.get(i);
            Tenant t1 = tenantList.get(j);

            if (t1.isCurrentTenant == false) {
                j++;
                continue;
            }

            if (l1.hasPaidRent == true && Objects.equals(t1.tenantID, tenantID))  {
                out = true;
            }
            j++;
            i++;
        }

        return out;
    }

}

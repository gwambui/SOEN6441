package model;

import java.util.Random;

public class Lease  {

    public String leaseStartDate;
    public String leaseEndDate;
    public Double monthlyRentAmount;
    public int leaseID;

    public boolean hasPaidRent;

    public String getLeaseStartDate() {
        return leaseStartDate;
    }

    public String getLeaseEndDate() {
        return leaseEndDate;
    }

    public Double getMonthlyRentAmount() {
        return monthlyRentAmount;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseStartDate(String leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public void setLeaseEndDate(String leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public void setMonthlyRentAmount(Double monthlyRentAmount) {
        this.monthlyRentAmount = monthlyRentAmount;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "leaseStartDate='" + leaseStartDate + '\'' +
                ", leaseEndDate='" + leaseEndDate + '\'' +
                ", monthlyRentAmount=" + monthlyRentAmount +
                ", leaseID=" + leaseID +
                '}';
    }

    public int setID() {
        Random r = new Random();
        leaseID = (100000 + r.nextInt(1000));
        return leaseID;
    }

    public boolean getHasPaidRent() {
        return hasPaidRent;
    }

    public void setHasPaidRent(boolean hasPaidRent) {
        this.hasPaidRent = hasPaidRent;
    }

}

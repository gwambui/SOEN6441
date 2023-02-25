package model;

public class Tenant {
    String name;
    String email;
    long leaseId;
    long propId;

    public Tenant(String name, String email, int i, int i1) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(long leaseId) {
        this.leaseId = leaseId;
    }

    public long getPropId() {
        return propId;
    }

    public void setPropId(long propId) {
        this.propId = propId;
    }
}

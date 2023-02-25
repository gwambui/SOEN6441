package model;

public abstract class SingleDwelling implements Property{
    long id;
    String sreet;
    String city;
    String postalCode;
    int streetNumber;
    int bedrooms;
    int bathrooms;


    public SingleDwelling(long id, String sreet, String city, String postalCode, int streetNumber, int bedrooms, int bathrooms) {
        this.id = id;
        this.sreet = sreet;
        this.city = city;
        this.postalCode = postalCode;
        this.streetNumber = streetNumber;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
    }

    public SingleDwelling() {

    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSreet() {
        return sreet;
    }

    public void setSreet(String sreet) {
        this.sreet = sreet;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }
}

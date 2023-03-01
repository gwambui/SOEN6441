package model;

public abstract class SingleDwelling implements Property{
    int id;
    String type;
    int buildingId;
    boolean available;
    String sreet;
    String city;
    String postalCode;
    int streetNumber;
    int bedrooms;
    int bathrooms;


    public SingleDwelling (String type, int Id, int buildingId, boolean av, String sreet, String city,
                          String postalCode, int streetNumber, int bedrooms, int bathrooms )
    {   this.type = type;
        this.id = Id;
        this.buildingId = buildingId;
        this.available = av;
        this.sreet = sreet;
        this.city = city;
        this.postalCode = postalCode;
        this.streetNumber = streetNumber;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

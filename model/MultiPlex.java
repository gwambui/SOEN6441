package model;

public abstract class MultiPlex implements Property{



    @Override
    public String toString() {
        return "MultiPlex{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", available=" + available +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", streetNumber=" + streetNumber +
                ", numberofUnits=" + numberofUnits +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }
    int id;
    String type;
    int buildingId;
    boolean available = false;
    String street;
    String city;
    String postalCode;
    int streetNumber;
    int numberofUnits;
    String buildingName;

    public String getType() {
        return type;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public int getId() {
        return id;
    }
    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
    public boolean isAvailable() {
        return false;
    }
}

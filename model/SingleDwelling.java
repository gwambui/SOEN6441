package model;
/*
    SingleDwelling class implements the property interface. Its child classes are Condo, Apartment and House.
    SingleDwelling initializes most of the attributes for its child objects and does the object prints
 */
public abstract class SingleDwelling implements Property{
    int id;

    @Override
    public String toString() {
        return "SingleDwelling{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", buildingId=" + buildingId +
                ", available=" + available +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", streetNumber=" + streetNumber +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }

    String type;
    int buildingId;
    boolean available;
    String street;
    String city;
    String postalCode;
    int streetNumber;
    int bedrooms;
    int bathrooms;
    String buildingName;


    public SingleDwelling (String type, int Id, int buildingId, boolean av, String street, String city,
                          String postalCode, int streetNumber, int bedrooms, int bathrooms)
    {   this.type = type;
        this.id = Id;
        this.buildingId = buildingId;
        this.available = av;
        this.street = street;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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
        return street;
    }

    public void setSreet(String street) {
        this.street = street;
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

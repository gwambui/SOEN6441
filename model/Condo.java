package model;
/*
    Condo class extends SingleDwelling which implements the property interface.
 */
public class Condo extends SingleDwelling{

    int sqfootage;
    int UnitNumber;

    public Condo(String type, int id, int buildingId, boolean av, String street, String city,
                 String postalCode, int streetNumber, int bedrooms, int bathrooms , int unitNumber, int sqfootage)
    {
        super(type, id, buildingId, av, street, city, postalCode, streetNumber, bedrooms, bathrooms);
        this.sqfootage = sqfootage;
        this.UnitNumber = unitNumber;
    }

    public Condo() {

    }

    public String toString() {

        return super.toString()+ " Condo{" +
                "sqfootage=" + sqfootage +
                ", UnitNumber=" + UnitNumber +
                '}';
    }

    @Override
    public String getBuildingName() {
        return buildingName;
    }

    @Override
    public String getStreet() {
        return street;
    }

    public int getCondoNumber() {
        return UnitNumber;
    }
}

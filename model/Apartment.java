package model;

public class Apartment extends SingleDwelling{

    int aptNumber;


    public Apartment(String type, int id, int buildingId, boolean av, String street, String city,
                     String postalCode, int streetNumber, int bedrooms, int bathrooms , int unitNumber)
    {
        super(type, id, buildingId, av, street, city, postalCode, streetNumber, bedrooms, bathrooms);

        this.aptNumber = unitNumber;
    }

    public String toString() {
        return super.toString()+ " Apartment{" +
                "aptNumber=" + aptNumber +
                '}';
    }

    public Apartment() {

    }

    @Override
    public String getBuildingName() {
        return null;
    }

    @Override
    public String getStreet() {
        return street;
    }

}

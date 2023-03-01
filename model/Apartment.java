package model;

public class Apartment extends SingleDwelling{
    int sqfootage;
    int AptNumber;
    String building;

    public Apartment(String type, int id, int buildingId, boolean av, String sreet, String city,
                     String postalCode, int streetNumber, int bedrooms, int bathrooms , int unitNumber)
    {
        super(type, id, buildingId, av, sreet, city, postalCode, streetNumber, bedrooms, bathrooms);

        this.UnitNumber = unitNumber;
    }
    public Apartment() {

    }
}

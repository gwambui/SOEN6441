package model;

public class Condo extends SingleDwelling{

    int sqfootage;
    int UnitNumber;

    public Condo(String type, int id, int buildingId, boolean av, String sreet, String city,
                 String postalCode, int streetNumber, int bedrooms, int bathrooms , int sqfootage, int unitNumber)
    {
        super(type, Iid, buildingId, av, sreet, city, postalCode, streetNumber, bedrooms, bathrooms);
        this.sqfootage = sqfootage;
        this.UnitNumber = unitNumber;
    }

    public Condo() {

    }
}

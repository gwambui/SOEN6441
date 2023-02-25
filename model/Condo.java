package model;

public class Condo extends SingleDwelling{

    int sqfootage;
    int UnitNumber;
    String building;

    public Condo(long id, String sreet, String city, String postalCode, int streetNumber, int bedrooms, int bathrooms) {
        super(id,sreet, city, postalCode, streetNumber, bedrooms, bathrooms);
    }

    public Condo() {

    }
}

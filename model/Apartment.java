package model;

public class Apartment extends SingleDwelling{
    int sqfootage;
    int AptNumber;
    String building;

    public Apartment(long id, String sreet, String city, String postalCode, int streetNumber, int bedrooms, int bathrooms) {
        super(id, sreet, city, postalCode, streetNumber, bedrooms, bathrooms);
    }

    public Apartment() {

    }
}

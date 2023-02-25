package model;

public class House extends SingleDwelling  {


    public House(long id, String sreet, String city, String postalCode, int streetNumber, int bedrooms, int bathrooms) {
        super(id, sreet, city, postalCode, streetNumber, bedrooms, bathrooms);
    }

    public House() {

    }

    @Override
    public String toString() {
        return "House{}";
    }

}

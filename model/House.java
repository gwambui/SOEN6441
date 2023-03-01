package model;

public class House extends SingleDwelling  {


    public House(String type, int ID, int buildingID, boolean av, String sreet, String city,
                 String postalCode, int streetNumber, int bedrooms, int bathrooms )
    {
        super(type, ID, buildingID, av, sreet, city, postalCode, streetNumber, bedrooms,bathrooms);
    }

    public House() {

    }

    @Override
    public String toString() {
        return "House{}";
    }

}

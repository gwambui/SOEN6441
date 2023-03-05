package model;

public class House extends SingleDwelling  {


    public House(String type, int ID, int buildingID, boolean av, String street, String city,
                 String postalCode, int streetNumber, int bedrooms, int bathrooms )
    {
        super(type, ID, buildingID, av, street, city, postalCode, streetNumber, bedrooms,bathrooms);
    }

    public House() {

    }



    @Override
    public String getBuildingName() {
        return null;
    }

    @Override
    public String getStreet() {
        return null;
    }
}

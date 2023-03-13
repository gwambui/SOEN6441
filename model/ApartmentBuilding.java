package model;
/*
    ApartmentBuilding extends the Multiplex class which implements Property interface
    Multiplex represents buildings that contain more than one single dwelling.
    Multiplex handles all the common attributes of its child classes
 */
public class ApartmentBuilding extends MultiPlex{
    public ApartmentBuilding(String type, int ID, boolean available, String street, String city, String postalCode, int streetNumber, int numberofUnits, String buildingName) {

        this.type = type;
        this.id = ID;
        this.available = available;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.streetNumber = streetNumber;
        this.numberofUnits = numberofUnits;
        this.buildingName = buildingName;
    }

    @Override
    public int getBuildingId() {
        return 0;
    }






}

package model;

public class CondoBuilding extends MultiPlex{
    public CondoBuilding(String type, int ID, boolean available, String street, String city, String postalCode, int streetNumber, int numberofUnits,  String buildingName) {
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

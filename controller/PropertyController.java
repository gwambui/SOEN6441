package controller;

import model.*;
import view.PropertyView;

import java.util.ArrayList;

public class PropertyController {
    private PropertyView propertyView;
    PropertyController pc;
    public static ArrayList<Property> properties = new ArrayList<>();

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public PropertyController(PropertyView propertyView) {
        this.propertyView = propertyView;

    }


    public void addNewSd(String type, int id, boolean av, String street, String city,
                         String postalCode, int streetNumber, int bedrooms, int bathrooms) {
        SingleDwelling sd = new House(type, id, 0, av, street, city, postalCode, streetNumber, bedrooms, bathrooms);
        properties.add(sd);
    }


    /*
    Single dwelling for apartment or condo. address information will be fetched from the building info
 */
    public void addNewSd(String type, int id, String buildingName, boolean av, int bedrooms, int bathrooms, int unitNumber, int sqFootage) {
        int buildingId = 0;
        String street = null;
        String city = null;
        String postalCode = null;
        int streetNumber = 0;
        SingleDwelling sd;
        for (Property p : properties) {
            if (p.getType().equals("aptBuilding") || p.getType().equals("condoBuilding")) {
                if (p.getBuildingName().equalsIgnoreCase(buildingName)) {
                    buildingId = p.getId();
                    street = p.getStreet();
                    city = p.getCity();
                    postalCode = p.getPostalCode();
                    streetNumber = p.getStreetNumber();

                }
            }
        }
        if (buildingId == 0) {
            System.out.println("The building you provided is not registered");
            System.out.println("Please register the bulding then add its apartments/condos");
            return;
        }

        if (type.equalsIgnoreCase("apartment")) {
            sd = new Apartment(type, id, buildingId, av, street, city, postalCode, streetNumber, bedrooms, bathrooms, unitNumber);
        } else {
            sd = new Condo(type, id, buildingId, av, street, city, postalCode, streetNumber, bedrooms, bathrooms, unitNumber, sqFootage);

        }
        properties.add(sd);
    }


    public void addNewBuilding(String type, int id, String street, String city, String postalCode, int streetNumber,
                               int numberofUnits, String buildingName) {
        MultiPlex mx = null;
        if (type.equals("condoBuilding")) {
            mx = new CondoBuilding(type, id, false, street, city, postalCode, streetNumber, numberofUnits, buildingName);

        } else if (type.equals("aptBuilding")) {

            mx = new ApartmentBuilding(type, id, false, street, city, postalCode, streetNumber, numberofUnits, buildingName);
        }
        properties.add(mx);

    }


    public void changeAvailableFlag(int buildingID, int apartmentNum) {

        for (Property p : pc.getProperties()) {
            if (!(p.getType().equalsIgnoreCase("condoBuilding")) &&
                    !(p.getType().equalsIgnoreCase("aptBuilding")) &&
                    (p.getBuildingId() == buildingID)) {
                Apartment s = (Apartment) p;
                if (s.getAptNumber() == apartmentNum) {
                    ((Apartment) p).setAvailable(false);
                }
            }
        }
    }
}
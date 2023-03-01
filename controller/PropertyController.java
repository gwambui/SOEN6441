package controller;

import model.House;
import model.Property;
import model.SingleDwelling;
import view.PropertyView;

import java.util.ArrayList;

public class PropertyController {
    private PropertyView propertyView;
    private ArrayList<Property> properties;

    public PropertyController(PropertyView propertyView) {
        this.propertyView = propertyView;
        this.properties = new ArrayList<Property>();
    }


    public void addNewproperty() {
    }

    public void addNewSd(String type, int ID, String buildingName, boolean av, String sreet, String city,
                            String postalCode, int streetNumber, int bedrooms, int bathrooms ){
        buildingId = null;
        if (type.equals("house")){
            SingleDwelling sd = new House(type, ID, null, av, sreet, city, postalCode, streetNumber, bedrooms,bathrooms);
            properties.add(sd);
        }
        else{

            for (Property p : properties){
                if (p.buildingName.equals(buildingName) & (p.type.equals("aptBulding")|| p.type.equals("condoBulding"))){
                    buildingId = p.id;
                }
            }
            if(buildingId == null){
                System.out.println("The building you provided is not registered");
                System.out.println("Please register the bulding then add its apartments/condos");
                exit();
            }
        }


    }

    public void addNewBuilding(String type, int ID,  String sreet, String city,
                               String postalCode, int streetNumber, int numberofUnits, String buildingName){
        if type.equals("condoBuilding"){
            Multiplex mx = new CondoBuilding(type, ID, sreet, city, postalCode, streetNumber, numberofUnits, buildingName)

        }else if type.equals("aptBuilding"){
            Multiplex mx = new ApartmentBuilding(type, ID, sreet, city, postalCode, streetNumber, numberofUnits, buildingName)
        }
        properties.add(mx)


    }
}

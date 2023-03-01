package view;

import model.Property;
import model.SingleDwelling;
import java.util.Random

import java.util.ArrayList;

public class PropertyView {
    PropertyController pc = new PropertyController();
    /*
    Property attributes
     */
    String type;
    int ID;
    int buildingID;
    boolean avail;
    String sreet;
    String city;
    String postalCode;
    int streetNumber;
    int bedrooms;
    int bathrooms;
    int sqfootage;
    int unitNumber;
    //for multiplexes
    int numberofUnits;
    String buildingName;
    public void printPropertyDetails(ArrayList<SingleDwelling> singleDwelling){
        for (SingleDwelling prop : singleDwelling ) {
            System.out.println("property type: "+ prop.getClass());
            System.out.println("address: " + prop.getPostalCode());
            System.out.println("street name : " + prop.getCity() );
            System.out.println("postal code: " + prop.getSreet());
        }

    }

    public void addProperty() {

        int ID = (int)Math.floor(Math.random() * (10000 - 999 + 1) + 999);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the property attributes below");

        do{
            System.out.println("property type numeric choice: 1.house, 2.apartment, 3.condo, 4.Apartment building, 5.Condo Building");
            int type = sc.nextInt();
            switch (type) {
                case 1:
                    addProperty("house");
                    break;
                case 2:
                    addProperty("apartment");
                    break;
                case 3:
                    addProperty("condo");
                    break;
                case 4:
                    addProperty("aptBuilding");
                    break;
                case 5:
                    addProperty("condoBuilding");
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }while(type > 5 || type < 1)

        }
        System.out.println("address: " + prop.getPostalCode());
        System.out.println("street name : " + prop.getCity() );
        System.out.println("postal code: " + prop.getSreet());
    public void addProperty(String type){
        ID = (int)Math.floor(Math.random() * (10000 - 999 + 1) + 999);
        System.out.println("Enter the street name");
        sreet = sc.nextLine()
        System.out.println("Enter the city name");
        city = sc.nextLine();
        System.out.println("Enter the postal code");
        postalCode = sc.nextLine();
        System.out.println("Enter the street number");
        streetNumber = sc.nextint();

        if (type.equals("aptBuilding")|| type.equals("condoBuilding"))){
//          Add multiplex Attributes
            System.out.println("Enter the number of units in the building");
            numberofUnits = sc.nextint();
            System.out.println("Enter the building name");
            buildingName = sc.nextLine();
            pc.addNewBuilding(type, ID, sreet, city, postalCode, streetNumber, numberofUnits, buildingName)
        }
        else {
            (type.equals("apartment") || type.equals("condo") || type.equals("house"))

            System.out.println("Enter the building name type 'none' for house");
            buildingName = sc.nextLine();
            System.out.println("Is the property available? y/n ");
            char av = sc.nextLine()
            avail = (av == 'y')?;
            System.out.println("Enter the number of bedrooms");
            bedrooms = sc.nextint();
            System.out.println("Enter the number of bathrooms");
            bathrooms = sc.nextint();
            if (type.equals("house")){
                buildingID = null;

            }
            pc.addNewSd(type, ID, buildingID, avail, sreet, city, postalCode, streetNumber, bedrooms, bathrooms )
        }


    }

    public void multiplexAttributes(){
        System.out.println("Enter the number of units in the building");
        numberofUnits = sc.nextint();
        System.out.println("Enter the building name");
        buildingName = sc.nextLine();


    }
}

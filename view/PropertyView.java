package view;

import controller.PropertyController;
import model.Apartment;
import model.Condo;
import model.Property;
import model.SingleDwelling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import java.util.ArrayList;
import java.util.Scanner;

public class PropertyView {
    public PropertyView() {
        this.pc = new PropertyController(this);

    }

    PropertyController pc;// = new PropertyController();
    /*
    Property attributes
     */
    String type;
    int id;
    int buildingID;
    boolean avail;
    String street;
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
//    String country;

    public void printPropertyDetails(ArrayList<SingleDwelling> singleDwelling) {
        for (SingleDwelling prop : singleDwelling) {
            System.out.println("property type: " + prop.getClass());
            System.out.println("address: " + prop.getPostalCode());
            System.out.println("street name : " + prop.getCity());
            System.out.println("postal code: " + prop.getStreet());
        }

    }

    /*
         get info from user for type of property to add.
         case house,apartment,condo, condoBuilding,aptBuilding
         //HOUSE
         long id;
         String street;
         String city;
         String postalCode;
         int streetNumber;
         int bedrooms;
         int bathrooms;
  */
    public void addProperty() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the property attributes below");
        int type;
        do {
            System.out.println("property type numeric choice: 1.house, 2.apartment, 3.condo, 4.Apartment building, 5.Condo Building");
            type = sc.nextInt();
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
        } while (type > 5 || type < 1);

    }


//        System.out.println("address: "+prop.getPostalCode());
//        System.out.println("street name : "+prop.getCity());
//        System.out.println("postal code: "+prop.getStreet());

    public void addProperty(String type){
        id = (int)Math.floor(Math.random() * (10000 - 999 + 1) + 999);
        Scanner sc = new Scanner(System.in);
        if (type.equals("aptBuilding")|| type.equals("condoBuilding")){
//          Add multiplex Attributes
            System.out.println("Enter the street name");
            street = sc.nextLine();
            System.out.println("Enter the city name");
            city = sc.nextLine();
            System.out.println("Enter the postal code");
            postalCode = sc.nextLine();
            System.out.println("Enter the street number");
            streetNumber = sc.nextInt();
            System.out.println("Enter the number of units in the building");
            numberofUnits = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the building name");
            buildingName = sc.nextLine();

            pc.addNewBuilding(type, id, street, city, postalCode, streetNumber, numberofUnits, buildingName);
        }
        else if (type.equals("house")) {
            System.out.println("Enter the street name");
            street = sc.nextLine();
            System.out.println("Enter the city name");
            city = sc.nextLine();
            System.out.println("Enter the postal code");
            postalCode = sc.nextLine();
            System.out.println("Enter the street number");
            streetNumber = sc.nextInt();
            System.out.println("Is the property available? y/n ");
            char av = sc.next().charAt(0);
            avail = (av == 'y')? true : false;
            System.out.println("Enter the number of bedrooms");
            bedrooms = sc.nextInt();
            System.out.println("Enter the number of bathrooms");
            bathrooms = sc.nextInt();
            pc.addNewSd(type, id,  avail, street, city, postalCode, streetNumber, bedrooms, bathrooms);
        }
        else if (type.equals("apartment") || type.equals("condo"))
        {
            System.out.println("Enter the building name ");
//            sc.nextLine();
            buildingName = sc.nextLine();
            System.out.println("Is the property available? y/n ");
            char av = sc.next().charAt(0);
            avail = (av == 'y')? true : false;
            System.out.println("Enter the number of bedrooms");
            bedrooms = sc.nextInt();
            System.out.println("Enter the number of bathrooms");
            bathrooms = sc.nextInt();
            System.out.println("Enter the apartment or condo unit number");
            int unitNumber = sc.nextInt();
            System.out.println("Enter the condo square footage, 0 for apartment");
            int sqfootage =  sc.nextInt();
            pc.addNewSd(type, id, buildingName, avail, bedrooms, bathrooms, unitNumber, sqfootage);
        }

    }




    public void addHistoricalProperty(){
        Scanner read = null;
        String country;
        try {
            read = new Scanner(new File("..\\data\\sampleProps.txt"));
            read.useDelimiter(",");
            while (read.hasNext())
            {
                type = read.next().strip();
                id = Integer.parseInt(read.next());
                street = read.next();
                city = read.next();
                postalCode = read.next();
                streetNumber = Integer.parseInt(read.next());
                numberofUnits = Integer.parseInt(read.next());
                buildingName = read.next();
                country = read.next();

                pc.addNewBuilding(type, id, street, city, postalCode, streetNumber, numberofUnits, buildingName);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        read.close();
//        read house list
        try{
            read = new Scanner(new File("data\\sampleHouse.txt"));
            read.useDelimiter(",");

            while (read.hasNext()) {
                type = read.next().strip();
                id = Integer.parseInt(read.next());
                street = read.next();
                city = read.next();
                postalCode = read.next();
                bedrooms = Integer.parseInt(read.next());
                bathrooms = Integer.parseInt(read.next());
                country = read.next();
                pc.addNewSd(type, id, true, street, city, postalCode, streetNumber, bedrooms, bathrooms);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        read.close();
        //read apartment/condo list
        try{
            read = new Scanner(new File("data\\sampleCondo.txt"));
            read.useDelimiter(",");

            while (read.hasNext()) {
                type = read.next().strip();
                id = Integer.parseInt(read.next());
                buildingName = read.next();
                avail = read.nextBoolean();
                bedrooms = Integer.parseInt(read.next());
                bathrooms = Integer.parseInt(read.next());
                unitNumber = Integer.parseInt(read.next());
                sqfootage = Integer.parseInt(read.next());
                country = read.next();
                pc.addNewSd(type, id, buildingName, avail, bedrooms, bathrooms, unitNumber, sqfootage);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        read.close();

    }

    public void displayProperties() {

        for (Property p : pc.getProperties()){
            System.out.println(p);
        }
    }

    public void displayRentedUnits() {
        for (Property p : pc.getProperties()) {
            if (!(p.getType().equalsIgnoreCase("condoBuilding")) &&
                    !(p.getType().equalsIgnoreCase("aptBuilding")) &&
                    p.isAvailable() == false ){
                System.out.println(p);
            }
        }
    }
    public void displayVacantUnits() {
        for (Property p : pc.getProperties()) {
            if (p.isAvailable() == true) {
                System.out.println(p);
            }
        }
    }

    public void changeAvailableFlag(int buildingID, int unitNum) {

        for (Property p : pc.getProperties()) {
            if (!(p.getType().equalsIgnoreCase("condoBuilding")) &&
                    !(p.getType().equalsIgnoreCase("aptBuilding")) &&
                    (p.getBuildingId() == buildingID)) {

                if (p.getType().equals("apartment")) {
                    Apartment s = (Apartment) p;
                    if (s.getAptNumber() == unitNum) {
                        ((Apartment) p).setAvailable(false);
                    }
                }

                else if (p.getType().equals("condo")) {
                    Condo c = (Condo) p;
                    if (c.getCondoNumber() == unitNum) {
                        ((Condo) c).setAvailable(false);
                    }
                }
            }
        }
    }

    public boolean checkID(int buildingID) {
        boolean out = false;

        for (Property p : pc.getProperties()) {
            if (p.getBuildingId() == buildingID) {
                out = true;
            }
        }
        return out;
    }

    public boolean checkApartmentNo(int buildingID, int apartmentNum) {
        boolean out = false;

        for (Property p : pc.getProperties()) {
            if (p.getBuildingId() == buildingID) {
                Apartment s = (Apartment) p;
                if (s.getAptNumber() == apartmentNum) {
                    out = true;
                }
            }
        }
        return out;
    }

    public boolean checkCondoNo(int buildingID, int condoNum) {
        boolean out = false;

        for (Property p : pc.getProperties()) {
            if (p.getBuildingId() == buildingID) {
                Condo c = (Condo) p;
                if (c.getCondoNumber() == condoNum) {
                    out = true;
                }
            }
        }
        return out;
    }

    public boolean isCondo(int buildingID) {
        boolean out = false;

        for (Property p : pc. getProperties()) {
            if ((p.getBuildingId() == buildingID) && (p.getType().equals("condo"))) {
                out = true;
            }
        }
        return out;
    }

    public boolean checkCondoAvailability(int buildingID, int unitNumber) {
        boolean out = false;

        for (Property p : pc. getProperties()) {
            if (p.getBuildingId() == buildingID) {
                Condo c = (Condo) p;
                if (c.getCondoNumber() == unitNumber && p.isAvailable()) {
                    out = true;
                }
            }
        }

        return out;
    }

    public boolean checkApartmentAvailability(int buildingID, int unitNumber) {
        boolean out = false;

        for (Property p : pc. getProperties()) {
            if (p.getBuildingId() == buildingID) {
                Apartment s = (Apartment) p;
                if (s.getAptNumber() == unitNumber && p.isAvailable()) {
                    out = true;
                }
            }
        }

        return out;
    }



}

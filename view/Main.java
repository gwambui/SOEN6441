package view;

import controller.PropertyController;
import controller.TenantController;

//    Options
//    Add a property
//    Add a tenant
//    Rent a unit
//    Display properties ->PropertyView();
//    Display tenants
//    Display rented units
//    Display vacant units
//    Display all leases
//    Exit
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //    create property MVC
//        Property house1 = new House();
        PropertyView propertyView = new PropertyView();
        PropertyController pc = new PropertyController(propertyView);
        /*
        get info from user for type of property to add.
        case house,apartment,condo, condoBuilding,aptBuilding
        //HOUSE
        long id;
        String sreet;
        String city;
        String postalCode;
        int streetNumber;
        int bedrooms;
        int bathrooms;
         */
        switch ("prop".toLowerCase()){
            case "house":

                pc.addNewHouse(123,"street","city", "pCode", 12,2,2);

        }




//        create tenant mvc
        TenantView tv = new TenantView();
        TenantController tc = new TenantController(tv);
        tc.addNewTenant("name","email",1234,5678);
    }





}
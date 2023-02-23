package view;

import controller.PropertyController;
import model.House;
import model.Property;

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
        //    create property model
        Property house1 = new House();
        PropertyView propertyView = new PropertyView();
        PropertyController pc = new PropertyController(propertyView);
    }





}
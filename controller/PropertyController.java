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

    public void addNewHouse(long id,String street, String city, String pCode , int strNum ,int bath,int br) {
        SingleDwelling sd = new House(123,"street","city", "pCode", 12,
                2,2);
        properties.add(sd);
    }
}

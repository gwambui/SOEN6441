package controller;

import model.Property;
import view.PropertyView;

import java.util.ArrayList;

public class PropertyController {
    private PropertyView propertyView;
    private ArrayList<Property> properties;

    public PropertyController(PropertyView propertyView) {
        this.propertyView = propertyView;
        this.properties = new ArrayList<Property>();
    }



}

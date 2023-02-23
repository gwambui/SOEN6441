package view;

import model.Property;
import model.SingleDwelling;

import java.util.ArrayList;

public class PropertyView {

    public void printPropertyDetails(ArrayList<SingleDwelling> singleDwelling){
        for (SingleDwelling prop : singleDwelling ) {
            System.out.println("property type: "+ prop.getClass());
            System.out.println("address: " + prop.getPostalCode());
            System.out.println("street name : " + prop.getCity() );
            System.out.println("postal code: " + prop.getSreet());
        }

    }

}

package view;


import controller.PropertyController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Apartment;
import model.Condo;
import model.Property;
import model.SingleDwelling;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;

public class PropertyViewFX {

    public PropertyViewFX(Stage stage) {
        this.pc = new PropertyController(this);
        this.stage = stage;
    }
    PropertyController pc;// = new PropertyController();
    Stage stage;
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
    public void addProperty(Group base) {
//        Property grop
        Group propertyGroup = new Group();
//        Create radio button to select propery type
        Label propLabel = new Label("What type of property will you add");
        RadioButton prb1 = new RadioButton("house");
        RadioButton prb2 = new RadioButton("apartment");
        RadioButton prb3 = new RadioButton("condo");
        RadioButton prb4 = new RadioButton("apartment building");
        RadioButton prb5 = new RadioButton("condo building");

        prb1.setUserData("house");
        prb2.setUserData("apartment");
        prb3.setUserData("condo");
        prb4.setUserData("apartment building");
        prb5.setUserData("condo building");

        final ToggleGroup propToggle = new ToggleGroup();
        prb1.setToggleGroup(propToggle);
        prb2.setToggleGroup(propToggle);
        prb3.setToggleGroup(propToggle);
        prb4.setToggleGroup(propToggle);
        prb5.setToggleGroup(propToggle);

        HBox propBox = new HBox(50);
        propBox.setFillHeight(false);
        propBox.setPadding(new Insets(100, 5, 5, 50));
        propBox.getChildren().addAll(propLabel,prb1,prb2,prb3,prb4,prb5);

//        Create form to fill property info
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.setPadding(new Insets(25, 125, 25, 25));
//        grid.setPadding(new Insets(25, 25, 25, 25));
        propertyGroup.getChildren().addAll(propBox);
        base.getChildren().add(propertyGroup);
//        Scene scene = new Scene(base, 300, 275);

        propToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                RadioButton rbp = (RadioButton)propToggle.getSelectedToggle();
                propLabel.setText(" selected");
                if (rbp != null) {
                    String selection = propToggle.getSelectedToggle().getUserData().toString();
                    propLabel.setText(selection + " selected");
                    if (selection.equalsIgnoreCase("house")) {
                        propertyGroup.getChildren().remove(propBox);
                        houseform(grid,propertyGroup);

                    }else if (selection.equalsIgnoreCase("apartment") || selection.equalsIgnoreCase("condo")) {
                        apartmentCondoform(grid,base);
                    }else if (selection.equalsIgnoreCase("apartment building")|| selection.equalsIgnoreCase("condo building")) {
                        buildingForm(grid,base);
                    }

                }
            }
        });


    }

    public void houseform(GridPane grid,Group pGroup){

        id = (int)Math.floor(Math.random() * (10000 - 999 + 1) + 999);
        Text scenetitle = new Text("To add a house, please provide the following details");
        scenetitle.setX(100);
        scenetitle.setY(100);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        grid.add(scenetitle, 0, 4, 2, 1);

        Label streetlabel = new Label("street name:");
        grid.add(streetlabel, 0, 5);

        TextField streetName = new TextField();
        grid.add(streetName, 1, 5);

        Label citylabel = new Label("city name:");
        grid.add(citylabel, 0, 6);

        TextField cityName = new TextField();
        grid.add(cityName, 1, 6);

        Label postalCodeLabel = new Label("postal code:");
        grid.add(postalCodeLabel, 0, 7);

        TextField pCode = new TextField();
        grid.add(pCode, 1, 7);

        Label strNumberLabel = new Label("street number:");
        grid.add(strNumberLabel, 0, 8);

        TextField strNum = new TextField();
        grid.add(strNum, 1, 8);

        Label availLabel = new Label("Is the property available? y/n:");
        grid.add(availLabel, 0, 9);

        TextField available = new TextField();
        grid.add(available, 1, 9);

        Label bedLabel = new Label("number of bedrooms");
        grid.add(bedLabel, 0, 10);

        TextField beds = new TextField();
        grid.add(beds, 1, 10);

        Label bathLabel = new Label("number of bathrooms");
        grid.add(bathLabel, 0, 11);

        TextField baths = new TextField();
        grid.add(baths, 1, 11);

        Button btn = new Button("SUBMIT");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 13);
        pGroup.getChildren().add(grid);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 13);

        btn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                actiontarget.setFill(Color.BLUEVIOLET);
                actiontarget.setText("Information submitted");
                String street = streetName.getText();
                Boolean avail = available.getText().equalsIgnoreCase ("y")? true : false;
                String postalCode = pCode.getText();
                Integer bedrooms = Integer.parseInt(beds.getText());
                Integer bathrooms = Integer.parseInt(baths.getText());
                String city = cityName.getText();
                Integer streetNumber = Integer.parseInt(strNum.getText());
//                TODO
//                PASS THIS ACTION TO ANOTHER THREAD
                pc.addNewSd(type, id,  avail, street, city, postalCode, streetNumber, bedrooms, bathrooms);
            }

        });


//        Scene scene = new Scene(pGroup, 300, 275);
//        stage.setScene(scene);
//        stage.show();



    }

    public void apartmentCondoform(GridPane grid,Group base){
        id = (int)Math.floor(Math.random() * (10000 - 999 + 1) + 999);
        Text scenetitle = new Text("To add an apartment or condo, please provide the following details");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        TextField buildingName = new TextField("Building name");
        grid.add(buildingName, 1, 1);

        TextField av = new TextField("Is the property available? y/n");
        grid.add(av, 1, 1);
//        Boolean avail = (av == 'y')? true : false;
        TextField bedrooms = new TextField("number of bedrooms");
        grid.add(bedrooms, 1, 1);

        TextField bathrooms = new TextField("number of bathrooms");
        grid.add(bathrooms, 1, 1);

        TextField unitNumber = new TextField("unit number");
        grid.add(unitNumber, 1, 1);

        TextField squareFootage = new TextField("cond square footage 0 if the entry is an apartment");
        grid.add(squareFootage, 1, 1);

//        pc.addNewSd(type, id, buildingName, avail, bedrooms, bathrooms, unitNumber, sqfootage);
    }

    public void buildingForm(GridPane grid,Group base){
        id = (int)Math.floor(Math.random() * (10000 - 999 + 1) + 999);
        Text scenetitle = new Text("To add an apartment or condo bulding, please provide the following details");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        TextField streetName = new TextField("street name:");
        grid.add(streetName, 0, 1);

        TextField cityName = new TextField("city name:");
        grid.add(streetName, 0, 1);

        TextField postalCode = new TextField("postal code:");
        grid.add(postalCode, 0, 1);

        TextField streetNumber = new TextField("street number:");
        grid.add(streetNumber, 0, 1);

        TextField unitCount = new TextField("Enter the number of units in the building");
        grid.add(unitCount, 1, 1);

        TextField bedrooms = new TextField("number of bedrooms");
        grid.add(bedrooms, 1, 1);

        TextField bathrooms = new TextField("number of bathrooms");
        grid.add(bathrooms, 1, 1);
    }
    public void addProperty(String type){
        id = (int)Math.floor(Math.random() * (10000 - 999 + 1) + 999);
        Scanner sc = new Scanner(System.in);
        if (type.equals("aptBuilding")|| type.equals("condoBuilding")){
//          Add multiplex Attributes


            System.out.println("Enter the building name");
            buildingName = sc.nextLine();

            pc.addNewBuilding(type, id, street, city, postalCode, streetNumber, numberofUnits, buildingName);
        }



    }


    public void addHistoricalProperty(){
        Scanner read = null;
        String country;
        try {
            read = new Scanner(new File("ph1\\src\\data\\sampleProps.txt"));
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
            read = new Scanner(new File("ph1\\src\\data\\sampleHouse.txt"));
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
            read = new Scanner(new File("ph1\\src\\data\\sampleCondo.txt"));
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

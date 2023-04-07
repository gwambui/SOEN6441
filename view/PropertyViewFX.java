package view;


import controller.PropertyController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class PropertyViewFX extends Thread {

    public PropertyViewFX(Stage stage, Group base, VBox adminBox) {
        this.pc = new PropertyController(this);
        this.stage = stage;
        this.base = base;
        this.adminBox = adminBox;
    }
    PropertyController pc;// = new PropertyController();
    Stage stage;
    Group base;
    VBox adminBox;
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
    public void addProperty(Group base, VBox adminBox) {
//        Property grop
        Group propertyGroup = new Group();
//        Create radio button to select propery type
        Label propLabel = new Label("What type of property will you add");
        RadioButton prb1 = new RadioButton("house");
        RadioButton prb2 = new RadioButton("apartment");
        RadioButton prb3 = new RadioButton("condo");
        RadioButton prb4 = new RadioButton("apartment building");
        RadioButton prb5 = new RadioButton("condo building");
        RadioButton prb6 = new RadioButton("back");

        prb1.setUserData("house");
        prb2.setUserData("apartment");
        prb3.setUserData("condo");
        prb4.setUserData("aptBuilding");
        prb5.setUserData("condoBuilding");
        prb6.setUserData("back");

        final ToggleGroup propToggle = new ToggleGroup();
        prb1.setToggleGroup(propToggle);
        prb2.setToggleGroup(propToggle);
        prb3.setToggleGroup(propToggle);
        prb4.setToggleGroup(propToggle);
        prb5.setToggleGroup(propToggle);
        prb6.setToggleGroup(propToggle);

        HBox propBox = new HBox(50);
        propBox.setFillHeight(false);
        propBox.setPadding(new Insets(100, 5, 5, 50));
        propBox.getChildren().addAll(propLabel,prb1,prb2,prb3,prb4,prb5,prb6);

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
                base.getChildren().remove(adminBox);
                if (rbp != null) {
                    String selection = propToggle.getSelectedToggle().getUserData().toString();
                    propLabel.setText(selection + " selected");
                    if (selection.equalsIgnoreCase("house")) {
                        prb1.setSelected(false);
                        base.getChildren().remove(adminBox);
                        propertyGroup.getChildren().remove(propBox);
                        houseform(selection,grid,propertyGroup,base,adminBox);
//                        if (complete) base.getChildren().add(adminBox);
                    }else if (selection.equalsIgnoreCase("apartment") || selection.equalsIgnoreCase("condo")) {
                        base.getChildren().remove(adminBox);
                        propertyGroup.getChildren().remove(propBox);
                        prb2.setSelected(false);
                        prb3.setSelected(false);
                        apartmentCondoform(selection,grid,propertyGroup,base,adminBox);

                    }else if (selection.equalsIgnoreCase("aptBuilding")|| selection.equalsIgnoreCase("condoBuilding")) {
                        base.getChildren().remove(adminBox);
                        propertyGroup.getChildren().remove(propBox);
                        prb4.setSelected(false);
                        prb5.setSelected(false);
                        buildingForm(selection,grid,propertyGroup,base,adminBox);
                    } else if (selection.equalsIgnoreCase("back")) {
                        propertyGroup.getChildren().remove(propBox);
                        base.getChildren().remove(propertyGroup);
                        base.getChildren().add(adminBox);

                    }
                }
            }
        });


    }

    public void houseform(String type, GridPane grid, Group pGroup, Group base, VBox adminBox){

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
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 13);
        pGroup.getChildren().add(grid);

//        Button back = new Button("BACK");
//        hbBtn.getChildren().add(back);
//        grid.add(hbBtn, 0, 13);


        btn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {

                String street = streetName.getText();
                String city = cityName.getText();
                Integer streetNumber = Integer.parseInt(strNum.getText());
                String postalCode = pCode.getText();
                Boolean avail = available.getText().equalsIgnoreCase ("y")? true : false;
                Integer bedrooms = Integer.parseInt(beds.getText());
                Integer bathrooms = Integer.parseInt(baths.getText());
//                TODO
//                PASS THIS ACTION TO ANOTHER THREAD
                pc.addNewSd(type, id,  avail, street, city, postalCode, streetNumber, bedrooms, bathrooms);
                pGroup.getChildren().remove(grid);
                base.getChildren().remove(pGroup);
                base.getChildren().add(adminBox);

            }

        });
//        back.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
//            @Override
//            public void handle(javafx.event.ActionEvent e) {
//                pGroup.getChildren().remove(grid);
//                base.getChildren().remove(pGroup);
//                base.getChildren().add(adminBox);
//
//            }
//
//        });

    }

    public void apartmentCondoform(String type,GridPane grid,Group pGroup,Group base,VBox adminBox){

        Text scenetitle = new Text("To add an apartment or condo, please provide the following details");
        id = (int)Math.floor(Math.random() * (10000 - 999 + 1) + 999);
        scenetitle.setX(100);
        scenetitle.setY(100);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 4, 2, 1);

        Label buildLabel = new Label("Building name: ");
        grid.add(buildLabel, 0, 5);

        TextField building = new TextField();
        grid.add(building, 1, 5);

        Label availLabel = new Label("Is the property available? y/n:");
        grid.add(availLabel, 0, 6);

        TextField available = new TextField();
        grid.add(available, 1, 6);

        Label bedLabel = new Label("number of bedrooms");
        grid.add(bedLabel, 0, 7);

        TextField beds = new TextField();
        grid.add(beds, 1, 7);

        Label bathLabel = new Label("number of bathrooms");
        grid.add(bathLabel, 0, 8);

        TextField baths = new TextField();
        grid.add(baths, 1, 8);

        Label unitLabel = new Label("unit number");
        grid.add(unitLabel, 0, 9);

        TextField unit = new TextField();
        grid.add(unit, 1, 9);

        Label sqfLabel = new Label("cond square footage 0 if the entry is an apartment");
        grid.add(sqfLabel, 0, 10);

        TextField squareFootage = new TextField();
        grid.add(squareFootage, 1, 10);

        Button btn = new Button("SUBMIT");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 13);
        pGroup.getChildren().add(grid);

//        Button back = new Button("BACK");
//        hbBtn.getChildren().add(back);
//        grid.add(hbBtn, 0, 13);


        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 13);

        btn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                actiontarget.setFill(Color.BLUEVIOLET);
                actiontarget.setText("Information submitted");
                String buildingName = building.getText();
                Boolean avail = available.getText().equalsIgnoreCase ("y")? true : false;
                Integer bedrooms = Integer.parseInt(beds.getText());
                Integer bathrooms = Integer.parseInt(baths.getText());
                Integer unitNumber = Integer.parseInt(unit.getText());
                Integer sqfootage = Integer.parseInt(squareFootage.getText());
//                TODO
//                PASS THIS ACTION TO ANOTHER THREAD
                pc.addNewSd(type, id, buildingName, avail, bedrooms, bathrooms, unitNumber, sqfootage);
                pGroup.getChildren().remove(grid);
                base.getChildren().remove(pGroup);
                base.getChildren().add(adminBox);
            }

        });
//        back.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
//            @Override
//            public void handle(javafx.event.ActionEvent e) {
//                pGroup.getChildren().remove(grid);
//                base.getChildren().remove(pGroup);
//                base.getChildren().add(adminBox);
//
//            }
//
//        });
    }

    public void buildingForm(String type,GridPane grid,Group pGroup,Group base,VBox adminBox){
        id = (int)Math.floor(Math.random() * (10000 - 999 + 1) + 999);
        Text scenetitle = new Text("To add an apartment or condo bulding, please provide the following details");
        scenetitle.setX(100);
        scenetitle.setY(100);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

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

        Label unitCountLabel = new Label("Enter the number of units in the building");
        grid.add(unitCountLabel, 0, 9);

        TextField unitCount = new TextField();
        grid.add(unitCount, 1, 9);

        Label bldNameLabel = new Label("Enter the Building name");
        grid.add(bldNameLabel, 0, 10);

        TextField buildName = new TextField();
        grid.add(buildName, 1, 10);

        Button btn = new Button("SUBMIT");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 13);
        pGroup.getChildren().add(grid);

//        Button back = new Button("BACK");
//        hbBtn.getChildren().add(back);
//        grid.add(hbBtn, 0, 13);


        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 13);

        btn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                actiontarget.setFill(Color.BLUEVIOLET);
                actiontarget.setText("Information submitted");
                String street = streetName.getText();
                String city = cityName.getText();
                Integer streetNumber = Integer.parseInt(strNum.getText());
                String postalCode = pCode.getText();
                String buildingName = buildName.getText();

                Integer numberofUnits = Integer.parseInt(unitCount.getText());

//                TODO
//                PASS THIS ACTION TO ANOTHER THREAD
                pc.addNewBuilding(type, id, street, city, postalCode, streetNumber, numberofUnits, buildingName);

                pGroup.getChildren().remove(grid);
                base.getChildren().remove(pGroup);
                base.getChildren().add(adminBox);
            }

        });
//        back.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
//            @Override
//            public void handle(javafx.event.ActionEvent e) {
//                pGroup.getChildren().remove(grid);
//                base.getChildren().remove(pGroup);
//                base.getChildren().add(adminBox);
//
//            }
//
//        });
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

    public void displayProperties(Group base, VBox adminBox) {
        int i= 2;
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(50, 5, 5, 25));

        Text sceneTitle = new Text("Property list");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
        pane.add(sceneTitle, 0, 0, 2, 1);

        Button back = new Button("BACK");
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().add(back);
        pane.add(hbox, 0, 0,2, 1);

        for (Property p : pc.getProperties()){

            i+=1;
            Text propertyInfo = new Text(p.toString());
            pane.add(propertyInfo, 1, i);

        }

        base.getChildren().add(pane);



        back.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                base.getChildren().remove(pane);
                base.getChildren().add(adminBox);
            }
        });
    }

    public void displayRentedUnits(Group base, VBox adminBox) {
        int i= 2;
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(50, 5, 5, 25));

        Text sceneTitle = new Text("Rented Properties");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
        pane.add(sceneTitle, 0, 0, 2, 1);

        Button back = new Button("BACK");
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().add(back);
        pane.add(hbox, 0, 0,2, 1);
        for (Property p : pc.getProperties()) {
            if (!(p.getType().equalsIgnoreCase("condoBuilding")) &&
                    !(p.getType().equalsIgnoreCase("aptBuilding")) &&
                    p.isAvailable() == false ){
                i += 1;
                Text propertyInfo = new Text(p.toString());
                pane.add(propertyInfo, 1, i);
            }
        }
        base.getChildren().add(pane);

        back.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                base.getChildren().remove(pane);
                base.getChildren().add(adminBox);
            }
        });


    }
    public void displayVacantUnits(Group base, VBox adminBox) {
        int i= 2;
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(50, 5, 5, 25));

        Text sceneTitle = new Text("Vacant Properties");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
        pane.add(sceneTitle, 0, 0, 2, 1);

        Button back = new Button("BACK");
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().add(back);
        pane.add(hbox, 0, 0,2, 1);
        for (Property p : pc.getProperties()) {
            if (p.isAvailable() == true) {
                i += 1;
                Text propertyInfo = new Text(p.toString());
                pane.add(propertyInfo, 1, i);
            }
        }
        base.getChildren().add(pane);

        back.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent e) {
                base.getChildren().remove(pane);
                base.getChildren().add(adminBox);
            }
        });
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

    public void run () {
        displayProperties(base, adminBox);
    }



}
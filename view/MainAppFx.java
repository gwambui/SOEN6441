package view;

import controller.LeaseController;
import controller.TenantController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Lease;

import static controller.LeaseController.leases;
import static controller.TenantController.tenants;

public class MainAppFx extends Application {

    @Override
    public void start(Stage stage) {
        Lease lease = new Lease();

        // Create tenant objects
        TenantView tv = new TenantView();
        TenantController tc = new TenantController();
        LeaseController lc = new LeaseController();
        PayRentView prv = new PayRentView();
        PropertyViewFX pvfx = new PropertyViewFX(stage);

        //Setting title to the Stage
        stage.setTitle("Main Menu");

        String greeting= "Hello and welcome!";
        Text text = new Text();
        text.setText(greeting);
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        Group base = new Group();
        Group root = new Group(text);

        //A radio button with the specified label
        RadioButton rb1 = new RadioButton("Sign in as Administrator");
        RadioButton rb2 = new RadioButton("Sign in as Tenant");
        RadioButton rb3 = new RadioButton("Exit");
        final ToggleGroup mainToggle = new ToggleGroup();

        rb1.setToggleGroup(mainToggle);
        rb2.setToggleGroup(mainToggle);
        rb3.setToggleGroup(mainToggle);

        Label l1 = new Label("Root Menu");
        Label l2 = new Label("nothing selected");
        root.getChildren().add(l1);


        rb1.setUserData("admin");
        rb2.setUserData("tenant");
        rb3.setUserData("exit");
    // create a tile pane
        VBox box = new VBox(25);
        box.setFillWidth(false);
        box.setPadding(new Insets(100, 5, 5, 50));
        box.getChildren().addAll(l1, rb1, rb2, rb3,l2);
//      create admin menu pane
        final ToggleGroup adminToggle = new ToggleGroup();
        Label adminLabel = new Label("what do you want to do");
        RadioButton rba1 = new RadioButton("Add a property");
        RadioButton rba2 = new RadioButton("Add a tenant/Rent a Unit");
        RadioButton rba3 = new RadioButton("Display properties ");
        RadioButton rba4 = new RadioButton("Display tenants");
        RadioButton rba5 = new RadioButton("Display Potential Tenants");
        RadioButton rba6 = new RadioButton("Display rented units");
        RadioButton rba7 = new RadioButton("Display vacant units");
        RadioButton rba8 = new RadioButton("Display all leases");
        RadioButton rba9 = new RadioButton("Load historical property from list");
        RadioButton rba10 = new RadioButton("Return to main menu");
        Label adminSelect = new Label("No selection yet");
        rba1.setUserData("1");rba2.setUserData("2");rba3.setUserData("3");rba4.setUserData("4");rba5.setUserData("5");
        rba6.setUserData("6");rba7.setUserData("7");rba8.setUserData("8");rba9.setUserData("9");rba10.setUserData("10");

        rba1.setToggleGroup(adminToggle);
        rba2.setToggleGroup(adminToggle);
        rba3.setToggleGroup(adminToggle);
        rba4.setToggleGroup(adminToggle);
        rba5.setToggleGroup(adminToggle);
        rba6.setToggleGroup(adminToggle);
        rba7.setToggleGroup(adminToggle);
        rba8.setToggleGroup(adminToggle);
        rba9.setToggleGroup(adminToggle);
        rba10.setToggleGroup(adminToggle);


        //    add admin buttons to admin menu
        VBox adminBox = new VBox(25);
        adminBox.setFillWidth(false);
        adminBox.setPadding(new Insets(100, 5, 5, 250));
        adminBox.getChildren().addAll(adminLabel,rba1,rba2, rba3, rba4, rba5, rba6, rba7, rba8, rba9, rba10, adminSelect);
//    create Tenant pane
        VBox tenantBox = new VBox(25);

//        Add tenant buttons to tenant pane



        //Creating a scene object
        base.getChildren().addAll(root,box);
        Scene scene = new Scene(base, 800, 800, Color.GHOSTWHITE);
        stage.setTitle("Main Menu");

        TenantViewFX tvfx = new TenantViewFX(stage, scene);
        LeaseViewFX lvfx = new LeaseViewFX(stage, scene);
        PayRentViewFX prvfx = new PayRentViewFX(stage, scene);

        mainToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                RadioButton rb = (RadioButton)mainToggle.getSelectedToggle();
                if (rb != null) {
                    String selection = mainToggle.getSelectedToggle().getUserData().toString();
                    l2.setText(selection + " selected");
                    if (selection.equalsIgnoreCase("admin")) {
                        base.getChildren().remove(box);
                        base.getChildren().add(adminBox);
                        rb1.setSelected(false);
                    }else if (selection.equalsIgnoreCase("tenant")) {
                        base.getChildren().remove(box);
//                        Add tenant functionality
                         prvfx.PayRentView(base, box);
                        rb2.setSelected(false);
                    }else if (selection.equalsIgnoreCase("exit")) {
                        stage.close();
                        RunFx.closeProg();
                    }

                }

            }
        });

        PropertyView pv = new PropertyView();
        adminToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                RadioButton rb = (RadioButton)adminToggle.getSelectedToggle();
                if (rb != null) {
                    String selection = adminToggle.getSelectedToggle().getUserData().toString();
                    adminSelect.setText(rb.getText() + " selected");
                    switch (selection){
                        case "1":
                            base.getChildren().remove(adminBox);
                            pvfx.addProperty(base);
                            rba1.setSelected(false);
                            break;
                        case "2":
                            base.getChildren().remove(adminBox);
                            tvfx.TenantInput(base, adminBox);
                            rba2.setSelected(false);
                            break;
                        case "3":
                            base.getChildren().remove(adminBox);
                            pvfx.displayProperties();
                            rb3.setSelected(false);
                            break;
                        case "4":
                            //base.getChildren().remove(adminBox);
                            tvfx.DisplayTenants(stage, tenants, true);
                            rba4.setSelected(false);
                            break;
                        case "5":
                            //base.getChildren().remove(adminBox);
                            tvfx.DisplayTenants(stage, tenants, false);
                            rba5.setSelected(false);
                            break;
                        case "6":
                            base.getChildren().remove(adminBox);
                            pvfx.displayRentedUnits();
                            rba6.setSelected(false);
                            break;
                        case "7":
                            base.getChildren().remove(adminBox);
                            pvfx.displayVacantUnits();
                            rba7.setSelected(false);
                            break;
                        case "8":
                            //base.getChildren().remove(adminBox);
                            lvfx.DisplayAllLeases(stage, leases);
                            rba8.setSelected(false);
                            break;
                        case "9":
                           // base.getChildren().remove(adminBox);
                            pvfx.addHistoricalProperty();
                            rba9.setSelected(false);
                            break;
                        case "10":
                            base.getChildren().remove(adminBox);
                            base.getChildren().add(box);
                            rba10.setSelected(false);
                            break;
                    }

                }
            }
        });




        stage.setScene(scene);
        stage.show();


//        //    create property MVC
//        PropertyController pc = new PropertyController(pv);




//        Scene scene = new Scene(new StackPane(l), 640, 480);

    }

    public static void main(String[] args) {
        launch();
    }



}


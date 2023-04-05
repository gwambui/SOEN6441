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
import model.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;

import controller.PropertyController;
import controller.TenantController;
import javafx.stage.Stage;


public class TenantViewFX extends Tenant {

    Stage stage;
    Scene mainScene;
    public TenantViewFX(Stage stage, Scene mainScene) {
        this.stage = stage;
        this.mainScene = mainScene;
    }

    public void TenantInput(Group base, VBox adminBox)  {
        Group tenantGroup = new Group();

        this.mainScene = mainScene;

        Tenant tenant = new Tenant();
        Lease lease = new Lease();
        LeaseView lv = new LeaseView();
        PropertyView pv = new PropertyView();
        LeaseViewFX lvfx = new LeaseViewFX(stage, mainScene);

        stage.setTitle("Add a Tenant");
        Label tenantLabel = new Label("Please select a tenant type: ");

        RadioButton prb1 = new RadioButton("Current Tenant");
        RadioButton prb2 = new RadioButton("Potential Tenant");

        prb1.setUserData("Current Tenant");
        prb2.setUserData("Potential Tenant");

        final ToggleGroup tenantToggle = new ToggleGroup();
        prb1.setToggleGroup(tenantToggle);
        prb2.setToggleGroup(tenantToggle);

        HBox tenantBox = new HBox(50);
        tenantBox.setFillHeight(false);
        tenantBox.setPadding(new Insets(100, 5, 5, 50));
        tenantBox.getChildren().addAll(tenantLabel,prb1,prb2);

//        Create form to fill property info
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.setPadding(new Insets(25, 125, 25, 25));
//        grid.setPadding(new Insets(25, 25, 25, 25));
        tenantGroup.getChildren().addAll(tenantBox);
        base.getChildren().add(tenantGroup);


        prb1.setOnAction(e -> {


            GridPane grid1 = new GridPane();
            grid1.setAlignment(Pos.CENTER_LEFT);
            grid1.setHgap(10);
            grid1.setVgap(10);
            grid1.setPadding(new Insets(25, 125, 25, 25));

            isCurrentTenant = true;

            Label firstNameLabel = new Label("Tenant First Name: ");
            TextField firstNameTextField = new TextField();

            Label lastNameLabel = new Label("Tenant Last Name: ");
            TextField lastNameTextField = new TextField();

            Label emailLabel = new Label("email address: ");
            TextField emailTextField = new TextField();

            Label buildingLabel = new Label("Input the building ID the tenant is living in: ");
            TextField buildingTextField = new TextField();

            Label unitLabel = new Label("Input unit number tenant is renting:");
            TextField unitTextField = new TextField();

            // add the labels and text fields to the grid pane
            grid1.add(firstNameLabel, 0, 0);
            grid1.add(firstNameTextField, 1, 0);
            grid1.add(lastNameLabel, 0, 1);
            grid1.add(lastNameTextField, 1, 1);
            grid1.add(emailLabel, 0, 2);
            grid1.add(emailTextField, 1, 2);
            grid1.add(buildingLabel, 0, 3);
            grid1.add(buildingTextField, 1, 3);
            grid1.add(unitLabel, 0, 4);
            grid1.add(unitTextField, 1, 4);

            Button submitButton = new Button("Submit");

            grid1.add(submitButton, 1,5);

            submitButton.setOnAction(event -> {

              firstName = firstNameTextField.getText();
              lastName = lastNameTextField.getText();
              email = emailTextField.getText();
              buildingTenantID =  Integer.parseInt(buildingTextField.getText());
              apartmentNum = Integer.parseInt(unitTextField.getText());

                //Set tenant ID
                tenant.setID();
                tenantID = tenant.getTenantID();
                System.out.println("This tenants ID is: " + tenant.getTenantID());

                //Add a new tenant to the array list by calling controller
                TenantController tc = new TenantController();
                tc.addNewTenant(isCurrentTenant, tenantID, firstName, lastName, email, buildingTenantID, apartmentNum);

                base.getChildren().add(adminBox);
                base.getChildren().remove(tenantGroup);
                //Call lease input only when a current tenant is being inputted
                lvfx.LeaseInput();

            });



            // create a new scene with the grid pane
            Scene scene = new Scene(grid1, 800, 800);

            // show the new scene
            stage.setScene(scene);
            stage.show();


        });

//        Create form to fill property info

        GridPane grid2 = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);

        grid2.setPadding(new Insets(25, 125, 25, 25));
        prb2.setOnAction(e -> {

            isCurrentTenant = false;

            Label firstNameLabel = new Label("Tenant First Name: ");
            TextField firstNameTextField = new TextField();

            Label lastNameLabel = new Label("Tenant Last Name: ");
            TextField lastNameTextField = new TextField();

            Label emailLabel = new Label("email address: ");
            TextField emailTextField = new TextField();

            Label buildingLabel = new Label("Input the building ID the potential tenant is interested in: ");
            TextField buildingTextField = new TextField();

            Label unitLabel = new Label("Input unit number tenant is interested in: ");
            TextField unitTextField = new TextField();

            // add the labels and text fields to the grid pane
            grid2.add(firstNameLabel, 0, 0);
            grid2.add(firstNameTextField, 1, 0);
            grid2.add(lastNameLabel, 0, 1);
            grid2.add(lastNameTextField, 1, 1);
            grid2.add(emailLabel, 0, 2);
            grid2.add(emailTextField, 1, 2);
            grid2.add(buildingLabel, 0, 3);
            grid2.add(buildingTextField, 1, 3);
            grid2.add(unitLabel, 0, 4);
            grid2.add(unitTextField, 1, 4);

            Button submitButton = new Button("Submit");

            grid2.add(submitButton, 1,5);

            submitButton.setOnAction(event -> {
                        firstName = firstNameTextField.getText();
                        lastName = lastNameTextField.getText();
                        email = emailTextField.getText();
                        buildingTenantID = Integer.parseInt(buildingTextField.getText());
                        apartmentNum = Integer.parseInt(unitTextField.getText());

                        //Set tenant ID
                        tenant.setID();
                        tenantID = tenant.getTenantID();
                        System.out.println("This tenants ID is: " + tenant.getTenantID());

                        //Add a new tenant to the array list by calling controller
                        TenantController tc = new TenantController();
                        tc.addNewTenant(isCurrentTenant, tenantID, firstName, lastName, email, buildingTenantID, apartmentNum);

                        base.getChildren().add(adminBox);
                        base.getChildren().remove(tenantGroup);

                        stage.setScene(mainScene.getRoot().getScene());
                        stage.show();

                    });

            Scene scene = new Scene(grid2, 800, 800);

            // show the new scene
            stage.setScene(scene);
            stage.show();
        });

    }


}

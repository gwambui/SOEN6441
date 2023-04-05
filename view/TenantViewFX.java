package view;

import controller.TenantController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Lease;
import model.Tenant;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;


public class TenantViewFX extends Tenant {

    Stage stage;
    Scene mainScene;
    public TenantViewFX(Stage stage, Scene mainScene) {
        this.stage = stage;
        this.mainScene = mainScene;
    }

    public void TenantInput(Group base, VBox adminBox) {
        Group tenantGroup = new Group();
        AtomicBoolean availabilityFlag = new AtomicBoolean(false);
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

        //Clear Buttons after being selected
        tenantToggle.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            prb1.setSelected(false);
            prb2.setSelected(false);
        });

        //Add back button
        Button exitButton = new Button("Exit");

        HBox tenantBox = new HBox(50);
        tenantBox.setFillHeight(false);
        tenantBox.setPadding(new Insets(100, 5, 5, 50));
        tenantBox.getChildren().addAll(tenantLabel, prb1, prb2, exitButton);


//        Create form to fill tenant info
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.setPadding(new Insets(25, 125, 25, 25));
//        grid.setPadding(new Insets(25, 25, 25, 25));
        tenantGroup.getChildren().addAll(tenantBox);
        base.getChildren().add(tenantGroup);

        exitButton.setOnAction(e -> {
            tenantGroup.getChildren().removeAll(tenantBox);
            base.getChildren().remove(tenantGroup);
            base.getChildren().add(adminBox);
            stage.setTitle("Main Menu");

        });


        //For current tenants
        prb1.setOnAction(e -> {

            GridPane grid1 = new GridPane();
            grid1.setAlignment(Pos.CENTER_LEFT);
            grid1.setHgap(10);
            grid1.setVgap(10);
            grid1.setPadding(new Insets(25, 125, 25, 25));

            //Current tenant
            isCurrentTenant = true;

            //Print all labels and text fields

            Label currentTenantLabel = new Label("Current Tenant: " );

            currentTenantLabel.setStyle("-fx-font-weight: bold;");

            // Set the font size to 20 points
            currentTenantLabel.setStyle("-fx-font-size: 20pt;");

            Label voidLabel = new Label(" ");

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

            Button backButton = new Button("Back");

            // add the labels and text fields to the grid pane
            grid1.add(currentTenantLabel, 0,0);
            grid1.add(voidLabel, 0,1);
            grid1.add(firstNameLabel, 0, 2);
            grid1.add(firstNameTextField, 1, 2);
            grid1.add(lastNameLabel, 0, 3);
            grid1.add(lastNameTextField, 1, 3);
            grid1.add(emailLabel, 0, 4);
            grid1.add(emailTextField, 1, 4);
            grid1.add(buildingLabel, 0, 5);
            grid1.add(buildingTextField, 1, 5);
            grid1.add(unitLabel, 0, 6);
            grid1.add(unitTextField, 1, 6);
            grid1.add(backButton, 0, 7);

            Button submitButton = new Button("Submit");

            grid1.add(submitButton, 1, 7);

            backButton.setOnAction(event -> {

                stage.setScene(mainScene);
                stage.show();
            });

            submitButton.setOnAction(event -> {

                firstName = firstNameTextField.getText();
                lastName = lastNameTextField.getText();
                email = emailTextField.getText();
                buildingTenantID = Integer.parseInt(buildingTextField.getText());
                apartmentNum = Integer.parseInt(unitTextField.getText());

                //Error handling checks to ensure building ID and apartment number exist and are available before storing information
                if (!pv.checkID(buildingTenantID)) {
                    showError("The inputted building ID does not match any building ID in the system. Please try again.");
                } else if ((pv.checkCondoNo(buildingTenantID, apartmentNum) == false) && pv.isCondo(buildingTenantID) && pv.checkCondoAvailability(buildingTenantID, apartmentNum)) {
                    showError("The inputted unit number does not match any unit number for this building or is not available. Please try again.");
                } else if (pv.checkApartmentNo(buildingTenantID, apartmentNum) == false && !pv.isCondo(buildingTenantID) && pv.checkApartmentAvailability(buildingTenantID, apartmentNum)) {
                    showError("The inputted unit number does not match any unit number for this building or is not available. Please try again.");
                }

                //If all checks pass, set tenant ID, store information in model and move to lease input page
                else {
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
                }

            });


            // create a new scene with the grid pane
            Scene scene = new Scene(grid1, 800, 800);

            // show the new scene
            stage.setScene(scene);
            stage.show();


        });

        //For potential tenants



        prb2.setOnAction(e -> {
            GridPane grid2 = new GridPane();
            grid2.setAlignment(Pos.CENTER_LEFT);
            grid2.setHgap(10);
            grid2.setVgap(10);

            grid2.setPadding(new Insets(25, 125, 25, 25));

            //Potential tenant
            isCurrentTenant = false;

            Label potentialTenantLabel = new Label("Potential Tenant: " );

            potentialTenantLabel.setStyle("-fx-font-weight: bold;");

            // Set the font size to 20 points
            potentialTenantLabel.setStyle("-fx-font-size: 20pt;");

            Label voidLabel = new Label(" ");

            //Print all labels and text fields
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

            Button backButton = new Button("Back");

            // add the labels and text fields to the grid pane
            grid2.add(potentialTenantLabel, 0,0);
            grid2.add(voidLabel,0,1);
            grid2.add(firstNameLabel, 0, 2);
            grid2.add(firstNameTextField, 1, 2);
            grid2.add(lastNameLabel, 0, 3);
            grid2.add(lastNameTextField, 1, 3);
            grid2.add(emailLabel, 0, 4);
            grid2.add(emailTextField, 1, 4);
            grid2.add(buildingLabel, 0, 5);
            grid2.add(buildingTextField, 1, 5);
            grid2.add(unitLabel, 0, 6);
            grid2.add(unitTextField, 1, 6);
            grid2.add(backButton, 0, 7);

            //Add submit button
            Button submitButton = new Button("Submit");
            grid2.add(submitButton, 1, 7);

            backButton.setOnAction(event -> {
                stage.setScene(mainScene);
                stage.show();
            });


            submitButton.setOnAction(event -> {

                //Error handling to ensure user is inputting correct building ID and apartment number
                if (!pv.checkID(buildingTenantID)) {
                    showError("The inputted building ID does not match any building ID in the system. Please try again.");
                } else if (pv.checkCondoNo(buildingTenantID, apartmentNum) == false && pv.isCondo(buildingTenantID) && pv.checkCondoAvailability(buildingTenantID, apartmentNum)) {
                    showError("The inputted unit number does not match any unit number for this building or is not available. Please try again.");
                } else if (pv.checkApartmentNo(buildingTenantID, apartmentNum) == false && !pv.isCondo(buildingTenantID) && pv.checkApartmentAvailability(buildingTenantID, apartmentNum)) {
                    showError("The inputted unit number does not match any unit number for this building or is not available. Please try again.");
                }

                //If all checks pass,
                else {
                    //Assign all variables to text field inpits
                    firstName = firstNameTextField.getText();
                    lastName = lastNameTextField.getText();
                    email = emailTextField.getText();
                    buildingTenantID = Integer.parseInt(buildingTextField.getText());
                    apartmentNum = Integer.parseInt(unitTextField.getText());

                    //Set tenant ID
                    tenant.setID();
                    tenantID = tenant.getTenantID();

                    //Check if apartment or condo is available and print a message to the user
                    if (pv.isCondo(buildingTenantID)) {
                        availabilityFlag.set(pv.checkCondoAvailability(buildingTenantID, apartmentNum));
                    } else {
                        availabilityFlag.set(pv.checkApartmentAvailability(buildingTenantID, apartmentNum));
                    }

                    //Print out correct availability message
                    if (availabilityFlag.get()) {
                        showSuccessMessage("NOTE: This apartment is currently AVAILABLE");
                    } else {
                        showSuccessMessage("NOTE: This apartment is currently UNAVAILABLE");
                    }


                    //Add a new tenant to the array list by calling controller
                    TenantController tc = new TenantController();
                    tc.addNewTenant(isCurrentTenant, tenantID, firstName, lastName, email, buildingTenantID, apartmentNum);

                    //Scene handling
                    base.getChildren().add(adminBox);
                    base.getChildren().remove(tenantGroup);

                    //Set scene
                    stage.setScene(mainScene.getRoot().getScene());
                    stage.show();
                }
            });

            Scene pscene = new Scene(grid2, 800, 800);

            // show the new scene
            stage.setScene(pscene);
            stage.show();
        });

    }

    //Method for displaying current or potential tenants depending on choice (if true show current tenants, if false show potential tenants)
    public void DisplayTenants(Stage primaryStage, ArrayList<Tenant> tenantList, boolean choice) {
        int tenantNum = 1;

        if (choice) {
            stage.setTitle("Display Current Tenants");
        }

        else {
            stage.setTitle("Display Potential Tenants");
        }
        VBox container = new VBox();
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPadding(new Insets(25, 125, 25, 25));

        for (Tenant t:tenantList) {

            //Only pull from the current tenants or potential tenants depending on whether or not they are a current tenant
            if (t.isCurrentTenant == !choice) {
                continue;
            }

            Label tenantLabel = new Label("Tenant number: " + tenantNum);
            container.getChildren().add(tenantLabel);

            tenantLabel.setStyle("-fx-font-weight: bold;");

            // Set the font size to 20 points
            tenantLabel.setStyle("-fx-font-size: 20pt;");

            if (choice) {
                //Print all correct labels and text fields

                Label tenantNameLabel = new Label("The tenant name is: " + t.getFirstName() + " " + t.getLastName());
                container.getChildren().add(tenantNameLabel);
                Label tenantIDLabel = new Label("Tenant ID: " + t.getTenantID());
                container.getChildren().add(tenantIDLabel);
                Label tenantEmailLabel = new Label("The tenants email address is: " + t.getEmail());
                container.getChildren().add(tenantEmailLabel);
                Label buildingIDLabel = new Label("They live in building ID: " + t.getBuildingTenantID());
                container.getChildren().add(buildingIDLabel);
                Label apartmentNumLabel = new Label("They are renting unit number: " + t.getApartmentNum());
                container.getChildren().add(apartmentNumLabel);
                container.getChildren().add(new Label(" "));
                tenantNum++;
            }

            else {

                //Print all correct labels and text fields

                Label tenantNameLabel = new Label("The potential tenants name is: " + t.getFirstName() + " " + t.getLastName());
                container.getChildren().add(tenantNameLabel);
                Label tenantIDLabel = new Label("Tenant ID " + t.getTenantID());
                container.getChildren().add(tenantIDLabel);
                Label tenantEmailLabel = new Label("The potential tenants email address is: " + t.getEmail());
                container.getChildren().add(tenantEmailLabel);
                Label buildingIDLabel = new Label("They are interested in living in building ID: " + t.getBuildingTenantID());
                container.getChildren().add(buildingIDLabel);
                Label apartmentNumLabel = new Label("They are interested in renting unit number: " + t.getApartmentNum());
                container.getChildren().add(apartmentNumLabel);
                container.getChildren().add(new Label(" "));
                tenantNum++;
            }

        }

        //Add back button
        Button backButton = new Button("Back");
        container.getChildren().add(backButton);

        backButton.setOnAction(event -> {
            // switch back to the main menu scene
            stage.setScene(mainScene.getRoot().getScene());
            stage.setTitle("Main Menu");
            stage.show();
        });

        //Add scroll pane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(container);

        //Set scene
        Scene scene = new Scene(scrollPane, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // Method to show an error message
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //Method to show a success message
    public void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




}




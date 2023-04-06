package view;

import controller.LeaseController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Lease;

import java.util.ArrayList;

public class LeaseViewFX {

    //Initialize variables
    int leaseID;
    String leaseStartDate;
    String leaseEndDate;
    Double monthlyRentAmount;
    Stage stage;
    Scene mainScene;


    //Lease view constructor
    public LeaseViewFX(Stage stage, Scene mainScene) {
        this.stage = stage;
        this.mainScene = mainScene;
    }

    //Method to deal with lease input
    public void LeaseInput() {
        Lease lease = new Lease();

        //Set stage title
        stage.setTitle("Add a Lease");

        //Set lease ID
        lease.setID();
        leaseID = lease.getLeaseID();

        //Initialize labels, grid, HBox and Text Fields required for inputting lease information
        Label leaseLabel = new Label("Please input lease information: ");

        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER_LEFT);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 125, 25, 25));

        HBox leaseBox = new HBox(50);
        leaseBox.getChildren().addAll(leaseLabel);

        Label leaseStartDateLabel = new Label("Lease start date (DD/MM/YYYY): ");
        TextField startDateTextField = new TextField();

        Label leaseEndDateLabel = new Label("Lease end date (DD/MM/YYYY): ");
        TextField endDateTextField = new TextField();

        Label monthlyRentAmountLabel = new Label("Monthly rent amount ($): ");
        TextField rentAmountTextField = new TextField();


        // add the labels and text fields to the grid pane
        grid2.add(leaseLabel, 0,0);
        grid2.add(leaseStartDateLabel, 0, 1);
        grid2.add(startDateTextField, 1, 1);
        grid2.add(leaseEndDateLabel, 0, 2);
        grid2.add(endDateTextField, 1, 2);
        grid2.add(monthlyRentAmountLabel, 0, 3);
        grid2.add(rentAmountTextField, 1, 3);

        Button submitButton = new Button("Submit");

        grid2.add(submitButton, 1,5);

        //Submit button listener
        submitButton.setOnAction(e -> {

            //Store information in correct variables
            leaseStartDate = startDateTextField.getText();
            leaseEndDate = endDateTextField.getText();
            monthlyRentAmount = Double.parseDouble(rentAmountTextField.getText());

            //Initialize lease controller, pass information to controller to handle business logic
            LeaseController lc = new LeaseController();
            lc.addNewLease(leaseID, leaseStartDate, leaseEndDate, monthlyRentAmount, false);

            //new scene to reset actions
            stage.setScene(mainScene.getRoot().getScene());
            stage.show();
            stage.setTitle("Main Menu");

        });

        // create a new scene with the grid pane
        Scene scene = new Scene(grid2, 800, 800);

        // show the new scene
        stage.setScene(scene);
        stage.show();


    }

    //Method to display all leases
    public void DisplayAllLeases(Stage primaryStage, ArrayList<Lease> leaseList) {
        int leaseNum = 1;  //initialize lease num to be 1

        //Create VBox container and stage parameters
        stage.setTitle("Display All Leases");
        VBox container = new VBox();
        container.setSpacing(10);

        container.setAlignment(Pos.CENTER_LEFT);
        container.setPadding(new Insets(25, 125, 25, 25));

        //Loop through lease list to retrieve correct information and print them to GUI screen
        for (Lease l:leaseList) {

            //Main label
            Label leaseLabel = new Label("Lease number: " + leaseNum);
            container.getChildren().add(leaseLabel);

            //Bold title
            leaseLabel.setStyle("-fx-font-weight: bold;");

            // Set the font size to 20 points
            leaseLabel.setStyle("-fx-font-size: 20pt;");

            //Print out lease information
            Label leaseIDLabel = new Label("The lease ID is: " + l.getLeaseID());
            container.getChildren().add(leaseIDLabel);
            Label leaseStartDateLabel = new Label("The lease start date is: " + l.getLeaseStartDate());
            container.getChildren().add(leaseStartDateLabel);
            Label leaseEndDateLabel = new Label("The lease start date is: " + l.getLeaseEndDate());
            container.getChildren().add(leaseEndDateLabel);
            Label amountLabel = new Label("The monthly rent amount is: " + l.getMonthlyRentAmount());
            container.getChildren().add(amountLabel);
            Label hasPaidRentLabel = new Label("The monthly rent has been paid for this lease (true/false): " + l.getHasPaidRent());
            container.getChildren().add(hasPaidRentLabel);
            container.getChildren().add(new Label(" "));

            leaseNum ++;

        }

        //Create back button
        Button backButton = new Button("Back");
        container.getChildren().add(backButton);

        backButton.setOnAction(event -> {
            // switch back to the main menu scene
            stage.setScene(mainScene.getRoot().getScene());
            stage.setTitle("Main Menu");
            stage.show();
        });

        //Create scroll pane and wrap container with it
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(container);

        //Set up scene and show stage
        Scene scene = new Scene(scrollPane, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        }

    }

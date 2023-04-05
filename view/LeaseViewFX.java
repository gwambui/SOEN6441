package view;
import controller.LeaseController;
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

import javax.swing.*;

import static controller.LeaseController.leases;
import static controller.TenantController.tenants;

public class LeaseViewFX {
    Lease lease ;
    int leaseID;
    String leaseStartDate;
    String leaseEndDate;
    Double monthlyRentAmount;
    Stage stage;
    Scene mainScene;



    public LeaseViewFX(Stage stage, Scene mainScene) {
        this.stage = stage;
        this.mainScene = mainScene;
    }

    public void LeaseInput() {
        Lease lease = new Lease();

        stage.setTitle("Add a Lease");
        lease.setID();
        leaseID = lease.getLeaseID();

        //Display all prompts for lease input
        System.out.println("The lease ID associated with this tenant is: " + leaseID);

        Label leaseLabel = new Label("Please input lease information: ");

        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER_LEFT);
        grid2.setHgap(10);
        grid2.setVgap(10);

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

        submitButton.setOnAction(e -> {
            leaseStartDate = startDateTextField.getText();
            leaseEndDate = endDateTextField.getText();
            monthlyRentAmount = Double.parseDouble(rentAmountTextField.getText());

            LeaseController lc = new LeaseController();
            lc.addNewLease(leaseID, leaseStartDate, leaseEndDate, monthlyRentAmount, false);

            //new scene to reset actions

            stage.setScene(mainScene.getRoot().getScene());
            stage.show();

        });

        // create a new scene with the grid pane
        Scene scene = new Scene(grid2, 800, 800);

        // show the new scene
        stage.setScene(scene);
        stage.show();


    }

    public void DisplayAllLeases(Stage primaryStage, ArrayList<Lease> leaseList) {
        int leaseNum = 1;

        stage.setTitle("Display All Leases");
        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setSpacing(10);


        for (Lease l:leaseList) {
            Label leaseLabel = new Label("Lease number: " + leaseNum);
            container.getChildren().add(leaseLabel);

            leaseLabel.setStyle("-fx-font-weight: bold;");

            // Set the font size to 20 points
            leaseLabel.setStyle("-fx-font-size: 20pt;");


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

        Button backButton = new Button("Back");
        container.getChildren().add(backButton);

        backButton.setOnAction(event -> {
            // switch back to the main menu scene
            stage.setScene(mainScene.getRoot().getScene());
            stage.setTitle("Main Menu");
            stage.show();
        });

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(container);

        Scene scene = new Scene(scrollPane, 500, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        }

    }

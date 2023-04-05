package view;

import controller.LeaseController;
import controller.TenantController;
import model.Tenant;

import static controller.LeaseController.leases;
import static controller.TenantController.tenants;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PayRentViewFX {
    Stage stage;
    Scene mainScene;
    public PayRentViewFX(Stage stage, Scene mainScene) {
        this.stage = stage;
        this.mainScene = mainScene;
    }
    public void PayRentView(Group base, VBox adminBox) {
        Tenant tenant = new Tenant();
        TenantController tc = new TenantController();
        LeaseController lc = new LeaseController();

        AtomicInteger inputtedID = new AtomicInteger();
        AtomicReference<Double> rentAmount = new AtomicReference<>((double) 0);
        AtomicReference<Double> amountPaying = new AtomicReference<>((double) 0);
        AtomicBoolean result = new AtomicBoolean(false);

        stage.setTitle("Tenant Login");

        // Create a grid pane for the login form
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add a title to the form
        Label title = new Label("Login");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(title, 0, 0, 2, 1);

        // Add a text field for the username
        Label usernameLabel = new Label("Tenant ID:");
        grid.add(usernameLabel, 0, 1);
        TextField usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 1);



        // Add a submit button
        Button submitButton = new Button("Submit");
        HBox submitBox = new HBox(10);
        submitBox.setAlignment(Pos.BOTTOM_RIGHT);
        submitBox.getChildren().add(submitButton);
        grid.add(submitBox, 1, 4);

        // Add a cancel button
        Button cancelButton = new Button("Cancel");
        HBox cancelBox = new HBox(10);
        cancelBox.setAlignment(Pos.BOTTOM_LEFT);
        cancelBox.getChildren().add(cancelButton);
        grid.add(cancelBox, 0, 4);



        cancelButton.setOnAction(e -> {
          stage.setScene(mainScene);
          stage.show();
          stage.setTitle("Main Menu");
          base.getChildren().add(adminBox);

        });

        submitButton.setOnAction(e -> {

            inputtedID.set(Integer.parseInt(usernameTextField.getText()));

            if (!tc.checkTenantID(tenants, inputtedID.get())) {
                showError("The Tenant ID does not exist, please try again.");
            }

            if (tc.checkTenantID(tenants, inputtedID.get()) && lc.checkIfPaid(leases, tenants, inputtedID.get())) {
                showError("The rent has already been paid for this tenant for this month.");
            }

            if (tc.checkTenantID(tenants, inputtedID.get()) && !lc.checkIfPaid(leases, tenants, inputtedID.get())) {
                // Create a grid pane for the login form
                GridPane grid1 = new GridPane();
                grid1.setAlignment(Pos.CENTER);
                grid1.setHgap(10);
                grid1.setVgap(10);
                grid1.setPadding(new Insets(25, 25, 25, 25));

                stage.setTitle("Pay Rent");

                // Add a title to the form
                Label title1 = new Label("Pay Rent");
                title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                grid1.add(title1, 0, 0, 2, 1);

                rentAmount.set(lc.getRentAmount(leases, tenants, inputtedID.get()));

                Label amountLabel = new Label("The amount of rent owing is: $" + rentAmount );
                grid1.add(amountLabel, 0, 1);

                Label messagePaid = new Label("Please input the amount ($) you are paying (must be paid in full): ");
                grid1.add(messagePaid, 0, 2);

                TextField amountPaidText = new TextField();
                grid1.add(amountPaidText, 1, 2);

                Button submitButton2 = new Button("Submit");
                HBox submitBox2 = new HBox(10);
                submitBox2.setAlignment(Pos.BOTTOM_RIGHT);
                submitBox2.getChildren().add(submitButton2);
                grid1.add(submitBox2, 1, 4);

                Scene paidScene = new Scene(grid1, 800, 800);
                stage.setScene(paidScene);
                stage.show();

                submitButton2.setOnAction(event-> {
                    amountPaying.set(Double.parseDouble(amountPaidText.getText()));

                    result.set(lc.payRent(leases, inputtedID.get(), tenants, rentAmount.get(), amountPaying.get()));

                    if (result.get()) {
                        showSuccessMessage("You have successfully paid your rent!");
                        stage.setScene(mainScene);
                        stage.show();
                        stage.setTitle("Main Menu");
                        base.getChildren().add(adminBox);
                    } else {
                        showError("The inputted rent amount does not match the actual rent amount. Please input the correct rent amount to pay your rent.");
                    }
                });

            }
        });


        Scene scene = new Scene(grid, 800, 800);
        stage.setScene(scene);
        stage.show();

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

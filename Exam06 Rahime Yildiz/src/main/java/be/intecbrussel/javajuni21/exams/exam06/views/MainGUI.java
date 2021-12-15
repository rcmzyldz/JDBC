package be.intecbrussel.javajuni21.exams.exam06.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainGUI extends Application {

    final HBox hBox = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new Group());
        stage.setTitle("Main View");
        stage.setWidth(750);
        stage.setHeight(600);

        final Label header = new Label("Main Menu");
        header.setFont(new Font("Arial", 14));

        final Label message = new Label("Status: loaded.");
        header.setFont(new Font("Arial", 10));

        final Button categoryRoute = new Button("Category");
        categoryRoute.setOnAction(onClick -> {
            new CategoryGUI().start(stage);
        });

        final Button brewerRoute = new Button("Brewer");
        brewerRoute.setOnAction(onClick -> {
            new BrewerGUI().start(stage);
        });

        final Button beerRoute = new Button("Beer");
        beerRoute.setOnAction(onClick -> {
            new BeerGUI().start(stage);
        });

        final Button customerRoute = new Button("Customer");
        customerRoute.setOnAction(onClick -> {
            new CustomerGUI().start(stage);
        });

        hBox.getChildren().addAll(categoryRoute, brewerRoute, beerRoute, customerRoute);
        hBox.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(header, hBox, message);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);

        stage.show();
    }

}

package be.intecbrussel.javajuni21.exams.exam06.views;

import be.intecbrussel.javajuni21.exams.exam06.exceptions.BrewerException;
import be.intecbrussel.javajuni21.exams.exam06.models.entities.Brewer;
import be.intecbrussel.javajuni21.exams.exam06.repositories.BrewerRepository;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class BrewerGUI extends Application {

    private final TableView<BrewerModel> table = new TableView<>();
    private final ObservableList<BrewerModel> data = FXCollections.observableArrayList();
    private final BrewerRepository brewerRepository = new BrewerRepository();
    final HBox actBox = new HBox();
    final HBox navBox = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new Group());
        stage.setTitle("Brewer View");
        stage.setWidth(750);
        stage.setHeight(600);

        final Label header = new Label("Brewer Records");
        header.setFont(new Font("Arial", 14));

        final Label message = new Label("Status messages");
        header.setFont(new Font("Arial", 10));

        table.setEditable(true);

        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(30);
        idCol.setCellValueFactory(
                new PropertyValueFactory<BrewerModel, Integer>("id"));
        idCol.setEditable(false);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<BrewerModel, String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit((EventHandler<CellEditEvent<BrewerModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO

                    // UPDATE TABLE COLUMN

                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setName(t.getNewValue());
                }
        );


        TableColumn addressCol = new TableColumn("Address");
        addressCol.setMinWidth(150);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<BrewerModel, String>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit((EventHandler<CellEditEvent<BrewerModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO

                    // UPDATE TABLE COLUMN
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setAddress(t.getNewValue());
                }
        );

        TableColumn postcodeCol = new TableColumn("Postcode");
        postcodeCol.setMinWidth(80);
        postcodeCol.setCellValueFactory(
                new PropertyValueFactory<BrewerModel, String>("postcode"));
        postcodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        postcodeCol.setOnEditCommit((EventHandler<CellEditEvent<BrewerModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO

                    // UPDATE TABLE COLUMN
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setPostcode(t.getNewValue());
                }
        );

        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(200);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<BrewerModel, String>("city"));
        cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cityCol.setOnEditCommit((EventHandler<CellEditEvent<BrewerModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO
                    Integer brewerId = t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId();

                    // TODO: continue here ..

                    // UPDATE TABLE COLUMN
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setCity(t.getNewValue());
                }
        );

        // table.setItems(data);
        table.getColumns().addAll(idCol, nameCol, addressCol, postcodeCol, cityCol);

        final TextField addName = new TextField();
        addName.setPromptText("Name");
        addName.setMaxWidth(nameCol.getPrefWidth());

        final TextField addAddress = new TextField();
        addAddress.setMaxWidth(addressCol.getPrefWidth());
        addAddress.setPromptText("Address");

        final TextField addPostcode = new TextField();
        addPostcode.setMaxWidth(postcodeCol.getPrefWidth());
        addPostcode.setPromptText("Postcode");

        final TextField addCity = new TextField();
        addCity.setMaxWidth(cityCol.getPrefWidth());
        addCity.setPromptText("City");

        final Button addButton = new Button("Add");
        addButton.setOnAction(onClick -> {

            // SAVE NEW RECORD TO DB
            try {
                int noOfBrewersCreated = brewerRepository.create(
                        new Brewer()
                                .withName(addName.getText())
                                .withAddress(addAddress.getText())
                                .withPostcode(addPostcode.getText())
                                .withCity(addCity.getText())
                                .withTurnover(0)
                );

                if (noOfBrewersCreated > 0) {
                    // ADD ROW TO LIST VIEW
                    data.add(new BrewerModel(
                            0,
                            addName.getText(),
                            addAddress.getText(),
                            addPostcode.getText(),
                            addCity.getText()));
                    addName.clear();
                    addAddress.clear();
                    addPostcode.clear();
                    addCity.clear();

                    message.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                    message.setText("Brewer created!");

                } else {
                    message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                    message.setText("ERROR: Brewer could NOT be created!");
                }
            } catch (BrewerException brewerException) {
                message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                message.setText(brewerException.getMessage());
            }

        });

        final Button viewButton = new Button("View");
        viewButton.setOnAction(onClick -> {

            // CLEAR TABLE
            table.getItems().clear();

            // MAP DB RECORDS TO VIEW COMPONENTS
            List<Brewer> brewerList = brewerRepository.read();
            for (Brewer brewer : brewerList) {
                data.add(new BrewerModel(
                        brewer.getId(),
                        brewer.getName(), brewer.getAddress(),
                        brewer.getPostcode(), brewer.getCity()));
            }

            table.setItems(data);

            message.setText("Brewer records are re-loaded from DB");

        });


        final Button backButton = new Button("Back");
        backButton.setOnAction(onClick -> {
            scene.getWindow().hide();
            new MainGUI().start(stage);
        });

        navBox.getChildren().addAll(backButton);
        navBox.setSpacing(3);

        actBox.getChildren().addAll(addName, addAddress, addPostcode, addCity, addButton, viewButton);
        actBox.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(header, table, actBox, navBox, message);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static class BrewerModel {

        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty address;
        private final SimpleStringProperty postcode;
        private final SimpleStringProperty city;

        private BrewerModel(Integer id, String name, String address, String postcode, String city) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.address = new SimpleStringProperty(address);
            this.postcode = new SimpleStringProperty(postcode);
            this.city = new SimpleStringProperty(city);
        }

        public Integer getId() {
            return id.get();
        }

        public void setId(Integer brewerId) {
            id.set(brewerId);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String brewerName) {
            name.set(brewerName);
        }

        public String getAddress() {
            return address.get();
        }

        public void setAddress(String brewerAddress) {
            address.set(brewerAddress);
        }

        public String getPostcode() {
            return postcode.get();
        }

        public void setPostcode(String brewerPostCode) {
            postcode.set(brewerPostCode);
        }

        public String getCity() {
            return city.get();
        }

        public void setCity(String brewerCity) {
            city.set(brewerCity);
        }
    }
}
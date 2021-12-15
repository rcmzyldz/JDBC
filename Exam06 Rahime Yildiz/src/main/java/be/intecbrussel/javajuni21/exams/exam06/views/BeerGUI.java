package be.intecbrussel.javajuni21.exams.exam06.views;

import be.intecbrussel.javajuni21.exams.exam06.models.entities.Beer;
import be.intecbrussel.javajuni21.exams.exam06.repositories.BeerRepository;
import javafx.application.Application;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class BeerGUI extends Application {

    private final TableView<BeerModel> table = new TableView<>();
    private final static BeerRepository beerRepository = new BeerRepository();
    private final ObservableList<BeerModel> data = FXCollections.observableArrayList();

    final HBox navBox = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Beers View");
        stage.setWidth(450);
        stage.setHeight(500);

        final Label label = new Label("Beer Records in the DB");
        label.setFont(new Font("Arial", 14));

        table.setEditable(true);

        TableColumn idCol = new TableColumn("Id");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<BeerModel, Integer>("id"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<BeerModel, String>("name"));

        TableColumn priceCol = new TableColumn("Price");
        priceCol.setMinWidth(200);
        priceCol.setCellValueFactory(
                new PropertyValueFactory<BeerModel, Float>("price"));

        TableColumn stockCol = new TableColumn("Stock");
        stockCol.setMinWidth(50);
        stockCol.setCellValueFactory(
                new PropertyValueFactory<BeerModel, Integer>("stock"));

        // MAP DATABASE_RECORDS TO VIEW_COMPONENT
        List<Beer> records = beerRepository.read();
        for (Beer record : records) {
            data.add(new BeerModel(record.getId(), record.getName(), record.getPrice(), record.getStock()));
        }

        table.setItems(data);
        table.getColumns().addAll(idCol, nameCol, priceCol, stockCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, navBox);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static class BeerModel {

        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleFloatProperty price;
        private final SimpleIntegerProperty stock;

        public BeerModel(Integer id, String name, Float price, Integer stock) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleFloatProperty(price);
            this.stock = new SimpleIntegerProperty(stock);
        }

        public int getId() {
            return id.get();
        }

        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public void setId(int id) {
            this.id.set(id);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public float getPrice() {
            return price.get();
        }

        public SimpleFloatProperty priceProperty() {
            return price;
        }

        public void setPrice(float price) {
            this.price.set(price);
        }

        public int getStock() {
            return stock.get();
        }

        public SimpleIntegerProperty stockProperty() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock.set(stock);
        }
    }
}
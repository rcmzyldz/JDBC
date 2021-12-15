package be.intecbrussel.javajuni21.exams.exam06.views;

import be.intecbrussel.javajuni21.exams.exam06.exceptions.CategoryException;
import be.intecbrussel.javajuni21.exams.exam06.models.entities.Category;
import be.intecbrussel.javajuni21.exams.exam06.repositories.CategoryRepository;
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

public class CategoryGUI extends Application {

    private final TableView<CategoryModel> table = new TableView<>();
    private final ObservableList<CategoryModel> data = FXCollections.observableArrayList();
    private final CategoryRepository categoryRepository = new CategoryRepository();
    final HBox actBox = new HBox();
    final HBox navBox = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new Group());
        stage.setTitle("Category View");
        stage.setWidth(750);
        stage.setHeight(600);

        final Label header = new Label("Category Records");
        header.setFont(new Font("Arial", 14));

        final Label message = new Label("Status messages");
        header.setFont(new Font("Arial", 10));

        table.setEditable(true);

        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(30);
        idCol.setCellValueFactory(
                new PropertyValueFactory<CategoryModel, Integer>("id"));
        idCol.setEditable(false);

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(200);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<CategoryModel, String>("title"));
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        titleCol.setOnEditCommit((EventHandler<CellEditEvent<CategoryModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO

                    // UPDATE TABLE COLUMN

                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setTitle(t.getNewValue());
                }
        );


        TableColumn slugCol = new TableColumn("Address");
        slugCol.setMinWidth(150);
        slugCol.setCellValueFactory(
                new PropertyValueFactory<CategoryModel, String>("address"));
        slugCol.setCellFactory(TextFieldTableCell.forTableColumn());
        slugCol.setOnEditCommit((EventHandler<CellEditEvent<CategoryModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO

                    // UPDATE TABLE COLUMN
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setSlug(t.getNewValue());
                }
        );

        // table.setItems(data);
        table.getColumns().addAll(idCol, titleCol, slugCol);

        final TextField addTitle = new TextField();
        addTitle.setPromptText("Name");
        addTitle.setMaxWidth(titleCol.getPrefWidth());

        final TextField addSlug = new TextField();
        addSlug.setMaxWidth(slugCol.getPrefWidth());
        addSlug.setPromptText("Slug");

        final Button addButton = new Button("Add");
        addButton.setOnAction(onClick -> {

            // SAVE NEW RECORD TO DB
            try {
                int noOfCategorysCreated = categoryRepository.create(
                        new Category()
                                .withTitle(addTitle.getText())
                                .withSlug(addSlug.getText())
                );

                if (noOfCategorysCreated > 0) {
                    // ADD ROW TO LIST VIEW
                    data.add(new CategoryModel(
                            0,
                            addTitle.getText(),
                            addSlug.getText()));
                    addTitle.clear();
                    addSlug.clear();

                    message.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                    message.setText("Category created!");

                } else {
                    message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                    message.setText("ERROR: Category could NOT be created!");
                }
            } catch (CategoryException categoryException) {
                message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                message.setText(categoryException.getMessage());
            }

        });

        final Button viewButton = new Button("View");
        viewButton.setOnAction(onClick -> {

            // CLEAR TABLE
            table.getItems().clear();

            // MAP DB RECORDS TO VIEW COMPONENTS
            List<Category> categoryList = categoryRepository.read();
            for (Category category : categoryList) {
                data.add(new CategoryModel(
                        category.getId(),
                        category.getTitle(),
                        category.getSlug()));
            }

            table.setItems(data);

            message.setText("Category records are re-loaded from DB");

        });

        final Button backButton = new Button("Back");
        backButton.setOnAction(onClick -> {
            scene.getWindow().hide();
            new MainGUI().start(stage);
        });

        navBox.getChildren().addAll(backButton);
        navBox.setSpacing(3);

        actBox.getChildren().addAll(addTitle, addSlug, addButton, viewButton);
        actBox.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(header, table, actBox, navBox, message);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);

        stage.show();
    }


    public static class CategoryModel {

        private final SimpleIntegerProperty id;
        private final SimpleStringProperty title;
        private final SimpleStringProperty slug;

        private CategoryModel(Integer id, String title, String slug) {
            this.id = new SimpleIntegerProperty(id);
            this.title = new SimpleStringProperty(title);
            this.slug = new SimpleStringProperty(slug);
        }

        public Integer getId() {
            return id.get();
        }

        public void setId(Integer categoryId) {
            id.set(categoryId);
        }

        public String getTitle() {
            return title.get();
        }

        public void setTitle(String categoryName) {
            title.set(categoryName);
        }

        public String getSlug() {
            return slug.get();
        }

        public void setSlug(String categoryAddress) {
            slug.set(categoryAddress);
        }
    }
}

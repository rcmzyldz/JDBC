package be.intecbrussel.javajuni21.exams.exam06.views;

import be.intecbrussel.javajuni21.exams.exam06.exceptions.CustomerException;
import be.intecbrussel.javajuni21.exams.exam06.models.entities.Customer;
import be.intecbrussel.javajuni21.exams.exam06.repositories.CustomerRepository;
import com.github.javafaker.Faker;
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

import java.sql.Date;
import java.time.Instant;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;

public class CustomerGUI extends Application {

    private final TableView<CustomerModel> table = new TableView<>();
    private final ObservableList<CustomerModel> data = FXCollections.observableArrayList();
    private final CustomerRepository customerRepository = new CustomerRepository();
    final HBox actBox = new HBox();
    final HBox navBox = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new Group());
        stage.setTitle("Customer View");
        stage.setWidth(660);
        stage.setHeight(720);

        final Label header = new Label("Customer Records");
        header.setFont(new Font("Arial", 14));

        final Label message = new Label("Status messages");
        header.setFont(new Font("Arial", 10));

        table.setEditable(true);

        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(40);
        idCol.setCellValueFactory(
                new PropertyValueFactory<CustomerModel, Integer>("id"));
        idCol.setEditable(false);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<CustomerModel, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit((EventHandler<CellEditEvent<CustomerModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO

                    // UPDATE TABLE COLUMN

                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setFirstName(t.getNewValue());
                }
        );


        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<CustomerModel, String>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit((EventHandler<CellEditEvent<CustomerModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO

                    // UPDATE TABLE COLUMN
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setLastName(t.getNewValue());
                }
        );

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<CustomerModel, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit((EventHandler<CellEditEvent<CustomerModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO

                    // UPDATE TABLE COLUMN
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setEmail(t.getNewValue());
                }
        );

        TableColumn passcodeCol = new TableColumn("Pass Code");
        passcodeCol.setMinWidth(200);
        passcodeCol.setCellValueFactory(
                new PropertyValueFactory<CustomerModel, String>("passcode"));
        passcodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passcodeCol.setOnEditCommit((EventHandler<CellEditEvent<CustomerModel, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO
                    Integer customerId = t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).getId();

                    // TODO: continue here ..

                    // UPDATE TABLE COLUMN
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setPasscode(t.getNewValue());
                }
        );

        // table.setItems(data);
        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, emailCol, passcodeCol);

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());

        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");

        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        final TextField addPasscode = new TextField();
        addPasscode.setMaxWidth(passcodeCol.getPrefWidth());
        addPasscode.setPromptText("Passcode");

        final Button addButton = new Button("Add");
        Date registryDate = new Date(Instant.now().getEpochSecond());
        addButton.setOnAction(onClick -> {

            // SAVE NEW RECORD TO DB
            try {
                int noOfCustomersCreated = customerRepository.create(
                        new Customer()
                                .withFirstName(addFirstName.getText())
                                .withLastName(addLastName.getText())
                                .withEmail(addEmail.getText())
                                .withPasscode(addPasscode.getText())
                                .withActive(1)
                                .withRegistryDate(registryDate)
                );

                if (noOfCustomersCreated > 0) {
                    // ADD ROW TO LIST VIEW
                    data.add(new CustomerModel(
                            0,
                            addFirstName.getText(),
                            addLastName.getText(),
                            addEmail.getText(),
                            addPasscode.getText()));
                    addFirstName.clear();
                    addLastName.clear();
                    addEmail.clear();
                    addPasscode.clear();

                    message.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                    message.setText("Customer created!");

                } else {
                    message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                    message.setText("ERROR: Customer could NOT be created!");
                }
            } catch (CustomerException customerException) {
                message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                message.setText(customerException.getMessage());
            }

        });

        final Button viewButton = new Button("View");
        viewButton.setOnAction(onClick -> {

            // CLEAR TABLE
            table.getItems().clear();

            // MAP DB RECORDS TO VIEW COMPONENTS
            List<Customer> customerList = customerRepository.read();
            for (Customer customer : customerList) {
                data.add(new CustomerModel(
                        customer.getId(),
                        customer.getFirstName(), customer.getLastName(),
                        customer.getEmail(), customer.getPasscode()));
            }

            table.setItems(data);

            message.setText("Customer records are re-loaded from DB");

        });

        final TextField quantityField = new TextField("Quantity");
        quantityField.setMaxWidth(50);
        final Button generateButton = new Button("Generate");
        addButton.setOnAction(onClick -> {

            // SAVE NEW RECORD TO DB

            final Faker faker = new Faker(Locale.forLanguageTag("BE"));

            try {

                int qty = Integer.parseInt(quantityField.getText().trim());
                if (qty > 0) {
                    for (int randomIndex = 0; randomIndex < qty; randomIndex++) {
                        String firstName = faker.name().firstName();
                        String lastName = faker.name().lastName();
                        String email = faker.internet().emailAddress();
                        String passcode = faker.internet().password(8, 12);
                        int noOfCustomersCreated = customerRepository.create(
                                new Customer()
                                        .withFirstName(firstName)
                                        .withLastName(lastName)
                                        .withEmail(email)
                                        .withPasscode(passcode)
                                        .withActive(1)
                                        .withRegistryDate(registryDate)
                        );

                        if (noOfCustomersCreated > 0) {
                            // ADD ROW TO LIST VIEW
                            data.add(new CustomerModel(0, firstName, lastName, email, passcode));

                            addFirstName.clear();
                            addLastName.clear();
                            addEmail.clear();
                            addPasscode.clear();

                            message.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                            message.setText(message.getText() + "Customer created!");

                        } else {
                            message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                            message.setText("ERROR: Customer could NOT be created!");
                        }

                    }
                }


            } catch (CustomerException | InputMismatchException exception) {
                message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                message.setText(exception.getMessage());
            }

        });

        final Button backButton = new Button("Back");
        backButton.setOnAction(onClick -> {
            scene.getWindow().hide();
            new MainGUI().start(stage);
        });

        navBox.getChildren().addAll(backButton);
        navBox.setSpacing(3);

        actBox.getChildren().addAll(addFirstName, addLastName, addEmail, addPasscode,
                addButton, viewButton, quantityField, generateButton);
        actBox.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(header, table, actBox, navBox, message);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static class CustomerModel {

        private final SimpleIntegerProperty id;
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
        private final SimpleStringProperty passcode;

        private CustomerModel(Integer id, String fName, String lName, String email, String passcode) {
            this.id = new SimpleIntegerProperty(id);
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            this.passcode = new SimpleStringProperty(passcode);
        }

        public Integer getId() {
            return id.get();
        }

        public void setId(Integer customerId) {
            id.set(customerId);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String lname) {
            lastName.set(lname);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String mail) {
            email.set(mail);
        }

        public String getPasscode() {
            return passcode.get();
        }

        public void setPasscode(String code) {
            passcode.set(code);
        }
    }
}
/*package com.hotel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    // 🏨 Rooms Tab Inputs
    @FXML private TextField roomNumberField;
    @FXML private ComboBox<String> roomTypeBox;
    @FXML private TextField priceField;

    // 🏨 TableView + Columns
    @FXML private TableView<Room> roomTable;
    @FXML private TableColumn<Room, String> roomNoCol;
    @FXML private TableColumn<Room, String> typeCol;
    @FXML private TableColumn<Room, Double> priceCol;
    @FXML private TableColumn<Room, String> statusCol;

    // Room Button
    @FXML private Button addRoomBtn;

    // 👤 CUSTOMER SECTION (✅ STEP 1 ADDED)

    @FXML private TextField customerNameField;
    @FXML private TextField contactField;
    @FXML private ComboBox<String> customerRoomBox;

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> contactCol;
    @FXML private TableColumn<Customer, String> roomCol;

    @FXML private Button addCustomerBtn;

    // 📖 Booking Section
@FXML private ComboBox<String> bookingCustomerBox;
@FXML private ComboBox<String> bookingRoomBox;

@FXML private Button bookBtn;
@FXML private Button checkoutBtn;

    // 📖 Status Label
    @FXML private Label statusLabel;

    // Data storage
    private ObservableList<Room> roomList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    // 🔹 Initialize method (auto runs)
    @FXML
    public void initialize() {

        // Room ComboBox values
        roomTypeBox.getItems().addAll("Single", "Double", "Deluxe");

        // 🏨 Room Table mapping
        roomNoCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRoomNo()));

        typeCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getType()));

        priceCol.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getPrice()));

        statusCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getStatus()));

        roomTable.setItems(roomList);

        // 👤 Customer Table mapping
        nameCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        contactCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getContact()));

        roomCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRoomNo()));

        customerTable.setItems(customerList);

        // Default message
        statusLabel.setText("Status: Ready");
    }

    // 🔹 Add Room Function
    @FXML
    private void addRoom() {

        String roomNo = roomNumberField.getText();
        String type = roomTypeBox.getValue();
        String priceText = priceField.getText();

        if (roomNo.isEmpty() || type == null || priceText.isEmpty()) {
            statusLabel.setText("Please fill all fields!");
            return;
        }

        double price;

        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price!");
            return;
        }

        Room room = new Room(roomNo, type, price, "Available");
        roomList.add(room);

        // 👉 Add room to customer dropdown also
        customerRoomBox.getItems().add(roomNo);

        statusLabel.setText("Room added successfully!");

        roomNumberField.clear();
        roomTypeBox.setValue(null);
        priceField.clear();
    }

    // 🔹 Add Customer Function (STEP 5)
    @FXML
    private void addCustomer() {

        String name = customerNameField.getText();
        String contact = contactField.getText();
        String roomNo = customerRoomBox.getValue();

        if (name.isEmpty() || contact.isEmpty() || roomNo == null) {
            statusLabel.setText("Fill all customer fields!");
            return;
        }

        Customer customer = new Customer(name, contact, roomNo);
        customerList.add(customer);

        statusLabel.setText("Customer added!");

        customerNameField.clear();
        contactField.clear();
        customerRoomBox.setValue(null);
    }
}*/


/*package com.hotel;

import java.io.FileWriter;
import java.io.IOException;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    // 🏨 Rooms Tab Inputs
    @FXML private TextField roomNumberField;
    @FXML private ComboBox<String> roomTypeBox;
    @FXML private TextField priceField;

    // 🏨 TableView + Columns
    @FXML private TableView<Room> roomTable;
    @FXML private TableColumn<Room, String> roomNoCol;
    @FXML private TableColumn<Room, String> typeCol;
    @FXML private TableColumn<Room, Double> priceCol;
    @FXML private TableColumn<Room, String> statusCol;

    @FXML private Button addRoomBtn;

    // 👤 CUSTOMER SECTION
    @FXML private TextField customerNameField;
    @FXML private TextField contactField;
    @FXML private ComboBox<String> customerRoomBox;

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> contactCol;
    @FXML private TableColumn<Customer, String> roomCol;

    @FXML private Button addCustomerBtn;

    // 📖 BOOKING SECTION
    @FXML private ComboBox<String> bookingCustomerBox;
    @FXML private ComboBox<String> bookingRoomBox;
    @FXML private TextField daysField;

    @FXML private Button bookBtn;
    @FXML private Button checkoutBtn;

    // 📖 Status Label
    @FXML private Label statusLabel;

    // 📊 Data storage
    private ObservableList<Room> roomList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    // 🔹 Initialize
    @FXML
    public void initialize() {

        // Room types
        roomTypeBox.getItems().addAll("Single", "Double", "Deluxe");

        // 🏨 Room Table
        roomNoCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRoomNo()));

        typeCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getType()));

        priceCol.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getPrice()));

        statusCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getStatus()));

        roomTable.setItems(roomList);

        // 👤 Customer Table
        nameCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        contactCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getContact()));

        roomCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRoomNo()));

        customerTable.setItems(customerList);

        statusLabel.setText("Status: Ready");
    }

    // 🔹 ADD ROOM
    @FXML
    private void addRoom() {

        String roomNo = roomNumberField.getText();
        String type = roomTypeBox.getValue();
        String priceText = priceField.getText();

        if (roomNo.isEmpty() || type == null || priceText.isEmpty()) {
            statusLabel.setText("Please fill all fields!");
            return;
        }

        double price;

        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price!");
            return;
        }

        Room room = new Room(roomNo, type, price, "Available");
        roomList.add(room);

        // 👉 Update dropdowns
        customerRoomBox.getItems().add(roomNo);
        bookingRoomBox.getItems().add(roomNo);

        statusLabel.setText("Room added successfully!");

        roomNumberField.clear();
        roomTypeBox.setValue(null);
        priceField.clear();

        saveRoomsToFile();
    }


    private void saveRoomsToFile() {

    try {
        FileWriter writer = new FileWriter("rooms.txt");

        for (Room r : roomList) {
            writer.write(r.getRoomNo() + "," +
                         r.getType() + "," +
                         r.getPrice() + "," +
                         r.getStatus() + "\n");
        }

        writer.close();

    } catch (IOException e) {
        statusLabel.setText("Error saving file!");
    }
}

    // 🔹 ADD CUSTOMER
    @FXML
    private void addCustomer() {

        String name = customerNameField.getText();
        String contact = contactField.getText();
        String roomNo = customerRoomBox.getValue();

        if (name.isEmpty() || contact.isEmpty() || roomNo == null) {
            statusLabel.setText("Fill all customer fields!");
            return;
        }

        Customer customer = new Customer(name, contact, roomNo);
        customerList.add(customer);

        // 👉 Update booking dropdown
        bookingCustomerBox.getItems().add(name);

        statusLabel.setText("Customer added!");

        customerNameField.clear();
        contactField.clear();
        customerRoomBox.setValue(null);
    }

    // 🔹 BOOK ROOM
    @FXML
    private void bookRoom() {

        String customer = bookingCustomerBox.getValue();
        String roomNo = bookingRoomBox.getValue();

        if (customer == null || roomNo == null) {
            statusLabel.setText("Select customer and room!");
            return;
        }

        for (Room r : roomList) {

            if (r.getRoomNo().equals(roomNo)) {

                if (r.getStatus().equals("Booked")) {
                    statusLabel.setText("Room already booked!");
                    return;
                }

                r.setStatus("Booked");
                roomTable.getItems().clear();
                roomTable.setItems(roomList);
                roomTable.refresh();

                statusLabel.setText("Room booked successfully!");
                return;
            }
        }
        for (Room r : roomList) {
    if (r.getRoomNo().equals(roomNo)) {

        if (r.getStatus().equals("Booked")) {
            statusLabel.setText("Room already booked!");
            return;
        }

        r.setStatus("Booked");
        roomTable.refresh();
    }
}
    }

    @FXML
private void showAllRooms() {
    roomTable.setItems(roomList);
    statusLabel.setText("Showing all rooms");
}

    @FXML
private void showAvailableRooms() {
    ObservableList<Room> available = FXCollections.observableArrayList();

    for (Room r : roomList) {
        if (r.getStatus().equals("Available")) {
            available.add(r);
        }
    }

    roomTable.setItems(available);
    statusLabel.setText("Showing available rooms");
}

    // 🔹 CHECKOUT
    
    @FXML
private void checkout() {

    String roomNo = bookingRoomBox.getValue();
    String daysText = daysField.getText();

    if (roomNo == null || daysText.isEmpty()) {
        statusLabel.setText("Select room and enter days!");
        return;
    }

    double days;

    try {
        days = Double.parseDouble(daysText);
    } catch (Exception e) {
        statusLabel.setText("Invalid number of days!");
        return;
    }

    for (Room r : roomList) {
        if (r.getRoomNo().equals(roomNo)) {

            double bill = r.getPrice() * days;

            r.setStatus("Available");
            roomTable.setItems(roomList);
            roomTable.refresh();
            statusLabel.setText("Checked out!");

            statusLabel.setText("Bill: ₹" + bill + " for " + days + " days");

            // clear field
            daysField.clear();

            return;
        }
    }
}
}*/
      

/*package com.hotel;

import java.io.FileWriter;
import java.io.IOException;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    // 🏨 Rooms Tab Inputs
    @FXML private TextField roomNumberField;
    @FXML private ComboBox<String> roomTypeBox;
    @FXML private TextField priceField;

    // 🏨 TableView + Columns
    @FXML private TableView<Room> roomTable;
    @FXML private TableColumn<Room, String> roomNoCol;
    @FXML private TableColumn<Room, String> typeCol;
    @FXML private TableColumn<Room, Double> priceCol;
    @FXML private TableColumn<Room, String> statusCol;

    @FXML private Button addRoomBtn;

    // 👤 CUSTOMER SECTION
    @FXML private TextField customerNameField;
    @FXML private TextField contactField;
    @FXML private ComboBox<String> customerRoomBox;

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> contactCol;
    @FXML private TableColumn<Customer, String> roomCol;

    @FXML private Button addCustomerBtn;

    // 📖 BOOKING SECTION
    @FXML private ComboBox<String> bookingCustomerBox;
    @FXML private ComboBox<String> bookingRoomBox;
    @FXML private TextField daysField;

    @FXML private Button bookBtn;
    @FXML private Button checkoutBtn;

    // 📖 Status Label
    @FXML private Label statusLabel;

    // 📊 Data storage
    private ObservableList<Room> roomList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    // 🔹 INITIALIZE
    @FXML
    public void initialize() {

        // Room types
        roomTypeBox.getItems().addAll("Single", "Double", "Deluxe");

        // 🏨 Room Table Mapping
        roomNoCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRoomNo()));

        typeCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getType()));

        priceCol.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getPrice()));

        statusCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getStatus()));

        roomTable.setItems(roomList);

        // 👤 Customer Table Mapping
        nameCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        contactCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getContact()));

        roomCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRoomNo()));

        customerTable.setItems(customerList);

        statusLabel.setText("Status: Ready");
    }

    // 🔹 ADD ROOM
    @FXML
    private void addRoom() {

        String roomNo = roomNumberField.getText();
        String type = roomTypeBox.getValue();
        String priceText = priceField.getText();

        if (roomNo.isEmpty() || type == null || priceText.isEmpty()) {
            statusLabel.setText("Please fill all fields!");
            return;
        }

        double price;

        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price!");
            return;
        }

        Room room = new Room(roomNo, type, price, "Available");
        roomList.add(room);

        // Update dropdowns
        customerRoomBox.getItems().add(roomNo);
        bookingRoomBox.getItems().add(roomNo);

        statusLabel.setText("Room added successfully!");

        roomNumberField.clear();
        roomTypeBox.setValue(null);
        priceField.clear();

        saveRoomsToFile();
    }

    // 🔹 SAVE TO FILE
    private void saveRoomsToFile() {
        try {
            FileWriter writer = new FileWriter("rooms.txt");

            for (Room r : roomList) {
                writer.write(r.getRoomNo() + "," +
                             r.getType() + "," +
                             r.getPrice() + "," +
                             r.getStatus() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            statusLabel.setText("Error saving file!");
        }
    }

    // 🔹 ADD CUSTOMER
    @FXML
    private void addCustomer() {

        String name = customerNameField.getText();
        String contact = contactField.getText();
        String roomNo = customerRoomBox.getValue();

        if (name.isEmpty() || contact.isEmpty() || roomNo == null) {
            statusLabel.setText("Fill all customer fields!");
            return;
        }

        Customer customer = new Customer(name, contact, roomNo);
        customerList.add(customer);

        // Update booking dropdown
        bookingCustomerBox.getItems().add(name);

        statusLabel.setText("Customer added!");

        customerNameField.clear();
        contactField.clear();
        customerRoomBox.setValue(null);
    }

    // 🔹 BOOK ROOM
    @FXML
    private void bookRoom() {

        String customer = bookingCustomerBox.getValue();
        String roomNo = bookingRoomBox.getValue();

        if (customer == null || roomNo == null) {
            statusLabel.setText("Select customer and room!");
            return;
        }

        for (Room r : roomList) {

            if (r.getRoomNo().equals(roomNo)) {

                if (r.getStatus().equals("Booked")) {
                    statusLabel.setText("Room already booked!");
                    return;
                }

                r.setStatus("Booked");
                roomTable.refresh();

                statusLabel.setText("Room booked successfully!");
                return;
            }
        }
    }

    // 🔹 SHOW ALL ROOMS
    @FXML
    private void showAllRooms() {
        roomTable.setItems(roomList);
        statusLabel.setText("Showing all rooms");
    }

    // 🔹 SHOW AVAILABLE ROOMS
    @FXML
    private void showAvailableRooms() {

        ObservableList<Room> available = FXCollections.observableArrayList();

        for (Room r : roomList) {
            if (r.getStatus().equals("Available")) {
                available.add(r);
            }
        }

        roomTable.setItems(available);
        statusLabel.setText("Showing available rooms");
    }

    // 🔹 CHECKOUT
    @FXML
    private void checkout() {

        String roomNo = bookingRoomBox.getValue();
        String daysText = daysField.getText();

        if (roomNo == null || daysText.isEmpty()) {
            statusLabel.setText("Select room and enter days!");
            return;
        }

        double days;

        try {
            days = Double.parseDouble(daysText);
        } catch (Exception e) {
            statusLabel.setText("Invalid number of days!");
            return;
        }

        for (Room r : roomList) {
            if (r.getRoomNo().equals(roomNo)) {

                if (r.getStatus().equals("Available")) {
                    statusLabel.setText("Room is already free!");
                    return;
                }

                double bill = r.getPrice() * days;

                r.setStatus("Available");
                roomTable.refresh();

                statusLabel.setText("Checked out! Bill: ₹" + bill + " for " + days + " days");

                daysField.clear();
                return;
            }
        }
    }
}*/



/*package com.hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    // ── Rooms Tab ──
    @FXML private TextField          roomNumberField;
    @FXML private ComboBox<String>   roomTypeBox;
    @FXML private TextField          priceField;
    @FXML private TableView<Room>    roomTable;
    @FXML private TableColumn<Room, String> roomNoCol;
    @FXML private TableColumn<Room, String> typeCol;
    @FXML private TableColumn<Room, Double> priceCol;
    @FXML private TableColumn<Room, String> statusCol;
    private FilteredList<Room> filteredRooms;


    // ── Customers Tab ──
    @FXML private TextField            customerNameField;
    @FXML private TextField            contactField;
    @FXML private ComboBox<String>     customerRoomBox;
    @FXML private TableView<Customer>  customerTable;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> contactCol;
    @FXML private TableColumn<Customer, String> roomCol;

    // ── Booking Tab ──
    @FXML private ComboBox<String> bookingCustomerBox;
    @FXML private ComboBox<String> bookingRoomBox;   // shows AVAILABLE rooms only
    @FXML private ComboBox<String> checkoutRoomBox;  // shows BOOKED rooms only
    @FXML private TextField        daysField;

    // ── Billing Tab ──
    @FXML private TableView<Bill>   billingTable;
    @FXML private TableColumn<Bill, String>  billNameCol;
    @FXML private TableColumn<Bill, String>  billRoomCol;
    @FXML private TableColumn<Bill, Integer> billDaysCol;
    @FXML private TableColumn<Bill, Double>  billPriceCol;
    @FXML private TableColumn<Bill, Double>  billTotalCol;
    @FXML private TableColumn<Bill, String>  billDateCol;

    // ── Status bar (moved to BorderPane bottom — always visible) ──
    @FXML private Label statusLabel;

    // ── In-memory data ──
    private final ObservableList<Room>     roomList     = FXCollections.observableArrayList();
    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private final ObservableList<Bill>     billList     = FXCollections.observableArrayList();

    private static final String ROOMS_FILE     = "rooms.txt";
    private static final String CUSTOMERS_FILE = "customers.txt";
    private static final String BILLS_FILE     = "bills.txt";

    // ─────────────────────────────────────────────
    //  INITIALIZE
    // ─────────────────────────────────────────────
    @FXML
    public void initialize() {
        roomTypeBox.getItems().addAll("Single", "Double", "Deluxe");

        // Room table columns
        roomNoCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoomNo()));
        typeCol  .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getType()));
        priceCol .setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getPrice()));
       // statusCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getStatus()));
       statusCol.setCellValueFactory(d -> d.getValue().statusProperty());

        filteredRooms = new FilteredList<>(roomList, r -> true); // show all by default
        roomTable.setItems(filteredRooms);

        // Customer table columns
        nameCol   .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        contactCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getContact()));
        roomCol   .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoomNo()));
        customerTable.setItems(customerList);

        // Billing table columns
        billNameCol .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerName()));
        billRoomCol .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoomNo()));
        billDaysCol .setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getDays()));
        billPriceCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getPricePerDay()));
        billTotalCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getTotal()));
        billDateCol .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDate()));
        billingTable.setItems(billList);

        // FIX: Load saved data on startup
        loadRoomsFromFile();
        loadCustomersFromFile();
        loadBillsFromFile();

        setStatus("Ready — " + roomList.size() + " rooms, "
                + customerList.size() + " customers loaded.");
    }

    // ─────────────────────────────────────────────
    //  STATUS HELPER
    // ─────────────────────────────────────────────
    private void setStatus(String msg) {
        statusLabel.setText("  " + msg);
    }

    // ─────────────────────────────────────────────
    //  ROOM ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void addRoom() {
        String roomNo    = roomNumberField.getText().trim();
        String type      = roomTypeBox.getValue();
        String priceText = priceField.getText().trim();

        if (roomNo.isEmpty() || type == null || priceText.isEmpty()) {
            setStatus("Please fill all room fields!");
            return;
        }

        // FIX: Duplicate room number check
        for (Room r : roomList) {
            if (r.getRoomNo().equalsIgnoreCase(roomNo)) {
                setStatus("Room " + roomNo + " already exists!");
                return;
            }
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            setStatus("Enter a valid positive price!");
            return;
        }

        Room room = new Room(roomNo, type, price, "Available");
        roomList.add(room);
        customerRoomBox.getItems().add(roomNo);
        bookingRoomBox.getItems().add(roomNo); // available rooms dropdown

        saveRoomsToFile();
        setStatus("Room " + roomNo + " (" + type + ") added at ₹" + price + "/day.");
        roomNumberField.clear();
        roomTypeBox.setValue(null);
        priceField.clear();
    }

    @FXML
    private void showAllRooms() {
        filteredRooms.setPredicate(r -> true); // show everything        setStatus("Showing all " + roomList.size() + " rooms.");
    }

    @FXML
    private void showAvailableRooms() {
            filteredRooms.setPredicate(null);  // force clear first
        filteredRooms.setPredicate(r -> r.getStatus().equals("Available"));
        long count = roomList.stream().filter(r -> r.getStatus().equals("Available")).count();
        setStatus("Showing " + count + " available room(s).");
    }

    // ─────────────────────────────────────────────
    //  CUSTOMER ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void addCustomer() {
        String name    = customerNameField.getText().trim();
        String contact = contactField.getText().trim();
        String roomNo  = customerRoomBox.getValue();

        if (name.isEmpty() || contact.isEmpty() || roomNo == null) {
            setStatus("Fill all customer fields!");
            return;
        }

        // FIX: Basic phone number validation
        if (!contact.matches("\\d{10}")) {
            setStatus("Contact must be exactly 10 digits!");
            return;
        }

        Customer customer = new Customer(name, contact, roomNo);
        customerList.add(customer);

        // FIX: Show name + room to avoid ambiguity with same-name customers
        bookingCustomerBox.getItems().add(name + " [" + roomNo + "]");

        saveCustomersToFile();
        setStatus("Customer " + name + " added successfully!");
        customerNameField.clear();
        contactField.clear();
        customerRoomBox.setValue(null);
    }

    // ─────────────────────────────────────────────
    //  BOOKING ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void bookRoom() {
        String customerEntry = bookingCustomerBox.getValue();
        String roomNo        = bookingRoomBox.getValue();

        if (customerEntry == null || roomNo == null) {
            setStatus("Select a customer and an available room!");
            return;
        }

        for (Room r : roomList) {
            if (r.getRoomNo().equals(roomNo)) {
                if (r.getStatus().equals("Booked")) {
                    setStatus("Room " + roomNo + " is already booked!");
                    return;
                }
                r.setStatus("Booked");
                filteredRooms.setPredicate(null);  // force clear first
                filteredRooms.setPredicate(r2 -> true); // force live refresh
                roomTable.refresh();

                // FIX: Move room between dropdowns
                bookingRoomBox.getItems().remove(roomNo);
                bookingRoomBox.setValue(null);
                checkoutRoomBox.getItems().add(roomNo);

                saveRoomsToFile();
                setStatus("Room " + roomNo + " booked for " + customerEntry + "!");
                return;
            }
        }
        setStatus("Room not found in the system!");
    }

    @FXML
    private void checkout() {
        String roomNo   = checkoutRoomBox.getValue();
        String daysText = daysField.getText().trim();

        if (roomNo == null || daysText.isEmpty()) {
            setStatus("Select a booked room and enter days stayed!");
            return;
        }

        // FIX: days as int, not double
        int days;
        try {
            days = Integer.parseInt(daysText);
            if (days <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            setStatus("Days must be a positive whole number (e.g. 3)!");
            return;
        }

        for (Room r : roomList) {
            if (r.getRoomNo().equals(roomNo)) {
                if (r.getStatus().equals("Available")) {
                    setStatus("Room " + roomNo + " is not currently booked!");
                    return;
                }

                // Find the customer linked to this room
                String customerName = "Unknown";
                for (Customer c : customerList) {
                    if (c.getRoomNo().equals(roomNo)) {
                        customerName = c.getName();
                        break;
                    }
                }

                double billAmount = r.getPrice() * days;

                // Create and store bill record
                Bill bill = new Bill(customerName, roomNo, days, r.getPrice());
                billList.add(bill);
                saveBillsToFile();

                // Release room
                r.setStatus("Available");
                filteredRooms.setPredicate(null);
                filteredRooms.setPredicate(r2 -> true);            
                roomTable.refresh();
                checkoutRoomBox.getItems().remove(roomNo);
                checkoutRoomBox.setValue(null);
                bookingRoomBox.getItems().add(roomNo);
                daysField.clear();

                saveRoomsToFile();
                setStatus("Checkout done! Bill for " + customerName + ": ₹"
                        + String.format("%.2f", billAmount) + " (" + days + " day(s))");
                return;
            }
        }
        setStatus("Room not found in the system!");
    }

    // ─────────────────────────────────────────────
    //  BILLING ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void clearBills() {
        if (billList.isEmpty()) {
            setStatus("No billing history to clear.");
            return;
        }
        billList.clear();
        try { Files.deleteIfExists(Paths.get(BILLS_FILE)); } catch (IOException ignored) {}
        setStatus("Billing history cleared.");
    }

    // ─────────────────────────────────────────────
    //  FILE: ROOMS
    // ─────────────────────────────────────────────
    private void saveRoomsToFile() {
        // FIX: try-with-resources so writer is always closed safely
        try (BufferedWriter w = new BufferedWriter(new FileWriter(ROOMS_FILE))) {
            for (Room r : roomList) {
                w.write(r.toCSV());
                w.newLine();
            }
        } catch (IOException e) {
            setStatus("Error saving rooms: " + e.getMessage());
        }
    }

    private void loadRoomsFromFile() {
        File file = new File(ROOMS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Room r = Room.fromCSV(line);
                if (r == null) continue;
                roomList.add(r);
                customerRoomBox.getItems().add(r.getRoomNo());
                if (r.getStatus().equals("Available")) {
                    bookingRoomBox.getItems().add(r.getRoomNo());
                } else {
                    checkoutRoomBox.getItems().add(r.getRoomNo());
                }
            }
        } catch (IOException e) {
            setStatus("Error loading rooms: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  FILE: CUSTOMERS
    // ─────────────────────────────────────────────
    // FIX: Customer file persistence — was completely missing before
    private void saveCustomersToFile() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer c : customerList) {
                w.write(c.toCSV());
                w.newLine();
            }
        } catch (IOException e) {
            setStatus("Error saving customers: " + e.getMessage());
        }
    }

    private void loadCustomersFromFile() {
        File file = new File(CUSTOMERS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Customer c = Customer.fromCSV(line);
                if (c == null) continue;
                customerList.add(c);
                bookingCustomerBox.getItems().add(c.getName() + " [" + c.getRoomNo() + "]");
            }
        } catch (IOException e) {
            setStatus("Error loading customers: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  FILE: BILLS
    // ─────────────────────────────────────────────
    private void saveBillsToFile() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(BILLS_FILE))) {
            for (Bill b : billList) {
                w.write(b.toCSV());
                w.newLine();
            }
        } catch (IOException e) {
            setStatus("Error saving bills: " + e.getMessage());
        }
    }

    private void loadBillsFromFile() {
        File file = new File(BILLS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Bill b = Bill.fromCSV(line);
                if (b != null) billList.add(b);
            }
        } catch (IOException e) {
            setStatus("Error loading bills: " + e.getMessage());
        }
    }
} */






















/* 
package com.hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    // ── Rooms Tab ──
    @FXML private TextField roomNumberField;
    @FXML private ComboBox<String> roomTypeBox;
    @FXML private TextField priceField;
    @FXML private TableView<Room> roomTable;
    @FXML private TableColumn<Room, String> roomNoCol;
    @FXML private TableColumn<Room, String> typeCol;
    @FXML private TableColumn<Room, Double> priceCol;
    @FXML private TableColumn<Room, String> statusCol;

    // ── Customers Tab ──
    @FXML private TextField customerNameField;
    @FXML private TextField contactField;
    @FXML private ComboBox<String> customerRoomBox;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> contactCol;
    @FXML private TableColumn<Customer, String> roomCol;

    // ── Booking Tab ──
    @FXML private ComboBox<String> bookingCustomerBox;
    @FXML private ComboBox<String> bookingRoomBox;
    @FXML private ComboBox<String> checkoutRoomBox;
    @FXML private TextField daysField;

    // ── Billing Tab ──
    @FXML private TableView<Bill> billingTable;
    @FXML private TableColumn<Bill, String>  billNameCol;
    @FXML private TableColumn<Bill, String>  billRoomCol;
    @FXML private TableColumn<Bill, Integer> billDaysCol;
    @FXML private TableColumn<Bill, Double>  billPriceCol;
    @FXML private TableColumn<Bill, Double>  billTotalCol;
    @FXML private TableColumn<Bill, String>  billDateCol;

    // ── Status bar ──
    @FXML private Label statusLabel;

    // ── In-memory data ──
    private final ObservableList<Room>     roomList     = FXCollections.observableArrayList();
    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private final ObservableList<Bill>     billList     = FXCollections.observableArrayList();

    // FilteredList — NOT @FXML, just a regular field
    private FilteredList<Room> filteredRooms;

    // Tracks which predicate is currently active so we can re-apply it after status changes
    private Predicate<Room> currentPredicate = r -> true;

    private static final String ROOMS_FILE     = "rooms.txt";
    private static final String CUSTOMERS_FILE = "customers.txt";
    private static final String BILLS_FILE     = "bills.txt";

    // ─────────────────────────────────────────────
    //  INITIALIZE
    // ─────────────────────────────────────────────
    @FXML
    public void initialize() {
        roomTypeBox.getItems().addAll("Single", "Double", "Deluxe");

        // statusCol bound to statusProperty() — auto-redraws when setStatus() is called
        roomNoCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoomNo()));
        typeCol  .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getType()));
        priceCol .setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getPrice()));
        statusCol.setCellValueFactory(d -> d.getValue().statusProperty());

        // FilteredList wraps roomList — table points to filteredRooms, NOT roomList directly
        filteredRooms = new FilteredList<>(roomList, currentPredicate);
        roomTable.setItems(filteredRooms);

        nameCol   .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        contactCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getContact()));
        roomCol   .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoomNo()));
        customerTable.setItems(customerList);

        billNameCol .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerName()));
        billRoomCol .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoomNo()));
        billDaysCol .setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getDays()));
        billPriceCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getPricePerDay()));
        billTotalCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getTotal()));
        billDateCol .setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDate()));
        billingTable.setItems(billList);

        loadRoomsFromFile();
        loadCustomersFromFile();
        loadBillsFromFile();

        setStatus("Ready — " + roomList.size() + " rooms, "
                + customerList.size() + " customers loaded.");
    }

    // ─────────────────────────────────────────────
    //  FILTER HELPER
    //  Always go via this method — it saves the predicate
    //  so bookRoom/checkout can re-apply it after status changes
    // ─────────────────────────────────────────────
    private void applyPredicate(Predicate<Room> predicate) {
        currentPredicate = predicate;
        filteredRooms.setPredicate(null);        // force JavaFX to see it as a new predicate
        filteredRooms.setPredicate(predicate);
    }

    // ─────────────────────────────────────────────
    //  STATUS HELPER
    // ─────────────────────────────────────────────
    private void setStatus(String msg) {
        statusLabel.setText("  " + msg);
    }

    // ─────────────────────────────────────────────
    //  ROOM ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void addRoom() {
        String roomNo    = roomNumberField.getText().trim();
        String type      = roomTypeBox.getValue();
        String priceText = priceField.getText().trim();

        if (roomNo.isEmpty() || type == null || priceText.isEmpty()) {
            setStatus("Please fill all room fields!");
            return;
        }

        for (Room r : roomList) {
            if (r.getRoomNo().equalsIgnoreCase(roomNo)) {
                setStatus("Room " + roomNo + " already exists!");
                return;
            }
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            setStatus("Enter a valid positive price!");
            return;
        }

        Room room = new Room(roomNo, type, price, "Available");
        roomList.add(room);
        customerRoomBox.getItems().add(roomNo);
        bookingRoomBox.getItems().add(roomNo);

        // Re-apply current predicate so new room appears correctly in filtered view
        applyPredicate(currentPredicate);

        saveRoomsToFile();
        setStatus("Room " + roomNo + " (" + type + ") added at ₹" + price + "/day.");
        roomNumberField.clear();
        roomTypeBox.setValue(null);
        priceField.clear();
    }

    @FXML
    private void showAllRooms() {
        applyPredicate(r -> true);
        setStatus("Showing all " + roomList.size() + " rooms.");
    }

    @FXML
    private void showAvailableRooms() {
        applyPredicate(r -> r.getStatus().equals("Available"));
        long count = roomList.stream()
                             .filter(r -> r.getStatus().equals("Available"))
                             .count();
        setStatus("Showing " + count + " available room(s).");
    }

    // ─────────────────────────────────────────────
    //  CUSTOMER ACTIONS
    // ─────────────────────────────────────────────

    
    @FXML
    private void addCustomer() {
        String name    = customerNameField.getText().trim();
        String contact = contactField.getText().trim();
        String roomNo  = customerRoomBox.getValue();

        if (name.isEmpty() || contact.isEmpty() || roomNo == null) {
            setStatus("Fill all customer fields!");
            return;
        }

        if (!contact.matches("\\d{10}")) {
            setStatus("Contact must be exactly 10 digits!");
            return;
        }

        Customer customer = new Customer(name, contact, roomNo);
        customerList.add(customer);
        bookingCustomerBox.getItems().add(name + " [" + roomNo + "]");

        saveCustomersToFile();
        setStatus("Customer " + name + " added successfully!");
        customerNameField.clear();
        contactField.clear();
        customerRoomBox.setValue(null);
    }
    

    // ─────────────────────────────────────────────
    //  BOOKING ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void bookRoom() {
        String customerEntry = bookingCustomerBox.getValue();
        String roomNo        = bookingRoomBox.getValue();

        
        if (customerEntry == null || roomNo == null) {
            setStatus("Select a customer and an available room!");
            return;
        }

        for (Room r : roomList) {
            if (r.getRoomNo().equals(roomNo)) {
                if (r.getStatus().equals("Booked")) {
                    setStatus("Room " + roomNo + " is already booked!");
                    return;
                }

                r.setStatus("Booked");

                // Re-apply current predicate so filtered view reflects new status immediately
                applyPredicate(currentPredicate);

                bookingRoomBox.getItems().remove(roomNo);
                bookingRoomBox.setValue(null);
                checkoutRoomBox.getItems().add(roomNo);

                saveRoomsToFile();
                setStatus("Room " + roomNo + " booked for " + customerEntry + "!");
                return;
            }
        }
        setStatus("Room not found in the system!");
    }




       
       
       
        @FXML
private void showAvailableRooms() {
    System.out.println("=== AVAILABLE ONLY CLICKED ===");
    for (Room r : roomList) {
        System.out.println("Room: " + r.getRoomNo() + " | status: " + r.getStatus());
    }
    applyPredicate(r -> r.getStatus().equals("Available"));
    long count = roomList.stream().filter(r -> r.getStatus().equals("Available")).count();
    setStatus("Showing " + count + " available room(s).");
}

@FXML
private void bookRoom() {
    String customerEntry = bookingCustomerBox.getValue();
    String roomNo        = bookingRoomBox.getValue();

    System.out.println("=== BOOK ROOM CALLED ===");
    System.out.println("Customer: " + customerEntry);
    System.out.println("Room selected: " + roomNo);
    System.out.println("Total rooms in list: " + roomList.size());

    if (customerEntry == null || roomNo == null) {
        setStatus("Select a customer and an available room!");
        return;
    }

    for (Room r : roomList) {
        System.out.println("Checking room: " + r.getRoomNo() + " | status: " + r.getStatus());
        if (r.getRoomNo().equals(roomNo)) {
            System.out.println("FOUND MATCH — setting to Booked");
            r.setStatus("Booked");
            System.out.println("Status after set: " + r.getStatus());

            applyPredicate(currentPredicate);
            bookingRoomBox.getItems().remove(roomNo);
            bookingRoomBox.setValue(null);
            checkoutRoomBox.getItems().add(roomNo);
            saveRoomsToFile();
            setStatus("Room " + roomNo + " booked for " + customerEntry + "!");
            return;
        }
    }
    System.out.println("NO MATCH FOUND — room not in list");
    setStatus("Room not found in the system!");
}

    @FXML
    private void checkout() {
        String roomNo   = checkoutRoomBox.getValue();
        String daysText = daysField.getText().trim();

        if (roomNo == null || daysText.isEmpty()) {
            setStatus("Select a booked room and enter days stayed!");
            return;
        }

        int days;
        try {
            days = Integer.parseInt(daysText);
            if (days <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            setStatus("Days must be a positive whole number (e.g. 3)!");
            return;
        }

        for (Room r : roomList) {
            if (r.getRoomNo().equals(roomNo)) {
                if (r.getStatus().equals("Available")) {
                    setStatus("Room " + roomNo + " is not currently booked!");
                    return;
                }

                String customerName = "Unknown";
                for (Customer c : customerList) {
                    if (c.getRoomNo().equals(roomNo)) {
                        customerName = c.getName();
                        break;
                    }
                }

                Bill bill = new Bill(customerName, roomNo, days, r.getPrice());
                billList.add(bill);
                saveBillsToFile();

                double billAmount = r.getPrice() * days;
                r.setStatus("Available");

                // Re-apply current predicate so filtered view reflects new status immediately
                applyPredicate(currentPredicate);

                checkoutRoomBox.getItems().remove(roomNo);
                checkoutRoomBox.setValue(null);
                bookingRoomBox.getItems().add(roomNo);
                daysField.clear();

                saveRoomsToFile();
                setStatus("Checkout done! Bill for " + customerName + ": ₹"
                        + String.format("%.2f", billAmount) + " (" + days + " day(s))");
                return;
            }
        }
        setStatus("Room not found in the system!");
    }

    // ─────────────────────────────────────────────
    //  BILLING ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void clearBills() {
        if (billList.isEmpty()) {
            setStatus("No billing history to clear.");
            return;
        }
        billList.clear();
        try { Files.deleteIfExists(Paths.get(BILLS_FILE)); } catch (IOException ignored) {}
        setStatus("Billing history cleared.");
    }

    // ─────────────────────────────────────────────
    //  FILE: ROOMS
    // ─────────────────────────────────────────────
    private void saveRoomsToFile() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(ROOMS_FILE))) {
            for (Room r : roomList) {
                w.write(r.toCSV());
                w.newLine();
            }
        } catch (IOException e) {
            setStatus("Error saving rooms: " + e.getMessage());
        }
    }

    private void loadRoomsFromFile() {
        File file = new File(ROOMS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Room r = Room.fromCSV(line);
                if (r == null) continue;
                roomList.add(r);
                customerRoomBox.getItems().add(r.getRoomNo());
                if (r.getStatus().equals("Available")) {
                    bookingRoomBox.getItems().add(r.getRoomNo());
                } else {
                    checkoutRoomBox.getItems().add(r.getRoomNo());
                }
            }
        } catch (IOException e) {
            setStatus("Error loading rooms: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  FILE: CUSTOMERS
    // ─────────────────────────────────────────────
    private void saveCustomersToFile() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer c : customerList) {
                w.write(c.toCSV());
                w.newLine();
            }
        } catch (IOException e) {
            setStatus("Error saving customers: " + e.getMessage());
        }
    }

    private void loadCustomersFromFile() {
        File file = new File(CUSTOMERS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Customer c = Customer.fromCSV(line);
                if (c == null) continue;
                customerList.add(c);
                bookingCustomerBox.getItems().add(c.getName() + " [" + c.getRoomNo() + "]");
            }
        } catch (IOException e) {
            setStatus("Error loading customers: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  FILE: BILLS
    // ─────────────────────────────────────────────
    private void saveBillsToFile() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(BILLS_FILE))) {
            for (Bill b : billList) {
                w.write(b.toCSV());
                w.newLine();
            }
        } catch (IOException e) {
            setStatus("Error saving bills: " + e.getMessage());
        }
    }

    private void loadBillsFromFile() {
        File file = new File(BILLS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Bill b = Bill.fromCSV(line);
                if (b != null) billList.add(b);
            }
        } catch (IOException e) {
            setStatus("Error loading bills: " + e.getMessage());
        }
    }*/









        //FINALLLLLLLLLL
         
package com.hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

    // ── Rooms Tab ──
    @FXML private TextField roomNumberField;
    @FXML private ComboBox<RoomType> roomTypeBox;
    @FXML private TextField priceField;
    @FXML private TableView<Room> roomTable;
    @FXML private TableColumn<Room, String> roomNoCol;
    @FXML private TableColumn<Room, String> typeCol;
    @FXML private TableColumn<Room, Double> priceCol;
    @FXML private TableColumn<Room, String> statusCol;

    // ── Customers Tab ──
    @FXML private TextField customerNameField;
    @FXML private TextField contactField;
    @FXML private ComboBox<String> customerRoomBox;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> contactCol;
    @FXML private TableColumn<Customer, String> roomCol;

    // ── Booking Tab ──
    @FXML private ComboBox<String> bookingCustomerBox;
    @FXML private ComboBox<String> bookingRoomBox;
    @FXML private ComboBox<String> checkoutRoomBox;
    @FXML private TextField daysField;

    // ── Billing Tab ──
    @FXML private TableView<Bill> billingTable;
    @FXML private TableColumn<Bill, String>  billNameCol;
    @FXML private TableColumn<Bill, String>  billRoomCol;
    @FXML private TableColumn<Bill, Integer> billDaysCol;
    @FXML private TableColumn<Bill, Double>  billPriceCol;
    @FXML private TableColumn<Bill, Double>  billTotalCol;
    @FXML private TableColumn<Bill, String>  billDateCol;

    // ── Status bar ──
    @FXML private Label statusLabel;

    // ── In-memory data ──
    private final ObservableList<Room>     roomList     = FXCollections.observableArrayList();
    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private final ObservableList<Bill>     billList     = FXCollections.observableArrayList();

    // Tracks which filter is active: "all" or "available"
    private String activeFilter = "all";

    private static final String ROOMS_FILE     = "rooms.txt";
    private static final String CUSTOMERS_FILE = "customers.txt";
    private static final String BILLS_FILE     = "bills.txt";


 @FXML
public void initialize() {
    roomTypeBox.getItems().addAll(RoomType.values());

    roomNoCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoomNo()));
    typeCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getType()));
    priceCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getPrice()));

    // Proper binding to statusProperty (CRITICAL CHANGE)
    statusCol.setCellValueFactory(d -> d.getValue().statusProperty());

    // Custom cell factory for styling
    statusCol.setCellFactory(col -> new TableCell<>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                setText(null);
                setStyle("");
            } else {
                Room room = getTableRow().getItem();
                String status = room.getStatus();

                setText(status);

                // Color coding
                if ("Booked".equalsIgnoreCase(status)) {
                    setStyle("-fx-text-fill: #dc2626; -fx-font-weight: bold;");
                } else {
                    setStyle("-fx-text-fill: #16a34a; -fx-font-weight: bold;");
                }
            }
        }
    });

    // Table setup
    roomTable.setItems(roomList);

    nameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
    contactCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getContact()));
    roomCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoomNo()));
    customerTable.setItems(customerList);

    billNameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerName()));
    billRoomCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getRoomNo()));
    billDaysCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getDays()));
    billPriceCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getPricePerDay()));
    billTotalCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getTotal()));
    billDateCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDate()));
    billingTable.setItems(billList);

    // Load data
    loadRoomsFromFile();
    loadCustomersFromFile();
    loadBillsFromFile();

    setStatus("Ready — " + roomList.size() + " rooms, "
            + customerList.size() + " customers loaded.");
}


private void refreshTable() {

    FilteredList<Room> filteredList = new FilteredList<>(roomList);

    filteredList.setPredicate(room -> {
        if ("available".equalsIgnoreCase(activeFilter)) {
            return "Available".equalsIgnoreCase(room.getStatus());
        }
        return true;
    });

    roomTable.setItems(filteredList);
}

    // ─────────────────────────────────────────────
    //  STATUS HELPER
    // ─────────────────────────────────────────────
    private void setStatus(String msg) {
        statusLabel.setText("  " + msg);
    }

    // ─────────────────────────────────────────────
    //  ROOM ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void addRoom() {
        String roomNo    = roomNumberField.getText().trim();
        RoomType type      = roomTypeBox.getValue();
        String priceText = priceField.getText().trim();

        if (roomNo.isEmpty() || type == null || priceText.isEmpty()) {
            setStatus("Please fill all room fields!");
            return;
        }

        for (Room r : roomList) {
            if (r.getRoomNo().equalsIgnoreCase(roomNo)) {
                setStatus("Room " + roomNo + " already exists!");
                return;
            }
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            setStatus("Enter a valid positive price!");
            return;
        }
 
        RoomType selectedType = roomTypeBox.getValue();
        price = Double.parseDouble(priceField.getText());
        Room room = new Room(roomNo, selectedType, price, "Available");
        roomList.add(room);
        customerRoomBox.getItems().add(roomNo);
        bookingRoomBox.getItems().add(roomNo);

        refreshTable();
        saveRoomsToFile();
        setStatus("Room " + roomNo + " (" + type + ") added at ₹" + price + "/day.");
        roomNumberField.clear();
        roomTypeBox.setValue(null);
        priceField.clear();
    }

    @FXML
    private void showAllRooms() {
        activeFilter = "all";
        refreshTable();
        setStatus("Showing all " + roomList.size() + " rooms.");
    }

    @FXML
    private void showAvailableRooms() {
        activeFilter = "available";
        refreshTable();
        long count = roomList.stream()
                             .filter(r -> "Available".equals(r.getStatus()))
                             .count();
        setStatus("Showing " + count + " available room(s).");
    }

    // ─────────────────────────────────────────────
    //  CUSTOMER ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void addCustomer() {
        String name    = customerNameField.getText().trim();
        String contact = contactField.getText().trim();
        String roomNo  = customerRoomBox.getValue();

        if (name.isEmpty() || contact.isEmpty() || roomNo == null) {
            setStatus("Fill all customer fields!");
            return;
        }

        if (!contact.matches("\\d{10}")) {
            setStatus("Contact must be exactly 10 digits!");
            return;
        }

        Customer customer = new Customer(name, contact, roomNo);
        customerList.add(customer);
        bookingCustomerBox.getItems().add(name + " [" + roomNo + "]");

        saveCustomersToFile();
        setStatus("Customer " + name + " added successfully!");
        customerNameField.clear();
        contactField.clear();
        customerRoomBox.setValue(null);
    }

    // ─────────────────────────────────────────────
    //  BOOKING ACTIONS
    // ─────────────────────────────────────────────
   @FXML
    private void bookRoom() {
        String customerEntry = bookingCustomerBox.getValue();
        String roomNo        = bookingRoomBox.getValue();

        if (customerEntry == null || roomNo == null) {
            setStatus("Select a customer and an available room!");
            return;
        }

        // Find the Room object directly in roomList by index
        Room targetRoom = null;
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomNo().equals(roomNo)) {
                targetRoom = roomList.get(i);
                break;
            }
        }

        if (targetRoom == null) {
            setStatus("Room not found!");
            return;
        }

        synchronized (this) {
    if ("Booked".equalsIgnoreCase(targetRoom.getStatus())) {
        setStatus("Room already booked!");
        return;
    }

    targetRoom.setStatus("Booked");
}

new Thread(new RoomServiceTask("Room Cleaning", roomNo)).start();
new Thread(new RoomServiceTask("Food Delivery", roomNo)).start();
        System.out.println("Set room " + roomNo + " to Booked. Verify: " + targetRoom.getStatus());
        System.out.println("Same object in roomList? " + (roomList.contains(targetRoom)));

        bookingRoomBox.getItems().remove(roomNo);
        bookingRoomBox.setValue(null);
        checkoutRoomBox.getItems().add(roomNo);

        refreshTable(); // re-reads Room.getStatus() for every row
        saveRoomsToFile();
        setStatus("Room " + roomNo + " booked for " + customerEntry + "!");
    }

    @FXML
    private void checkout() {
        String roomNo   = checkoutRoomBox.getValue();
        String daysText = daysField.getText().trim();

        if (roomNo == null || daysText.isEmpty()) {
            setStatus("Select a booked room and enter days stayed!");
            return;
        }

        int days;
        try {
            days = Integer.parseInt(daysText);
            if (days <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            setStatus("Days must be a positive whole number (e.g. 3)!");
            return;
        }

        Room targetRoom = null;
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomNo().equals(roomNo)) {
                targetRoom = roomList.get(i);
                break;
            }
        }

        if (targetRoom == null) {
            setStatus("Room not found!");
            return;
        }

        if ("Available".equals(targetRoom.getStatus())) {
            setStatus("Room " + roomNo + " is not currently booked!");
            return;
        }

        String customerName = "Unknown";
        for (Customer c : customerList) {
            if (c.getRoomNo().equals(roomNo)) {
                customerName = c.getName();
                break;
            }
        }

        Bill bill = new Bill(customerName, roomNo, days, targetRoom.getPrice());
        billList.add(bill);
        saveBillsToFile();

        double billAmount = targetRoom.getPrice() * days;
        targetRoom.setStatus("Available");

        checkoutRoomBox.getItems().remove(roomNo);
        checkoutRoomBox.setValue(null);
        bookingRoomBox.getItems().add(roomNo);
        daysField.clear();

        refreshTable();
        saveRoomsToFile();
        setStatus("Checkout done! Bill for " + customerName + ": ₹"
                + String.format("%.2f", billAmount) + " (" + days + " day(s))");
    }

    // ─────────────────────────────────────────────
    //  BILLING ACTIONS
    // ─────────────────────────────────────────────
    @FXML
    private void clearBills() {
        if (billList.isEmpty()) {
            setStatus("No billing history to clear.");
            return;
        }
        billList.clear();
        try { Files.deleteIfExists(Paths.get(BILLS_FILE)); } catch (IOException ignored) {}
        setStatus("Billing history cleared.");
    }

    // ─────────────────────────────────────────────
    //  FILE: ROOMS
    // ─────────────────────────────────────────────
    private void saveRoomsToFile() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(ROOMS_FILE))) {
            for (Room r : roomList) {
                w.write(r.toCSV());
                w.newLine();
            }
        } catch (IOException e) {
            setStatus("Error saving rooms: " + e.getMessage());
        }
    }

    private void loadRoomsFromFile() {
        File file = new File(ROOMS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Room r = Room.fromCSV(line);
                if (r == null) continue;
                roomList.add(r);
                customerRoomBox.getItems().add(r.getRoomNo());
                if ("Available".equals(r.getStatus())) {
                    bookingRoomBox.getItems().add(r.getRoomNo());
                } else {
                    checkoutRoomBox.getItems().add(r.getRoomNo());
                }
            }
        } catch (IOException e) {
            setStatus("Error loading rooms: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  FILE: CUSTOMERS
    // ─────────────────────────────────────────────
    private void saveCustomersToFile() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer c : customerList) {
                w.write(c.toCSV());
                w.newLine();
            }
        } catch (IOException e) {
            setStatus("Error saving customers: " + e.getMessage());
        }
    }

    private void loadCustomersFromFile() {
        File file = new File(CUSTOMERS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Customer c = Customer.fromCSV(line);
                if (c == null) continue;
                customerList.add(c);
                bookingCustomerBox.getItems().add(c.getName() + " [" + c.getRoomNo() + "]");
            }
        } catch (IOException e) {
            setStatus("Error loading customers: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  FILE: BILLS
    // ─────────────────────────────────────────────
    private void saveBillsToFile() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(BILLS_FILE))) {
            for (Bill b : billList) {
                w.write(b.toCSV());
                w.newLine();
            }
        } catch (IOException e) {
            setStatus("Error saving bills: " + e.getMessage());
        }
    }

    private void loadBillsFromFile() {
        File file = new File(BILLS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Bill b = Bill.fromCSV(line);
                if (b != null) billList.add(b);
            }
        } catch (IOException e) {
            setStatus("Error loading bills: " + e.getMessage());
        }
    }
}











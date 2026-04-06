         
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











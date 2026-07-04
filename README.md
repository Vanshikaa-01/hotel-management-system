# Hotel Management System

A desktop application built with **Java and JavaFX** to manage day-to-day hotel operations — room inventory, customer check-ins, bookings, and billing — through a clean, color-coded GUI.

## Features

- **Room Management**: Add rooms with type (Single / Double / Deluxe), price per day, and live status tracking. Rooms are color-coded (green = available, red = booked) and can be filtered to show all or only available rooms.
- **Customer Management**: Register customers with name, contact number (validated to 10 digits), and room assignment.
- **Booking & Checkout**: Book an available room to a customer, and check out with automatic bill calculation based on days stayed and room rate.
- **Billing History**: View a running table of all generated bills, with the option to clear history.
- **Background Task Simulation**: On booking a room, background threads simulate real hotel services (room cleaning, food delivery) running concurrently without blocking the UI.
- **Persistent Storage**: Room, customer, and billing data is saved to local text files (`rooms.txt`, `customers.txt`, `bills.txt`) in CSV format and reloaded automatically on app start.

## Tech Stack

- **Language**: Java
- **GUI**: JavaFX (FXML for layout, CSS for styling)
- **Build Tool**: Maven
- **Persistence**: File I/O (CSV-formatted `.txt` files)
- **Concurrency**: Java Threads (`Runnable` tasks for simulated background services)
- **Testing**: JUnit

## Project Structure

```
src/main/java/com/hotel/
├── Main.java              # App entry point, loads FXML UI
├── Controller.java        # Core application logic and event handling
├── Room.java               # Room model + CSV serialization
├── Customer.java           # Customer model + CSV serialization
├── Bill.java                # Bill model + CSV serialization
├── RoomType.java           # Enum: SINGLE, DOUBLE, DELUXE
└── RoomServiceTask.java    # Simulated background service tasks (Runnable)

src/main/resources/
├── ui.fxml                 # UI layout
└── style.css               # Styling

src/test/java/com/hotel/
└── AppTest.java             # Unit tests
```

## How It Works

1. **Rooms tab** – Add rooms and view them in a live-updating, color-coded table.
2. **Customers tab** – Register customers and assign them to rooms.
3. **Booking tab** – Book an available room to a registered customer; triggers background service tasks.
4. **Checkout** – Select a booked room, enter days stayed, and generate a bill automatically.
5. **Billing tab** – View and manage billing history.

All data persists across sessions via local text files, so the app retains state between runs without needing an external database.

## Running the Project

```bash
mvn clean javafx:run
```

(Requires JavaFX SDK and Maven configured for your environment.)

## Future Improvements

- Migrate from flat-file storage to a proper database (e.g., SQLite/PostgreSQL)
- Add user authentication for staff/admin roles
- Add search and sorting functionality to tables
- Export bills as PDF invoices

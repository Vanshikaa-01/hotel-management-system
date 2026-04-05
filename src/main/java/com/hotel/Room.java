
package com.hotel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {

    private String roomNo;
    private RoomType type;
    private double price;
    private final StringProperty status = new SimpleStringProperty();

    public Room(String roomNo, RoomType type, double price, String status) {
        this.roomNo = roomNo;
        this.type   = type;
        this.price  = price;
        this.status.set(status);
    }

    public String getRoomNo() { return roomNo; }
    public String getType()   { return type.name(); }
    public double getPrice()  { return price; }
    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }

    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }
    public void setType(RoomType type)     { this.type = type; }
    public void setPrice(double price)   { this.price = price; }
    public void setStatus(String status) { this.status.set(status); }

    public String toCSV() {
        return roomNo + "," + type + "," + price + "," + status.get();
    }

    public static Room fromCSV(String line) {
    if (line == null || line.trim().isEmpty()) return null;

    String[] p = line.split(",");
    if (p.length != 4) return null;

    try {
        //  Convert String → Enum
        RoomType type = RoomType.valueOf(p[1].trim().toUpperCase());

        return new Room(
            p[0].trim(),                         // roomNo
            type,                                // enum
            Double.parseDouble(p[2].trim()),     // price
            p[3].trim()                          // status
        );

    } catch (Exception e) {
        return null; // handles both NumberFormat + invalid enum
    }
}

    @Override
    public String toString() {
        return roomNo + " | " + type + " | ₹" + price + "/day | " + status.get();
    }
}












/*package com.hotel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {

    private String roomNo;
    private String type;
    private double price;
    private final StringProperty status = new SimpleStringProperty();

    public Room(String roomNo, String type, double price, String status) {
        this.roomNo = roomNo;
        this.type = type;
        this.price = price;
        this.status.set(status);
    }

    public String getRoomNo() { return roomNo; }
    public String getType() { return type; }
    public double getPrice() { return price; }

    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }

    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }
    public void setType(String type) { this.type = type; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status.set(status); }

    public String toCSV() {
        return roomNo + "," + type + "," + price + "," + status.get();
    }

    public static Room fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;

        String[] p = line.split(",");
        if (p.length != 4) return null;

        try {
            return new Room(
                p[0].trim(),
                p[1].trim(),
                Double.parseDouble(p[2].trim()),
                p[3].trim()
            );
        } catch (Exception e) {
            return null;
        }
    }
}*/

package com.hotel;

public class Customer {

    private String name;
    private String contact;
    private String roomNo;

    public Customer(String name, String contact, String roomNo) {
        this.name    = name;
        this.contact = contact;
        this.roomNo  = roomNo;
    }

    // ── Getters ──
    public String getName()    { return name; }
    public String getContact() { return contact; }
    public String getRoomNo()  { return roomNo; }

    // ── Setters (added) ──
    public void setName(String name)       { this.name = name; }
    public void setContact(String contact) { this.contact = contact; }
    public void setRoomNo(String roomNo)   { this.roomNo = roomNo; }

    // ── File serialization ──
    public String toCSV() {
        return name + "," + contact + "," + roomNo;
    }

    public static Customer fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] p = line.split(",");
        if (p.length != 3) return null;
        return new Customer(p[0].trim(), p[1].trim(), p[2].trim());
    }

    @Override
    public String toString() {
        return name + " | " + contact + " | Room: " + roomNo;
    }
}
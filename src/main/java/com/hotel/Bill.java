package com.hotel;

import java.time.LocalDate;

public class Bill {

    private String customerName;
    private String roomNo;
    private int    days;
    private double pricePerDay;
    private double total;
    private String date;

    // Constructor used at checkout — calculates total automatically
    public Bill(String customerName, String roomNo, int days, double pricePerDay) {
        this.customerName = customerName;
        this.roomNo       = roomNo;
        this.days         = days;
        this.pricePerDay  = pricePerDay;
        this.total        = Math.round(days * pricePerDay * 100.0) / 100.0;
        this.date         = LocalDate.now().toString();
    }

    // Private constructor used only by fromCSV
    private Bill(String customerName, String roomNo, int days,
                 double pricePerDay, double total, String date) {
        this.customerName = customerName;
        this.roomNo       = roomNo;
        this.days         = days;
        this.pricePerDay  = pricePerDay;
        this.total        = total;
        this.date         = date;
    }

    // ── Getters ──
    public String getCustomerName() { return customerName; }
    public String getRoomNo()       { return roomNo; }
    public int    getDays()         { return days; }
    public double getPricePerDay()  { return pricePerDay; }
    public double getTotal()        { return total; }
    public String getDate()         { return date; }

    // ── File serialization ──
    public String toCSV() {
        return customerName + "," + roomNo + "," + days + ","
             + pricePerDay + "," + total + "," + date;
    }

    public static Bill fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] p = line.split(",");
        if (p.length != 6) return null;
        try {
            return new Bill(
                p[0].trim(), p[1].trim(),
                Integer.parseInt(p[2].trim()),
                Double.parseDouble(p[3].trim()),
                Double.parseDouble(p[4].trim()),
                p[5].trim()
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return customerName + " | Room " + roomNo + " | "
             + days + " days | ₹" + total + " | " + date;
    }
}
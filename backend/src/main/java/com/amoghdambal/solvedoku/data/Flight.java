package com.amoghdambal.solvedoku.data;

import org.springframework.data.annotation.Id;

public class Flight {
    @Id
    private String id;

    private String origin;
    private String destination;

    private String airline;
    private int flightNumber;

    public Flight() {
        this.origin = null;
        this.destination = null;
        this.airline = null;
        this.flightNumber = -1;
    }

    public Flight(String o, String d, String airline, int n) {
        this.origin = o;
        this.destination = d;
        this.flightNumber = n;
        this.airline = airline;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String newID) {
        this.id = newID;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String dest) {
        this.destination = dest;
    }

    public String getAirline() {
        return this.airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(int fn) {
        this.flightNumber = fn;
    }
}

package com.example.parkinglot.models;

public abstract class Gate {

    private final String gateName;

    private final int position;

    public Gate(String gateName, int position) {
        this.gateName = gateName;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getGateName() {
        return gateName;
    }
}
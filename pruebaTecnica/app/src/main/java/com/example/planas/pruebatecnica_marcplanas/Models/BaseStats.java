package com.example.planas.pruebatecnica_marcplanas.Models;

public class BaseStats {

    private int value;
    private String name;

    public BaseStats(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public BaseStats(){

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

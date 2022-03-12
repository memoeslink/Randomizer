package com.memoeslink.common;

public class WeightedChar {
    private char value;
    private double weight;

    public WeightedChar() {
        value = '\0';
        weight = 0.0D;
    }

    public WeightedChar(char value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

package com.example.navdraw;

public class TaxSample {
    private String ID;
    private String name;
    private String location;
    private double TaxedIncome;
    private double PayedTax;

    @Override
    public String toString() {
        return "TaxSample{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", TaxedIncome=" + TaxedIncome +
                ", PayedTax=" + PayedTax +
                '}';
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTaxedIncome() {
        return TaxedIncome;
    }

    public void setTaxedIncome(double taxedIncome) {
        TaxedIncome = taxedIncome;
    }

    public double getPayedTax() {
        return PayedTax;
    }

    public void setPayedTax(double payedTax) {
        PayedTax = payedTax;
    }
}
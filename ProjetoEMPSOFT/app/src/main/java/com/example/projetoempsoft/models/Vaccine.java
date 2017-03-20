package com.example.projetoempsoft.models;

/**
 * Created by silvarthur on 3/19/17.
 */

public class Vaccine {

    String vaccineType;
    String veterinarian;
    String date;
    String returnDate;

    public Vaccine(String vaccineType, String veterinarian, String date, String returnDate) {
        this.vaccineType = vaccineType;
        this.veterinarian = veterinarian;
        this.date = date;
        this.returnDate = returnDate;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public String getDate() {
        return date;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}

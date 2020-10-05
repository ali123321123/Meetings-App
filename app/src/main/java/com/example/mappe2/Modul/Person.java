package com.example.mappe2.Modul;

public class Person {

    private String navn , telefonnr;
    private int img;

    public Person(String navn, String telefonnr, int img) {
        this.navn = navn;
        this.telefonnr = telefonnr;
        this.img = img;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}

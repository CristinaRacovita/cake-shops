package model;

import java.io.Serializable;

public class ContUser implements Serializable {
    private String numeUtilizator;
    private String parola;

    public ContUser(String nume, String pass){
        this.numeUtilizator = nume;
        this.parola = pass;
    }

    public void setNumeUtilizator(String nume){
        this.numeUtilizator = nume;
    }

    public void setParola(String pass){
        this.parola = pass;
    }

    public String getNumeUtilizator(){
        return this.numeUtilizator;
    }

    public String getParola(){
        return this.parola;
    }

    @Override
    public String toString() {
        return numeUtilizator + " " + parola;
    }
}

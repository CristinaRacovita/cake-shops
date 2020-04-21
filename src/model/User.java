package model;

import java.io.Serializable;

public class User extends Persoana implements Serializable {
    private String functie;

    @Override
    public String toString() {
        return functie + " " + nume + " " + prenume + " " + varsta + " " + dataAgajarii;
    }

    public User(Persoana persoana, String functie) {
        super(persoana.nume,persoana.prenume,persoana.varsta,persoana.dataAgajarii);
        this.functie = functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getFunctie() {
        return functie;
    }
}

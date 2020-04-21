package model;

import java.io.Serializable;

public class ContUserAngajat extends ContUser implements Serializable {
    public ContUserAngajat(String nume, String parola){
        super(nume+"@angajat.com",parola);
    }
}

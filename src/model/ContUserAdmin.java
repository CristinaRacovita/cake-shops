package model;

import java.io.Serializable;

public class ContUserAdmin extends ContUser implements Serializable {
    public ContUserAdmin(String nume, String pass){
        super(nume+"@admin.com",pass);
    }
}

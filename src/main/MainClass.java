package main;

import controller.*;
import model.ContUser;
import model.PersistentaConturi;
import model.PersistentaUser;
import model.User;
import view.*;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args){

        InterfataAutentificare a = new InterfataAutentificare();
        InterfataAngajat an = new InterfataAngajat();
        InterfataAdministrator ad = new InterfataAdministrator();
        InterfataPrajituri p = new InterfataPrajituri();
        InterfataEditarePrajitura pe = new InterfataEditarePrajitura();
        InterfataAdaugarePrajitura ap = new InterfataAdaugarePrajitura();
        InterfataAdaugareAngajat aa = new InterfataAdaugareAngajat();
        InterfataEditareAngajat ae = new InterfataEditareAngajat();

        ControlInterfataAutentificare ca = new ControlInterfataAutentificare(a,ad,an);
        ControlInterfataAngajat can = new ControlInterfataAngajat(an,a,p);
        ControlInterfataPrajituri cp = new ControlInterfataPrajituri(p,an,ap,pe,can);
        ControlInterfataAdministrator cad = new ControlInterfataAdministrator(ad,a,aa,ae);
        ControlInterfataAdaugareAngajat caa = new ControlInterfataAdaugareAngajat(aa,ad);
        ControlInterfataAdaugarePrajitura cap = new ControlInterfataAdaugarePrajitura(ap,p,can);
        ControlInterfataEditarePrajitura cep = new ControlInterfataEditarePrajitura(pe,p,can);
        ControlInterfataEditareAngajat cea = new ControlInterfataEditareAngajat(ad,ae);

        //de verificat contul si de sters ce scrii la autentificare

        /*PersistentaConturi persistentaConturi = new PersistentaConturi("conturi.bin");
        PersistentaUser persistentaUser = new PersistentaUser("salariati.bin");
        List<User> userList = persistentaUser.vizualizare();
        List<ContUser> contUsers = persistentaConturi.incarcare(userList);

        for(ContUser c: contUsers){
            System.out.println(c);
        }*/


    }
}



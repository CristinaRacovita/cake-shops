package controller;

import model.ContUser;
import model.PersistentaConturi;
import view.InterfataAdministrator;
import view.InterfataAngajat;
import view.InterfataAutentificare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControlInterfataAutentificare {
    private InterfataAutentificare interfataAutentificare;
    private InterfataAngajat interfataAngajat;
    private InterfataAdministrator interfataAdministrator;

    private static final String angajat = "@angajat";
    private static final String admin = "@admin";


    public ControlInterfataAutentificare(InterfataAutentificare interfataAutentificare, InterfataAdministrator interfataAdministrator, InterfataAngajat interfataAngajat){
        this.interfataAutentificare = interfataAutentificare;
        this.interfataAdministrator = interfataAdministrator;
        this.interfataAngajat = interfataAngajat;
        interfataAutentificare.addNextButtonListener(new AddNextButtonListener());
    }
    public class AddNextButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String numeUtilizator = interfataAutentificare.getNumeUtilizator();
            char[] parola = interfataAutentificare.getNumeParola();
            String pass = String.valueOf(parola);
            ContUser contUser= new ContUser(numeUtilizator,pass);
            boolean angajatFunctie = numeUtilizator.contains(angajat);
            boolean adminFunctie = numeUtilizator.contains(admin);
            PersistentaConturi persistentaConturi = new PersistentaConturi("conturi.bin");
            List<ContUser> contUserList = persistentaConturi.vizualizare();
            boolean ok = false;
            for(ContUser c :contUserList){
                if(c.getNumeUtilizator().equals(contUser.getNumeUtilizator())){
                    if(c.getParola().equals(c.getParola())){
                        ok = true;
                    }
                }
            }
            if(ok) {
                if (angajatFunctie) {
                    interfataAngajat.setVisible(true);
                    interfataAutentificare.setVisible(false);
                }
                if (adminFunctie) {
                    interfataAdministrator.setVisible(true);
                    interfataAutentificare.setVisible(false);
                }
            }
            else{
                JOptionPane.showMessageDialog(interfataAutentificare,"User sau parola incorecte!","Incercati din nou",JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}

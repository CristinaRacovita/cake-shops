package controller;

import model.ContUser;
import model.PersistentaUser;
import model.Persoana;
import model.User;
import view.InterfataAdministrator;
import view.InterfataEditareAngajat;
import view.InterfataEditarePrajitura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlInterfataEditareAngajat {
    InterfataAdministrator interfataAdministrator;
    InterfataEditareAngajat interfataEditareAngajat;

    public ControlInterfataEditareAngajat(InterfataAdministrator interfataAdministrator, InterfataEditareAngajat interfataEditareAngajat){
        this.interfataAdministrator = interfataAdministrator;
        this.interfataEditareAngajat = interfataEditareAngajat;

        interfataEditareAngajat.addInpoiListener(new InapoiListener());
    }

    public class InapoiListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = interfataEditareAngajat.getNumeText();
            String prenume = interfataEditareAngajat.getPrenumeText();
            String varsta = interfataEditareAngajat.getVarstaText();
            String dataAngajare = interfataEditareAngajat.getDataAngajarii();
            String[] row = {nume,prenume,varsta,dataAngajare};

            if(nume.compareTo("") != 0 && prenume.compareTo("") != 0 && varsta.compareTo("") != 0 && dataAngajare.compareTo("") != 0) {
                Persoana persoana = new Persoana(nume,prenume,Integer.parseInt(varsta),dataAngajare);
                User user = new User(persoana,"angajat");
                DefaultTableModel model = (DefaultTableModel) interfataAdministrator.getTable().getModel();
                model.addRow(row);
                ContUser contUser = new PersistentaUser("salariati.bin").salvare(user);
                String[] option = {"OK"};
                int result = JOptionPane.showOptionDialog(null,"Noul nume utilizator: "+ contUser.getNumeUtilizator()+ "\nNoua parola: "+ contUser.getParola(),"S-a editat cu succes!",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
                if(result == JOptionPane.OK_OPTION) {
                    interfataEditareAngajat.setVisible(false);
                    interfataEditareAngajat.setNume("");
                    interfataEditareAngajat.setPreume("");
                    interfataEditareAngajat.setVarsta("");
                    interfataEditareAngajat.setDataAngajarii("");
                    interfataAdministrator.setVisible(true);
                }
            }
            else{
                JOptionPane.showMessageDialog(interfataEditareAngajat,"Toate campurile trebuie completate!","Eroare",JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}

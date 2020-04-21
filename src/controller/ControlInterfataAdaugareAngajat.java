package controller;

import model.ContUser;
import model.PersistentaUser;
import model.Persoana;
import model.User;
import view.InterfataAdaugareAngajat;
import view.InterfataAdministrator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlInterfataAdaugareAngajat {
    InterfataAdaugareAngajat interfataAdaugareAngajat;
    InterfataAdministrator interfataAdministrator;

    public ControlInterfataAdaugareAngajat(InterfataAdaugareAngajat interfataAdaugareAngajat, InterfataAdministrator interfataAdministrator){
        this.interfataAdaugareAngajat = interfataAdaugareAngajat;
        this.interfataAdministrator = interfataAdministrator;

        interfataAdaugareAngajat.addInpoiListener(new FinishListener());
    }

    public class FinishListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = interfataAdaugareAngajat.getNumeText();
            String prenume = interfataAdaugareAngajat.getPrenumeText();
            String varsta = interfataAdaugareAngajat.getVarstaText();
            String dataAngajare = interfataAdaugareAngajat.getDataAngajarii();
            String[] row = {nume,prenume,varsta,dataAngajare};

            if(nume.compareTo("") != 0 && prenume.compareTo("") != 0 && varsta.compareTo("") != 0 && dataAngajare.compareTo("") != 0) {
                Persoana persoana = new Persoana(nume,prenume,Integer.parseInt(varsta),dataAngajare);
                User user = new User(persoana,"angajat");
                DefaultTableModel model = (DefaultTableModel) interfataAdministrator.getTable().getModel();
                model.addRow(row);
                ContUser contUser = new PersistentaUser("salariati.bin").salvare(user);
                String[] option = {"OK"};
                int result = JOptionPane.showOptionDialog(null,"Nume utilizator: "+ contUser.getNumeUtilizator()+ "\nParola: "+ contUser.getParola(),"Angajat adaugat cu succes!",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
                if(result == JOptionPane.OK_OPTION) {
                    interfataAdaugareAngajat.setVisible(false);
                    interfataAdaugareAngajat.setNume("");
                    interfataAdaugareAngajat.setPreume("");
                    interfataAdaugareAngajat.setVarsta("");
                    interfataAdaugareAngajat.setDataAngajarii("");
                    interfataAdministrator.setVisible(true);
                }
            }
            else{
                JOptionPane.showMessageDialog(interfataAdaugareAngajat,"Toate campurile trebuie completate!","Eroare",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

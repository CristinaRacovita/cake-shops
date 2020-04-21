package controller;

import model.*;
import view.InterfataAdaugareAngajat;
import view.InterfataAdministrator;
import view.InterfataAutentificare;
import view.InterfataEditareAngajat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlInterfataAdministrator {
    InterfataAdministrator interfataAdministrator;
    InterfataAutentificare interfataAutentificare;
    InterfataAdaugareAngajat interfataAdaugareAngajat;
    InterfataEditareAngajat interfataEditareAngajat;

    public ControlInterfataAdministrator(InterfataAdministrator interfataAdministrator, InterfataAutentificare interfataAutentificare, InterfataAdaugareAngajat interfataAdaugareAngajat,InterfataEditareAngajat interfataEditareAngajat) {
        this.interfataAdministrator = interfataAdministrator;
        this.interfataAutentificare = interfataAutentificare;
        this.interfataAdaugareAngajat = interfataAdaugareAngajat;
        this.interfataEditareAngajat = interfataEditareAngajat;

        interfataAdministrator.setInapoiListener(new InapoiListener());
        interfataAdministrator.setAdaugaListener(new AdaugaListener());
        interfataAdministrator.addDelteListener(new DeleteListener());
        interfataAdministrator.addVizListener(new VizListener());
        interfataAdministrator.addEditListener(new EditListener());
    }

    public class EditListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel angajati = (DefaultTableModel) interfataAdministrator.getTable().getModel();
            int index = interfataAdministrator.getTable().getSelectedRow();
            int modelIndex = interfataAdministrator.getTable().convertRowIndexToModel(index);

            String nume = (String) angajati.getValueAt(modelIndex,0);
            String prenume = (String) angajati.getValueAt(modelIndex,1);
            String varsta = (String) angajati.getValueAt(modelIndex,2);
            String dataAngajare = (String) angajati.getValueAt(modelIndex,3);

            Persoana persoana = new Persoana(nume,prenume,Integer.parseInt(varsta),dataAngajare);
            User user = new User(persoana,"@angajat");
            try {
                new PersistentaUser("salariati.bin").stergere(user);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if(modelIndex!= -1){
                angajati.removeRow(modelIndex);
            }

            interfataEditareAngajat.setNume(nume);
            interfataEditareAngajat.setPreume(prenume);
            interfataEditareAngajat.setVarsta(varsta);
            interfataEditareAngajat.setDataAngajarii(dataAngajare);
            interfataAdministrator.setVisible(false);
            interfataEditareAngajat.setVisible(true);
        }
    }

    public class InapoiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            interfataAutentificare.setNumeUtilizator("");
            interfataAutentificare.setParola("");
            interfataAdministrator.setVisible(false);
            interfataAutentificare.setVisible(true);
        }
    }

    public class AdaugaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            interfataAdministrator.setVisible(false);
            interfataAdaugareAngajat.setVisible(true);
        }
    }

    public class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel angajati = (DefaultTableModel) interfataAdministrator.getTable().getModel();
            PersistentaUser persistentaUser = new PersistentaUser("salariati.bin");
            int index = interfataAdministrator.getTable().getSelectedRow();
            if(index != -1){
                int modelIndex = interfataAdministrator.getTable().convertRowIndexToModel(index);
                Persoana persoana = new Persoana((String)angajati.getValueAt(modelIndex,0),(String)angajati.getValueAt(modelIndex,1),Integer.parseInt((String) angajati.getValueAt(modelIndex,2)),(String)angajati.getValueAt(modelIndex,3));
                User user = new User(persoana,"angajat");
                try {
                    persistentaUser.stergere(user);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                angajati.removeRow(modelIndex);
            }
            else{
                System.out.println("NIMIC\n");
            }
        }
    }

    public class VizListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> userLista;
            userLista = (new PersistentaUser("salariati.bin").vizualizareAngajati());
            String[][] rows = formRows(userLista);
            interfataAdministrator.tableFiller(rows,userLista.size());
        }
    }

    private String[][] formRows(List<User> userLista){
        String[][] rows = new String[100][100];
        List<String> lista = new ArrayList<String>();
        for(int i = 0; i < userLista.size(); i+=1){
            lista.add(userLista.get(i).getNume());
            lista.add(userLista.get(i).getPrenume());
            lista.add(String.valueOf(userLista.get(i).getVarsta()));
            lista.add(userLista.get(i).getDataAgajarii());
        }
        int i = 0;
        for(int k = 0; k < lista.size()/4; k++) {
            for (int j = 0; j < 4; j++) {
                rows[k][j] = lista.get(i);
                i++;
            }
        }
        return rows;
    }
}

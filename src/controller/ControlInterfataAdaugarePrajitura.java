package controller;

import model.PersistentaPrajitura;
import model.Prajitura;
import view.InterfataAdaugarePrajitura;
import view.InterfataPrajituri;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlInterfataAdaugarePrajitura {
    private InterfataAdaugarePrajitura interfataAdaugarePrajitura;
    private InterfataPrajituri interfataPrajituri;
    private ControlInterfataAngajat controlInterfataAngajat;

    public ControlInterfataAdaugarePrajitura(InterfataAdaugarePrajitura interfataAdaugarePrajitura, InterfataPrajituri interfataPrajituri,ControlInterfataAngajat controlInterfataAngajat){
        this.interfataAdaugarePrajitura = interfataAdaugarePrajitura;
        this.interfataPrajituri = interfataPrajituri;
        this.controlInterfataAngajat = controlInterfataAngajat;

        interfataAdaugarePrajitura.addInpoiListener(new FinishListener());
    }

    public class FinishListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String denumire = interfataAdaugarePrajitura.getDenumireText();
            String pret = interfataAdaugarePrajitura.getPretText();
            String disponibilitate = interfataAdaugarePrajitura.getDisponibilitateText();
            String valabilitate = interfataAdaugarePrajitura.getValabilitateText();

            boolean disp = false;

            if(disponibilitate.contains("pe stoc")){
                disp = true;
            }

            String file = controlInterfataAngajat.getPersistentaPrajitura();
            String nume = "";
            switch (file){
                case "cofetarie1.bin":
                    nume = "Zahar Ars";
                    break;
                case "cofetarie2.bin":
                    nume = "Deliciu";
                    break;
                case "cofetarie3.bin":
                    nume = "Viata Dulce";
                    break;
            }
            String[] row = {nume,denumire,pret,disponibilitate,valabilitate};
            Prajitura prajitura = new Prajitura(nume,denumire,Float.parseFloat(pret),disp,valabilitate);
            new PersistentaPrajitura(controlInterfataAngajat.getPersistentaPrajitura()).salvare(prajitura);

            if(denumire.compareTo("") != 0 && pret.compareTo("") != 0 && disponibilitate.compareTo("") != 0 && valabilitate.compareTo("") != 0) {
                DefaultTableModel model = (DefaultTableModel) interfataPrajituri.getTable().getModel();
                model.addRow(row);

                interfataAdaugarePrajitura.setVisible(false);

                interfataAdaugarePrajitura.setNume("");
                interfataAdaugarePrajitura.setPret("");
                interfataAdaugarePrajitura.setDisponibilitate("");
                interfataAdaugarePrajitura.setValabilitate("");

                interfataPrajituri.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(interfataAdaugarePrajitura,"Toate campurile trebuie completate!","Eroare",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

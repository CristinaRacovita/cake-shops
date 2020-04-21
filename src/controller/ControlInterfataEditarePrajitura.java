package controller;

import model.PersistentaPrajitura;
import model.Prajitura;
import view.InterfataEditarePrajitura;
import view.InterfataPrajituri;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlInterfataEditarePrajitura {
    InterfataEditarePrajitura interfataEditarePrajitura;
    InterfataPrajituri interfataPrajituri;
    ControlInterfataAngajat controlInterfataAngajat;

    public ControlInterfataEditarePrajitura(InterfataEditarePrajitura interfataEditarePrajitura, InterfataPrajituri interfataPrajituri,ControlInterfataAngajat controlInterfataAngajat){
        this.interfataEditarePrajitura = interfataEditarePrajitura;
        this.interfataPrajituri = interfataPrajituri;
        this.controlInterfataAngajat=controlInterfataAngajat;

        interfataEditarePrajitura.addInpoiListener(new FinishListener());
    }

    public class FinishListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String denumire = interfataEditarePrajitura.getDenumireText();
            String pret = interfataEditarePrajitura.getPretText();
            String disponibilitate = interfataEditarePrajitura.getDisponibilitateText();
            String valabilitate = interfataEditarePrajitura.getValabilitateText();
            String numefisier = controlInterfataAngajat.getPersistentaPrajitura();

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
            new PersistentaPrajitura(numefisier).salvare(prajitura);

            if(denumire.compareTo("") != 0 && pret.compareTo("") != 0 && disponibilitate.compareTo("") != 0 && valabilitate.compareTo("") != 0) {
                DefaultTableModel model = (DefaultTableModel) interfataPrajituri.getTable().getModel();
                model.addRow(row);

                interfataEditarePrajitura.setVisible(false);

                interfataEditarePrajitura.setNume("");
                interfataEditarePrajitura.setPret("");
                interfataEditarePrajitura.setDisponibilitate("");
                interfataEditarePrajitura.setValabilitate("");

                interfataPrajituri.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(interfataEditarePrajitura,"Toate campurile trebuie completate!","Eroare",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

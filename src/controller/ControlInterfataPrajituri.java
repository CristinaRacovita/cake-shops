package controller;

import model.PersistentaPrajitura;
import model.PersistentaUser;
import model.Prajitura;
import view.InterfataAdaugarePrajitura;
import view.InterfataAngajat;
import view.InterfataEditarePrajitura;
import view.InterfataPrajituri;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlInterfataPrajituri {
    private InterfataPrajituri interfataPrajituri;
    private InterfataAngajat interfataAngajat;
    private InterfataAdaugarePrajitura interfataAdaugarePrajitura;
    private InterfataEditarePrajitura interfataEditarePrajitura;
    private ControlInterfataAngajat controlInterfataAngajat;

    public ControlInterfataPrajituri(InterfataPrajituri interfataPrajituri,InterfataAngajat interfataAngajat,InterfataAdaugarePrajitura interfataAdaugarePrajitura,InterfataEditarePrajitura interfataEditarePrajitura,ControlInterfataAngajat controlInterfataAngajat){
        this.interfataPrajituri = interfataPrajituri;
        this.interfataAngajat = interfataAngajat;
        this.interfataAdaugarePrajitura = interfataAdaugarePrajitura;
        this.interfataEditarePrajitura = interfataEditarePrajitura;
        this.controlInterfataAngajat = controlInterfataAngajat;

        interfataPrajituri.addComboListener(new ActionComboListener());
        interfataPrajituri.getMinim().addMouseListener(new MinimMouseListener());
        interfataPrajituri.getMaxim().addMouseListener(new MaximMouseListener());
        interfataPrajituri.getData().addMouseListener(new DataMouseListener());
        interfataPrajituri.setInapoiButon(new InapoiListener());
        interfataPrajituri.getCautareText().addMouseListener(new NumeMouseListener());
        interfataPrajituri.setAdaugareButon(new AdaugaListener());
        interfataPrajituri.addDeleteListener(new DeleteListener());
        interfataPrajituri.addEditListener(new EditListener());

    }
    public class ActionComboListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectare = interfataPrajituri.getCombo();
            switch (selectare){
                case "Pret":
                    interfataPrajituri.deletePanel();
                    JTextField minim = interfataPrajituri.getMinim();
                    JTextField maxim = interfataPrajituri.getMaxim();

                    JPanel panelMinim = new JPanel();
                    panelMinim.add(minim);

                    JPanel panelCriteriiText = new JPanel();

                    JPanel panelMaxim = new JPanel();
                    panelMaxim.add(maxim);

                    panelCriteriiText.add(panelMinim);
                    panelCriteriiText.add(panelMaxim);
                    panelCriteriiText.setLayout(new BoxLayout(panelCriteriiText, BoxLayout.X_AXIS));
                    interfataPrajituri.addPanel(panelCriteriiText);
                    String[] pretMin = new String[1];
                    String[] pretMax = new String[1];
                    minim.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            pretMin[0] = minim.getText();
                            pretMax[0] = maxim.getText();
                            if(pretMax[0]!= null || pretMin[0] != null) {
                                List<Prajitura> prajituraLista;
                                if(!controlInterfataAngajat.getPersistentaPrajitura().equals("")) {
                                    prajituraLista = (new PersistentaPrajitura(controlInterfataAngajat.getPersistentaPrajitura())).vizualizare(Integer.parseInt(pretMin[0]), Integer.parseInt(pretMax[0]));
                                }else{

                                    prajituraLista = new PersistentaPrajitura("cofetarie1.bin").vizualizare(Integer.parseInt(pretMin[0]), Integer.parseInt(pretMax[0]));
                                    prajituraLista.addAll(new PersistentaPrajitura("cofetarie2.bin").vizualizare(Integer.parseInt(pretMin[0]), Integer.parseInt(pretMax[0])));
                                    prajituraLista.addAll(new PersistentaPrajitura("cofetarie3.bin").vizualizare(Integer.parseInt(pretMin[0]), Integer.parseInt(pretMax[0])));
                                }
                                String[][] rows1 = formRows(prajituraLista);
                                interfataPrajituri.tableFiller(rows1, prajituraLista.size());
                            }
                        }
                    });
                    maxim.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            pretMin[0] = minim.getText();
                            pretMax[0] = maxim.getText();
                            if(pretMax[0]!= null || pretMin[0] != null) {
                                List<Prajitura> prajituraLista;
                                if(!controlInterfataAngajat.getPersistentaPrajitura().equals("")) {
                                    prajituraLista = (new PersistentaPrajitura(controlInterfataAngajat.getPersistentaPrajitura())).vizualizare(Integer.parseInt(pretMin[0]), Integer.parseInt(pretMax[0]));
                                }else{
                                    prajituraLista = new PersistentaPrajitura("cofetarie1.bin").vizualizare(Integer.parseInt(pretMin[0]), Integer.parseInt(pretMax[0]));
                                    prajituraLista.addAll(new PersistentaPrajitura("cofetarie2.bin").vizualizare(Integer.parseInt(pretMin[0]), Integer.parseInt(pretMax[0])));
                                    prajituraLista.addAll(new PersistentaPrajitura("cofetarie3.bin").vizualizare(Integer.parseInt(pretMin[0]), Integer.parseInt(pretMax[0])));
                                }
                                String[][] rows1 = formRows(prajituraLista);
                                interfataPrajituri.tableFiller(rows1, prajituraLista.size());
                            }
                        }
                    });
                    break;
                case "Valabilitate":
                    interfataPrajituri.deletePanel();
                    JTextField data = interfataPrajituri.getData();
                    JPanel panelData = new JPanel();
                    panelData.add(data);
                    interfataPrajituri.addPanel(panelData);
                    final String[] dataText = new String[1];
                    data.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dataText[0] = data.getText();
                            List<Prajitura> prajituraLista;
                            if(!controlInterfataAngajat.getPersistentaPrajitura().equals("")) {
                                prajituraLista = (new PersistentaPrajitura(controlInterfataAngajat.getPersistentaPrajitura())).vizualizare(dataText[0], false);
                            }else{
                                prajituraLista = new PersistentaPrajitura("cofetarie1.bin").vizualizare(dataText[0], false);
                                prajituraLista.addAll(new PersistentaPrajitura("cofetarie2.bin").vizualizare(dataText[0], false));
                                prajituraLista.addAll(new PersistentaPrajitura("cofetarie3.bin").vizualizare(dataText[0], false));
                            }
                            String[][] rows1 = formRows(prajituraLista);
                            interfataPrajituri.tableFiller(rows1,prajituraLista.size());
                        }
                    });
                    break;
                case "Nume":
                    interfataPrajituri.deletePanel();
                    JTextField nume = interfataPrajituri.getCautareText();
                    JPanel panelNume = new JPanel();
                    panelNume.add(nume);
                    interfataPrajituri.addPanel(panelNume);
                    final String[] numeText = new String[1];
                    nume.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            numeText[0] = nume.getText();
                            List<Prajitura> prajituraLista;
                            if(!controlInterfataAngajat.getPersistentaPrajitura().equals("")) {
                                prajituraLista = (new PersistentaPrajitura(controlInterfataAngajat.getPersistentaPrajitura())).vizualizare(numeText[0], true);
                            }else{
                                prajituraLista = new PersistentaPrajitura("cofetarie1.bin").vizualizare(numeText[0], true);
                                prajituraLista.addAll(new PersistentaPrajitura("cofetarie2.bin").vizualizare(numeText[0], true));
                                prajituraLista.addAll(new PersistentaPrajitura("cofetarie3.bin").vizualizare(numeText[0], true));
                            }
                            String[][] rows1 = formRows(prajituraLista);
                            interfataPrajituri.tableFiller(rows1,prajituraLista.size());
                        }
                    });

                    break;
                case "Doar vizualizare":
                    interfataPrajituri.deletePanel();
                    List<Prajitura> prajituraLista;
                    if(!controlInterfataAngajat.getPersistentaPrajitura().equals("")) {
                        prajituraLista = (new PersistentaPrajitura(controlInterfataAngajat.getPersistentaPrajitura())).vizualizare();
                    }else{
                        prajituraLista = new PersistentaPrajitura("cofetarie1.bin").vizualizare();
                        prajituraLista.addAll(new PersistentaPrajitura("cofetarie2.bin").vizualizare());
                        prajituraLista.addAll(new PersistentaPrajitura("cofetarie3.bin").vizualizare());
                    }
                    String[][] rows = formRows(prajituraLista);
                    interfataPrajituri.tableFiller(rows,prajituraLista.size());
                    break;
                case "Disponibilitate":
                    interfataPrajituri.deletePanel();
                    List<Prajitura> prajituraLista1;
                    if(!controlInterfataAngajat.getPersistentaPrajitura().equals("")) {
                        prajituraLista1 = (new PersistentaPrajitura(controlInterfataAngajat.getPersistentaPrajitura())).vizualizare(true);
                    }else{
                        prajituraLista1 = new PersistentaPrajitura("cofetarie1.bin").vizualizare(true);
                        prajituraLista1.addAll(new PersistentaPrajitura("cofetarie2.bin").vizualizare(true));
                        prajituraLista1.addAll(new PersistentaPrajitura("cofetarie3.bin").vizualizare(true));
                    }
                    String[][] rows2 = formRows(prajituraLista1);
                    interfataPrajituri.tableFiller(rows2,prajituraLista1.size());
                    break;
                default:
                    interfataPrajituri.deletePanel();
                    break;
            }
            interfataPrajituri.getContentPane().validate();
            interfataPrajituri.getContentPane().repaint();
        }
    }
    public class MinimMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            interfataPrajituri.setTextMinim("");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public class MaximMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            interfataPrajituri.setTextMaxim("");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public class DataMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            interfataPrajituri.setTextData("");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public class NumeMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            interfataPrajituri.setCautareText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public class InapoiListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String[][] row ={{}};
            interfataPrajituri.setCombo();
            interfataPrajituri.tableFiller(row,0);
            interfataPrajituri.setVisible(false);
            interfataAngajat.setVisible(true);
        }
    }
    public class AdaugaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(controlInterfataAngajat.getPersistentaPrajitura().equals("")){
                JOptionPane.showMessageDialog(interfataPrajituri,"In acest mod de vizualizare nu se pot adauga produse!\n                    Va rugam selectati altul!","Eroare",JOptionPane.ERROR_MESSAGE);
            }else {
                interfataPrajituri.setVisible(false);
                interfataAdaugarePrajitura.setVisible(true);
            }
        }
    }
    public class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel prajituri = (DefaultTableModel) interfataPrajituri.getTable().getModel();
            int index = interfataPrajituri.getTable().getSelectedRow();
            if(index != -1){
                int modelIndex = interfataPrajituri.getTable().convertRowIndexToModel(index);
                Prajitura prajitura = new Prajitura();
                prajitura.setNumePrajitura((String) prajituri.getValueAt(modelIndex,1));
                prajitura.setPret(Float.parseFloat((String)prajituri.getValueAt(modelIndex,2)));
                if(((String) prajituri.getValueAt(modelIndex,3)).compareTo("pe stoc")==0){
                    prajitura.setDisponibilitateProdus(true);
                }
                else{
                    prajitura.setDisponibilitateProdus(false);
                }
                prajitura.setValabilitate((String) prajituri.getValueAt(modelIndex,4));

                prajituri.removeRow(modelIndex);
                try {
                    (new PersistentaPrajitura(controlInterfataAngajat.getPersistentaPrajitura())).stergere(prajitura);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else{
                System.out.println("Nu ati selectat nimic!");
            }
        }
    }
    private String[][] formRows(List<Prajitura> prajituraLista){
        String[][] rows = new String[100][100];
        List<String> lista = new ArrayList<String>();
        for(int i = 0; i < prajituraLista.size(); i+=1){
            lista.add(prajituraLista.get(i).getNumeCofetarie());
            lista.add(prajituraLista.get(i).getNumePrajitura());
            lista.add(prajituraLista.get(i).getPret()+"");
            if(prajituraLista.get(i).isDisponibilitateProdus()) {
                lista.add(new String("pe stoc"));
            }
            else{
                lista.add(new String("stoc epuizat"));
            }
            lista.add(prajituraLista.get(i).getValabilitate());
        }
        int i = 0;
        for(int k = 0; k < lista.size()/5; k++) {
            for (int j = 0; j < 5; j++) {
                rows[k][j] = lista.get(i);
                i++;
            }
        }
        return rows;
    }
    public class EditListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel prajituri = (DefaultTableModel) interfataPrajituri.getTable().getModel();
            int index = interfataPrajituri.getTable().getSelectedRow();
            if(controlInterfataAngajat.getPersistentaPrajitura().equals("")){
                JOptionPane.showMessageDialog(interfataPrajituri,"In acest mod de vizualizare nu se pot face editari!\n                    Va rugam selectati altul!","Eroare",JOptionPane.ERROR_MESSAGE);
            }else {
                if (index != -1) {
                    int modelIndex = interfataPrajituri.getTable().convertRowIndexToModel(index);
                    boolean disp = false;
                    if(((String) prajituri.getValueAt(modelIndex, 3)).equals("pe stoc")){
                        disp = true;
                    }
                    Prajitura prajitura = new Prajitura((String) prajituri.getValueAt(modelIndex, 0),(String) prajituri.getValueAt(modelIndex, 1),Float.parseFloat((String) prajituri.getValueAt(modelIndex, 2)),disp,(String) prajituri.getValueAt(modelIndex, 4));
                    interfataEditarePrajitura.setNume((String) prajituri.getValueAt(modelIndex, 1));
                    interfataEditarePrajitura.setPret((String) prajituri.getValueAt(modelIndex, 2));
                    interfataEditarePrajitura.setDisponibilitate((String) prajituri.getValueAt(modelIndex, 3));
                    interfataEditarePrajitura.setValabilitate((String) prajituri.getValueAt(modelIndex, 4));
                    interfataPrajituri.setVisible(false);
                    prajituri.removeRow(modelIndex);
                    try {
                        new PersistentaPrajitura(controlInterfataAngajat.getPersistentaPrajitura()).stergere(prajitura);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    interfataEditarePrajitura.setVisible(true);
                }
            }

        }
    }
}


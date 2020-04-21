package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;


public class InterfataPrajituri extends JFrame {

    private String[] criterii = {" ","Doar vizualizare","Pret","Nume","Disponibilitate","Valabilitate"};
    private JLabel filtru = new JLabel("Filtrare dupa: ");
    private JComboBox criteriiFiltrare = new JComboBox(criterii);

    private String[] columnNames = {"Cofetarie","Nume prajitura", "Pret", "Disponibilitate", "Valabilitate"};
    private Object[][] data = {
            {},
    };
    private TableModel tableModel = new DefaultTableModel(data,columnNames);
    private JTable prajituri = new JTable(tableModel);

    private JTextField cautareText = new JTextField("denumire",10);
    private JButton adaugareButon = new JButton("Adauga prajitura");
    private JButton inapoi = new JButton("Inapoi");

    private JPanel criteriu = new JPanel();
    private JTextField minim = new JTextField("Min",5);
    private JTextField maxim = new JTextField("Max",5);
    private JTextField dataExp = new JTextField("limita",10);

    private JMenuItem deleteItem = new JMenuItem("Stergere");
    private JMenuItem editItem = new JMenuItem("Editeaza");

    public InterfataPrajituri(){
        this.setTitle("Prajituri");
        this.setSize(800,700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JPopupMenu popupMenu = new JPopupMenu();

        popupMenu.add(deleteItem);
        popupMenu.add(editItem);

        prajituri.setBounds(10,20,700,600);
        prajituri.setComponentPopupMenu(popupMenu);
        JScrollPane sp=new JScrollPane(prajituri);

        JPanel panelFiltru = new JPanel();
        panelFiltru.add(filtru);

        JPanel panelCriterii = new JPanel();
        panelCriterii.add(criteriiFiltrare);

        JPanel panelFiltrare = new JPanel();
        panelFiltrare.add(panelFiltru);
        panelFiltrare.add(panelCriterii);
        panelFiltrare.add(criteriu);
        panelFiltrare.setLayout(new BoxLayout(panelFiltrare, BoxLayout.X_AXIS));

        JPanel panelTabel = new JPanel();
        panelTabel.add(sp);

        JPanel panelCautareButon =  new JPanel();
        panelCautareButon.add(adaugareButon);

        JPanel panelButonInapoi = new JPanel();
        panelButonInapoi.add(inapoi);

        JPanel panelButoane = new JPanel();
        panelButoane.add(panelButonInapoi);
        panelButoane.add(panelCautareButon);
        panelButoane.setLayout(new BoxLayout(panelButoane, BoxLayout.X_AXIS));

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelFiltrare);
        panelTotal.add(panelTabel);
        panelTotal.add(panelButoane);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));
        this.add(panelTotal);
    }

    public void addPanel(JPanel p){
        criteriu.add(p);
    }
    public void deletePanel(){
        criteriu.removeAll();
    }
    public String getCombo(){
        return criteriiFiltrare.getSelectedItem().toString();
    }
    public void setCombo() { this.criteriiFiltrare.setSelectedIndex(0);}
    public void addComboListener (ActionListener e){
        criteriiFiltrare.addActionListener(e);
    }
    public void setTextMinim(String s){
        minim.setText(s);
    }
    public void setTextMaxim(String s){
        maxim.setText(s);
    }
    public void setTextData(String s){
        dataExp.setText(s);
    }
    public void setCautareText(String s){
        cautareText.setText(s);
    }
    public JTextField getMinim(){
        return minim;
    }
    public JTextField getMaxim(){
        return maxim;
    }
    public JTextField getData(){
        return dataExp;
    }
    public JTextField getCautareText(){
        return cautareText;
    }
    public void setInapoiButon(ActionListener a){
        inapoi.addActionListener(a);
    }
    public void setAdaugareButon(ActionListener a){ adaugareButon.addActionListener(a); }
    public void addDeleteListener(ActionListener a){ deleteItem.addActionListener(a); }
    public JTable getTable(){ return prajituri; }
    public void tableFiller(String[][] rows,int n){
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnCount(5);
        modelTable.setColumnIdentifiers(columnNames);
        int i = 0;
        while(i != n ){
            modelTable.addRow(rows[i]);
            i++;
        }
        prajituri.setModel(modelTable);
    }
    public void addEditListener(ActionListener a){
        editItem.addActionListener(a);
    }
}

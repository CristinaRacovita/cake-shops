package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfataAdministrator extends JFrame {

    private String[] columnNames = {"Nume", "Prenume", "Varsta", "Data Angajarii"};
    private Object[][] data = {{}};

    private TableModel tableModel = new DefaultTableModel(data,columnNames);
    private JTable angajati = new JTable(tableModel);

    private JButton adauga = new JButton("Adaugare angajat");
    private JButton inapoi = new JButton("Inapoi");
    private JButton vizualizare = new JButton("Vizualizare Angajati");

    private JMenuItem delete = new JMenuItem("Stergere");
    private JMenuItem edit = new JMenuItem("Editare");

    public InterfataAdministrator(){
        this.setTitle("Administrator");
        this.setSize(600,400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);


        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(delete);
        popupMenu.add(edit);

        angajati.setComponentPopupMenu(popupMenu);
        angajati.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(angajati);

        JPanel panelVizualizare = new JPanel();
        panelVizualizare.add(vizualizare);

        JPanel panelInapoi = new JPanel();
        panelInapoi.add(inapoi);

        JPanel panelAdaugare = new JPanel();
        panelAdaugare.add(adauga);

        JPanel panelButoane = new JPanel();
        panelButoane.add(panelInapoi);
        panelButoane.add(panelVizualizare);
        panelButoane.add(panelAdaugare);
        panelButoane.setLayout(new BoxLayout(panelButoane, BoxLayout.X_AXIS));

        JPanel panelTabel = new JPanel();
        panelTabel.add(sp);

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelTabel);
        panelTotal.add(panelButoane);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));

        this.add(panelTotal);
    }

    public void setInapoiListener (ActionListener a){
        inapoi.addActionListener(a);
    }

    public void setAdaugaListener (ActionListener a){
        adauga.addActionListener(a);
    }

    public void addDelteListener (ActionListener a){
        delete.addActionListener(a);
    }

    public void addEditListener (ActionListener a){
        edit.addActionListener(a);
    }

    public void addVizListener (ActionListener a){
        vizualizare.addActionListener(a);
    }

    public JTable getTable (){
        return angajati;
    }

    public void tableFiller(String[][] rows,int n){
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnCount(4);
        modelTable.setColumnIdentifiers(columnNames);
        int i = 0;
        while(i != n ){
            modelTable.addRow(rows[i]);
            i++;
        }
        angajati.setModel(modelTable);
    }
}

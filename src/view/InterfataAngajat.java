package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfataAngajat extends JFrame {

    private JComboBox vizPrj = new JComboBox(new String[]{"Zahar Ars","Deliciu","Viata Dulce","Toate"});
    private JButton vizualizarePrajituri = new JButton("OK");
    private JButton inapoi = new JButton("Inapoi");
    private JButton vizualizareStatistici = new JButton("OK");
    private JButton salvare = new JButton("OK");
    private JComboBox cofetarieAleasaRapoarte = new JComboBox(new String[]{"Zahar Ars","Deliciu","Viata Dulce","Toate"});
    private JComboBox cofetarieAleasaStatistici = new JComboBox(new String[]{"Zahar Ars","Deliciu","Viata Dulce","Toate"});
    private JTextField format = new JTextField(5);


    public InterfataAngajat(){
        this.setTitle("Angajat");
        this.setSize(600,400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JPanel vizText = new JPanel();
        vizText.add(vizPrj);

        JPanel vizLabel = new JPanel();
        JLabel vizualizare = new JLabel("Vizualizare prajituri cofetarie");
        vizLabel.add(vizualizare);

        JPanel vizButon = new JPanel();
        vizButon.add(vizualizarePrajituri);

        JPanel panelVizualizare = new JPanel();
        panelVizualizare.add(vizLabel);
        panelVizualizare.add(vizText);
        panelVizualizare.add(vizButon);
        panelVizualizare.setLayout(new BoxLayout(panelVizualizare, BoxLayout.X_AXIS));

        JLabel statistici = new JLabel("Vizualizare statistici ale cofetariei: ");
        JPanel labelStatistici = new JPanel();
        labelStatistici.add(statistici);

        JPanel panelStatisticiButon = new JPanel();
        panelStatisticiButon.add(vizualizareStatistici);

        JPanel panelStatisticiCombo = new JPanel();
        panelStatisticiCombo.add(cofetarieAleasaStatistici);

        JPanel panelStatistici = new JPanel();
        panelStatistici.add(labelStatistici);
        panelStatistici.add(panelStatisticiCombo);
        panelStatistici.add(panelStatisticiButon);
        panelStatistici.setLayout(new BoxLayout(panelStatistici, BoxLayout.X_AXIS));


        JPanel panelInapoi = new JPanel();
        panelInapoi.add(inapoi);

        JPanel panelSalvare = new JPanel();
        JLabel salvarePrajitura = new JLabel("Salvare rapoarte ale cofetariei: ");
        panelSalvare.add(salvarePrajitura);

        JPanel panelCofetarieRapoarte = new JPanel();
        panelCofetarieRapoarte.add(cofetarieAleasaRapoarte);

        JPanel panelSalvareLabel = new JPanel();
        JLabel formatLabel = new JLabel("in format: ");
        panelSalvareLabel.add(formatLabel);

        JPanel panelFormat = new JPanel();
        panelFormat.add(format);

        JPanel panelSalvareButon = new JPanel();
        panelSalvareButon.add(salvare);

        JPanel panelSalvareFormat = new JPanel();
        panelSalvareFormat.add(panelSalvare);
        panelSalvareFormat.add(panelCofetarieRapoarte);
        panelSalvareFormat.add(panelSalvareLabel);
        panelSalvareFormat.add(panelFormat);
        panelSalvareFormat.add(panelSalvareButon);
        panelSalvareFormat.setLayout(new BoxLayout(panelSalvareFormat, BoxLayout.X_AXIS));

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelVizualizare);
        panelTotal.add(panelStatistici);
        panelTotal.add(panelSalvareFormat);
        panelTotal.add(panelInapoi);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));

        this.add(panelTotal);
    }

    public void addInapoiListener(ActionListener addInapoiListener) {
        inapoi.addActionListener(addInapoiListener);
    }

    public void addVizualizareListener(ActionListener addVizualizareListener) {
        vizualizarePrajituri.addActionListener(addVizualizareListener);
    }

    public void addSaveListener (ActionListener a) { salvare.addActionListener(a); }

    public String getFormatText() { return format.getText(); }

    public void addListenetStatistici(ActionListener a){
        vizualizareStatistici.addActionListener(a);
    }
    public String getVizualizare(){
        return vizPrj.getSelectedItem().toString();
    }
    public String getVizualizareRaport(){
        return cofetarieAleasaRapoarte.getSelectedItem().toString();
    }
    public String getVizualizareStatistica(){
        return cofetarieAleasaStatistici.getSelectedItem().toString();
    }

}

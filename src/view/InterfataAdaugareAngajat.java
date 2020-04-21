package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfataAdaugareAngajat extends JFrame {
    private JLabel numeAngajat = new JLabel("Nume: ");
    private JTextField nume = new JTextField(10);

    private JLabel prenumeAngajat = new JLabel("Prenume: ");
    private JTextField prenume = new JTextField(10);

    private JLabel varstaAngajat = new JLabel("Varsta: ");
    private JTextField varsta = new JTextField(10);

    private JLabel dataAngajarii = new JLabel("Data angajarii: ");
    private JTextField data = new JTextField(10);

    private JButton finish = new JButton("OK");

    public InterfataAdaugareAngajat(){
        this.setTitle("Adauga Angajat");
        this.setSize(300,300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JPanel panelNumeText = new JPanel();
        panelNumeText.add(nume);

        JPanel panelNumeLabel = new JPanel();
        panelNumeLabel.add(numeAngajat);

        JPanel panelNume = new JPanel();
        panelNume.add(panelNumeLabel);
        panelNume.add(panelNumeText);
        panelNume.setLayout(new BoxLayout(panelNume, BoxLayout.X_AXIS));

        JPanel panelPrenumeText = new JPanel();
        panelPrenumeText.add(prenume);

        JPanel panelPrenumeLabel = new JPanel();
        panelPrenumeLabel.add(prenumeAngajat);

        JPanel panelPret = new JPanel();
        panelPret.add(panelPrenumeLabel);
        panelPret.add(panelPrenumeText);
        panelPret.setLayout(new BoxLayout(panelPret, BoxLayout.X_AXIS));

        JPanel panelVarstaText = new JPanel();
        panelVarstaText.add(varsta);

        JPanel panelVarstaLabel = new JPanel();
        panelVarstaLabel.add(varstaAngajat);

        JPanel panelDisponibilitate = new JPanel();
        panelDisponibilitate.add(panelVarstaLabel);
        panelDisponibilitate.add(panelVarstaText);
        panelDisponibilitate.setLayout(new BoxLayout(panelDisponibilitate, BoxLayout.X_AXIS));

        JPanel panelAngajareText = new JPanel();
        panelAngajareText.add(data);

        JPanel panelAngajareLabel = new JPanel();
        panelAngajareLabel.add(dataAngajarii);

        JPanel panelValabilitate = new JPanel();
        panelValabilitate.add(panelAngajareLabel);
        panelValabilitate.add(panelAngajareText);
        panelValabilitate.setLayout(new BoxLayout(panelValabilitate, BoxLayout.X_AXIS));

        JPanel panelButon = new JPanel();
        panelButon.add(finish);

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelNume);
        panelTotal.add(panelPret);
        panelTotal.add(panelDisponibilitate);
        panelTotal.add(panelValabilitate);
        panelTotal.add(panelButon);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));

        this.add(panelTotal);
    }

    public void addInpoiListener(ActionListener a){
        finish.addActionListener(a);
    }
    public String getNumeText(){ return nume.getText(); }
    public String getPrenumeText() { return prenume.getText(); }
    public String getVarstaText() { return varsta.getText(); }
    public String getDataAngajarii() { return data.getText(); }
    public void setNume(String s){ nume.setText(s); }
    public void setPreume(String s){ prenume.setText(s); }
    public void setVarsta(String s){ varsta.setText(s); }
    public void setDataAngajarii(String s){ data.setText(s); }

}

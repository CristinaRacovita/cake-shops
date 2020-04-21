package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfataAdaugarePrajitura extends JFrame {
    private JLabel numePrajitura = new JLabel("Nume Prajitura: ");
    private JTextField nume = new JTextField(10);

    private JLabel pretPrajitura = new JLabel("Pret Prajitura: ");
    private JTextField pret = new JTextField(10);

    private JLabel disponibilitatePrajitura = new JLabel("Disponibilitate: ");
    private JTextField disponibilitate = new JTextField(10);

    private JLabel valabilitatePrajitura = new JLabel("Valabilitate: ");
    private JTextField valabilitate = new JTextField(10);

    private JButton finish = new JButton("OK");

    public InterfataAdaugarePrajitura(){
        this.setTitle("Adauga Prajitura");
        this.setSize(300,300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JPanel panelNumeText = new JPanel();
        panelNumeText.add(nume);

        JPanel panelNumeLabel = new JPanel();
        panelNumeLabel.add(numePrajitura);

        JPanel panelNume = new JPanel();
        panelNume.add(panelNumeLabel);
        panelNume.add(panelNumeText);
        panelNume.setLayout(new BoxLayout(panelNume, BoxLayout.X_AXIS));


        JPanel panelPretText = new JPanel();
        panelPretText.add(pret);

        JPanel panelPretLabel = new JPanel();
        panelPretLabel.add(pretPrajitura);

        JPanel panelPret = new JPanel();
        panelPret.add(panelPretLabel);
        panelPret.add(panelPretText);
        panelPret.setLayout(new BoxLayout(panelPret, BoxLayout.X_AXIS));

        JPanel panelDisponibilitateText = new JPanel();
        panelDisponibilitateText.add(disponibilitate);

        JPanel panelDisponibilitateLabel = new JPanel();
        panelDisponibilitateLabel.add(disponibilitatePrajitura);

        JPanel panelDisponibilitate = new JPanel();
        panelDisponibilitate.add(panelDisponibilitateLabel);
        panelDisponibilitate.add(panelDisponibilitateText);
        panelDisponibilitate.setLayout(new BoxLayout(panelDisponibilitate, BoxLayout.X_AXIS));

        JPanel panelValabilitateText = new JPanel();
        panelValabilitateText.add(valabilitate);

        JPanel panelValabilitateLabel = new JPanel();
        panelValabilitateLabel.add(valabilitatePrajitura);

        JPanel panelValabilitate = new JPanel();
        panelValabilitate.add(panelValabilitateLabel);
        panelValabilitate.add(panelValabilitateText);
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
    public String getDenumireText(){ return nume.getText(); }
    public String getPretText() { return pret.getText(); }
    public String getDisponibilitateText() { return disponibilitate.getText(); }
    public String getValabilitateText() { return valabilitate.getText(); }

    public void setNume(String n) {
        nume.setText(n);
    }

    public void setPret(String p) {
        pret.setText(p);
    }

    public void setDisponibilitate(String d) {
        disponibilitate.setText(d);
    }

    public void setValabilitate(String v) {
        valabilitate.setText(v);
    }
}

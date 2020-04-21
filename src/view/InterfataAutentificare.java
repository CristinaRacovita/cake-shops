package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfataAutentificare extends JFrame{

    private JLabel numeUtilizator = new JLabel("Nume Utilizator");
    private JLabel parola = new JLabel("Parola");

    private JTextField numeUtilizatorText = new JTextField(15);
    private JPasswordField parolaText = new JPasswordField(15);

    private JButton nextButton = new JButton("OK");

    public InterfataAutentificare(){
        this.setTitle("Autentificare");
        this.setSize(360,250);

        JPanel panelButon = new JPanel();
        panelButon.add(nextButton);

        JPanel panelNumeText = new JPanel();
        panelNumeText.add(numeUtilizatorText);

        JPanel panelParolaText = new JPanel();
        panelParolaText.add(parolaText);

        JPanel panelNumeLabel = new JPanel();
        panelNumeLabel.add(numeUtilizator);

        JPanel panelParolaLabel = new JPanel();
        panelParolaLabel.add(parola);

        JPanel panelNumeLabelText = new JPanel();
        panelNumeLabelText.add(panelNumeLabel);
        panelNumeLabelText.add(panelNumeText);
        panelNumeLabelText.setLayout(new BoxLayout(panelNumeLabelText, BoxLayout.X_AXIS));

        JPanel panelParolaLabelText = new JPanel();
        panelParolaLabelText.add(panelParolaLabel);
        panelParolaLabelText.add(panelParolaText);
        panelParolaLabelText.setLayout(new BoxLayout(panelParolaLabelText, BoxLayout.X_AXIS));

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelNumeLabelText);
        panelTotal.add(panelParolaLabelText);
        panelTotal.add(panelButon);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        this.add(panelTotal);
        this.setVisible(true);

    }

    public void addNextButtonListener(ActionListener addNextButonListener) {
        nextButton.addActionListener(addNextButonListener);
    }

    public String getNumeUtilizator (){
        return numeUtilizatorText.getText();
    }
    public char[] getNumeParola (){
        return parolaText.getPassword();
    }
    public void setNumeUtilizator (String s){
         numeUtilizatorText.setText(s);
    }
    public void setParola (String c){
        parolaText.setText(c);
    }

}

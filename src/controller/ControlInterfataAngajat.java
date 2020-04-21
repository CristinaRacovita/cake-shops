package controller;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import view.InterfataAngajat;
import view.InterfataAutentificare;
import view.InterfataPrajituri;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ControlInterfataAngajat {
    private InterfataAngajat interfataAngajat;
    private InterfataAutentificare interfataAutentificare;
    private InterfataPrajituri interfataPrajituri;
    private String nameFile;

    public ControlInterfataAngajat(InterfataAngajat interfataAngajat,InterfataAutentificare interfataAutentificare,InterfataPrajituri interfataPrajituri){
        this.interfataAngajat = interfataAngajat;
        this.interfataAutentificare = interfataAutentificare;
        this.interfataPrajituri = interfataPrajituri;

        interfataAngajat.addInapoiListener(new ActionInapoiListener());
        interfataAngajat.addVizualizareListener(new ActionVizualizareListener());
        interfataAngajat.addSaveListener(new SaveListener());
        interfataAngajat.addListenetStatistici(new StatisticiListener());

    }

    public class ActionInapoiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           interfataAngajat.setVisible(false);
           interfataAutentificare.setNumeUtilizator("");
           interfataAutentificare.setParola("");
           interfataAutentificare.setVisible(true);
        }
    }
    public class ActionVizualizareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cofetarie = interfataAngajat.getVizualizare();
            switch (cofetarie){
                case "Zahar Ars":
                    nameFile = "cofetarie1.bin";
                    interfataAngajat.setVisible(false);
                    interfataPrajituri.setVisible(true);
                    break;
                case "Deliciu":
                    nameFile = "cofetarie2.bin";
                    interfataAngajat.setVisible(false);
                    interfataPrajituri.setVisible(true);
                    break;
                case "Viata Dulce":
                    nameFile = "cofetarie3.bin";
                    interfataAngajat.setVisible(false);
                    interfataPrajituri.setVisible(true);
                    break;
                case "Toate":
                    nameFile = "";
                    interfataAngajat.setVisible(false);
                    interfataPrajituri.setVisible(true);
                    break;
            }
        }
    }
    public class SaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String path = System.getProperty("user.dir");
            File userDirectory = new File(path);
            String format = interfataAngajat.getFormatText();
            String cofetarie = interfataAngajat.getVizualizareRaport();
            String numeFisier = "";
            switch (cofetarie){
                case "Zahar Ars":
                    numeFisier = "cofetarie1.bin";
                    break;
                case "Deliciu":
                    numeFisier = "cofetarie2.bin";
                    break;
                case "Viata Dulce":
                    numeFisier = "cofetarie3.bin";
                    break;
                case "Toate":
                    break;
            }
            List<Prajitura> prajituraLista;
            if(!numeFisier.equals("")) {
                prajituraLista = new PersistentaPrajitura(numeFisier).vizualizare();
            }
            else{
                prajituraLista = new PersistentaPrajitura("cofetarie1.bin").vizualizare();
                prajituraLista.addAll(new PersistentaPrajitura("cofetarie2.bin").vizualizare());
                prajituraLista.addAll(new PersistentaPrajitura("cofetarie3.bin").vizualizare());
            }
            try {
                switch (format){
                    case "csv":
                        String fileCSV = "raport.csv";
                        FileWriter writer = new FileWriter(fileCSV);
                        SalvareRapoarte.CSVFile(writer,prajituraLista);
                        Desktop.getDesktop().open(userDirectory);
                        break;
                    case "xml":
                        String fileXML = "raport.xml";
                        SalvareRapoarte.XMLFile(cofetarie,fileXML, new String[]{"Cofetarie","Denumire", "Pret", "Disponibilitate", "Valabilitate"},prajituraLista);
                        Desktop.getDesktop().open(userDirectory);
                        break;
                    case "json":
                        String fileJSON = "raport.json";
                        FileWriter w = new FileWriter(fileJSON);
                        SalvareRapoarte.jsonFile(w, new String[]{"Cofetarie","Denumire", "Pret", "Disponibilitate", "Valabilitate"},prajituraLista);
                        Desktop.getDesktop().open(userDirectory);
                        break;
                    default:
                        JOptionPane.showMessageDialog(interfataAngajat,"Va rugam introduceti alt format!","Format necunoscut",JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (IOException | ParserConfigurationException | TransformerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class StatisticiListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String cofetarie = interfataAngajat.getVizualizareStatistica();
            String numeFisier = "";
            switch (cofetarie){
                case "Zahar Ars":
                    numeFisier = "cofetarie1.bin";
                    break;
                case "Deliciu":
                    numeFisier = "cofetarie2.bin";
                    break;
                case "Viata Dulce":
                    numeFisier = "cofetarie3.bin";
                    break;
                case "Toate":
                    break;
            }
            StatisticaPrajituri statisticaPrajituri;
            BuilderStatisticaPrajitura builderStatisticaPrajitura = new BuilderStatisticaPrajitura();
            boolean tot;
            if(!numeFisier.equals("")) {
                tot = false;
                statisticaPrajituri = builderStatisticaPrajitura.creareStatisticaPrajituri((new PersistentaPrajitura(numeFisier)).vizualizare());
            }else{
                tot = true;
                List<Prajitura> prajituraLista = new PersistentaPrajitura("cofetarie1.bin").vizualizare();
                prajituraLista.addAll(new PersistentaPrajitura("cofetarie2.bin").vizualizare());
                prajituraLista.addAll(new PersistentaPrajitura("cofetarie3.bin").vizualizare());
                statisticaPrajituri = builderStatisticaPrajitura.creareStatisticaPrajituri(prajituraLista);
            }
            try {
                statisticaPrajituri.generareLineChart("Statistica Prajituri "+cofetarie,tot);
                statisticaPrajituri.generarePieChart("Statistica Prajituri "+cofetarie,tot);
                statisticaPrajituri.generareBarChart("Statistica Prajituri "+cofetarie,tot);
                File userDirectory = new File(System.getProperty("user.dir"));
                Desktop.getDesktop().open(userDirectory);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public String getPersistentaPrajitura() {
        return nameFile;
    }
}

package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistentaPrajitura{
    private String numeFisier;

    public PersistentaPrajitura(String numeFisier){
        this.numeFisier = numeFisier;
    }

    public PersistentaPrajitura() {
    }

    public String getNumeFisier() {
        return numeFisier;
    }

    public void incarcare(List<Prajitura> prajituri){
        try {
            FileOutputStream file = new FileOutputStream(numeFisier);
            ObjectOutputStream prajituriFile = new ObjectOutputStream(file);
            prajituriFile.writeObject(prajituri);
            prajituriFile.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Prajitura> vizualizare(){
        try{
            FileInputStream file = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(file);
            List<Prajitura> prajituri = (ArrayList<Prajitura>)in.readObject();
            in.close();
            return prajituri;
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("IOException is caught");
        }
        return null;
    }

    public List<Prajitura> vizualizare(String nume,boolean ok){
        List<Prajitura> prajituri = vizualizare();
        List<Prajitura> prajituriFiltrate = new ArrayList<Prajitura>();
        try {
            FileInputStream file = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(file);
            for (Prajitura p : prajituri) {
                if (p.getNumePrajitura().contains(nume) && ok) {
                    prajituriFiltrate.add(p);
                }
                else if(nume.substring(0,1).compareTo(p.getValabilitate().substring(0,1)) >= 0 && !ok){
                    prajituriFiltrate.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prajituriFiltrate;
    }

    public List<Prajitura> vizualizare(int pretMin, int pretMax){
        List<Prajitura> prajituri = vizualizare();
        List<Prajitura> prajituriFiltrate = new ArrayList<Prajitura>();
        try {
            FileInputStream file = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(file);
            for (Prajitura p : prajituri) {
                if (p.getPret() >= pretMin && p.getPret() <= pretMax) {
                    prajituriFiltrate.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prajituriFiltrate;
    }
    public List<Prajitura> vizualizare(boolean disp){
        List<Prajitura> prajituri = vizualizare();
        List<Prajitura> prajituriFiltrate = new ArrayList<Prajitura>();
        try {
            FileInputStream file = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(file);
            for (Prajitura p : prajituri) {
                if (p.isDisponibilitateProdus() == disp) {
                    prajituriFiltrate.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prajituriFiltrate;
    }

    public boolean stergere(Prajitura prajitura) throws IOException {
        List<Prajitura> prajituraLista = (new PersistentaPrajitura(getNumeFisier())).vizualizare();
        new FileOutputStream(getNumeFisier()).close();
        prajituraLista.removeIf(prj -> prj.getNumePrajitura().equals(prajitura.getNumePrajitura()));
        new PersistentaPrajitura(getNumeFisier()).incarcare(prajituraLista);
        return true;
    }

    public boolean salvare(Prajitura prajitura){
        try {
            List<Prajitura> prajituraLista = vizualizare();
            prajituraLista.add(prajitura);
            new FileOutputStream(numeFisier).close();
            FileOutputStream file = new FileOutputStream(numeFisier);
            ObjectOutputStream prajituriFile = new ObjectOutputStream(file);
            prajituriFile.writeObject(prajituraLista);
            prajituriFile.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

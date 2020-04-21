package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistentaUser {
    private String numeFisier;

    public PersistentaUser(String numeFisier){
        this.numeFisier = numeFisier;
    }

    public String getNumeFisier() {
        return numeFisier;
    }

    public void setNumeFisier(String numeFisier) {
        this.numeFisier = numeFisier;
    }
    public void incarcare(List<User> users){
        try {
            FileOutputStream file = new FileOutputStream(numeFisier);
            ObjectOutputStream usersFile = new ObjectOutputStream(file);
            usersFile.writeObject(users);
            usersFile.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> vizualizare(){
        try{
            FileInputStream file = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(file);
            List<User> users = (ArrayList<User>)in.readObject();
            in.close();
            return users;
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("IOException is caught");
        }
        return null;
    }
    public List<User> vizualizareAngajati(){
        try{
            FileInputStream file = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(file);
            List<User> users = (ArrayList<User>)in.readObject();
            List<User> useri = new ArrayList<>();
            for(User u:users){
                if(u.getFunctie().equals("angajat")) {
                    useri.add(u);
                }
            }
            in.close();
            return useri;
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("IOException is caught");
        }
        return null;
    }

    public boolean stergere(User user) throws IOException {
        List<User> userList = vizualizare();
        PersistentaConturi persistentaConturi = new PersistentaConturi("conturi.bin");
        List<ContUser> contUsers = persistentaConturi.vizualizare();
        /*for(ContUser c:contUsers){
            System.out.println("Inainte de stergere");
            System.out.println(c);
        }*/
        new FileOutputStream(getNumeFisier()).close();
        new FileOutputStream("conturi.bin").close();
        userList.removeIf(prj -> prj.getNume().equals(user.getNume()));
        List<String> parole = new ArrayList<>();
        for(ContUser c: contUsers){
            parole.add(c.getParola());
        }
        contUsers.removeIf(usr -> usr.getNumeUtilizator().equals(user.getNume()+"-"+user.getPrenume()+"@angajat.com"));
        /*for(ContUser c:contUsers){
            System.out.println("Dupa stergere");
            System.out.println(c);
        }*/
        persistentaConturi.incarcare(userList,parole);
        incarcare(userList);
        return true;
    }

    public ContUser salvare(User user){
        try {
            List<User> userList = vizualizare();
            userList.add(user);
            PersistentaConturi persistentaConturi = new PersistentaConturi("conturi.bin");
            List<ContUser> contUsers = persistentaConturi.vizualizare();
            /*for(ContUser c:contUsers){
                System.out.println("Inainte de salvare");
                System.out.println(c);
            }*/
            new FileOutputStream(numeFisier).close();
            new FileOutputStream("conturi.bin").close();
            List<String> parole = new ArrayList<>();
            for(ContUser c:contUsers){
                //System.out.println("Dupa salvare");
                //System.out.println(c);
                parole.add(c.getParola());
            }
            parole.add(persistentaConturi.generareParola());
            ContUser contUser = FabricaConturi.obtinereCont("angajat",user.getNume()+"-"+user.getPrenume(),parole.get(parole.size()-1));
            persistentaConturi.incarcare(userList,parole);
            FileOutputStream file = new FileOutputStream(numeFisier);
            ObjectOutputStream usersFile = new ObjectOutputStream(file);
            usersFile.writeObject(userList);
            usersFile.close();
            file.close();
            return contUser;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

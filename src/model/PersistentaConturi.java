package model;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersistentaConturi {
    String numeFisier;
    public PersistentaConturi(String numeFisier){
        this.numeFisier = numeFisier;
    }

    public List<ContUser> incarcare(List<User> users){
        try {
            FileOutputStream file = new FileOutputStream(numeFisier);
            ObjectOutputStream usersFile = new ObjectOutputStream(file);
            List<ContUser> contUsers = new ArrayList<>();
            for(User u : users){
                String numeUtilizator = u.getNume()+"-"+u.getPrenume();
               contUsers.add(FabricaConturi.obtinereCont(u.getFunctie(),numeUtilizator,generareParola()));
            }
            usersFile.writeObject(contUsers);
            usersFile.close();
            file.close();
            return contUsers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ContUser> incarcare(List<User> users,List<String> parole){
        try {
            FileOutputStream file = new FileOutputStream(numeFisier);
            ObjectOutputStream usersFile = new ObjectOutputStream(file);
            List<ContUser> contUsers = new ArrayList<>();
            int i = 0;
            for(User u : users){
                String numeUtilizator = u.getNume()+"-"+u.getPrenume();
                contUsers.add(FabricaConturi.obtinereCont(u.getFunctie(),numeUtilizator,parole.get(i)));
                i++;
            }
            usersFile.writeObject(contUsers);
            usersFile.close();
            file.close();
            return contUsers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<ContUser> vizualizare(){
        try{
            FileInputStream file = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(file);
            List<ContUser> users = (ArrayList<ContUser>)in.readObject();
            in.close();
            return users;
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("IOException is caught");
        }
        return null;
    }

    public String generareParola(){
        int leftlimit = 97; //a
        int rightlimit = 122; //z
        int targetLength = 10;
        Random random = new Random();
        return random.ints(leftlimit,rightlimit+1).limit(targetLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public String getNumeFisier() {
        return numeFisier;
    }

    public void setNumeFisier(String numeFisier) {
        this.numeFisier = numeFisier;
    }
}

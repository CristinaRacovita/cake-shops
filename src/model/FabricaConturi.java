package model;

public class FabricaConturi {
    public static ContUser obtinereCont(String tipCont,String numeUtilizator, String pass){
        if(tipCont == null){
            return null;
        }
        if(tipCont.compareTo("angajat")==0){
            return new ContUserAngajat(numeUtilizator,pass);
        }
        if(tipCont.compareTo("admin")==0){
            return new ContUserAdmin(numeUtilizator,pass);
        }
        return null;
    }
}

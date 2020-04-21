package model;

import java.util.List;

public class BuilderStatisticaPrajitura {

    public StatisticaPrajituri creareStatisticaPrajituri(List<Prajitura> prajituraLista){
        StatisticaPrajituri statisticaPrajituri= new StatisticaPrajituri();
        for(Prajitura p : prajituraLista) {
            statisticaPrajituri.AdaugarePrajitura(p);
        }
        return statisticaPrajituri;
    }
}

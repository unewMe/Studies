package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable.DataBase;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PeopleListModel implements Observer
{

    private ObservableList<String> peopleList = FXCollections.observableArrayList();
    private ArrayList<Osoby> osoby = new ArrayList<>(DataBase.getPeople());


    //Ustalanie poczatkowych wartosci dla listy osob
    public void initializePeople()
    {
        peopleList.clear();
        peopleList.add(String.format("%-20s %-20s %-20s %-20s %-20s","Imie","Nazwisko","Pesel","Wiek","Miejsce Zamieszkania"));

        for(int i=0;i< osoby.size();i++)
        {
            //Dodawanie kompletnych informacji na teamt osoby do listy
            peopleList.add(osoby.get(i).infoOsoba());
        }
    }


    public ObservableList<String> getPeopleList()
    {
//        for(int i = 0; i< peopleList.size(); i++)
//            System.out.println(peopleList.get(i));
        return peopleList;
    }

    @Override
    public void update(ArrayList<Osoby> people)
    {
        this.osoby.clear();
        for(int i=0;i<people.size();i++)
        {
            this.osoby.add(people.get(i));
        }
        //this.osoby.add(people.get(people.size()-1));
        initializePeople();

    }
}

package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable;



import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.Observer;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.SetDataBase;
import KlasyOperacyjneNaStrukturzeUczelni.Serialization.Serialization;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;

import java.util.ArrayList;

public class DataBase implements Subject
{
    private static ArrayList<Osoby> people=new ArrayList<>();

    private ArrayList<Observer> observers=new ArrayList<>();

    public static ArrayList<Osoby> getPeople()
    {
        return people;
    }

    public void add(Osoby person)
    {
        people.add(person);
        notifyObservers();
    }

    public void remove(Osoby person)
    {
        people.remove(person);
        notifyObservers();
    }

    public static boolean save()
    {
        return Serialization.save(people);
    }

    public static boolean read()
    {
        return Serialization.read(people);
    }



    @Override
    public void registerObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers()
    {
        ArrayList<Osoby> osobyArrayList = new ArrayList<>(people);
        for(int i=0;i<observers.size();i++)
        {
            observers.get(i).update(people);
        }
    }
}

package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers;

import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Pracownicy;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.WorkerMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class WorkersModel implements Observer
{

    private ObservableList<Pracownicy> workersList = FXCollections.observableArrayList();
    private ArrayList<Pracownicy> workers = new ArrayList<>(WorkerMethods.getWorkersList());


    //Ustalanie poczatkowych wartosci dla listy pracownikow
    public void initializeWorkers()
    {
        workersList.clear();
//        System.out.println(workers.size());

        for(int i = 0; i< workers.size(); i++)
        {
            workersList.add(workers.get(i));
        }
    }

    public ObservableList<Pracownicy> getWorkersList()
    {
        return workersList;
    }
    @Override
    public void update(ArrayList<Osoby> people)
    {

        Osoby osoba = people.get(people.size()-1);
        if(osoba instanceof Pracownicy)
        {
            this.workers.add((Pracownicy) people.get(people.size() - 1));
            initializeWorkers();
        }

    }
}

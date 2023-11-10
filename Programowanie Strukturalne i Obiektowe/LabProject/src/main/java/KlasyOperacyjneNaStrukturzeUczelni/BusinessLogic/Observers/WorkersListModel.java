package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers;

import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Pracownicy;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.WorkerMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class WorkersListModel implements Observer
{
    private ObservableList<String> workersList = FXCollections.observableArrayList();
    private ArrayList<Pracownicy> workers = new ArrayList<>(WorkerMethods.getWorkersList());


    //Ustalanie poczatkowych wartosci dla listy pracownikow
    public void initializeWorkers()
    {
        workersList.clear();
//        System.out.println(workers.size());

        workersList.add(String.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s","Imie","Nazwisko","Pesel","Wiek","Miejsce zamieszkania","Typ Umowy","Wymiar Godzin Pracy","Nadgodzinny","Pensja"));
        for(int i = 0; i< workers.size(); i++)
        {
            //Dodawanie kompletnych informacji na temat pracownika do listy
            workersList.add(workers.get(i).infoPracownicy());
        }
    }

    public ObservableList<String> getWorkersList()
    {
        return workersList;
    }

    @Override
    public void update(ArrayList<Osoby> people)
    {

        ArrayList<Pracownicy> workersPom=new ArrayList<>();
        workers.clear();
        for (Osoby person: people)
            if(person instanceof Pracownicy)
            {
                this.workers.add((Pracownicy) person);
            }

        initializeWorkers();

    }
}

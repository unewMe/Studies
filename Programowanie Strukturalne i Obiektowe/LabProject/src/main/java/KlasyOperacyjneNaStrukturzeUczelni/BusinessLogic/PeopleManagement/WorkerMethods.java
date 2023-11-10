package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement;

import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.*;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable.DataBase;
import KlasyOperacyjneNaStrukturzeUczelni.PensjaPracownika.UstalaniePensji;

import java.util.ArrayList;

public class WorkerMethods
{

    private static DataBase dataBase= SetDataBase.getDataBase();

    //Ustalanie czasu pracy i nadgodzin
    public static void setWorkingTimeAndOvertime(Pracownicy worker, int workingTime, int overtime)
    {
        worker.setWymiarGodzinPracy(workingTime);
        worker.setNadgodzinny(overtime);
    }


    //Ustalanie pensji
    public static double setSalary(Pracownicy worker)
    {
        UstalaniePensji ustalaniePensji = new UstalaniePensji();
        ustalaniePensji.wybierzStrategie(worker.getTypUmowy());
        double salary = ustalaniePensji.obliczaniePensji(worker);
        worker.setPensja(salary);
        return salary;
    }

    public static ArrayList<Pracownicy> getWorkersList()
    {
        ArrayList<Osoby> person = new ArrayList<>(DataBase.getPeople());
        ArrayList<Pracownicy> workers = new ArrayList<>();
        for(int i=0;i<person.size();i++)
        {
            Osoby pom = person.get(i);
            if(pom instanceof Pracownicy)
            {
                workers.add((PracownicyPWR) pom);
            }
        }
        return workers;
    }

    //Dodawanie pracownika zewnetrznego do bazy danych
    public static void addExternalWorker(String name, String surname, String id, String placeOfResidence, int age, String contractType, String companyName)
    {
        dataBase.add(new PracownicyZewnetrzni(name, surname, id, placeOfResidence, age, contractType, companyName));
    }

    //Dodawanie pracownika pwr do bazy danych
    public static void addPwrWorker(String name, String surname, String id, String placeOfResidence, int age, String contractType, String academicDegree)
    {
        dataBase.add(new PracownicyPWR(name, surname, id, placeOfResidence, age, contractType, academicDegree));
    }

    public static boolean remove(String pesel)
    {
        Pracownicy worker = getWorker(pesel);
        if(worker!=null)
        {
            dataBase.remove(worker);
            return true;
        }
        return false;
    }

    public static Pracownicy getWorker(String pesel)
    {
        ArrayList<Osoby> people = new ArrayList<>(DataBase.getPeople());
        for(int i=0;i<people.size();i++)
        {
            Osoby person = people.get(i);
            if(person instanceof Pracownicy)
            {
                Pracownicy worker = (Pracownicy) person;
                if(worker.getPesel().equals(pesel))
                    return worker;
            }
        }
        return null;

    }

    public static void setDataBase(DataBase dataBase) {
        WorkerMethods.dataBase = dataBase;
    }
}

package KlasyOperacyjneNaStrukturzeUczelni.Serialization;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable.DataBase;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.SetDataBase;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;

import java.io.*;
import java.util.ArrayList;

public class Serialization
{

    private static DataBase dataBase = SetDataBase.getDataBase();
    public static boolean save(ArrayList<Osoby> people)
    {
        try(ObjectOutputStream dane = new ObjectOutputStream(new FileOutputStream("osoby.ser")) )
        {

            for(int i=0;i<people.size();i++)
            {
                dane.writeObject(people.get(i));
            }

            return true;
        }
        catch (IOException a)
        {

            System.out.println("Nie mozna zapisac danych do pliku");
            System.out.println("There was a problem with " + a);
            return false;


        }

    }

    public static boolean read(ArrayList<Osoby> people)
    {
        try(ObjectInputStream dane = new ObjectInputStream(new FileInputStream("osoby.ser")))
        {
            Osoby person;

            while(dane.available()>=0)
            {
                person = (Osoby) dane.readObject();
                dataBase.add(person);
            }

            return true;

        }
        catch (ClassNotFoundException a)
        {
            return false;
        }
        catch (IOException a)
        {
            return true;
        }

    }


    }




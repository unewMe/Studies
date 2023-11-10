package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement;

import Exceptions.StringFormatException;

import java.io.*;
import java.util.ArrayList;

public class ReadData
{

    //Odczyt danych z pliku
    public static boolean readFromFile(File file)
    {

        ArrayList<String> data=new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String readWord="";
            while((readWord=reader.readLine())!=null)
            {
                data.add(readWord);
            }
            if(data.size()>0)
                return addToDataBase(data);
            else
                return false;
        }
        catch (IOException a)
        {
            return false;
        }
    }


    //Dodawanie danych do bazy danych
    public static boolean addToDataBase(ArrayList<String> data)
    {
        int licznik=0;

        for(int i=0;i< data.size();i++)
        {
            String personData[] = data.get(i).split("\\s+");

            if(personData[0].equalsIgnoreCase("PracownikPWR") || personData[0].equalsIgnoreCase("PracownikZewnetrzny") )
            {
                try
                {
                    boolean state = CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(personData);
                    if (state)
                        licznik++;
                }
                catch (StringFormatException a)
                {
                    return false;
                }

            }
            else if(personData[0].equalsIgnoreCase("StudentDzienny") || personData[0].equalsIgnoreCase("StudentZaoczny"))
            {
                try
                {
                    boolean state = CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(personData);
                    if (state)
                        licznik++;
                }
                catch (StringFormatException a)
                {
                    return false;
                }
            }
        }
        if(licznik!=data.size())
            return false;


        for(int i=0;i<data.size();i++)
        {
            String personData[] = data.get(i).split("\\s+");

            if(personData[0].equalsIgnoreCase("PracownikPWR"))
            {
                WorkerMethods.addPwrWorker(personData[1],personData[2],personData[3],personData[4],Integer.parseInt(personData[5]),conversContract(personData[6]),personData[7]);

            }

            if(personData[0].equalsIgnoreCase("PracownikZewnetrzny"))
            {
                WorkerMethods.addExternalWorker(personData[1],personData[2],personData[3],personData[4],Integer.parseInt(personData[5]),conversContract(personData[6]),personData[7]);
            }

            if(personData[0].equalsIgnoreCase("StudentDzienny"))
            {
                StudentMethods.addFullTimeStudent(personData[1],personData[2],personData[3],personData[4],Integer.parseInt(personData[5]),personData[6],Integer.parseInt(personData[7]),Integer.parseInt(personData[8]),personData[9]);

            }
            if(personData[0].equalsIgnoreCase("StudentZaoczny"))
            {

                try
                {
                    boolean state = CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(personData);
                    if(state)
                    {
                        StudentMethods.addExtramuralyStudent(personData[1],personData[2],personData[3],personData[4],Integer.parseInt(personData[5]),personData[6],Integer.parseInt(personData[7]),Integer.parseInt(personData[8]),personData[9]);
                    }
                    else
                    {
                        return false;
                    }
                }
                catch (StringFormatException a)
                {
                    return false;
                }
            }
        }
        return true;
    }


    public static String conversContract(String s)
    {
        if(s.equalsIgnoreCase("UmowaOPrace"))
            return "Umowa o Prace";
        else
            return "Umowa Zlecenie";
    }

}

package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement;


import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable.DataBase;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.*;
import KlasyOperacyjneNaStrukturzeUczelni.StypendiumStudenta.UstalanieRodzajuStypendium;


import java.util.ArrayList;

public class StudentMethods
{

    private static DataBase dataBase = SetDataBase.getDataBase();

    //Dodawanie studenta dziennego do bazy danych
    public static void addFullTimeStudent(String name, String surname, String id, String placeOfResidence, int age, String index, int studyYear, int distanceFromHome, String isInStudentCouncil)
    {
        Student student = new StudentDzienny(name,surname,id,placeOfResidence,age,index,studyYear,distanceFromHome,isInStudentCouncil);
        dataBase.add(student);
        System.out.println(student);
    }

    //Dodawanie studenta zaocznego do bazy danych
    public static void addExtramuralyStudent(String name, String surname, String id, String placeOfResidence, int age, String index, int studyYear, int distanceFromHome, String isWorking)
    {
        Student student = new StudentZaoczny(name,surname,id,placeOfResidence,age,index,studyYear,distanceFromHome,isWorking);
        dataBase.add(student);
    }
    //Usuwanie z bazy
    public static boolean remove(String index)
    {
        Student student = getStudent(index);
        if(student!=null)
        dataBase.remove(student);
        else
            return false;

        return true;
    }


    //Dodawanie kursu studentowi
    public static void addCourse(Student student, String courseName)
    {
            student.dodajKurs(courseName);
    }

    //Dodawanie oceny z kursu
    public static void addGradeToCourse(Student student, Kurs course, double grade)

    {
        student.dodajOceneZKursu(grade,course.getNazwaKursu());

    }


    //Pobieranie studenta z bazy danych do indeksie
    public static Student getStudent(String index)
    {
        ArrayList<Osoby> people = new ArrayList<>(DataBase.getPeople());
        for(int i=0;i<people.size();i++)
        {
            Osoby person = people.get(i);
            if(person instanceof Student )
            {
                Student student = (Student) person;
                if(student.getIndeks().equals(index))
                    return student;
            }
        }
        return null;
    }


    //Ustalanie stypendium
    public static double setScholarship(Student student, int choice)
    {
        double scholarship=0;

        switch (choice)
        {
            case 1:
            {
                UstalanieRodzajuStypendium ustalanieRodzajuStypendium = new UstalanieRodzajuStypendium();
                ustalanieRodzajuStypendium.setStrategyNaOdlegloscIOceny();
                scholarship=ustalanieRodzajuStypendium.ObliczanieStydendium(student);
                student.setStypendium(scholarship);
                return scholarship;

            }
            case 2:
            {
                UstalanieRodzajuStypendium ustalanieRodzajuStypendium = new UstalanieRodzajuStypendium();
                ustalanieRodzajuStypendium.setStrategyNaZajeciaIOceny();
                scholarship=ustalanieRodzajuStypendium.ObliczanieStydendium(student);
                student.setStypendium(scholarship);
                return scholarship;
            }
            default:
                return scholarship;


        }
    }

    public static ArrayList<Student> getStudentsList()
    {
        ArrayList<Osoby> people = new ArrayList<>(DataBase.getPeople());
        ArrayList<Student> students = new ArrayList<>();
        for(int i=0;i<people.size();i++)
        {
            Osoby person = people.get(i);
            if(person instanceof Student)
            {
                students.add((Student)person);
            }
        }
        return students;
    }

    public static void setDataBase(DataBase dataBase)
    {
        StudentMethods.dataBase = dataBase;
    }
}

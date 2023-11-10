package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.StudentMethods;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class StudentsListModel implements Observer
{

    private ObservableList<String> studentsList = FXCollections.observableArrayList();
    private ArrayList<Student> students = new ArrayList<>(StudentMethods.getStudentsList());


    //Ustalanie poczatkowych wartosci dla listy studentow
    public void initializeStudents()
    {
        studentsList.clear();
        //if(students.size()>0)
        studentsList.add(String.format("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s","Imie","Nazwisko","Pesel","Wiek","Miejsce zamieszkania","Indeks","Rok Studiow","Typ Studiow","Stypendium","Odl od domu"));
        for(int i=0;i< students.size();i++)
        {
            //dodawanie kompletnych informacji na temat studenta do listy
            studentsList.add(students.get(i).infoStudent());
        }
    }


    public ObservableList<String> getStudentsList()
    {
//        for(int i = 0; i< studentsList.size(); i++)
//            System.out.println(studentsList.get(i));
        return studentsList;
    }


    @Override
    public void update(ArrayList<Osoby> people)
    {
       ArrayList<Student> studentsPom = new ArrayList<>();
       students.clear();
       for(Osoby person:people )
        if(person instanceof Student)
        {
            this.students.add((Student) person);

        }
        initializeStudents();

    }

    public ArrayList<Student> getStudents()
    {
        return students;
    }
}

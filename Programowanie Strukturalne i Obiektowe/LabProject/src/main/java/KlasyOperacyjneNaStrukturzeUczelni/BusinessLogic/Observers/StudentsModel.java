package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.StudentMethods;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Kurs;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class StudentsModel implements Observer
{

    private ObservableList<Student> studentsList = FXCollections.observableArrayList();
    private ObservableList<Kurs> coursesList = FXCollections.observableArrayList();
    private ArrayList<Student> students = new ArrayList<>(StudentMethods.getStudentsList());



    //Ustalanie poczatkowych wartosci dla listy studentow
    public void initializeStudents()
    {
        studentsList.clear();
//        System.out.println(students.size());

        for(int i=0;i<students.size();i++)
        {
            studentsList.add(students.get(i));
        }
    }

    //Ustalanie poczatkowych wartosci dla listy kursow
    public void initializeCourse(Student student)
    {
        ArrayList<Kurs> kursy = student.getKursy();
        for(int i=0;i<kursy.size();i++)
        {
            coursesList.add(kursy.get(i));
        }
    }


    public ObservableList<Kurs> getCoursesList()
    {
        return coursesList;
    }

    public ObservableList<Student> getStudentsList()
    {
        return studentsList;
    }

    @Override
    public void update(ArrayList<Osoby> people)
    {
        Osoby person = people.get(people.size()-1);
        if(person instanceof Student)
        {
            students.add((Student) person);
            initializeStudents();
        }
    }
}

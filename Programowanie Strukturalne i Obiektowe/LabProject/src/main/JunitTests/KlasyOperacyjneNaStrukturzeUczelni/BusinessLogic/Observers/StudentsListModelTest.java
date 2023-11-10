package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable.DataBase;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.StudentDzienny;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentsListModelTest {


    private DataBase dataBase;
    private StudentsListModel studentsListModel;
    @BeforeEach
    void setUp()
    {
        dataBase = new DataBase();
        studentsListModel = new StudentsListModel();
        dataBase.registerObserver(studentsListModel);

    }
    @Test
    void CheckStudents()
    {
        Student student1 = new StudentDzienny("Dawid","Chudzicki","1234","Czestochowa",20,"12345",1,186,"Tak");
        Student student2 = new StudentDzienny("Dawid","Chudzicki","1234","Czestochowa",20,"1342345",1,186,"Tak");
        dataBase.add(student1);dataBase.add(student2);
        assertEquals(true,DataBase.getPeople().equals(studentsListModel.getStudents()));
    }
}
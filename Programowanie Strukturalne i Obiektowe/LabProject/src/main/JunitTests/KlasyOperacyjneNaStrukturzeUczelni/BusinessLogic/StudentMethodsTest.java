package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable.DataBase;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.StudentMethods;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.StudentDzienny;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentMethodsTest
{
    private DataBase dataBase;
    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp()
    {
        dataBase = new DataBase();
        student1 = new StudentDzienny("Dawid","Chudzicki","1234","Czestochowa",20,"12345",1,186,"Tak");
        student2 = new StudentDzienny("Dawid","Chudzicki","1234","Czestochowa",20,"1342345",1,186,"Tak");
        dataBase.add(student1);dataBase.add(student2);
        StudentMethods.setDataBase(dataBase);
    }

    @Test
    void remove()
    {
        StudentMethods.remove("12345");
        ArrayList<Osoby> people = DataBase.getPeople();
        assertEquals(false,people.contains(student1));
    }

    @Test
    void getStudent()
    {
        Student student = StudentMethods.getStudent("1342345");
        assertEquals(true,student.equals(student2));
    }
}
package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable.DataBase;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.WorkerMethods;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Pracownicy;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.PracownicyPWR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WorkerMethodsTest {

    private DataBase dataBase;
    private Pracownicy worker1;
    private Pracownicy worker2;

    @BeforeEach
    void setUp()
    {
        dataBase = new DataBase();
        worker1 = new PracownicyPWR("Dawid","Chudzicki","12567","Czestochowa",20,"Umowa o Prace","Doktor");
        worker2 = new PracownicyPWR("Dawid","Chudzicki","125678","Czestochowa",20,"Umowa o Prace","Magister");
        dataBase.add(worker1);dataBase.add(worker2);
        WorkerMethods.setDataBase(dataBase);
    }

    @Test
    void remove()
    {
        WorkerMethods.remove("12567");
        ArrayList<Osoby> people = DataBase.getPeople();
        assertEquals(false,people.contains(worker1));
    }

    @Test
    void getWorker()
    {
        Pracownicy worker = WorkerMethods.getWorker("125678");
        assertEquals(true,worker.equals(worker2));
    }
}
package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable.DataBase;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.Observer;

public class SetDataBase
{

    private static DataBase dataBase;

    public SetDataBase()
    {
        dataBase = new DataBase();
    }
    public static DataBase getDataBase()
    {
        return dataBase;
    }

    //Rejstrowanie i usuwanie obserwatorow bazy dancyh
    public static void register(Observer observer)
    {
        dataBase.registerObserver(observer);
    }

    public static void remove(Observer observer)
    {
        dataBase.removeObserver(observer);
    }

}

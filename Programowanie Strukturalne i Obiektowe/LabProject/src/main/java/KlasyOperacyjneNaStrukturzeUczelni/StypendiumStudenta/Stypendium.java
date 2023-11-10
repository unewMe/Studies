package KlasyOperacyjneNaStrukturzeUczelni.StypendiumStudenta;


import KlasyOperacyjneNaStrukturzeUczelni.StrategiaStypendium.metodaStypendium;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;

public abstract class Stypendium
{
    protected metodaStypendium Ob;

    public double ObliczanieStydendium(Student student)
    {
        double stupendium = Ob.ObliczanieStydendium(student);
        return stupendium;
    }
}

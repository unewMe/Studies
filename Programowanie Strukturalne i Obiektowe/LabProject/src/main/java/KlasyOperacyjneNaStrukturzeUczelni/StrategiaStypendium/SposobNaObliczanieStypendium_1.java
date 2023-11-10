package KlasyOperacyjneNaStrukturzeUczelni.StrategiaStypendium;

import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Kurs;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;

import java.util.ArrayList;

public class SposobNaObliczanieStypendium_1 implements metodaStypendium
{
    @Override
    public double ObliczanieStydendium(Student student)
    {
        double stypendium=0;

        double sredniaOcen=0;
        int licznik=0;
        ArrayList<Kurs> kursy = student.getKursy();
        int odlegoscOdMiejscaZamieszkania = student.getOdlOdMiejscaZamieszkania();





            if(odlegoscOdMiejscaZamieszkania<500)
            {
                stypendium+=odlegoscOdMiejscaZamieszkania;
            }
            else
            {
                stypendium+=500;
            }


            for(int i=0;i<kursy.size();i++)
            {


                    sredniaOcen +=kursy.get(i).getOcena();
                    licznik++;

            }
            if(licznik!=0)
                sredniaOcen/=licznik;

            if(sredniaOcen>4)
            {
                if (student.getTypStudiow().equalsIgnoreCase("dzienne")) {

                    stypendium += sredniaOcen * 60*2;

                }
                else if(student.getTypStudiow().equalsIgnoreCase("zaoczne"))
                {
                    stypendium+=sredniaOcen*60;
                }
            }




        return stypendium;



    }
}

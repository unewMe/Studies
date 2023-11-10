package KlasyOperacyjneNaStrukturzeUczelni.StrategiaStypendium;


import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Kurs;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.StudentDzienny;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.StudentZaoczny;

import java.util.ArrayList;

public class SposobNaObliczanieStudendium_2 implements metodaStypendium
{
    public double ObliczanieStydendium(Student student)
    {
        double stypendium=0;
        double sredniaOcen=0;
        int licznik=0;

        ArrayList<Kurs> kursy = student.getKursy();

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
                    if(student instanceof StudentDzienny)
                    {
                        StudentDzienny pom = (StudentDzienny) student;
                        if (pom.getCzyUdzielaSieWSamorzadzie().equalsIgnoreCase("tak"))
                            stypendium += 500;
                    }

                }
                else if(student.getTypStudiow().equalsIgnoreCase("zaoczne"))
                {
                    stypendium+=sredniaOcen*60;

                    if(student instanceof StudentZaoczny)
                    {
                        StudentZaoczny pom = (StudentZaoczny) student;
                        if(pom.getCzyPracujeNaEtat().equalsIgnoreCase("tak"))
                            stypendium/=2;
                    }


                }
            }

        if (student.getTypStudiow().equalsIgnoreCase("dzienne") ) {

            StudentDzienny pom = (StudentDzienny) student;
            if(pom.getCzyUdzielaSieWSamorzadzie().equalsIgnoreCase("tak"))
                stypendium+=500;

        }
        else if(student.getTypStudiow().equalsIgnoreCase("zaoczne"))
        {
            StudentZaoczny pom = (StudentZaoczny) student;

            if(pom.getCzyPracujeNaEtat().equalsIgnoreCase("tak"))
                stypendium/=2;
        }




        return stypendium;



    }

}

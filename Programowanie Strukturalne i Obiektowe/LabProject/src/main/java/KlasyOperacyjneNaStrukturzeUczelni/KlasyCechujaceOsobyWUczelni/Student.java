package KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni;

import java.util.ArrayList;
import java.util.Objects;

public abstract class  Student extends Osoby
{

    private String indeks;
    private int rokStudiow;

    private ArrayList<Kurs> kursy;

    private double stypendium = 0;

    private String typStudiow;

    private int odlOdMiejscaZamieszkania=0;


    public Student(String imie,String nazwisko,String pesel,String miejscezamieszkania,int wiek,String indeks, int rokStudiow,int odlOdMiejscaZamieszkania)
    {
        super(imie,nazwisko,pesel,miejscezamieszkania,wiek);
        this.indeks = indeks;
        this.rokStudiow = rokStudiow;
        this.kursy = new ArrayList<>();
        this.odlOdMiejscaZamieszkania = odlOdMiejscaZamieszkania;
    }

    public String getIndeks()
   {
       return indeks;
   }

    public int getOdlOdMiejscaZamieszkania() {
        return odlOdMiejscaZamieszkania;
    }

    public String getTypStudiow() {
        return typStudiow;
    }

    public void setTypStudiow(String typStudiow)
    {
        this.typStudiow = typStudiow;
    }

    public void setStypendium(double stypendium)
    {
        this.stypendium = stypendium;
    }

    public void dodajKurs(String nazwa)
   {
       kursy.add(new Kurs(nazwa));
   }

    public void dodajOceneZKursu(double ocena,String nazwa)
   {
       for(int i = 0;i<kursy.size();i++)
       {
           Kurs kurs = kursy.get(i);
           if(kurs.getNazwaKursu().equals(nazwa))
           {
               kurs.setOcena(ocena);
           }
       }
   }

    public ArrayList<Kurs> getKursy()
    {
        return kursy;
    }


    //Pobieranie pelnych danych dotyczacych studenta
    public String infoStudent()
    {
       String s = super.infoOsoba() + String.format("%-20s %-20d %-20s %-20f %-20d",indeks,rokStudiow,typStudiow,stypendium,odlOdMiejscaZamieszkania);
       return s;
    }


    //Pobieranie danch dotyczacych kursow studenta
    public String infoKurs()
    {
       String s="";
       for(int i=0;i< kursy.size();i++)
       {
           Kurs kurs = kursy.get(i);
               s+=kurs.getNazwaKursu() + "   Ocena: " + kurs.getOcena();
               s+="\n";
       }
       return s;
    }

    //Pobieranie dancych do wyswietlenia
    public String toString()
    {
        return super.toString()  + indeks;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof Student)
        {
            Student pom = (Student) obj;
            if(indeks.equals(pom.indeks))
                return true;
            else
                return false;
        }
        return false;
    }
}

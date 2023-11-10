package KlasyOperacyjneNaStrukturzeUczelni.StrategiaPensji;

import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Pracownicy;

public class SposobNaObliczanieUmowyOPrace implements ObliczaniePensji
{

    public double obliczaniePensji(Pracownicy p)
    {
        double pensja = 0;

        if(p.getTypUmowy().equalsIgnoreCase("Umowa o Prace"))
        {
            int wymiarGodzin = p.getWymiarGodzinPracy();
            pensja = wymiarGodzin*30/8*100;
        }
        return pensja;
    }

}

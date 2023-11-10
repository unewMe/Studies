package KlasyOperacyjneNaStrukturzeUczelni.StrategiaPensji;

import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Pracownicy;

public class SposobNaObliczanieUmowyZlecenie implements ObliczaniePensji
{

    public double obliczaniePensji(Pracownicy p)
    {
        double pensja = 0;
        double stawkaGodzinowa = 24;

        if(p.getTypUmowy().equals("Umowa Zlecenie"))
        {
            int wymiarGodzin = p.getWymiarGodzinPracy();


            pensja = wymiarGodzin*stawkaGodzinowa;
        }

        return pensja;
    }


}

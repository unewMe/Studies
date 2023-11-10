package KlasyOperacyjneNaStrukturzeUczelni.PensjaPracownika;


import KlasyOperacyjneNaStrukturzeUczelni.StrategiaPensji.SposobNaObliczanieUmowyOPrace;
import KlasyOperacyjneNaStrukturzeUczelni.StrategiaPensji.SposobNaObliczanieUmowyZlecenie;

public class UstalaniePensji extends Pensja
{

    public void wybierzStrategie(String Strategia)
    {
        switch (Strategia)
        {
            case "Umowa o Prace":
            {
                ObPen = new SposobNaObliczanieUmowyOPrace();
            }
            break;
            case "Umowa Zlecenie":
            {
                ObPen = new SposobNaObliczanieUmowyZlecenie();
            }
            break;

        }
    }





}

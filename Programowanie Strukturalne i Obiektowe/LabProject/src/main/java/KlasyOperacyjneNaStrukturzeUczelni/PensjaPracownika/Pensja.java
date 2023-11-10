package KlasyOperacyjneNaStrukturzeUczelni.PensjaPracownika;

import KlasyOperacyjneNaStrukturzeUczelni.StrategiaPensji.ObliczaniePensji;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Pracownicy;

public abstract class Pensja
{
        protected ObliczaniePensji ObPen;

        public double obliczaniePensji(Pracownicy p)
        {
            double pensja = ObPen.obliczaniePensji(p);
            return pensja;
        }

}

package KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni;

import java.io.Serializable;

public class Kurs implements Serializable
{
    private static final long serialVersionUID = 34564313213L;
    private String nazwaKursu;
    private double ocena=0;

    public Kurs(String nazwaKursu)
    {
        this.nazwaKursu = nazwaKursu;
    }

    public Kurs(String nazwaKursu, double ocena)
    {
        this.nazwaKursu = nazwaKursu;
        this.ocena = ocena;

    }

    public double getOcena()
    {
        return ocena;
    }

    public String getNazwaKursu()
    {
        return nazwaKursu;
    }

    public void setOcena(double ocena)
    {
        this.ocena = ocena;
    }

    public String toString()
    {
        String s = nazwaKursu;
        if(ocena!=0)
        {
            s+="\tOcena: " + ocena;
        }
        return s;
    }

}

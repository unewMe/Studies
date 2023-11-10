package KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni;

import java.io.Serializable;

public abstract class Osoby implements Serializable
{

    private static final long serialVersionUID = 3567918L;

    private String imie;
    private String nazwisko;
    private String pesel;
    private String miejsceZamieszakania;
    private int wiek;


    public Osoby(String imie,String nazwisko,String pesel,String miejsceZamieszakania,int wiek)
    {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.miejsceZamieszakania = miejsceZamieszakania;
        this.wiek = wiek;

    }

    public String getPesel()
    {
        return pesel;
    }

    //Pobieranie pelnych informacji na temat osoby
    public String infoOsoba()
    {
        return String.format("%-20s %-20s %-20s %-20d %-20s",imie,nazwisko,pesel,wiek,miejsceZamieszakania);
    }


    //Przekazywanie czesciowych danych do wyswietlenia
    public String toString()
    {
        return String.format("%-10s %-10s",nazwisko,imie);
    }


}

package KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni;

public class PracownicyZewnetrzni extends Pracownicy
{
    private String nazwaFrimy;
    private String budynekRemontowy;
    private int czasZleceniaPracyWMiesiacach;

    public PracownicyZewnetrzni(String imie,String nazwisko,String pesel,String miejscezamieszkania,int wiek,String typUmowy,String nazwaFrimy)
    {
        super(imie, nazwisko, pesel, miejscezamieszkania, wiek, typUmowy);
        this.nazwaFrimy = nazwaFrimy;

    }

    public void setBudynekRemontowy(String budynekRemontowy)
    {
        this.budynekRemontowy = budynekRemontowy;
    }

    public void setCzasZleceniaPracyWMiesiacach(int czasZleceniaPracyWMiesiacach)
    {
        this.czasZleceniaPracyWMiesiacach = czasZleceniaPracyWMiesiacach;
    }

}

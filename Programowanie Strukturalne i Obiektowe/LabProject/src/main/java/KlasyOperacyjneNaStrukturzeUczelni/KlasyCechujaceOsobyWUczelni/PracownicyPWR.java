package KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni;

public class PracownicyPWR extends Pracownicy
{
    private double premia=0;
    private String stopienNaukowy;

    public PracownicyPWR(String imie,String nazwisko,String pesel,String miejscezamieszkania,int wiek,String typUmowy,String stopienNaukowy)
    {
        super(imie, nazwisko, pesel, miejscezamieszkania, wiek, typUmowy);
        this.stopienNaukowy = stopienNaukowy;
    }

    public void setPremia(double premia)
    {
        this.premia = premia;
    }

}

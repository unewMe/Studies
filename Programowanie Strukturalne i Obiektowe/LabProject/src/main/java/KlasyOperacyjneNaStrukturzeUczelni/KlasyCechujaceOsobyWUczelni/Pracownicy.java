package KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni;

public abstract class Pracownicy extends Osoby
{
    private String typUmowy;
    private int wymiarGodzinPracy;

    private int nadgodzinny;
    private double pensja;

    public Pracownicy(String imie,String nazwisko,String pesel,String miejscezamieszkania, int wiek,String typUmowy,int wymiarGodzinPracy)
    {
        super(imie,nazwisko,pesel,miejscezamieszkania,wiek);
        this.typUmowy = typUmowy;
        this.wymiarGodzinPracy = wymiarGodzinPracy;

    }

    public Pracownicy(String imie,String nazwisko,String pesel,String miejscezamieszkania, int wiek,String typUmowy)
    {
        super(imie,nazwisko,pesel,miejscezamieszkania,wiek);
        this.typUmowy = typUmowy;

    }

    public String getTypUmowy()
    {
        return typUmowy;
    }

    public void setWymiarGodzinPracy(int wymiarGodzinPracy)
    {
        this.wymiarGodzinPracy = wymiarGodzinPracy;
    }

    public int getWymiarGodzinPracy()
    {
        return wymiarGodzinPracy;
    }

    public void setPensja(double pensja)
    {
        this.pensja = pensja;
    }

    public void setNadgodzinny(int nadgodzinny) {
        this.nadgodzinny = nadgodzinny;
    }

    public int getNadgodzinny() {
        return nadgodzinny;
    }

    //Pobieranie pelnych danych na temat pracownikow
    public String infoPracownicy()
    {
        return super.infoOsoba() + String.format("%-20s %-20d %-20d %-20f",typUmowy,wymiarGodzinPracy,nadgodzinny,pensja);
    }

    //Pobieranie danych do wyswietlenia
    public String toString()
    {
        return super.toString() + super.getPesel();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Pracownicy)
        {
            Pracownicy pom = (Pracownicy) obj;
            if(super.getPesel().equals(pom.getPesel()))
                return true;
            else
                return false;
        }
        return false;
    }
}

package KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni;
public class StudentZaoczny extends Student
{

    private String czyPracujeNaEtat;


    public StudentZaoczny(String imie,String nazwisko,String pesel,String miejscezamieszkania,int wiek,String indeks,int rokStudiow,int odOdZamieszkania,String czyPracujeNaEtat)
    {
        super(imie,nazwisko,pesel,miejscezamieszkania,wiek,indeks,rokStudiow,odOdZamieszkania);
        super.setTypStudiow("Zaoczne");
        this.czyPracujeNaEtat = czyPracujeNaEtat;

    }

    public String getCzyPracujeNaEtat()
    {
        return czyPracujeNaEtat;
    }


}

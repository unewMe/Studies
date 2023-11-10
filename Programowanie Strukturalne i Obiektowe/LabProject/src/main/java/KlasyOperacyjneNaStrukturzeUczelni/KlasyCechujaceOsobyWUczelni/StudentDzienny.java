package KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni;


public class StudentDzienny extends Student
{
    private String czyUdzielaSieWSamorzadzie;

    public StudentDzienny(String imie,String nazwisko,String pesel,String miejscezamieszkania,int wiek,String indeks,int rokStudiow,int odOdZamieszkania,String czyUdzielaSieWSamorzadzie)
    {
        super(imie,nazwisko,pesel,miejscezamieszkania,wiek,indeks,rokStudiow,odOdZamieszkania);
        super.setTypStudiow("Dzienne");
        this.czyUdzielaSieWSamorzadzie = czyUdzielaSieWSamorzadzie;
    }

    public String getCzyUdzielaSieWSamorzadzie()
    {
        return czyUdzielaSieWSamorzadzie;
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }
}

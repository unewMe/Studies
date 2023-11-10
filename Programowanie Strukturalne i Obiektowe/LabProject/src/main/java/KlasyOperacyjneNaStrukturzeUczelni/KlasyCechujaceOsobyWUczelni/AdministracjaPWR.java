package KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni;

public class AdministracjaPWR

{
    //Ustalanie premii
    public static void ustalaniePremii(PracownicyPWR p)
    {
        int nadGodziny = p.getNadgodzinny();

        if(nadGodziny>0)
        {
            double premia = (double) Math.round((((double)(nadGodziny)/(double)(p.getWymiarGodzinPracy()))*1000)*100)/100;
            p.setPremia(premia);
        }
    }

}

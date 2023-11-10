package KlasyOperacyjneNaStrukturzeUczelni.StypendiumStudenta;

import KlasyOperacyjneNaStrukturzeUczelni.StrategiaStypendium.SposobNaObliczanieStudendium_2;
import KlasyOperacyjneNaStrukturzeUczelni.StrategiaStypendium.SposobNaObliczanieStypendium_1;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UstalanieRodzajuStypendium extends Stypendium
{
    public void wybierzStrategie()
    {
        Scanner odczyt = new Scanner(new InputStreamReader(System.in));
        int sposob=-1;
        System.out.println("Wybierz sposob obliczania:");
        System.out.println("1.Wedlug odleglosci zamieszkania i ocen");
        System.out.println("2.Wedlug dodatkowych zajec i ocen");
        try
        {
            sposob=odczyt.nextInt();odczyt.nextLine();
        }
        catch (InputMismatchException a)
        {
            System.out.println("Podano zly format");
            odczyt.nextLine();
        }

        switch (sposob)
        {
            case 1:
            {
                Ob = new SposobNaObliczanieStypendium_1();
            }
            break;
            case 2:
            {
                Ob = new SposobNaObliczanieStudendium_2();
            }
            break;

        }

    }

    public void setStrategyNaOdlegloscIOceny()
    {
        Ob = new SposobNaObliczanieStypendium_1();
    }

    public void setStrategyNaZajeciaIOceny()
    {
        Ob = new SposobNaObliczanieStudendium_2();
    }



}

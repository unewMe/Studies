package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers;

import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Osoby;

import java.util.ArrayList;

public interface Observer
{
    public void update(ArrayList<Osoby> osoby);
}

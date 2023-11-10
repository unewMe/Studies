package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.Observer;

public interface Subject {

    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}

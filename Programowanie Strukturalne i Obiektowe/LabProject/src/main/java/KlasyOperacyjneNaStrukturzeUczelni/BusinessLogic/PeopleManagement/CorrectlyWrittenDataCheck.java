package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement;

import Exceptions.StringFormatException;
import PeopleCreationPatterns.CheckPersonDataPatterns;

public class CorrectlyWrittenDataCheck
{


    //Sprawdzanie poprawnosci zapisanych danych
    public static boolean isCorrectlyWrittenWorker(String pom[]) throws StringFormatException
    {
        if (!(pom.length == 8))
            return false;


            String wrongWords = "";
            boolean nameStan = CheckPersonDataPatterns.letterPattern(pom[1]);
            boolean surnameStan = CheckPersonDataPatterns.letterPattern(pom[2]);
            boolean idStan = CheckPersonDataPatterns.numberPattern(pom[3]);
            boolean placeOfResidenceStan = CheckPersonDataPatterns.letterPattern(pom[4]);
            boolean ageStan = CheckPersonDataPatterns.numberPattern(pom[5]);
            boolean academicDegreeOrCompanyNameStan = CheckPersonDataPatterns.letterPattern(pom[7]);

            if (!nameStan) {
                wrongWords += "imie,";
            }
            if (!surnameStan) {
                wrongWords += "nazwisko,";
            }
            if (!idStan) {
                wrongWords += "pesel,";
            }
            if (!placeOfResidenceStan) {
                wrongWords += "miejsce zamieszkania,";
            }
            if (!ageStan) {
                wrongWords += "wiek,";
            }
            if (!academicDegreeOrCompanyNameStan)
            {
                if (pom[0].equalsIgnoreCase("PracownikPWR"))
                {
                    wrongWords += "stopien naukowy";
                } else if (pom[0].equalsIgnoreCase("PracownikZewnetrzny"))
                {
                    wrongWords += "nazwa firmy, ";
                }
            }

            if (wrongWords.length() != 0)
            {
                throw new StringFormatException("Podano zly format daty w zakladce " + wrongWords);
            }

            if(!pom[6].equalsIgnoreCase("UmowaOPrace") && !pom[6].equalsIgnoreCase("UmowaZlecenie"))
                return false;

            return true;

    }

    public static boolean isCorrectlyWrittenStudent(String pom[]) throws StringFormatException
    {
        if(!(pom.length==10))
            return false;
        if(!pom[9].equalsIgnoreCase("Tak") && !pom[9].equalsIgnoreCase("Nie"))
            return false;


            String wrongWords="";
            boolean stateName= CheckPersonDataPatterns.letterPattern(pom[1]);
            boolean stateSurname= CheckPersonDataPatterns.letterPattern(pom[2]);
            boolean stateId= CheckPersonDataPatterns.numberPattern(pom[3]);
            boolean statePlaceOfResidence= CheckPersonDataPatterns.letterPattern(pom[4]);
            boolean stateIndex= CheckPersonDataPatterns.numberPattern(pom[6]);
            boolean stateAge = CheckPersonDataPatterns.numberPattern(pom[5]);
            boolean stateStudyYear= CheckPersonDataPatterns.numberPattern(pom[7]);
            boolean stateDistanceFromHome= CheckPersonDataPatterns.numberPattern(pom[8]);

            if(!stateName)
            {
                wrongWords+="imie,";
            }
            if(!stateSurname)
            {
                wrongWords+="nazwisko,";
            }
            if(!stateId)
            {
                wrongWords+="pesel,";
            }
            if(!statePlaceOfResidence)
            {
                wrongWords+="miejsce zamieszkania,";
            }
            if(!stateIndex)
            {
                wrongWords+="indeks,";
            }
            if(!stateAge)
            {
                wrongWords+="wiek,";
            }
            if(!stateStudyYear)
            {
                wrongWords+="rok studiow,";
            }
            if(!stateDistanceFromHome)
            {
                wrongWords+="odleglosc od domu";
            }
            if(wrongWords.length()!=0)
            {
                throw new StringFormatException("Podano zly format daty w zakladce " + wrongWords);
            }


            return true;


    }
}

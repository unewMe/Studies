package KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic;

import Exceptions.StringFormatException;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.CorrectlyWrittenDataCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorrectlyWrittenDataCheckTest
{

    @Test
    void isCorrectlyWrittenWorker()
    {
        String allCorrectlyWritten[] = {"PracownicyPWR","Dawid","Chudzicki","12345","Czestochowa","20","UmowaOPrace","Doktor"};
        assertDoesNotThrow(()-> CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(allCorrectlyWritten));
        assertEquals(true,CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(allCorrectlyWritten));

    }

    @Test
    void isCorrectlyWrittenWorkerByHisPersonalData()
    {
        String allCorrectlyWritten[] = {"PracownikPWR","Dawid","Chudzicki","12345","Czestochowa","20","UmowaOPrace","Doktor"};
        String inCorrectlyWrittenName[] = allCorrectlyWritten.clone();inCorrectlyWrittenName[1]="D1wid";
        String inCorrectlyWrittenSurname[] = allCorrectlyWritten.clone();inCorrectlyWrittenSurname[2]="Ch1dzicki";
        String inCorrectlyWrittenID[] = allCorrectlyWritten.clone();inCorrectlyWrittenID[3]="12a45";
        String inCorrectlyWrittenPlaceOfResidence[] = allCorrectlyWritten.clone();inCorrectlyWrittenPlaceOfResidence[4]="Czestochow4";
        String inCorrectlyWrittenAge[] =allCorrectlyWritten.clone();inCorrectlyWrittenAge[5]="z0";
        String inCorrectlyWrittenContractType[] = allCorrectlyWritten.clone();inCorrectlyWrittenContractType[6]="Umowa1Prace";
        String inCorrectlyWrittenAcademicDegree[] =  allCorrectlyWritten.clone();inCorrectlyWrittenAcademicDegree[7]="Dokt4r";
        String inCorrectlyWrittenAll[] = {"PracownikPWR","D4wid","Ch1dzicki","12e45","Czestochow4","2d","Umowa1Prace","Do4tor"};

        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(inCorrectlyWrittenName));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(inCorrectlyWrittenSurname));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(inCorrectlyWrittenID));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(inCorrectlyWrittenPlaceOfResidence));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(inCorrectlyWrittenAge));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(inCorrectlyWrittenAcademicDegree));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(inCorrectlyWrittenAll));
        assertEquals(false,CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(inCorrectlyWrittenContractType));

    }

    @Test
    void isCorrectlyWrittenStudent()
    {

        String allCorrectlyWritten[] = {"StudentDzienny","Dawid","Chudzicki","12345","Czestochowa","20","5467","1","186","Tak"};
        assertDoesNotThrow(() -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(allCorrectlyWritten));
        assertTrue(CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(allCorrectlyWritten));
    }

    @Test
    void isInCorrectlyWrittenStudentByHisPersonalData()
    {

        String allCorrectlyWritten[] = {"StudentDzienny","Dawid","Chudzicki","12345","Czestochowa","20","5467","1","186","Tak"};
        String inCorrectlyWrittenName[] = allCorrectlyWritten.clone();inCorrectlyWrittenName[1]="D1wid";
        String inCorrectlyWrittenSurname[] = allCorrectlyWritten.clone();inCorrectlyWrittenSurname[2]="Ch1dzicki";
        String inCorrectlyWrittenID[] = allCorrectlyWritten.clone();inCorrectlyWrittenID[3]="12a45";
        String inCorrectlyWrittenPlaceOfResidence[] = allCorrectlyWritten.clone();inCorrectlyWrittenPlaceOfResidence[4]="Czestochow4";
        String inCorrectlyWrittenAge[] =allCorrectlyWritten.clone();inCorrectlyWrittenAge[5]="z0";
        String inCorrectlyWrittenIndex[] = allCorrectlyWritten.clone();inCorrectlyWrittenIndex[6]="567a";
        String inCorrectlyWrittenStudyYear[] = allCorrectlyWritten.clone();inCorrectlyWrittenStudyYear[7]="1a";
        String inCorrectlyWrittenDistanceFromHome[] = allCorrectlyWritten.clone();inCorrectlyWrittenDistanceFromHome[8]="18a";
        String inCorrectlyWrittenChoice[] = allCorrectlyWritten.clone();inCorrectlyWrittenChoice[9]="T4k";
        String inCorrectlyWrittenAll[] = {"StudentDzienny","D4wid","Ch1dzicki","12d45","Cz1stochowa","z0","546d","d","18d","Tak"};

        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenName));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenSurname));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenID));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenPlaceOfResidence));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenAge));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenIndex));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenStudyYear));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenDistanceFromHome));
        assertThrows(StringFormatException.class, () -> CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenAll));
        assertEquals(false,CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(inCorrectlyWrittenChoice));




    }
}
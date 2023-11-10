package obslugagui.labproject2.PopUp;

import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import java.util.Optional;

public class Dialogue
{
    public static void numberError()
    {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Zly format liczby");
        alertError.setHeaderText("Podano zly format liczby!!");
        alertError.setContentText("");
        alertError.showAndWait();
    }
    public static void studentCreatedInfo()
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Student");
        alertInfo.setHeaderText("Student zostal utworzony!!");
        alertInfo.setContentText("Powrot do menu");
        alertInfo.showAndWait();
    }

    public static Optional<ButtonType> confirmationWindow()
    {
        Alert confirmWindow = new Alert(Alert.AlertType.CONFIRMATION);
        confirmWindow.setTitle("Potwierdz");
        confirmWindow.setHeaderText("Czy na pewno chcesz opuscic program?");
        confirmWindow.setContentText("Nie zapisane zmiany zostana utracone!!");
        Optional<ButtonType> choice = confirmWindow.showAndWait();
        return choice;
    }

    public static void courseAddedInfo()
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Kurs");
        alertInfo.setHeaderText("Kurs zostal dodany!!");
        alertInfo.setContentText("Powrot do menu");
        alertInfo.showAndWait();
    }

    public static void gradeAddedInfo()
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Kurs");
        alertInfo.setHeaderText("Ocena zostala dodana!!");
        alertInfo.setContentText("Powrot do menu");
        alertInfo.showAndWait();
    }

    public static void setUpScholarshipInfo(double stypendium)
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Stypendium");
        alertInfo.setHeaderText("Stypendium w wysokosci: " + stypendium + " zostalo ustalone!!");
        alertInfo.setContentText("Powrot do menu");
        alertInfo.showAndWait();
    }

    public static void successfullySaved()
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Zmiany");
        alertInfo.setHeaderText("Plik zostal z powowdzeniem zapisany");
        alertInfo.showAndWait();
    }

    public static void unSuccessfullySaved()
    {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Zmiany");
        alertError.setHeaderText("Wystapil blad podczas zapisu");
        alertError.showAndWait();
    }

    public static void successfullyRead()
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Zmiany");
        alertInfo.setHeaderText("Plik zostal z powowdzeniem odczytany");
        alertInfo.showAndWait();
    }

    public static void unSuccessfullyRead()
    {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Zmiany");
        alertError.setHeaderText("Wystapil blad podczas odczytu");
        alertError.showAndWait();
    }

    public static void successfullyReadFile()
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Odczyt");
        alertInfo.setHeaderText("Pomyslnie Wczytano z Pliku");
        alertInfo.showAndWait();
    }

    public static void fileFormatError()
    {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText("Podano niepoprawny format pliku");
        alertError.showAndWait();
    }

    public static void unSuccessfullyReadFile()
    {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Zmiany");
        alertError.setHeaderText("Wystapil blad podczas odczytu");
        alertError.setContentText("Nie poprawny format danych z pliku lub podany like jest pusty");
        alertError.showAndWait();
    }

    public static void fileReadingWarning()
    {
        Alert alertWarning = new Alert(Alert.AlertType.WARNING);
        alertWarning.setTitle("Odczyt");
        alertWarning.setHeaderText("Dane w pliku powinny znajdowac sie w ponizszej postaci:");
        alertWarning.setContentText("Przyklad: StudentDzienny imie nazwisko wiek ..., Pelne Informacje na temat typow w zakladce Help");
        alertWarning.showAndWait();
    }

    public static void studentCourse(Student student)
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Kursy");

        TextArea textArea = new TextArea(student.infoKurs());
        alertInfo.setHeaderText("Kursy Studenta:");
        alertInfo.getDialogPane().setContent(textArea);
        alertInfo.showAndWait();
    }

    public static void unSuccessfullyCreatedPerson(String message)
    {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Format Error");
        alertError.setHeaderText(message);
        alertError.showAndWait();
    }

    public static void workerCreatedInfo()
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Pracownik");
        alertInfo.setHeaderText("Pracownik zostal utworzony!!");
        alertInfo.setContentText("Powrot do menu");
        alertInfo.showAndWait();
    }

    public static void succesfullySetWorkingTimeAndOverTime()
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Pracownik");
        alertInfo.setHeaderText("Ustalono wymiar godzin pracy i nagodzin!!");
        alertInfo.setContentText("Powrot do menu");
        alertInfo.showAndWait();
    }

    public static void salaryInfo(double salary)
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Pensja");
        alertInfo.setHeaderText("Pensja w wysokosci: " + salary + " zostala ustalona!!");
        alertInfo.setContentText("Powrot do menu");
        alertInfo.showAndWait();
    }

    public static void helpCenter()
    {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Informacje");

        TextArea textArea = new TextArea("StudentDzienny imie, nazwisko, pesel, miejsce zamiszkania,wiek,indeks,rok studiow,odleglosc od domu,czy udziela sie w samorzadzie(Tak,Nie) \n\n" +
                "StudentZaoczny imie, nazwisko, pesel, miejsce zamiszkania,wiek,indeks,rok studiow,odleglosc od domu,czy pracuje(Tak,Nie) \n\n" +"PracownikPWR imie, nazwisko, pesel, miejsce zamieszkania, wiek,typ umowy(UmowaOPrace,UmowaZlecenie),stopien naukowy \n\n" +
                "PracownikZewnetrzny imie, nazwisko, pesel, miejsce zamieszkania, wiek,typ umowy(UmowaOPrace,UmowaZlecenie),nazwa firmy \n");

        alertInfo.setHeaderText("Dane w pliku powinny być w postaci:");
        alertInfo.getDialogPane().setContent(textArea);
        alertInfo.getDialogPane().setContent(textArea);
        alertInfo.showAndWait();
    }

    public static void unSuccessfullyRemovedPerson(String message)
    {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText("Nie wybrano " + message);
        alertError.showAndWait();
    }





}

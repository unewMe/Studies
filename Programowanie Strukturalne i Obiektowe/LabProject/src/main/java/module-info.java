module obslugagui.labproject2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens obslugagui.labproject2 to javafx.fxml;
    exports obslugagui.labproject2;
    exports obslugagui.labproject2.Student;
    opens obslugagui.labproject2.Student to javafx.fxml;
    exports obslugagui.labproject2.Pracownik;
    opens obslugagui.labproject2.Pracownik to javafx.fxml;
}
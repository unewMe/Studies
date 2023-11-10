package obslugagui.labproject2.Student;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.StudentsModel;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.StudentMethods;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Kurs;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import obslugagui.labproject2.MainController;
import obslugagui.labproject2.PopUp.Dialogue;

public class AddGradePanelController
{

    private MainController mainController;
    @FXML
    private ComboBox<Student> comboBoxStudent;
    @FXML
    private ComboBox<Kurs> comboBoxCourse;
    @FXML
    private TextField gradeField;
    @FXML
    private Button submitButton;

    private StudentsModel studentsModel = new StudentsModel();

    @FXML
    public void initialize()
    {
        comboBoxStudent.disableProperty().bind(gradeField.textProperty().isEmpty());
        comboBoxCourse.disableProperty().bind(comboBoxStudent.getSelectionModel().selectedItemProperty().isNull());
        submitButton.disableProperty().bind(comboBoxCourse.getSelectionModel().selectedItemProperty().isNull());
        studentsModel.initializeStudents();
        comboBoxStudent.setItems(studentsModel.getStudentsList());
    }

    public void comboBoxAction(ActionEvent event)
    {
            studentsModel.initializeCourse(comboBoxStudent.getSelectionModel().getSelectedItem());
            comboBoxCourse.setItems(studentsModel.getCoursesList());
    }



    public void submitButtonPressed()
    {
        String grade = gradeField.getText();
        Student student = comboBoxStudent.getSelectionModel().getSelectedItem();
        Kurs kurs = comboBoxCourse.getSelectionModel().getSelectedItem();
        try
        {
            int ocenaPom = Integer.parseInt(grade);
            StudentMethods.addGradeToCourse(student,kurs,ocenaPom);
            Dialogue.gradeAddedInfo();
        }
        catch (NumberFormatException a)
        {
            Dialogue.numberError();
        }

        mainController.getBorderPane().setCenter(null);
        mainController.getTopButtonsPanelController().setPeopleList();


    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }
}

package obslugagui.labproject2.Student;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.SetDataBase;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.StudentsModel;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.StudentMethods;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import obslugagui.labproject2.MainController;
import obslugagui.labproject2.PopUp.Dialogue;

public class AddCoursePanelController
{

    private MainController mainController;
    @FXML
    private ComboBox<Student> comboBox;
    @FXML
    private Button submitButton;
    @FXML
    private TextField courseField;


    @FXML
    public void initialize()
    {
        submitButton.disableProperty().bind(
                Bindings.or(
                courseField.textProperty().isEmpty(),comboBox.getSelectionModel().selectedItemProperty().isNull()
                ));


        StudentsModel studentsModel = new StudentsModel();
        SetDataBase.register(studentsModel);
        studentsModel.initializeStudents();
        comboBox.setItems(studentsModel.getStudentsList());

    }



    public void submitButtonPressed()
    {
       Student student =  comboBox.getSelectionModel().getSelectedItem();
       String courseName = courseField.getText();
       StudentMethods.addCourse(student,courseName);
       Dialogue.courseAddedInfo();
       mainController.getBorderPane().setCenter(null);
       mainController.getTopButtonsPanelController().setPeopleList();

    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }
}

package obslugagui.labproject2.Student;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.StudentsModel;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.StudentMethods;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import obslugagui.labproject2.MainController;
import obslugagui.labproject2.PopUp.Dialogue;

public class ScholarshipPanelController
{

    private MainController mainController;
    @FXML
    private Button submitButton;
    @FXML
    private RadioButton activitiesRadioButton;
    @FXML
    private RadioButton distanceFromHomeRadioButton;
    @FXML
    private ComboBox<Student> comboBox;

    public void initialize()
    {
        activitiesRadioButton.disableProperty().bind(comboBox.getSelectionModel().selectedItemProperty().isNull());
        distanceFromHomeRadioButton.disableProperty().bind(comboBox.getSelectionModel().selectedItemProperty().isNull());

        submitButton.disableProperty().bind(
                Bindings.and(
                        activitiesRadioButton.selectedProperty().not(),
                        distanceFromHomeRadioButton.selectedProperty().not()
                )
        );

        StudentsModel studentsModel = new StudentsModel();
        studentsModel.initializeStudents();
        comboBox.setItems(studentsModel.getStudentsList());


    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void submitButtonPressed()
    {
        Student student = comboBox.getSelectionModel().getSelectedItem();
        double scholarship=0;

        if(distanceFromHomeRadioButton.isSelected())
        {
            scholarship= StudentMethods.setScholarship(student,1);
        }
        else if(activitiesRadioButton.isSelected())
        {
            scholarship= StudentMethods.setScholarship(student,2);
        }
        Dialogue.setUpScholarshipInfo(scholarship);


        mainController.getBorderPane().setCenter(null);
        mainController.getTopButtonsPanelController().setPeopleList();
    }





}

package obslugagui.labproject2.Student;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.SetDataBase;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.StudentsListModel;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.StudentMethods;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import obslugagui.labproject2.PopUp.Dialogue;

public class StudentsListPanelController
{
    @FXML
    private Button showCourseButton;
    @FXML
    private Button removeStudentButton;
    @FXML
    private ListView<String> listView;

    public void initialize()
    {
        showCourseButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
        removeStudentButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
        StudentsListModel studentsListModel = new StudentsListModel();
        SetDataBase.register(studentsListModel);
        studentsListModel.initializeStudents();
        listView.setItems(studentsListModel.getStudentsList());
    }

    public void buttonPressed(ActionEvent event)
    {
        String pom[] = listView.getSelectionModel().getSelectedItem().split("\\s+");
        Object source = event.getSource();
        if(source==showCourseButton)
        {
            Student student = StudentMethods.getStudent(pom[5]);
            if(student!=null)
                Dialogue.studentCourse(student);
            else
                Dialogue.unSuccessfullyRemovedPerson("studenta");
        }
        else if(source==removeStudentButton)
        {
            boolean state = StudentMethods.remove(pom[5]);
            if(!state)
                Dialogue.unSuccessfullyRemovedPerson("studenta");

        }

    }

}

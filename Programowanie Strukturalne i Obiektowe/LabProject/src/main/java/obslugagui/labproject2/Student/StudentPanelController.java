package obslugagui.labproject2.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import obslugagui.labproject2.*;

public class StudentPanelController
{
    @FXML
    private Button addStudentButton;
    @FXML
    private Button addGradeButton;
    @FXML
    private Button addCourseButton;
    @FXML
    private Button setScholarshipButton;

    private MainController mainController;


    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void buttonPressed(ActionEvent event)
    {
        Object source = event.getSource();

        if(source==addStudentButton)
        {
            CreateStudentPanelController createStudentPanelController = (CreateStudentPanelController) mainController.addPane("createStudentPanel.fxml");
            createStudentPanelController.setMainController(mainController);
        }
        else if(source==addCourseButton)
        {
            AddCoursePanelController addCoursePanelController = (AddCoursePanelController) mainController.addPane("addCoursePanel.fxml");
            addCoursePanelController.setMainController(mainController);
        }
        else if(source==addGradeButton)
        {
            AddGradePanelController addGradePanelController= (AddGradePanelController) mainController.addPane("addGradePanel.fxml");
            addGradePanelController.setMainController(mainController);
        }
        else if(source==setScholarshipButton)
        {
            ScholarshipPanelController scholarshipPanelController = (ScholarshipPanelController) mainController.addPane("scholarshipPanel.fxml");
            scholarshipPanelController.setMainController(mainController);
        }



    }


}

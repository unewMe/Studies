package obslugagui.labproject2.Student;

import Exceptions.StringFormatException;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.CorrectlyWrittenDataCheck;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.StudentMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import obslugagui.labproject2.MainController;
import obslugagui.labproject2.PopUp.Dialogue;

public class CreateStudentPanelController
{


    @FXML
    private Button submitButton;

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField placeOfResidenceField;
    @FXML
    private TextField ageField;
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton fullTimeRadioButton;
    @FXML
    private RadioButton extramuralRadioButton;
    @FXML
    private TextField studyYearField;
    @FXML
    private TextField distanceFromHomeField;
    @FXML
    private TextField indexField;
    @FXML
    private RadioButton yesRadioButton;
    @FXML
    private MainController mainController;

    @FXML
    private ToggleGroup yesOrNoSelection;



    @FXML
    public void initialize()
    {


    }

    public void radioButtonAction(ActionEvent event)
    {
        Object source = event.getSource();
        if(source==fullTimeRadioButton)
        {
            questionLabel.setText("Czy udziela sie w samorzadzie?");
        }
        else if(source==extramuralRadioButton)
        {
            questionLabel.setText("Czy pracuje?");
        }
    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void submitButtonPressed()
    {
        boolean state = true;
        String pom[]=new String[10];

        String name = nameField.getText();pom[1]=name;
        String surname = surnameField.getText();pom[2]=surname;
        String id = idField.getText();pom[3]=id;
        String placeOfResidence = placeOfResidenceField.getText();pom[4]=placeOfResidence;
        String age = ageField.getText();pom[5]=age;
        String index = indexField.getText();pom[6]=index;
        String studyYear = studyYearField.getText();pom[7]=studyYear;
        String distanceFromHome= distanceFromHomeField.getText();pom[8]=distanceFromHome;
        String studentCouncilorWork ="";


        if(yesRadioButton.isSelected())
            studentCouncilorWork="Tak";
        else
            studentCouncilorWork="Nie";


        pom[9]=studentCouncilorWork;


        if(fullTimeRadioButton.isSelected())
            pom[0]="StudentDzienny";
        else
            pom[0]="StudentZaoczny";


        try
        {
            CorrectlyWrittenDataCheck.isCorrectlyWrittenStudent(pom);
        }
        catch (StringFormatException a)
        {
            state=false;
            Dialogue.unSuccessfullyCreatedPerson(a.getMessage());

        }

        if(state)
        {
            if(fullTimeRadioButton.isSelected())
            {
                StudentMethods.addFullTimeStudent(name, surname, id, placeOfResidence, Integer.parseInt(age), index, Integer.parseInt(studyYear), Integer.parseInt(distanceFromHome), studentCouncilorWork);

            }
            else if(extramuralRadioButton.isSelected())
            {
                StudentMethods.addExtramuralyStudent(name, surname, id, placeOfResidence, Integer.parseInt(age), index, Integer.parseInt(studyYear), Integer.parseInt(distanceFromHome), studentCouncilorWork);
            }
            Dialogue.studentCreatedInfo();
            mainController.getBorderPane().setCenter(null);
            mainController.getTopButtonsPanelController().setPeopleList();
        }









    }



}

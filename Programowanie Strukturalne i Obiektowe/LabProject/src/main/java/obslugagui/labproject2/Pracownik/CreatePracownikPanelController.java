package obslugagui.labproject2.Pracownik;

import Exceptions.StringFormatException;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.CorrectlyWrittenDataCheck;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.WorkerMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import obslugagui.labproject2.MainController;
import obslugagui.labproject2.PopUp.Dialogue;

public class CreatePracownikPanelController
{
    @FXML
    private Button submitButton;
    @FXML
    private RadioButton pwrWorkerRadioButton;
    @FXML
    private RadioButton externalWorkerRadioButton;
    @FXML
    private RadioButton employmentContractRadioButton;
    @FXML
    private RadioButton taskContractRadioButton;
    @FXML
    private Label academicDegreeOrCompanyNameLabel;
    @FXML
    private TextField academicDegreeOrCompanyNameField;

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField placeOfResidenceField;
    @FXML
    private ToggleGroup contractSelection;
    private MainController mainController;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void workerRadioButtonAction(ActionEvent event)
    {
        Object source = event.getSource();
        if(source==pwrWorkerRadioButton)
        {
            academicDegreeOrCompanyNameLabel.setText("Podaj stopien naukowy:");
        }
        else if(source==externalWorkerRadioButton)
        {
            academicDegreeOrCompanyNameLabel.setText("Podaj nazwe firmy:");
        }
    }

    public void submitButtonPressed()
    {
        RadioButton radioButton = (RadioButton) contractSelection.getSelectedToggle();
        String pom[] = new String[8];
        String name = nameField.getText();pom[1]=name;
        String surname = surnameField.getText();pom[2]=surname;
        String id = idField.getText();pom[3]=id;
        String placeOfResidence = placeOfResidenceField.getText();pom[4]=placeOfResidence;
        String age = ageField.getText();pom[5]=age;
        String contract = radioButton.getText();pom[6]=contract;
        String academicDegreeOrCompanyName = academicDegreeOrCompanyNameField.getText();pom[7]=academicDegreeOrCompanyName;

        boolean stan=true;
        if(pwrWorkerRadioButton.isSelected())
        {
            pom[0]="PracownikPWR";
        }
        else
        {
            pom[0]="PracownikZewnetrzny";
        }

        try
        {
            CorrectlyWrittenDataCheck.isCorrectlyWrittenWorker(pom);

        }
        catch (StringFormatException a)
        {
            stan=false;
            Dialogue.unSuccessfullyCreatedPerson(a.getMessage());
        }

        if(stan)
        {

            if(pwrWorkerRadioButton.isSelected())
            {
                WorkerMethods.addPwrWorker(name,surname,id,placeOfResidence,Integer.parseInt(age),contract,academicDegreeOrCompanyName);
            }
            else if(externalWorkerRadioButton.isSelected())
            {
                WorkerMethods.addExternalWorker(name,surname,id,placeOfResidence,Integer.parseInt(age),contract,academicDegreeOrCompanyName);
            }

            Dialogue.workerCreatedInfo();
            mainController.getBorderPane().setCenter(null);
            mainController.getTopButtonsPanelController().setPeopleList();

        }
    }
}

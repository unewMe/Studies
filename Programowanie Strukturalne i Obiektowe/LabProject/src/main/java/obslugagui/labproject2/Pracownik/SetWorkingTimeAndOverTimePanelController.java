package obslugagui.labproject2.Pracownik;


import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.WorkersModel;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.SetDataBase;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Pracownicy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import obslugagui.labproject2.MainController;
import obslugagui.labproject2.PopUp.Dialogue;

public class SetWorkingTimeAndOverTimePanelController
{

    @FXML
    private Button submitButton;
    @FXML
    private ComboBox<Pracownicy> comboBox;
    @FXML
    private TextField workingTimeField;
    @FXML
    private TextField overtimeField;
    private MainController mainController;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void initialize()
    {
        overtimeField.disableProperty().bind(workingTimeField.textProperty().isEmpty());
        comboBox.disableProperty().bind(overtimeField.textProperty().isEmpty());
        submitButton.setDisable(true);
        WorkersModel workersModel = new WorkersModel();
        SetDataBase.register(workersModel);
        workersModel.initializeWorkers();
        comboBox.setItems(workersModel.getWorkersList());

    }

    public void comboBoxAction()
    {
        submitButton.setDisable(false);
    }

    public void submitButtonPressed()
    {
        Pracownicy worker = comboBox.getSelectionModel().getSelectedItem();
        String workingTime = workingTimeField.getText();
        String overtime = overtimeField.getText();
        int workingTimePom = -1;
        int overtimePom = -1;
        try
        {
               workingTimePom = Integer.parseInt(workingTime);
               overtimePom = Integer.parseInt(overtime);
               worker.setWymiarGodzinPracy(workingTimePom);
               worker.setNadgodzinny(overtimePom);
               Dialogue.succesfullySetWorkingTimeAndOverTime();
               mainController.getBorderPane().setCenter(null);
               mainController.getTopButtonsPanelController().setPeopleList();

        }
        catch (NumberFormatException a)
        {
            Dialogue.numberError();
        }
    }
}

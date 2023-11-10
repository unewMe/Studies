package obslugagui.labproject2.Pracownik;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.WorkersModel;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.SetDataBase;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.WorkerMethods;
import KlasyOperacyjneNaStrukturzeUczelni.KlasyCechujaceOsobyWUczelni.Pracownicy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import obslugagui.labproject2.MainController;
import obslugagui.labproject2.PopUp.Dialogue;

public class SetSalaryPanelController
{
    @FXML
    private ComboBox<Pracownicy> comboBox;
    @FXML
    private Button submitButton;

    private MainController mainController;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void initialize()
    {
        submitButton.disableProperty().bind(comboBox.getSelectionModel().selectedItemProperty().isNull());
        WorkersModel workersModel = new WorkersModel();
        workersModel.initializeWorkers();
        SetDataBase.register(workersModel);
        comboBox.setItems(workersModel.getWorkersList());
    }

    public void submitButtonPressed()
    {
        Pracownicy worker = comboBox.getSelectionModel().getSelectedItem();
        double salary = WorkerMethods.setSalary(worker);
        Dialogue.salaryInfo(salary);
        mainController.getBorderPane().setCenter(null);
        mainController.getTopButtonsPanelController().setPeopleList();

    }

}

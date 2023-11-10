package obslugagui.labproject2.Pracownik;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.WorkersListModel;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.SetDataBase;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.WorkerMethods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import obslugagui.labproject2.PopUp.Dialogue;

public class WorkersListPanelController
{
    @FXML
    private ListView<String> listView;

    @FXML
    private Button removeWorkerButton;

    public void initialize()
    {
        removeWorkerButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
        WorkersListModel workersListModel = new WorkersListModel();
        SetDataBase.register(workersListModel);
        workersListModel.initializeWorkers();
        listView.setItems(workersListModel.getWorkersList());
    }

    public void buttonPressed()
    {
        String pom[] = listView.getSelectionModel().getSelectedItem().split("\\s+");
        boolean state = WorkerMethods.remove(pom[2]);
        if(!state)
        {
            Dialogue.unSuccessfullyRemovedPerson("pracownika");
        }
    }
}

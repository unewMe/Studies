package obslugagui.labproject2;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observers.PeopleListModel;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.SetDataBase;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PeopleListController
{

    @FXML
    private ListView<String> listView;


    @FXML
    public void initialize()
    {
        PeopleListModel peopleListModel = new PeopleListModel();
        SetDataBase.register(peopleListModel);
        peopleListModel.initializePeople();

        listView.setItems(peopleListModel.getPeopleList());
    }


}

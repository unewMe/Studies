package obslugagui.labproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import obslugagui.labproject2.Pracownik.WorkerPanelController;
import obslugagui.labproject2.Student.StudentPanelController;

public class TopButtonsPanelController
{


    private MainController mainController;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private ToggleButton peopleList;
    @FXML
    private ToggleButton studentsList;
    @FXML
    private ToggleButton workersList;
    @FXML
    private Button studentsPanel;
    @FXML
    private Button workersPanel;


    public void initialize()
    {
        peopleList.setSelected(true);
    }

    public void setPeopleList()
    {
        ActionEvent actionEvent = new ActionEvent(peopleList,null);
        buttonPressed(actionEvent);
    }

    public void setMainView(MainController mainController)
    {
        this.mainController = mainController;
        setPeopleList();
    }

    @FXML
    public void buttonPressed(ActionEvent actionEvent)
    {
        Object source = actionEvent.getSource();
        if(source==peopleList)
        {
            mainController.addPane("peopleList.fxml");
        }
        else if(source == studentsPanel)
        {
            if(toggleGroup.getSelectedToggle()!=null)
                toggleGroup.getSelectedToggle().setSelected(false);

            StudentPanelController studentPanelController = (StudentPanelController) mainController.addPane("studentPanel.fxml");
            studentPanelController.setMainController(mainController);
        }
        else if(source==workersPanel)
        {
            if(toggleGroup.getSelectedToggle()!=null)
                toggleGroup.getSelectedToggle().setSelected(false);

            WorkerPanelController workerPanelController = (WorkerPanelController) mainController.addPane("workerPanel.fxml");
            workerPanelController.setMainController(mainController);
        }
        else if(source==studentsList)
        {
            mainController.addPane("studentsList.fxml");
        }
        else if(source==workersList)
        {
            mainController.addPane("workersList.fxml");
        }



    }
}

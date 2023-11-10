package obslugagui.labproject2.Pracownik;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import obslugagui.labproject2.MainController;

public class WorkerPanelController
{
    @FXML
    private Button setWorkingTimeAndOvertimeButton;
    @FXML
    private Button setSalaryButton;
    @FXML
    private Button createPwrWorkerButton;


    private MainController mainController;

    public void buttonPressed(ActionEvent event)
    {
        Object source = event.getSource();
        if(source==createPwrWorkerButton)
        {
            CreatePracownikPanelController createPracownikPanelController = (CreatePracownikPanelController) mainController.addPane("createWorkerPanel.fxml");
            createPracownikPanelController.setMainController(mainController);
        }
        else if(source==setWorkingTimeAndOvertimeButton)
        {
           SetWorkingTimeAndOverTimePanelController setWorkingTimeAndOverTimePanelController = (SetWorkingTimeAndOverTimePanelController) mainController.addPane("setWorkingTimeAndOvertimePanel.fxml");
           setWorkingTimeAndOverTimePanelController.setMainController(mainController);
        }
        else if(source==setSalaryButton)
        {
            SetSalaryPanelController setSalaryPanelController = (SetSalaryPanelController) mainController.addPane("setSalaryPanel.fxml");
            setSalaryPanelController.setMainController(mainController);
        }

    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }
}

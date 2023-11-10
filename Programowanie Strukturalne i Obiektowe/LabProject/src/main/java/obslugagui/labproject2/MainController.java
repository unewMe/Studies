package obslugagui.labproject2;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.Observable.DataBase;
import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.ReadData;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import obslugagui.labproject2.PopUp.Dialogue;
import obslugagui.labproject2.Student.StudentPanelController;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MainController
{

    @FXML
    private MenuItem saveAppMenuItem;
    @FXML
    private RadioMenuItem modernaStyleMenuItem;
    @FXML
    private RadioMenuItem caspianStyleMenuItem;
    @FXML
    private MenuItem readFromFileMenuItem;
    @FXML
    private MenuItem  readFileMenuItem;
    @FXML
    private MenuItem closeAppMenuItem;
    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TopButtonsPanelController topButtonsPanelController;

    @FXML
    private StudentPanelController studentPanelController;

    @FXML
    public void initialize()
    {
        topButtonsPanelController.setMainView(this);
    }

    public BorderPane getBorderPane()
    {
        return borderPane;
    }

    public TopButtonsPanelController getTopButtonsPanelController()
    {
        return topButtonsPanelController;
    }

    public Object addPane(String path)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        Pane pane = null;
        try
        {
            pane = loader.load();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(pane);
        pane.setMaxHeight(400);
        pane.setMaxWidth(700);
        return loader.getController();

    }

    public void menuBarButtonOptions(ActionEvent event)
    {
        Object source = event.getSource();
        if(source==closeAppMenuItem)
        {
            Optional<ButtonType> choice = Dialogue.confirmationWindow();
            if(choice.get()==ButtonType.OK)
            {
                Platform.exit();
                System.exit(0);
            }
        }
        else if(source==saveAppMenuItem)
        {
            boolean stan = DataBase.save();
            if(stan)
            {
                Dialogue.successfullySaved();
            }
            else
            {
                Dialogue.unSuccessfullySaved();
            }
        }
        else if(source==readFileMenuItem)
        {
            boolean stan = DataBase.read();
            if(stan)
            {
                Dialogue.successfullyRead();
                readFileMenuItem.setDisable(true);
            }
            else
            {
                Dialogue.unSuccessfullyRead();
            }
        }
        else if(source==readFromFileMenuItem)
        {
            Dialogue.fileReadingWarning();
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if(file!=null)
            {
                String pom[] = file.getName().split("\\.");
                if(pom[1].equalsIgnoreCase("txt"))
                {
                    boolean stan = ReadData.readFromFile(file);
                    if (stan)
                        Dialogue.successfullyReadFile();
                    else {
                        Dialogue.unSuccessfullyReadFile();
                    }
                }
                else
                {
                    Dialogue.fileFormatError();
                }
            }
            else
            {

            }
        }
        else if(source==helpMenuItem)
        {
            Dialogue.helpCenter();
        }
        else if(source==caspianStyleMenuItem)
        {
            Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
        }
        else if(source==modernaStyleMenuItem)
        {
            Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        }
    }

}

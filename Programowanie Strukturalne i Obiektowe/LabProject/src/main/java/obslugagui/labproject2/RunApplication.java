package obslugagui.labproject2;

import KlasyOperacyjneNaStrukturzeUczelni.BusinessLogic.PeopleManagement.SetDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        SetDataBase setDataBase = new SetDataBase();
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("borderPane.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Uczelnia");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
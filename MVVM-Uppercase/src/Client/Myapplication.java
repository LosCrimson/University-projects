package Client;

import Client.model.ModelManager;
import Client.view.ViewHandler;
import Client.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Myapplication extends Application
{
  public void start(Stage primaryStage)
  {
    try
    {
      ModelManager model = new ModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);
      view.start(primaryStage);
    }
    catch (Exception e)
    {
      System.out.println("Myapp dieded");
    }
  }
}

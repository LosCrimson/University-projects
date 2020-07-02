package Server;

import Server.mediator.UppercaseConnector;
import Server.model.Model;
import Server.model.ModelManager;
import Server.view.SimpleConsoleView;

public class UppecaseMainServer
{
  public static void main(String args[])
  {
    Model model = new ModelManager();
    
    // simple console view
    SimpleConsoleView view = new SimpleConsoleView(model);
    
    // starting server
    UppercaseConnector server = new UppercaseConnector(model);
    Thread serverThread = new Thread(server);
    serverThread.start();
  }
}
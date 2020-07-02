package Client.model;

import Client.mediator.ServerModel;
import Client.mediator.UppercaseClient;

import java.io.IOException;

public class ModelManager
{
  private static ServerModel serverModel;

  public ModelManager()
  {
    serverModel = new UppercaseClient();
    try
    {
      serverModel.connect();
    }
    catch (IOException e)
    {
      System.out.println("Model manager constructor error");
    }
  }

  public String Convert(String source)
  {
    String txt = "";
    try
    {
     txt = serverModel.convertClient(source);
    }
    catch (IOException e)
    {
      System.out.println("Model manager Convert error");
    }
    return txt;
  }

}

package Client.viewmodel;

import Client.model.ModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConvertViewModel
{
  private StringProperty request;
  private StringProperty reply;
  private StringProperty error;
  private ModelManager model;

  public ConvertViewModel(ModelManager model)
  {
    this.model = model;
    this.request = new SimpleStringProperty();
    this.reply = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
  }

  public void convert()
  {
    System.out.println("ConvertVieModel error");
    try
    {
      reply.set(model.Convert(request.get()));
      System.out.println("ConvertViewModel convert request " + request.get());
      System.out.println("ConvertViewModel convert reply " + reply.get());
      error.set("");
    }
    catch (Exception e)
    {
      error.setValue(e.getMessage());
      e.printStackTrace();
    }
  }

  public StringProperty errorProperty()
  {
    return this.error;
  }

  public StringProperty requestProperty()
  {
    return this.request;
  }

  public StringProperty replyProperty()
  {
    return this.reply;
  }

}

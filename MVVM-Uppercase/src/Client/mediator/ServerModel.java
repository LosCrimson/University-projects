package Client.mediator;

import java.io.IOException;

public interface ServerModel
{
  public void connect() throws IOException;
  public void disconnect() throws IOException;
  public String convertClient(String source) throws IOException;
}

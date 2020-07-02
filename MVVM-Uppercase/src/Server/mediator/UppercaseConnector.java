package Server.mediator;

import java.io.*;
import java.net.*;

import Server.model.Model;

public class UppercaseConnector implements Runnable
{
  private final int PORT = 6789;
  private Model model;
  private boolean running;
  private ServerSocket welcomeSocket;

  public UppercaseConnector(Model model)
  {
    this.model = model;
  }

  private void start() throws IOException
  {
    model.addLog("Starting Server...");
    welcomeSocket = new ServerSocket(PORT);

    running = true;
    while (running)
    {
      model.addLog("Waiting for a client...");
      Socket socket = welcomeSocket.accept();
      UppercaseClientHandler client = new UppercaseClientHandler(socket, model);
      Thread clientThread = new Thread(client);
      clientThread.setDaemon(true);
      clientThread.start();
    }
  }

  @Override public void run()
  {
    try
    {
      start();
    }
    catch (IOException e)
    {
      model.addLog("Error: " + e.getMessage());
    }
  }

  public void stop()
  {
    running = false;
    try
    {
      welcomeSocket.close();
    }
    catch (Exception e)
    {
      //
    }
  }

}

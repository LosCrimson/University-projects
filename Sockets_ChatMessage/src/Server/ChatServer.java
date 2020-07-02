package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer
{
  private ServerSocket welcomeSocket;
  private boolean running;
  private int PORT = 0423;

  public ChatServer(int port)
  {
    this.PORT = port;
  }

  public void execute()
  {
    try
    {
      System.out.println("Starting Server...");
      welcomeSocket = new ServerSocket(PORT);

      running = true;
      while (running)
      {
        //Connects to the client.
        System.out.println("Waiting for client...");
        Socket socket = welcomeSocket.accept();

        //Starts a Thread so that the Handler could parse the information sent by the client
        Thread client = new Thread(new CommunicationThreadHandler(socket));
        client.setDaemon(true);
        client.start();
      }
    }
    catch (IOException e)
    {
      System.out.println("Connection Error in ChatServer: " + e.getMessage());
    }
  }
}


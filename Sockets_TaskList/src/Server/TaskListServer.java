package Server;

import Model.TaskList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskListServer
{
  private int PORT = 0425;

  private ServerSocket welcomeSocket;
  private TaskList taskList;
  private int port = PORT;
  private boolean running;

  public TaskListServer(TaskList taskList, int port)
  {
    this.taskList = taskList;
    this.port = port;
  }

  public void execute()
  {
    running = true;

    try
    {
      System.out.println("Starting Server...");
      welcomeSocket = new ServerSocket(port);

      while (running)
      {
        //Connect to the client.
        System.out.println("Waiting for client...");
        Socket socket = welcomeSocket.accept();

        //Starts a Thread so that the Handler could parse the information sent be the client
        Thread client = new Thread(new TaskListCommunicationThreadHandler(socket, taskList));
        client.setDaemon(true);
        client.start();
      }
    }
    catch (IOException e)
    {
      System.out.println("TaskListServer Error: " + e.getMessage());
    }
  }
}

package Server;

import Model.Package;
import Model.Task;
import Model.TaskList;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TaskListCommunicationThreadHandler implements Runnable
{
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;
  private String ip;
  private boolean running;
  private TaskList taskList;
  private Package convertedpackage;

  public TaskListCommunicationThreadHandler(Socket socket, TaskList taskList)
  {
    this.socket = socket;
    this.taskList = taskList;
  }

  public void run()
  {
    running = true;

    try
    {

      //Read information form the client
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(new PrintWriter(socket.getOutputStream(), true));

      while (running)
      {
        System.out.println("Handler waiting for client... ");

        String Clientmessage = in.readLine();
        String returntoclient = operation(Clientmessage);

        out.println(returntoclient);

      }
    }
    catch (IOException e)
    {
      System.out.println("Handler Error: " + e);
    }
  }

  private String operation(String request)
  {
    String choice = "";
    String returnstatment = "";

    //Create a new Gson object for conversion later
    Gson gson = new Gson();

    //Determines if clients message needs to be converted from json
    if (!request.equals("GET") && !request.equals("SIZE") && !request
        .equals("EXIT"))
    {
      choice = "ADD";
    }
    else
    {
      request = choice;
    }

    switch (choice)
    {
      case "ADD":
        convertedpackage = gson.fromJson(request, Package.class);
        Task addingtask = convertedpackage.getTask();
        TaskList taskList = new TaskList();
        taskList.add(addingtask);
        returnstatment = request;
      break;
      case "GET":
        TaskList gettasklist = new TaskList();
        String json = gson.toJson(gettasklist.getAndRemoveNextTask());
        returnstatment = json;
      break;
      case "SIZE":
        TaskList sizetasklist = new TaskList();
        String returnsizetoclient = String.valueOf(sizetasklist.size());
        if(sizetasklist.size() > 0)
        {
          returnstatment = returnsizetoclient;
        }
        else
        {
          returnstatment = "0";
        }
      break;
      case "EXIT":
        running = false;
        returnstatment = "EXIT";
      break;
    }
    return returnstatment;
  }
}

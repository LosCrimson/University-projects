package Client;

import Model.Task;
import Model.TaskList;
import com.google.gson.Gson;
import Model.Package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TaskListClient
{
  public static final String HOST = "localhost";
  public static final int PORT = 2504;

  private Scanner input;
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;
  private String host;
  private int port;
  private boolean running;

  public TaskListClient(String host, int port)
  {
    this.host = host;
    this.port = port;
  }

  public TaskListClient()
  {
    this.host = HOST;
    this.port = PORT;
  }

  public void execute()
  {
    running = true;
    try
    {

      Gson gson = new Gson();

      while (running)
      {
        //Starting the server input and output in order to send and receive data.
        Socket socket = new Socket(HOST, PORT);
        // Also keyboard input.
        Scanner input = new Scanner(System.in);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("1) ADD: ");
        System.out.println("2) GET: ");
        System.out.println("3) SIZE: ");
        System.out.println("4) EXIT:");
        int inputlong = input.nextInt();
        input.nextLine();

        switch (inputlong)
        {
          case 1:
            //Reads the input for the Task object and creates one
            System.out.println("Client: " + inputlong + " ADD");
            System.out.println("Enter task: ");
            String clienttask = input.nextLine();
            System.out.println("Enter estimated time: ");
            long estimatedtime = input.nextLong();
            input.nextLine();
            Task task = new Task(clienttask, estimatedtime);

            //Converts the task object to a Package object
            System.out.println("Client task"  + task.toString());
            Package clientpackage = new Package("ADD", task);
            System.out.println("Client Package: " + clientpackage.toString());

            //Converts Package object to json String
            String json = gson.toJson(clientpackage);

            //Send the json String to the server
            out.println(json);

            //Receives the response form the Server
            String ADDreply = in.readLine();
            System.out.println("Server Json: " + ADDreply);

            //Converts the Servers response to a Package object
            Package replyclientpackage = gson.fromJson(ADDreply, Package.class);
            System.out.println("Server Package: " + replyclientpackage.toString());
            break;
          case 2:
            System.out.println("Client: " + inputlong + " GET");

            //Send the command to the server
            out.println("GET");

            //Receive a response from the server
            String GETreply = in.readLine();
            System.out.println("Server json: " + GETreply);

            //Converts the Servers response to a TaskList object
            Task replytasklist = gson.fromJson(GETreply, Task.class);
            System.out.println("All the tasks: " + replytasklist.toString());
            break;
          case 3:
            System.out.println("Client: " + inputlong + " SIZE");

            //Send the command to the server
            out.println("SIZE");

            //Receive a response from the server
            String SIZEreply = in.readLine();
            System.out.println("Server json: " + SIZEreply);

            //Converts the Servers response to a TaskList object
            TaskList replytasklistsize = gson.fromJson(SIZEreply, TaskList.class);
            System.out.println("The tasks size: " + replytasklistsize.size());
            break;
          case 4:
            System.out.println("Client: " + inputlong + " EXIT");

            //Send the command to the server
            out.println("EXIT");

            //Receive a response form the server
            String EXITreply = in.readLine();
            System.out.println("Server EXIT: " + EXITreply);

            //If servers answers EXIT ends the loops and closes the socket othervise nothing
            if (EXITreply.equals("EXIT"))
            {
              running = false;
              close();
            }
            else
            {
              System.out.println("You messed up the code bub");
            }
            break;
        }
      }
    }
    catch (IOException e)
    {
      System.out.println("TaskListClient Error: " + e);
    }
  }

  public void close()
  {
    try
    {
      in.close();
      out.close();
      socket.close();
      input.close();
    }
    catch (IOException e)
    {
      System.out.println("TaskListClient Error: " + e);
    }
  }
}

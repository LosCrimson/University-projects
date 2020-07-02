package Client;

import Server.ChatServer;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient
{
  public static final String HOST = "localhost";
  public static final int PORT = 0423;


  private Scanner input;
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;
  private String host;
  private int port;
  private boolean running;

  public ChatClient(String host, int port)
  {
    this.host = host;
    this.port = port;
  }

  public ChatClient()
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

      //Starting server input and output in order to receive and send information
      Socket socket = new Socket(HOST, PORT);
      Scanner input = new Scanner(System.in);
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      while (running)
      {
        //Read a String and send to the Server
        System.out.println("Please type in the message you want to send: ");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        System.out.println("Client: " + inputString);

        //Converts the input string into a Message object
        Message sendmessage = new Message(inputString);

        //Converts the Message object to JSON
        System.out.println("Message object:" + sendmessage.toString());
        String json = gson.toJson(sendmessage);

        //Sends the JSON String to the Server and closes the socket ergo starting the loop form the top.
        out.println(json);
      }
    }
    catch (IOException e)
    {
      System.out.println("ChatClient Error: " + e);
    }
  }

  public void close()
  {
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      throw new IllegalStateException("Cannot close TCP connection ", e);
    }
  }
}

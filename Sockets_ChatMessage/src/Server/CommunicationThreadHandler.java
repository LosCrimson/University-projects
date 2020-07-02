package Server;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicationThreadHandler implements Runnable
{
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;
  private String ip;
  private boolean running;

  public CommunicationThreadHandler(Socket socket)
  {
    this.socket = socket;
  }

  @Override public void run()
  {
    running = true;

    try
    {

      Gson gson = new Gson();
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      while (running)
      {
        //Reads information from client
        System.out.println("Waiting for a message from client...");
        String reply = in.readLine();
        System.out.println("Json String from client: " + reply);

        //Converts JSON to Message object
        Message message = gson.fromJson(reply, Message.class);
        System.out.println("Message from the client: " + message.toString());
      }
    }
    catch (IOException e)
    {
      System.out.println("Handler Error: " + e);
    }
  }
}

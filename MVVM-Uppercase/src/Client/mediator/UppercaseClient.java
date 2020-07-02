package Client.mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UppercaseClient implements ServerModel
{
  public final String HOST = "localhost";
  public final int PORT = 6789;
  private String host;
  private int port;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;

  public UppercaseClient(String host, int port)
  {
    this.host = host;
    this.port = port;
  }

  public UppercaseClient()
  {
    this.host = HOST;
    this.port = PORT;
  }

  @Override public void connect() throws IOException
  {
    //Starting the server input and output in order to send and receive data.
    Socket socket = new Socket(HOST, PORT);
    out = new PrintWriter(socket.getOutputStream(), true);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  @Override public void disconnect() throws IOException
  {
    socket.close();
    out.close();
    in.close();
  }

  @Override public String convertClient(String source) throws IOException
  {
    //Send information to the Server
    System.out.println("UppercaseClient convert" + source);
    out.println(source);

    //Gets the response form the Server
    String ConvertedToUppercase = in.readLine();
    return ConvertedToUppercase;
  }
}

import Server.ChatServer;

public class ServerMain
{
  public static void main(String[] args)
  {
    ChatServer chatserver = new ChatServer(0423);
    chatserver.execute();
  }
}

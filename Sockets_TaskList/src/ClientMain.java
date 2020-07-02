import Client.TaskListClient;

public class ClientMain
{
  public static void main(String[] args)
  {
    TaskListClient taskListClient = new TaskListClient();
    taskListClient.execute();
  }
}

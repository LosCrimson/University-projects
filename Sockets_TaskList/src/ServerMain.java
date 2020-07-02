import Model.TaskList;
import Server.TaskListServer;

public class ServerMain
{
  public static void main(String[] args)
  {
    TaskList taskList = new TaskList();
    TaskListServer taskListServer = new TaskListServer(taskList, 2504);
    taskListServer.execute();
  }
}

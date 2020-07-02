import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FootballFan implements PropertyChangeListener
{
  private String myName;
  private String myTeam;
  private FootballGame game;

  public FootballFan(String myName, FootballGame game, boolean homeTeamFan)
  {
    this.myName = myName;
    this.game = game;
    if (homeTeamFan)
    {
      this.myTeam = game.getHomeTeam();
    }
    else
    {
      this.myTeam = game.getAwayTeam();
    }
    cheer(myTeam);
    game.addListener(this);
  }

  public void cheer(String team)
  {
    System.out.println(myName + "> Come on " + team);
  }

  public void reactOnGoal(String team)
  {
    if (team.equals(myTeam))
    {
      System.out.println(myName + "> YAY (" + team + " scored)");
    }
    else
    {
      System.out.println(myName + "> BOOOOO (" + team + " scored)");
    }
  }

  @Override public void propertyChange(PropertyChangeEvent event)
  {
    if (!event.getPropertyName().equals("score"))
    reactOnGoal(event.getPropertyName());
  }
}

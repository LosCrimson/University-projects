import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Scoreboard implements PropertyChangeListener
{
  private FootballGame game;

  public Scoreboard(FootballGame game)
  {
    this.game = game;
    System.out.println("SCOREBOARD: " + game.getScore());
    game.addListener(this);
  }

  @Override public void propertyChange(PropertyChangeEvent event)
  {
    if(event.getPropertyName().equals("score"))
      System.out.println(game.getScore());
  }
}
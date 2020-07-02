package via.sdj2.thread;

import via.sdj2.thread.view.TemperatureViewController;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;

public class RunnableClock implements  Runnable, PropertyChangeSubject
{
  private DateTimeFormatter timeFormatter;
  private PropertyChangeSupport property;

  public RunnableClock(TemperatureViewController viewConroller)
  {
    property = new PropertyChangeSupport(this);
  }

  @Override public void run()
  {
    while (true)
    {
      LocalTime time = LocalTime.now();
      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      String timeString = time.format(timeFormatter);
      System.out.println(timeString);
      new Thread(() ->property.firePropertyChange(timeString, null, null)).start();
      try
      {
        sleep(1000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}

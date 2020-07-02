package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.AuctionItem;
import model.AuctionModel;
import model.Bid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AuctionItemViewModel implements PropertyChangeListener
{
  private StringProperty item;
  private StringProperty time;
  private IntegerProperty bid;
  private StringProperty bidder;
  private IntegerProperty currentBid;
  private StringProperty currentBidder;
  private StringProperty error;
  private BooleanProperty end;
  private StringProperty currentBidTitle;
  private AuctionModel model;

  public AuctionItemViewModel(AuctionModel model)
  {
    this.model = model;
    model.addListener(null, this);
    this.item = new SimpleStringProperty(model.getItem());
    this.time = new SimpleStringProperty();
    this.bid = new SimpleIntegerProperty();
    this.bidder = new SimpleStringProperty();
    this.currentBid = new SimpleIntegerProperty(model.getCurrentBid());
    this.currentBidder = new SimpleStringProperty(model.getCurrentBidder());
    this.error = new SimpleStringProperty();
    this.end = new SimpleBooleanProperty(false);
    this.currentBidTitle = new SimpleStringProperty();
    currentBidTitle.set("current bid;");

  }

  public void clear()
  {
    item.set(""); //In order to clear just make Strings blank
    time.set("");
    bidder.set("");
    currentBidder.set("");
    error.set("");
    currentBidTitle.set("");
  }

  public void bid()
  {
    if (end.get() || (bid.get() <= currentBid.get()) || (currentBidder.get().equals("you")))
    {
      error.set("Somehting is wrong");
    }
    else
    {
      model.placeBid(bid.get(), "you");
      error.set("Stable");
    }
  }

  public StringProperty getItemProperty()
  {
    return item;
  }

  public IntegerProperty getBidProperty()
  {
    return bid;
  }


  public StringProperty timeProperty()
  {
    return time;
  }


  public StringProperty getBidderProperty()
  {
    return bidder;
  }


  public IntegerProperty getCurrentBidProperty()
  {
    return currentBid;
  }


  public StringProperty getCurrentBidderProperty()
  {
    return currentBidder;
  }


  public StringProperty errorProperty()
  {
    return error;
  }

  public BooleanProperty endProperty()
  {
    return end;
  }

  public StringProperty currentBidTitleProperty()
  {
    return currentBidTitle;
  }

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {
    Platform.runLater(()->
    {
     if(propertyChangeEvent.getPropertyName().equals("time"))
     {
       time.set(propertyChangeEvent.getNewValue() + "");
     }
     else
     {
       if (propertyChangeEvent.getPropertyName().equals("bid"))
       {
         Bid biddin = (Bid)propertyChangeEvent.getNewValue();
         currentBidder.set(biddin.getBidder());
         currentBid.set(biddin.getBid());
       }
       else
       {
         if(propertyChangeEvent.getPropertyName().equals("end"))
         {
           end.set(true);
           currentBidTitle.set("Final Bid:");
         }
       }
     }
    });
  }
}

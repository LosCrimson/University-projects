package viewmodel;

import model.AuctionModel;

public class ViewModelFactory
{
  private AuctionItemViewModel auctionviewmodel;

  public ViewModelFactory(AuctionModel model)
  {
    auctionviewmodel = new AuctionItemViewModel(model);
  }

  public AuctionItemViewModel getAuctionViewModel()
  {
    return auctionviewmodel;
  }
}

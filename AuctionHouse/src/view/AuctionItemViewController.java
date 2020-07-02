package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import viewmodel.AuctionItemViewModel;

public class AuctionItemViewController
{
  @FXML private Label itemLabel;
  @FXML private Label timeLabel;
  @FXML private Label currentBidTitle;
  @FXML private Label currentBidLabel;
  @FXML private Label currentBidderLabel;
  @FXML private TextField bidField;
  @FXML private Label errorLabel;
  private Region root;
  private ViewHandler viewHandler;
  private AuctionItemViewModel viewModel;

  public AuctionItemViewController()
  {
    // should be empty because, you don't do anything with it it just calls the window.
    // Because viewmodel does all the work and fills it out.
  }

  public void init(ViewHandler viewHandler, AuctionItemViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    currentBidderLabel.textProperty().bind(viewModel.getCurrentBidderProperty());
    itemLabel.textProperty().bind(viewModel.getItemProperty());
    timeLabel.textProperty().bind(viewModel.timeProperty());
    currentBidTitle.textProperty().bind(viewModel.currentBidTitleProperty());
    Bindings.bindBidirectional(currentBidLabel.textProperty(),
        viewModel.getCurrentBidProperty(), new NumberStringConverter());
    Bindings.bindBidirectional(currentBidLabel.textProperty(),
        viewModel.getCurrentBidProperty(), new StringConverter<Number>()
        {
          @Override public String toString(Number n)
          {
            if (n == null || n.intValue() < -1)
            {
              return "";
            }
            return n.toString();
          }

          @Override public Number fromString(String s)
          {
            try
            {
              return Integer.parseInt(s);
            }
            catch (Exception e)
            {
              return 0;
            }
          }
        });
    Bindings.bindBidirectional(bidField.textProperty(),
        viewModel.getBidProperty(), new NumberStringConverter());
    Bindings.bindBidirectional(bidField.textProperty(),
        viewModel.getBidProperty(), new StringConverter<Number>()
        {
          @Override public String toString(Number n)
          {
            if (n == null || n.intValue() < -1)
            {
              return "";
            }
            return n.toString();
          }

          @Override public Number fromString(String s)
          {
            try
            {
              return Integer.parseInt(s);
            }
            catch (Exception e)
            {
              return 0;
            }
          }
        });
    errorLabel.textProperty().bind(viewModel.errorProperty());
    viewModel.endProperty().addListener((alen, josh, drake) ->
    {
      timeLabel.setStyle("-fx-background-color:RED");
    });
  }

  public void reset()
  {

  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void bidOnAction()
  {
    this.viewModel.bid();
  }
}

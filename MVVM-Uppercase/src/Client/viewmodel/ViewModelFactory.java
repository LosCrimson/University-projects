package Client.viewmodel;

import Client.model.ModelManager;

public class ViewModelFactory
{
  private ConvertViewModel convertViewModel;

  public ViewModelFactory(ModelManager model)
  {
    convertViewModel = new ConvertViewModel(model);
  }

  public ConvertViewModel getConvertViewModel()
  {
    return convertViewModel;
  }
}

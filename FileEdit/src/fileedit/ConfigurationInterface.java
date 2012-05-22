package fileedit;
import javax.swing.Icon;

public abstract interface ConfigurationInterface
{
  public abstract String getTabTitle();

  public abstract String getTabToolTip();

  public abstract Icon getTabIcon();

  public abstract boolean isSavePending();

  public abstract boolean savePreferences();

  public abstract void resetSavePending();
}

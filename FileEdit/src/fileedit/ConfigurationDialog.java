package fileedit;
 import java.awt.Component;
 import java.awt.Container;
 import java.awt.Dialog;
 import java.awt.Dimension;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import java.io.IOException;
 import javax.swing.Box;
 import javax.swing.BoxLayout;
 import javax.swing.JButton;
 import javax.swing.JDialog;
 import javax.swing.JOptionPane;
 import javax.swing.JTabbedPane;
 
 public class ConfigurationDialog extends JDialog
   implements ActionListener
 {
   private Preferences pref = new Preferences();
   private JTabbedPane tab = new JTabbedPane();
   private JButton saveButton = new JButton("Save");
   private JButton closeButton = new JButton("Close");
 
   private ConfigurationInterface[] config = { new FileEditorConfiguration(this.pref), new CodeTemplatePanel(this.pref) };
   private WindowHandler windowHandler = new WindowHandler();
 
   public ConfigurationDialog()
   {
     super((Dialog) null, "FileEdit Configuration", true);
 
     setSize(new Dimension(600, 450));
 
     Container contentPane = getContentPane();
     contentPane.setLayout(new BoxLayout(contentPane, 0));
     contentPane.setForeground(Common.FG_COLOR);
     contentPane.setBackground(Common.WPB_COLOR);
 
     this.tab.setForeground(Common.FG_COLOR);
     this.tab.setBackground(Common.WPB_COLOR);
 
     for (int x = 0; x < this.config.length; x++) {
       this.tab.addTab(this.config[x].getTabTitle(), this.config[x].getTabIcon(), (Component)this.config[x], this.config[x].getTabToolTip());
     }
 
     Box buttonPanel = Box.createHorizontalBox();
     buttonPanel.add(Box.createHorizontalGlue());
     buttonPanel.add(this.saveButton);
     buttonPanel.add(Box.createHorizontalStrut(10));
     buttonPanel.add(this.closeButton);
 
     Box mainPanel = Box.createVerticalBox();
     mainPanel.add(Box.createVerticalStrut(10));
     mainPanel.add(this.tab);
     mainPanel.add(Box.createVerticalStrut(10));
     mainPanel.add(buttonPanel);
     mainPanel.add(Box.createVerticalStrut(10));
 
     contentPane.add(Box.createHorizontalStrut(10));
     contentPane.add(mainPanel);
     contentPane.add(Box.createHorizontalStrut(10));
 
     this.saveButton.addActionListener(this);
     this.closeButton.addActionListener(this);
 
     setDefaultCloseOperation(0);
     addWindowListener(this.windowHandler);
 
     pack();
   }
 
   public void actionPerformed(ActionEvent e) {
     Object src = e.getSource();
     if (src == this.saveButton)
       save();
     else if (src == this.closeButton)
       this.windowHandler.windowClosing(new WindowEvent(this, 201));
   }
 
   public boolean save()
   {
     for (int x = 0; x < this.config.length; x++) {
       if (!this.config[x].savePreferences()) {
         this.tab.setSelectedIndex(x);
         return false;
       }
     }
 
     try
     {
       this.pref.save();
       for (int x = 0; x < this.config.length; x++) this.config[x].resetSavePending();
       Common.showMessage("Save", "Preferences were saved successfully", null);
       return true;
     } catch (IOException e) {
       JOptionPane.showMessageDialog(null, e.toString(), "Error saving preferences", 0);
     }return false;
   }
 
   public static void main(String[] args)
   {
     ConfigurationDialog ff = new ConfigurationDialog();
     ff.setVisible(true);
   }
 
   private class WindowHandler extends WindowAdapter
   {
     WindowHandler()
     {
     }
 
     public void windowClosing(WindowEvent e)
     {
       boolean savePending = false;
       for (int x = 0; x < ConfigurationDialog.this.config.length; x++) { if (!ConfigurationDialog.this.config[x].isSavePending()) continue; savePending = true; break;
       }
 
       if (savePending)
       {
         if (Common.confirm("Save Pending", "Changes are pending.  Do you want to save before closing?", null))
         {
           if (!ConfigurationDialog.this.save()) return;
         }
       }
 
       ConfigurationDialog.this.dispose();
     }
   }
 }

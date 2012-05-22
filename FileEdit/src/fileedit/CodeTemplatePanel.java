package fileedit;
 import java.awt.Component;
 import java.awt.Dimension;
 import java.awt.event.ItemEvent;
 import java.awt.event.ItemListener;
 import javax.swing.Box;
 import javax.swing.Icon;
 import javax.swing.JComboBox;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.JTextArea;
 import javax.swing.JTextField;
 import javax.swing.event.DocumentEvent;
 import javax.swing.event.DocumentListener;
 
 public class CodeTemplatePanel extends JPanel
   implements ItemListener, DocumentListener, ConfigurationInterface
 {
   public static final String CPP = "C++";
   public static final String CSHARP = "C#";
   public static final String JAVA = "Java";
   private final Preferences pref;
   private JComboBox<String> language = Common.createJComboBox(new String[] { "C++", "Java", "C#" });
   private JLabel languageLabel = Common.createJLabel("Language: ");
   private JLabel extensionLabel = Common.createJLabel("Extension: ");
   private JTextField extension = Common.createJTextField(5, new Dimension(150, 20));
   private JTextArea template = Common.createJTextArea("");
   private boolean savePending = false;
   private String CPPTemplate;
   private String CPPExtension;
   private String CSHARPTemplate;
   private String CSHARPExtension;
   private String JAVATemplate;
   private String JAVAExtension;
   private boolean initializing = true;
 
   public CodeTemplatePanel(Preferences pref)
   {
     this.pref = pref;
 
     this.JAVATemplate = pref.getJAVATemplate();
     this.CPPTemplate = pref.getCPPTemplate();
     this.CSHARPTemplate = pref.getCSHARPTemplate();
     this.JAVAExtension = pref.getJAVAExtension();
     this.CPPExtension = pref.getCPPExtension();
     this.CSHARPExtension = pref.getCSHARPExtension();
 
     Common.setDefaultAttributes(this);
 
     Box lang = Common.createHorizontalBox(new Component[] { this.languageLabel, this.language, Box.createHorizontalGlue(), this.extensionLabel, this.extension });
 
     JScrollPane scroll = Common.createJScrollPane(this.template);
     this.template.getDocument().addDocumentListener(this);
     this.extension.getDocument().addDocumentListener(this);
 
     add(lang, "North");
     add(scroll, "Center");
 
     this.language.addItemListener(this);
 
     this.template.setText(this.JAVATemplate);
     this.extension.setText(this.JAVAExtension);
     this.language.setSelectedItem("Java");
 
     this.savePending = false;
 
     this.initializing = false;
   }
 
   public void itemStateChanged(ItemEvent e)
   {
     if (this.initializing) return;
 
     String templateText = this.template.getText();
     if (templateText == null) templateText = "";
 
     String templateExt = this.extension.getText();
     if (templateExt == null) templateExt = "";
     String lang = (String)e.getItem();
     boolean isSelected = e.getStateChange() == 1;
 
     if (isSelected) {
       if (lang.equals("Java")) {
         this.template.setText(this.JAVATemplate);
         this.extension.setText(this.JAVAExtension);
       } else if (lang.equals("C++")) {
         this.template.setText(this.CPPTemplate);
         this.extension.setText(this.CPPExtension);
       } else {
         this.template.setText(this.CSHARPTemplate);
         this.extension.setText(this.CSHARPExtension);
       }
     }
     else if (lang.equals("Java")) {
       this.JAVATemplate = templateText;
       this.JAVAExtension = templateExt;
     } else if (lang.equals("C++")) {
       this.CPPTemplate = templateText;
       this.CPPExtension = templateExt;
     } else {
       this.CSHARPTemplate = templateText;
       this.CSHARPExtension = templateExt;
     }
   }
 
   public void changedUpdate(DocumentEvent e)
   {
     this.savePending = true; } 
   public void insertUpdate(DocumentEvent e) { this.savePending = true; } 
   public void removeUpdate(DocumentEvent e) { this.savePending = true; } 
   public String getTabTitle() {
     return "Code Template"; } 
   public Icon getTabIcon() { return null; } 
   public String getTabToolTip() { return "Specify code templates"; } 
   public boolean isSavePending() { return this.savePending; } 
   public void resetSavePending() { this.savePending = false; }
 
   public boolean savePreferences()
   {
     String lang = (String)this.language.getSelectedItem();
     if (lang.equals("Java")) {
       this.JAVATemplate = this.template.getText();
       this.JAVAExtension = this.extension.getText();
     } else if (lang.equals("C++")) {
       this.CPPTemplate = this.template.getText();
       this.CPPExtension = this.extension.getText();
     } else {
       this.CSHARPTemplate = this.template.getText();
       this.CSHARPExtension = this.extension.getText();
     }
 
     this.pref.setJAVATemplate(this.JAVATemplate);
     this.pref.setCPPTemplate(this.CPPTemplate);
     this.pref.setCSHARPTemplate(this.CSHARPTemplate);
     this.pref.setJAVAExtension(this.JAVAExtension);
     this.pref.setCPPExtension(this.CPPExtension);
     this.pref.setCSHARPExtension(this.CSHARPExtension);
     return true;
   }
 }

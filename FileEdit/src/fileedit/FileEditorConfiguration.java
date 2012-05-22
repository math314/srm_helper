package fileedit;
 import java.awt.BorderLayout;
 import java.awt.Component;
 import java.awt.Dimension;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.Box;
 import javax.swing.Icon;
 import javax.swing.JCheckBox;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JTextField;
 import javax.swing.event.DocumentEvent;
 import javax.swing.event.DocumentListener;
 
 public class FileEditorConfiguration extends JPanel
   implements ActionListener, DocumentListener, ConfigurationInterface
 {
   Preferences pref;
   JLabel dirNameLabel = Common.createJLabel("Enter directory to read/write problems to:");
   JTextField dirNameField = Common.createJTextField(40, new Dimension(400, 21));
   JCheckBox backup = Common.createJCheckBox("Backup existing file then overwrite (uncheck if you want to keep existing file)");
   JLabel fileNameLabel = Common.createJLabel("Enter filename to use (no extension):");
   JTextField fileNameField = Common.createJTextField(40, new Dimension(400, 21));
   JCheckBox htmlDesc = Common.createJCheckBox("Write the problem description using HTML");
   JCheckBox useLineComments = Common.createJCheckBox("Use Line Comments for Problem Description");
   JCheckBox overrideFileNameField = Common.createJCheckBox("Make filename equal to classname");
   JCheckBox provideBreakField = Common.createJCheckBox("Force Breaks at");
   JTextField breakAtField = Common.createJTextField(4, new Dimension(75, 21));
   JLabel beginCutLabel = Common.createJLabel("$BEGINCUT$ ");
   JTextField beginCutField = Common.createJTextField(40, new Dimension(400, 21));
   JLabel endCutLabel = Common.createJLabel("$ENDCUT$ ");
   JTextField endCutField = Common.createJTextField(40, new Dimension(400, 21));
 
   JCheckBox problemDescFileWrite = Common.createJCheckBox("Write Problem Description to separate file");
   JLabel problemDescFileLabel = Common.createJLabel("File Extension: ");
   JTextField problemDescFileField = Common.createJTextField(4, new Dimension(75, 21));
   JLabel sigFileLabel = Common.createJLabel("Enter signature filename: ");
   JTextField sigFileField = Common.createJTextField(40, new Dimension(400, 21));
 
   //2012/05/21 math add
   JLabel executeScriptLabel = Common.createJLabel("Enter execute Script: ");
   JTextField executeScriptField = Common.createJTextField(40, new Dimension(400, 21));
   
   boolean savePending = false;
 
   public FileEditorConfiguration(Preferences pref)
   {
     this.pref = pref;
     Common.setDefaultAttributes(this, new BorderLayout());
 
     setBackground(Common.WPB_COLOR);
 
     String dirName = pref.getDirectoryName();
     this.dirNameField.setText(dirName);
 
     this.backup.setSelected(pref.isBackup());
 
     this.overrideFileNameField.setSelected(!pref.isOverrideFileName());
 
     this.useLineComments.setSelected(pref.isLineComments());
 
     this.problemDescFileWrite.setSelected(pref.isWriteProblemDescFile());
 
     String probDescExt = pref.getProblemDescExtension();
     this.problemDescFileField.setText(probDescExt);
     this.problemDescFileLabel.setEnabled(this.problemDescFileWrite.isSelected());
     this.problemDescFileField.setEnabled(this.problemDescFileWrite.isSelected());
 
     String fileName = pref.getFileName();
     this.fileNameLabel.setEnabled(!this.overrideFileNameField.isSelected());
     this.fileNameField.setText(fileName);
     this.fileNameField.setEnabled(!this.overrideFileNameField.isSelected());
 
     String sigFileName = pref.getSignatureFileName();
     this.sigFileField.setText(sigFileName);
 
     this.provideBreakField.setSelected(pref.isProvideBreaks());
     this.breakAtField.setText(String.valueOf(pref.getBreakAt()));
     this.breakAtField.setEnabled(this.provideBreakField.isSelected());
 
     String beginCutString = pref.getBeginCut();
     this.beginCutField.setText(beginCutString);
 
     String endCutString = pref.getEndCut();
     this.endCutField.setText(endCutString);
 
     this.useLineComments.setSelected(pref.isLineComments());
     if (pref.isWriteProblemDescFile()) {
       this.useLineComments.setEnabled(false);
       this.useLineComments.setSelected(false);
     }
 
     this.htmlDesc.setSelected(pref.isHTMLDesc());
     if (this.htmlDesc.isSelected()) {
       this.useLineComments.setEnabled(false);
 
       this.provideBreakField.setEnabled(false);
       this.breakAtField.setEnabled(false);
 
       this.problemDescFileWrite.setEnabled(true);
       this.problemDescFileWrite.setSelected(true);
 
       this.problemDescFileLabel.setEnabled(true);
       this.problemDescFileField.setEnabled(true);
     }
     
     //2012/05/21 math add
     this.executeScriptField.setText(pref.getExecuteScript());
 
     Box dirNamePane = Common.createHorizontalBox(new Component[] { this.dirNameLabel, this.dirNameField }, true);
 
     Box backupPane = Common.createHorizontalBox(new Component[] { this.backup }, true);
 
     Box fileNamePane = Common.createHorizontalBox(new Component[] { Box.createHorizontalStrut(20), this.fileNameLabel, this.fileNameField }, true);
 
     Box sigFilePane = Common.createHorizontalBox(new Component[] { this.sigFileLabel, this.sigFileField }, true);
 
     Box overridePane = Common.createHorizontalBox(new Component[] { this.overrideFileNameField }, true);
 
     Box lineCommentsPane = Common.createHorizontalBox(new Component[] { this.useLineComments }, true);
 
     Box htmlDescPane = Common.createHorizontalBox(new Component[] { this.htmlDesc }, true);
 
     Box breakAtPane = Common.createHorizontalBox(new Component[] { this.provideBreakField, this.breakAtField }, true);
 
     Box beginCutPane = Common.createHorizontalBox(new Component[] { this.beginCutLabel, this.beginCutField }, true);
 
     Box endCutPane = Common.createHorizontalBox(new Component[] { this.endCutLabel, this.endCutField }, true);
 
     Box probDescFileWriteBox = Common.createHorizontalBox(new Component[] { this.problemDescFileWrite }, true);
 
     Box probDescFileExtBox = Common.createHorizontalBox(new Component[] { Box.createHorizontalStrut(20), this.problemDescFileLabel, this.problemDescFileField }, true);
 
     //2012/05/21 math add
     Box execucriptPane = Common.createHorizontalBox(new Component[] { this.executeScriptLabel, this.executeScriptField}, true);
     
     Box all = Box.createVerticalBox();
     all.add(Box.createVerticalStrut(10));
     all.add(dirNamePane);
     all.add(Box.createVerticalStrut(5));
     all.add(backupPane);
     all.add(Box.createVerticalStrut(5));
     all.add(overridePane);
     all.add(Box.createVerticalStrut(1));
     all.add(fileNamePane);
 
     all.add(Box.createVerticalStrut(5));
     all.add(htmlDescPane);
 
     all.add(Box.createVerticalStrut(5));
     all.add(probDescFileWriteBox);
     all.add(Box.createVerticalStrut(1));
     all.add(probDescFileExtBox);
 
     all.add(Box.createVerticalStrut(5));
     all.add(lineCommentsPane);
     all.add(Box.createVerticalStrut(1));
     all.add(breakAtPane);
     all.add(Box.createVerticalStrut(1));
     all.add(beginCutPane);
     all.add(Box.createVerticalStrut(1));
     all.add(endCutPane);
     all.add(Box.createVerticalStrut(5));
     all.add(sigFilePane);
     
     //2012/05/21 math add
     all.add(Box.createVerticalStrut(5));
     all.add(execucriptPane);
     
     all.add(Box.createVerticalGlue());
 
     add(all, "North");
 
     this.dirNameField.getDocument().addDocumentListener(this);
     this.fileNameField.getDocument().addDocumentListener(this);
     this.breakAtField.getDocument().addDocumentListener(this);
     this.beginCutField.getDocument().addDocumentListener(this);
     this.endCutField.getDocument().addDocumentListener(this);
     this.problemDescFileField.getDocument().addDocumentListener(this);
     this.sigFileField.getDocument().addDocumentListener(this);
     this.useLineComments.addActionListener(this);
     this.overrideFileNameField.addActionListener(this);
     this.problemDescFileWrite.addActionListener(this);
     this.provideBreakField.addActionListener(this);
     this.htmlDesc.addActionListener(this);
     
   //2012/05/21 math add
     this.executeScriptField.getDocument().addDocumentListener(this);
   }
 
   public void actionPerformed(ActionEvent e)
   {
     Object source = e.getSource();
 
     this.savePending = true;
 
     if (source == this.overrideFileNameField) {
       this.fileNameLabel.setEnabled(!this.overrideFileNameField.isSelected());
       this.fileNameField.setEnabled(!this.overrideFileNameField.isSelected());
     } else if ((source == this.provideBreakField) && (!this.htmlDesc.isSelected())) {
       this.breakAtField.setEnabled(this.provideBreakField.isSelected());
     } else if (source == this.problemDescFileWrite) {
       if (this.htmlDesc.isSelected()) this.problemDescFileWrite.setSelected(true);
       this.problemDescFileLabel.setEnabled(this.problemDescFileWrite.isSelected());
       this.problemDescFileField.setEnabled(this.problemDescFileWrite.isSelected());
       this.useLineComments.setEnabled(!this.problemDescFileWrite.isSelected());
       if (this.problemDescFileWrite.isSelected()) this.useLineComments.setSelected(false); 
     }
     else if (source == this.htmlDesc) {
       this.useLineComments.setEnabled(!this.htmlDesc.isSelected());
       this.provideBreakField.setEnabled(!this.htmlDesc.isSelected());
       this.breakAtField.setEnabled(!this.htmlDesc.isSelected());
 
       if (this.htmlDesc.isSelected()) {
         this.useLineComments.setSelected(false);
         this.provideBreakField.setSelected(false);
         this.problemDescFileWrite.setEnabled(true);
         this.problemDescFileWrite.setSelected(true);
 
         this.problemDescFileLabel.setEnabled(true);
         this.problemDescFileField.setEnabled(true);
       }
       if (this.problemDescFileWrite.isSelected()) {
         this.useLineComments.setEnabled(false);
         this.useLineComments.setSelected(false);
       }
     }
   }
 
   public void changedUpdate(DocumentEvent e)
   {
     this.savePending = true; } 
   public void insertUpdate(DocumentEvent e) { this.savePending = true; } 
   public void removeUpdate(DocumentEvent e) { this.savePending = true; } 
   public String getTabTitle() {
     return "General"; } 
   public Icon getTabIcon() { return null; } 
   public String getTabToolTip() { return "General Configuration"; } 
   public boolean isSavePending() { return this.savePending; } 
   public void resetSavePending() { this.savePending = false; }
 
   public boolean savePreferences()
   {
     if ((this.overrideFileNameField.isSelected()) && (this.fileNameField.getText().trim().equals(""))) {
       Common.showMessage("Error", "You must specify a filename", null);
       return false;
     }
 
			  int breakAt;
     try
     {
       breakAt = Integer.parseInt(this.breakAtField.getText());
     }
     catch (NumberFormatException e)
     {
       Common.showMessage("Error", "The break at is not a number", null);
       return false;
     }
     this.pref.setDirectoryName(this.dirNameField.getText());
     this.pref.setFileName(this.fileNameField.getText());
     this.pref.setBeginCut(this.beginCutField.getText());
     this.pref.setEndCut(this.endCutField.getText());
     this.pref.setProblemDescExtension(this.problemDescFileField.getText());
     this.pref.setSignatureFileName(this.sigFileField.getText());
 
     //2012/05/21 math add
     this.pref.setExecuteScript(this.executeScriptField.getText());
     
     this.pref.setLineComments(this.useLineComments.isSelected());
     this.pref.setOverrideFileName(!this.overrideFileNameField.isSelected());
     this.pref.setProvideBreaks(this.provideBreakField.isSelected());
     this.pref.setWriteProblemDescFile(this.problemDescFileWrite.isSelected());
     this.pref.setBreakAt(breakAt);
     this.pref.setHTMLDesc(this.htmlDesc.isSelected());
     this.pref.setBackup(this.backup.isSelected());
 
     return true;
   }
 }

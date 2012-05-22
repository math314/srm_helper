package fileedit;
 import com.topcoder.client.contestApplet.common.LocalPreferences;
 import java.io.IOException;
 import java.util.Observer;
 
 public class Preferences
 {
   private static LocalPreferences pref = LocalPreferences.getInstance();
   private Observer notify;
   public static final String JAVATEMPLATE = "fileeditor.config.javatemplate";
   public static final String CPPTEMPLATE = "fileeditor.config.cpptemplate";
   public static final String CSHARPTEMPLATE = "fileeditor.config.csharptemplate";
   public static final String JAVAEXTENSION = "fileeditor.config.javaextension";
   public static final String CPPEXTENSION = "fileeditor.config.cppextension";
   public static final String CSHARPEXTENSION = "fileeditor.config.csharpextension";
   public static final String DIRNAMEKEY = "fileeditor.config.dirName";
   public static final String FILENAMEKEY = "fileeditor.config.fileName";
   public static final String OVERRIDEFILENAME = "fileeditor.config.overrideFileName";
   public static final String PROVIDEBREAKS = "fileeditor.config.provideBreaks";
   public static final String BREAKAT = "fileeditor.config.breakAt";
   public static final String LINECOMMENTS = "fileeditor.config.lineComments";
   public static final String BEGINCUT = "fileeditor.config.beginCut";
   public static final String ENDCUT = "fileeditor.config.endCut";
   public static final String PROBDESCFILEWRITE = "fileeditor.config.probdescfilewrite";
   public static final String PROBDESCFILEEXTENSION = "fileeditor.config.probdescfileextnsion";
   public static final String SIGNATUREFILENAME = "fileeditor.config.signaturefilename";
   public static final String POWEREDBY = "fileeditor.config.poweredby";
   public static final String HTMLDESC = "fileeditor.config.htmldesc";
   public static final String BACKUP = "fileeditor.config.backup";

   //2012/05/12 math add
   public static final String EXECUTESCRIPT = "fileeditor.config.executescript";
   
   public Preferences()
   {
   }
 
   public Preferences(Observer notify)
   {
     this.notify = notify;
     pref.addSaveObserver(notify);
   }
 
   public void finalize()
   {
     if (this.notify != null) pref.removeSaveObserver(this.notify); 
   }
 
   public String getJAVATemplate()
   {
     return getStringProperty("fileeditor.config.javatemplate", "$BEGINCUT$\n$PROBLEMDESC$\n$ENDCUT$\nimport java.util.*;\npublic class $CLASSNAME$ {\n\tpublic $RC$ $METHODNAME$($METHODPARMS$) {\n\t\t\n\t}\n\tpublic static void main(String[] args) {\n\t\t$CLASSNAME$ temp = new $CLASSNAME$();\n\t\tSystem.out.println(temp.$METHODNAME$($METHODPARMS$));\n\t}\n}");
   }
 
   public String getJAVAExtension() {
     return getStringProperty("fileeditor.config.javaextension", "java");
   }
 
   public final String getCSHARPTemplate() {
     return getStringProperty("fileeditor.config.csharptemplate", "using System;\r\nusing System.Collections;\r\npublic class $CLASSNAME$ {\r\n\tpublic $RC$ $METHODNAME$($METHODPARMS$) {\r\n\t\t$CARETPOSITION$\r\n\t}\r\n}");
   }
 
   public String getCSHARPExtension() {
     return getStringProperty("fileeditor.config.csharpextension", "cs");
   }
 
   public String getCPPTemplate() {
     return getStringProperty("fileeditor.config.cpptemplate", "$BEGINCUT$\n$PROBLEMDESC$\n$ENDCUT$\n#line $NEXTLINENUMBER$ \"$FILENAME$\"\n#include <string>\n#include <vector>\nclass $CLASSNAME$ {\n\tpublic:\n\t$RC$ $METHODNAME$($METHODPARMS$) {\n\t\t\n\t}\n};");
   }
 
   public String getCPPExtension() {
     return getStringProperty("fileeditor.config.cppextension", "cpp");
   }
 
   public String getDirectoryName() {
     return getStringProperty("fileeditor.config.dirName", ".");
   }
 
   public String getFileName() {
     return getStringProperty("fileeditor.config.fileName", "problem");
   }
 
   public String getSignatureFileName() {
     return getStringProperty("fileeditor.config.signaturefilename", "");
   }
 
   public String getBeginCut() {
     return getStringProperty("fileeditor.config.beginCut", "// BEGIN CUT HERE");
   }
 
   public String getEndCut() {
     return getStringProperty("fileeditor.config.endCut", "// END CUT HERE");
   }
 
   public String getProblemDescExtension() {
     return getStringProperty("fileeditor.config.probdescfileextnsion", "txt");
   }
   
   //2012/05/21 math add
   public String getExecuteScript() {
	 return getStringProperty(EXECUTESCRIPT, "");
   }
 
   public boolean isOverrideFileName() {
     return getBooleanProperty("fileeditor.config.overrideFileName", false);
   }
 
   public boolean isProvideBreaks() {
     return getBooleanProperty("fileeditor.config.provideBreaks", false);
   }
 
   public boolean isLineComments() {
     return getBooleanProperty("fileeditor.config.lineComments", true);
   }
 
   public boolean isWriteProblemDescFile() {
     return getBooleanProperty("fileeditor.config.probdescfilewrite", false);
   }
 
   public boolean isPoweredBy() {
     return getBooleanProperty("fileeditor.config.poweredby", true);
   }
 
   public boolean isHTMLDesc() {
     return getBooleanProperty("fileeditor.config.htmldesc", false);
   }
 
   public boolean isBackup() {
     return getBooleanProperty("fileeditor.config.backup", true);
   }
 
   public int getBreakAt() {
     String value = pref.getProperty("fileeditor.config.breakAt");
     if ((value == null) || (value.equals(""))) return 60;
     try
     {
       return Integer.parseInt(value); } catch (NumberFormatException e) {
     }
     return 60;
   }
 
   public void setJAVATemplate(String template) {
     pref.setProperty("fileeditor.config.javatemplate", template); } 
   public void setCPPTemplate(String template) { pref.setProperty("fileeditor.config.cpptemplate", template); } 
   public void setCSHARPTemplate(String template) { pref.setProperty("fileeditor.config.csharptemplate", template); } 
   public void setJAVAExtension(String extension) { pref.setProperty("fileeditor.config.javaextension", extension); } 
   public void setCPPExtension(String extension) { pref.setProperty("fileeditor.config.cppextension", extension); } 
   public void setCSHARPExtension(String extension) { pref.setProperty("fileeditor.config.csharpextension", extension); } 
   public void setDirectoryName(String text) { pref.setProperty("fileeditor.config.dirName", text); } 
   public void setFileName(String text) { pref.setProperty("fileeditor.config.fileName", text); } 
   public void setBeginCut(String text) { pref.setProperty("fileeditor.config.beginCut", text); } 
   public void setEndCut(String text) { pref.setProperty("fileeditor.config.endCut", text); } 
   public void setSignatureFileName(String text) { pref.setProperty("fileeditor.config.signaturefilename", text); } 
   public void setProblemDescExtension(String text) { pref.setProperty("fileeditor.config.probdescfileextnsion", text); } 
   public void setOverrideFileName(boolean override) { pref.setProperty("fileeditor.config.overrideFileName", override ? "true" : "false"); } 
   public void setProvideBreaks(boolean provideBreaks) { pref.setProperty("fileeditor.config.provideBreaks", provideBreaks ? "true" : "false"); } 
   public void setLineComments(boolean lineComments) { pref.setProperty("fileeditor.config.lineComments", lineComments ? "true" : "false"); } 
   public void setWriteProblemDescFile(boolean probDescFile) { pref.setProperty("fileeditor.config.probdescfilewrite", probDescFile ? "true" : "false"); } 
   public void setHTMLDesc(boolean htmlDesc) { pref.setProperty("fileeditor.config.htmldesc", htmlDesc ? "true" : "false"); } 
   public void setBackup(boolean backup) { pref.setProperty("fileeditor.config.backup", backup ? "true" : "false"); } 
   public void setBreakAt(int breakAt) { pref.setProperty("fileeditor.config.breakAt", String.valueOf(breakAt)); }

   //2012/05/21 math add
   public void setExecuteScript(String text) { pref.setProperty(EXECUTESCRIPT, text); }
   
   private final String getStringProperty(String key, String defaultValue) {
     String value = pref.getProperty(key);
     return (value == null) || (value.equals("")) ? defaultValue : value;
   }
 
   private final boolean getBooleanProperty(String key, boolean defaultValue) {
     String value = pref.getProperty(key);
     return (value == null) || (value.equals("")) ? defaultValue : value.equals("true");
   }
 
   public void save() throws IOException {
     pref.savePreferences();
   }
 
   public static void main(String[] args) {
     Preferences pref = new Preferences();
     pref.setJAVATemplate("kdjf");
     try { pref.save(); } catch (Exception e) { System.out.println(e);
     }
   }
 }

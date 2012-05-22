package fileedit;
 import java.awt.Component;
 import java.awt.Font;
 import javax.swing.Box;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.JTextArea;
 
 public class FileEditorPanel extends JPanel
 {
   public FileEditorPanel(JTextArea textArea)
   {
     Common.setDefaultAttributes(this);
 
     JLabel statusLabel = Common.createJLabel("FileEdit v2.70 ext", new Font(getFont().getFontName(), 3, getFont().getSize()));
     JLabel reminderLabel = Common.createJLabel("*** Make sure you remotely compile before test/submit ***", new Font(getFont().getFontName(), 3, getFont().getSize()));
 
     JScrollPane scrollPane = Common.createJScrollPane(textArea);
     scrollPane.setBorder(Common.getTitledBorder("Activity Log"));
 
     Box status = Common.createHorizontalBox(new Component[] { statusLabel, Box.createHorizontalGlue(), reminderLabel }, false);
 
     add(scrollPane, "Center");
     add(status, "South");
   }
 
   public static void main(String[] s)
   {
     JFrame frame = new JFrame();
     frame.setDefaultCloseOperation(3);
     JTextArea text = new JTextArea();
     text.setForeground(Common.FG_COLOR);
     text.setBackground(Common.BG_COLOR);
     frame.getContentPane().add(new FileEditorPanel(text));
     frame.pack();
     frame.setVisible(true);
   }
 }

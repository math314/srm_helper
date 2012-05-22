package fileedit;
 import java.awt.BorderLayout;
 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Container;
 import java.awt.Dimension;
 import java.awt.Font;
 import java.awt.LayoutManager;
 import java.awt.Point;
 import java.awt.Toolkit;
 import java.awt.event.FocusAdapter;
 import java.awt.event.FocusEvent;
 import javax.swing.BorderFactory;
 import javax.swing.Box;
 import javax.swing.JButton;
 import javax.swing.JCheckBox;
 import javax.swing.JCheckBoxMenuItem;
 import javax.swing.JComboBox;
 import javax.swing.JLabel;
 import javax.swing.JList;
 import javax.swing.JMenuItem;
 import javax.swing.JOptionPane;
 import javax.swing.JPopupMenu;
 import javax.swing.JScrollPane;
 import javax.swing.JTextArea;
 import javax.swing.JTextField;
 import javax.swing.JTree;
 import javax.swing.border.Border;
 import javax.swing.border.TitledBorder;
 import javax.swing.text.JTextComponent;
 import javax.swing.tree.DefaultTreeCellRenderer;
 import javax.swing.tree.TreeModel;
 
 public class Common
 {
   public static final Color FG_COLOR = Color.white;
   public static final Color BG_COLOR = Color.black;
   public static final Color WPB_COLOR = Color.decode("0x333333");
   public static final Color TF_COLOR = Color.white;
   public static final Color TB_COLOR = Color.black;
   public static final Color HF_COLOR = Color.white;
   public static final Color HB_COLOR = Color.decode("0x003300");
   public static final Color THF_COLOR = Color.decode("0xCCFF99");
   public static final Color THB_COLOR = Color.black;
   public static final Color PT_COLOR = Color.decode("0xCCFF99");
   public static final Color PB_COLOR = Color.black;
   public static final int CONTESTCONSTANTSJAVA = 1;
   public static final int CONTESTCONSTANTSCPP = 3;
   public static final int CONTESTCONSTANTSCSHARP = 4;
   public static final Font DEFAULTFONT = new Font("SansSerif", 0, 12);
 
   public static final Box createHorizontalBox(Component[] a) {
     return createHorizontalBox(a, true);
   }
 
   public static final Box createHorizontalBox(Component[] a, boolean endGlue) {
     Box temp = Box.createHorizontalBox();
     if (a.length == 0) return temp;
 
     for (int x = 0; x < a.length - 1; x++) {
       temp.add(a[x]);
       temp.add(Box.createHorizontalStrut(5));
     }
 
     temp.add(a[(a.length - 1)]);
     if (endGlue) temp.add(Box.createHorizontalGlue());
 
     return temp;
   }
 
   public static final JLabel createJLabel(String text) {
     return createJLabel(text, DEFAULTFONT);
   }
 
   public static final JLabel createJLabel(String text, Font font) {
     return createJLabel(text, null, 2, font);
   }
 
   public static final JLabel createJLabel(String text, Dimension size) {
     return createJLabel(text, size, 2, DEFAULTFONT);
   }
 
   public static final JLabel createJLabel(String text, Dimension size, int alignment) {
     return createJLabel(text, size, alignment, DEFAULTFONT);
   }
 
   public static final JLabel createJLabel(String text, Dimension size, int alignment, Font font)
   {
     JLabel temp = new JLabel(text);
     temp.setForeground(FG_COLOR);
     temp.setBackground(WPB_COLOR);
     temp.setFont(font);
     temp.setHorizontalAlignment(alignment);
 
     if (size != null) {
       temp.setMinimumSize(size);
       temp.setPreferredSize(size);
       temp.setMaximumSize(size);
     }
 
     return temp;
   }
 
   public static final JCheckBox createJCheckBox(String text) {
     return createJCheckBox(text, false, DEFAULTFONT);
   }
 
   public static final JCheckBox createJCheckBox(String text, boolean selected, Font font) {
     JCheckBox temp = new JCheckBox(text);
     temp.setSelected(selected);
     temp.setFont(font);
     temp.setForeground(FG_COLOR);
     temp.setBackground(WPB_COLOR);
     return temp;
   }
 
   public static final JCheckBoxMenuItem createJCheckBoxMenuItem(String text, boolean selected) {
     return createJCheckBoxMenuItem(text, selected, DEFAULTFONT);
   }
 
   public static final JCheckBoxMenuItem createJCheckBoxMenuItem(String text, boolean selected, Font font) {
     JCheckBoxMenuItem temp = new JCheckBoxMenuItem(text, selected);
     temp.setFont(font);
     temp.setForeground(FG_COLOR);
     temp.setBackground(WPB_COLOR);
     return temp;
   }
 
   public static final JPopupMenu createJPopupMenu(JMenuItem[] items) {
     JPopupMenu temp = new JPopupMenu();
     for (int x = 0; x < items.length; x++) temp.add(items[x]);
     return temp;
   }
 
   public static final JTextArea createJTextArea(String text) {
     return createJTextArea(text, null, DEFAULTFONT);
   }
 
   public static final JTextArea createJTextArea(String text, boolean selectAll) {
     return createJTextArea(text, null, DEFAULTFONT, selectAll);
   }
 
   public static final JTextArea createJTextArea(String text, Dimension size) {
     return createJTextArea(text, size, DEFAULTFONT);
   }
 
   public static final JTextArea createJTextArea(String text, Font font) {
     return createJTextArea(text, null, font);
   }
 
   public static final JTextArea createJTextArea(String text, Dimension size, Font font) {
     return createJTextArea(text, size, font, true);
   }
   public static final JTextArea createJTextArea(String text, Dimension size, Font font, boolean selectAll) {
     JTextArea temp = new JTextArea(text);
     temp.setForeground(FG_COLOR);
     temp.setBackground(BG_COLOR);
     temp.setCaretColor(FG_COLOR);
     temp.setFont(font);
     temp.setBorder(BorderFactory.createLineBorder(FG_COLOR, 1));
     if (size != null) temp.setPreferredSize(size);
 
     if (selectAll) temp.addFocusListener(new SelectAll(temp));
     temp.setLineWrap(false);
     return temp;
   }
 
   public static final JTextField createJTextField(int size, Dimension max) {
     return createJTextField(size, max, DEFAULTFONT);
   }
 
   public static final JTextField createJTextField(int size, Dimension max, Font font) {
     JTextField temp = new JTextField(size);
     temp.setForeground(FG_COLOR);
     temp.setBackground(BG_COLOR);
     temp.setCaretColor(FG_COLOR);
     temp.setFont(font);
     temp.setBorder(BorderFactory.createLineBorder(FG_COLOR, 1));
     temp.setMaximumSize(max);
     temp.addFocusListener(new SelectAll(temp));
     return temp;
   }
 
   public static final JTextField createFixedJTextField(int size, Dimension max) {
     return createFixedJTextField(size, max, DEFAULTFONT);
   }
 
   public static final JTextField createFixedJTextField(int size, Dimension max, Font font) {
     JTextField temp = new JTextField(size);
     temp.setForeground(FG_COLOR);
     temp.setBackground(BG_COLOR);
     temp.setCaretColor(FG_COLOR);
     temp.setFont(font);
     temp.setBorder(BorderFactory.createLineBorder(FG_COLOR, 1));
     temp.setMaximumSize(max);
     PlainDocumentLimited doc = new PlainDocumentLimited(size);
     temp.setDocument(doc);
     temp.addFocusListener(new SelectAll(temp));
     return temp;
   }
 
   public static final JButton createJButton(String text) {
     return createJButton(text, null, DEFAULTFONT);
   }
 
   public static final JButton createJButton(String text, Dimension size) {
     return createJButton(text, size, DEFAULTFONT);
   }
 
   public static final JButton createJButton(String text, Font font) {
     return createJButton(text, null, font);
   }
 
   public static final JButton createJButton(String text, Dimension size, Font font) {
     JButton temp = new JButton(text);
     temp.setFont(font);
 
     if (size != null) {
       temp.setMinimumSize(size);
       temp.setPreferredSize(size);
       temp.setMaximumSize(size);
     }
     return temp;
   }
 
   public static final JComboBox<String> createJComboBox(String[] list)
   {
     return createJComboBox(list, DEFAULTFONT);
   }
 
   public static final JComboBox<String> createJComboBox(String[] list, Font font) {
     JComboBox<String> temp = createComboBox();
     temp.setFont(font);
     for (int x = 0; x < list.length; x++) temp.addItem(list[x]);
     return temp;
   }
 
   public static JComboBox<String> createComboBox() {
     JComboBox<String> jcb = new JComboBox<String>();
     jcb.setBackground(WPB_COLOR);
     jcb.setForeground(Color.white);
 
     jcb.setRenderer(new ContestListCellRenderer());
 
     return jcb;
   }
 
   public static final JScrollPane createJScrollPane(Component a) {
     return createJScrollPane(a, null, null);
   }
 
   public static final JScrollPane createJScrollPane(Component a, Dimension size) {
     return createJScrollPane(a, size, null);
   }
 
   public static final JScrollPane createJScrollPane(Component a, Dimension size, Border border)
   {
     JScrollPane temp = new JScrollPane(a);
     temp.setBackground(WPB_COLOR);
     temp.getViewport().setBackground(WPB_COLOR);
     if (size != null) temp.getViewport().setPreferredSize(size);
     if (border != null) temp.setBorder(border);
 
     return temp;
   }
 
   public static final void setDefaultAttributes(Container panel) {
     setDefaultAttributes(panel, new BorderLayout());
   }
 
   public static final void setDefaultAttributes(Container panel, LayoutManager layout) {
     panel.setLayout(layout);
     panel.setBackground(WPB_COLOR);
   }
 
   public static final JTree createJTree(TreeModel model)
   {
     JTree temp = new JTree(model);
     temp.setBackground(BG_COLOR);
     temp.setForeground(FG_COLOR);
     temp.setRootVisible(false);
 
     PopsTreeCellRenderer rend = new PopsTreeCellRenderer();
     rend.setTextNonSelectionColor(FG_COLOR);
     rend.setTextSelectionColor(FG_COLOR);
     rend.setBackgroundSelectionColor(HB_COLOR);
     rend.setBackgroundNonSelectionColor(BG_COLOR);
     rend.setBorderSelectionColor(HB_COLOR);
     rend.setBackground(BG_COLOR);
 
     temp.setCellRenderer(rend);
     temp.setDoubleBuffered(true);
     return temp;
   }
 
   public static final JList<Object> createJList(Object[] list)
   {
     JList<Object> temp = new JList<Object>(list);
     temp.setBackground(BG_COLOR);
     temp.setForeground(FG_COLOR);
     temp.setSelectionBackground(HB_COLOR);
     temp.setSelectionForeground(HF_COLOR);
 
     return temp;
   }
 
   public static Point adjustWindowLocation(Point location)
   {
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 
     if (location.x < 0) location.x = 0;
     if (location.x > screenSize.width - 125) location.x = (screenSize.width - 125);
 
     if (location.y < 0) location.y = 0;
     if (location.y > screenSize.height - 125) location.y = (screenSize.height - 125);
 
     return location;
   }
 
   public static Dimension adjustWindowSize(Point location, Dimension size)
   {
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 
     if (size.width < 0) size.width = 600;
     if (size.width + location.x > screenSize.width) screenSize.width -= location.x;
 
     if (size.height < 0) size.height = 400;
     if (size.height + location.y > screenSize.height) screenSize.height -= location.y;
 
     return size;
   }
 
   public static TitledBorder getTitledBorder(String title) {
     Border border = new RoundBorder(PB_COLOR, 5, true);
     MyTitledBorder tb = new MyTitledBorder(border, title, 1, 1);
     tb.setTitleColor(PT_COLOR);
     return tb;
   }
 
   public static void showMessage(String title, String msg, Component comp) {
     JOptionPane.showMessageDialog(comp, msg, title, 1);
   }
 
   public static boolean confirm(String title, String msg, Component comp) {
     int choice = JOptionPane.showConfirmDialog(comp, msg, title, 
       0, 
       2);
 
     return choice == 0;
   }
 
   public static String input(String title, String msg, Component comp)
   {
     String value = JOptionPane.showInputDialog(comp, msg, title, 
       3);
     return value;
   }
 
   private static class SelectAll extends FocusAdapter
   {
     JTextComponent parent;
 
     public SelectAll(JTextComponent parent)
     {
       this.parent = parent;
     }
 
     public void focusGained(FocusEvent e) {
       this.parent.selectAll();
     }
   }
 
   private static class PopsTreeCellRenderer extends DefaultTreeCellRenderer {
     public Dimension getPreferredSize() {
       Dimension ret = super.getPreferredSize();
       if (ret != null) ret = new Dimension(ret.width + 25, ret.height);
       return ret;
     }
   }
 }

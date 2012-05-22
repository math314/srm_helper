package fileedit;
 import java.awt.Component;
 import javax.swing.JLabel;
 import javax.swing.JList;
 import javax.swing.ListCellRenderer;
 
 public class ContestListCellRenderer extends JLabel
   implements ListCellRenderer<Object>
 {
   JList<Object> internalList = null;
 
   public ContestListCellRenderer()
   {
     setOpaque(true);
   }
 
   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
   {
     if ((this.internalList != list) && (list != null)) {
       this.internalList = list;
 
       list.setSelectionForeground(Common.TF_COLOR);
       list.setSelectionBackground(Common.TB_COLOR);
     }
 
     if (isSelected) {
       setForeground(Common.HF_COLOR);
       setBackground(Common.HB_COLOR);
     } else {
       setForeground(Common.TF_COLOR);
       setBackground(Common.TB_COLOR);
     }
 
     if (value != null) {
       setText(value.toString());
     }
 
     return this;
   }
 }
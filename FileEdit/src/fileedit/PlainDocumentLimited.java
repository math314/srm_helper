package fileedit;
 import javax.swing.text.AttributeSet;
 import javax.swing.text.BadLocationException;
 import javax.swing.text.PlainDocument;
 
 public class PlainDocumentLimited extends PlainDocument
 {
   private int limit;
   private boolean toUppercase = false;
 
   public PlainDocumentLimited(int limit)
   {
     this.limit = limit;
   }
 
   public PlainDocumentLimited(int limit, boolean upper)
   {
     this.limit = limit;
     this.toUppercase = upper;
   }
 
   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
     if (str == null) return;
 
     if (getLength() + str.length() <= this.limit) {
       if (this.toUppercase) str = str.toUpperCase();
       super.insertString(offset, str, attr);
     }
   }
 }

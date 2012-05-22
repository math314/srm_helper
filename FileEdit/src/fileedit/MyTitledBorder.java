package fileedit;
 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Font;
 import java.awt.FontMetrics;
 import java.awt.Graphics;
 import java.awt.Insets;
 import java.awt.Point;
 import java.awt.Rectangle;
 import javax.swing.border.Border;
 import javax.swing.border.TitledBorder;
 
 public class MyTitledBorder extends TitledBorder
 {
   protected static final int EDGE_SPACING = 3;
   protected static final int TEXT_SPACING = 3;
   protected static final int TEXT_INSET_H = 5;
   private Point textLoc = new Point();
 
   public MyTitledBorder(Border b, String t, int j, int p)
   {
     super(b, t, j, p);
   }
 
   private static boolean computeIntersection(Rectangle dest, int rx, int ry, int rw, int rh)
   {
     int x1 = Math.max(rx, dest.x);
     int x2 = Math.min(rx + rw, dest.x + dest.width);
     int y1 = Math.max(ry, dest.y);
     int y2 = Math.min(ry + rh, dest.y + dest.height);
     dest.x = x1;
     dest.y = y1;
     dest.width = (x2 - x1);
     dest.height = (y2 - y1);
 
     return (dest.width > 0) && (dest.height > 0);
   }
 
   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
   {
     Border border = getBorder();
 
     if ((getTitle() == null) || (getTitle().equals(""))) {
       if (border != null) {
         border.paintBorder(c, g, x, y, width, height);
       }
       return;
     }
 
     Rectangle grooveRect = new Rectangle(x + 3, y + 3, 
       width - 6, 
       height - 6);
     Font font = g.getFont();
     Color color = g.getColor();
 
     g.setFont(getFont(c));
 
     FontMetrics fm = g.getFontMetrics();
     int fontHeight = fm.getHeight();
     int descent = fm.getDescent();
     int ascent = fm.getAscent();
 
     int stringWidth = fm.stringWidth(getTitle());
     Insets insets;
     if (border != null)
       insets = border.getBorderInsets(c);
     else {
       insets = new Insets(0, 0, 0, 0);
     }
 
     int titlePos = getTitlePosition();
     switch (titlePos) {
     case 1:
{
       int diff = ascent + descent + (Math.max(3, 
         6) - 3);
       grooveRect.y += diff;
       grooveRect.height -= diff;
       this.textLoc.y = (grooveRect.y - (descent + 3));
       break;
}
     case 0:
     case 2:
{
       int diff = Math.max(0, ascent / 2 + 3 - 3);
       grooveRect.y += diff;
       grooveRect.height -= diff;
       this.textLoc.y = 
         (grooveRect.y - descent + 
         (insets.top + ascent + descent) / 2);
       break;
}
     case 3:
       this.textLoc.y = (grooveRect.y + insets.top + ascent + 3);
       break;
     case 4:
       this.textLoc.y = 
         (grooveRect.y + grooveRect.height - (
         insets.bottom + descent + 3));
       break;
     case 5:
       grooveRect.height -= fontHeight / 2;
       this.textLoc.y = 
         (grooveRect.y + grooveRect.height - descent + 
         (ascent + descent - insets.bottom) / 2);
       break;
     case 6:
       grooveRect.height -= fontHeight;
       this.textLoc.y = 
         (grooveRect.y + grooveRect.height + ascent + 
         3);
     }
 
     switch (getTitleJustification()) {
     case 0:
     case 1:
       this.textLoc.x = (grooveRect.x + 5 + insets.left);
       break;
     case 3:
       this.textLoc.x = 
         (grooveRect.x + grooveRect.width - (
         stringWidth + 5 + insets.right));
       break;
     case 2:
       this.textLoc.x = 
         (grooveRect.x + 
         (grooveRect.width - stringWidth) / 2);
     }
 
     if (border != null) {
       if ((titlePos == 2) || (titlePos == 5)) {
         Rectangle clipRect = new Rectangle();
 
         Rectangle saveClip = g.getClipBounds();
 
         clipRect.setBounds(saveClip);
         if (computeIntersection(clipRect, x, y, this.textLoc.x, height)) {
           g.setClip(clipRect);
           border.paintBorder(c, g, grooveRect.x, grooveRect.y, 
             grooveRect.width, grooveRect.height);
         }
 
         clipRect.setBounds(saveClip);
         if (computeIntersection(clipRect, this.textLoc.x + stringWidth, 0, 
           width - stringWidth - this.textLoc.x, height)) {
           g.setClip(clipRect);
           border.paintBorder(c, g, grooveRect.x, grooveRect.y, 
             grooveRect.width, grooveRect.height);
         }
 
         clipRect.setBounds(saveClip);
         if (titlePos == 2) {
           if (computeIntersection(clipRect, this.textLoc.x, grooveRect.y + insets.top, 
             stringWidth, height - grooveRect.y - insets.top)) {
             g.setClip(clipRect);
             border.paintBorder(c, g, grooveRect.x, grooveRect.y, 
               grooveRect.width, grooveRect.height);
           }
         }
         else if (computeIntersection(clipRect, this.textLoc.x, y, 
           stringWidth, height - insets.bottom - (
           height - grooveRect.height - grooveRect.y))) {
           g.setClip(clipRect);
           border.paintBorder(c, g, grooveRect.x, grooveRect.y, 
             grooveRect.width, grooveRect.height);
         }
 
         g.setClip(saveClip);
       }
       else {
         border.paintBorder(c, g, grooveRect.x, grooveRect.y, 
           grooveRect.width, grooveRect.height);
       }
     }
 
     g.setColor(getTitleColor());
     g.drawString(getTitle(), this.textLoc.x, this.textLoc.y);
 
     g.setFont(font);
     g.setColor(color);
   }
 }

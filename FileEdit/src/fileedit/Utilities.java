package fileedit;
 import com.topcoder.client.contestant.ProblemComponentModel;
 import com.topcoder.shared.language.Language;
 import com.topcoder.shared.problem.DataType;
 import java.io.*;
 import java.text.BreakIterator;
 import java.util.*;
 
 public class Utilities
 {
   public static final String lineEnding = System.getProperty("line.separator");
   private static final Preferences pref = new Preferences();
 
   public static final String getSource(Language language, ProblemComponentModel component, String fileName, String problemText)
   {
     String source = "";
 
     if (language.getId() == 1)
       source = source + pref.getJAVATemplate();
     else if (language.getId() == 3)
       source = source + pref.getCPPTemplate();
     else {
       source = source + pref.getCSHARPTemplate();
     }
 
     source = replaceAll(source, "$BEGINCUT$", pref.getBeginCut());
     source = replaceAll(source, "$ENDCUT$", pref.getEndCut());
     source = replaceAll(source, "$PROBLEMDESC$", pref.isWriteProblemDescFile() ? "" : problemText);
     source = replaceAll(source, "$CLASSNAME$", component.getClassName());
     source = replaceAll(source, "$METHODNAME$", component.getMethodName());
     source = replaceAll(source, "$RC$", component.getReturnType().getDescriptor(language));
     source = replaceAll(source, "$FILENAME$", fileName);
 
     StringBuffer parmString = new StringBuffer();
     DataType[] dataType = component.getParamTypes();
     String[] parms = component.getParamNames();
     char varName = 'a';
 
     for (int x = 0; x < dataType.length; x++) {
       if (parmString.length() != 0) parmString.append(", ");
       parmString.append(dataType[x].getDescriptor(language));
       parmString.append(' ');
       if (x < parms.length) {
         parmString.append(parms[x]);
       } else {
         parmString.append(varName);
         varName = (char)(varName + '\001');
       }
     }
 
     source = replaceAll(source, "$METHODPARMS$", parmString.toString());
     source = replaceLineNumber(source);
 
     return source;
   }
 
   public static final String replaceUserDefined(String source, Map<String,String> userDefinedTags)
   {
     for (Iterator<String> itr = userDefinedTags.keySet().iterator(); itr.hasNext(); ) {
       try
       {
         String tag = (String)itr.next();
         if (tag == null) {
           continue;
         }
         String replaceSource = (String)userDefinedTags.get(tag);
         if (replaceSource == null) {
           continue;
         }
         source = replaceAll(source, tag, replaceSource);
       } catch (ClassCastException e) {
         System.out.println("Error in userDefinedTags - either the tag or it's source was not a String type");
       }
     }
 
     return source;
   }
 
   public static final String replaceAll(String source, String text, String newText) {
     int pos = -1;
     while (true) {
       pos = source.indexOf(text);
       if (pos < 0) break;
       source = source.substring(0, pos) + newText + source.substring(pos + text.length());
     }
     return source;
   }
 
   public static final String replaceLineNumber(String source)
   {
     LineNumberReader in = new LineNumberReader(new StringReader(source));
     StringBuffer str = new StringBuffer(source.length());
     while (true)
     {
				String temp;
       try
       {
         temp = in.readLine();
         if (temp == null) break; 
       }
       catch (IOException e) {
         return source;
       }
 
       temp = replaceAll(temp, "$LINENUMBER$", String.valueOf(in.getLineNumber()));
       temp = replaceAll(temp, "$NEXTLINENUMBER$", String.valueOf(in.getLineNumber() + 1));
 
       str.append(temp);
       str.append(lineEnding);
     }
 
     return str.toString();
   }
 
   public static final String parseProblem(String problem)
   {
     if (problem.length() < 1) return problem;
 
     int breakAt = pref.isProvideBreaks() ? pref.getBreakAt() : 2147483647;
     boolean lineComments = pref.isLineComments();
 
     StringBuffer buf = new StringBuffer(problem.length() + 50);
     BreakIterator itr = BreakIterator.getLineInstance();
     itr.setText(problem);
 
     int start = itr.first();
     int end = itr.next();
 
     if (lineComments) buf.append("// ");
 
     while (end != -1)
     {
       int p1 = problem.indexOf("\r\n", start);
       if (p1 < 0) p1 = 2147483647;
 
       int p2 = problem.indexOf("\n", start);
       if (p2 < 0) p2 = 2147483647;
 
       int p3 = problem.indexOf("\r", start);
       if (p3 < 0) p3 = 2147483647;
 
       int pos = Math.min(p1, Math.min(p2, p3));
       if ((pos >= start) && (pos <= start + breakAt)) {
         buf.append(problem.substring(start, pos));
         buf.append(lineEnding);
         if (lineComments) buf.append("// ");
         start = pos;
         if ((pos + 2 < problem.length()) && (problem.substring(pos, pos + 2).equals("\r\n")))
           start += 2;
         else {
           start++;
         }
 
       }
       else
       {
         if (end - start >= breakAt)
         {
           int prev = itr.previous();
           if (start == prev)
             end = itr.next();
           else {
             end = prev;
           }
 
           buf.append(problem.substring(start, end));
           buf.append(lineEnding);
           if (lineComments) buf.append("// ");
           start = end;
         }
 
         end = itr.next();
       }
     }
 
     buf.append(problem.substring(start, itr.last()));
 
     return buf.toString();
   }
 
   public static void main(String[] args) {
     List<String> parms = new ArrayList<String>();
     parms.add("String");
     parms.add("String");
     parms.add("int");
     String probState = "PROBLEM STATEMENT\r\nWhen putting together a problem set, a writer must keep in mind the difficulty and length of a problem.  A good problem set is one with an easy, a middle, and a hard problem, but doesn't take too much or too little time to complete.\r\n\nYou will be given an input of three int[].  The first int[] consists of easy problem times, the second consists of middle problem times, and the third consists of hard problem times.  Return the number of legal problem set combinations, where a legal set contains exactly 1 easy, 1 middle and 1 hard problem, and the total time is between 60 and 75 inclusive.\r\n\r\nDEFINITION\r\nClass name: Chooser\r\nMethod name: numSets\r\nParameters: int[], int[], int[]\r\nReturns: int\r\nThe method signature is:\r\nint numSets(int[] easy, int[] middle, int[] hard)\r\nBe sure your method is public.\r\n\r\nTopCoder will ensure the following:\r\n*Each int[] will contain between 0 and 10 elements, inclusive.\r\n*Each element of easy will be an int between 5 and 15, inclusive.\r\n*Each element of middle will be an int between 15 and 45, inclusive.\r\n*Each element of hard will be an int between 30 and 55, inclusive.\r\n\r\nEXAMPLES\r{5,10,15}\r\n{15,25}\r\n{45}\r\nThere are 3*2*1=6 possible sets.  However, since 10+25+45=80 and 15+25+45=85, two of the sets are illegal, so the method returns 4.\r\n\r\n{5,5,5}\r\n{15,15,15}\r\n{45,45,45}\r\nThere are 3*3*3=27 possible sets, all legal.  The return value is 27.\r\n\r\n{5,5,5}\r\n{15,15,15}\r\n{45,45,35}\r\nThere are 27 possible sets again, but for this input any set with the 35 minute hard problem is too short.  Therefore there are only 3*3*2=18 legal sets, and the return value is 18.\r\n\r\n{}\r\n{15,25}\r\n{30,35,40}\r\nSince there are no easy problems, there are no legal problem sets.  The return value is 0.\n";
     try
     {
       System.setOut(new PrintStream(new FileOutputStream("run.log"))); } catch (IOException localIOException) {
     }
     System.out.println(parseProblem(probState));
   }
 }

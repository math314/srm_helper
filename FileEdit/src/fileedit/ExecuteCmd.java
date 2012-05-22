package fileedit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

public class ExecuteCmd {
	public static String Execute(String cmd,String filePath) throws InterruptedException,
    IOException {
	    ProcessBuilder pb = new ProcessBuilder("cmd", "/C", cmd, filePath);
	    Process p = pb.start();
	    
	    try{
		    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    Catcher c = new Catcher(br);
		    c.start();
		    p.waitFor();
		    return c.out.toString();
	    } finally{
		    p.getErrorStream().close();
		    p.getInputStream().close();
		    p.getOutputStream().close();
		    p.destroy();
	    }
	}
}

class Catcher extends Thread {
	  Reader in;
	  StringWriter out = new StringWriter();
	  public Catcher(Reader in) {
	    this.in = in;
	  }

	  public void run() {
	    int c;
	    try {
	      while ((c = in.read()) != -1) {
	        out.write((char)c);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	  }
}
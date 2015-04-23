import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Parser {
  private File file;
  public synchronized void setFile(File f) {
    file = f;
  }
  public synchronized File getFile() {
    return file;
  }
  private FileInputStream getFileInputStream() throws FileNotFoundException{
	  return new FileInputStream(file);
  }
  private FileOutputStream getFileOutputStream() throws FileNotFoundException {
	  return new FileOutputStream(file);
  }
  public String getContent(boolean withoutUnitCode) throws IOException {
	    FileInputStream i = getFileInputStream();
	    String output = "";
	    int data;
	    while ((data = i.read()) > 0) {
	    	if (withoutUnitCode){
	  	      if (data < 0x80) {
	  	        output += (char) data;
	  	      }
	    	} else {	  	     
	  	        output += (char) data;
	    	}

	    }
	    return output;
	  }
  
  public void saveContent(String content) throws IOException {
    FileOutputStream o = getFileOutputStream();
    for (int i = 0; i < content.length(); i += 1) {
      o.write(content.charAt(i));
    }
  }
}

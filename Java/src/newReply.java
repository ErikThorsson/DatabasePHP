import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;


public class newReply {

	public static void main(String[] args) throws MalformedURLException, IOException, ParseException{
		
		String pass = "eko";
		String id = "3";
		String insert = "('Zeus', 'how dare you!')";  
		
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/newReply.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
				    ps.print("id="+id);
				    ps.print("&pass="+ pass);
				    ps.print("&insert="+ insert);
				    //ps.print("&pass="+pass);
				 
				    // we have to get the input stream in order to actually send the request
				    con.getInputStream();
				 
				    // close the print stream
				    ps.close();
				    } catch (MalformedURLException e) {
				        e.printStackTrace();
				    } catch (IOException e) {
				        e.printStackTrace();
				    }
	}
}


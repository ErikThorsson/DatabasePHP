import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;


public class createUser {

	public static void main(String[] args) throws MalformedURLException, IOException, ParseException{
		
		String user = "Harry";
		String userPass = "Potter";
		String pass = "eko";
		String insert = "('" + user + "', '" + userPass +"')";  
		
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/createUser.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
				    ps.print("&pass="+ pass);
				    ps.print("&user="+ user);
				    ps.print("&userPass="+ userPass);
				    ps.print("&insert="+ insert);
				 
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
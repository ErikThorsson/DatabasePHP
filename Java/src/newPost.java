import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;


public class newPost {

	public static void main(String[] args) throws MalformedURLException, IOException, ParseException{
		
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		String ts = currentTimestamp.toString();
		
		String insert = "('eddie' ,'what up', 'nm', '33.8755', '84.3900', " + "'" + ts +"')";  
		
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/remoteInsert.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
				    ps.print("insert="+insert);
				 
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

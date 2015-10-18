import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;

import java.net.URL;
import java.net.URLConnection;

public class insert {
	public static void main(String[] args) throws MalformedURLException, IOException{
		String insert = "('test6','dvahjvsgsd','456', '2015-10-6 05:54:13')";
		 URL url = new URL("45.55.44.240/DatabasePHP/remoteInsert.php");
		 String urlParameters = "insert="+insert;    

//get
//		 HttpClient client = new DefaultHttpClient();
//		 HttpGet request = new HttpGet();
//		 request.setURI(new URI("45.55.44.240/DatabasePHP/remoteInsert.php"));
//	
		 
//authentication
//		 String data  = URLEncoder.encode("username", "UTF-8") 
//		 + "=" + URLEncoder.encode("root", "UTF-8");
//		 data += "&" + URLEncoder.encode("password", "UTF-8") 
//		 + "=" + URLEncoder.encode("eko", "UTF-8");
		 
		 URLConnection conn = url.openConnection(); 		 
		 OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
		 wr.write( urlParameters ); 
		 BufferedReader reader = new BufferedReader(new 
		 InputStreamReader(conn.getInputStream()));
		 
   }
		    	
}

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

	public class getUsers {
		public static void main(String[] args) throws MalformedURLException, IOException{
			//System.out.println(s);
			String user = "Freddy";
			String userPass = "a";
			String s = scrape(user, userPass);
			ArrayList<user> list = parse(s);
			//System.out.println(list.size());
			for(int i=0; i<list.size(); i++)
				System.out.println(list.get(i).user + "," + list.get(i).pass + ", " + list.get(i).photo);
		}
		
		public static String scrape(String user, String userPass) throws IOException {
				 try {
					    // open a connection to the site
					    URL url = new URL("http://45.55.44.240/DatabasePHP/AuthenticateUser.php");
					    URLConnection con = url.openConnection();
					    // activate the output
					    con.setDoOutput(true);
					    PrintStream ps = new PrintStream(con.getOutputStream());
					    // send your parameters to your site
					    ps.print("user="+user);
					    ps.print("&userPass="+userPass);

					    // we have to get the input stream in order to actually send the request
					    String s = IOUtils.toString(con.getInputStream());
						System.out.println(s);
					 
					    // close the print stream
					    ps.close();					 
					    return s;
					    
					    } catch (MalformedURLException e) {
					        e.printStackTrace();
					    } catch (IOException e) {
					        e.printStackTrace();
					    }
			return null;
		}
		
		public static ArrayList parse(String s) {
			ArrayList<user> list = new ArrayList<user>(); //to store post objects
			s = s.replace("[", ""); //split unnecessary chars out
			s = s.replace("]", "");
			s = s.replace("{", "");
			String[] st = s.split("}");
			
			for(int i=0; i<st.length -1;i++) {
				Pattern p = Pattern.compile("\"([^\"]*)\""); //split by double quotation marks
				Matcher m = p.matcher(st[i]);
				int counter = 0;
				user r = null;
				if(counter == 0) //only make a new object every 8 lines
				{
				r = new user();
				list.add(r);
				}
				
				
				while (m.find()) {
					counter++;
				  if(counter == 2)
				  {
					  r.user = m.group(1);
				  }
				  if(counter == 4)
				  {
					  r.pass = m.group(1);
				  }
				  if(counter == 6)
				  {
					  r.photo = m.group(1);
				  }
				}
			}
		    return list;
		}
	}

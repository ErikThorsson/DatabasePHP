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

	public class getReplies {
		public static void main(String[] args) throws MalformedURLException, IOException{
			String s = scrape("5");
			//System.out.println(s);
			ArrayList<reply> list = parse(s);
			//System.out.println(list.size());
			for(int i=0; i<list.size(); i++)
				System.out.println(list.get(i).user + ", " +list.get(i).txt + ", " + list.get(i).time_stamp);
		}

		public static String scrape(String id) throws IOException {
				 try {
					    // open a connection to the site
					    URL url = new URL("http://45.55.44.240/DatabasePHP/getReplies.php");
					    URLConnection con = url.openConnection();
					    // activate the output
					    con.setDoOutput(true);
					    PrintStream ps = new PrintStream(con.getOutputStream());
					    // send your parameters to your site
					    ps.print("id="+id);
					 
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
			ArrayList<reply> list = new ArrayList<reply>(); //to store post objects
			s = s.replace("[", ""); //split unnecessary chars out
			s = s.replace("]", "");
			s = s.replace("{", "");
			String[] st = s.split("}");
			
			for(int i=0; i<st.length -1;i++) {
				Pattern p = Pattern.compile("\"([^\"]*)\""); //split by double quotation marks
				Matcher m = p.matcher(st[i]);
				int counter = 0;
				reply r = null;
				if(counter == 0) //only make a new object every 8 lines
				{
				r = new reply();
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
					  r.txt = m.group(1);
				  }
				  if(counter == 6)
				  {
					  r.time_stamp = m.group(1);
				  }
				}
			}
		    return list;
		}
	}

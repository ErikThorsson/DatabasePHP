import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;


public class RG_DB_tests {
	public static void main(String[] args) throws MalformedURLException, IOException, ParseException{
	RG_DB_tests r = new RG_DB_tests();
	//r.newPost("('Batman' ,'Where are my parents?', \"I'm so lonely\", '163.566', '156.563')");
	int id = 5;
	//r.newReply("('" + id + "', 'Douglas Adams', \"oh that's just a horse in the bathroom\")");  
	//r.getReplies(5);
	//r.getRadius(50, 50, 50);
	r.getUsers("Harry", "Potter");
	}
	
	public RG_DB_tests() {}
	
	public void newPost(String s) {
		String pass = "eko";
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/newPost.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
				    ps.print("insert="+s + "&pass="+ pass);
				 
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
	
	public void newReply(String s) {
		String pass = "eko";
		
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/newReply.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
				    ps.print("&pass="+ pass);
				    ps.print("&insert="+ s);
				 
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
	
	public String getReplies(int id) {

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
			    String st = IOUtils.toString(con.getInputStream());
				System.out.println(st);
			 
				//System.out.println(s);
				ArrayList<reply> list = repliesParse(st);
				//System.out.println(list.size());
				for(int i=0; i<list.size(); i++)
					System.out.println(list.get(i).user + ", " +list.get(i).txt + ", " + list.get(i).time_stamp);
				
			    // close the print stream
			    ps.close();					 
			    return st;
			    
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
	return null;
	}
	
	public String getRadius(double dis, double lat, double longi) throws IOException {
		String s;
		 try {
			    // open a connection to the site
			    URL url = new URL("http://45.55.44.240/DatabasePHP/selectRadius.php");
			    URLConnection con = url.openConnection();
			    // activate the output
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    // send your parameters to your site
			    ps.print("dis="+ dis);
			    ps.print("&myLat="+ lat);
			    ps.print("&myLongi="+ longi);
			 
			    // we have to get the input stream in order to actually send the request
			    s = IOUtils.toString(con.getInputStream());
				System.out.println(s);
				
				ArrayList<post> list = radiusParse(s);
				for(int i=0; i<list.size(); i++)
					System.out.println(list.get(i).id + ", " + list.get(i).user + ", " +list.get(i).title + ", " +list.get(i).text 
							+ ", " +list.get(i).latitude + ", " +list.get(i).longitude + ", " +list.get(i).time_stamp);
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
	
	public String getUsers(String user, String userPass) throws IOException {
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
			 
				ArrayList<user> list = userParse(s);
				//System.out.println(list.size());
				for(int i=0; i<list.size(); i++)
					System.out.println(list.get(i).user + "," + list.get(i).pass + ", " + list.get(i).photo);
				
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
	
	public ArrayList repliesParse(String s) {
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
	
	public ArrayList radiusParse(String s) {
		ArrayList<post> list = new ArrayList<post>(); //to store post objects
		s = s.replace("[", ""); //split unnecessary chars out
		s = s.replace("]", "");
		s = s.replace("{", "");
		String[] st = s.split("}");
		
		for(int i=0; i<st.length -1;i++) {
			Pattern p = Pattern.compile("\"([^\"]*)\""); //split by double quotation marks
			Matcher m = p.matcher(st[i]);
			
			int counter = 0;
			post po = null;
			if(counter == 0) //only make a new object every 8 lines
			{
			po = new post();
			list.add(po);
			}	
			
			while (m.find()) { //loop through every split
				counter++;
			  if(counter == 2)
			  {
				  po.id = Integer.parseInt(m.group(1));
			  }
			  if(counter == 4)
			  {
				  po.user = m.group(1);
			  }
			  if(counter == 6)
			  {
				  po.title = m.group(1);
			  }
			  if(counter == 8) 
			  {
				  po.text = m.group(1);
			  }
			  if(counter == 10) 
			  {
				  po.latitude = Double.parseDouble(m.group(1));
			  }
			  if(counter == 12) 
			  {
				  po.longitude = Double.parseDouble(m.group(1));
			  }
			  if(counter == 14) 
			  {
				  po.time_stamp = m.group(1);
			  	  counter = 0; //reset for next group
			  }
			}
		}
	    return list;
	}
	
	public static ArrayList userParse(String s) {
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
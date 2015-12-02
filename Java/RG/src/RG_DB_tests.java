import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RG_DB_tests {
	public static void main(String[] args) throws MalformedURLException, IOException, ParseException{
	RG_DB_tests r = new RG_DB_tests();

	//r.newPost("('Freddy', \"?\", '33.7510001', '-84.3858811')");
	//String pS = r.getPosts();
	//r.deletePost("1");
	
	//r.newReply("('3', 'Douglas Adams', \"oh that's just a horse in the bathroom\")");  
	//r.newReply("('14', 'Erik', \"Oh god my hands...\")");  
	
	//String l = r.getReplies(3);
	/*[{"id":"6","user":"Douglas Adams","txt":"oh that's just a horse in the bathroom","ts":"2015-11-28 16:37:18"},
	{"id":"7","user":"Douglas Adams","txt":"oh that's just a horse in the bathroom","ts":"2015-11-28 16:41:42"}] 
	*/
	
	//r.deleteReply("3", "5");
	
	//String near = r.getRadius(6000, 0, 0);
	/*
	 * [{"id":"2","user":"Freddy","content":"?","lat":"33.751","lng":"-84.3859","ts":"2015-11-29 21:23:37"}
	 * ,{"id":"3","user":"Freddy","content":"?","lat":"33.751","lng":"-84.3859","ts":"2015-12-01 19:38:12"}] 
	*/
	
	//r.authenUser("Erik", "zzz");
	//output is BOOLEAN
	
	//r.newUser("Bill", "potato");
	//r.deleteUser("Batman");
	
	//r.getTime(-1,-1, -1, 6000 ,0,0);
	
	//r.getUser("Erik");
	/*
	 * [{"photo":"cfcd208495d565ef66e7dff9f98764da"}] 
	 */
	
	//r.postByUser("Freddy");
	//r.repliesByUser("Erik");
	
	//r.LIKEME("1", "Fred");
	
	//r.getLIKES("1");
	//[{"USER":"Fred"}] 
	}
	
	public String readInputStream(URLConnection con) throws IOException {
		InputStream in = con.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder result = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
		    result.append(line);
		}
		return result.toString();
	}
	public RG_DB_tests() {}
	
	/**
	 * returns JSON posts based on time or time and radius.
	 * Put -1 for each of the first 4 you won't be using. Min can only go up to 60.
	 * @param hr
	 * @param min
	 * @param d
	 * @param dis
	 * @param lat
	 * @param longi
	 */
	public void getTime(int hr, int min, int d, double dis, double lat, double longi) {
		 try {
			    URL url = new URL("http://45.55.44.240/DatabasePHP/getTime.php");
			    URLConnection con = url.openConnection();
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    Date date = new Date();   // given date
			    Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
			    calendar.setTime(date);   // assigns calendar to given date 
			    int hour  = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
			    int minute  = calendar.get(Calendar.MINUTE); 
			    int day = calendar.get(Calendar.DATE);
			    String mode = "";
			    if(hr != -1) {
			    	mode = "hour";
			    	hour = hour - hr;
			    }
			    if(min != -1)
			    {
			    	mode = "min";
			    	if(minute - min < 0)
			    	{
			    		minute = 60 + (minute - min);
			    		hour--;
			    	}
			    }
			    if(d != -1)
			    {
			    	day = day - d;
			    	mode = "day";
			    }
			    //System.out.println("mode is " + mode + " and the minute is " + (minute) + " and hour is " + (hour) + " and day is " + (day));
			    ps.print("min="+ (minute) + "&hr="+ (hour) + "&day="+ (day) + "&mode="+ (mode));
			    ps.print("&dis="+ dis);
			    ps.print("&myLat="+ lat);
			    ps.print("&myLongi="+ longi);
				String s = readInputStream(con);
			    System.out.println(s);
			    ps.close();
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    
			    }
}
	
	/**
	 * Takes in the post parameters : username, post title, text, latitude, longitude to create a post on the server
	 * @param s
	 */
	public void newPost(String s) {
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/newPost.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
				    ps.print("insert="+ s);
				 
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
	
	/**
	 * Returns the JSON string of posts
	 * @return
	 * @throws org.json.simple.parser.ParseException 
	 */
	public String getPosts() {
		 try {
			    // open a connection to the site
			    URL url = new URL("http://45.55.44.240/DatabasePHP/getPosts.php");
			    URLConnection con = url.openConnection();
			    // activate the output
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    String s = readInputStream(con);
			    System.out.println(s);
			    ps.close();	
			    
			    return s;
			    
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
	return null;
	}
	
	/**
	 * Deletes post with given id
	 * @param s
	 */
	public void deletePost(String s) {
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/deletePost.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
				    ps.print("delete="+ s);
				    // we have to get the input stream in order to actually send the request
				    con.getInputStream();
				    ps.close();
				    } catch (MalformedURLException e) {
				        e.printStackTrace();
				    } catch (IOException e) {
				        e.printStackTrace();
				    
				    }
	}
	
	/**
	 * deletes replies with postID, replyID
	 * @param postID
	 * @param replyID
	 */
	public void deleteReply(String postID, String replyID) {
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/deleteReply.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
				    ps.print("delete="+ replyID + "&pid="+ postID);
				    // we have to get the input stream in order to actually send the request
				    con.getInputStream();
				    ps.close();
				    } catch (MalformedURLException e) {
				        e.printStackTrace();
				    } catch (IOException e) {
				        e.printStackTrace();
				    
				    }
	}
	
	/**
	 * new Reply with parameters: postID, user txt
	 * @param s
	 */
	public void newReply(String s) {
		
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/newReply.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
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
	

	public void newUser(String user, String userPass){
		String insert = "('" + user + "', '" + userPass +"')";  
		
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/createUser.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
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
	
	public void deleteUser(String s) {
			 try {
				    // open a connection to the site
				    URL url = new URL("http://45.55.44.240/DatabasePHP/deleteUser.php");
				    URLConnection con = url.openConnection();
				    // activate the output
				    con.setDoOutput(true);
				    PrintStream ps = new PrintStream(con.getOutputStream());
				    // send your parameters to your site
				    ps.print("delete="+ s);
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
	
	/**
	 * get replies form given postID
	 * @param id
	 * @return
	 */
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
				String s = readInputStream(con);
				System.out.println(s);
			 
			    ps.close();					 
			    return s;
			    
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
	return null;
	}
	
	/**
	 * returns posts within given distance, from your latitude, longitude
	 * @param dis
	 * @param lat
	 * @param longi
	 * @return
	 * @throws IOException
	 */
	public String getRadius(double dis, double lat, double longi) throws IOException {
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
				String s = readInputStream(con);
				System.out.println(s);
				

			    ps.close();
				return s;

			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
	return null;
	}
	
	/**
	 * get user info given his name, pass. Only gives the photo hash name for now.
	 * @param user
	 * @param userPass
	 * @return
	 * @throws IOException
	 */
	public String getUser(String user) throws IOException {
		 try {
			    // open a connection to the site
			    URL url = new URL("http://45.55.44.240/DatabasePHP/getUserData.php");
			    URLConnection con = url.openConnection();
			    // activate the output
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    // send your parameters to your site
			    ps.print("user="+user);
			    // we have to get the input stream in order to actually send the request
				String s = readInputStream(con);
				System.out.println(s);
			 
				return s;
			    
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
	return null;
	}
	
	/**
	 * returns boolean for user
	 * @param user
	 * @param userPass
	 * @return
	 * @throws IOException
	 */
	public String authenUser(String user, String userPass) throws IOException {
		 try {
			    // open a connection to the site
			    URL url = new URL("http://45.55.44.240/DatabasePHP/authenticate_user.php");
			    URLConnection con = url.openConnection();
			    // activate the output
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    // send your parameters to your site
			    ps.print("user="+user);
			    ps.print("&userPass="+userPass);

			    // we have to get the input stream in order to actually send the request
				String s = readInputStream(con);
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
	
	public String postByUser(String user) throws IOException {
		 try {
			    // open a connection to the site
			    URL url = new URL("http://45.55.44.240/DatabasePHP/posts_by_user.php");
			    URLConnection con = url.openConnection();
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    ps.print("user="+user);
				String s = readInputStream(con);
				System.out.println(s);
			    ps.close();					 
			    return s;
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
	return null;
	}
	
	public String repliesByUser(String user) throws IOException {
		 try {
			    // open a connection to the site
			    URL url = new URL("http://45.55.44.240/DatabasePHP/replies_by_user.php");
			    URLConnection con = url.openConnection();
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    ps.print("user="+user);
				String s = readInputStream(con);
				System.out.println(s);
			    ps.close();					 
			    return s;
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
	return null;
	}
	
	/**
	 * this method is for user self validation purposes. 
	 * @param s
	 */
	public void LIKEME(String id, String user) {
		 try {
			    URL url = new URL("http://45.55.44.240/DatabasePHP/addLIKE.php");
			    URLConnection con = url.openConnection();
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    String insert = "('"+ id + "' , '"+ user + "')";
			    ps.print("insert="+ insert);
			    con.getInputStream();			 
			    ps.close();
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    
			    }
}
	
	public void getLIKES(String id) {
		 try {
			    URL url = new URL("http://45.55.44.240/DatabasePHP/getLIKES.php");
			    URLConnection con = url.openConnection();
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    ps.print("id="+ id);
			    String s = readInputStream(con);
				System.out.println(s);
				ps.close();
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    
			    }
}
	
	public void like_EXIST(String id) {
		 try {
			    URL url = new URL("http://45.55.44.240/DatabasePHP/getLIKES.php");
			    URLConnection con = url.openConnection();
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    ps.print("id="+ id);
			    String s = readInputStream(con);
				System.out.println(s);
				ps.close();
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    
			    }
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

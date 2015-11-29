import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RG_DB_tests {
	public static void main(String[] args) throws MalformedURLException, IOException, ParseException, org.json.simple.parser.ParseException{
	RG_DB_tests r = new RG_DB_tests();

	//r.newPost("('Freddy' ,'random', \"?\", '50.5', '67.0')");
	//String pS = r.getPosts();
	//r.deletePost("21");
	
	//r.newReply("('3', 'Douglas Adams', \"oh that's just a horse in the bathroom\")");  
	//String l = r.getReplies(3);
	//r.deleteReply("3", "5");
	
	//String near = r.getRadius(6000, 0, 0);
	
	//String s = r.getUser("a", "b");
	//r.authenUser("a", "b");
	//r.newUser("a", "b");
	//r.deleteUser("Batman");
	
	r.getTime(-1,-1, 6, -1 ,0,0);
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
	 * returns JSON posts based on time or time and radius. Parameters: hr, min, day, distance, lat, longi
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
	public String getUser(String user, String userPass) throws IOException {
		 try {
			    // open a connection to the site
			    URL url = new URL("http://45.55.44.240/DatabasePHP/getUserData.php");
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
}

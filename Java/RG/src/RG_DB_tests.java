import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import org.apache.commons.io.IOUtils;


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
	r.authenUser("a", "b");
	//r.newUser("a", "b");
	//r.deleteUser("Batman");
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

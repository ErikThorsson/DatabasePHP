import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

public class test {
	public static void main(String[] args) throws MalformedURLException, IOException{
		String s = scrape();
		//System.out.println(s);
		ArrayList<Post> list = parse(s);
		//System.out.println(list.size());
		for(int i=0; i<list.size(); i++)
			System.out.println(list.get(i).title + ", " + list.get(i).post + ", " +list.get(i).location);
	}
	public static String scrape() throws IOException {
		InputStream in = new URL( "http://45.55.44.240/DatabasePHP/select.php" ).openStream();
		String s;
		 try {
		   s = IOUtils.toString( in );
		 } finally {
		   IOUtils.closeQuietly(in);
		 }
		return s;
	}
	
	public static ArrayList parse(String s) {
		ArrayList<Post> list = new ArrayList<Post>(); //to store post objects
		s = s.replace("[", ""); //split unnecessary chars out
		s = s.replace("]", "");
		s = s.replace("{", "");
		String[] st = s.split("}");
		
		for(int i=0; i<st.length -1;i++) {
			Pattern p = Pattern.compile("\"([^\"]*)\""); //split by double quotation marks
			Matcher m = p.matcher(st[i]);
			
			int counter =0;
			Post po = null;
			if(counter == 0) //only make a new object every 8 lines
			{
			po = new Post();
			list.add(po);
			//System.out.println("made post");
			}
			
			
			while (m.find()) { //loop through every split
				//System.out.println(m.group(1));
				counter++;
			  if(counter == 2)
			  {
				  po.title = m.group(1);
				  //System.out.println(m.group(1));
			  }
			  if(counter == 4)
			  {
				  po.post = m.group(1);
			  }
			  if(counter == 6)
			  {
				  po.location = Integer.parseInt(m.group(1));
			  }
			  if(counter == 8) 
			  {
				  po.time_stamp = m.group(1);
			  	  counter = 0; //reset for next group
			  }
			}
		}
	    return list;
	}
}



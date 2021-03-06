import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

public class readDB {
	public static void main(String[] args) throws MalformedURLException, IOException{
		String s = scrape();
		//System.out.println(s);
		ArrayList<post> list = parse(s);
		//System.out.println(list.size());
		for(int i=0; i<list.size(); i++)
			System.out.println(list.get(i).id + ", " + list.get(i).user + ", " +list.get(i).title + ", " +list.get(i).text 
					+ ", " +list.get(i).latitude + ", " +list.get(i).longitude + ", " +list.get(i).time_stamp);
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
}



